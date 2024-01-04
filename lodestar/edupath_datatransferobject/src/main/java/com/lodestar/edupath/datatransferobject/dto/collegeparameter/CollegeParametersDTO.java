package com.lodestar.edupath.datatransferobject.dto.collegeparameter;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeParametersDTO implements IModel
{
	private static final long					serialVersionUID	= 1L;

	private int									id;
	private String								parameter;
	private String								description;
	private String								filterDisplayName;
	private String								paramName;
	private int									displayOrder;
	private Boolean								isRangeParam;
	private Boolean								isMultiSelect;

	private List<CollegeParametersValuesDTO>	values;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getParameter()
	{
		return parameter;
	}

	public void setParameter(String parameter)
	{
		this.parameter = parameter;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getFilterDisplayName()
	{
		return filterDisplayName;
	}

	public void setFilterDisplayName(String filterDisplayName)
	{
		this.filterDisplayName = filterDisplayName;
	}

	public String getParamName()
	{
		return paramName;
	}

	public void setParamName(String paramName)
	{
		this.paramName = paramName;
	}

	public int getDisplayOrder()
	{
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public Boolean getIsRangeParam()
	{
		return isRangeParam;
	}

	public void setIsRangeParam(Boolean isRangeParam)
	{
		this.isRangeParam = isRangeParam;
	}

	public List<CollegeParametersValuesDTO> getValues()
	{
		return values;
	}

	public void setValues(List<CollegeParametersValuesDTO> values)
	{
		this.values = values;
	}

	public Boolean getIsMultiSelect()
	{
		return isMultiSelect;
	}

	public void setIsMultiSelect(Boolean isMultiSelect)
	{
		this.isMultiSelect = isMultiSelect;
	}
}
