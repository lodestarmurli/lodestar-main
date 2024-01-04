package com.lodestar.edupath.datatransferobject.dto.summaryreport;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;

public class OccupationIndustryVO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					studentId;
	private List<OccupationDTO>	occupations;
	private List<IndustryDTO>	industries;

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public List<OccupationDTO> getOccupations()
	{
		return occupations;
	}

	public void setOccupations(List<OccupationDTO> occupations)
	{
		this.occupations = occupations;
	}

	public List<IndustryDTO> getIndustries()
	{
		return industries;
	}

	public void setIndustries(List<IndustryDTO> industries)
	{
		this.industries = industries;
	}
}
