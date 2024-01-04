package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialCombinationVO implements Serializable, IModel
{
	private static final long		serialVersionUID	= 1L;

	private int						id;
	private String					name;
	private int						centersInIndia;
	private int						centersInCity;
	private int						yearOfEst;
	private String					programNames;
	private String					admissionCriteria;
	private int						highestRank;

	// non-table column
	private List<ExamTutorialVO>	examTutorialList	= new ArrayList<ExamTutorialVO>();
	private int						ageOfCenter;
	private int						noOfFaculty;
	private String					timings;
	private String					address;
	private String					contactNumbers;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCentersInIndia()
	{
		return centersInIndia;
	}

	public void setCentersInIndia(int centersInIndia)
	{
		this.centersInIndia = centersInIndia;
	}

	public int getCentersInCity()
	{
		return centersInCity;
	}

	public void setCentersInCity(int centersInCity)
	{
		this.centersInCity = centersInCity;
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

	public List<ExamTutorialVO> getExamTutorialList()
	{
		return examTutorialList;
	}

	public void setExamTutorialList(List<ExamTutorialVO> examTutorialList)
	{
		this.examTutorialList = examTutorialList;
	}

	@Override
	public String toString()
	{
		return "TutorialCombinationVO [id=" + id + ", name=" + name + ", centersInIndia=" + centersInIndia + ", centersInCity=" + centersInCity + ", yearOfEst="
				+ yearOfEst + ", programNames=" + programNames + ", admissionCriteria=" + admissionCriteria + ", highestRank=" + highestRank + ", examTutorialList="
				+ examTutorialList + ", ageOfCenter=" + ageOfCenter + ", noOfFaculty=" + noOfFaculty + ", timings=" + timings + ", address=" + address
				+ ", contactNumbers=" + contactNumbers + "]";
	}

	public int getHighestRank()
	{
		return highestRank;
	}

	public void setHighestRank(int highestRank)
	{
		this.highestRank = highestRank;
	}

	public int getAgeOfCenter()
	{
		return ageOfCenter;
	}

	public void setAgeOfCenter(int ageOfCenter)
	{
		this.ageOfCenter = ageOfCenter;
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

}
