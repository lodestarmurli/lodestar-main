package com.lodestar.edupath.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant.MessageNotificationType;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.notification.NotificationTemplateGenerator;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.LeadParentDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageNotificationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class PasswordGeneratorService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(PasswordGeneratorService.class);

	public static String getRandomAlphanumeric()
	{
		return getRandomAlphanumeric(ApplicationConstants.PASSWORD_LENGTH);
	}

	public static String getRandomAlphanumeric(int count)
	{
		return RandomStringUtils.randomAlphanumeric(count);
	}

	public static String getRandomNumeric()
	{
		return getRandomNumeric(ApplicationConstants.NUMERIC_LENGTH);
	}

	public static String getRandomNumeric(int count)
	{
		return RandomStringUtils.randomNumeric(count);
	}

	public static void sendNewNotification(int userId, String userName, String userEmailId, int userRoleId, String password) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.NEW_USER.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			userEmailId
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("UserName", userName);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		parameterMap.put("userPassword", password);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("send mail");
	}

	public static void sendNewNotification(Map<String, Object> parameterMap, String userName, String userEmailId, int roleTypeId, String templateName, String code)
			throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(userEmailId.split(","));

		if (null == parameterMap)
		{
			parameterMap = new HashMap<String, Object>();
		}
		String webURL = globalDTO.getPropertyValue();
		if (templateName.equals(NotificationConstant.MessageTemplateNameAndType.FORGOT_PASSWORD.name()))
		{
			webURL = webURL + "/resetForgotPassword?code=" + code;
		}
		parameterMap.put("webURL", webURL);
		parameterMap.put("UserName", userName);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("send mail");
	}
	
	
	public static void sendNewNotificationLST(Map<String, Object> parameterMap, String userName, String userEmailId, int roleTypeId, String templateName, String code,String token)
			throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(userEmailId.split(","));

		if (null == parameterMap)
		{
			parameterMap = new HashMap<String, Object>();
		}
		String webURL = globalDTO.getPropertyValue()+"/vark/VarkStartTest?token="+token;
		if (templateName.equals(NotificationConstant.MessageTemplateNameAndType.FORGOT_PASSWORD.name()))
		{
			webURL = webURL + "/resetForgotPassword?code=" + code;
		}
		parameterMap.put("webURL", webURL);
		parameterMap.put("UserName", userName);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("send mail");
	}

	public static void sendNewNotificationForDHStudent(Map<String, Object> parameterMap, String userName, String userEmailId, int roleTypeId, String templateName, String code)
			throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);
		
		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(userEmailId.split(","));

		if (null == parameterMap)
		{
			parameterMap = new HashMap<String, Object>();
		}
		String webURL = globalDTO.getPropertyValue()+"/DH-Lodestar/login";
		if (templateName.equals(NotificationConstant.MessageTemplateNameAndType.FORGOT_PASSWORD.name()))
		{
			webURL = webURL + "/resetForgotPassword?code=" + code;
		}
		parameterMap.put("webURL", webURL);
		parameterMap.put("UserName", userName);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("send mail");
	}
	public static void sendNewNotificationForTrialReport(Map<String, Object> parameterMap, String userName, String userEmailId, int roleTypeId, String templateName,
			String folderNFilePath, String parentName) throws Exception
	{
		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(userEmailId.split(","));
		notificationDTO.setFilePath(folderNFilePath);
		if (null == parameterMap)
		{
			parameterMap = new HashMap<String, Object>();
		}
		parameterMap.put("UserName", userName);
		parameterMap.put("parentName", parentName);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Trial Student send mail");
	}
	
	public static void sendNewNotificationToAdmin(Map<String, Object> parameterMap, String userName, String userEmailId, int roleTypeId, String templateName,
			String folderNFilePath, String parentName) throws Exception
	{
		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(userEmailId.split(","));
		notificationDTO.setFilePath(folderNFilePath);
		if (null == parameterMap)
		{
			parameterMap = new HashMap<String, Object>();
		}
		parameterMap.put("UserName", userName);
		parameterMap.put("parentName", parentName);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Trial Student send mail");
	}
	
	//Start Sasedeve Edited by Mrutyunjaya on Date 13-10-2017
	
	public static void sendleadparentmessage(String userName, String userEmailId, int userRoleId,String link) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Lead_Parent_Message.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			userEmailId
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("link", link);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	
	public static void sendleadparentmessageHDFC(String userName, String userEmailId, int userRoleId,String link) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Lead_Parent_Message_HDFC.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			userEmailId
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("link", link);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	
	public static void sendleadparentmessageToLodestar(String userName, String sphno,String semail,String pemail,String pname,String pcontactno, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadParent.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			"archisman@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("semail", semail);
		parameterMap.put("sphno", sphno);
		parameterMap.put("pemail", pemail);
		parameterMap.put("pname", pname);
		parameterMap.put("pcontactno", pcontactno);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	//start Added by bharath on 06/08/2020
	public static void sendOccNameMessageToLodestar(String userName,String sname, String semail,String pemail,String pcontactno, int userRoleId, String searchval) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.AdminCareerFitment.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			"contact@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("sname", sname);
		parameterMap.put("semail", semail);
		parameterMap.put("pemail", pemail);
		parameterMap.put("searchval", searchval);
		parameterMap.put("pcontactno", pcontactno);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		OUT.debug("Career Fitment occupation missing send mail parameterMap:{}",parameterMap);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Career Fitment occupation missing send mail");
	}
	//end Added by bharath on 06/08/2020
	
	
	
	public static void sendleadStudentmessageToLodestar(String userName,String pemail,String pno,String sname,String semail,String sno,String sldid, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadStudent.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			"archisman@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("semail", semail);
		parameterMap.put("sno", sno);
		parameterMap.put("pemail", pemail);
		parameterMap.put("pno", pno);
		parameterMap.put("sname", sname);
		parameterMap.put("sldid", sldid);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	
	public static void sendleadChildmessageToLodestar(String userName,String pemail,String pno,String sname,String semail,String sno,String sldid, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadChild.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			"prudhvi@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("semail", semail);
		parameterMap.put("sno", sno);
		parameterMap.put("pemail", pemail);
		parameterMap.put("pno", pno);
		parameterMap.put("sname", sname);
		parameterMap.put("sldid", sldid);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	public static void sendTYEProgTestmessageToLodestar(String userName,String pemail,String pno,String sname,String semail,String sno,String sldid, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_TYEProgTest.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			"archisman@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("semail", semail);
		parameterMap.put("sno", sno);
		parameterMap.put("pemail", pemail);
		parameterMap.put("pno", pno);
		parameterMap.put("sname", sname);
		parameterMap.put("sldid", sldid);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("NewTYEProgTestRegistration send mail");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void sendleadparentmessageToLodestarHDFC(String userName, String sphno,String semail,String pemail,String pname,String pcontactno, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadParent_HDFC.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(new String[]
		{
			"archisman@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("semail", semail);
		parameterMap.put("sphno", sphno);
		parameterMap.put("pemail", pemail);
		parameterMap.put("pname", pname);
		parameterMap.put("pcontactno", pcontactno);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	
	
	
	public static void AppointmentBookToLodestar(String datetime, String pemail,String LDID, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadParent_AppointmentBook.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName("Admin");
		notificationDTO.setRecipientMailIds(new String[]
		{
			"santhoshgm@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("dattime", datetime);
		parameterMap.put("pemail", pemail);
		parameterMap.put("LDID", LDID);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	
	
	public static void AppointmentBookToLodestarHDFC(String datetime, String pemail,String LDID, String StudentName,String StudentEmailId, String StudentPhone,String ParentPhone, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadParent_AppointmentBook_HDFC.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName("Admin");
		notificationDTO.setRecipientMailIds(new String[]
		{
			"archisman@lodestar.guru"
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("dattime", datetime);
		parameterMap.put("pemail", pemail);
		parameterMap.put("LDID", LDID);
		parameterMap.put("StudentName", StudentName);
		parameterMap.put("StudentEmailId", StudentEmailId);
		parameterMap.put("StudentPhone", StudentPhone);
		parameterMap.put("ParentPhone", ParentPhone);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	
	public static void sendNewNotificationForLeadParentTrialReport(Map<String, Object> parameterMap, String userName, String userEmailId, int roleTypeId, String templateName,
			String folderNFilePath, String parentName,String token) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);
		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName(userName);
		notificationDTO.setRecipientMailIds(userEmailId.split(","));
//		notificationDTO.setRecipientMailIds(new String[]
//				{
//					"prudhvi@lodestar.guru"
//				});
		notificationDTO.setFilePath(folderNFilePath);
		if (null == parameterMap)
		{ 
			parameterMap = new HashMap<String, Object>();
		}
		parameterMap.put("StudentName", userName);
		parameterMap.put("webURL",globalDTO.getPropertyValue());
		parameterMap.put("token",token);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Trial Student send mail");
	}
	
	public static void sendNewNotificationForTYEProgTest(int roleTypeId, String templateName,StudentDetailsDTO studentDetailsDTO,String folderNFilePath,String token,LeadParentDTO leadparent) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);
		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(templateName);
		notificationDTO.setRoleTypeId(roleTypeId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName("Admin");
		notificationDTO.setRecipientMailIds(new String[]
				{
						"archisman@lodestar.guru"
					});
		notificationDTO.setFilePath(folderNFilePath);
		
		Map<String, Object>	parameterMap = new HashMap<String, Object>();
		OUT.info("LD ID: "+studentDetailsDTO.getUserId());
		OUT.info("Student No: "+studentDetailsDTO.getStudentcontactNumber());
		if(leadparent.getLDID()!=null && !leadparent.getLDID().trim().equals(""))
		{
			parameterMap.put("ldid", leadparent.getLDID());
		}
		else
		{
			parameterMap.put("ldid", "");
		}
		
		if(studentDetailsDTO.getName()!=null && !studentDetailsDTO.getName().trim().isEmpty())
		{
			parameterMap.put("sname", studentDetailsDTO.getName());
		}
		else
		{
			parameterMap.put("sname", "");
		}
		
		if(leadparent.getStudentEmailID()!=null && !leadparent.getStudentEmailID().trim().isEmpty())
		{
			parameterMap.put("semail", leadparent.getStudentEmailID());
		}
		else
		{
			parameterMap.put("semail", "");
		}
		
		
		if(leadparent.getStudentContactNo()!=null && !leadparent.getStudentContactNo().trim().equals(""))
		{
			parameterMap.put("sphno", leadparent.getStudentContactNo());
		}
		else
		{
			parameterMap.put("sphno", "");
		}
		
		if(leadparent.getParentEmailID()!=null && !leadparent.getParentEmailID().trim().equals(""))
		{
			parameterMap.put("pemail", leadparent.getParentEmailID());
		}
		else
		{
			parameterMap.put("pemail", "");
		}
		if(leadparent.getParentName()!=null && !leadparent.getParentName().trim().equals(""))
		{
			parameterMap.put("pname", leadparent.getParentName());
		}
		else
		{
			parameterMap.put("pname", "");
		}
		if(leadparent.getParentContactNo()!=null && !leadparent.getParentContactNo().trim().equals(""))
		{
			parameterMap.put("pcontactno", leadparent.getParentContactNo());
		}
		else
		{
			parameterMap.put("pcontactno", "");
		}
		
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		parameterMap.put("token",token);
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Trial Student TYEProgTest send mail");
	}
	
	
	
	
	public static void SendMailTOParentBookingSlots(String datestr, String timestr,String parentemail, String StudentName,String Gender, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Parent_Booking_Slots.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName("Parent");
		notificationDTO.setRecipientMailIds(new String[]
		{
				parentemail.trim()
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datestr", datestr);
		parameterMap.put("timestr", timestr);
		parameterMap.put("StudentName", StudentName);
		parameterMap.put("Gender", Gender);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}
	
	public static void SendMailTOStudentBookingSlots(String datestr, String timestr,String Studentemail, String StudentName, int userRoleId) throws Exception
	{
		GlobalSttingDAO globalDAO = new GlobalSttingDAO();
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		globalDTO = globalDAO.getPropertesValueByName(globalDTO);

		MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
		notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
		notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Student_Booking_Slots.name());
		notificationDTO.setRoleTypeId(userRoleId);
		notificationDTO.setNew(true);
		notificationDTO.setUserName("Parent");
		notificationDTO.setRecipientMailIds(new String[]
		{
			Studentemail.trim()
		});
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datestr", datestr);
		parameterMap.put("timestr", timestr);
		parameterMap.put("StudentName", StudentName);
		parameterMap.put("webURL", globalDTO.getPropertyValue());
		
		NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
		OUT.debug("Lead Parent send mail");
	}

	

	//END Sasedeve Edited by Mrutyunjaya on Date 13-10-2017
	
	
	//Start Sasedeve edited by Mrutyunjaya on date 10-01-2018
	
	
		public static void sendleadparentmessageToLodestarCampaign(String userName, String sphno,String semail,String pemail,String pname,String pcontactno, int userRoleId,String Subject,String adminemails) throws Exception
		{
			GlobalSttingDAO globalDAO = new GlobalSttingDAO();
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);

			MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
			notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
			notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadParent_Campaign.name());
			notificationDTO.setRoleTypeId(userRoleId);
			notificationDTO.setNew(true);
			notificationDTO.setUserName(userName);
			notificationDTO.setRecipientMailIds(new String[]
			{
					adminemails
			});
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("semail", semail);
			parameterMap.put("sphno", sphno);
			parameterMap.put("pemail", pemail);
			parameterMap.put("pname", pname);
			parameterMap.put("pcontactno", pcontactno);
			parameterMap.put("webURL", globalDTO.getPropertyValue());
			parameterMap.put("subject", Subject);
			
			NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
			OUT.debug("Lead Parent send mail");
		}
		
		
		public static void sendleadparentmessageToLodestarCampaign1(String userName, String sphno,String semail,String pemail,String pname,String pcontactno, int userRoleId,String Subject,String adminemails) throws Exception
		{
			GlobalSttingDAO globalDAO = new GlobalSttingDAO();
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);

			MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
			notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
			notificationDTO.setMessageType(NotificationConstant.MessageTemplateNameAndType.Admin_LeadParent_Campaign1.name());
			notificationDTO.setRoleTypeId(userRoleId);
			notificationDTO.setNew(true);
			notificationDTO.setUserName(userName);
			notificationDTO.setRecipientMailIds(new String[]
			{
					adminemails
			});
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("semail", semail);
			parameterMap.put("sphno", sphno);
			parameterMap.put("pemail", pemail);
			parameterMap.put("pname", pname);
			parameterMap.put("pcontactno", pcontactno);
			parameterMap.put("webURL", globalDTO.getPropertyValue());
			parameterMap.put("subject", Subject);
			
			NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);
			OUT.debug("Lead Parent send mail");
		}
		
		
		
		
		//End Sasedeve edited by Mrutyunjaya on date 10-01-2018
	
	
	
}
