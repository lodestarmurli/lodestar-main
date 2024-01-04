package com.lodestar.edupath.datatransferobject.dto.tags;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TagsDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					industryId;
	private int					pathWayId;
	private int					occupationId;
	private String				subjects;

	private int					eduLevelId;

	private int					categoryId;
	private int					subCategoryId;
	private int					areaId;
	private boolean				isActive;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public int getPathWayId()
	{
		return pathWayId;
	}

	public void setPathWayId(int pathWayId)
	{
		this.pathWayId = pathWayId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getEduLevelId()
	{
		return eduLevelId;
	}

	public void setEduLevelId(int eduLevelId)
	{
		this.eduLevelId = eduLevelId;
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

	public int getAreaId()
	{
		return areaId;
	}

	public void setAreaId(int areaId)
	{
		this.areaId = areaId;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getSubjects()
	{
		return subjects;
	}

	public void setSubjects(String subjects)
	{
		this.subjects = subjects;
	}

}
