package com.lodestar.edupath.datatransferobject.dto.collegedetails;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeActivityVO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				activity;
	private String				activityExist;

	public String getActivityExist()
	{
		return activityExist;
	}

	public void setActivityExist(String activityExist)
	{
		this.activityExist = activityExist;
	}

	public String getActivity()
	{
		return activity;
	}

	public void setActivity(String activity)
	{
		this.activity = activity;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "CollegeActivityVO [id=" + id + ", activity=" + activity + ", activityExist=" + activityExist + "]";
	}

}
