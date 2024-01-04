package com.lodestar.edupath.datatransferobject.dto.occupation;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class OccupationAbilityMappingDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					occupationId;
	private boolean				ra;
	private boolean				va;
	private boolean				sa;
	private boolean				na;
	private boolean				ma;

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

	public boolean isRa()
	{
		return ra;
	}

	public void setRa(boolean ra)
	{
		this.ra = ra;
	}

	public boolean isVa()
	{
		return va;
	}

	public void setVa(boolean va)
	{
		this.va = va;
	}

	public boolean isSa()
	{
		return sa;
	}

	public void setSa(boolean sa)
	{
		this.sa = sa;
	}

	public boolean isNa()
	{
		return na;
	}

	public void setNa(boolean na)
	{
		this.na = na;
	}

	public boolean isMa()
	{
		return ma;
	}

	public void setMa(boolean ma)
	{
		this.ma = ma;
	}

}
