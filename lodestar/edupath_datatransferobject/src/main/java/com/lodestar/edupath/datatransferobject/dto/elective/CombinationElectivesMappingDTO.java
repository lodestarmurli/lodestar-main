package com.lodestar.edupath.datatransferobject.dto.elective;

public class CombinationElectivesMappingDTO
{
	private int		id;
	private Integer	combinationId;
	private Integer	electiveId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Integer getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(Integer combinationId)
	{
		this.combinationId = combinationId;
	}

	public Integer getElectiveId()
	{
		return electiveId;
	}

	public void setElectiveId(Integer electiveId)
	{
		this.electiveId = electiveId;
	}

}
