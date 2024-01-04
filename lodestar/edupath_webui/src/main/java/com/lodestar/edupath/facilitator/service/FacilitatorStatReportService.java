package com.lodestar.edupath.facilitator.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;

import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorStatsReportDTO;


public class FacilitatorStatReportService {
	
	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(FacilitatorStatReportService.class);
	
	
	public List<SchoolDTO> getAllSchools() throws Exception {
		return new SchoolDAO().getAllSchoolList();
	}
	
	
	public List<String> getPreviousYear() {
		 Calendar presentYear = Calendar.getInstance();
	        presentYear.add(Calendar.YEAR, 1);
	        List<String> listYears = new ArrayList<String>();
	        String date1 = "2015";
	        String date2 = Integer.toString(presentYear.get(Calendar.YEAR));

	        DateFormat formater = new SimpleDateFormat("yyyy");

	        Calendar beginCalendar = Calendar.getInstance();
	        Calendar finishCalendar = Calendar.getInstance();

	        try {
	            beginCalendar.setTime(formater.parse(date1));
	            finishCalendar.setTime(formater.parse(date2));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        while (beginCalendar.before(finishCalendar)) {
	            // add one month to date per loop
	            String date =     formater.format(beginCalendar.getTime());
	            listYears.add(date);
	            
	            beginCalendar.add(Calendar.YEAR, 1);
	        }
	        Collections.reverse(listYears);
	        OUT.debug("FacilitatorStatReportService getPreviousYear: previous years from today: {} ",listYears);
	        
	    return listYears;
	}


	public List<FacilitatorStatsReportDTO> getFacilitatorStatsBySchoolId(int schoolId, int sessionDate) {
		
		OUT.debug("FacilitatorStatReportService: getFacilitatorStatsBySchoolId- schoolId:{},sessionDate:{}",schoolId,sessionDate);
		List<FacilitatorStatsReportDTO> faciList = new ArrayList<FacilitatorStatsReportDTO>();
		FacilitatorStatsReportDTO facilitatorStatsReportDTO = new FacilitatorStatsReportDTO();
		facilitatorStatsReportDTO.setYear(sessionDate);
		facilitatorStatsReportDTO.setSchoolId(schoolId);
		faciList = new FacilitatorDetailsDAO().getFacilitatorStatsReport(facilitatorStatsReportDTO);
		OUT.debug("FacilitatorStatReportService: getFacilitatorStatsBySchoolId- faciList:{},",faciList);
		return faciList;
	}
	

}
