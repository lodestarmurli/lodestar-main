package com.lodestar.edupath.datatransferobject.dto.path;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathPGSpecializationDTO implements IModel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					eduPathId;
	private String				name;


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

	@Override public String toString()
	{
		return "EduPathPGSpecializationDTO [id=" + id + ", eduPathId=" + eduPathId + ", name=" + name + "]";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
