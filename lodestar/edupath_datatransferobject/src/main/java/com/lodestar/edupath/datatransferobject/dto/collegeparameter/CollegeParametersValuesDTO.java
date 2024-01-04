package com.lodestar.edupath.datatransferobject.dto.collegeparameter;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeParametersValuesDTO implements IModel
{
	private static final long					serialVersionUID	= 1L;

	private int									id;
	private String								displayValue;
	private String								actualValue;
	private int									parameterId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDisplayValue()
	{
		return displayValue;
	}

	public void setDisplayValue(String displayValue)
	{
		this.displayValue = displayValue;
	}

	public String getActualValue()
	{
		return actualValue;
	}

	public void setActualValue(String actualValue)
	{
		this.actualValue = actualValue;
	}

	public int getParameterId()
	{
		return parameterId;
	}

	public void setParameterId(int parameterId)
	{
		this.parameterId = parameterId;
	}
}
