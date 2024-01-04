package com.lodestar.edupath.APIS.dailyHunt;


import com.lodestar.edupath.base.BaseAction;

import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterestResultReport extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(InterestResultReport.class);
	
	private String									id;
	private String   								Status="ERROR";
	
	private String   								Message="";
	private String									or_report1="";
	private String									or_report2="";
	private String									interestreportlink="";
	
	private InterestResultUtilService 				utliservice; 
	private InterestResultReportService				service;
	
	private DHStudentDAO studentDAO = new DHStudentDAO();
	
	DHStudentDAO dhStudentDAO = new DHStudentDAO();
	
	public InterestResultReport()
	{
		utliservice =new InterestResultUtilService();
		service = new InterestResultReportService();
	}
	

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the InterestResultReport Summary Action Of======================= {}", InterestResultReport.class);
		
		try
		{
			String hearderResult = Util.validateGetHeader();
			OUT.debug("hearderResult Receive=>"+hearderResult);
			if (hearderResult.equalsIgnoreCase("success")) 
			{
				OUT.debug("id Receive=>"+id);
				if(id!=null && !getId().equals("") )
				{
					OUT.debug("StudentID Receive=>"+id);
					DHStudentDetailsDTO studentDTO = service.getStudentByDHID(id.trim());
					
					if (studentDTO != null) 
					{
						studentDTO.setRaisecCode(utliservice.getStudentRAISEC_Code(studentDTO.getId()));
						if(studentDTO.getRaisecCode()==null || studentDTO.getRaisecCode()=="")
						{
							Status="202";
							Message="RaiseCode not generated. Student must answer 60 Questions.";
							or_report1 = "";
							or_report2 = "";
							interestreportlink = "";
							return SUCCESS;
						}
						
						List<OccupationDTO> occlistwithfitment = service.getOccupationlist(studentDTO);
						
						String occupationsId = null;
						for (OccupationDTO occupationRating : occlistwithfitment) 
						{
							if (occupationsId != null) {
								occupationsId += ",";
							} else {
								occupationsId = new String();
							}
							occupationsId += occupationRating.getId();
						}
						
						String token1 = service.createToken(studentDTO.getId(),  occlistwithfitment.get(0).getId());
						String token2 = service.createToken(studentDTO.getId(),  occlistwithfitment.get(1).getId());
						String pdfToken= service.createPDFToken(studentDTO.getId());
						
						DHStudentOccupationDTO studentOccDTO = new DHStudentOccupationDTO();
						studentOccDTO.setStudentId(studentDTO.getId());
						studentOccDTO.setOccList(occupationsId);
						studentOccDTO.setOcc1Id(occlistwithfitment.get(0).getId());
						studentOccDTO.setOcc2Id(occlistwithfitment.get(1).getId());
						studentOccDTO.setToken1(token1);
						studentOccDTO.setToken2(token2);
						studentOccDTO.setPdfToken(pdfToken);
						studentOccDTO.setPersonalityCode(studentDTO.getRaisecCode());
						int result =service.insertORUpdateStudentOcc(studentOccDTO);
						
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
						{
						
						or_report1 = globalDTO.getPropertyValue()+ ApplicationProperties.getInstance().getProperty("com.DailyHunt.OR.download.url")+token1;
						or_report2 = globalDTO.getPropertyValue()+ApplicationProperties.getInstance().getProperty("com.DailyHunt.OR.download.url")+token2;
						interestreportlink =globalDTO.getPropertyValue()+ApplicationProperties.getInstance().getProperty("com.DailyHunt.pdf.download.url")+ pdfToken;
						
						}
						Status = "200";
						Message = "successfully fetch";
						
						
						
						
					} else {
						OUT.info("DHStudent ID not found in database");
						or_report1 = "";
						or_report2 = "";
						interestreportlink = "";
						Status = "404";
						Message = "Student not found";
					}

				}
				else
				{
					OUT.info("DHStudent ID NULL");
					or_report1="";
					or_report2="";
					interestreportlink="";
					Status="404";
					Message="DailyHunt student ID null";
				}
			}else if(hearderResult.equalsIgnoreCase("errorMethod"))
			{
				Status="204";
				Message="Invalid Method";
				return SUCCESS;
			}
			else{
				Status="204";
			Message="Header invalid ";
			return SUCCESS;
			}
		
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			Status="ERROR";
			Message="Internal Exception Level 1";
		}
		
		
		
		
		return SUCCESS;
	}
	
	
	
	
	
	
	private boolean isEmailIdValid(String email)
	{
		
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
		Pattern pattern = Pattern.compile(regex);
		if (null == email || email.isEmpty())
		{
			
			return false;
		}
		if (!email.isEmpty() && !pattern.matcher(email).matches())
		{
			
			return false;
		}
		return true;
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

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getOr_report1() {
		return or_report1;
	}


	public void setOr_report1(String or_report1) {
		this.or_report1 = or_report1;
	}


	public String getOr_report2() {
		return or_report2;
	}


	public void setOr_report2(String or_report2) {
		this.or_report2 = or_report2;
	}


	public String getInterestreportlink() {
		return interestreportlink;
	}


	public void setInterestreportlink(String interestreportlink) {
		this.interestreportlink = interestreportlink;
	}
	

	
	
}
