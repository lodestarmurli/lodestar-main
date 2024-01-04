package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;
import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialsDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private String				headOfficeAddress;
	private String				contactNumbers;
	private String				website;
	private String				nrankInst;
	private Date				yearOfEst;
	private int					centerAllIndia;
	private Boolean				isActive;
	private int					studentId;

	// non-table columns
	private int					examId;
	private int					cityId;
	private String				locality;
	private int					tutorialCityCentersId;

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

	public String getHeadOfficeAddress()
	{
		return headOfficeAddress;
	}

	public void setHeadOfficeAddress(String headOfficeAddress)
	{
		this.headOfficeAddress = headOfficeAddress;
	}

	public String getContactNumbers()
	{
		return contactNumbers;
	}

	public void setContactNumbers(String contactNumbers)
	{
		this.contactNumbers = contactNumbers;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public String getNrankInst()
	{
		return nrankInst;
	}

	public void setNrankInst(String nrankInst)
	{
		this.nrankInst = nrankInst;
	}

	public Date getYearOfEst()
	{
		return yearOfEst;
	}

	public void setYearOfEst(Date yearOfEst)
	{
		this.yearOfEst = yearOfEst;
	}

	public int getCenterAllIndia()
	{
		return centerAllIndia;
	}

	public void setCenterAllIndia(int centerAllIndia)
	{
		this.centerAllIndia = centerAllIndia;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}

	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getExamId()
	{
		return examId;
	}

	public void setExamId(int examId)
	{
		this.examId = examId;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
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
		return "TutorialsDTO [id=" + id + ", name=" + name + ", headOfficeAddress=" + headOfficeAddress + ", contactNumbers=" + contactNumbers + ", website="
				+ website + ", nrankInst=" + nrankInst + ", yearOfEst=" + yearOfEst + ", centerAllIndia=" + centerAllIndia + ", isActive=" + isActive
				+ ", studentId=" + studentId + ", examId=" + examId + ", cityId=" + cityId + ", locality=" + locality + "]";
	}

	public int getTutorialCityCentersId()
	{
		return tutorialCityCentersId;
	}

	public void setTutorialCityCentersId(int tutorialCityCentersId)
	{
		this.tutorialCityCentersId = tutorialCityCentersId;
	}

}
