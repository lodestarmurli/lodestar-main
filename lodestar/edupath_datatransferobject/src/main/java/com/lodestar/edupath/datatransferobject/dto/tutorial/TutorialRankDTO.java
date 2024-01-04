package com.lodestar.edupath.datatransferobject.dto.tutorial;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialRankDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					tutorialId;
	private int					examId;
	private int					year;
	private int					classroom;
	private int					correspondance;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getClassroom()
	{
		return classroom;
	}

	public void setClassroom(int classroom)
	{
		this.classroom = classroom;
	}

	public int getCorrespondance()
	{
		return correspondance;
	}

	public void setCorrespondance(int correspondance)
	{
		this.correspondance = correspondance;
	}

	@Override
	public String toString()
	{
		return "TutorialRank [id=" + id + ", tutorialId=" + tutorialId + ", examId=" + examId + ", year=" + year + ", classroom=" + classroom + ", correspondance="
				+ correspondance + "]";
	}

}
