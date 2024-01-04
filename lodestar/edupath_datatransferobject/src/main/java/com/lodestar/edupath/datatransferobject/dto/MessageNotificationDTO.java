/*
 * @(#) MessageNotificationDTO.java  
 * 
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0 
 * @Date Mar 12, 2013
 * @Author Smrutiranjan Behera
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.Arrays;

public class MessageNotificationDTO implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	// Used for new user registration and forgot password.
	private String				userName;
	private String				userId;
	private String				userPassword;

	private String				notificationType;
	private String				messageType;
	private String				webURL;

	// One or more recipient emailIds and mobile Numbers.
	private String[]			recipientMailIds;
	private String[]			mobileNumbers;

	// Generated Template(Body) and Subject string.
	private String				notificationBody;
	private String				notificationSubject;

	private boolean				isNew				= true;
	private Integer				messageQueueId;

	private Object				bodyData;

	private int					roleTypeId;

	private String				filePath;

	private String				parentName;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String[] getRecipientMailIds()
	{
		return recipientMailIds;
	}

	public void setRecipientMailIds(String[] recipientMailIds)
	{
		this.recipientMailIds = recipientMailIds;
	}

	public String getNotificationType()
	{
		return notificationType;
	}

	public void setNotificationType(String notificationType)
	{
		this.notificationType = notificationType;
	}

	public String getMessageType()
	{
		return messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public String getWebURL()
	{
		return webURL;
	}

	public void setWebURL(String webURL)
	{
		this.webURL = webURL;
	}

	public String[] getMobileNumbers()
	{
		return mobileNumbers;
	}

	public void setMobileNumbers(String[] mobileNumbers)
	{
		this.mobileNumbers = mobileNumbers;
	}

	public boolean isNew()
	{
		return isNew;
	}

	public void setNew(boolean isNew)
	{
		this.isNew = isNew;
	}

	public Integer getMessageQueueId()
	{
		return messageQueueId;
	}

	public void setMessageQueueId(Integer messageQueueId)
	{
		this.messageQueueId = messageQueueId;
	}

	public String getNotificationBody()
	{
		return notificationBody;
	}

	public void setNotificationBody(String notificationBody)
	{
		this.notificationBody = notificationBody;
	}

	public String getNotificationSubject()
	{
		return notificationSubject;
	}

	public void setNotificationSubject(String notificationSubject)
	{
		this.notificationSubject = notificationSubject;
	}

	@Override
	public String toString()
	{
		return "MessageNotificationDTO [userName=" + userName + ", parentName =" + parentName +"userId=" + userId + ", userPassword=" + userPassword + ", notificationType=" + notificationType
				+ ", messageType=" + messageType + ", webURL=" + webURL + ", recipientMailIds=" + Arrays.toString(recipientMailIds) + ", mobileNumbers="
				+ Arrays.toString(mobileNumbers) + ", notificationBody=" + notificationBody + ", notificationSubject=" + notificationSubject + ", isNew=" + isNew
				+ ", messageQueueId=" + messageQueueId + "]";
	}

	public Object getBodyData()
	{
		return bodyData;
	}

	public void setBodyData(Object bodyData)
	{
		this.bodyData = bodyData;
	}

	public int getRoleTypeId()
	{
		return roleTypeId;
	}

	public void setRoleTypeId(int roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getParentName()
	{
		return parentName;
	}

	public void setParentName(String parentName)
	{
		this.parentName = parentName;
	}
}
