package com.lodestar.edupath.vark.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentAnswerDAO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.schoolreport.SchoolReportDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentAnswerDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentResultDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.vark.service.LSTResultService;

public class LSTResultAction extends BaseAction{
	private static final long			serialVersionUID	= 6090415257988873149L;
	private static Logger				OUT					= LoggerFactory.getLogger(LSTResultAction.class);
	private List<String> sessionYears =new ArrayList<String>();
	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
	private List<VarkStudentResultDTO> varkReport = new ArrayList<VarkStudentResultDTO>();
	LSTResultService service = new LSTResultService();
	private int schoolId;
	private String yearId;
	
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Inside LSTResultAction execute");
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject) 
		{
			return "SessionExpired";
		}
		try 
		{
			schoolList =  service.getAllSchools();
			sessionYears =service.getYears();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}
	
	
	public String searchData() throws Exception {
		OUT.info("LSTResultAction: inside searchData method");

		try {
			schoolId = Integer.parseInt(request.getParameter("schoolId"));
			yearId = request.getParameter("date");
			OUT.debug("Inside LSTResultAction searchData schoolId:{}, sessionDate:{}",schoolId,yearId);
			VarkStudentResultDTO vsDTO= new VarkStudentResultDTO();
			vsDTO.setSchoolId(schoolId);
			vsDTO.setYearId(yearId);
			varkReport = new VarkStudentAnswerDAO().getVarkStudentResultBySchoolID(vsDTO);
			schoolList =  service.getAllSchools();
			sessionYears =service.getYears();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

	}

	public List<String> getSessionYears() {
		return sessionYears;
	}

	public void setSessionYears(List<String> sessionYears) {
		this.sessionYears = sessionYears;
	}

	public StudentDetailsDTO getStudentDetailsDTO() {
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO) {
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public List<SchoolDTO> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<SchoolDTO> schoolList) {
		this.schoolList = schoolList;
	}


	public List<VarkStudentResultDTO> getVarkReport() {
		return varkReport;
	}


	public void setVarkReport(List<VarkStudentResultDTO> varkReport) {
		this.varkReport = varkReport;
	}


	public int getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}


	public String getYearId() {
		return yearId;
	}


	public void setYearId(String yearId) {
		this.yearId = yearId;
	}

	
	
	
}
