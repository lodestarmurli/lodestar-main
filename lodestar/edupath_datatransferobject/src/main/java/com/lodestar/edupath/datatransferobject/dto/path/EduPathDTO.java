package com.lodestar.edupath.datatransferobject.dto.path;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathDTO implements IModel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					industryId;
	private int					pathwayId;
	private int					occupationId;
	private int					pucompulsoryStreamId;
	private int					pupreferredStreamId;
	private boolean				isActive;
	
	//non table
	private List<Integer>       occupationIdsList;
	private List<Integer>       industryIdsList;
	private String 				streamName;
	private int 				streamId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIndustryId()
	{
		return industryId;
	}

	public void setIndustryId(int industryId)
	{
		this.industryId = industryId;
	}

	public int getPathwayId()
	{
		return pathwayId;
	}

	public void setPathwayId(int pathwayId)
	{
		this.pathwayId = pathwayId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getPucompulsoryStreamId()
	{
		return pucompulsoryStreamId;
	}

	public void setPucompulsoryStreamId(int pucompulsoryStreamId)
	{
		this.pucompulsoryStreamId = pucompulsoryStreamId;
	}

	public int getPupreferredStreamId()
	{
		return pupreferredStreamId;
	}

	public void setPupreferredStreamId(int pupreferredStreamId)
	{
		this.pupreferredStreamId = pupreferredStreamId;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	@Override public String toString()
	{
		return "EduPathDTO [id=" + id + ", industryId=" + industryId + ", pathwayId=" + pathwayId + ", occupationId=" + occupationId + ", pucompulsoryStreamId=" + pucompulsoryStreamId + ", pupreferredStreamId=" + pupreferredStreamId + ", isActive=" + isActive + "]";
	}

	public List<Integer> getOccupationIdsList()
	{
		return occupationIdsList;
	}

	public void setOccupationIdsList(List<Integer> occupationIdsList)
	{
		this.occupationIdsList = occupationIdsList;
	}

	public List<Integer> getIndustryIdsList()
	{
		return industryIdsList;
	}

	public void setIndustryIdsList(List<Integer> industryIdsList)
	{
		this.industryIdsList = industryIdsList;
	}

	public String getStreamName()
	{
		return streamName;
	}

	public void setStreamName(String streamName)
	{
		this.streamName = streamName;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}
}
