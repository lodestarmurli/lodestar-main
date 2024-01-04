package com.lodestar.edupath.facilitatorstudent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.TipsAndSuggestionService.TipsAndSuggestionService;
import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.AuthenticationService;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTForEvalDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.facilitatorstudent.service.FacilitatorStudentReportGenteratedService;
import com.lodestar.edupath.facilitatorstudent.service.FacilitatorStudentService;
import com.lodestar.edupath.util.ApplicationMenuUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FacilitatorStudentReportGenteratedAction extends BaseAction implements ModelDriven<StudentDetailsDTO> {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(FacilitatorStudentReportGenteratedAction.class);
	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();
	private String sessionDate;
	private String studLoginId;
	private String show;
	private Boolean isReview;
	private Integer zohoVlaue = 0;
	private FacilitatorStudentReportGenteratedService service = new FacilitatorStudentReportGenteratedService();

	// private Map<Integer, String> studentMap = new HashMap<Integer, String>();

	public String execute() {
		OUT.info("FacilitatorStudentReportGenteratedAction: inside execute method");
		try {
			
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SUB_MENU_REF_NAME, ApplicationConstants.APP_MENU_COMPLETED_REPORT_REFNAME);
			setSelectedId(SessionTypeEnum.PRESESSION_FACI.getSessionName(), sessionMap);
			sessionMap.remove(ApplicationConstants.SUB_MENU_REF_NAME);
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			studentSessionObject.setFullName(null);
			studentSessionObject.setId(0);
			studentSessionObject.setLoginId(null);
			studentSessionObject.setUserId(0);
			studentSessionObject.setCityId(0);
			studentSessionObject.setEmailId(null);
			studentSessionObject.setIsCanChangeCart(true);
			studentDetailsList = service.getStudentDetailsByReportGenerated();
			
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return SUCCESS;
	}

	
	
	
	private FacilitatorDetailsDTO getfacilitatorDetails() {
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(getUserSessionObject().getId());
		return new FacilitatorStudentService().getFacilitatorDetailsByUserId(userDetailDTO);
	}

	private FacilitatorDetailsDTO getfacilitatorDetailsByStudentId() {
		StudentSessionObject studentSessionObject = getStudentSessionObject();
		int id = studentSessionObject.getId();
		return new FacilitatorStudentService().getfacilitatorDetailsByStudentId(id);
	}

	public StudentDetailsDTO getStudentDetailsDTO() {
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO) {
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public List<StudentDetailsDTO> getStudentDetailsList() {
		return studentDetailsList;
	}

	public void setStudentDetailsList(List<StudentDetailsDTO> studentDetailsList) {
		this.studentDetailsList = studentDetailsList;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	@Override
	public StudentDetailsDTO getModel() {
		return studentDetailsDTO;
	}

	public String getStudLoginId() {
		return studLoginId;
	}

	public void setStudLoginId(String studLoginId) {
		this.studLoginId = studLoginId;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public Boolean getIsReview() {
		return isReview;
	}

	public void setIsReview(Boolean isReview) {
		this.isReview = isReview;
	}

	public Integer getZohoVlaue() {
		return zohoVlaue;
	}

	public void setZohoVlaue(Integer zohoVlaue) {
		this.zohoVlaue = zohoVlaue;
	}
}
