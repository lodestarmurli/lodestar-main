package com.lodestar.edupath.student.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.GenderTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.bean.StudentBean;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.student.bean.TrailStudentVO;
import com.lodestar.edupath.student.service.TrailStudentDetailService;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.opensymphony.xwork2.ModelDriven;

public class TrialStudentDetailsAction extends BaseAction implements ModelDriven<StudentDetailsDTO>
{

	private static final long		serialVersionUID	= 4107476161853263600L;
	private static Logger			OUT					= LoggerFactory.getLogger(TrialStudentDetailsAction.class);

	private List<StudentDetailsDTO>	studentList			= new ArrayList<StudentDetailsDTO>();

	private StudentDetailsDTO		studentDetailsDTO	= new StudentDetailsDTO();

	private TrailStudentDetailService	service;
	private int 						userId;	
	private Integer						studentId;
	private List<CityDTO>				cityList			= new ArrayList<CityDTO>();
	private List<ClassDTO>				classList			= new ArrayList<ClassDTO>();
	private List<SchoolDTO>				schoolList			= new ArrayList<SchoolDTO>();
	private List<FacilitatorDetailsDTO>	facilitatorList		= new ArrayList<FacilitatorDetailsDTO>();
	private List<GenderTypeEnum>		genderList			= new ArrayList<GenderTypeEnum>();
	private StudentBean					studentDTO			= new StudentBean();
	private String						session1DateValue;
	private String						session1Time;
	private String						session2DateValue;
	private String						session2Time;
	private String						session3DateValue;
	private String						session3Time;
	private List<String>				timeList			= new ArrayList<String>();
	private int							sessionTimeGap;
	private Map<String, String>			globalSettingMap;
	private String						openSessionType;
	private JSONObject					jsonObject			= new JSONObject();
	private JSONObject					venuejsonObject		= new JSONObject();
	private JSONObject					schooljsonObject	= new JSONObject();
	private JSONObject					facilitatorCityjsonObject	= new JSONObject();
	private StudentDetailService		service2 =  new StudentDetailService();			
	
	public TrialStudentDetailsAction()
	{
		service = new TrailStudentDetailService();
	}

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the TailStudentDetailsAction Summary Action Of======================= {}", TrialStudentDetailsAction.class);
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		// studentList = service.getAllStudentDetails(userSessionObject);
		createDataTableColumn(TrailStudentVO.class);
		
