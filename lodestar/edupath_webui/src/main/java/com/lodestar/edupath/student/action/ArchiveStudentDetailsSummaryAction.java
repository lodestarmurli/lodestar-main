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
import com.lodestar.edupath.student.bean.StudentArchiveVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.student.service.ArchiveStudentDetailService;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.opensymphony.xwork2.ModelDriven;

public class ArchiveStudentDetailsSummaryAction extends BaseAction implements ModelDriven<StudentDetailsDTO>
{

	private static final long		serialVersionUID	= 4107476161853263600L;
	private static Logger			OUT					= LoggerFactory.getLogger(ArchiveStudentDetailsSummaryAction.class);

	private List<StudentDetailsDTO>	studentList			= new ArrayList<StudentDetailsDTO>();

	private StudentDetailsDTO		studentDetailsDTO	= new StudentDetailsDTO();

	private ArchiveStudentDetailService	service;

	public ArchiveStudentDetailsSummaryAction()
	{
		service = new ArchiveStudentDetailService();
	}

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the Summary Action Of======================= {}", ArchiveStudentDetailsSummaryAction.class);
		UserSessionObject userSessionObject = getUserSessionObject();
		if (null == userSessionObject)
		{
			return "SessionExpired";
		}
		// studentList = service.getAllStudentDetails(userSessionObject);
		createDataTableColumn(StudentArchiveVO.class);
		return SUCCESS;
	}

	public void getArchiveJSONSummary()
	{
		try
		{
			
			dataTableVO = DataTableUtils.setDataTableVO(request, StudentArchiveVO.class);
			List<StudentArchiveVO> finalList = service.getAllStudentSummary(dataTableVO);
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