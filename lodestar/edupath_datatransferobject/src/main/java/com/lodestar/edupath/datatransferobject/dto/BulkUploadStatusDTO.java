package com.lodestar.edupath.datatransferobject.dto;

import java.sql.Timestamp;

public class BulkUploadStatusDTO implements IModel
{

	private static final long	serialVersionUID	= 1L;
	private Integer				id;
	private String				module;
	private String				fileName;
	private Timestamp			uploadedAt;
	private String				uploadedBy;
	private Timestamp			completedAt;
	private String				status;
	private String				message;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getModule()
	{
		return module;
	}

	public void setModule(String module)
	{
		this.module = module;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public Timestamp getUploadedAt()
	{
		return uploadedAt;
	}

	public void setUploadedAt(Timestamp uploadedAt)
	{
		this.uploadedAt = uploadedAt;
	}

	public String getUploadedBy()
	{
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy)
	{
		this.uploadedBy = uploadedBy;
	}

	public Timestamp getCompletedAt()
	{
		return completedAt;
	}

	public void setCompletedAt(Timestamp completedAt)
	{
		this.completedAt = completedAt;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
