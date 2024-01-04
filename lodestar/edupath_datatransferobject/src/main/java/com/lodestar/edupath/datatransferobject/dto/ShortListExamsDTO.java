package com.lodestar.edupath.datatransferobject.dto;

import java.util.List;

public class ShortListExamsDTO implements IModel
{

	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					studentId;
	private int					entranceExamId;
	private int					occupationId;
	private int					occIndustryId;
	private int					cityId;

	private List<Integer>		occIdsList;

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

	public int getEntranceExamId()
	{
		return entranceExamId;
	}

	public void setEntranceExamId(int entranceExamId)
	{
		this.entranceExamId = entranceExamId;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getOccIndustryId()
	{
		return occIndustryId;
	}

	public void setOccIndustryId(int occIndustryId)
	{
		this.occIndustryId = occIndustryId;
	}

	@Override public String toString()
	{
		return "ShortListExamsDTO [id=" + id + ", studentId=" + studentId + ", entranceExamId=" + entranceExamId + ", occupationId=" + occupationId
				+ ", occIndustryId=" + occIndustryId + "]";
	}

	public List<Integer> getOccIdsList()
	{
		return occIdsList;
	}

	public void setOccIdsList(List<Integer> occIdsList)
	{
		this.occIdsList = occIdsList;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}



}
