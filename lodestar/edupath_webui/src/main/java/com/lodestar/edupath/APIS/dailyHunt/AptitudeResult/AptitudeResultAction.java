package com.lodestar.edupath.APIS.dailyHunt.AptitudeResult;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.dailyHunt.Util;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

public class AptitudeResultAction extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(AptitudeResultAction.class);

	private String   								Status="ERROR";
	
	private String   								Message="";
	private String									id;
	private String									or_report1="";
	private String									or_report2="";
	private String									aptitudeReportLink="";
	
	
	AptitudeResultService service = new AptitudeResultService();
	
	@Override
	public String execute() throws Exception
	{
		OUT.debug("Excute  the InterestResultReport Summary Action Of======================= {}", AptitudeResultAction.class);
		
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
						DHStudentCGTResultDTO studentCGTResultDTO = service.getDHStudentCGTResultByStudentId(studentDTO);
						OUT.debug("AptitudeResultAction studentCGTResultDTO:{}",studentCGTResultDTO);
						String occlist = studentCGTResultDTO.getOccupationIds();
						String occ1=occlist.split(",")[0];
						String occ2=occlist.split(",")[1];
						OUT.debug("AptitudeResultAction occlist:{}, occ1:{}, occ2:{}",occlist,occ1,occ2);
						String token1 = service.createToken(studentDTO.getId(),  occ1);
						String token2 = service.createToken(studentDTO.getId(),  occ2);
						String pdfToken= service.createPDFToken(studentDTO);
						studentCGTResultDTO.setOccId1(Integer.parseInt(occ1));
						studentCGTResultDTO.setOccId2(Integer.parseInt(occ2));
						studentCGTResultDTO.setToken1(token1);
						studentCGTResultDTO.setToken2(token2);
						studentCGTResultDTO.setPdfToken(pdfToken);
						OUT.debug("AptitudeResultAction token1:{}, token2:{}, pdfToken:{}",token1,token2,pdfToken);
						service.updateToken(studentCGTResultDTO);
						GlobalSettingDTO globalDTO = new GlobalSettingDTO();
						globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
						globalDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
						if (null != globalDTO.getPropertyValue() && !globalDTO.getPropertyValue().isEmpty() && token1 != null && token2 != null && pdfToken != null )
						{
							aptitudeReportLink=globalDTO.getPropertyValue()+ ApplicationProperties.getInstance().getProperty("com.DailyHunt.Aptitude.PDF.download.url")+pdfToken;
							or_report1 = globalDTO.getPropertyValue()+ ApplicationProperties.getInstance().getProperty("com.DailyHunt.Aptitude.OR.download.url")+token1;   
							or_report2 = globalDTO.getPropertyValue()+ ApplicationProperties.getInstance().getProperty("com.DailyHunt.Aptitude.OR.download.url")+token2;    
							Status="200";
							Message="successfully fetch";
							
						
						}else {
							Status="ERROR";
							Message="Internal Exception Level 1";
						}
						
						
						
						
					}
					else {
						Status="202";
						Message="Student not found";
					}
				}
				else {
					Status="202";
					Message="DHID missing";
				}
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






	public String getAptitudeReportLink() {
		return aptitudeReportLink;
	}






	public void setAptitudeReportLink(String aptitudeReportLink) {
		this.aptitudeReportLink = aptitudeReportLink;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
