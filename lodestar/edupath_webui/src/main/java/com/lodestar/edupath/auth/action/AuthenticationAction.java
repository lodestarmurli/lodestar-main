/*
 * @(#) AuthenticationAction.java
 *
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0
 * @Date 28may, 2014
 * @Author sujit
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */

package com.lodestar.edupath.auth.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.AuthenticationFormBean;
import com.lodestar.edupath.auth.service.AuthenticationService;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.util.ApplicationProperties;
import com.opensymphony.xwork2.ModelDriven;

public class AuthenticationAction extends BaseAction implements ModelDriven<AuthenticationFormBean>
{
	private static final long				serialVersionUID	= -7199192041920562909L;

	private static final Logger				OUT					= LoggerFactory.getLogger(AuthenticationAction.class.getName());

	private HttpServletRequest				request;
	private final AuthenticationFormBean	formBean			= new AuthenticationFormBean();
	

	@Override
	public String execute()
	{
		OUT.debug("Execution start from here");
		UserDetailDTO userDetailDTo = null;
		String name = "";
		boolean isReviewer = false;
		boolean isTrialStudent = true;
		boolean isStudentORType = false;
		UserSessionObject sessionObj = new UserSessionObject();
		try
		{
			AuthenticationService service = new AuthenticationService();
			try
			{
				userDetailDTo = service.getUserDetailTO(formBean.getLoginId());
			}
			catch (Exception e)
			{
				OUT.error("Exception : ", e);
				addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.check.errmsg.connection"));
				return LOGIN;
			}
			if (userDetailDTo == null && "admin".equals(formBean.getLoginId()))
			{
				OUT.debug("Going to create default admin user");
				formBean.setPassword("admin");
				userDetailDTo = service.insertAdminUser();
			}
			if (userDetailDTo == null)
			{
				OUT.info("Lodestar Login is not successful with login Id :" + formBean.getLoginId());
				addActionError(getText("com.edupath.loginscreen.message.logininvalid"));
				return LOGIN;
			}
			String dbPassword = new String(AESCipher.decrypt(userDetailDTo.getPassword()));
			if (!formBean.getPassword().equals(dbPassword))
			{
				OUT.info("Lodestar Login is not successful with login Id :" + formBean.getLoginId() + dbPassword.length());
				addActionError(getText("com.edupath.loginscreen.message.logininvalid"));
				return LOGIN;
			}

			name = service.getUserName(userDetailDTo);
			if (userDetailDTo.getRoleWeight() == RoleTypeEnum.FACILITATOR.getWeight())
			{
				isReviewer = service.getFacilitatorIsReviewerByUserId(userDetailDTo);
			}
			if (null == service.getTrialStudentType(userDetailDTo))
			{
				isTrialStudent = false;
			}
			else
			{
				sessionObj.setEmailId(service.getFatherEmailId());
			}
			if(null != service.getStudentORType(userDetailDTo))
			{
				isStudentORType = true;
			}
			
			
			OUT.info("Lodestar Login is successful with login Id :" + formBean.getLoginId());
			insertAuditLogger(name, userDetailDTo.getLoginId());
		}
		catch (Exception e)
		{
			OUT.error("Exception : ", e);
			addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.action.check.errmsg.connection"));
			return LOGIN;
		}
		sessionObj.setId(userDetailDTo.getId());
		sessionObj.setFullName(CommonUtil.replaceXMLEntities(name));
		sessionObj.setLoginId(userDetailDTo.getLoginId());
		sessionObj.setRoleId(userDetailDTo.getRoleId());
		sessionObj.setRoleWeight(userDetailDTo.getRoleWeight());
		sessionObj.setRoleTypeId(userDetailDTo.getRoleTypeId());
		sessionObj.setReviewer(isReviewer);
		sessionObj.setTrial(isTrialStudent);
		request.setAttribute(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty(), sessionObj);
		return SUCCESS;
	}

	private void insertAuditLogger(String name, String loginId)
	{
		StringBuilder message = new StringBuilder();
		message.append(name).append(" logged into the application ");
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.LOGIN, message.toString(), loginId);
	}

	@Override
	public AuthenticationFormBean getModel()
	{
		return formBean;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}
}
