package com.lodestar.edupath.programTest.CareerFitment.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.RaisecCodeDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.RiasecActivityDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.StudentCareerFitmentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.programTest.CareerFitment.service.CareerFitmentPDFReportService;

public class CareerFitmentPDFReport extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentPDFReport.class);
	
	private String fileName;
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos;
	private SqlSession session = null;
	private Map<String, Object>	systemRecommendation = new HashMap<String, Object>();
	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
	StudentDetailsDTO student = null;
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	CareerFitmentRecommendation _CFRecommendation = new CareerFitmentRecommendation();
	private String webURL="";
	List<RiasecActivityDTO> riasecActivityDTOList = new ArrayList<RiasecActivityDTO>();
	
	@SuppressWarnings("unchecked")
	public String execute() 
	{
		OUT.info("Inside CareerFitmentPDFReport execute");
		try 
		{
			
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			OUT.debug("bharath CareerFitmentPDFReport getUserSessionObject():{}", getUserSessionObject());
			 
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(student.getId(), false);
			OUT.debug("bharath CareerFitmentPDFReport student:{}",student);
			StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,student.getId());
			OUT.debug("bharath CareerFitmentPDFReport cgtResult:{}",cgtResult);
			
			Map<String,Object> recommendationResult= _CFRecommendation.getCareerFitmentRecommendation(student,cgtResult);
			if(recommendationResult == null)
			{
				return ERROR;
			}
			
			//Start FOR Raisec Code page 
			Map<String,String> app_score = new HashMap<String,String>();
			String _appscorestr = (String) systemRecommendation.get(ApplicationConstants.SystemRecommendation.APP_SCORE.name());
			String[] keyVals=_appscorestr.split(",");
			for(String keyVal:keyVals)
	        {
	          String[] parts = keyVal.split(":",2);
	          app_score.put(parts[0],parts[1]);
	        }
			RaisecCodeDTO raiseccodeDTO = new RaisecCodeDTO();
			RaisecCodeDAO raiseccodeDAO = new RaisecCodeDAO();
			raiseccodeDTO = raiseccodeDAO.getRaisecCode(cgtResult.getPersonalityCode());
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
			//End FOR Raisec Code page
			OUT.debug("bharath CareerFitmentPDFReport recommendationResult.key=:{} recommendationResult:{},raiseccodeDTO:{},app_score:{}",recommendationResult.keySet(), recommendationResult,raiseccodeDTO,app_score );
			
			GlobalSttingDAO globalDAO = new GlobalSttingDAO();
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
			globalDTO = globalDAO.getPropertesValueByName(globalDTO);
			webURL = globalDTO.getPropertyValue()+"/engineeringSelector/AptitudeImprovementEngineeringSelectorPDFReport";
			
			cgtOcc=(List<OccupationDTO>) recommendationResult.get("cgtOcc");
			List<DHEntExamDTO> entExamlist=(List<DHEntExamDTO>) recommendationResult.get("entExamlist");
			List<String> Codelist= (List<String>) recommendationResult.get("raisecCodelist");
			int countHighFitment = (int) recommendationResult.get("countHighFitment");
			List<OccupationDTO> top3Occ = (List<OccupationDTO>) recommendationResult.get("allOccList");

			if(!Codelist.isEmpty())
			{
				riasecActivityDTOList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_RIASEC_ACTIVITY_LIST,Codelist);
			}
			OUT.debug("bharath 	CareerFitmentPDFReport RiasecActivityDTOList:{}",riasecActivityDTOList);
			
			if(Codelist.isEmpty() && countHighFitment == 1 )
			{
				baos= new CareerFitmentPDFReportService().GeneratePDFReport(student,cgtOcc,raiseccodeDTO,app_score,entExamlist,webURL);
			}
			else if(!Codelist.isEmpty() && countHighFitment == 1 )
			{
				baos= new CareerFitmentPDFReportService().GeneratePDFReportActivities(student,cgtOcc,raiseccodeDTO,app_score,entExamlist,webURL,riasecActivityDTOList);
			}
			else if(Codelist.isEmpty() && countHighFitment == 0 )
			{
				baos= new CareerFitmentPDFReportService().GeneratePDFReportAlternative(student,cgtOcc,raiseccodeDTO,app_score,entExamlist,webURL,top3Occ);
			}
			else if(!Codelist.isEmpty() && countHighFitment == 0 )
			{
				baos= new CareerFitmentPDFReportService().GeneratePDFReportAlternativeActivities(student,cgtOcc,raiseccodeDTO,app_score,entExamlist,webURL,riasecActivityDTOList,top3Occ);
			}
			
			if (baos != null) {
				fileName= "Lodestar_Career_Fitment_Report.pdf";
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
				return SUCCESS;
			} 

			
		} catch (Exception e) {
			if (session != null) {
				session.close();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
			
		
		return ERROR;
	
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
	
	
	
	
}
