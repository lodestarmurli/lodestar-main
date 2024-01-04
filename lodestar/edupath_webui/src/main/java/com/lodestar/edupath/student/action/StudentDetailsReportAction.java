package com.lodestar.edupath.student.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorStatsReportDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.student.service.StudentDetailsReportService;
import com.opensymphony.xwork2.ModelDriven;


public class StudentDetailsReportAction extends BaseAction implements ModelDriven<SessionScheduleDetailsDTO> {
	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(StudentDetailsReportAction.class);
	
	private StudentDetailsReportService service = new StudentDetailsReportService();
	private int schoolId;
	private int sessionDate;
	private List<String> listYears = new ArrayList<String>();
	private List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
	private List<SessionScheduleDetailsDTO> studentStatsReport = new ArrayList<SessionScheduleDetailsDTO>();

	
	
	public String execute() {
		OUT.debug("StudentDetailsReportAction: Inside execute method");
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject) {
			return "SessionExpired";
		}
		try {
			schoolList = service.getAllSchools();
			listYears = service.getPreviousYear();
			OUT.debug("StudentDetailsReportAction: schoolList:{}", schoolList);
			OUT.debug("StudentDetailsReportAction: listYears:{}", listYears);

		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		return SUCCESS;
		}

	public String searchStats() throws Exception {
		OUT.info("CollegeReportSummaryAction: inside getDetails method");

		try {
			schoolId = Integer.parseInt(request.getParameter("schoolId"));
			sessionDate = Integer.parseInt( request.getParameter("date"));
			studentStatsReport = service.getStudentStatsBySchoolId(schoolId, sessionDate);
			schoolList = service.getAllSchools();
			listYears = service.getPreviousYear();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

	}
	
	
	

	public StudentDetailsReportService getService() {
		return service;
	}

	public void setService(StudentDetailsReportService service) {
		this.service = service;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(int sessionDate) {
		this.sessionDate = sessionDate;
	}

	public List<String> getListYears() {
		return listYears;
	}

	public void setListYears(List<String> listYears) {
		this.listYears = listYears;
	}

	public List<SchoolDTO> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<SchoolDTO> schoolList) {
		this.schoolList = schoolList;
	}

	public List<SessionScheduleDetailsDTO> getStudentStatsReport() {
		return studentStatsReport;
	}

	public void setStudentStatsReport(List<SessionScheduleDetailsDTO> studentStatsReport) {
		this.studentStatsReport = studentStatsReport;
	}

	@Override
	public SessionScheduleDetailsDTO getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
