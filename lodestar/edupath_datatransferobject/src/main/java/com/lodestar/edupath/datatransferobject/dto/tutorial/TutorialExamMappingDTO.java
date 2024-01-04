package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialExamMappingDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					tutorialId;
	private int					examId;
	private int					noFaculty;
	private String				qualificationOfFaculty;
	private String				admissionCriteria;
	private int					noClassEnroll;
	private int					noCorresEnroll;
	private String				topRankCutOff;
	private String				clearClass;
	private String				clearCorres;

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

	public int getNoFaculty()
	{
		return noFaculty;
	}

	public void setNoFaculty(int noFaculty)
	{
		this.noFaculty = noFaculty;
	}

	public String getQualificationOfFaculty()
	{
		return qualificationOfFaculty;
	}

	public void setQualificationOfFaculty(String qualificationOfFaculty)
	{
		this.qualificationOfFaculty = qualificationOfFaculty;
	}

	public String getAdmissionCriteria()
	{
		return admissionCriteria;
	}

	public void setAdmissionCriteria(String admissionCriteria)
	{
		this.admissionCriteria = admissionCriteria;
	}

	public int getNoClassEnroll()
	{
		return noClassEnroll;
	}

	public void setNoClassEnroll(int noClassEnroll)
	{
		this.noClassEnroll = noClassEnroll;
	}

	public int getNoCorresEnroll()
	{
		return noCorresEnroll;
	}

	public void setNoCorresEnroll(int noCorresEnroll)
	{
		this.noCorresEnroll = noCorresEnroll;
	}

	public String getTopRankCutOff()
	{
		return topRankCutOff;
	}

	public void setTopRankCutOff(String topRankCutOff)
	{
		this.topRankCutOff = topRankCutOff;
	}

	public String getClearClass()
	{
		return clearClass;
	}

	public void setClearClass(String clearClass)
	{
		this.clearClass = clearClass;
	}

	public String getClearCorres()
	{
		return clearCorres;
	}

	public void setClearCorres(String clearCorres)
	{
		this.clearCorres = clearCorres;
	}

	@Override
	public String toString()
	{
		return "TutorialExamMapping [id=" + id + ", tutorialId=" + tutorialId + ", examId=" + examId + ", noFaculty=" + noFaculty + ", qualificationOfFaculty="
				+ qualificationOfFaculty + ", admissionCriteria=" + admissionCriteria + ", noClassEnroll=" + noClassEnroll + ", noCorresEnroll=" + noCorresEnroll
				+ ", topRankCutOff=" + topRankCutOff + ", clearClass=" + clearClass + ", clearCorres=" + clearCorres + "]";
	}

}
