package com.lodestar.edupath.datatransferobject.dto;

import java.util.Date;

public class ForgotPasswordRequestDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					userId;
	private String				code;
	private Date				startTime;
	private Date				endTime;
	private boolean				used;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public boolean isUsed()
	{
		return used;
	}

	public void setUsed(boolean used)
	{
		this.used = used;
	}

	@Override
	public String toString()
	{
		return "ForgotPasswordRequestDTO [id=" + id + ", userId=" + userId + ", code=" + code + ", startTime=" + startTime + ", endTime=" + endTime + ", used="
				+ used + "]";
	}

}
