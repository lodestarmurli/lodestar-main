package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class SubAdminDTO implements Serializable, IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private String				emailId;
	private int				    userId;
	private String              contactNumber;

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

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public String getContactNumber()
	{
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString()
	{
		return "SubAdminDTO [id=" + id + ", name=" + name + ", emailId=" + emailId + ", userId=" + userId + ", contactNumber=" + contactNumber + "]";
	}

}
