package com.lodestar.edupath.datatransferobject.dto.role;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class RoleTypeDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= -1007025021067984465L;
	private int					id;
	private String				name;
	private int					weight;

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

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	@Override
	public String toString()
	{
		return "RoleTypeDTO [id=" + id + ", name=" + name + ", weight=" + weight + "]";
	}

}
