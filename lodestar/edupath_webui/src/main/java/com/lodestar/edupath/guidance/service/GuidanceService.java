package com.lodestar.edupath.guidance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;

public class GuidanceService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(GuidanceService.class);

	public FacilitatorDetailsDTO getGuidanceDetails(int userId)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = null;
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(userId);
			if (student != null)
			{
				FacilitatorDetailsDAO ftdao = new FacilitatorDetailsDAO();
				facilitatorDetailsDTO = ftdao.getFacilitatorByStudentId(student.getId());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return facilitatorDetailsDTO;
	}
}
