/*
 * @(#) AuthorizationAction.java  
 * 
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0 
 * @Date Nov 8, 2011
 * @Author srishailam.p
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */

package com.lodestar.edupath.auth.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.AuthenticationService;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackStatusDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.PROGRAMTEST;
import com.lodestar.edupath.sessionfeedback.service.SessionFeedBackFromService;
import com.lodestar.edupath.util.ApplicationProperties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AuthorizationAction extends ActionSupport implements ServletRequestAware
{
	private static final long												serialVersionUID	= -2555041140157370721L;

	private static final Logger												OUT					= LoggerFactory.getLogger(AuthorizationAction.class.getName());

	private HttpServletRequest												request				= null;

	private Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>>	menuItemsByRole		= new HashMap<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>>();

	@Override
	public String execute() throws Exception
	{
		Object userSession = request.getAttribute(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty());
		try
		{
			if (userSession == null)
			{
				return LOGIN;
			}
			UserSessionObject userSessionObj = (UserSessionObject) userSession;
			AuthenticationService service = new AuthenticationService();

			int roleId = userSessionObj.getRoleId();
			int roleWeight = userSessionObj.getRoleWeight();
			boolean isStreamSelector_trail=false;
			boolean isEgineeringBranchSelector_trail=false;
			boolean isCareerDegreeDiscovery=false;
			boolean isProgramTestStudent=false;
			userSessionObj.setStreamSelector(false);
			OUT.debug("RoleId:    " + roleId);

			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			userSessionObj.setSessionId(request.getSession().getId());
			sessionMap.put(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty(), userSession);
			sessionMap.put(ApplicationConstants.SessionProperty.SELECTED_SESSION_STUDENT_ID.getProperty(), 0);
			sessionMap.put(ApplicationConstants.SessionProperty.STUDENT_SESSION_OBJECT.getProperty(), new StudentSessionObject());
			sessionMap.put("streamSelector", userSessionObj.isStreamSelector());
			if (roleWeight == RoleTypeEnum.USER.getWeight())
			{
				sessionMap.put(ApplicationConstants.SessionProperty.IS_SESSION_STUDENT.getProperty(), true);
				SessionScheduleDetailsDTO scheduleDetailsDTO = new SessionScheduleDetailsDAO().getSessionByUserId(roleWeight, userSessionObj.getId());
				if (null != scheduleDetailsDTO)
				{
					if (scheduleDetailsDTO.isSession1FaciCompleted())
					{
						StudentSessionObject student = new StudentSessionObject();
						student.setSessionOneCompleted(1);
						sessionMap.put(ApplicationConstants.SessionProperty.STUDENT_SESSION_OBJECT.getProperty(), student);
					}
				}
				//Start Sasedeve edited By Mrutyunjaya On Date 31-07-2017
				
				StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userSessionObj.getId());
				if(studentDetailsByUserId.getSource().equalsIgnoreCase(PROGRAMTEST.STREAMSELECTOR.getSource()))
				{
					isStreamSelector_trail=true;
					userSessionObj.setStreamSelector(true);
					userSessionObj.setProgramTestStudent(true);	
				}
				else if(studentDetailsByUserId.getSource().equalsIgnoreCase(PROGRAMTEST.ENGINEERINGBRANCHSELECTOR.getSource()))
				{
					isEgineeringBranchSelector_trail=true;
					userSessionObj.setEngineeringBranchSelector(true);
					userSessionObj.setProgramTestStudent(true);					
				}
				else if(studentDetailsByUserId.getSource().equalsIgnoreCase(PROGRAMTEST.CAREERDEGREEDISCOVERY.getSource()))
				{
					isCareerDegreeDiscovery=true;
					userSessionObj.setCareerDegreeDiscovery(true);
					userSessionObj.setProgramTestStudent(true);					
				}
				else if(studentDetailsByUserId.getSource().equalsIgnoreCase(PROGRAMTEST.CAREERFITMENT.getSource()))
				{
					userSessionObj.setCareerFitment(true);
					userSessionObj.setProgramTestStudent(true);					
				}
				if(studentDetailsByUserId!=null && studentDetailsByUserId.getClassId()==5)
				{
					sessionMap.put(ApplicationConstants.SessionProperty.Is_Twelve_Plus.getProperty(),true);
					
					
					sessionMap.put(ApplicationConstants.SessionProperty.Is_IntresttestCompleted.getProperty(),new SessionFeedBackFromService().GetInteresttestcompletd(userSessionObj.getId()));
					
					sessionMap.put(ApplicationConstants.SessionProperty.Is_ApptitudetestCompleted.getProperty(),new SessionFeedBackFromService().GetApptitudeTestCompleted(userSessionObj.getId()));
				}
				else
				{
					sessionMap.put(ApplicationConstants.SessionProperty.Is_Twelve_Plus.getProperty(),false);
					
					sessionMap.put(ApplicationConstants.SessionProperty.Is_IntresttestCompleted.getProperty(),false);
					sessionMap.put(ApplicationConstants.SessionProperty.Is_ApptitudetestCompleted.getProperty(),false);
					
					
				}
				if(studentDetailsByUserId.getClassId() != 0)
				{
					userSessionObj.setClassId(studentDetailsByUserId.getClassId());
				}
				
				
				//End Sasedeve edited By Mrutyunjaya On Date 31-07-2017
			}
			else
			{
				sessionMap.put(ApplicationConstants.SessionProperty.IS_SESSION_STUDENT.getProperty(), false);
				sessionMap.put("sessionOneCompleted", 0);
				
				
				sessionMap.put(ApplicationConstants.SessionProperty.Is_IntresttestCompleted.getProperty(),false);
				sessionMap.put(ApplicationConstants.SessionProperty.Is_ApptitudetestCompleted.getProperty(),false);
				
				
			}
			this.menuItemsByRole = service.getTabbedMenuItemsByRole(roleId, roleWeight, userSessionObj.getReviewer(), userSessionObj.isTrial(),userSessionObj);
			
			//Start Sasedeve edited By Mrutyunjaya On Date 20-07-2017
			SessionFeedBackStatusDTO Sessionfeedbackstatus = null;
			if(roleWeight == RoleTypeEnum.USER.getWeight())
			{
				Sessionfeedbackstatus=	new SessionFeedBackFromService().GetSessionFeedBackStatus(userSessionObj.getId());
			}
			
			//End Sasedeve edited By Mrutyunjaya On Date 20-07-2017
			if (this.menuItemsByRole.size() == 0)
			{
				addActionError(ApplicationProperties.getInstance().getProperty("com.edupath.loginscreen.unauthorized.reason1"));
				return ERROR;
			}
			Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap = null;
			Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> sidebarMenuMap = null;

			if (this.menuItemsByRole.size() > 0)
			{
				sessionMap.put(ApplicationConstants.SessionProperty.HEADER_SESSION_MENU_LIST.getProperty(), menuItemsByRole.get(ApplicationConstants.HEADER_LIST));
				sessionMap.put(ApplicationConstants.SessionProperty.SIDEBAR_SESSION_MENU_LIST.getProperty(), menuItemsByRole.get(ApplicationConstants.SIDEBAR_LIST));

				//Start Sasedeve edited By Mrutyunjaya On Date 20-07-2017
				
				
				if(Sessionfeedbackstatus!=null && Sessionfeedbackstatus.getSessionOneFeedBackStatus()==1)
				{
					sessionMap.put(ApplicationConstants.SessionProperty.Session_One_FeedBack.getProperty(),true);
				}
				else
				{
					sessionMap.put(ApplicationConstants.SessionProperty.Session_One_FeedBack.getProperty(),false);
				}
				
				
				
				if(Sessionfeedbackstatus!=null && Sessionfeedbackstatus.getSessionTwoFeedBackStatus()==1)
				{
					sessionMap.put(ApplicationConstants.SessionProperty.Session_Two_FeedBack.getProperty(),true);
				}
				else
				{
					sessionMap.put(ApplicationConstants.SessionProperty.Session_Two_FeedBack.getProperty(),false);
				}
				
				
				
				
				
				
				//End Sasedeve edited By Mrutyunjaya On Date 20-07-2017
				int parentMenuItemId = 0;
				int menuItemId = 0;
				String chiledActionPath = "";
				String parentActionPath = "";
				int headerSessionId = 0;

				if (null != menuItemsByRole.get(ApplicationConstants.HEADER_LIST))
				{
					Map<String, String> headerSubTitle = service.getHeaderSubTitle(isProgramTestStudent);
					sessionMap.put(ApplicationConstants.SessionProperty.HEADER__SESSION_SUB_TITLE.getProperty(), headerSubTitle);

					headerMenuMap = menuItemsByRole.get(ApplicationConstants.HEADER_LIST);
					sidebarMenuMap = menuItemsByRole.get(ApplicationConstants.SIDEBAR_LIST);
					headerSessionId = service.getHeaderId(userSessionObj, headerMenuMap, roleWeight);
					if (headerSessionId == 0)
					{
						headerSessionId = menuItemsByRole.get(ApplicationConstants.HEADER_LIST).entrySet().iterator().next().getKey().getId();
					}
					sessionMap.put(ApplicationConstants.SessionProperty.SELECTED_HEADER_ID.getProperty(), headerSessionId);
					sessionMap.put(ApplicationConstants.SessionProperty.ACTIVE_HEADER.getProperty(), headerSessionId);

					for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> applicationMap : sidebarMenuMap.entrySet())
					{
						if (null != applicationMap.getKey().getParentId() && applicationMap.getKey().getParentId() == headerSessionId)
						{
							menuItemId = applicationMap.getKey().getId();
							chiledActionPath = applicationMap.getKey().getActionPath();
							break;
						}
					}
					for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuEntry : menuItemsByRole.get(ApplicationConstants.HEADER_LIST).entrySet())
					{
						if (headerSessionId == headerMenuEntry.getKey().getId() && headerMenuEntry.getKey().getRefName().equalsIgnoreCase("Summary"))
						{
							parentMenuItemId = headerMenuEntry.getKey().getId();
							parentActionPath = headerMenuEntry.getKey().getActionPath();
							break;
						}
					}
				}
				if (null != menuItemsByRole.get(ApplicationConstants.SIDEBAR_LIST))
				{
					if (parentMenuItemId == 0 && null != menuItemsByRole.get("SidebarList").entrySet().iterator().next().getKey())
					{
						parentMenuItemId = menuItemsByRole.get("SidebarList").entrySet().iterator().next().getKey().getId();
						parentActionPath = menuItemsByRole.get("SidebarList").entrySet().iterator().next().getKey().getActionPath();
					}
					if (null != menuItemsByRole.get("SidebarList").entrySet().iterator().next().getValue()
							&& !menuItemsByRole.get("SidebarList").entrySet().iterator().next().getValue().isEmpty())
					{
						if (menuItemId != 0)
						{
							menuItemId = menuItemsByRole.get("SidebarList").entrySet().iterator().next().getValue().get(0).getId();
							chiledActionPath = menuItemsByRole.get("SidebarList").entrySet().iterator().next().getValue().get(0).getActionPath();
						}
					}
				}

				request.setAttribute("parentMenuItemId", parentMenuItemId);
				request.setAttribute("menuItemId", menuItemId);
				request.setAttribute("chiledActionPath", chiledActionPath);
				request.setAttribute("parentActionPath", parentActionPath);


				request.setAttribute("menuList", menuItemsByRole);
				OUT.debug("Menu header{}, sidebar{}: ", menuItemsByRole.get("HeaderList"), menuItemsByRole.get("SidebarList"));
				return SUCCESS;
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		request.getSession().invalidate();
		return LOGIN;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.request = arg0;
	}

	/**
	 * @param menuItemsByRole
	 *            the menuItemsByRole to set
	 */
	public void setMenuItemsByRole(Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> menuItemsByRole)
	{
		this.menuItemsByRole = menuItemsByRole;
	}

	/**
	 * @return the menuItemsByRole
	 */
	public Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> getMenuItemsByRole()
	{
		return menuItemsByRole;
	}
}
