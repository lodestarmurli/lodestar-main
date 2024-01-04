package com.lodestar.edupath.APIS.dailyHunt.AptitudeResultDownload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.dailyHunt.InterestResultReportService;
import com.lodestar.edupath.APIS.dailyHunt.InterestResultUtilService; 
import com.lodestar.edupath.APIS.dailyHunt.Util;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHEdupathDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentOccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.RaisecCodeDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEdupathDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHEntExamDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class DownloadAptitudeReportPDFAction  extends BaseAction{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(DownloadAptitudeReportPDFAction.class);
	
	private String   								Status="ERROR";
	
	private String   								Message="";
	private String 									token;
	private String									fileName;
	
	private DHStudentDetailsDTO 					studentDTO = new DHStudentDetailsDTO();
	private DownloadAptitudeReportPDFService 		downloadPDFReportService = new DownloadAptitudeReportPDFService(); 
	DHStudentOccupationDAO 							studentOccDAO = new DHStudentOccupationDAO();
	private InterestResultUtilService 				utliservice = new InterestResultUtilService();
	private Map<String, Object>				systemRecommendation	= new HashMap<String, Object>();
	
	private String 									 studentType;
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos ;
	
	

	public String execute()
	{
		OUT.info("Inside DownloadAptitudeReportPDFAction execute");
		if(token!=null || token!="")
		{
			String jsonStr=new EncryptionDecryptionData().Decrypt(token);
			String hearderResult ="";
			JSONObject jsonDataObject = new JSONObject(jsonStr.trim());
			if(jsonDataObject.has("validateHeader") && jsonDataObject.getString("validateHeader").equalsIgnoreCase("1"))
			{
				hearderResult = Util.validateGetHeader();
			}
			else
			{
				hearderResult="success";
			}

			OUT.debug("Inside DownloadAptitudeReportPDFAction execute hearderResult:{}",hearderResult);
			if (hearderResult.equalsIgnoreCase("success")) {
			
				// jsonDataObject1 :{"StudentId":"4","Stage":"1","date":"Fri Apr 03 15:08:15 IST 2020"}
				
				try 
				{
					
					if( jsonDataObject.has("StudentId") && jsonDataObject.has("studentType") && jsonDataObject.has("date"))
					{
						studentDTO.setId(Integer.parseInt(jsonDataObject.getString("StudentId")));
						studentType = jsonDataObject.getString("studentType");
						Date jsondate= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(jsonDataObject.getString("date"));
						Date d2 = new Date();
						long diff = d2.getTime() - jsondate.getTime();

						long diffSeconds = diff / 1000 % 60;
						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffDays = diff / (24 * 60 * 60 * 1000);

						if(diffMinutes > 5 && diffSeconds > 0 )
						{
							Status = "ERROR";
							Message = "Token expired";
							return "data";
						}
						
					
					}else
					{
						Status = "ERROR";
						Message = "Invalid Token";
						return "data";
					}
					
					
					if(jsonDataObject.has("studentType"))
					{
						DHStudentDAO dhStudentDAO = new DHStudentDAO();
						studentDTO=dhStudentDAO.getDHStudentByID(studentDTO);
						studentDTO.setRaisecCode(utliservice.getStudentRAISEC_Code(studentDTO.getId()));
						InterestResultReportService interestResultReportService = new InterestResultReportService();
						InterestResultUtilService utilService = new InterestResultUtilService();
						systemRecommendation  = utilService.getSystemRecommendation(studentDTO.getId(),false);
						String occupationIdstr = (String) systemRecommendation.get(ApplicationConstants.SystemRecommendation.OCCUPATION_IDS.name());
						String [] occstr=occupationIdstr.split(",");
						List<Integer> occupationIds= new ArrayList<Integer>();
						for(int counter=0;counter< occstr.length; counter++)
						{
							occupationIds.add(Integer.parseInt(occstr[counter]));
						}
						List<OccupationDTO> occlistwithfitment= (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.CGT.name());
						List<DHEdupathDTO> edupathlist= DHEdupathDAO.getDHEdupathbyOccId(occupationIds);
						for(OccupationDTO occ:occlistwithfitment)
						{
							for(DHEdupathDTO edu:edupathlist)
							{
								if(occ.getId()==edu.getOccupationId())
								{
//									edu.setOccName(occ.getName());
//									edu.setDhPriority(occ.getDhPriority());
									occ.setDheduDTO(edu);
								}
							}
						}
						Collections.sort(edupathlist, new EdupathPrioritySorter());
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
						raiseccodeDTO = raiseccodeDAO.getRaisecCode(studentDTO.getRaisecCode());
						String raiseccode=raiseccodeDTO.getRaisec();
						fileName=raiseccode.replaceAll(",", "")+".pdf";
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

						//ByteArrayOutputStream baos= generatePDFReportFileobj.GenerateFileDH(studentDetailsDTO,studentCGTResult,occupIndusMap);
						
						if(studentType.equalsIgnoreCase("TYPE1"))
						{
							OUT.debug("call to write pdf studentDTO:{}",studentDTO);
							if(studentDTO.getClassStr().equalsIgnoreCase("11") || studentDTO.getClassStr().equalsIgnoreCase("12") )
							{
								baos = downloadPDFReportService.GenerateDHPDFReport2A_11_12(studentDTO,occlistwithfitment,raiseccodeDTO,entExamlist,edupathlist,app_score);
							}
							else
							{
								baos = downloadPDFReportService.GenerateDHPDFReport2A(studentDTO,occlistwithfitment,raiseccodeDTO,entExamlist,edupathlist,app_score);
							}
							
						}else if(studentType.equalsIgnoreCase("TYPE2"))
						{
							baos = downloadPDFReportService.GenerateDHPDFReport2B(studentDTO,occlistwithfitment,raiseccodeDTO,app_score);
						}
						else
						{
							Status = "ERROR";
							Message = "Invalid Token student type";
							return "error";
						}
						if (baos != null) {
							fileInputStream = new ByteArrayInputStream(baos.toByteArray());
						} else {
							Status = "ERROR";
							Message = "Internal Exception Level 1";
							return "error";

						}
					}
					else
					{
						Status = "ERROR";
						Message = "Invalid Token";
						return "data";
					}
					
					
					
				}catch (Exception e) {
					OUT.error(ApplicationConstants.EXCEPTION, e);
					Status = "ERROR";
					Message = "Internal Exception Level 1";
				}
				
			}else if(hearderResult.equalsIgnoreCase("errorMethod"))
			{
				Status="204";
				Message="Invalid Method";
				return "data";
			}else{
				Status="204";
			Message="Header invalid ";
			return "data";
			}
		
		}else{
			Status="204";
		Message="Token null ";
		return SUCCESS;
		}
	
		return "success";
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


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
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


class EdupathPrioritySorter implements Comparator<DHEdupathDTO>
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EdupathPrioritySorter.class);
	public int compare(DHEdupathDTO e1, DHEdupathDTO e2)
	{			
		return Integer.valueOf(e1.getDhPriority()).compareTo(e2.getDhPriority());
		
		
	}
}

class OccupationPrioritySorter implements Comparator<OccupationDTO>
	{
		private static final Logger	OUT	= LoggerFactory.getLogger(OccupationPrioritySorter.class);
		public int compare(OccupationDTO o1, OccupationDTO o2)
		{			
			return Integer.valueOf(o1.getDhPriority()).compareTo(o2.getDhPriority());
			
			
		}
}

