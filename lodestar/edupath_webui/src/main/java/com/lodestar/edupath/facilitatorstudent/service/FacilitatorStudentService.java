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

public class FacilitatorStudentService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorStudentService.class);

	public List<StudentDetailsDTO> getStudentSessionDetailsById(int id)
	{
		List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();
		try
		{
			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			studentDetailsDTO.setId(id);
			studentDetailsDTO.setSection(SystemRecommendation.INTREST_TEST_SECTION);
			// studentDetailsDTO.setDefaultDays(ApplicationConstants.DAYS);
			List<StudentDetailsDTO> studentDetailsList1 = new StudentDetailsDAO().getAllStudentSessionDetailsByFacilitatorId(studentDetailsDTO);
			for (StudentDetailsDTO studentDetailsDTO2 : studentDetailsList1)
			{
				if (studentDetailsDTO2.getSession1Date() != null)
				{
					String sessionDate1 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession1Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession1DateStr(sessionDate1);
				}
				if (studentDetailsDTO2.getSession2Date() != null)
				{
					String sessionDate2 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession2Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession2DateStr(sessionDate2);
				}
				if (studentDetailsDTO2.getSession3Date() != null)
				{
					String sessionDate3 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession3Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession3DateStr(sessionDate3);
				}
				studentDetailsDTO2.setVenue(studentDetailsDTO2.getVenue().replaceAll("\\r|\\n", " "));
				studentDetailsList.add(studentDetailsDTO2);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		// Remove data which Session Date is null
		ListIterator<StudentDetailsDTO> listItr = studentDetailsList.listIterator();
		while (listItr.hasNext())
		{
			StudentDetailsDTO dto = listItr.next();
			if (dto.getSession1Date() == null)
			{
				listItr.remove();
			}
		}
		Collections.sort(studentDetailsList, new SessionDateComparator());
		return studentDetailsList;
	}

	public List<StudentDetailsDTO> getStudentSessionDetailsByDate(int id, Date date)
	{
		List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();
		try
		{
			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			studentDetailsDTO.setId(id);
			studentDetailsDTO.setSelectedDate(date);
			studentDetailsDTO.setSection(SystemRecommendation.INTREST_TEST_SECTION);
			List<StudentDetailsDTO> studentDetailsList1 = new StudentDetailsDAO().getAllStudentSessionDetailsByDate(studentDetailsDTO);
			for (StudentDetailsDTO studentDetailsDTO2 : studentDetailsList1)
			{
				if (studentDetailsDTO2.getSession1Date() != null)
				{
					String sessionDate1 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession1Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession1DateStr(sessionDate1);
				}
				if (studentDetailsDTO2.getSession2Date() != null)
				{
					String sessionDate2 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession2Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession2DateStr(sessionDate2);
				}
				if (studentDetailsDTO2.getSession3Date() != null)
				{
					String sessionDate3 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession3Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession3DateStr(sessionDate3);
				}
				studentDetailsDTO2.setVenue(studentDetailsDTO2.getVenue().replaceAll("\\r|\\n", " "));
				
				studentDetailsList.add(studentDetailsDTO2);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDetailsList;
	}

	public SessionScheduleDetailsDTO getSessionSheduleDetailsByStudentId(int id)
	{
		SessionScheduleDetailsDTO detailsDTO = null;
		try
		{
			detailsDTO = new SessionScheduleDetailsDAO().getSessionDetailsByStudentId(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return detailsDTO;
	}

	public StudentDetailsDTO getStudentSessionDetailsByStudentLoginId(String studentLoginId)
	{
		StudentDetailsDTO detailsDTO = new StudentDetailsDTO();
		detailsDTO.setLoginId(studentLoginId);
		detailsDTO.setSection(SystemRecommendation.INTREST_TEST_SECTION);
		try
		{
			detailsDTO = new StudentDetailsDAO().getStudentSessionDetailsByStudentLoginId(detailsDTO);
			if (detailsDTO != null)
			{
				if (detailsDTO.getSession1Date() != null)
				{
					String sessionDate1 = TimeUtil.convertTimeAsString(detailsDTO.getSession1Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					detailsDTO.setSession1DateStr(sessionDate1);
				}
				if (detailsDTO.getSession2Date() != null)
				{
					String sessionDate2 = TimeUtil.convertTimeAsString(detailsDTO.getSession2Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					detailsDTO.setSession2DateStr(sessionDate2);
				}
				if (detailsDTO.getSession3Date() != null)
				{
					String sessionDate3 = TimeUtil.convertTimeAsString(detailsDTO.getSession3Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					detailsDTO.setSession3DateStr(sessionDate3);
				}
				
				
				//Start SASEDEVE edited by Mrutyunjaya on Date 12-06-2017
				if (detailsDTO.getVenue() != null)
				{
				
					detailsDTO.setVenue(detailsDTO.getVenue().replaceAll("\\r|\\n", " "));
				}
				
				//END SASEDEVE edited by Mrutyunjaya on Date 12-06-2017
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return detailsDTO;

	}

	public FacilitatorDetailsDTO getFacilitatorDetailsByUserId(UserDetailDTO userDetailDTO)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = null;
		try
		{
			facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsByUserId(userDetailDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorDetailsDTO;
	}

	/*
	 * public List<StudentTUMDTO> getStudentTUMByStudentId(int id)
	 * {
	 * StudentTUMDTO tumdto = new StudentTUMDTO();
	 * tumdto.setStudentId(id);
	 * return new StudentTUMDAO().getSuduntTUMDetailsByStudentId(tumdto);
	 * }
	 */

	class SessionDateComparator implements Comparator<StudentDetailsDTO>
	{
		@Override
		public int compare(StudentDetailsDTO o1, StudentDetailsDTO o2)
		{
			int session3Day=-1;
			int session1Day = (int) ((o1.getSession1Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			int session2Day = (int) ((o1.getSession2Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			if(o1.getSession3Date() != null) {
				session3Day = (int) ((o1.getSession3Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			}
			if (session1Day < 0)
			{
				session1Day = Integer.MAX_VALUE;
			}
			if (session2Day < 0)
			{
				session2Day = Integer.MAX_VALUE;
			}
			if (session3Day < 0)
			{
				session3Day = Integer.MAX_VALUE;
			}
			
			Integer date1 = Math.min(session1Day, Math.min(session2Day, session3Day));
			session3Day=-1;
			session1Day = (int) ((o2.getSession1Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			session2Day = (int) ((o2.getSession2Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			if(o2.getSession3Date() != null) {
				session3Day = (int) ((o2.getSession3Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			}
			if (session1Day < 0)
			{
				session1Day = Integer.MAX_VALUE;
			}
			if (session2Day < 0)
			{
				session2Day = Integer.MAX_VALUE;
			}
			if (session3Day < 0)
			{
				session3Day = Integer.MAX_VALUE;
			}

			Integer date2 = Math.min(session1Day, Math.min(session2Day, session3Day));
			return date1.compareTo(date2);
		}
	}
	
	
	//start by bharath on 19/9/2019
			public FacilitatorDetailsDTO getfacilitatorDetailsByStudentId(int id)
			{
				FacilitatorDetailsDTO facilitatorDetailsDTO = null;
				try
				{
					facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorByStudentId(id);
				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
				return facilitatorDetailsDTO;
			}
			//end by bharath on 19/9/2019
}
