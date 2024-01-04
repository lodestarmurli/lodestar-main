package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.Arrays;

public class GlobalSettingDTO implements Serializable, IModel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Integer				id;

	private String				propertyName;

	private String				propertyValue;

	private String				isProtected;

	private byte[]				protectedValue;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	public String getPropertyValue()
	{
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue)
	{
		this.propertyValue = propertyValue;
	}

	public String getIsProtected()
	{
		return isProtected;
	}

	public void setIsProtected(String isProtected)
	{
		this.isProtected = isProtected;
	}

	public byte[] getProtectedValue()
	{
		return protectedValue;
	}

	public void setProtectedValue(byte[] protectedValue)
	{
		this.protectedValue = protectedValue;
	}

	@Override
	public String toString()
	{
		return "GlobalSettingDTO [id=" + id + ", propertyName=" + propertyName + ", propertyValue=" + propertyValue + ", isProtected=" + isProtected
				+ ", protectedValue=" + Arrays.toString(protectedValue) + "]";
	}

}
