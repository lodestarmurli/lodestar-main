package com.lodestar.edupath.student.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.facilitatorstudent.service.FacilitatorStudentService;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.student.service.StudentDetailService;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.opensymphony.xwork2.ModelDriven;

public class StudentDetailsSummaryAction extends BaseAction implements ModelDriven<StudentDetailsDTO>
{

	private static final long		serialVersionUID	= 4107476161853263600L;
	private static Logger			OUT					= LoggerFactory.getLogger(StudentDetailsSummaryAction.class);

	private List<StudentDetailsDTO>	studentList			= new ArrayList<StudentDetailsDTO>();

	private StudentDetailsDTO		studentDetailsDTO	= new StudentDetailsDTO();

	private StudentDetailService	service;
	private int 					userId;
	private String studLoginId;
	List<StudentVO> finalList;
	List<StudentIndividualVO> finalListIndividual;

	public StudentDetailsSummaryAction()
	{
		service = new StudentDetailService();
	}

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute StudentDetailsSummaryAction  the Summary Action Of======================= {}", StudentDetailsSummaryAction.class);
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		// studentList = service.getAllStudentDetails(userSessionObject);
		createDataTableColumn(StudentVO.class);
		getJSONSummary();
		return SUCCESS;
	}

	public void getJSONSummary()
	{
		try
		{
			dataTableVO = DataTableUtils.setDataTableVO(request, StudentVO.class);
			//List<StudentVO> finalList = service.getAllStudentSummary(dataTableVO);
			finalList = service.getAllStudentSummaryNew(dataTableVO);
			//JSONArray dataArr = convertCollectionToJSONArray(finalList);
			//JSONObject jsonObj = DataTableUtils.createDataTableJSON(dataTableVO.getDraw(), dataTableVO.getRecordsTotalCount(), dataTableVO.getRecordsFilteredCount(), dataArr);
			//writeToJSONResponse(jsonObj);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	
	public void getCompleteSummary()
	{
		try
		{
			userId = Integer.parseInt(request.getParameter("userId"));
			List<StudentIndividualVO> finalList = service.getIndividualStudentSummary(userId);
			JSONArray dataArr = convertCollectionToJSONArray(finalList);
			JSONObject jsonObj = DataTableUtils.createDataTableJSON(dataTableVO.getDraw(), dataTableVO.getRecordsTotalCount(),dataTableVO.getRecordsFilteredCount(), dataArr);
			writeToJSONResponse(dataArr);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
	}
	
	
	//Start by bharath on 10/10/2019
	
	public String searchData() {
		OUT.debug("StudentDetailsSummaryAction : inside searchData method");
		try {
			dataTableVO = DataTableUtils.setDataTableVO(request, StudentVO.class);
			studLoginId = request.getParameter("studLoginId");
			OUT.debug("getting student session details for loginId : {}", studLoginId);
			finalList = service.getStudentDetailsByLoginId(studLoginId);
			OUT.debug("getting student session details for finalList : {}", finalList);
			//JSONArray dataArr = convertCollectionToJSONArray(finalList);
			//JSONObject jsonObj = DataTableUtils.createDataTableJSON(dataTableVO.getDraw(), dataTableVO.getRecordsTotalCount(), dataTableVO.getRecordsFilteredCount(), dataArr);
			//writeToJSONResponse(jsonObj);
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}
	//End by bharath on 10/10/2019
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStudLoginId() {
		return studLoginId;
	}

	public void setStudLoginId(String studLoginId) {
		this.studLoginId = studLoginId;
	}

	public List<StudentVO> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<StudentVO> finalList) {
		this.finalList = finalList;
	}

	public List<StudentIndividualVO> getFinalListIndividual() {
		return finalListIndividual;
	}

	public void setFinalListIndividual(List<StudentIndividualVO> finalListIndividual) {
		this.finalListIndividual = finalListIndividual;
	}
}