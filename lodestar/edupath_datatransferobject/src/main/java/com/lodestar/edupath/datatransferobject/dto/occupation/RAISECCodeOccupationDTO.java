package com.lodestar.edupath.datatransferobject.dto.occupation;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class RAISECCodeOccupationDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					occupationId;
	private String				raiseCode;

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

	public String getRaiseCode()
	{
		return raiseCode;
	}

	public void setRaiseCode(String raiseCode)
	{
		this.raiseCode = raiseCode;
	}

	@Override
	public String toString() {
		return "RAISECCodeOccupationDTO [id=" + id + ", occupationId=" + occupationId + ", raiseCode=" + raiseCode
				+ "]";
	}

	
}
