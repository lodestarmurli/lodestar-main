package com.lodestar.edupath.collegeparameter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeparameter.CollegeParametersDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeparameter.StudentCollegeParametersDAO;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;

public class StudentCollegeParametersService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentCollegeParametersService.class);

	public StudentCollegeParametersDTO getStudentCollegeParametersDetailsByStudentId(int loginId)
	{
		OUT.debug("Inside StudentCollegeParameters Service");
		StudentCollegeParametersDTO dto = null;
		try
		{
			dto = new StudentCollegeParametersDAO().getStudentCollegeParametersByStudentId(getStudentId(loginId));
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return dto;
	}

	public void insertStudentCollegeParametersDetails(StudentCollegeParametersDTO dto, int loginId)
	{
		OUT.debug("Inside StudentCollegeParameters Service");
		try
		{
			dto.setStudentId(getStudentId(loginId));
			new StudentCollegeParametersDAO().insertStudentCollegeParameters(dto);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public void updateStudentCollegeParametersDetails(StudentCollegeParametersDTO dto, int loginId)
	{
		OUT.debug("Inside StudentParameterService");
		try
		{
			dto.setStudentId(getStudentId(loginId));
			new StudentCollegeParametersDAO().updateStudentCollegeParameters(dto);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	private int getStudentId(int loginId)
	{
		try
		{
			StudentDetailsDAO dao = new StudentDetailsDAO();
			StudentDetailsDTO student = dao.getStudentDetailsByUserId(loginId);

			return student.getId();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return 0;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	//======Start Sasedeve edited By Mrutyunjaya On Date 17-04-2017
	public List<CollegeParametersDTO> getCollegeParameters(int studentId) throws Exception
	{
		return new CollegeParametersDAO().getAllParameters(studentId, false);
	}
	//======End Sasedeve edited By Mrutyunjaya On Date 17-04-2017
}