		return SUCCESS;
	}

	public void getJSONSummary()
	{
		try
		{
			dataTableVO = DataTableUtils.setDataTableVO(request, TrailStudentVO.class);
			List<TrailStudentVO> finalList = service.getAllStudentSummaryNew(dataTableVO);
			JSONArray dataArr = convertCollectionToJSONArray(finalList);
			JSONObject jsonObj = DataTableUtils.createDataTableJSON(dataTableVO.getDraw(), dataTableVO.getRecordsTotalCount(),
					dataTableVO.getRecordsFilteredCount(), dataArr);
			writeToJSONResponse(jsonObj);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	
	public void getCompleteSummary()
	{
		OUT.debug("studentId : {} and userId : {} and openSessionType : {}", studentId, userId = Integer.parseInt(request.getParameter("userId")), openSessionType);
		try
		{
			userId = Integer.parseInt(request.getParameter("userId"));
			List<StudentIndividualVO> finalList = service.getIndividualStudentSummary(userId);
			JSONArray dataArr = convertCollectionToJSONArray(finalList);
			JSONObject jsonObj = DataTableUtils.createDataTableJSON(dataTableVO.getDraw(), dataTableVO.getRecordsTotalCount(),dataTableVO.getRecordsFilteredCount(), dataArr);
//			JSONObject jsonObj=(JSONObject) finalList;
			writeToJSONResponse(dataArr);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	public String openSession()
	{
		OUT.debug("TailStudentDetailsAction : inside openSession method");
		userId = Integer.parseInt(request.getParameter("userId"));
		studentId = Integer.parseInt(request.getParameter("studentId"));
		openSessionType = request.getParameter("openSessionType");
		OUT.debug("studentId : {} and userId : {} and openSessionType : {}, getUserSessionObject().getLoginId():{}", studentId, userId, openSessionType, getUserSessionObject().getLoginId());
		/*try
		{
			OUT.debug("studentId : {} and userId : {} and openSessionType : {}, getUserSessionObject().getLoginId():{}", studentId, userId, openSessionType, getUserSessionObject().getLoginId());
			service.modifyStudentSessionByOpenSession(studentId, userId, openSessionType, getUserSessionObject().getLoginId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}*/
		return "openSession";
	}
	
	
	public String delete() throws Exception
	{
		
		OUT.debug("TailStudentDetailsAction : inside delete method");
		userId = Integer.parseInt(request.getParameter("userId"));
		studentId = Integer.parseInt(request.getParameter("studentId"));
		openSessionType = request.getParameter("openSessionType");
		OUT.debug("studentId : {} and userId : {} and openSessionType : {}", studentId, userId, openSessionType);
		if (null == studentId || studentId == 0)
		{
			addActionError("Invalid id for student: " + studentId);
			return ERROR;
		}
		StudentDetailsDTO dto = service.getStudentDTO(studentId);
		EActionStatus status = service.doDelete(dto);
		if (status == EActionStatus.FAILURE)
		{
			addActionError("Due to Exception Student can not be deleted");
			return ERROR;
		}
		addActionMessage("Student " + studentDTO.getName() + " deleted successfully");
		StringBuilder auditMessage = new StringBuilder();
		auditMessage.append("Student ").append(studentDTO.getName()).append(" (").append(studentDTO.getId()).append(") ").append(" details has been deleted on " +service.getAuditDateStr());
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.MANAGE_STUDENT, auditMessage.toString(), getUserSessionObject().getLoginId());
		return "delete";
	}
	
	
	private void fieldRequired() throws Exception
	{
		cityList = service2.getCityList();
		classList = service2.getClassList();
		schoolList = service2.getSchoolList();
		facilitatorList = service2.getFacilitatorlist();
		genderList = Arrays.asList(GenderTypeEnum.values());
		timeList = service2.getTimeList();
		
		
		Map<Integer, ArrayList<Map<String, String>>> VenueAdressMap = null;
		Map<Integer, ArrayList<Map<String, String>>> schoolCityMap = null;
		Map<Integer, ArrayList<Map<String, String>>> facilitatorCityMap = null;
		
		if (cityList != null && !cityList.isEmpty())
		{
			VenueAdressMap = service2.getallvenuedetail(cityList);
			schoolCityMap = service2.getallcitySchool(cityList);
			facilitatorCityMap = service2.getallFacilitatorListCity(cityList);
			
		}
		
		jsonObject.put("VenueAdressMap", VenueAdressMap);
		
		schooljsonObject.put("schoolCityMap", schoolCityMap);
		
		facilitatorCityjsonObject.put("facilitatorcityMap", facilitatorCityMap);
		
		globalSettingMap = new HashMap<String, String>();
		service2.getGlobalSettingMap(globalSettingMap);
		
		
	}

	public String edit() throws Exception
	{
		if (null == studentId || studentId == 0)
		{
			addActionError("Invalid id for student: " + studentId);
			return ERROR;
		}
		StudentDetailsDTO dto = service2.getStudentDTO(studentId);
		
		if(dto.getFatherEmailId() == null && dto.getMotheremailId()  == null)
		{
			dto.setFatherEmailId(dto.getFatheremailId());
		}
		if(dto.getFathercontactno() ==null && dto.getMothercontactno() ==null)
		{
			dto.setFathercontactno(dto.getContactNumber());
		}
		if(dto.getFatherName() == null && dto.getMotherName()== null)
		{
			dto.setFatherName(dto.getFathername());
		}
		
		
		if(dto.getVenue() != null)
		{
			dto.setVenue((dto.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
		}
		
		
		
		
	
		BeanUtilsBean.getInstance().copyProperties(studentDTO, dto);
		if (null == studentDTO)
		{
			addActionError("due to Exception can not get the value for student");
			return ERROR;
		}
		if (studentDTO.getStudentType() == null)
		{
			studentDTO.setStudentType(StudentTypeEnum.FULL);
		}
		fieldRequired();
		modifiedRequiredField();
		request.setAttribute("actionType", "modify");
		return SUCCESS;
	}

	private void modifiedRequiredField()
	{
		if (StudentTypeEnum.FULL == studentDTO.getStudentType())
		{
			String session1DateStr = TimeUtil.getDateFromMillis(studentDTO.getSeDetailsDTO().getSession1Date().getTime(), TimeUtil.FILTER_DATE_TIME_FORMAT);
			setSession1DateValue(session1DateStr.split(" ")[0]);
			setSession1Time(session1DateStr.split(" ")[1]);
			String session2DateStr = TimeUtil.getDateFromMillis(studentDTO.getSeDetailsDTO().getSession2Date().getTime(), TimeUtil.FILTER_DATE_TIME_FORMAT);
			setSession2DateValue(session2DateStr.split(" ")[0]);
			setSession2Time(session2DateStr.split(" ")[1]);
			if(studentDTO.getSeDetailsDTO().getSession3Date()!=null) {
				String session3DateStr = TimeUtil.getDateFromMillis(studentDTO.getSeDetailsDTO().getSession3Date().getTime(), TimeUtil.FILTER_DATE_TIME_FORMAT);
				setSession3DateValue(session3DateStr.split(" ")[0]);
				setSession3Time(session3DateStr.split(" ")[1]);
			}
			
		}
	}

	
	

	public List<StudentDetailsDTO> getStudentList()
	{
		return studentList;
	}

	public void setStudentList(List<StudentDetailsDTO> studentList)
	{
		this.studentList = studentList;
	}

	public StudentDetailsDTO getStudentDetailsDTO()
	{
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO)
	{
		this.studentDetailsDTO = studentDetailsDTO;
	}

	@Override
	public StudentDetailsDTO getModel()
	{
		return studentDetailsDTO;
	}

	public TrailStudentDetailService getService() {
		return service;
	}

	public void setService(TrailStudentDetailService service) {
		this.service = service;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public StudentBean getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentBean studentDTO) {
		this.studentDTO = studentDTO;
	}

	public String getSession1DateValue() {
		return session1DateValue;
	}

	public void setSession1DateValue(String session1DateValue) {
		this.session1DateValue = session1DateValue;
	}

	public String getSession1Time() {
		return session1Time;
	}

	public void setSession1Time(String session1Time) {
		this.session1Time = session1Time;
	}

	public String getSession2DateValue() {
		return session2DateValue;
	}

	public void setSession2DateValue(String session2DateValue) {
		this.session2DateValue = session2DateValue;
	}

	public String getSession2Time() {
		return session2Time;
	}

	public void setSession2Time(String session2Time) {
		this.session2Time = session2Time;
	}

	public String getSession3DateValue() {
		return session3DateValue;
	}

	public void setSession3DateValue(String session3DateValue) {
		this.session3DateValue = session3DateValue;
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

	public String getOpenSessionType() {
		return openSessionType;
	}

	public void setOpenSessionType(String openSessionType) {
		this.openSessionType = openSessionType;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
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
	
	public StudentTypeEnum[] getStudentTypeEnum()
	{
		return StudentTypeEnum.values();
	}
	
	public StudentTypeEnum.StudentTestTakenEnum[] getStudentTestEnum()
	{
		return StudentTypeEnum.StudentTestTakenEnum.values();
	}

	@Override
	
	public String toString() {
		return "StudentDetailsAction [studentId=" + studentId + ", cityList=" + cityList + ", classList=" + classList
				+ ", schoolList=" + schoolList + ", facilitatorList=" + facilitatorList + ", genderList=" + genderList
				+ ", studentDTO=" + studentDTO + ", session1DateValue=" + session1DateValue + ", session1Time="
				+ session1Time + ", session2DateValue=" + session2DateValue + ", session2Time=" + session2Time
				+ ", session3DateValue=" + session3DateValue + ", session3Time=" + session3Time + ", service=" + service
				+ ", timeList=" + timeList + ", sessionTimeGap=" + sessionTimeGap + ", globalSettingMap="
				+ globalSettingMap + ", openSessionType=" + openSessionType + ", jsonObject=" + jsonObject
				+ ", venuejsonObject=" + venuejsonObject + ", schooljsonObject=" + schooljsonObject
				+ ", facilitatorCityjsonObject=" + facilitatorCityjsonObject + "]";
	}
	
	
	
}