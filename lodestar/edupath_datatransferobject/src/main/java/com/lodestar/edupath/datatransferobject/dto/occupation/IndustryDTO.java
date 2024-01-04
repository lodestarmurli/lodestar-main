package com.lodestar.edupath.datatransferobject.dto.occupation;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class IndustryDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private String				name;
	private boolean				isPrimary;

	// non table
	private int					pathWayId;
	private String				pathWayName;

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

	public boolean isPrimary()
	{
		return isPrimary;
	}

	public boolean getIsPrimary()
	{
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary)
	{
		this.isPrimary = isPrimary;
	}

	public int getPathWayId()
	{
		return pathWayId;
	}

	public void setPathWayId(int pathWayId)
	{
		this.pathWayId = pathWayId;
	}

	public String getPathWayName()
	{
		return pathWayName;
	}

	public void setPathWayName(String pathWayName)
	{
		this.pathWayName = pathWayName;
	}
}
