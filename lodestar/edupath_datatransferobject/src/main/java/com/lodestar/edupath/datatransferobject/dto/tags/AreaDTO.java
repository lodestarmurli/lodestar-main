package com.lodestar.edupath.datatransferobject.dto.tags;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class AreaDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private int					categoryId;
	private int					subCategoryId;
	private SubCategoryDTO		subCategoryDTO;
	private CategoryDTO			categoryDTO;

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

	public int getSubCategoryId()
	{
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId)
	{
		this.subCategoryId = subCategoryId;
	}

	@Override
	public String toString()
	{
		return "AreaDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", subCategoryId=" + subCategoryId + ", subCategoryDTO=" + subCategoryDTO
				+ ", categoryDTO=" + categoryDTO + "]";
	}

	public SubCategoryDTO getSubCategoryDTO()
	{
		return subCategoryDTO;
	}

	public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO)
	{
		this.subCategoryDTO = subCategoryDTO;
	}

	public CategoryDTO getCategoryDTO()
	{
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO)
	{
		this.categoryDTO = categoryDTO;
	}

}
