package com.lodestar.edupath.student.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.bean.StudentVO; 

public class StudentDetailsReportService {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(StudentDetailsReportService.class);

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
	        OUT.debug("StudentDetailsReportService getPreviousYear: previous years from today: {} ",listYears);
	        
	    return listYears;
	}
	
	
public List<SessionScheduleDetailsDTO> getStudentStatsBySchoolId(int schoolId, int sessionDate) {
		
		OUT.debug("StudentDetailsReportService: getStudentStatsBySchoolId- schoolId:{},sessionDate:{}",schoolId,sessionDate);
		List<SessionScheduleDetailsDTO> studentList = new ArrayList<SessionScheduleDetailsDTO>();
		List<SessionScheduleDetailsDTO> finalStudentList = new ArrayList<SessionScheduleDetailsDTO>();
		SessionScheduleDetailsDTO studentStatsReportDTO = new SessionScheduleDetailsDTO();
		studentStatsReportDTO.setYear(sessionDate);
		studentStatsReportDTO.setSchoolId(schoolId);
		try {
			studentList = new SessionScheduleDetailsDAO().getStudentStatsReportBySchool(studentStatsReportDTO);
			OUT.debug("StudentDetailsReportService: getStudentStatsBySchoolId- StudentList:{},", studentList);
			if (null != studentList && !studentList.isEmpty()) 
			{
				for (SessionScheduleDetailsDTO studentDetailsDTO : studentList) 
				{
					if (null != studentDetailsDTO.getLoginId()) 
					{
						if (null != studentDetailsDTO.getSession1Date()) {

							studentDetailsDTO.setSession1DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSession1Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession1DateStr("-");
						}
						if (null != studentDetailsDTO.getSession2Date()) {

							studentDetailsDTO.setSession2DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSession2Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession2DateStr("-");
						}
						if (null != studentDetailsDTO.getSession3Date()) {

							studentDetailsDTO.setSession3DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSession3Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession3DateStr("-");
						}
						
						
						if (null != studentDetailsDTO.getSession1FaciCompletedDate()) {
							
							studentDetailsDTO.setSession1FaciCompletedDateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSession1FaciCompletedDate().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession1FaciCompletedDateStr("-");
						}
						
						if (null != studentDetailsDTO.getSession2FaciCompletedDate()) {
							
							studentDetailsDTO.setSession2FaciCompletedDateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSession2FaciCompletedDate().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession2FaciCompletedDateStr("-");
						}
						

						if (null != studentDetailsDTO.getSession3FaciCompletedDate()) {
							
							studentDetailsDTO.setSession3FaciCompletedDateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSession3FaciCompletedDate().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession3FaciCompletedDateStr("-");
						}
						
						
						if (null != studentDetailsDTO.getSentForReviewDate()) {
							
							studentDetailsDTO.setSentForReviewDateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSentForReviewDate().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSentForReviewDateStr("-");
						}
						
						if (null != studentDetailsDTO.getReportGeneratedDate()) {
							
							studentDetailsDTO.setReportGeneratedDateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getReportGeneratedDate().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setReportGeneratedDateStr("-");
						}
						OUT.debug("StudentDetailsReportService: getStudentStatsBySchoolId- studentDetailsDTO:{},", studentDetailsDTO);
						finalStudentList.add(studentDetailsDTO);
					}
				}
			}	
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalStudentList;
	}
	
	
	
	
}
