package com.lodestar.edupath.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant.MessageNotificationType;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.notification.NotificationTemplateGenerator;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageNotificationDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.scheduler.SchedulerTask;

public class SessionReminderService implements SchedulerTask
{
	private static final Logger	OUT			= LoggerFactory.getLogger(SessionReminderService.class);

	private static final String	DATE_FORMAT	= "dd-MMM-yyyy";
	private static final String	TIME_FORMAT	= "hh:mm a";

	@Override
	public void setId(Integer id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String taskName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setGroupName(String taskGroupName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setParams(String taskParams)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getId()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGroupName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParams()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute()
	{

		OUT.info("Started Session Remainder Service");
		try
		{
			GlobalSttingDAO globalDAO = new GlobalSttingDAO();
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();

			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.SESSION1_REMAINDER_IN_DAYS.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);

			String beforeDays = "1";
			if (globalDTO != null && null != globalDTO.getPropertyValue())
			{
				beforeDays = globalDTO.getPropertyValue();
			}

			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("beforeDays", beforeDays);
			List<SessionScheduleDetailsDTO> resultAsList1 = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SESSION_REMAINDER1, parameterMap);
			OUT.info("Number of session reminder mails for session one to be sent {}", resultAsList1.size());

			globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);
			String webURL = globalDTO.getPropertyValue();

			for (SessionScheduleDetailsDTO sessionScheduleDetailsDTO : resultAsList1)
			{
				sendNotificationNUpdate(sessionScheduleDetailsDTO, 1, webURL);
			}

			globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.SESSION2_REMAINDER_IN_DAYS.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);

			beforeDays = "3";
			if (globalDTO != null && null != globalDTO.getPropertyValue())
			{
				beforeDays = globalDTO.getPropertyValue();
			}
			parameterMap = new HashMap<>();
			parameterMap.put("beforeDays", beforeDays);
			resultAsList1 = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SESSION_REMAINDER2, parameterMap);
			OUT.info("Number of session reminder mails for session two to be sent {}", resultAsList1.size());
			for (SessionScheduleDetailsDTO sessionScheduleDetailsDTO : resultAsList1)
			{
				sendNotificationNUpdate(sessionScheduleDetailsDTO, 2, webURL);
			}

			globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.SESSION3_REMAINDER_IN_DAYS.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);

			beforeDays = "3";
			if (globalDTO != null && null != globalDTO.getPropertyValue())
			{
				beforeDays = globalDTO.getPropertyValue();
			}
			parameterMap = new HashMap<>();
			parameterMap.put("beforeDays", beforeDays);
			resultAsList1 = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SESSION_REMAINDER3, parameterMap);
			OUT.info("Number of session reminder mails for session three to be sent {}", resultAsList1.size());
			for (SessionScheduleDetailsDTO sessionScheduleDetailsDTO : resultAsList1)
			{
				sendNotificationNUpdate(sessionScheduleDetailsDTO, 3, webURL);
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		OUT.info("Completed Session Remainder Service");
	}

	private void sendNotificationNUpdate(SessionScheduleDetailsDTO sessionScheduleDetailsDTO, int sessionNumber, String webURL)
	{
		try
		{
			String sessionDate = null;
			String sessionTime = null;
			StudentDetailsDTO dto = (StudentDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_DETAIL_BY_STUDENTID,
					sessionScheduleDetailsDTO.getStudentId());
			String messageType = null;
			switch (sessionNumber)
			{
				case 1:
					sessionDate = TimeUtil.convertTimeAsString(sessionScheduleDetailsDTO.getSession1Date().getTime(), DATE_FORMAT);
					sessionTime = TimeUtil.convertTimeAsString(sessionScheduleDetailsDTO.getSession1Date().getTime(), TIME_FORMAT);
					messageType = NotificationConstant.MessageTemplateNameAndType.SESSION1_REMINDER.name();
					break;
				case 2:
					sessionDate = TimeUtil.convertTimeAsString(sessionScheduleDetailsDTO.getSession2Date().getTime(), DATE_FORMAT);
					sessionTime = TimeUtil.convertTimeAsString(sessionScheduleDetailsDTO.getSession2Date().getTime(), TIME_FORMAT);
					messageType = NotificationConstant.MessageTemplateNameAndType.SESSION2_REMINDER.name();
					break;
				case 3:
					if(sessionScheduleDetailsDTO.getSession3Date() != null) {
						sessionDate = TimeUtil.convertTimeAsString(sessionScheduleDetailsDTO.getSession3Date().getTime(), DATE_FORMAT);
						sessionTime = TimeUtil.convertTimeAsString(sessionScheduleDetailsDTO.getSession3Date().getTime(), TIME_FORMAT);
						messageType = NotificationConstant.MessageTemplateNameAndType.SESSION3_REMINDER.name();
						break;
					}
			}

			MessageNotificationDTO notificationDTO = new MessageNotificationDTO();
			notificationDTO.setNotificationType(MessageNotificationType.EMAIL.name());
			notificationDTO.setMessageType(messageType);
			notificationDTO.setNew(true);

			notificationDTO.setRoleTypeId(dto.getRoleTypeId());

			notificationDTO.setUserName(dto.getName());
			notificationDTO.setRecipientMailIds(new String[]
			{
				dto.getFatheremailId()
			});
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("UserName", dto.getName());
			parameterMap.put("sessionDate", sessionDate);
			parameterMap.put("sessionTime", sessionTime);
			parameterMap.put("webURL", webURL);
			boolean addNotification = NotificationTemplateGenerator.addNotification(notificationDTO, parameterMap);

			if (addNotification)
			{
				String query = null;

				switch (sessionNumber)
				{
					case 1:
						query = MyBatisMappingConstants.UPDATE_SESSION_REMAINDER1;
						break;
					case 2:
						query = MyBatisMappingConstants.UPDATE_SESSION_REMAINDER2;
						break;
					case 3:
						query = MyBatisMappingConstants.UPDATE_SESSION_REMAINDER3;
						break;
				}

				MyBatisManager.getInstance().update(query, sessionScheduleDetailsDTO.getId());

				OUT.debug("Remainder Mail sent to {}, studenId: {}, sessionDate:{}", new Object[]
				{
						dto.getFatheremailId(),
						dto.getId(),
						sessionDate
				});
			}
			else
			{
				OUT.warn("Unable to add notification to {}, studenId: {}, sessionDate:{} ", new Object[]
				{
						dto.getFatheremailId(),
						dto.getId(),
						sessionDate
				});

			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}
}
