package com.lodestar.edupath.datatransferobject.dto.elective;

public class CombinationDTO
{
	private int		id;
	private String	name;
	private Integer	streamId;
	
	//
	private Integer	noOfCollege;
	
	private String subjectIds;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getStreamId()
	{
		return streamId;
	}

	public void setStreamId(Integer streamId)
	{
		this.streamId = streamId;
	}

	public Integer getNoOfCollege()
	{
		return noOfCollege;
	}

	public void setNoOfCollege(Integer noOfCollege)
	{
		this.noOfCollege = noOfCollege;
	}

	public String getSubjectIds()
	{
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds)
	{
		this.subjectIds = subjectIds;
	}

}
