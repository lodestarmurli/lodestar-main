package com.lodestar.edupath.APIS.chanakya;


import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCodeDescription;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

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
					ChanakyaStudentDetailsDTO studentDTO = service.getStudentByCHNKID(id.trim());
					
					if (studentDTO != null) 
					{
						studentDTO.setRaisecCode(utliservice.getStudentRAISEC_Code(studentDTO.getId()));
						if(studentDTO.getRaisecCode()==null || studentDTO.getRaisecCode()=="")
						{
							Status="202";
							Message="RaiseCode not generated. Student must answer 33 Questions.";
							interestreportlink = "";
							return SUCCESS;
						}
					

						String pdfToken= service.createPDFToken(studentDTO.getId());
						
						ChanakyaStudentOccupationDTO studentOccDTO = new ChanakyaStudentOccupationDTO();
						studentOccDTO.setStudentId(studentDTO.getId());
						studentOccDTO.setPdfToken(pdfToken);
						studentOccDTO.setPersonalityCode(studentDTO.getRaisecCode());
						int result =service.insertORUpdateStudentOcc(studentOccDTO);
						
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty())
						{
						interestreportlink =globalDTO.getPropertyValue()+ApplicationProperties.getInstance().getProperty("com.Chanakya.pdf.download.url")+ pdfToken;
						}
						String personalityName="";
						String personalityDescription="";
						for(RaisecCode RCenum: RaisecCode.values())
						{
							if(studentDTO.getRaisecCode().trim().equalsIgnoreCase(RCenum.name()))
							{
								personalityName=RCenum.Value();
							}
						}
						for(RaisecCodeDescription RCenum: RaisecCodeDescription.values())
						{
							if(studentDTO.getRaisecCode().trim().equalsIgnoreCase(RCenum.name()))
							{
								personalityDescription=RCenum.Value();
							}
						}
						Status = "200";
						Message = "Congratulations on completing the iCAN test. Your most dominant Vocational Personality is - "+personalityName+ 
								".\n"+personalityDescription+ 
								"\nPlease get in touch with a Chanakya University Counsellor to learn more about careers and courses suitable for your Vocational Personality type." ; 
								
						
						
						
						
					} else {
						OUT.info("Chanakyat ID not found in database");
						interestreportlink = "";
						Status = "404";
						Message = "Student not found";
					}

				}
				else
				{
					OUT.info("ChanakyaStudent ID NULL");
					interestreportlink="";
					Status="404";
					Message="Chanakya student ID null";
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
