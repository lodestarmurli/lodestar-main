package com.lodestar.edupath.tutorialsearch.bean;

import java.io.Serializable;
import java.util.List;

public class TutorialSearchFilterVO implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private Integer				examId;
	private List<Integer>		cityIds;
	private int					studentId;
	private String				locality;
	private List<Integer>		tutorialIds;

	// Pagination
	private int					pageNo;
	private boolean				getTotalCount;

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public boolean isGetTotalCount()
	{
		return getTotalCount;
	}

	public void setGetTotalCount(boolean getTotalCount)
	{
		this.getTotalCount = getTotalCount;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public Integer getExamId()
	{
		return examId;
	}

	public void setExamId(Integer examId)
	{
		this.examId = examId;
	}

	public List<Integer> getCityIds()
	{
		return cityIds;
	}

	public void setCityIds(List<Integer> cityIds)
	{
		this.cityIds = cityIds;
	}

	public String getLocality()
	{
		return locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public List<Integer> getTutorialIds()
	{
		return tutorialIds;
	}

	public void setTutorialIds(List<Integer> tutorialIds)
	{
		this.tutorialIds = tutorialIds;
	}

}
