package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeCourseFeeSeatsDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					collegeId;
	private int					streamId;
	private int					combinationId;
	private int					reservationId;
	private int					fees;
	private int					seats;
	private int					cutoff;

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

	public int getReservationId()
	{
		return reservationId;
	}

	public void setReservationId(int reservationId)
	{
		this.reservationId = reservationId;
	}

	public int getFees()
	{
		return fees;
	}

	public void setFees(int fees)
	{
		this.fees = fees;
	}

	public int getSeats()
	{
		return seats;
	}

	public void setSeats(int seats)
	{
		this.seats = seats;
	}

	public int getCutoff()
	{
		return cutoff;
	}

	public void setCutoff(int cutoff)
	{
		this.cutoff = cutoff;
	}

	public int getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(int combinationId)
	{
		this.combinationId = combinationId;
	}

}
