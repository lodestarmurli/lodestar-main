package com.lodestar.edupath.datatransferobject.dto.occupation;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class OccupationTaggingDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					occupationId;
	private String				occupationTag;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public String getOccupationTag()
	{
		return occupationTag;
	}

	public void setOccupationTag(String occupationTag)
	{
		this.occupationTag = occupationTag;
	}
}
