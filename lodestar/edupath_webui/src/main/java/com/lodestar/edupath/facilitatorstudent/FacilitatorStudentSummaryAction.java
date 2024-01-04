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
import com.lodestar.edupath.datatransferobject.dto.student.TumCgtResultDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.facilitatorstudent.service.FacilitatorStudentService;
import com.lodestar.edupath.util.ApplicationMenuUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FacilitatorStudentSummaryAction extends BaseAction implements ModelDriven<StudentDetailsDTO> {

	private static final long serialVersionUID = 1L;
	private static final Logger OUT = LoggerFactory.getLogger(FacilitatorStudentSummaryAction.class);
	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();
	private String sessionDate;
	private String studLoginId;
	private String show;
	private Boolean isReview;
	private Integer zohoVlaue = 0;

	// private Map<Integer, String> studentMap = new HashMap<Integer, String>();

	public String execute() {
		OUT.info("FacilitatorStudentSummaryAction: inside execute method");
		try {
			isReview = (Boolean) request.getSession().getAttribute("isReview");
			request.getSession().setAttribute("isReview", false);
			if (isReview != null && isReview) {
				return "reviewerSummary";
			}
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			setSelectedId(SessionTypeEnum.PRESESSION_FACI.getSessionName(), sessionMap);
			sessionDate = request.getParameter("date");
			int id = getUserSessionObject().getId();
			if (null != sessionDate && !sessionDate.isEmpty()) {
				Date date = TimeUtil.getDate(sessionDate, TimeUtil.FILTER_DATE_FORMAT);
				studentDetailsList = new FacilitatorStudentService().getStudentSessionDetailsByDate(id, date);
				
				zohoVlaue = 0;
			} else {

				StudentSessionObject studentSessionObject = getStudentSessionObject();
				studentSessionObject.setFullName(null);
				studentSessionObject.setId(0);
				studentSessionObject.setLoginId(null);
				studentSessionObject.setUserId(0);
				studentSessionObject.setCityId(0);
				studentSessionObject.setEmailId(null);
				studentSessionObject.setIsCanChangeCart(true);

				studentDetailsList = new FacilitatorStudentService().getStudentSessionDetailsById(id);
				zohoVlaue = 1;
			}
			OUT.debug("studentdetail list: {}", studentDetailsList.size());
			for (StudentDetailsDTO studentDTO : studentDetailsList) {
//				List<StudentTUMDTO> tumList = studentDTO.getStudentTUMList();
//				List<StudentCGTForEvalDTO> cgtList = studentDTO.getStudentCGTList();
//
//				if (tumList.size() < 3 && cgtList.size() < 10)
//				{
//					studentDTO.setStudentAlertMessage(getText("com.edupath.facilitator.student.summary.alert.tum.cgt.message"));
//				}
//				else if (tumList.size() < 3)
//				{
//					studentDTO.setStudentAlertMessage(getText("com.edupath.facilitator.student.summary.alert.tum.message"));
//				}
//				else if (cgtList.size() < 10)
//				{
//					studentDTO.setStudentAlertMessage(getText("com.edupath.facilitator.student.summary.alert.cgt.message"));
//				}
				// studentDetailsList.add(studentDTO);
				List<TumCgtResultDTO> tumcgtlist1 = studentDTO.getTumcgtList();
				for (TumCgtResultDTO tumcgtlist : tumcgtlist1) {

					if (tumcgtlist.getTUMResult() == 0
							&& (tumcgtlist.getInterestResult() == 0 || tumcgtlist.getAptitudeResult() == 0)) {
						studentDTO.setStudentAlertMessage(
								getText("com.edupath.facilitator.student.summary.alert.tum.cgt.message"));
					} else if (tumcgtlist.getTUMResult() == 0) {
						studentDTO.setStudentAlertMessage(
								getText("com.edupath.facilitator.student.summary.alert.tum.message"));
					} else if (tumcgtlist.getInterestResult() == 0 || tumcgtlist.getAptitudeResult() == 0) {
						studentDTO.setStudentAlertMessage(
								getText("com.edupath.facilitator.student.summary.alert.cgt.message"));
					}
				}
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return SUCCESS;
	}

	public String getDetails() {
		OUT.info("FacilitatorStudentSummaryAction: inside getDetails method");
		request.getSession().setAttribute("isReview", isReview);
		StudentSessionObject studentSessionObject = getStudentSessionObject();
		studentSessionObject.setFullName(studentDetailsDTO.getName());
		studentSessionObject.setId(studentDetailsDTO.getId());
		studentSessionObject.setLoginId(studentDetailsDTO.getLoginId());
		studentSessionObject.setUserId(studentDetailsDTO.getUserId());
		studentSessionObject.setCityId(studentDetailsDTO.getCityId());
		studentSessionObject.setEmailId(studentDetailsDTO.getFatheremailId());
		studentSessionObject.setIsCanChangeCart(true);
		SessionScheduleDetailsDTO scheduleDetailsDTO;
		OUT.info("FacilitatorStudentSummaryAction: studentDetailsDTO.getCityId()" + studentDetailsDTO.getCityId());

		try {
			scheduleDetailsDTO = new FacilitatorStudentService().getSessionSheduleDetailsByStudentId(studentDetailsDTO.getId());
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();

			if (null == sessionMap) {
				return "SessionExpired";
			}
			if (scheduleDetailsDTO != null) {
				String session = null;
				FacilitatorDetailsDTO facilitatorDetailsDTO = getfacilitatorDetails();
				FacilitatorDetailsDTO studentfacilitator = getfacilitatorDetailsByStudentId();
				boolean canChangeCart;
				boolean IsfacilitatorExist = false;
				if(studentfacilitator != null)
				{
					IsfacilitatorExist= true;
				}
				if ( IsfacilitatorExist && (studentfacilitator.getId()!=facilitatorDetailsDTO.getId() && facilitatorDetailsDTO.getIsReviewer()==false)) 
				{
					sessionMap.put(ApplicationConstants.SessionProperty.ACTIVE_HEADER.getProperty(), 30);
					setSelectedId(SessionTypeEnum.SESSION_SUMMARY_FACI.getSessionName(), sessionMap);
					session = "summary";
					canChangeCart = disableHeaderMenu(getUserSessionObject(), ActionContext.getContext().getSession(),
							studentDetailsDTO.getId());
					studentSessionObject.setIsCanChangeCart(canChangeCart);
				}

				else 
				{
					if (!scheduleDetailsDTO.isSession1FaciCompleted()) {
						setSelectedId(SessionTypeEnum.SESSION_1_FACI.getSessionName(), sessionMap);
						session = "session1";

						// Start Sasedeve Edited By Mrutyunjaya on Date 12-09-2017

						boolean issession1completefacitips = new TipsAndSuggestionService()
								.GetTipsSession1CompletedFaci(studentDetailsDTO.getId());
						sessionMap.put(ApplicationConstants.SessionProperty.Is_Session1CompleteFaci.getProperty(),
								issession1completefacitips);

						if (!issession1completefacitips) {
							session = "tipsandsuggestionsession1";
						}

						// END Sasedeve Edited By Mrutyunjaya on Date 12-09-2017

					} else if (!scheduleDetailsDTO.isSession2FaciCompleted()) {
						setSelectedId(SessionTypeEnum.SESSION_2_FACI.getSessionName(), sessionMap);
						session = "session2";

						// Start Sasedeve Edited By Mrutyunjaya on Date 12-09-2017

						boolean issession2completefacitips = new TipsAndSuggestionService()
								.GetTipsSession2CompletedFaci(studentDetailsDTO.getId());
						sessionMap.put(ApplicationConstants.SessionProperty.Is_Session2CompleteFaci.getProperty(),
								issession2completefacitips);

						if (!issession2completefacitips) {
							session = "tipsandsuggestionsession2";
						}

						// END Sasedeve Edited By Mrutyunjaya on Date 12-09-2017

					} else if (!scheduleDetailsDTO.isSession3FaciCompleted() 	) {
						setSelectedId(SessionTypeEnum.SESSION_3_FACI.getSessionName(), sessionMap);
						session = "session3";
					} else {
						setSelectedId(SessionTypeEnum.SESSION_SUMMARY_FACI.getSessionName(), sessionMap);
						session = "summary";
					}

					AuditTrailLogger.addAuditInfo(ModuleNameEnum.USER_DEFINITION,
							"Student :" + studentDetailsDTO.getName() + " Details viewed ",
							facilitatorDetailsDTO.getEmailId());
					studentSessionObject.setSessionOneCompleted(1);
					sessionMap.put(ApplicationConstants.SessionProperty.ACTIVE_HEADER.getProperty(),
							sessionMap.get(ApplicationConstants.SessionProperty.SELECTED_HEADER_ID.getProperty()));
					canChangeCart = ApplicationMenuUtils.disableHeaderMenu(getUserSessionObject(),
							ActionContext.getContext().getSession(), studentDetailsDTO.getId());

					studentSessionObject.setIsCanChangeCart(canChangeCart);
				}
				return session;
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		zohoVlaue = 0;
		return "";

	}

	public static boolean disableHeaderMenuService(UserSessionObject userSessionObj,
			Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap, int roleWeight, int studentId)
			throws Exception {
		boolean isCanChangeCart = true;
		SessionScheduleDetailsDTO scheduleDetailsDTO = null;
		OUT.info("Get Session information for Header with {}", userSessionObj.getFullName());
		scheduleDetailsDTO = new SessionScheduleDetailsDAO().getSessionDetailsByStudentId(studentId);
		String sessionCompleted = SessionTypeEnum.SESSION3.getSessionName();
		if (null != scheduleDetailsDTO) {
			if (!sessionCompleted.isEmpty() && !sessionCompleted.equals("")) {
				for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> appMenu : headerMenuMap.entrySet()) {
					// Presession always disable clickable
					if (appMenu.getKey().getRefName().equalsIgnoreCase(SessionTypeEnum.PRESESSION.getSessionName())) {
						appMenu.getKey().setEnableClick(false);
					}
					if (appMenu.getKey().getRefName().equalsIgnoreCase(sessionCompleted)) {
						break;
					}
					// enable only for user role and
					if (sessionCompleted.equalsIgnoreCase(SessionTypeEnum.SESSION3.getSessionName())) {
						appMenu.getKey().setEnableClick(false);
						isCanChangeCart = false;
					}
				}
			}
		}
		return isCanChangeCart;
	}

	public static boolean disableHeaderMenu(UserSessionObject userSessionObject, Map<String, Object> sessionMap,
			int studentId) throws Exception {
		AuthenticationService service = new AuthenticationService();
		Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> appMenu = service.getTabbedMenuItemsByRole(
				userSessionObject.getRoleId(), userSessionObject.getRoleWeight(), userSessionObject.getReviewer(),
				userSessionObject.isTrial(),userSessionObject);
		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap = appMenu.get(ApplicationConstants.HEADER_LIST);
		boolean canChangeCart = disableHeaderMenuService(userSessionObject, headerMenuMap,
				userSessionObject.getRoleWeight(), studentId);
		sessionMap.put(ApplicationConstants.SessionProperty.HEADER_SESSION_MENU_LIST.getProperty(), headerMenuMap);
		sessionMap.put(ApplicationConstants.SessionProperty.SIDEBAR_SESSION_MENU_LIST.getProperty(),
				appMenu.get(ApplicationConstants.SIDEBAR_LIST));
		return canChangeCart;
	}

	public String searchData() {
		OUT.debug("FacilitatorStudentSummaryAction : inside searchData method");
		try {
			studLoginId = request.getParameter("studLoginId");
			OUT.debug("getting student session details for loginId : {}", studLoginId);
			studentDetailsDTO = new FacilitatorStudentService().getStudentSessionDetailsByStudentLoginId(studLoginId);
			FacilitatorDetailsDTO facilitatorDetailsDTO = getfacilitatorDetails();
			if (studentDetailsDTO != null) {
				List<TumCgtResultDTO> tumcgtlist1 = studentDetailsDTO.getTumcgtList();
				for (TumCgtResultDTO tumcgtlist : tumcgtlist1) {
					if (tumcgtlist.getTUMResult() == 0
							&& (tumcgtlist.getInterestResult() == 0 || tumcgtlist.getAptitudeResult() == 0)) {
						studentDetailsDTO.setStudentAlertMessage(
								getText("com.edupath.facilitator.student.summary.alert.tum.cgt.message"));
					} else if (tumcgtlist.getTUMResult() == 0) {
						studentDetailsDTO.setStudentAlertMessage(
								getText("com.edupath.facilitator.student.summary.alert.tum.message"));
					} else if (tumcgtlist.getInterestResult() == 0 || tumcgtlist.getAptitudeResult() == 0) {
						studentDetailsDTO.setStudentAlertMessage(
								getText("com.edupath.facilitator.student.summary.alert.cgt.message"));
					}
				}
				studentDetailsList.add(studentDetailsDTO);
				
				if (facilitatorDetailsDTO != null) {
					if (facilitatorDetailsDTO.getId() != studentDetailsDTO.getFacilitatorId()) {
						show = "yes";
					}
				}
				

			}
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.USER_DEFINITION, "Student :" + studLoginId + " searched ",
					facilitatorDetailsDTO.getEmailId());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		zohoVlaue = 0;
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
