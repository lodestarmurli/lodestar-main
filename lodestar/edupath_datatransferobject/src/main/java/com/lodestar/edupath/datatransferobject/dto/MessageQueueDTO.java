package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.sql.Timestamp;

//@PartitionModule(tableName = PartitionTableEnum.MSQ, period = PartitionPeriodEnum.MONTHLY)
public class MessageQueueDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private Timestamp			queuedTime;
	private String				messageSubject;
	private String				messageData;
	private String				toAddress;
	private String				status;
	private Integer				retryCount;
	private String				notifierType;
	private Timestamp			notifiedTime;
	private String				filePath;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Timestamp getQueuedTime()
	{
		return queuedTime;
	}

	public void setQueuedTime(Timestamp queuedTime)
	{
		this.queuedTime = queuedTime;
	}

	public String getMessageSubject()
	{
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject)
	{
		this.messageSubject = messageSubject;
	}

	public String getMessageData()
	{
		return messageData;
	}

	public void setMessageData(String messageData)
	{
		this.messageData = messageData;
	}

	public String getToAddress()
	{
		return toAddress;
	}

	public void setToAddress(String toAddress)
	{
		this.toAddress = toAddress;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Integer getRetryCount()
	{
		return retryCount;
	}

	public void setRetryCount(Integer retryCount)
	{
		this.retryCount = retryCount;
	}

	public String getNotifierType()
	{
		return notifierType;
	}

	public void setNotifierType(String notifierType)
	{
		this.notifierType = notifierType;
	}

	public Timestamp getNotifiedTime()
	{
		return notifiedTime;
	}

	public void setNotifiedTime(Timestamp notifiedTime)
	{
		this.notifiedTime = notifiedTime;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
}
