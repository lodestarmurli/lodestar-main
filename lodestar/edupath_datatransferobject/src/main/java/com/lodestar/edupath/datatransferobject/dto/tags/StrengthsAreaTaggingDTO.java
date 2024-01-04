package com.lodestar.edupath.datatransferobject.dto.tags;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StrengthsAreaTaggingDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private String				strengthName;
	private int					areaId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getStrengthName()
	{
		return strengthName;
	}

	public void setStrengthName(String strengthName)
	{
		this.strengthName = strengthName;
	}

	public int getAreaId()
	{
		return areaId;
	}

	public void setAreaId(int areaId)
	{
		this.areaId = areaId;
	}
}
