package com.lodestar.edupath.datatransferobject.dto.tutorialsearch;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialSearchVO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					tutorialId;
	private String				name;
	private int					centerAllIndia;
	private int					noOfCentersInCity;
	private int					yearOfEst;
	private String				programNames;
	private String				admissionCriteria;
	private int					noOfStudentsEnroll;
	private int					noOfStudentsClear;
	private String				throughPutRatio;
	private String				examNames;
	private int					classroom;

	// tutorial center level
	private int					tutorialCenterId;
	private int					ageOfCenter;
	private int					noOffEnrollments;
	private int					noOfFaculty;
	private String				timings;
	private String				address;
	private String				contactNumbers;
	private String				cityName;
	private String				locality;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCenterAllIndia()
	{
		return centerAllIndia;
	}

	public void setCenterAllIndia(int centerAllIndia)
	{
		this.centerAllIndia = centerAllIndia;
	}

	public int getNoOfCentersInCity()
	{
		return noOfCentersInCity;
	}

	public void setNoOfCentersInCity(int noOfCentersInCity)
	{
		this.noOfCentersInCity = noOfCentersInCity;
	}

	public int getYearOfEst()
	{
		return yearOfEst;
	}

	public void setYearOfEst(int yearOfEst)
	{
		this.yearOfEst = yearOfEst;
	}

	public String getProgramNames()
	{
		return programNames;
	}

	public void setProgramNames(String programNames)
	{
		this.programNames = programNames;
	}

	public String getAdmissionCriteria()
	{
		return admissionCriteria;
	}

	public void setAdmissionCriteria(String admissionCriteria)
	{
		this.admissionCriteria = admissionCriteria;
	}

	public int getNoOfStudentsEnroll()
	{
		return noOfStudentsEnroll;
	}

	public void setNoOfStudentsEnroll(int noOfStudentsEnroll)
	{
		this.noOfStudentsEnroll = noOfStudentsEnroll;
	}

	public int getNoOfStudentsClear()
	{
		return noOfStudentsClear;
	}

	public void setNoOfStudentsClear(int noOfStudentsClear)
	{
		this.noOfStudentsClear = noOfStudentsClear;
	}

	public String getExamNames()
	{
		return examNames;
	}

	public void setExamNames(String examNames)
	{
		this.examNames = examNames;
	}

	public int getClassroom()
	{
		return classroom;
	}

	public void setClassroom(int classroom)
	{
		this.classroom = classroom;
	}

	public String getThroughPutRatio()
	{
		return throughPutRatio;
	}

	public void setThroughPutRatio(String throughPutRatio)
	{
		this.throughPutRatio = throughPutRatio;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

	public int getTutorialCenterId()
	{
		return tutorialCenterId;
	}

	public void setTutorialCenterId(int tutorialCenterId)
	{
		this.tutorialCenterId = tutorialCenterId;
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

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getLocality()
	{
		return locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	@Override
	public String toString()
	{
		return "TutorialSearchVO [tutorialId=" + tutorialId + ", name=" + name + ", centerAllIndia=" + centerAllIndia + ", noOfCentersInCity=" + noOfCentersInCity
				+ ", yearOfEst=" + yearOfEst + ", programNames=" + programNames + ", admissionCriteria=" + admissionCriteria + ", noOfStudentsEnroll="
				+ noOfStudentsEnroll + ", noOfStudentsClear=" + noOfStudentsClear + ", throughPutRatio=" + throughPutRatio + ", examNames=" + examNames
				+ ", classroom=" + classroom + ", tutorialCenterId=" + tutorialCenterId + ", ageOfCenter=" + ageOfCenter + ", noOffEnrollments=" + noOffEnrollments
				+ ", noOfFaculty=" + noOfFaculty + ", timings=" + timings + ", address=" + address + ", contactNumbers=" + contactNumbers + ", cityName=" + cityName
				+ ", locality=" + locality + "]";
	}

}
