package com.lodestar.edupath.facilitatorstudent.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendation;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;

public class FacilitatorStudentReportGenteratedService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorStudentService.class);

	public  List<StudentDetailsDTO> getStudentDetailsByReportGenerated()
	{
		OUT.debug("FacilitatorStudentReportGenteratedService: inside getStudentDetailsByReportGenerated ");
		List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();
		try
		{
			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			
			List<StudentDetailsDTO> studentDetailsList1 = new StudentDetailsDAO().getStudentDetailsByReportGenerated(studentDetailsDTO);
			for (StudentDetailsDTO studentDetailsDTO2 : studentDetailsList1)
			{
				if (studentDetailsDTO2.getSession1FaciCompletedDate() != null)
				{
					String session1FaciCompletedDate = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession1FaciCompletedDate().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession1FaciCompletedDateStr(session1FaciCompletedDate);
				}
				if (studentDetailsDTO2.getSession2FaciCompletedDate() != null)
				{
					String session2FaciCompletedDate = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession2FaciCompletedDate().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession2FaciCompletedDateStr(session2FaciCompletedDate);
				}
				if (studentDetailsDTO2.getSession3FaciCompletedDate() != null)
				{
					String session3FaciCompletedDate = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession3FaciCompletedDate().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession3FaciCompletedDateStr(session3FaciCompletedDate);
				}
				
				if (studentDetailsDTO2.getReportGeneratedDate() != null)
				{
					String reportGeneratedDate = TimeUtil.convertTimeAsString(studentDetailsDTO2.getReportGeneratedDate().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setReportGeneratedDateStr(reportGeneratedDate);
				}
				studentDetailsList.add(studentDetailsDTO2);
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		return studentDetailsList;
	}
}
