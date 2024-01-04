package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialProgramDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					tutorialId;
	private String				programName;
	private int					examId;
	private Boolean				hasClassroom;
	private Boolean				hasCorrespondence;
	private String				totalMocks;
	private String				specialFeatures;

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

	public String getProgramName()
	{
		return programName;
	}

	public void setProgramName(String programName)
	{
		this.programName = programName;
	}

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public Boolean getHasClassroom()
	{
		return hasClassroom;
	}

	public void setHasClassroom(Boolean hasClassroom)
	{
		this.hasClassroom = hasClassroom;
	}

	public Boolean getHasCorrespondence()
	{
		return hasCorrespondence;
	}

	public void setHasCorrespondence(Boolean hasCorrespondence)
	{
		this.hasCorrespondence = hasCorrespondence;
	}

	public String getTotalMocks()
	{
		return totalMocks;
	}

	public void setTotalMocks(String totalMocks)
	{
		this.totalMocks = totalMocks;
	}

	public String getSpecialFeatures()
	{
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures)
	{
		this.specialFeatures = specialFeatures;
	}

	@Override
	public String toString()
	{
		return "TutorialProgram [id=" + id + ", tutorialId=" + tutorialId + ", programName=" + programName + ", examId=" + examId + ", hasClassroom=" + hasClassroom
				+ ", hasCorrespondence=" + hasCorrespondence + ", totalMocks=" + totalMocks + ", specialFeatures=" + specialFeatures + "]";
	}

}
