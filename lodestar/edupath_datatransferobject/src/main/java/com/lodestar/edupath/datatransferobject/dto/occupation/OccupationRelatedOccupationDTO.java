package com.lodestar.edupath.datatransferobject.dto.occupation;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class OccupationRelatedOccupationDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					occupationId;
	private int					relatedOccupationId;

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

	public int getRelatedOccupationId()
	{
		return relatedOccupationId;
	}

	public void setRelatedOccupationId(int relatedOccupationId)
	{
		this.relatedOccupationId = relatedOccupationId;
	}

}
