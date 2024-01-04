package com.lodestar.edupath.datatransferobject.dto.elective;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class SubjectStreamCombinationVO implements IModel
{

	private static final long		serialVersionUID	= 1L;

	private int						subjectId;
	private String					streamName;
	private List<CombinationDTO>	combinations;

	public String getStreamName()
	{
		return streamName;
	}

	public void setStreamName(String streamName)
	{
		this.streamName = streamName;
	}

	public List<CombinationDTO> getCombinations()
	{
		return combinations;
	}

	public void setCombinations(List<CombinationDTO> combinations)
	{
		this.combinations = combinations;
	}

	public int getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(int subjectId)
	{
		this.subjectId = subjectId;
	}

}
