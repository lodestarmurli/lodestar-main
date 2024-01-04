package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeAchievementsDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					collegeId;
	private String				achievement;
	private String				type;

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

	public String getAchievement()
	{
		return achievement;
	}

	public void setAchievement(String achievement)
	{
		this.achievement = achievement;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "CollegeAchievementsDTO [id=" + id + ", collegeId=" + collegeId + ", achievement=" + achievement + ", type=" + type + "]";
	}

}
