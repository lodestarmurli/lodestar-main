package com.lodestar.edupath.datatransferobject.dto.path;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class EduPathPUStreamsDTO implements IModel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					eduPathId;
	private int					streamId;
	private String				type;

	// non table
	private String				streamName;

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

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getStreamName()
	{
		return streamName;
	}

	public void setStreamName(String streamName)
	{
		this.streamName = streamName;
	}

	@Override public String toString()
	{
		return "PUStreamsDTO [id=" + id + ", eduPathId=" + eduPathId + ", streamId=" + streamId + ", type=" + type + ", streamName=" + streamName + "]";
	}
}
