package com.lodestar.edupath.collegereport;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.schoolreport.SchoolReportDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.opensymphony.xwork2.ModelDriven;

public class CollegeReportSummaryAction extends BaseAction implements ModelDriven<StudentDetailsDTO> {

	private static final long serialVersionUID = 4107476161853263600L;
	private static Logger OUT = LoggerFactory.getLogger(CollegeReportSummaryAction.class);

	private List<StudentDetailsDTO> studentList = new ArrayList<StudentDetailsDTO>();

	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
	private List<SchoolReportDTO> schoolReport = new ArrayList<SchoolReportDTO>();
	private String sessionDate;
	private List<String> sessionYears =new ArrayList<String>();
	private CollegeReportSummaryService service;
	private int schoolId;
	private String studLoginId;

	public CollegeReportSummaryAction() {
		service = new CollegeReportSummaryService();
	}

	@Override
	public String execute() throws Exception {
		OUT.debug("Inside CollegeReportSummaryAction execute");
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject) {
			return "SessionExpired";
		}
		try {
			schoolList =  service.getAllSchools();
			sessionYears =service.getYears();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return SUCCESS;
	}

	public String searchData() throws Exception {
		OUT.info("CollegeReportSummaryAction: inside getDetails method");

		try {
			schoolId = Integer.parseInt(request.getParameter("schoolId"));
			sessionDate = request.getParameter("date");
			schoolReport = service.getReportBySchoolId(schoolId, sessionDate);
			schoolList =  service.getAllSchools();
			sessionYears =service.getYears();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

	}

	public String excelDownload() throws Exception {
		OUT.info("CollegeReportSummaryAction: inside excelDownload method");

		try {
			schoolId = Integer.parseInt(request.getParameter("schoolId"));
			sessionDate = request.getParameter("date");
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}
	
	
	
	public List<SchoolDTO> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<SchoolDTO> schoolList) {
		this.schoolList = schoolList;
	}

	public List<StudentDetailsDTO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentDetailsDTO> studentList) {
		this.studentList = studentList;
	}

	public StudentDetailsDTO getStudentDetailsDTO() {
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO) {
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public List<SchoolReportDTO> getSchoolReport() {
		return schoolReport;
	}

	public void setSchoolReport(List<SchoolReportDTO> schoolReport) {
		this.schoolReport = schoolReport;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public List<String> getSessionYears() {
		return sessionYears;
	}

	public void setSessionYears(List<String> sessionYears) {
		this.sessionYears = sessionYears;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getStudLoginId() {
		return studLoginId;
	}

	public void setStudLoginId(String studLoginId) {
		this.studLoginId = studLoginId;
	}

	@Override
	public StudentDetailsDTO getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
