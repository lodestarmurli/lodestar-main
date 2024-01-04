package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StudentPlacementInInstitutesDTO implements Serializable, IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					collegeId;
	private String				instituteName;
	private int					countOfStudents;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public String getInstituteName()
	{
		return instituteName;
	}

	public void setInstituteName(String instituteName)
	{
		this.instituteName = instituteName;
	}

	public int getCountOfStudents()
	{
		return countOfStudents;
	}

	public void setCountOfStudents(int countOfStudents)
	{
		this.countOfStudents = countOfStudents;
	}

	@Override
	public String toString()
	{
		return "StudentPlacementInInstitutesDTO [id=" + id + ", collegeId=" + collegeId + ", instituteName=" + instituteName + ", countOfStudents="
				+ countOfStudents + "]";
	}

}
