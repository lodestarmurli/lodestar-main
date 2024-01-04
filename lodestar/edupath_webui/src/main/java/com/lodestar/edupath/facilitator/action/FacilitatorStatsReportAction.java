package com.lodestar.edupath.facilitator.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorStatsReportDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.schoolreport.SchoolReportDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.facilitator.service.FacilitatorStatReportService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FacilitatorStatsReportAction extends BaseAction implements ModelDriven<FacilitatorDetailsDTO> {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(FacilitatorStatsReportAction.class);
	private FacilitatorStatReportService service = new FacilitatorStatReportService();
	private List<String> listYears = new ArrayList<String>();
	private List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
	private List<FacilitatorStatsReportDTO> facilitatorStatsReport = new ArrayList<FacilitatorStatsReportDTO>();
	private int schoolId;
	private int sessionDate;

	public String execute() {
		OUT.debug("FacilitatorStatReportAction: Inside execute method");
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject) {
			return "SessionExpired";
		}
		try {
			schoolList = service.getAllSchools();
			listYears = service.getPreviousYear();
			OUT.debug("FacilitatorStatReportAction: schoolList:{}", schoolList);
			OUT.debug("FacilitatorStatReportAction: listYears:{}", listYears);

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
			facilitatorStatsReport = service.getFacilitatorStatsBySchoolId(schoolId, sessionDate);
			schoolList = service.getAllSchools();
			listYears = service.getPreviousYear();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

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

	
	public List<FacilitatorStatsReportDTO> getFacilitatorStatsReport() {
		return facilitatorStatsReport;
	}

	public void setFacilitatorStatsReport(List<FacilitatorStatsReportDTO> facilitatorStatsReport) {
		this.facilitatorStatsReport = facilitatorStatsReport;
	}

	@Override
	public FacilitatorDetailsDTO getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
