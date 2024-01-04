package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeInfraDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private int					count;
	private String				capacity;
	private int					collegeId;
	private String				type;

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

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "CollegeInfraDTO [id=" + id + ", name=" + name + ", count=" + count + ", capacity=" + capacity + ", collegeId=" + collegeId + ", type=" + type + "]";
	}

	public String getCapacity()
	{
		return capacity;
	}

	public void setCapacity(String capacity)
	{
		this.capacity = capacity;
	}

}
