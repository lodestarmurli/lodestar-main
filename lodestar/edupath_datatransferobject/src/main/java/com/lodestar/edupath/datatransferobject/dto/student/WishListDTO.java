package com.lodestar.edupath.datatransferobject.dto.student;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;

public class WishListDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					occupationId;
	private int					occIndustryId;
	private boolean				isSystemRecommended;

	// non table
	private OccupationDTO		occupation;

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

	public Integer getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(Integer occupationId)
	{
		this.occupationId = occupationId;
	}

	public boolean isSystemRecommended()
	{
		return isSystemRecommended;
	}

	public void setSystemRecommended(boolean isSystemRecommended)
	{
		this.isSystemRecommended = isSystemRecommended;
	}

	public OccupationDTO getOccupation()
	{
		return occupation;
	}

	public void setOccupation(OccupationDTO occupation)
	{
		this.occupation = occupation;
	}

	public int getOccIndustryId()
	{
		return occIndustryId;
	}

	public void setOccIndustryId(int occIndustryId)
	{
		this.occIndustryId = occIndustryId;
	}

}
