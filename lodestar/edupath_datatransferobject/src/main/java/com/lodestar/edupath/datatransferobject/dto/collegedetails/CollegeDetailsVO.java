package com.lodestar.edupath.datatransferobject.dto.collegedetails;

import java.util.Set;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeDetailsVO implements IModel
{
	private static final long			serialVersionUID	= 1L;

	private int							id;
	private String						collegeName;
	private String						collegeType;
	private int							ageOfTheInstitute;
	private String						address;
	private String						zone;
	private String						website;
	private String						fees;
	private String						seats;
	private String						addFormSubmDate;
	private String						addFormSubmAddr;
	private String						availableStreams;
	private String						availableCombinations;
	private int							noStudents;
	private int							lastCutOff;
	private String						passPercent;
	private int							noTeachingStaff;
	private double						applicationFormFees;
	private String						sexPref;
	private Set<CollegeActivityVO>		activities;
	private Set<CollegeInfraStructVO>	infraStructs;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCollegeName()
	{
		return collegeName;
	}

	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}

	public String getCollegeType()
	{
		return collegeType;
	}

	public void setCollegeType(String collegeType)
	{
		this.collegeType = collegeType;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public String getAddFormSubmDate()
	{
		return addFormSubmDate;
	}

	public void setAddFormSubmDate(String addFormSubmDate)
	{
		this.addFormSubmDate = addFormSubmDate;
	}

	public String getAddFormSubmAddr()
	{
		return addFormSubmAddr;
	}

	public void setAddFormSubmAddr(String addFormSubmAddr)
	{
		this.addFormSubmAddr = addFormSubmAddr;
	}

	public String getAvailableStreams()
	{
		return availableStreams;
	}

	public void setAvailableStreams(String availableStreams)
	{
		this.availableStreams = availableStreams;
	}

	public String getAvailableCombinations()
	{
		return availableCombinations;
	}

	public void setAvailableCombinations(String availableCombinations)
	{
		this.availableCombinations = availableCombinations;
	}

	public int getNoStudents()
	{
		return noStudents;
	}

	public void setNoStudents(int noStudents)
	{
		this.noStudents = noStudents;
	}

	public int getLastCutOff()
	{
		return lastCutOff;
	}

	public void setLastCutOff(int lastCutOff)
	{
		this.lastCutOff = lastCutOff;
	}

	public String getPassPercent()
	{
		return passPercent;
	}

	public void setPassPercent(String passPercent)
	{
		this.passPercent = passPercent;
	}

	public int getNoTeachingStaff()
	{
		return noTeachingStaff;
	}

	public void setNoTeachingStaff(int noTeachingStaff)
	{
		this.noTeachingStaff = noTeachingStaff;
	}

	public double getApplicationFormFees()
	{
		return applicationFormFees;
	}

	public void setApplicationFormFees(double applicationFormFees)
	{
		this.applicationFormFees = applicationFormFees;
	}

	public Set<CollegeActivityVO> getActivities()
	{
		return activities;
	}

	public void setActivities(Set<CollegeActivityVO> activities)
	{
		this.activities = activities;
	}

	public Set<CollegeInfraStructVO> getInfraStructs()
	{
		return infraStructs;
	}

	public void setInfraStructs(Set<CollegeInfraStructVO> infraStructs)
	{
		this.infraStructs = infraStructs;
	}

	public String getZone()
	{
		return zone;
	}

	public void setZone(String zone)
	{
		this.zone = zone;
	}

	public String getFees()
	{
		return fees;
	}

	public void setFees(String fees)
	{
		this.fees = fees;
	}

	public String getSeats()
	{
		return seats;
	}

	public void setSeats(String seats)
	{
		this.seats = seats;
	}

	public int getAgeOfTheInstitute()
	{
		return ageOfTheInstitute;
	}

	public void setAgeOfTheInstitute(int ageOfTheInstitute)
	{
		this.ageOfTheInstitute = ageOfTheInstitute;
	}

	@Override
	public String toString()
	{
		return "CollegeDetailsVO [id=" + id + ", collegeName=" + collegeName + ", collegeType=" + collegeType + ", ageOfTheInstitute=" + ageOfTheInstitute
				+ ", address=" + address + ", zone=" + zone + ", website=" + website + ", fees=" + fees + ", seats=" + seats + ", addFormSubmDate="
				+ addFormSubmDate + ", addFormSubmAddr=" + addFormSubmAddr + ", availableStreams=" + availableStreams + ", availableCombinations="
				+ availableCombinations + ", noStudents=" + noStudents + ", lastCutOff=" + lastCutOff + ", passPercent=" + passPercent + ", noTeachingStaff="
				+ noTeachingStaff + ", applicationFormFees=" + applicationFormFees + ", activities=" + activities + ", infraStructs=" + infraStructs + "]";
	}

	public String getSexPref()
	{
		return sexPref;
	}

	public void setSexPref(String sexPref)
	{
		this.sexPref = sexPref;
	}

}
