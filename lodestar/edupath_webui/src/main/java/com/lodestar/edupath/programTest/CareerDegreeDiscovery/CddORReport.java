package com.lodestar.edupath.programTest.CareerDegreeDiscovery;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.CareerDegreeDiscovery.CareerDegreeDiscoveryDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.document.DocumentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery.CareerDegreeDiscoveryResultDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.document.service.DocumentService;


public class CddORReport extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(CddORReport.class);
	
	private String									fileName;
	private String									docURL;
//	private static final String	BOX_VIEW_SESSION_URL	= "https://view-api.box.com/1/sessions";
	
	private static final String	ACCEPT					= "Accept";
	private static final String	JSON_CONTENT_TYPE		= "application/json";
	private static final String	AUTHORIZATION			= "Authorization";
	private static final String	BOX_VIEW_API_KEY		= "Token anwlrkl14qkh60a11kobd8ofid8zm0gs";

	private static final String	ASSETS					= "assets";
	private static final String	URLS					= "urls";
	
	private InputStream fileInputStream;
	DocumentService doumentservice = new DocumentService();
	private static final int MAX_DEPTH = 1;
	OccupationDTO occDTO = new OccupationDTO();
	OccupationDAO occDAO = new OccupationDAO(); 
	DocumentDTO docDTO = new DocumentDTO();
	DocumentsDAO docDAO = new DocumentsDAO();
	StudentDetailsDTO	student				= new StudentDetailsDTO();
	StudentDetailsDAO studentDAO = new StudentDetailsDAO();
	int occupationId=0;
	
	
	
	
	public String donloadOrOne()
	{
		try
		{
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			CareerDegreeDiscoveryDAO cddDAO = new CareerDegreeDiscoveryDAO();
			CareerDegreeDiscoveryResultDTO cddDTO = new CareerDegreeDiscoveryResultDTO();
			cddDTO=cddDAO.getCareerDegreeDiscoveryResult(student.getId());
			if(cddDTO != null)
			{
				occupationId=cddDTO.getOccOne();
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
				
			}
			else
			{
				return ERROR;
			}
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String donloadOrTwo()
	{
		try
		{
			if(getUserSessionObject()==null)
			{
				return "SessionExpired";
			}
			student = studentDAO.getStudentDetailsByUserId(getUserSessionObject().getId());
			CareerDegreeDiscoveryDAO cddDAO = new CareerDegreeDiscoveryDAO();
			CareerDegreeDiscoveryResultDTO cddDTO = new CareerDegreeDiscoveryResultDTO();
			cddDTO=cddDAO.getCareerDegreeDiscoveryResult(student.getId());
			if(cddDTO != null)
			{
				occupationId=cddDTO.getOccTwo();
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
				
			}
			else
			{
				return ERROR;
			}
			
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		
		return SUCCESS;
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
