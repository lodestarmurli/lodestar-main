package com.lodestar.edupath.datatransferobject.dto;

import java.util.ArrayList;
import java.util.List;

public class IntegratedCourseDTO implements IModel
{

	private static final long			serialVersionUID	= 1L;
	private int							id;
	private String						programName;
	private String						description;
	private String						institute;
	private String						location;
	private String						eligibility;
	private String						entrance;
	private String						selectionProcess;
	private String						noOfSeats;
	private String						feeStructure;
	private String						programType;
	private String						courseDuration;
	private boolean						isActive;

	// non-table
	private String						occupationName;
	private int							occupationId;
	private int							integratedCourseId;

	private List<IntegratedCourseDTO>	courseList			= new ArrayList<IntegratedCourseDTO>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getProgramName()
	{
		return programName;
	}

	public void setProgramName(String programName)
	{
		this.programName = programName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getInstitute()
	{
		return institute;
	}

	public void setInstitute(String institute)
	{
		this.institute = institute;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getEligibility()
	{
		return eligibility;
	}

	public void setEligibility(String eligibility)
	{
		this.eligibility = eligibility;
	}

	public String getEntrance()
	{
		return entrance;
	}

	public void setEntrance(String entrance)
	{
		this.entrance = entrance;
	}

	public String getSelectionProcess()
	{
		return selectionProcess;
	}

	public void setSelectionProcess(String selectionProcess)
	{
		this.selectionProcess = selectionProcess;
	}

	public String getFeeStructure()
	{
		return feeStructure;
	}

	public void setFeeStructure(String feeStructure)
	{
		this.feeStructure = feeStructure;
	}

	public String getProgramType()
	{
		return programType;
	}

	public void setProgramType(String programType)
	{
		this.programType = programType;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getOccupationName()
	{
		return occupationName;
	}

	public void setOccupationName(String occupationName)
	{
		this.occupationName = occupationName;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getIntegratedCourseId()
	{
		return integratedCourseId;
	}

	public void setIntegratedCourseId(int integratedCourseId)
	{
		this.integratedCourseId = integratedCourseId;
	}

	@Override public String toString()
	{
		return "IntegratedCourseDTO [id=" + id + ", programName=" + programName + ", description=" + description + ", institute=" + institute + ", location="
				+ location + ", eligibility=" + eligibility + ", entrance=" + entrance + ", selectionProcess=" + selectionProcess + ", noOfSeats=" + noOfSeats
				+ ", feeStructure=" + feeStructure + ", programType=" + programType + ", courseDuration=" + courseDuration + ", isActive=" + isActive
				+ ", occupationName=" + occupationName + "]";
	}

	public String getCourseDuration()
	{
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration)
	{
		this.courseDuration = courseDuration;
	}

	public void setNoOfSeats(String noOfSeats)
	{
		this.noOfSeats = noOfSeats;
	}

	public String getNoOfSeats()
	{
		return noOfSeats;
	}

	public List<IntegratedCourseDTO> getCourseList()
	{
		return courseList;
	}

	public void setCourseList(List<IntegratedCourseDTO> courseList)
	{
		this.courseList = courseList;
	}

}
