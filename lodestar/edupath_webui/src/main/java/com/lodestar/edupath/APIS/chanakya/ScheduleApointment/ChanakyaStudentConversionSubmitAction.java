package com.lodestar.edupath.APIS.chanakya.ScheduleApointment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentExtraDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.opensymphony.xwork2.ModelDriven;

public class ChanakyaStudentConversionSubmitAction extends BaseAction implements ModelDriven<StudentDetailsDTO> 
{
	private static final long serialVersionUID = 4228877756086138530L;
	private static Logger OUT = LoggerFactory.getLogger(ChanakyaStudentConversionSubmitAction.class);
	private StudentDetailsDTO studentDTO = new StudentDetailsDTO();
	private Integer DHStudentId;
	private Integer studentId;
	private List<CityDTO> cityList = new ArrayList<CityDTO>();
	private List<ClassDTO> classList = new ArrayList<ClassDTO>();
	private List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
	private List<FacilitatorDetailsDTO> facilitatorList = new ArrayList<FacilitatorDetailsDTO>();
	private List<GenderTypeEnum> genderList = new ArrayList<GenderTypeEnum>();
	private String session1DateValue;
	private String session1Time;
	private String session2DateValue;
	private String session2Time;
	private String session3DateValue;
	private String session3Time;
	private ChanakyaStudentConversionSubmitService service;
	private List<String> timeList = new ArrayList<String>();
	private int sessionTimeGap;
	private Map<String, String> globalSettingMap;

	private JSONObject jsonObject = new JSONObject();
	private JSONObject venuejsonObject = new JSONObject();
	private JSONObject schooljsonObject = new JSONObject();
	private JSONObject facilitatorCityjsonObject = new JSONObject();

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getVenuejsonObject() {
		return venuejsonObject;
	}

	public void setVenuejsonObject(JSONObject venuejsonObject) {
		this.venuejsonObject = venuejsonObject;
	}

	public JSONObject getSchooljsonObject() {
		return schooljsonObject;
	}

	public void setSchooljsonObject(JSONObject schooljsonObject) {
		this.schooljsonObject = schooljsonObject;
	}

	public JSONObject getFacilitatorCityjsonObject() {
		return facilitatorCityjsonObject;
	}

	public void setFacilitatorCityjsonObject(JSONObject facilitatorCityjsonObject) {
		this.facilitatorCityjsonObject = facilitatorCityjsonObject;
	}

	public ChanakyaStudentConversionSubmitAction() {
		service = new ChanakyaStudentConversionSubmitService();
	}

	public String insert() {
		OUT.debug("insert new student Record with name:{}", studentDTO.getName());
		OUT.debug("update DH student Record with DHStudentId:{}", studentId);
		try {
			
			UserSessionObject userSessionObject = getUserSessionObject();
			if (null == userSessionObject) {
				return "SessionExpired";
			}
			fieldRequired();
			modifiedRequiredField();
			request.setAttribute("actionType", "add");
			boolean check = checkDTO();
			if (!check) {
				addActionError("invalid Session Details");
				return ERROR;
			}
			if (null == studentDTO) {
				return ERROR;
			}
			if (studentDTO.getStudentType() == StudentTypeEnum.FULL) {
				String messageDependency = checkFacilitatorDependency(studentDTO);

				if (null == messageDependency || !messageDependency.isEmpty()) {
					if (null == messageDependency) {
						addActionError("can not be added student, due to Exception.");
					} else {
						addActionError(messageDependency);
					}
					if(studentDTO.getFatherEmailId() == null )
					{
						studentDTO.setFatherEmailId(studentDTO.getFatheremailId());
					}
					if(studentDTO.getFathercontactno() ==null )
					{
						studentDTO.setFathercontactno(studentDTO.getContactNumber());
					}
					if(studentDTO.getFatherName() == null )
					{
						studentDTO.setFatherName(studentDTO.getFathername());
					}
					
					
					return ERROR;
				}
			}
			studentDTO.setSource("DH_FULL");
			studentDTO.setSession3Date(null);
			studentDTO.setSession3DateStr(null);
			EActionStatus status = service.insertStudent(studentDTO,studentId);
			if (status == EActionStatus.FAILURE) {
				addActionError("can't add due to Exception");
				return ERROR;
			}
			StringBuilder auditMessage = new StringBuilder();
			auditMessage.append("Student ").append(studentDTO.getName())
					.append(" details has been created successfully and created On : " + service.getAuditDateStr());
			insertAudit(auditMessage.toString(), userSessionObject);
			addActionMessage(auditMessage.toString());
			updateDHStudentConverted();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;

	}

	private void modifiedRequiredField() {
		if (StudentTypeEnum.TRIAL == studentDTO.getStudentType()) {
			return;
		}
		String session1DateStr = studentDTO.getSession1DateStr();
		session1DateValue = session1DateStr.split(" ")[0];
		session1Time = session1DateStr.split(" ")[1];
		String session2DateStr = studentDTO.getSession2DateStr();
		session2DateValue = session2DateStr.split(" ")[0];
		session2Time = session2DateStr.split(" ")[1];
		if (studentDTO.getSession3DateStr() != null || !studentDTO.getSession3DateStr().equals("")) {
			String session3DateStr = studentDTO.getSession3DateStr();
			session3DateValue = session3DateStr.split(" ")[0];
			session3Time = session3DateStr.split(" ")[1];
		}
	}

	private boolean checkDTO() {
		if (StudentTypeEnum.TRIAL == studentDTO.getStudentType()) {
			return true;
		}
		if (null == studentDTO.getSession1DateStr() || null == studentDTO.getSession2DateStr()
				|| null == studentDTO.getSession3DateStr()) {
			return false;
		}
		return true;
	}

	private void insertAudit(String auditMessage, UserSessionObject userSessionObject) {
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.MANAGE_STUDENT, auditMessage, userSessionObject.getLoginId());
	}

