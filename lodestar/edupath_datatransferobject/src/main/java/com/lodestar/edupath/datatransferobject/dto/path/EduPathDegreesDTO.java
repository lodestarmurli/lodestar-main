package com.lodestar.edupath.datatransferobject.dto.path;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathDegreesDTO implements IModel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					eduPathId;
	private String				name;
	private String				type;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getEduPathId()
	{
		return eduPathId;
	}

	public void setEduPathId(int eduPathId)
	{
		this.eduPathId = eduPathId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override public String toString()
	{
		return "EduPathDegreesDTO [id=" + id + ", eduPathId=" + eduPathId + ", name=" + name + ", type=" + type + "]";
	}
}
