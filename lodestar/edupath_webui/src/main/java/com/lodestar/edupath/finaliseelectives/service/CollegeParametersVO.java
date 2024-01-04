package com.lodestar.edupath.finaliseelectives.service;

import org.json.JSONArray;

import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;

public class CollegeParametersVO extends CollegeParametersDTO
{
	private static final long	serialVersionUID	= 1L;

	private JSONArray			paramValues;

	public JSONArray getParamValues()
	{
		return paramValues;
	}

	public void setParamValues(JSONArray paramValues)
	{
		this.paramValues = paramValues;
	}
}