	private String checkFacilitatorDependency(StudentDetailsDTO studentDTO) {
		StringBuilder message = new StringBuilder();
		try {
			int session1Count = -1;
			int session2Count = -1;
			int session3Count = -1;

			session1Count = new SessionScheduleDetailsDAO().getcountBySessionByDate(studentDTO.getId(),
					studentDTO.getFacilitatorId(),
					TimeUtil.getDate(studentDTO.getSession1DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT), 1);

			session2Count = new SessionScheduleDetailsDAO().getcountBySessionByDate(studentDTO.getId(),
					studentDTO.getFacilitatorId(),
					TimeUtil.getDate(studentDTO.getSession2DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT), 2);
			if ((studentDTO.getClassId() == 1 || studentDTO.getClassId() == 2)
					&& (studentDTO.getSession3DateStr() != null || !studentDTO.getSession3DateStr().equals(""))) {
				session3Count = new SessionScheduleDetailsDAO().getcountBySessionByDate(studentDTO.getId(),
						studentDTO.getFacilitatorId(),
						TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT), 3);
			}
			if (session1Count > 0) {
				message.append("Session 1");
			}
			if (session2Count > 0) {
				if (!message.toString().isEmpty()) {
					message.append(", ");
				}
				message.append("Session 2");
			}
			if (session3Count > 0) {
				if (!message.toString().isEmpty()) {
					message.append(", ");
				}
				message.append("Session 3");
			}
			if (!message.toString().isEmpty()) {
				message.append(" Date Time is already scheduled for Facilitator");
			} else {
				List<SessionScheduleDetailsDTO> getSessionDetaillist = new SessionScheduleDetailsDAO()
						.getDetailsByFacilitatorId(studentDTO.getFacilitatorId());
				if (null != getSessionDetaillist && !getSessionDetaillist.isEmpty()) {
					for (SessionScheduleDetailsDTO sessionScheduleDetailsDTO : getSessionDetaillist) {
						if (sessionScheduleDetailsDTO.getStudentId() != studentDTO.getId()) {

							if (!isValidSessionDateTime(sessionScheduleDetailsDTO.getSession1Date(), 1)) {
								message.append("Session 1");
							}
							if (!isValidSessionDateTime(sessionScheduleDetailsDTO.getSession2Date(), 2)) {
								if (!message.toString().isEmpty()) {
									message.append(", ");
								}
								message.append("Session 2");
							}
							if (sessionScheduleDetailsDTO.getSession3Date() != null) {
								if (!isValidSessionDateTime(sessionScheduleDetailsDTO.getSession3Date(), 3)) {
									if (!message.toString().isEmpty()) {
										message.append(", ");
									}
									message.append("Session 3");
								}
							}
						}

					}
					if (!message.toString().isEmpty()) {
						message.append(" Date Time is already scheduled for Facilitator");
					}
				}
			}

		} catch (Exception e) {
			OUT.debug(ApplicationConstants.EXCEPTION, e);
			return null;
		}
		return message.toString();
	}

	private boolean isValidSessionDateTime(Date sessionDate, int count) {
		Calendar iterateSessionStartTime = Calendar.getInstance();
		Calendar iterateSessionStopTime = Calendar.getInstance();
		try {
			switch (count) {
			case 1:
				iterateSessionStartTime
						.setTime(TimeUtil.getDate(studentDTO.getSession1DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
				iterateSessionStopTime
						.setTime(TimeUtil.getDate(studentDTO.getSession1DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
				iterateSessionStopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
				break;
			case 2:
				iterateSessionStartTime
						.setTime(TimeUtil.getDate(studentDTO.getSession2DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
				if (studentDTO.getSession3DateStr() != null) {
					iterateSessionStopTime.setTime(
							TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
				}
				iterateSessionStopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
				break;
			case 3:
				if (studentDTO.getSession3DateStr() != null) {
					iterateSessionStartTime.setTime(
							TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
					iterateSessionStopTime.setTime(
							TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
					iterateSessionStopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
				}
				break;

			}

			if (null != sessionDate) {
				Calendar startTime = Calendar.getInstance();
				Calendar stopTime = Calendar.getInstance();
				startTime.setTimeInMillis(sessionDate.getTime());
				stopTime.setTimeInMillis(sessionDate.getTime());
				stopTime.add(Calendar.HOUR, ApplicationConstants.SESSION_HOURS);
				stopTime.add(Calendar.MINUTE, -1);
				if (iterateSessionStartTime.getTime().equals(startTime.getTime())
						|| iterateSessionStartTime.getTime().after(startTime.getTime())
								&& iterateSessionStartTime.getTime().before(stopTime.getTime())) {
					return false;
				} else if (iterateSessionStopTime.getTime().after(startTime.getTime())
						&& iterateSessionStopTime.getTime().before(stopTime.getTime())) {
					return false;
				}
			}
		} catch (ParseException e) {
			OUT.info("Date from database is in wrong format");
		}

		return true;
	}

	private void fieldRequired() throws Exception {
		setCityList(service.getCityList());
		setClassList(service.getClassList());
		setSchoolList(service.getSchoolList());
		setFacilitatorList(service.getFacilitatorlist());
		setGenderList(Arrays.asList(GenderTypeEnum.values()));
		setTimeList(service.getTimeList());


		Map<Integer, ArrayList<Map<String, String>>> VenueAdressMap = null;
		Map<Integer, ArrayList<Map<String, String>>> schoolCityMap = null;
		Map<Integer, ArrayList<Map<String, String>>> facilitatorCityMap = null;

		if (cityList != null && !cityList.isEmpty()) {
			VenueAdressMap = service.getallvenuedetail(cityList);
			schoolCityMap = service.getallcitySchool(cityList);
			facilitatorCityMap = service.getallFacilitatorListCity(cityList);

		}

		jsonObject.put("VenueAdressMap", VenueAdressMap);
		schooljsonObject.put("schoolCityMap", schoolCityMap);

		facilitatorCityjsonObject.put("facilitatorcityMap", facilitatorCityMap);


		globalSettingMap = new HashMap<String, String>();
		service.getGlobalSettingMap(globalSettingMap);
	}


	public void updateDHStudentConverted() 
	{
		SqlSession session = null;
		DHStudentExtraDetailsDAO dhStudentExtraDetailDAO = new DHStudentExtraDetailsDAO();
		try {
			session = MyBatisManager.getInstance().getSession();
			int result = dhStudentExtraDetailDAO.updateDHStudentConverted(session, studentId);
			session.commit();
		} catch (Exception e) {
			OUT.debug(ApplicationConstants.EXCEPTION, e);
		}

	}

	@Override
	public StudentDetailsDTO getModel() {
		return studentDTO;
	}

	public StudentDetailsDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDetailsDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public List<CityDTO> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityDTO> cityList) {
		this.cityList = cityList;
	}

	public List<ClassDTO> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassDTO> classList) {
		this.classList = classList;
	}

	public List<SchoolDTO> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<SchoolDTO> schoolList) {
		this.schoolList = schoolList;
	}

	public List<FacilitatorDetailsDTO> getFacilitatorList() {
		return facilitatorList;
	}

	public void setFacilitatorList(List<FacilitatorDetailsDTO> facilitatorList) {
		this.facilitatorList = facilitatorList;
	}

	public List<GenderTypeEnum> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<GenderTypeEnum> genderList) {
		this.genderList = genderList;
	}

	public String getSession1Time() {
		return session1Time;
	}

	public void setSession1Time(String session1Time) {
		this.session1Time = session1Time;
	}

	public String getSession2Time() {
		return session2Time;
	}

	public void setSession2Time(String session2Time) {
		this.session2Time = session2Time;
	}

	public String getSession3Time() {
		return session3Time;
	}

	public void setSession3Time(String session3Time) {
		this.session3Time = session3Time;
	}

	public List<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}

	public String getSession1DateValue() {
		return session1DateValue;
	}

	public void setSession1DateValue(String session1DateValue) {
		this.session1DateValue = session1DateValue;
	}

	public String getSession2DateValue() {
		return session2DateValue;
	}

	public void setSession2DateValue(String session2DateValue) {
		this.session2DateValue = session2DateValue;
	}

	public String getSession3DateValue() {
		return session3DateValue;
	}

	public void setSession3DateValue(String session3DateValue) {
		this.session3DateValue = session3DateValue;
	}

	public int getSessionTimeGap() {
		return sessionTimeGap;
	}

	public void setSessionTimeGap(int sessionTimeGap) {
		this.sessionTimeGap = sessionTimeGap;
	}

	public Map<String, String> getGlobalSettingMap() {
		return globalSettingMap;
	}

	public void setGlobalSettingMap(Map<String, String> globalSettingMap) {
		this.globalSettingMap = globalSettingMap;
	}

	public StudentTypeEnum[] getStudentTypeEnum() {
		return StudentTypeEnum.values();
	}

	public StudentTypeEnum.StudentTestTakenEnum[] getStudentTestEnum() {
		return StudentTypeEnum.StudentTestTakenEnum.values();
	}

	public Integer getDHStudentId() {
		return DHStudentId;
	}

	public void setDHStudentId(Integer dHStudentId) {
		DHStudentId = dHStudentId;
	}

}
