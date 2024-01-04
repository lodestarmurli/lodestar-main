package com.lodestar.edupath.datatransferobject.dto.collegesearch;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CustCollegeStreamVO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					streamId;
	private String				streamsOffered;

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public String getStreamsOffered()
	{
		return streamsOffered;
	}

	public void setStreamsOffered(String streamsOffered)
	{
		this.streamsOffered = streamsOffered;
	}

	@Override
	public String toString()
	{
		return "CustCollegeStreamDTO [streamId=" + streamId + ", streamsOffered=" + streamsOffered + "]";
	}

}
