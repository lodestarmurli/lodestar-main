package com.lodestar.edupath.datatransferobject.dto.tutorial;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TutorialCityCentersDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					tutorialId;
	private int					cityId;
	private String				locality;
	private String				address;
	private String				contactNumbers;
	private double				latitude;
	private double				longitude;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public int getTutorialId()
	{
		return tutorialId;
	}

	public void setTutorialId(int tutorialId)
	{
		this.tutorialId = tutorialId;
	}

}
