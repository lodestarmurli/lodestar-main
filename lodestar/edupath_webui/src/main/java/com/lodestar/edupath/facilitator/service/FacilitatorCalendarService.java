package com.lodestar.edupath.facilitator.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.LanguagesDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.facilitator.action.CalendarVO;
import com.lodestar.edupath.facilitator.action.SessionscheduleVO;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.opensymphony.xwork2.ModelDriven;

public class FacilitatorCalendarService  {
	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorCalendarService.class);
	
	public List<FacilitatorDetailsDTO> getAllFacilitatorDetails()
	{
		try
		{
			OUT.info("FacilitatorCalendarService : getting facilitator details from dao");
			List<FacilitatorDetailsDTO> facilitatorDetailsList1 = new ArrayList<FacilitatorDetailsDTO>();
			FacilitatorDetailsDTO facilitatorDetailsDTO1;
			List<FacilitatorDetailsDTO> facilitatorDetailsList = new FacilitatorDetailsDAO().getAllFacilitatorsDetailsList();
			for (FacilitatorDetailsDTO facilitatorDetailsDTO : facilitatorDetailsList)
			{
				facilitatorDetailsDTO1 = new FacilitatorDetailsDTO();
				facilitatorDetailsDTO1.setId(facilitatorDetailsDTO.getId());
				facilitatorDetailsDTO1.setName(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getName()));
				facilitatorDetailsDTO1.setEmailId(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getEmailId()));
				facilitatorDetailsDTO1.setPhoneNumber(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getPhoneNumber()));
				if (facilitatorDetailsDTO.getType().equalsIgnoreCase("P"))
				{
					facilitatorDetailsDTO1.setType("Part Time");
				}
				else
				{
					facilitatorDetailsDTO1.setType("Full Time");
				}
				facilitatorDetailsDTO1.setAreaAddr(CommonUtil.replaceXMLEntities(facilitatorDetailsDTO.getAreaAddr()));
				facilitatorDetailsDTO1.setIsActive(facilitatorDetailsDTO.getIsActive());
				facilitatorDetailsDTO1.setIsReviewer(facilitatorDetailsDTO.getIsReviewer());
				facilitatorDetailsDTO1.setUserId(facilitatorDetailsDTO.getUserId());
				facilitatorDetailsList1.add(facilitatorDetailsDTO1);
			}
			return facilitatorDetailsList1;
		}
		catch (Throwable e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public FacilitatorDetailsDTO getFacilitatorDetailsById(int id)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = null;
		try
		{
			facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsById(id);
			if (facilitatorDetailsDTO.getDob() != null)
			{
				String dob = TimeUtil.convertTimeAsString(facilitatorDetailsDTO.getDob().getTime(), TimeUtil.FILTER_DATE_FORMAT);
				facilitatorDetailsDTO.setDobStr(dob);
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorDetailsDTO;
	}
	
	
	
	public List<CalendarVO> getFacilitatorSchedule(int facilitatorId)
	{
		List<CalendarVO> finalList = new ArrayList<CalendarVO>();
		OUT.info("Inside FacilitatorCalendarservice getFacilitatorSchedule");
		try {
			List<SessionScheduleDetailsDTO> scheduleDetailsDTOList = new SessionScheduleDetailsDAO().getFacilitatorScheduleById(facilitatorId);
			
		for(SessionScheduleDetailsDTO scheduledetailsList : scheduleDetailsDTOList)
		{
			
			
			if( scheduledetailsList.getSession1Date() != null)
			{
				CalendarVO session1vo = new CalendarVO();
				Date session1date = (Date) scheduledetailsList.getSession1Date(); 
				String startDate = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss").format(session1date);
				session1vo.setStart(startDate.replace(",","T"));
				
				String title = scheduledetailsList.getLoginId()+" Name: "+ scheduledetailsList.getStudentName() +" Session 1 ";
				session1vo.setTitle(title);
				
				finalList.add(session1vo);
			}
			
			if( scheduledetailsList.getSession2Date() != null)
			{
				CalendarVO session2vo = new CalendarVO();
				Date session2date = (Date) scheduledetailsList.getSession2Date(); 
				String startDate = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss").format(session2date);
				session2vo.setStart(startDate.replace(",","T"));
				
				String title = scheduledetailsList.getLoginId()+" Name: "+ scheduledetailsList.getStudentName() +" Session 2 ";
				session2vo.setTitle(title);
				
				finalList.add(session2vo);
			}
			
			if( scheduledetailsList.getSession3Date() != null)
			{
				CalendarVO session3vo = new CalendarVO();
				Date session3date = (Date) scheduledetailsList.getSession3Date(); 
				String startDate = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss").format(session3date);
				session3vo.setStart(startDate.replace(",","T"));
				
				String title = scheduledetailsList.getLoginId()+" Name: "+ scheduledetailsList.getStudentName() +" Session 3 ";
				session3vo.setTitle(title);
				
				finalList.add(session3vo);
			}
		}
			
			
			
			
			
			
		}
		catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		return finalList;
	}
	
	
	
	
	


}
