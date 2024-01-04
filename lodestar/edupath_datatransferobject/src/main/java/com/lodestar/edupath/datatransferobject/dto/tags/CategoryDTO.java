package com.lodestar.edupath.datatransferobject.dto.tags;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.enumtype.TagTypeEnum;

public class CategoryDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private TagTypeEnum			catType;

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

	public TagTypeEnum getCatType()
	{
		return catType;
	}

	public void setCatType(TagTypeEnum catType)
	{
		this.catType = catType;
	}

	@Override
	public String toString()
	{
		return "CategoryDTO [id=" + id + ", name=" + name + ", catType=" + catType + "]";
	}

}
