package com.lodestar.edupath.PDFReport.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.lodestar.edupath.base.BaseAction;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finalsummary.FinalSummaryAction;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.lodestar.edupath.finalsummary.service.FinalSummaryService;
import com.lodestar.edupath.studentinfo.StudentInfoService;
import com.lodestar.edupath.util.ApplicationMenuUtils;
import com.lodestar.edupath.util.ApplicationProperties;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class StudentFinalReportService extends BaseAction implements Action,ModelDriven<ReportSummaryBean>{
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(FinalSummaryAction.class);
	private ReportSummaryBean	bean				= new ReportSummaryBean();
	private int ReportstudentId;
	private String fileName;
	private InputStream fileInputStream;
	private List<StudentTUMDTO> studentTUMList = new ArrayList<StudentTUMDTO>();
	private StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
	private StudentCGTResult studentCGTResult = new StudentCGTResult();
	private Map<String, String> occupIndusMap = new HashMap<String, String>();
	private int studentId;
	private ApplicationProperties properties = ApplicationProperties.getInstance();
	private GeneratePDFReportFile generatePDFReportFileobj = new GeneratePDFReportFile();
	
	public String execute()
	{
		OUT.info("Inside StudentFinalReportService.execute");
		int IsStudent1=ReportstudentId;
		
		StudentDetailsDTO studentDetailsByUserId;
		
		
		
		//System.out.println("==============IsStudent1=================>"+IsStudent1);
		
		//System.out.println("==============getStudentSessionObject().getId()=================>"+getStudentSessionObject().getUserId());
		
		//System.out.println("==============getStudentSessionObject().getId()=================>"+getUserSessionObject().getId());
		
		if(IsStudent1==1)
		{
			try {
				
				
				studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(getUserSessionObject().getId());
			} catch (Exception e) {
				
				OUT.error(ApplicationConstants.EXCEPTION, e);
				return "error";
			}
		}
		else
		{
			 try {
				studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(getStudentSessionObject().getUserId());
			} catch (Exception e) {
				
				OUT.error(ApplicationConstants.EXCEPTION, e);
				return "error";
			}
		}
		
		
		
		
		if(studentDetailsByUserId!=null)
		{
			int userId= studentDetailsByUserId.getUserId();
		
		
		
		OUT.debug("Show summary report for student user Id: {}", userId);
		try
		{
			
			
			setStudentId(studentDetailsByUserId.getId());
			//start bharath on 24-05-2019
			if(IsStudent1==1) 
			{
				bean = new FinalSummaryService().getSummaryReportForStudent(userId);	
			}else
			{
				bean = new FinalSummaryService().getSummaryReportForfacilitator(userId);   
			}
			//end bharath on 24-05-2019
			
			studentDetailsDTO = new StudentInfoService().getStudentDetailsById(studentId);
			
			List<StudentTUMDTO> tumList = null;
			Map<Long, String> studentQuesAnsMap = null;
			if (null != studentDetailsDTO) {

				tumList = studentDetailsDTO.getStudentTUMList();
				studentQuesAnsMap = studentDetailsDTO.getStudentQuesAnsMap();
			}
			if (tumList != null) {
				for (StudentTUMDTO studentTUMDTO : tumList) {
					studentQuesAnsMap.put((long) studentTUMDTO.getQuestionSlNo(), studentTUMDTO.getAnswer());
				}
			}

			studentCGTResult = new StudentInfoService().getStudentCGTResultByStudentId(studentId);
			if (studentCGTResult != null) {
				String occupationIds = studentCGTResult.getOccupationIds();
				if (occupationIds != null) {
					String[] occupaIdArray = occupationIds.split(",");
					for (String occupid : occupaIdArray) {
						OccupationDTO occupationDTO = new StudentInfoService()
								.getOccupationDetailsById(Integer.parseInt(occupid.trim()));
						if (occupationDTO != null) {
							occupIndusMap.put(occupationDTO.getName(), occupationDTO.getIndustryName());
						}
					}
				}
			}
			
			ByteArrayOutputStream baos= generatePDFReportFileobj.GenerateStudentFinalReport(bean,studentCGTResult,occupIndusMap);
			if (baos != null) {
				fileName = properties.getProperty("com.edupath.report.FinalReport.name");
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
			} else {
				return "error";
			}
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}
		return "success";
		}
		else
		{
			return "error";
		}
	}

	

	
	@Override
	public ReportSummaryBean getModel()
	{
		return bean;
	}

	public ReportSummaryBean getBean()
	{
		return bean;
	}

	public void setBean(ReportSummaryBean bean)
	{
		this.bean = bean;
	}


	

	




	public int getReportstudentId() {
		return ReportstudentId;
	}



	public void setReportstudentId(int reportstudentId) {
		ReportstudentId = reportstudentId;
	}



	public String getFileName() {
		return fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<StudentTUMDTO> getStudentTUMList() {
		return studentTUMList;
	}

	public void setStudentTUMList(List<StudentTUMDTO> studentTUMList) {
		this.studentTUMList = studentTUMList;
	}

	public StudentDetailsDTO getStudentDetailsDTO() {
		return studentDetailsDTO;
	}

	public void setStudentDetailsDTO(StudentDetailsDTO studentDetailsDTO) {
		this.studentDetailsDTO = studentDetailsDTO;
	}

	public StudentCGTResult getStudentCGTResult() {
		return studentCGTResult;
	}

	public void setStudentCGTResult(StudentCGTResult studentCGTResult) {
		this.studentCGTResult = studentCGTResult;
	}

	public Map<String, String> getOccupIndusMap() {
		return occupIndusMap;
	}

	public void setOccupIndusMap(Map<String, String> occupIndusMap) {
		this.occupIndusMap = occupIndusMap;
	}


}
