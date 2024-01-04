package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeStreamCombinationDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					collegeId;
	private int					streamId;
	private int					combinationId;
	private int					mgmtCutOff;
	private int					mgmtSeats;
	private int					govtSeats;
	private int					totalSeats;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public int getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(int combinationId)
	{
		this.combinationId = combinationId;
	}

	public int getMgmtCutOff()
	{
		return mgmtCutOff;
	}

	public void setMgmtCutOff(int mgmtCutOff)
	{
		this.mgmtCutOff = mgmtCutOff;
	}

	public int getMgmtSeats()
	{
		return mgmtSeats;
	}

	public void setMgmtSeats(int mgmtSeats)
	{
		this.mgmtSeats = mgmtSeats;
	}

	public int getGovtSeats()
	{
		return govtSeats;
	}

	public void setGovtSeats(int govtSeats)
	{
		this.govtSeats = govtSeats;
	}

	@Override
	public String toString()
	{
		return "CollegeStreamCombinationDTO [id=" + id + ", collegeId=" + collegeId + ", streamId=" + streamId + ", combinationId=" + combinationId
				+ ", mgmtCutOff=" + mgmtCutOff + ", mgmtSeats=" + mgmtSeats + ", govtSeats=" + govtSeats + "]";
	}

	public int getTotalSeats()
	{
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats)
	{
		this.totalSeats = totalSeats;
	}

}
