package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class StudentTutorialCentreShortListDTO implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					tutorialId;
	private int					tutorialCityCentersId;

	// non-table
	private String				tutorialName;
	private String				locality;
	private String				cityName;
	private String				tutorialCenterAddress;
	
	//Start SASEDEVE edited By Mrutyunjaya on Date 03-06-2017
	
	private String 			contactno;
	private String				timeslots;
	
	

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(String timeslots) {
		this.timeslots = timeslots;
	}

	
	//END SASEDEVE edited By Mrutyunjaya on Date 03-06-2017
	
	
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

	public int getTutorialCityCentersId()
	{
		return tutorialCityCentersId;
	}

	public void setTutorialCityCentersId(int tutorialCityCentersId)
	{
		this.tutorialCityCentersId = tutorialCityCentersId;
	}

	public String getTutorialName()
	{
		return tutorialName;
	}

	public void setTutorialName(String tutorialName)
	{
		this.tutorialName = tutorialName;
	}

	public String getLocality()
	{
		return locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	@Override
	public String toString()
	{
		return "StudentTutorialCentreShortListDTO [id=" + id + ", studentId=" + studentId + ", tutorialId=" + tutorialId + ", tutorialCityCentersId="
				+ tutorialCityCentersId + ", tutorialName=" + tutorialName + ", locality=" + locality + ", cityName=" + cityName + "]";
	}

	public String getTutorialCenterAddress()
	{
		return tutorialCenterAddress;
	}

	public void setTutorialCenterAddress(String tutorialCenterAddress)
	{
		this.tutorialCenterAddress = tutorialCenterAddress;
	}

}
