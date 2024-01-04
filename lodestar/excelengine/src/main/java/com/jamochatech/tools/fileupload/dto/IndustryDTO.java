package com.jamochatech.tools.fileupload.dto;

import java.io.Serializable;

import com.jamochatech.tools.excelengine.dto.IModel;

public class IndustryDTO implements IModel, Serializable
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private String				name;

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

	@Override
	public String toString()
	{
		return "IndustryDTO [id=" + id + ", name=" + name + "]";
	}
}
