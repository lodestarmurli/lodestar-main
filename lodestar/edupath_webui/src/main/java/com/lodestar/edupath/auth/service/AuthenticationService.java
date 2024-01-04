package com.lodestar.edupath.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.subadmin.SubAdminDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.SubAdminDTO;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

public class AuthenticationService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(AuthenticationService.class.getName());
	private String				emailId;

	public UserDetailDTO getUserDetailTO(String loginId) throws Exception
	{
		UserDetailDTO userDetailsTO = new UserDetailDTO();
		userDetailsTO.setLoginId(loginId);
		IModel resultAsObject = MyBatisManager.getInstance().getResultAsObject(UserDetailDTO.GET_USER_BY_LOGINID, userDetailsTO);
		UserDetailDTO userTO = (UserDetailDTO) resultAsObject;
		return userTO;
	}

	public UserDetailDTO insertAdminUser()
	{
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		try
		{
			String password = "admin";
			userDetailDTO.setLoginId("admin");
			userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
			userDetailDTO.setRoleId(1);
			userDetailDTO.setIsActive("Y");
			userDetailDTO.setUserType(UserTypeEnum.ADMIN.getDisplayName());
			MyBatisManager.getInstance().insert(UserDetailDTO.ADD_USER_DETAILS, userDetailDTO);

			return userDetailDTO;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return null;
		}
		finally
		{
			OUT.debug("Default Admin user creation: {} ", userDetailDTO.toString());
		}
	}

	public Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> getTabbedMenuItemsByRole(int roleId, int roleWeight, boolean isReviewer, boolean isTrial,UserSessionObject userSessionObj)
			throws Exception
	{
		Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> finalMenuMap = new HashMap<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>>();
		ApplicationMenuDTO app = new ApplicationMenuDTO();
		app.setRoleWeight(roleWeight);
		List<ApplicationMenuDTO> menuList = MyBatisManager.getInstance().getResultAsList(ApplicationMenuDTO.GET_APPLICATIONMENU_DETAILS_BY_ROLE, app);
		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap = new LinkedHashMap<ApplicationMenuDTO, List<ApplicationMenuDTO>>();
		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> sidebarMenuMap = new LinkedHashMap<ApplicationMenuDTO, List<ApplicationMenuDTO>>();
		List<ApplicationMenuDTO> childSideBarMenuList = null;

		if (RoleTypeEnum.ADMIN.getWeight() == roleWeight || RoleTypeEnum.SUBADMIN.getWeight() == roleWeight)
		{
			if (null != menuList && !menuList.isEmpty())
			{
				for (ApplicationMenuDTO applicationMenuDTO : menuList)
				{
					childSideBarMenuList = new ArrayList<ApplicationMenuDTO>();
					sidebarMenuMap.put(applicationMenuDTO, childSideBarMenuList);
				}
			}

			finalMenuMap.put(ApplicationConstants.SIDEBAR_LIST, sidebarMenuMap);
		}
		//start by bharath on 23-10-2019
		if (RoleTypeEnum.REPORTADMIN.getWeight() == roleWeight)
		{
			if (null != menuList && !menuList.isEmpty())
			{
				for (ApplicationMenuDTO applicationMenuDTO : menuList)
				{
					childSideBarMenuList = new ArrayList<ApplicationMenuDTO>();
					sidebarMenuMap.put(applicationMenuDTO, childSideBarMenuList);
				}
			}

			finalMenuMap.put(ApplicationConstants.SIDEBAR_LIST, sidebarMenuMap);
		}
		//end by bharath on 23-10-2019
		if (RoleTypeEnum.USER.getWeight() == roleWeight || RoleTypeEnum.STUDENTOR.getWeight() == roleWeight || RoleTypeEnum.FACILITATOR.getWeight() == roleWeight)
		{
			if (null != menuList && !menuList.isEmpty())
			{
				for (ApplicationMenuDTO applicationMenuDTO : menuList)
				{
					// Removed TellUsMoreform menu when student is trail
					if (isTrial && RoleTypeEnum.USER.getWeight() == roleWeight
							&& ApplicationConstants.APP_MENU_TELLUS_MORE_FORM_REFNAME.equalsIgnoreCase(applicationMenuDTO.getRefName()))
					{
						continue;
					}
					childSideBarMenuList = new ArrayList<ApplicationMenuDTO>();
					if (null == applicationMenuDTO.getParentId())
					{
						
						if(userSessionObj.isProgramTestStudent() && ApplicationConstants.PRESESSION.equalsIgnoreCase(applicationMenuDTO.getRefName()) )
						{
							OUT.debug("bharath AuthenticationService 1 isStreamSelector_trial:{} applicationMenuDTO:{}",userSessionObj.isProgramTestStudent(),applicationMenuDTO);
							applicationMenuDTO.setDisplayName("");
							headerMenuMap.put(applicationMenuDTO, childSideBarMenuList);
						}
						else if(!userSessionObj.isProgramTestStudent())
						{
							OUT.debug("bharath AuthenticationService 2 isStreamSelector_trial:{} applicationMenuDTO:{}",userSessionObj.isProgramTestStudent(),applicationMenuDTO);
							headerMenuMap.put(applicationMenuDTO, childSideBarMenuList);
						}
						else {
							continue;
						}
						 
						
					}
					else
					{
						for (ApplicationMenuDTO applicationMenuDTO2 : menuList)
						{
							if (null != applicationMenuDTO2.getParentId() && applicationMenuDTO2.getParentId() == applicationMenuDTO.getId())
							{
								childSideBarMenuList.add(applicationMenuDTO2);
							}
						}
						if (applicationMenuDTO.getMenuLevel() == 1)
						{	//updated by bharath on 15-10-2019
							
							 
							
							if (applicationMenuDTO.getRefName().equalsIgnoreCase(ApplicationConstants.APP_MENU_REVIEW_REFNAME) || applicationMenuDTO.getRefName().equalsIgnoreCase(ApplicationConstants.APP_MENU_COMPLETED_REPORT_REFNAME))
							{
								if (isReviewer)
								{
									sidebarMenuMap.put(applicationMenuDTO, childSideBarMenuList);
								}
							}
							else if(ApplicationConstants.EBFAVOURITESUBJECT.equalsIgnoreCase(applicationMenuDTO.getRefName()))
							{
								if (userSessionObj.isEngineeringBranchSelector())
								{
									sidebarMenuMap.put(applicationMenuDTO, childSideBarMenuList);
								}
							}
							else if(ApplicationConstants.CCDSTREAMSELECT.equalsIgnoreCase(applicationMenuDTO.getRefName()))
							{
								if (userSessionObj.isCareerDegreeDiscovery())
								{
									sidebarMenuMap.put(applicationMenuDTO, childSideBarMenuList);
								}
							}

							else
							{
								sidebarMenuMap.put(applicationMenuDTO, childSideBarMenuList);
							}						
						}
					}
				}
			}
//			if(isStreamSelector_trail)
//			{
//				headerMenuMap=null;
//			}
			finalMenuMap.put(ApplicationConstants.HEADER_LIST, headerMenuMap);
			finalMenuMap.put(ApplicationConstants.SIDEBAR_LIST, sidebarMenuMap);
			
		}

		return finalMenuMap;
	}

	public String getUserName(UserDetailDTO userDetailDTO) throws Exception
	{
		OUT.info("Find the user name with user id {}", userDetailDTO.getId());
		String name = "";
		if (userDetailDTO.getRoleWeight() == RoleTypeEnum.ADMIN.getWeight())
		{
			name = UserTypeEnum.ADMIN.getDisplayName();
		}
		else if (userDetailDTO.getRoleWeight() == RoleTypeEnum.USER.getWeight())
		{
			StudentDetailsDTO studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userDetailDTO);
			name = studentDTO.getName();
		}
		else if (userDetailDTO.getRoleWeight() == RoleTypeEnum.FACILITATOR.getWeight())
		{
			FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsByUserId(userDetailDTO);
			name = facilitatorDetailsDTO.getName();
		}
		else if (userDetailDTO.getRoleWeight() == RoleTypeEnum.SUBADMIN.getWeight())
		{
			SubAdminDTO subAdminDTO = new SubAdminDAO().getSubAdminDetailsByUserId(userDetailDTO);
			name = subAdminDTO.getName();
		}
		return name;

	}

	public int getHeaderId(UserSessionObject userSessionObj, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap, int roleWeight) throws Exception
	{
		int headerSessionId = 0;
		SessionScheduleDetailsDTO scheduleDetailsDTO = null;
		OUT.info("Get Session information for Header with {}", userSessionObj.getFullName());
		scheduleDetailsDTO = new SessionScheduleDetailsDAO().getSessionByUserId(userSessionObj.getRoleWeight(), userSessionObj.getId());
		String sessionCompleted = "";
		if (null != scheduleDetailsDTO)
		{
			if (scheduleDetailsDTO.isPreSessionCompleted())
			{
				sessionCompleted = SessionTypeEnum.PRESESSION.getSessionName();
			}
			if (scheduleDetailsDTO.isSession1FaciCompleted())
			{
				sessionCompleted = SessionTypeEnum.SESSION1.getSessionName();
			}
			if (scheduleDetailsDTO.isSession2FaciCompleted())
			{
				sessionCompleted = SessionTypeEnum.SESSION2.getSessionName();
			}
			if (scheduleDetailsDTO.isSession3FaciCompleted())
			{
				sessionCompleted = SessionTypeEnum.SESSION3.getSessionName();
			}
			if (RoleTypeEnum.USER.getWeight() == roleWeight)
			{
				if (scheduleDetailsDTO.getStudentFeedback() != null && scheduleDetailsDTO.getParentFeedback() != null)
				{
					sessionCompleted = SessionTypeEnum.SUMMARY.getSessionName();
				}
			}
			else if (scheduleDetailsDTO.isReportGenerated())
			{
				sessionCompleted = SessionTypeEnum.SUMMARY.getSessionName();
			}
			if (!sessionCompleted.isEmpty() && !sessionCompleted.equals(""))
			{
				for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> appMenu : headerMenuMap.entrySet())
				{
					// Presession always disable clickable
					if (appMenu.getKey().getRefName().equalsIgnoreCase(SessionTypeEnum.PRESESSION.getSessionName()))
					{
						appMenu.getKey().setEnableClick(false);
					}
					if (appMenu.getKey().getRefName().equalsIgnoreCase(sessionCompleted))
					{
						headerSessionId = appMenu.getKey().getId();
						break;
					}
					// enable only for user role and
					else if (RoleTypeEnum.USER.getWeight() == roleWeight
							&& (sessionCompleted.equalsIgnoreCase(SessionTypeEnum.SESSION3.getSessionName()) || sessionCompleted
									.equalsIgnoreCase(SessionTypeEnum.SUMMARY.getSessionName())))
					{
						appMenu.getKey().setEnableClick(false);
					}
					else if (RoleTypeEnum.FACILITATOR.getWeight() == roleWeight && sessionCompleted.equalsIgnoreCase(SessionTypeEnum.SESSION3.getSessionName()))
					{
						appMenu.getKey().setEnableClick(false);
					}
				}
			}
		}
		return headerSessionId;
	}

	/**
	 * @param userSessionObj
	 * @param headerMenuMap
	 * @param roleWeight
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public boolean disableHeaderMenu(UserSessionObject userSessionObj, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap, int roleWeight, int studentId)
			throws Exception
	{
		boolean isCanChangeCart = true;
		SessionScheduleDetailsDTO scheduleDetailsDTO = null;
		OUT.info("Get Session information for Header with {}", userSessionObj.getFullName());
		scheduleDetailsDTO = new SessionScheduleDetailsDAO().getSessionDetailsByStudentId(studentId);
		String sessionCompleted = "";
		if (null != scheduleDetailsDTO)
		{
			if (scheduleDetailsDTO.isPreSessionCompleted())
			{
				sessionCompleted = SessionTypeEnum.PRESESSION.getSessionName();
			}
			if (scheduleDetailsDTO.isSession1FaciCompleted())
			{
				sessionCompleted = SessionTypeEnum.SESSION1.getSessionName();
			}
			if (scheduleDetailsDTO.isSession2FaciCompleted())
			{
				sessionCompleted = SessionTypeEnum.SESSION2.getSessionName();
			}
			if (scheduleDetailsDTO.isSession3FaciCompleted())
			{
				sessionCompleted = SessionTypeEnum.SESSION3.getSessionName();
			}
			if (RoleTypeEnum.USER.getWeight() == roleWeight)
			{
				if (scheduleDetailsDTO.getStudentFeedback() != null && scheduleDetailsDTO.getParentFeedback() != null)
				{
					sessionCompleted = SessionTypeEnum.SUMMARY.getSessionName();
				}
			}
			else if (scheduleDetailsDTO.isReportGenerated())
			{
				sessionCompleted = SessionTypeEnum.SUMMARY.getSessionName();
			}
			if (!sessionCompleted.isEmpty() && !sessionCompleted.equals(""))
			{
				for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> appMenu : headerMenuMap.entrySet())
				{
					// Presession always disable clickable
					if (appMenu.getKey().getRefName().equalsIgnoreCase(SessionTypeEnum.PRESESSION.getSessionName()))
					{
						appMenu.getKey().setEnableClick(false);
					}
					if (appMenu.getKey().getRefName().equalsIgnoreCase(sessionCompleted))
					{
						break;
					}
					// enable only for user role and
					if (sessionCompleted.equalsIgnoreCase(SessionTypeEnum.SESSION3.getSessionName()))
					{
						appMenu.getKey().setEnableClick(false);
						isCanChangeCart = false;
					}
				}
			}
		}
		return isCanChangeCart;
	}

	public Map<String, String> getHeaderSubTitle(boolean isProgramTestStudent)
	{
		Map<String, String> headerSubTitle = new HashMap<String, String>();
		try
		{
			if(!isProgramTestStudent)
			{
				ApplicationProperties properties = ApplicationProperties.getInstance();
				headerSubTitle.put(properties.getProperty("com.lodestar.edupath.navigation.tab.Presession").trim(),
						properties.getProperty("com.lodestar.edupath.navigation.tab.presession.subtitle"));
				headerSubTitle.put(properties.getProperty("com.lodestar.edupath.navigation.tab.session1").trim(),
						properties.getProperty("com.lodestar.edupath.navigation.tab.session1.subtitle"));
				headerSubTitle.put(properties.getProperty("com.lodestar.edupath.navigation.tab.session2").trim(),
						properties.getProperty("com.lodestar.edupath.navigation.tab.session2.subtitle"));
				headerSubTitle.put(properties.getProperty("com.lodestar.edupath.navigation.tab.session3").trim(),
						properties.getProperty("com.lodestar.edupath.navigation.tab.session3.subtitle"));
				headerSubTitle.put(properties.getProperty("com.lodestar.edupath.navigation.tab.Summary").trim(),
						properties.getProperty("com.lodestar.edupath.navigation.tab.summary.subtitle"));
			
			}
		}
		
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return headerSubTitle;
	}

	public boolean getFacilitatorIsReviewerByUserId(UserDetailDTO userDetailDTO)
	{
		boolean isReviewer = false;
		try
		{
			FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsByUserId(userDetailDTO);
			if (null != facilitatorDetailsDTO)
			{
				isReviewer = facilitatorDetailsDTO.getIsReviewer();
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return isReviewer;
	}

	public String getTrialStudentType(UserDetailDTO userDetailDTO) throws Exception
	{
		OUT.info("Find the user name with user id {}", userDetailDTO.getId());
		if (userDetailDTO.getRoleWeight() == RoleTypeEnum.USER.getWeight())
		{
			StudentDetailsDTO studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userDetailDTO);
			OUT.debug("Funded Student Type : {}", studentDTO.getStudentType());
			emailId = studentDTO.getFatheremailId();
			if (null == studentDTO.getStudentType())
			{
				return StudentTypeEnum.TRIAL.name();
			}
			else if (StudentTypeEnum.TRIAL == studentDTO.getStudentType())
			{
				return studentDTO.getStudentType().name();
			}
		}
		return null;
	}

	
	public String getStudentORType(UserDetailDTO userDetailDTO) throws Exception
	{
	
		if (userDetailDTO.getRoleWeight() == RoleTypeEnum.STUDENTOR.getWeight())
		{
			StudentDetailsDTO studentDTO = new StudentDetailsDAO().getStudentDetailsByUserId(userDetailDTO);
			OUT.debug("Funded Student Type : {}", studentDTO.getStudentType());
			emailId = studentDTO.getFatheremailId();
//			if (StudentTypeEnum.ORGROUP == studentDTO.getStudentType())
//			{
//				return studentDTO.getStudentType().name();
//			}else if (StudentTypeEnum.ORGROUP == studentDTO.getStudentType())
//			{
//				return studentDTO.getStudentType().name();
//			}
		}
		
		
		return null;
		
	}
	
	
	
	public String getFatherEmailId()
	{
		return emailId;
	}
}
