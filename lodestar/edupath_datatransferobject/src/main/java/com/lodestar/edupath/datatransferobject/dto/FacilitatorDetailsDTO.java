package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FacilitatorDetailsDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				name;
	private String				type;
	private Date				dob;
	private String				panNumber;
	private int					highestQualificationId;
	private String				emailId;
	private String				altEmailId;
	private String				phoneNumber;
	private String				altPhoneNumber;
	private String				streetAddr;
	private String				areaAddr;
	private int					cityId;
	private Boolean				isReviewer;
	private int					userId;
	private Boolean				isActive;
	private String				additionalLanguages;

	// non-table fields
	private String				dobStr;
	private String				gender;

	// summary
	private String				residantArea;
	
	//vyankatesh Change 
	private List<Integer>				faceToFaceCityId =new ArrayList<Integer>();
	private List<Integer>					onCallCityId = new ArrayList<Integer>();
	
	//vyankatesh Change 
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public String getPanNumber()
	{
		return panNumber;
	}

	public void setPanNumber(String panNumber)
	{
		this.panNumber = panNumber;
	}

	public int getHighestQualificationId()
	{
		return highestQualificationId;
	}

	public void setHighestQualificationId(int highestQualificationId)
	{
		this.highestQualificationId = highestQualificationId;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getAltEmailId()
	{
		return altEmailId;
	}

	public void setAltEmailId(String altEmailId)
	{
		this.altEmailId = altEmailId;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getAltPhoneNumber()
	{
		return altPhoneNumber;
	}

	public void setAltPhoneNumber(String altPhoneNumber)
	{
		this.altPhoneNumber = altPhoneNumber;
	}

	public String getStreetAddr()
	{
		return streetAddr;
	}

	public void setStreetAddr(String streetAddr)
	{
		this.streetAddr = streetAddr;
	}

	public String getAreaAddr()
	{
		return areaAddr;
	}

	public void setAreaAddr(String areaAddr)
	{
		this.areaAddr = areaAddr;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public Boolean getIsReviewer()
	{
		return isReviewer;
	}

	public void setIsReviewer(Boolean isReviewer)
	{
		this.isReviewer = isReviewer;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}

	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getDobStr()
	{
		return dobStr;
	}

	public void setDobStr(String dobStr)
	{
		this.dobStr = dobStr;
	}

	public String getAdditionalLanguages()
	{
		return additionalLanguages;
	}

	public void setAdditionalLanguages(String additionalLanguages)
	{
		this.additionalLanguages = additionalLanguages;
	}

	

	@Override
	public String toString() {
		return "FacilitatorDetailsDTO [id=" + id + ", name=" + name + ", type=" + type + ", dob=" + dob + ", panNumber="
				+ panNumber + ", highestQualificationId=" + highestQualificationId + ", emailId=" + emailId
				+ ", altEmailId=" + altEmailId + ", phoneNumber=" + phoneNumber + ", altPhoneNumber=" + altPhoneNumber
				+ ", streetAddr=" + streetAddr + ", areaAddr=" + areaAddr + ", cityId=" + cityId + ", isReviewer="
				+ isReviewer + ", userId=" + userId + ", isActive=" + isActive + ", additionalLanguages="
				+ additionalLanguages + ", dobStr=" + dobStr + ", gender=" + gender + ", residantArea=" + residantArea
				+ ", faceToFaceCityId=" + faceToFaceCityId + ", onCallCityId=" + onCallCityId + "]";
	}

	public String getResidantArea()
	{
		StringBuilder residant = new StringBuilder();
		if (null != streetAddr && !streetAddr.isEmpty())
		{
			residant.append(streetAddr);
		}
		if (null != areaAddr && !areaAddr.isEmpty())
		{
			if (!residant.toString().isEmpty())
			{
				residant.append(",");
			}
			residant.append(areaAddr);
		}
		residantArea = residant.toString();
		return residantArea;
	}

	public void setResidantArea(String residantArea)
	{
		this.residantArea = residantArea;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	


	public List<Integer> getFaceToFaceCityId() {
		return faceToFaceCityId;
	}

	public void setFaceToFaceCityId(List<Integer> faceToFaceCityId) {
		this.faceToFaceCityId = faceToFaceCityId;
	}

	public List<Integer> getOnCallCityId() {
		return onCallCityId;
	}

	public void setOnCallCityId(List<Integer> onCallCityId) {
		this.onCallCityId = onCallCityId;
	}
	 /*Comparator for sorting the list by Student Name*/
    public static Comparator<FacilitatorDetailsDTO> FacilitatorDetailsDTOComparator = new Comparator<FacilitatorDetailsDTO>() {

	public int compare(FacilitatorDetailsDTO s1, FacilitatorDetailsDTO s2) {
	   String  Name1 = s1.getName().toUpperCase();
	   String  Name2 = s2.getName().toUpperCase();

	   //ascending order
	   return Name1.compareTo(Name2);

	   //descending order
	   //return StudentName2.compareTo(StudentName1);
    }};

}
