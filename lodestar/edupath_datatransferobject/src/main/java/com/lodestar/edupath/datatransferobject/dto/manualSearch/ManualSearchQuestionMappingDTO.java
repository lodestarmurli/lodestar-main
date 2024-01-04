package com.lodestar.edupath.datatransferobject.dto.manualSearch;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ManualSearchQuestionMappingDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private double					questionSlNo;
	private Integer				valueId;
	private String				lookupColumn;
	private String				tagValue;
	private String				paramJSON;
	private String				displayString;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getQuestionSlNo()
	{
		return questionSlNo;
	}

	public void setQuestionSlNo(double questionSlNo)
	{
		this.questionSlNo = questionSlNo;
	}

	public Integer getValueId()
	{
		return valueId;
	}

	public void setValueId(Integer valueId)
	{
		this.valueId = valueId;
	}

	public String getLookupColumn()
	{
		return lookupColumn;
	}

	public void setLookupColumn(String lookupColumn)
	{
		this.lookupColumn = lookupColumn;
	}

	public String getTagValue()
	{
		return tagValue;
	}

	public void setTagValue(String tagValue)
	{
		this.tagValue = tagValue;
	}

	public String getParamJSON()
	{
		return paramJSON;
	}

	public void setParamJSON(String paramJSON)
	{
		this.paramJSON = paramJSON;
	}

	public String getDisplayString()
	{
		return displayString;
	}

	public void setDisplayString(String displayString)
	{
		this.displayString = displayString;
	}

}
