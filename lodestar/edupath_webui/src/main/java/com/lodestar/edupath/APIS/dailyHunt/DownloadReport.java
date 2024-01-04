package com.lodestar.edupath.APIS.dailyHunt;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentOccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.document.DocumentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.document.service.DocumentService;
import com.lodestar.edupath.util.EncryptionDecryptionData;




public class DownloadReport extends BaseAction{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(DownloadReport.class);
	
	private String   								Status="ERROR";	
	private String   								Message="";
	private String 									token;
	private String									fileName;
	private String									docURL;
//	private static final String	BOX_VIEW_SESSION_URL	= "https://view-api.box.com/1/sessions";

	private static final String	ACCEPT					= "Accept";
	private static final String	JSON_CONTENT_TYPE		= "application/json";
	private static final String	AUTHORIZATION			= "Authorization";
	private static final String	BOX_VIEW_API_KEY		= "Token anwlrkl14qkh60a11kobd8ofid8zm0gs";

	private static final String	ASSETS					= "assets";
	private static final String	URLS					= "urls";

	
	private HttpServletRequest						request;
	
	private HttpHeaders 							httpHeaders;
	
	private DHStudentDAO studentDAO = new DHStudentDAO();
	DHStudentDAO dhStudentDAO = new DHStudentDAO();
	DocumentService doumentservice = new DocumentService();
	private static final int MAX_DEPTH = 1;
	private InputStream fileInputStream;
	OccupationDTO occDTO = new OccupationDTO();
	OccupationDAO occDAO = new OccupationDAO(); 
	DocumentDTO docDTO = new DocumentDTO();
	DocumentsDAO docDAO = new DocumentsDAO();
	DHStudentOccupationDAO studentOccDAO = new DHStudentOccupationDAO();
	DHStudentDetailsDTO studentDTO = new DHStudentDetailsDTO();
	int occupationId=0;

	public String execute()
	{
		OUT.info("Inside DownloadReport execute");
		
		
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

		// hearderResult = Util.validateHeader();
		if (hearderResult.equalsIgnoreCase("success")) {
			
				// jsonDataObject1 :{"StudentId":"4","Occupation":"193","date":"Fri Apr 03 15:08:15 IST 2020"}
				
				try 
				{
					
					if( jsonDataObject.has("StudentId") && jsonDataObject.has("Occupation") && jsonDataObject.has("date"))
					{
						
						studentDTO.setId(Integer.parseInt(jsonDataObject.getString("StudentId")));
						occupationId = Integer.parseInt(jsonDataObject.getString("Occupation"));
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
						
					}
					else
					{
						Status = "ERROR";
						Message = "Invalid Token";
						return "data";
					}
		
					
					DHStudentOccupationDTO studentoccDTO = studentOccDAO.getDHStudentOccupationbyToken(token);
					if(studentoccDTO.getStudentId() ==studentDTO.getId()) 	
					{
						docDTO = docDAO.getDocumentByOccId(occupationId);
						docURL = doumentservice.getDoucmentURL(docDTO.getDocumentPath());
						//BoxDownload.getFileFromBox(docURL) ;					
						BoxAPIConnection api = new BoxAPIConnection("m2277MKKnjdlsOUf6UXwCV1v86ePBOQl");
						BoxFile file =new BoxFile(api, docURL.trim());
						BoxFile.Info info = file.getInfo();
						File initialFile = new File(info.getName());
						FileOutputStream stream = new FileOutputStream(info.getName());
						file.download(stream);
						stream.close();
						
						fileName = info.getName();
						byte[] inFileBytes = Files.readAllBytes(initialFile.toPath());
						fileInputStream = new ByteArrayInputStream(inFileBytes);
						
						


						Status = SUCCESS;
						Message = "Report found.";
					}
					else
					{
						Status = "ERROR";
						Message ="Token not found";
						return "data";
					}
					
					
				} catch (Exception e) {
					OUT.error(ApplicationConstants.EXCEPTION, e);
					Status = "ERROR";
					Message = "Internal Exception Level 1";
				}
			}else if(hearderResult.equalsIgnoreCase("errorMethod"))
			{
				Status="204";
				Message="Invalid Method";
				return "data";
			}
			else{
				Status="204";
			Message="Header invalid ";
			return "data";
			}
			
		}
		else
		{
			Status = "ERROR";
			Message = "Token value null";
			return "data";
		}

		
		return "success";
		
		
	}
	
	
	
	 private static void listFolder(BoxFolder folder, int depth) {
	        for (BoxItem.Info itemInfo : folder) {
	            String indent = "";
	            for (int i = 0; i < depth; i++) {
	                indent += "    ";
	            }

	            System.out.println(indent + itemInfo.getName());
	            if (itemInfo instanceof BoxFolder.Info) {
	                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
	                if (depth < MAX_DEPTH) {
	                    listFolder(childFolder, depth + 1);
	                }
	            }
	        }
	    }
	

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		this.Message = message;
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
