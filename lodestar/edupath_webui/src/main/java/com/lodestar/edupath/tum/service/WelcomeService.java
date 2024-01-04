package com.lodestar.edupath.tum.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;

public class WelcomeService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(WelcomeService.class);

	public StudentDetailsDTO getStudentSession(int userId)
	{
		SessionScheduleDetailsDTO sessDetailsDTO = null;
		StudentDetailsDTO student = null;
		try
		{
			StudentDetailsDAO studentDAO = new StudentDetailsDAO();
			student = studentDAO.getStudentDetailsByUserId(userId);
			SessionScheduleDetailsDAO dao = new SessionScheduleDetailsDAO();
			sessDetailsDTO = dao.getSessionWithFacilitator(student.getId());
		    if(null != sessDetailsDTO)
		    {
		    	if(null != sessDetailsDTO.getSession1Date())
		    	{
		    		student.setSession1DateStr(TimeUtil.convertTimeAsString(sessDetailsDTO.getSession1Date(), TimeUtil.EMAIL_FORMAT) + " at " + TimeUtil.getAMOrPM(sessDetailsDTO.getSession1Date(), TimeUtil.AM_PM));
		    	}
		    	if(null != sessDetailsDTO.getSession1Date())
		    	{
		    		student.setSession2DateStr(TimeUtil.convertTimeAsString(sessDetailsDTO.getSession2Date(), TimeUtil.EMAIL_FORMAT) + " at " + TimeUtil.getAMOrPM(sessDetailsDTO.getSession2Date(), TimeUtil.AM_PM));
		    	}
		    	if(null != sessDetailsDTO.getSession1Date() && sessDetailsDTO.getSession3Date()!=null)
		    	{
		    		student.setSession3DateStr(TimeUtil.convertTimeAsString(sessDetailsDTO.getSession3Date(), TimeUtil.EMAIL_FORMAT) + " at " + TimeUtil.getAMOrPM(sessDetailsDTO.getSession3Date(), TimeUtil.AM_PM));
		    	}
		    	student.setPhoneNumber(sessDetailsDTO.getPhoneNumber());
		    	student.setName(sessDetailsDTO.getStudentName());
		    	student.setFacilitatorName(sessDetailsDTO.getName());
		    	//Start sasedeve Edited by vyankatesh date on 1-2-2017
		    	student.setVenue(sessDetailsDTO.getVenue().replaceAll("\\r|\\n", " "));
		    	student.setDueAmount(sessDetailsDTO.getDueAmount());
		    	System.out.println("============"+sessDetailsDTO.getDueAmount());
		        //END sasedeve Edited by vyankatesh date on 1-2-2017
		    }
		
		}
		catch (Exception e)
		{
			OUT.debug("Exception", e);
		}
		return student;
	}
	
}
