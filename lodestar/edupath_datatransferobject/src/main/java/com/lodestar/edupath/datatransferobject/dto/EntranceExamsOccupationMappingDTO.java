package com.lodestar.edupath.datatransferobject.dto;

public class EntranceExamsOccupationMappingDTO implements IModel
{

	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					entranceExamId;
	private int					occupationId;
	private String				required;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getEntranceExamId()
	{
		return entranceExamId;
	}

	public void setEntranceExamId(int entranceExamId)
	{
		this.entranceExamId = entranceExamId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	@Override public String toString()
	{
		return "EntranceExamsOccupationMappingDTO [id=" + id + ", entranceExamId=" + entranceExamId + ", occupationId=" + occupationId + "]";
	}

	public String getRequired()
	{
		return required;
	}

	public void setRequired(String required)
	{
		this.required = required;
	}

}
