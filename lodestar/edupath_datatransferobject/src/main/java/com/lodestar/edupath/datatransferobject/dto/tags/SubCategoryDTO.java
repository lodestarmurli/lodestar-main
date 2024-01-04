package com.lodestar.edupath.datatransferobject.dto.tags;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class SubCategoryDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private int					categoryId;

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

	public int getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}

	@Override
	public String toString()
	{
		return "SubCategoryDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + "]";
	}

}
