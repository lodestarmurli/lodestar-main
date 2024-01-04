package com.lodestar.edupath.datatransferobject.dto;

public class IntegratedCourseOccupationMappingDTO implements IModel
{

	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					integratedCourseId;
	private int					occupationId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIntegratedCourseId()
	{
		return integratedCourseId;
	}

	public void setIntegratedCourseId(int integratedCourseId)
	{
		this.integratedCourseId = integratedCourseId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	@Override
	public String toString()
	{
		return "IntegratedCourseOccupationMappingDTO [id=" + id + ", integratedCourseId=" + integratedCourseId + ", occupationId=" + occupationId + "]";
	}

}
