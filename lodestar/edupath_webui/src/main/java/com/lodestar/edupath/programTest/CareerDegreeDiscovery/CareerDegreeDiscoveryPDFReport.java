package com.lodestar.edupath.programTest.CareerDegreeDiscovery;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHEdupathDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.RaisecCodeDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.programTest.CareerDegreeDiscovery.CDDPDFReportService;
import com.lodestar.edupath.programTest.engineeringBranchSelector.EBS_Set2Service;


public class CareerDegreeDiscoveryPDFReport extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger	OUT	= LoggerFactory.getLogger(CareerDegreeDiscoveryPDFReport.class);
	
	private String fileName;
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos;
	private SqlSession session = null;
	private Map<String, Object>	systemRecommendation = new HashMap<String, Object>();
	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
	CDDPDFReportService service = new CDDPDFReportService();
	StudentDetailsDTO student = null;
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	CareerDegreeDiscoveryRecommendation _CDDRService= new CareerDegreeDiscoveryRecommendation();
	


	public String execute() {
		OUT.info("Inside CareerDegreeDiscoveryPDFReport execute");
		try {
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport getUserSessionObject():{}", getUserSessionObject());
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}

			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
//			systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(student.getId(), false);
			systemRecommendation = new EBS_Set2Service().getSystemRecommendation(student.getId(), false);
			StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,student.getId());
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport cgtResult:{}",cgtResult);
			cgtOcc=_CDDRService.getCareerDegreeDiscoveryRecommendation(student);
			OUT.debug("bharath CareerDegreeDiscoveryPDFReport cgtOcc:{}",cgtOcc);
			
			if(cgtOcc.isEmpty()) {
				return "lowAptitude";
			}
			 
			List<Integer> occupationIds= new ArrayList<Integer>();
			for(OccupationDTO occ:cgtOcc)
			{
				occupationIds.add(occ.getId());
			}
			List<OccupationDTO> occlistwithfitment=  cgtOcc;
			List<DHEdupathDTO> edupathlist= DHEdupathDAO.getDHEdupathbyOccId(occupationIds);
			for(OccupationDTO occ:occlistwithfitment)
			{
				for(DHEdupathDTO edu:edupathlist)
				{
					if(occ.getId()==edu.getOccupationId())
					{
//						edu.setOccName(occ.getName());
//						edu.setDhPriority(occ.getDhPriority());
						occ.setDheduDTO(edu);
					}
				}
			}

			OUT.debug("updated fitement edupathlist:{}",edupathlist);
			Collections.sort(occlistwithfitment, new OccupationPrioritySorter());
			OUT.debug("updated fitement occlistwithfitment:{}",occlistwithfitment);
			List<String> examList = new ArrayList<String>();
			for(DHEdupathDTO dhedupath : edupathlist)
			{
				String[] examNamelist= dhedupath.getEntExam().split(",");
				for(String _examName: examNamelist)
				{
					examList.add(_examName.trim());	
				}
				
			}
			List<DHEntExamDTO> entExamlist = DHEdupathDAO.getexamdescription(examList);
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
			
			 if (edupathlist.isEmpty()) {
				 return "reporterror";
			 }
			
			if(student.getClassId()==1 ||student.getClassId()==2)
			{
				baos = service.GeneratePDFReportClasss910(student,occlistwithfitment,raiseccodeDTO,app_score,entExamlist,edupathlist);
			}
			else
			{
				baos = service.GeneratePDFReportClasssRest(student,occlistwithfitment,raiseccodeDTO,app_score,entExamlist,edupathlist);
			}

			if (baos != null) {
				fileName= "Lodestar_Career_Degree_Discovery_Report.pdf";
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
				return "success";
			} 
			 
		
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
			
		}
		return "success";
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
