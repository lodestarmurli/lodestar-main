package com.lodestar.edupath.datatransferobject.dto.path;

public class EdupathCountDTO
{
	private int		jobCount;
	private int		streamId;
	private int		edupathId;
	private String	name;
	private String	type;

	private double	percentage;
	private String	percentageStr;

	public int getJobCount()
	{
		return jobCount;
	}

	public void setJobCount(int jobCount)
	{
		this.jobCount = jobCount;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public double getPercentage()
	{
		return percentage;
	}

	public void setPercentage(double percentage)
	{
		this.percentage = percentage;
	}

	@Override public String toString()
	{
		return "EdupathCountDTO [jobCount=" + jobCount + ", streamId=" + streamId + ", name=" + name + ", type=" + type + ", percentage=" + percentage + "]";
	}

	public int getEdupathId()
	{
		return edupathId;
	}

	public void setEdupathId(int edupathId)
	{
		this.edupathId = edupathId;
	}

	public String getPercentageStr()
	{
		return percentageStr;
	}

	public void setPercentageStr(String percentageStr)
	{
		this.percentageStr = percentageStr;
	}

}
