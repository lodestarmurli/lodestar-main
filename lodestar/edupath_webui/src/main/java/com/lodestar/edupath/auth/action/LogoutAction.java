package com.lodestar.edupath.auth.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LogoutAction extends BaseAction
{
	private HttpServletRequest	request;

	@Override
	public String execute() throws Exception
	{

		UserSessionObject userSessionObject = (UserSessionObject) request.getSession().getAttribute(
				ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty());
		if (null != userSessionObject)
		{
			StringBuilder message = new StringBuilder();
			message.append(userSessionObject.getLoginId()).append(" logged out from the application");
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.LOGOUT, message.toString(), userSessionObject.getLoginId());
		}

		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		sessionMap.remove(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY);
		sessionMap.remove(ApplicationConstants.SessionProperty.HEADER_SESSION_MENU_LIST);
		sessionMap.remove(ApplicationConstants.SessionProperty.SIDEBAR_SESSION_MENU_LIST);
		sessionMap.remove(ApplicationConstants.SessionProperty.SELECTED_HEADER_ID);
		if (null == request.getSession(false))
		{
			return SUCCESS;
		}
		request.getSession(false).invalidate();

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;
	}
}
