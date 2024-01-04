package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StudentsTopRankInExamsDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					collegeId;
	private int					examId;
	private int					rankValue;

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

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public int getRankValue()
	{
		return rankValue;
	}

	public void setRankValue(int rankValue)
	{
		this.rankValue = rankValue;
	}

	@Override
	public String toString()
	{
		return "StudentsTopRankInExamsDTO [id=" + id + ", collegeId=" + collegeId + ", examId=" + examId + ", rankValue=" + rankValue + "]";
	}

}
