package com.lodestar.edupath.datatransferobject.dto.path;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathPUElectivesDTO implements IModel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					eduPathId;
	private int					electiveId;
	private String				type;

	// non table
	private List<Integer>		idsList;
	private String				streamName;
	private String				subjectName;
	private int					streamId;
	private int					subjectId;
	private String				preferrdStreamId = "-1";
	private String				preferrdSubjectId = "-1";
	private String				isAnySubject		= "";
	private String				isAnyStream		= "";
	private int				 	occupationId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getEduPathId()
	{
		return eduPathId;
	}

	public void setEduPathId(int eduPathId)
	{
		this.eduPathId = eduPathId;
	}

	public int getElectiveId()
	{
		return electiveId;
	}

	public void setElectiveId(int electiveId)
	{
		this.electiveId = electiveId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override public String toString()
	{
		return "EduPathPUElectivesDTO [id=" + id + ", eduPathId=" + eduPathId + ", electiveId=" + electiveId + ", type=" + type + ", idsList=" + idsList + ", streamName=" + streamName + ", subjectName=" + subjectName + ", streamId=" + streamId + ", subjectId=" + subjectId + "]";
	}

	public List<Integer> getIdsList()
	{
		return idsList;
	}

	public void setIdsList(List<Integer> idsList)
	{
		this.idsList = idsList;
	}

	public String getStreamName()
	{
		return streamName;
	}

	public void setStreamName(String streamName)
	{
		this.streamName = streamName;
	}

	public String getSubjectName()
	{
		return subjectName;
	}

	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public int getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(int subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getPreferrdStreamId()
	{
		return preferrdStreamId;
	}

	public void setPreferrdStreamId(String preferrdStreamId)
	{
		this.preferrdStreamId = preferrdStreamId;
	}

	public String getPreferrdSubjectId()
	{
		return preferrdSubjectId;
	}

	public void setPreferrdSubjectId(String preferrdSubjectId)
	{
		this.preferrdSubjectId = preferrdSubjectId;
	}

	public String getIsAnySubject()
	{
		return isAnySubject;
	}

	public void setIsAnySubject(String isAnySubject)
	{
		this.isAnySubject = isAnySubject;
	}

	public String getIsAnyStream()
	{
		return isAnyStream;
	}

	public void setIsAnyStream(String isAnyStream)
	{
		this.isAnyStream = isAnyStream;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

}
