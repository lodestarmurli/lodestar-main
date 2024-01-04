package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TutorialCityCentersVO implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private int					tutorialCenterId;
	private int					tutorialId;
	private int					ageOfCenter;
	private int					noOffEnrollments;
	private int					highestRank;
	private int					noOfFaculty;
	private String				timings;
	private String				courses;
	private String				examNames;
	private String				address;
	private String				contactNumbers;
	private String				tutorialName;
	private int					year;

	// non-table
	private int					examId;
	private List<Integer>		tutorialIds			= new ArrayList<Integer>();

	public int getTutorialCenterId()
	{
		return tutorialCenterId;
	}

	public void setTutorialCenterId(int tutorialCenterId)
	{
		this.tutorialCenterId = tutorialCenterId;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

	public int getAgeOfCenter()
	{
		return ageOfCenter;
	}

	public void setAgeOfCenter(int ageOfCenter)
	{
		this.ageOfCenter = ageOfCenter;
	}

	public int getNoOffEnrollments()
	{
		return noOffEnrollments;
	}

	public void setNoOffEnrollments(int noOffEnrollments)
	{
		this.noOffEnrollments = noOffEnrollments;
	}

	public int getHighestRank()
	{
		return highestRank;
	}

	public void setHighestRank(int highestRank)
	{
		this.highestRank = highestRank;
	}

	public int getNoOfFaculty()
	{
		return noOfFaculty;
	}

	public void setNoOfFaculty(int noOfFaculty)
	{
		this.noOfFaculty = noOfFaculty;
	}

	public String getTimings()
	{
		return timings;
	}

	public void setTimings(String timings)
	{
		this.timings = timings;
	}

	public String getCourses()
	{
		return courses;
	}

	public void setCourses(String courses)
	{
		this.courses = courses;
	}

	public String getExamNames()
	{
		return examNames;
	}

	public void setExamNames(String examNames)
	{
		this.examNames = examNames;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getContactNumbers()
	{
		return contactNumbers;
	}

	public void setContactNumbers(String contactNumbers)
	{
		this.contactNumbers = contactNumbers;
	}

	public String getTutorialName()
	{
		return tutorialName;
	}

	public void setTutorialName(String tutorialName)
	{
		this.tutorialName = tutorialName;
	}

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public List<Integer> getTutorialIds()
	{
		return tutorialIds;
	}

	public void setTutorialIds(List<Integer> tutorialIds)
	{
		this.tutorialIds = tutorialIds;
	}

	@Override
	public String toString()
	{
		return "TutorialCityCentersVO [tutorialCenterId=" + tutorialCenterId + ", tutorialId=" + tutorialId + ", ageOfCenter=" + ageOfCenter + ", noOffEnrollments="
				+ noOffEnrollments + ", highestRank=" + highestRank + ", noOfFaculty=" + noOfFaculty + ", timings=" + timings + ", courses=" + courses
				+ ", examNames=" + examNames + ", address=" + address + ", contactNumbers=" + contactNumbers + ", tutorialName=" + tutorialName + ", examId="
				+ examId + ", tutorialIds=" + tutorialIds + "]";
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

}
