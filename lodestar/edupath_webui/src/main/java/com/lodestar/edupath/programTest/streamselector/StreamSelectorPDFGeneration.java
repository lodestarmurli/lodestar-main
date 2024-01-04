package com.lodestar.edupath.programTest.streamselector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.dailyHunt.InterestResultUtilService;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentOccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.RaisecCodeDAO;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorResultVO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StreamSelectorPDFGeneration extends BaseAction{

	private static final long	serialVersionUID	= 1L;
	
	private static final Logger		 OUT	 = LoggerFactory.getLogger(StreamSelectorPDFGeneration.class);
	private String   								Status="ERROR";
	
	private String   								Message="";
	private String 									token;
	private String									fileName;
	private Map<String, Object>				systemRecommendation	= new HashMap<String, Object>();
	StudentCGTResult cgtresultDTO = new StudentCGTResult();
	StudentCGTDAO cgtresultDAO = new StudentCGTDAO();
	
	StreamSelectorPDFGenerationService				service = new StreamSelectorPDFGenerationService();
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos ;
	
	StreamSelectorRecommendation sSR = new StreamSelectorRecommendation();
	List<StreamSelectorResultVO> resultMap = new ArrayList<StreamSelectorResultVO>();
	public String execute()
	{
		OUT.info("Inside StreamSelectorPDFGeneration execute");
		try
		{
			StudentDetailsDAO studentDAO= new StudentDetailsDAO();
			UserSessionObject userSessionObject = getUserSessionObject();
			if (null == userSessionObject)
			{
				return "SessionExpired";
			}
			UserDetailDTO userDetailDTO = new UserDetailDTO();
			
			userDetailDTO.setId(getUserSessionObject().getId());
			StudentDetailsDTO studentDetailsDTO =studentDAO.getStudentDetailsByUserId(userDetailDTO);
			
			systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(studentDetailsDTO.getId(), false);
			cgtresultDTO = cgtresultDAO.getStudentCGTResultByStudentId(studentDetailsDTO.getId());
			
			RaisecCodeDTO raiseccodeDTO = new RaisecCodeDTO();
			RaisecCodeDAO raiseccodeDAO = new RaisecCodeDAO();
			raiseccodeDTO = raiseccodeDAO.getRaisecCode(cgtresultDTO.getPersonalityCode());
			String raiseccode=raiseccodeDTO.getRaisec();
			String[] raiseccodeSplit=raiseccode.split(",");
			String raisecfullform="";
			for(String c: raiseccodeSplit)
			{
				for(RaisecCode RCenum: RaisecCode.values())
				{
					if(c.trim().equalsIgnoreCase(RCenum.name()))
					{
						raisecfullform+=RCenum.Value()+" ";
					}
				}
			}
			raiseccodeDTO.setFullFrom(raisecfullform);
			
			Map<String,String> app_score = new HashMap<String,String>();
			String[] keyVals=cgtresultDTO.getAppScore().split(",");
			for(String keyVal:keyVals)
	        {
	          String[] parts = keyVal.split(":",2);
	          app_score.put(parts[0].trim(),parts[1].trim());
	        }
			
			resultMap=sSR.getStreamSelectorRecommendation(studentDetailsDTO.getId(),cgtresultDTO);
			
			OUT.debug("StreamSelectorPDFGeneration resultMap:{}",resultMap);
			
			if(resultMap.size()==5)
			{
				baos = service.GeneratePDFReportSize5(studentDetailsDTO,resultMap,raiseccodeDTO,app_score);
			}
			else if(resultMap.size()==4)
			{
				baos = service.GeneratePDFReportSize4(studentDetailsDTO,resultMap,raiseccodeDTO,app_score);
			}
			else if(resultMap.size()==3)
			{
				baos = service.GeneratePDFReportSize3(studentDetailsDTO,resultMap,raiseccodeDTO,app_score);
			}
			else if(resultMap.size()==2)
			{
				baos = service.GeneratePDFReportSize2(studentDetailsDTO,resultMap,raiseccodeDTO,app_score);
			}
			else if(resultMap.size()==1)
			{
				baos = service.GeneratePDFReportSize1(studentDetailsDTO,resultMap,raiseccodeDTO,app_score);	
			}
			else {
				OUT.debug("StreamSelectorPDFGeneration terminating as there are no stream");
				return "error";
			}
			
			if (baos != null) {
				fileName= "Lodestar_Stream_Selector_Report.pdf";
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
				return "success";
			} 
			
		}catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			Status = "ERROR";
			Message = "Internal Exception Level 1";
			return "error";
		}
		return "error";
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public List<StreamSelectorResultVO> getResultMap() {
		return resultMap;
	}
	public void setResultMap(List<StreamSelectorResultVO> resultMap) {
		this.resultMap = resultMap;
	}
 
	
	
}
