package com.lodestar.edupath.datatransferobject.dto.tags;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.enumtype.TagTypeEnum;

public class AlertsDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private String				id;
	private TagTypeEnum			alertType;
	private String				name;
	private String				alertValue;
	private boolean				isActive;

	// Non table
	private int					occupationId;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public TagTypeEnum getAlertType()
	{
		return alertType;
	}

	public void setAlertType(TagTypeEnum alertType)
	{
		this.alertType = alertType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAlertValue()
	{
		return alertValue;
	}

	public void setAlertValue(String alertValue)
	{
		this.alertValue = alertValue;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	@Override
	public String toString()
	{
		return "AlertsDTO [id=" + id + ", alertType=" + alertType + ", name=" + name + ", alertValue=" + alertValue + ", isActive=" + isActive + ", occupationId="
				+ occupationId + "]";
	}

}
