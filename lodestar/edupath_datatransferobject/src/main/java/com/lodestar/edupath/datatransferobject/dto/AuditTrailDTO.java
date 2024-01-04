package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.Date;

public class AuditTrailDTO implements Serializable, IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				module;
	private String				action;
	private String				performedBy;
	private String				message;
	private Date				actionTime;


	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public String getPerformedBy()
	{
		return performedBy;
	}

	public void setPerformedBy(String performedBy)
	{
		this.performedBy = performedBy;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Date getActionTime()
	{
		return actionTime;
	}

	public void setActionTime(Date actionTime)
	{
		this.actionTime = actionTime;
	}

	@Override
	public String toString()
	{
		return "AuditTrailDTO [id=" + id + ", module=" + module + ", action=" + action + ", performedBy=" + performedBy + ", message=" + message + ", actionTime="
				+ actionTime + "]";
	}

}
