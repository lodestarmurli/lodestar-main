package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class MessageTemplateDTO implements Serializable, IModel
{
	private static final long	serialVersionUID							= 377087828023523140L;

	private int					id;
	private String				name;
	private int					roleTypeId;
	private String				notificationType;
	private String				messageType;
	private String				messageSubject;
	private String				templateString;
	private String				templateParams;
	private boolean				isActive;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public String getMessageSubject()
	{
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject)
	{
		this.messageSubject = messageSubject;
	}

	public String getTemplateString()
	{
		return templateString;
	}

	public void setTemplateString(String templateString)
	{
		this.templateString = templateString;
	}

	public String getTemplateParams()
	{
		return templateParams;
	}

	public void setTemplateParams(String templateParams)
	{
		this.templateParams = templateParams;
	}

	public boolean getIsActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public int getRoleTypeId()
	{
		return roleTypeId;
	}

	public void setRoleTypeId(int roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}
}
