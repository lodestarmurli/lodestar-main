package com.lodestar.edupath.programTest.engineeringBranchSelector;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHEdupathDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.RaisecCodeDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.engineeringselector.EngineeringSelectorDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EBFavouriteSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector.EngineeringSelectorDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class EngineeringSelectorPDFReport extends BaseAction {

	private static final long serialVersionUID = 1L;

	private static final Logger OUT = LoggerFactory.getLogger(EngineeringSelectorPDFReport.class);
	private String Status = "ERROR";
	
	private String Message = "";
	private String token;
	private String fileName;
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos;
	private SqlSession session = null;
	private Map<String, Object>	systemRecommendation = new HashMap<String, Object>();
	private String webURL="";
	Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	EngineeringSelectorRecommendation esr = new EngineeringSelectorRecommendation();
	StudentDetailsDTO student = null;
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	List<OccupationDTO> cgtOcc = new ArrayList<OccupationDTO>();
	EngineeringSelectorPDFReportService service = new EngineeringSelectorPDFReportService();
	

	public String execute() {
		OUT.info("Inside EngineeringSelectorPDFReport execute");
		try {
			OUT.debug("bharath EngineeringSelectorPDFReport getUserSessionObject():{}", getUserSessionObject());
//			if(getUserSessionObject()==null)
//			{
//				return
//			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(student.getId(), false);
			StudentCGTResult cgtResult = (StudentCGTResult) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_CGT_RESULT,student.getId());
			OUT.debug("bharath EngineeringSelectorPDFReport cgtResult:{}",cgtResult);
			
			EngineeringSelectorDTO dto = new EngineeringSelectorDTO();
			
			EBFavouriteSelectorDTO eBFSdto = new EBFavouriteSelectorDTO();
			EngineeringSelectorDAO eBFSdao = new EngineeringSelectorDAO();
			eBFSdto = eBFSdao.getEBFavouriteSubject(student.getId());

			Map<String, Integer> favSubPriority = new HashMap<String, Integer>();
			favSubPriority.put(ApplicationConstants.FAVSUBJECTS.PHYSICS.name(), eBFSdto.getPhysics());
			favSubPriority.put(ApplicationConstants.FAVSUBJECTS.CHEMISTRY.name(), eBFSdto.getChemistry());
			favSubPriority.put(ApplicationConstants.FAVSUBJECTS.MATHEMATICS.name(), eBFSdto.getMathematics());
			favSubPriority.put(ApplicationConstants.FAVSUBJECTS.BIOLOGY.name(), eBFSdto.getBiology());
			OUT.debug("bharath EngineeringSelectorPDFReport favSubPriority:{}", favSubPriority);
			
			
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
			
			Map<String,String> app_score = new HashMap<String,String>();
			String[] keyVals=cgtResult.getAppScore().split(",");
			for(String keyVal:keyVals)
	        {
	          String[] parts = keyVal.split(":",2);
	          app_score.put(parts[0].trim(),parts[1].trim());
	        }
			
			cgtOcc = esr.getEngineeringSelectorRecommendation(student.getId(), favSubPriority);
			boolean isset1=false;
			if(cgtOcc.size()==0)
			{
				EBS_Set2Service utilService =new EBS_Set2Service();
				systemRecommendation  = utilService.getSystemRecommendation(student.getId(),true);
				OUT.debug("bharath EngineeringSelectorPDFReport systemRecommendation:{}",systemRecommendation);
				String occupationIdstr = (String) systemRecommendation.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name());
				String [] occstr=occupationIdstr.split(",");
				List<Integer> occupationIds= new ArrayList<Integer>();
//				for(int counter=0;counter< occstr.length; counter++)
//				{
//					occupationIds.add(Integer.parseInt(occstr[counter]));
//				}
				List<OccupationDTO> occlistwithfitment= (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.CGT.name());
				for(OccupationDTO occ : occlistwithfitment)
				{
					occupationIds.add(occ.getId());
				}
				OUT.debug("bharath EngineeringSelectorPDFReport occlistwithfitment:{}",occlistwithfitment);
				List<DHEdupathDTO> edupathlist= DHEdupathDAO.getDHEdupathbyOccId(occupationIds);
				OUT.debug("bharath EngineeringSelectorPDFReport edupathlist:{}",edupathlist);
				for(OccupationDTO occ:occlistwithfitment)
				{
					for(DHEdupathDTO edu:edupathlist)
					{
						if(occ.getId()==edu.getOccupationId())
						{
							occ.setDheduDTO(edu);
						}
					}
				}
				OUT.debug("bharath EngineeringSelectorPDFReport occlistwithfitment:{}",occlistwithfitment);
				Collections.sort(edupathlist, new EdupathPrioritySorter());
				OUT.debug("updated fitement edupathlist:{}",edupathlist);
				Collections.sort(occlistwithfitment, new OccupationDHPrioritySorter());
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
				EngineeringSelectorDTO engineeringSelectorDTO = new EngineeringSelectorDTO();
				List<DHEntExamDTO> entExamlist = DHEdupathDAO.getexamdescription(examList);
				engineeringSelectorDTO.setStudentId(student.getId());
				engineeringSelectorDTO.setRaisecCode(cgtResult.getPersonalityCode().trim());
				engineeringSelectorDTO.setCompleteOccList(" ");
				engineeringSelectorDTO.setFavSubPriorityList(favSubPriority.toString().trim()); 
				engineeringSelectorDTO.setShortlistOcc(" "); 
				engineeringSelectorDTO.setShortlistOccWithFavSub(" ");
				engineeringSelectorDTO.setFinalList("[]");
				engineeringSelectorDTO.setSet2(occlistwithfitment.toString().trim());			
				session = MyBatisManager.getInstance().getSession();
				EngineeringSelectorDAO dao = new EngineeringSelectorDAO();
				int result = dao.insertORUpdateStudentCGTResult(session, engineeringSelectorDTO);
				session.commit();
				
				baos = service.GeneratePDFReportSET2(student,occlistwithfitment,raiseccodeDTO,app_score,entExamlist,edupathlist);
				
				if (baos != null) {
					fileName= "Lodestar_Engineering_Branch_Selector_Report.pdf";
					fileInputStream = new ByteArrayInputStream(baos.toByteArray());
					return "success";
				} 
				
			}else
			{
			
				//if occupation list is not empty
				for(OccupationDTO _ODTO:cgtOcc)
				{
					if(_ODTO.getEngineeringPriority()>=12 && _ODTO.getEngineeringPriority()<15)
					{
						isset1= true;
						GlobalSttingDAO globalDAO = new GlobalSttingDAO();
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = globalDAO.getPropertesValueByName(globalDTO);
						webURL = globalDTO.getPropertyValue()+"/engineeringSelector/AptitudeImprovementEngineeringSelectorPDFReport";
						break;
					}
				}
				
				if(!isset1) 
				{
					baos = service.GeneratePDFReport(student,cgtOcc,raiseccodeDTO,app_score);
				}
				else if(isset1 && cgtOcc.size()<=2)
				{
					baos = service.GeneratePDFReportSET1B(student,cgtOcc,raiseccodeDTO,app_score,webURL);
				}
				else if(isset1 && cgtOcc.size()>2)
				{
					baos = service.GeneratePDFReportSET1A(student,cgtOcc,raiseccodeDTO,app_score,webURL);
				}
				
				if (baos != null) {
					fileName= "Lodestar_Engineering_Branch_Selector_Report.pdf";
					fileInputStream = new ByteArrayInputStream(baos.toByteArray());
					return "success";
				} 
			
			}
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
			
		}
		return "data";
	}
	
	
	
	public String AptitudeImprovement()
	{
		try {
			fileName="AptitudeImprovement.pdf";
			fileInputStream = new FileInputStream(new File("/opt/pdf_template/2ndpartootyreport.pdf"));
			return "success";
		
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			
		}
		return ERROR;
	
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
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


class EdupathPrioritySorter implements Comparator<DHEdupathDTO>
{
//	private static final Logger	OUT	= LoggerFactory.getLogger(EdupathPrioritySorter.class);
	public int compare(DHEdupathDTO e1, DHEdupathDTO e2)
	{			
		return Integer.valueOf(e1.getDhPriority()).compareTo(e2.getDhPriority());
		
		
	}
}
	
}
