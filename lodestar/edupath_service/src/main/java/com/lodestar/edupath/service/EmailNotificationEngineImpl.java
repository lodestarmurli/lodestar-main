package com.lodestar.edupath.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.email.EmailGenerator;
import com.lodestar.edupath.email.EmailPacket;
import com.lodestar.edupath.email.notifications.EmailNotificationEntity;
import com.lodestar.edupath.notification.NotificationEngine;

public class EmailNotificationEngineImpl implements NotificationEngine
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EmailNotificationEngineImpl.class);	//$NON-NLS-1$

	private void sendNUpdateStatus(EmailNotificationEntity emailNotificationEntity)
	{
		try
		{
			boolean isSentSuccessfully = false;
			try
			{
				isSentSuccessfully = EmailGenerator.send(emailNotificationEntity.getEmailPacket());
			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
			}
			if (isSentSuccessfully)
			{
				emailNotificationEntity.msgQueue.setNotifiedTime(new Timestamp(System.currentTimeMillis()));
				emailNotificationEntity.msgQueue.setStatus(NotificationConstant.MessageNotificationStatus.SUCCESS.name());
				MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_MESSAGE_QUEUE, emailNotificationEntity.msgQueue);

			}
			else if ((int) emailNotificationEntity.msgQueue.getRetryCount() < NotificationConstant.MAX_RETRAY_COUNT_INTERVAL)
			{
				emailNotificationEntity.msgQueue.setStatus(NotificationConstant.MessageNotificationStatus.INPROGRESS.name());
				emailNotificationEntity.msgQueue.setRetryCount(emailNotificationEntity.msgQueue.getRetryCount() + 1);
				MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_MESSAGE_QUEUE, emailNotificationEntity.msgQueue);
				OUT.warn("Unable to send the email notification will try again after some time for messageQueueId:{} ", emailNotificationEntity.msgQueue.getId());
			}
			else
			{
				emailNotificationEntity.msgQueue.setStatus(NotificationConstant.MessageNotificationStatus.FAILED.name());
				emailNotificationEntity.msgQueue.setRetryCount(emailNotificationEntity.msgQueue.getRetryCount());
				MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_MESSAGE_QUEUE, emailNotificationEntity.msgQueue);
			}
			if (isSentSuccessfully)
			{
				EmailPacket packet = emailNotificationEntity.getEmailPacket();
				if (null != packet.getFilePath() && !packet.getFilePath().isEmpty())

				{
					String folderPath = getEmailFolderPath();
					File file = new File(folderPath + File.separator + packet.getFilePath().trim());
					if (null != file)
					{
						CommonUtil.deleteDir(file);
					}
				}
			}
		}
		catch (InterruptedException ie)
		{
			// ignore
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		catch (Throwable e)
		{
			OUT.error("Throwable", e);
		}
	}

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

		try
		{
			if (OUT.isInfoEnabled())
			{
				OUT.info("Message Queue job started");
			}
			List<MessageQueueDTO> messageList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_INPROGRESS_MESSAGE,
					NotificationConstant.MessageNotificationStatus.INPROGRESS.name());
			if (OUT.isInfoEnabled())
			{
				OUT.info("Total New/INPROGRESS message queue list in DB: {}", messageList.size());
			}
			if (messageList.size() > 0)
			{
				GlobalSttingDAO globalDAO = new GlobalSttingDAO();
				GlobalSettingDTO globalDTO = new GlobalSettingDTO();
				globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.ADMIN_EMAIL.getProperty());
				globalDTO = globalDAO.getPropertesValueByName(globalDTO);

				String[] ccList = null; 
				if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
				{
					ccList = globalDTO.getPropertyValue().split(",");
				}
				File[] attachments = null;
				String folderPath = getEmailFolderPath();
				for (MessageQueueDTO messageQueueDTO : messageList)
				{
					// setNotificationVOFromJSONData(notificationDTO, messageQueueDTO.getMessageData());
					EmailNotificationEntity entity = null;
					if (NotificationConstant.MessageNotificationType.EMAIL.name().equalsIgnoreCase(messageQueueDTO.getNotifierType()))
					{
						// if the messageQueueDTO has attachment folder
						// get the global attachment folder
						// global attachment Folder + messageQueueDTO attachment folder
						// iterate the folder or list files and pass
						if (null != messageQueueDTO.getFilePath() && !messageQueueDTO.getFilePath().isEmpty())
						{
							File file = new File(folderPath + File.separator + messageQueueDTO.getFilePath().trim());
							if (null != file)
							{
								attachments = file.listFiles();
							}
							if (attachments == null)
							{
								if (OUT.isInfoEnabled())
								{
									OUT.info("No Email attachments Founded for : {}", messageQueueDTO.getFilePath());
								}
								continue;
							}
						}
						EmailPacket emailPacket = new EmailPacket(messageQueueDTO.getToAddress().split(","), ccList, messageQueueDTO.getMessageSubject(),
								messageQueueDTO.getMessageData(), "HTML", attachments);
						emailPacket.setFilePath(messageQueueDTO.getFilePath());
						entity = new EmailNotificationEntity(emailPacket, null, messageQueueDTO);
						attachments = null;
					}
					else
					{
						// TODO FOR OTHERS
					}

					if (entity != null)
					{
						sendNUpdateStatus(entity);
						OUT.info("Notification Type " + messageQueueDTO.getNotifierType() + ", added to queue");
					}
				}
			}
			if (OUT.isInfoEnabled())
			{
				OUT.info("Message Queue job completed");
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

	}

	private String getEmailFolderPath()
	{
		try
		{
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.EMAIL_FOLDER_PATH.getProperty());
			globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
			return globalDTO.getPropertyValue();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}
}
