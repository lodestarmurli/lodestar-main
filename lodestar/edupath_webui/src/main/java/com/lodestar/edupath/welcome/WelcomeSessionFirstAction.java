package com.lodestar.edupath.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.tum.service.WelcomeService;

public class WelcomeSessionFirstAction extends BaseAction
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(WelcomeSessionFirstAction.class);
	private StudentDetailsDTO	studentDTO			= new StudentDetailsDTO();
	private WelcomeService		service;

	public String execute()
	{
		OUT.debug("Inside WelcomeSessionFirstAction");
		try
		{
			service = new WelcomeService();
			studentDTO = service.getStudentSession(getUserSessionObject().getId());
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String sessionTwo()
	{
		OUT.debug("Inside WelcomeSessionFirstAction");
		try
		{
			service = new WelcomeService();
			studentDTO = service.getStudentSession(getUserSessionObject().getId());
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public StudentDetailsDTO getStudentDTO()
	{
		return studentDTO;
	}
}
