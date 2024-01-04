package com.lodestar.edupath.collegeparameter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.collegeparameter.service.StudentCollegeParametersService;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;
import com.opensymphony.xwork2.ModelDriven;

public class StudentCollegeParametersAction extends BaseAction implements ModelDriven<StudentCollegeParametersDTO>
{
	private static final long				serialVersionUID		= 1L;
	private static final Logger				OUT						= LoggerFactory.getLogger(StudentCollegeParametersAction.class);

	private StudentCollegeParametersService	service;
	private StudentCollegeParametersDTO		studentParametersDTO	= new StudentCollegeParametersDTO();
	private List<CollegeParametersDTO>		collegeParamDetails;

	public String execute()
	{
		OUT.debug("Inside StudentParameterAction");
		try
		{
			setResponse();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String insertOrUpdate()
	{
		OUT.debug("Inside StudentParameterAction");
		try
		{
			service = new StudentCollegeParametersService();

			if (studentParametersDTO.getId() > 0)
			{
				service.updateStudentCollegeParametersDetails(studentParametersDTO, getUserSessionObject().getId());
			}
			else
			{
				service.insertStudentCollegeParametersDetails(studentParametersDTO, getUserSessionObject().getId());
			}
			setResponse();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	private void setResponse() throws Exception
	{
		service = new StudentCollegeParametersService();
		//======Start Sasedeve edited By Mrutyunjaya On Date 17-04-2017
		collegeParamDetails = service.getCollegeParameters(getUserSessionObject().getId());
		//======End Sasedeve edited By Mrutyunjaya On Date 17-04-2017
		studentParametersDTO = service.getStudentCollegeParametersDetailsByStudentId(getUserSessionObject().getId());
	}

	@Override
	public StudentCollegeParametersDTO getModel()
	{
		return studentParametersDTO;
	}

	public StudentCollegeParametersDTO getStudentParametersDTO()
	{
		return studentParametersDTO;
	}

	public void setStudentParametersDTO(StudentCollegeParametersDTO studentParametersDTO)
	{
		this.studentParametersDTO = studentParametersDTO;
	}

	public List<CollegeParametersDTO> getCollegeParamDetails()
	{
		return collegeParamDetails;
	}

	public void setCollegeParamDetails(List<CollegeParametersDTO> collegeParamDetails)
	{
		this.collegeParamDetails = collegeParamDetails;
	}

}
