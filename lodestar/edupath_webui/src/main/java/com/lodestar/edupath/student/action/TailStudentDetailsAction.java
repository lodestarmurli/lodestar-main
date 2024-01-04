package com.lodestar.edupath.student.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.student.bean.StudentBean;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.student.bean.TrailStudentVO;
import com.lodestar.edupath.student.service.TrailStudentDetailService;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.opensymphony.xwork2.ModelDriven;

public class TailStudentDetailsAction extends BaseAction implements ModelDriven<StudentDetailsDTO>
{

	private static final long		serialVersionUID	= 4107476161853263600L;
	private static Logger			OUT					= LoggerFactory.getLogger(TailStudentDetailsAction.class);

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

	public TailStudentDetailsAction()
	{
		service = new TrailStudentDetailService();
	}

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the TailStudentDetailsAction Summary Action Of======================= {}", TailStudentDetailsAction.class);
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
//			List<StudentVO> finalList = service.getAllStudentSummary(dataTableVO);
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
}