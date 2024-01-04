package com.lodestar.edupath.APIS.client; 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentOccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.RaisecCodeDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientRaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RaisecCode;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants.CLIENTNAME;

public class DownloadPDFReportAction extends BaseAction{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(DownloadPDFReportAction.class);
	
	private String   								Status="ERROR";
	
	private String   								Message="";
	private String 									token;
	private String									fileName;
	
	private ClientStudentDetailsDTO 					studentDTO = new ClientStudentDetailsDTO();
	private DownloadPDFReportService 				downloadPDFReportService = new DownloadPDFReportService(); 
	ClientStudentOccupationDAO 							studentOccDAO = new ClientStudentOccupationDAO();
	private InterestResultUtilService 				utliservice = new InterestResultUtilService();
	
	int stage;
	private InputStream fileInputStream;
	private ByteArrayOutputStream baos ;
	
	public String execute()
	{
		OUT.info("Inside DownloadPDFReportAction execute");
		if(token!=null || token!="")
		{
			String jsonStr=new EncryptionDecryptionData().Decrypt(token);
			String hearderResult_partnerName ="";
			JSONObject jsonDataObject = new JSONObject(jsonStr.trim());
			OUT.debug("bharath DownloadPDFReportAction jsonDataObject:{}",jsonDataObject);
			if(jsonDataObject.has("validateHeader") && jsonDataObject.getString("validateHeader").equalsIgnoreCase("1"))
			{
				hearderResult_partnerName = Util.validateGetHeader();
			}
			else
			{
				hearderResult_partnerName="success";
			}

			OUT.debug("bharath Inside DownloadPDFReportAction execute hearderResult:{}",hearderResult_partnerName);
			if (!hearderResult_partnerName.equalsIgnoreCase("error") && !hearderResult_partnerName.equalsIgnoreCase("errorMethod")) {
			
				// jsonDataObject1 :{"StudentId":"4","Stage":"1","date":"Fri Apr 03 15:08:15 IST 2020"}
				
				try 
				{
					
					if( jsonDataObject.has("StudentId") && jsonDataObject.has("Stage") && jsonDataObject.has("date"))
					{
						studentDTO.setId(Integer.parseInt(jsonDataObject.getString("StudentId")));
						stage = Integer.parseInt(jsonDataObject.getString("Stage"));
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
					
					
					if(stage==1)
					{
						
						ClientStudentDAO dhStudentDAO = new ClientStudentDAO();
						studentDTO=dhStudentDAO.getDHStudentByID(studentDTO);
						OUT.debug("bharath DownloadPDFreportAction studentDTO:{} ",studentDTO);
						//DHStudentOccupationDTO studentoccDTO = studentOccDAO.getDHStudentOccupationByStudentId(studentDTO.getId());
						studentDTO.setRaisecCode(utliservice.getStudentRAISEC_Code(studentDTO.getId()));
						InterestResultReportService interestResultReportService = new InterestResultReportService();
						List<OccupationDTO> occlistwithfitment = interestResultReportService.getOccupationlist(studentDTO);
						ClientRaisecCodeDTO raiseccodeDTO = new ClientRaisecCodeDTO();
						RaisecCodeDAO raiseccodeDAO = new RaisecCodeDAO();
						raiseccodeDTO = raiseccodeDAO.getRaisecCodeForClient(studentDTO.getRaisecCode());
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
						
						if(studentDTO.getClassStr().equalsIgnoreCase("8") || studentDTO.getClassStr().equalsIgnoreCase("9") || studentDTO.getClassStr().equalsIgnoreCase("10") || studentDTO.getClassStr().equalsIgnoreCase("11") || studentDTO.getClassStr().equalsIgnoreCase("12") )
						{
//							OUT.debug("bharath DownloadPDFReportAction CLIENTNAME.UNIFORMJUNCTION.getName():{}",CLIENTNAME.UNIFORMJUNCTION.getName());
//							OUT.debug("bharath DownloadPDFReportAction CLIENTNAME comparision {}",CLIENTNAME.UNIFORMJUNCTION.getName().equalsIgnoreCase(studentDTO.getClassName()));
//							if(studentDTO.getClassName().equalsIgnoreCase(CLIENTNAME.UNIFORMJUNCTION.getName()))
//							{
								baos = downloadPDFReportService.GenerateUFJStage1Report(studentDTO,occlistwithfitment,raiseccodeDTO);
//							}
//							CLIENTNAME.getName().equals(studentDTO.)
						}else if(studentDTO.getClassStr().equalsIgnoreCase("12 plus"))
						{
//							if(studentDTO.getClassName().equalsIgnoreCase(CLIENTNAME.UNIFORMJUNCTION.getName()))
//							{
								baos = downloadPDFReportService.GenerateUFJStage1ReportTwelve(studentDTO,occlistwithfitment,raiseccodeDTO);
//							}
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
				
			}else if(hearderResult_partnerName.equalsIgnoreCase("errorMethod"))
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


	public int getStage() {
		return stage;
	}


	public void setStage(int stage) {
		this.stage = stage;
	}


	public InputStream getFileInputStream() {
		return fileInputStream;
	}


	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}


}
