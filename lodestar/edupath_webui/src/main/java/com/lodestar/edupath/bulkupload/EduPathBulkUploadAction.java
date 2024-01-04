package com.lodestar.edupath.bulkupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.ChanakyaStudentTest.ChanakyaStudentIntersestTestBulkUploadProcessor;
import com.lodestar.edupath.bulkupload.interest.InterestTestBulkUploadProcessor;
import com.lodestar.edupath.bulkupload.payment.PaymentBulkUploadProcessor;
import com.lodestar.edupath.bulkupload.service.EduPathBulkUploadService;
import com.lodestar.edupath.bulkupload.student.CDDStudentBulkUploadProcessor;
import com.lodestar.edupath.bulkupload.student.StudentBulkUploadProcessor;
import com.lodestar.edupath.bulkupload.studentinteresttest.StudentIntersestTestBulkUploadProcessor;
import com.lodestar.edupath.bulkupload.upload.EduPathBulkUploadProcessor;
import com.lodestar.edupath.datatransferobject.dto.BulkUploadStatusDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;

import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class EduPathBulkUploadAction extends BaseAction
{
	private static final long	serialVersionUID	= -2970691028909884029L;

	private static final Logger	OUT					= LoggerFactory.getLogger(EduPathBulkUploadAction.class);

	private File				excel;

	private String				excelContentType;

	private String				excelFileName;

	private String				moduleName;
	
	private InputStream inputStream;
	private String fileName;
	private long contentLength;

	public String show()
	{
		boolean isInProgress = false;
		try
		{
			moduleName = request.getParameter("moduleName");
			BulkUploadStatusDTO excelUploadDTO = new EduPathBulkUploadService().getExcelUploadDetails(moduleName);
			JSONObject json = new JSONObject();
			if (excelUploadDTO != null)
			{
				json.put("fileName", excelUploadDTO.getFileName());
				json.put("uploadedAt", TimeUtil.getDateFromMillis(excelUploadDTO.getUploadedAt().getTime(), ""));
				json.put("uploadedBy", excelUploadDTO.getUploadedBy());
				json.put("completedAt",
						excelUploadDTO.getCompletedAt() != null ? TimeUtil.getDateFromMillis(excelUploadDTO.getCompletedAt().getTime(), TimeUtil.REPORT_DATE_FORMAT)
								: "-");
				json.put("status", excelUploadDTO.getStatus());
				json.put("message", excelUploadDTO.getMessage());
				if (excelUploadDTO.getStatus().equalsIgnoreCase(UploadStatusEnum.INPROGRESS.getValue()))
				{
					isInProgress = true;
				}
				request.setAttribute("uploadJson", json.toString());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		request.setAttribute("isInProgress", isInProgress);
		request.setAttribute("moduleName", moduleName);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	
//	public String downloadBoardComparator() throws Exception
//	{
//		 File fileToDownload = new File("E:/workspace/lodestar/edupath_webui/src/main/webapp/thirdparty/BoardComparison.xls");
//		 
//		 
//	        inputStream = new FileInputStream(fileToDownload);
//	        fileName = fileToDownload.getName();
//	        contentLength = fileToDownload.length();
//	        return SUCCESS;
//		
//	}
	
	
	
	public String download() throws Exception
	{
		UserSessionObject usersessionObj = (UserSessionObject) request.getSession().getAttribute(
				ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty());
		if (OUT.isDebugEnabled())
		{
			OUT.debug(usersessionObj.getFullName() + ", Requesting for downloading template for: " + moduleName);
		}
		EduPathBulkUploadService excelService = new EduPathBulkUploadService();
		List<? extends Object> masterList = new ArrayList<Object>();
		String xmlFilePath = "";
		String fileName = "";

		if (moduleName.equalsIgnoreCase(ModuleEnum.FACILITATORS.getModuleName()))
		{

			xmlFilePath = "FacilitatorsMap-config.xml";
			fileName = "FacilitatorsMap";
		}
		else if (moduleName.equalsIgnoreCase(ModuleEnum.CDDSTUDENT.getModuleName()))
		{

			xmlFilePath = "bulkuploadXML/CDD-Student-config.xml";
			fileName = "CDD-Student";
		}
		else if (moduleName.equalsIgnoreCase(ModuleEnum.STUDENT.getModuleName()))
		{

			xmlFilePath = "bulkuploadXML/Student-config.xml";
			fileName = "Student";
		}
		else if (ModuleEnum.INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName))
		{
			xmlFilePath = "bulkuploadXML/Interest-Test-config.xml";
			fileName = "InterestTest";
		}
		
		//start sasedeve Edited bt vyankatesh on 3-2-2017
		else if (ModuleEnum.PAYMENT.getModuleName().equalsIgnoreCase(moduleName))
		{
			//System.out.println("=============== i came here");
			xmlFilePath = "bulkuploadXML/Payment-config.xml";
			fileName = "Payment";
		}
		//End sasedeve Edited bt vyankatesh on 3-2-2017
		//Start sasedeve Edited bt vyankatesh on 3-5-2017
		else if (ModuleEnum.STUDENT_INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName))
		{
			//System.out.println("=============== i came here");
			xmlFilePath = "bulkuploadXML/Student-Intersest-Test-config.xml";
			fileName = "STUDENT_INTEREST_TEST";
		}
		//End sasedeve Edited bt vyankatesh on 13-5-2017
		List<NetValenceExcelRowObject> excelRowsList = excelService.prepareTemplate(masterList, moduleName);
		File file = null;
		file = excelService.generateXLSFile(excelRowsList, xmlFilePath, fileName, moduleName);
		sendFile(file);
		return null;
	}

	public String upload()
	{
		UserSessionObject sessionObj = (UserSessionObject) request.getSession().getAttribute(
				ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty());
		OUT.info(sessionObj.getFullName() + ", In upload action=" + excelFileName + ", moduleName=" + moduleName + ", file::" + excel);

		String threadName = Thread.currentThread().getName();
		Thread.currentThread().setName(moduleName + " Excel Upload");

		String xmlFilePath = "";
		if (moduleName.equalsIgnoreCase(ModuleEnum.FACILITATORS.getModuleName()))
		{
			xmlFilePath = "FacilitatorsMap-config.xml";
			EduPathBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
					sessionObj.getId(), sessionObj.getLoginId());
		}
		else if (moduleName.equalsIgnoreCase(ModuleEnum.CDDSTUDENT.getModuleName()))
		{
			xmlFilePath = "bulkuploadXML/CDD-Student-config.xml";
			CDDStudentBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
					sessionObj.getId(), sessionObj.getLoginId());
		}
		else if (moduleName.equalsIgnoreCase(ModuleEnum.STUDENT.getModuleName()))
		{
			xmlFilePath = "bulkuploadXML/Student-config.xml";
			StudentBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
					sessionObj.getId(), sessionObj.getLoginId());
		}
		else if (ModuleEnum.INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName))
		{
			xmlFilePath = "bulkuploadXML/Interest-Test-config.xml";
			InterestTestBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
					sessionObj.getId(), sessionObj.getLoginId());
		}
		else if (ModuleEnum.PAYMENT.getModuleName().equalsIgnoreCase(moduleName))
		{
			xmlFilePath = "bulkuploadXML/Payment-config.xml";
			PaymentBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
					sessionObj.getId(), sessionObj.getLoginId());
		}
		//start sasedeve Edited bt vyankatesh on 13-5-2017
		else if (ModuleEnum.STUDENT_INTEREST_TEST.getModuleName().equalsIgnoreCase(moduleName))
		{
			xmlFilePath = "bulkuploadXML/Student-Intersest-Test-config.xml";
			StudentIntersestTestBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
			sessionObj.getId(), sessionObj.getLoginId());
		}
		//End sasedeve Edited bt vyankatesh on 13-5-2017
		else if (ModuleEnum.CHANAKYA_STUDENT_TEST.getModuleName().equalsIgnoreCase(moduleName))
		{
			xmlFilePath = "bulkuploadXML/Chanakya-Student-Test-config.xml";
			OUT.debug("bharath inside  EduPathBulkUploadAction upload() CHANAKYA_STUDENT_TEST ");
			ChanakyaStudentIntersestTestBulkUploadProcessor.getInstance().uploadExcelTemplate(excel, excelFileName, moduleName, xmlFilePath, sessionObj.getFullName(),
			sessionObj.getId(), sessionObj.getLoginId());
		}


		try
		{
			PrintWriter writer = response.getWriter();
			OUT.debug("Getting record from bulkuploadstatus table for modulename: {}", moduleName);
			// OUT.debug("Data {}", HolidaysBulkUploadProcessor.jsonObj);
			BulkUploadStatusDTO excelUploadDTO = new EduPathBulkUploadService().getExcelUploadDetails(moduleName);
			if (excelUploadDTO != null)
			{
				JSONObject json = new JSONObject();
				json.put("fileName", excelUploadDTO.getFileName());
				json.put("uploadedAt", TimeUtil.getDateFromMillis(excelUploadDTO.getUploadedAt().getTime(), TimeUtil.REPORT_DATE_FORMAT));
				json.put("uploadedBy", excelUploadDTO.getUploadedBy());
				json.put("completedAt",
						excelUploadDTO.getCompletedAt() != null ? TimeUtil.getDateFromMillis(excelUploadDTO.getCompletedAt().getTime(), TimeUtil.REPORT_DATE_FORMAT)
								: "-");
				json.put("status", excelUploadDTO.getStatus());
				json.put("message", excelUploadDTO.getMessage());
				writer.write(json.toString());
				OUT.debug("upload details Json object values: {}", json.toString());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		Thread.currentThread().setName(threadName);
		return null;
	}

	public String view() throws Exception
	{
		try
		{
			OUT.debug("Requested for downloading processed excel file");
			File file = new EduPathBulkUploadService().showProcessedFile(request.getParameter("fileName"));
			OUT.debug("Sending error file : " + file.getName());
			sendFile(file);
			OUT.debug("File :" + file.getName() + " sent");
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	public String check()
	{
		// boolean inProgress = ContractorBulkUploadProcessor.getInstance().isInProgress();
		OUT.debug("Checking upload Status for :" + moduleName);
		try
		{
			PrintWriter writer = response.getWriter();
			response.setContentType("text/plain");
			JSONObject json = new JSONObject();
			BulkUploadStatusDTO excelUploadDTO = new EduPathBulkUploadService().getExcelUploadDetails(moduleName);
			if (excelUploadDTO != null)
			{
				json.put("fileName", excelUploadDTO.getFileName());
				json.put("uploadedAt", TimeUtil.getDateFromMillis(excelUploadDTO.getUploadedAt().getTime(), TimeUtil.REPORT_DATE_FORMAT));
				json.put("uploadedBy", excelUploadDTO.getUploadedBy());
				json.put("completedAt",
						excelUploadDTO.getCompletedAt() != null ? TimeUtil.getDateFromMillis(excelUploadDTO.getCompletedAt().getTime(), TimeUtil.REPORT_DATE_FORMAT)
								: "-");
				json.put("status", excelUploadDTO.getStatus());
				json.put("message", excelUploadDTO.getMessage());
				writer.write(json.toString());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}

	/**
	 * @param file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */

	protected void sendFile(File file) throws IOException, FileNotFoundException
	{
		String fileName = file.getName().replace(" ", ":");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);

		// render the output file
		ServletOutputStream out = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		byte buff[] = new byte[2048];
		while (fis.read(buff) != -1)
		{
			out.write(buff);
		}
		fis.close();
		out.close();
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public File getExcel()
	{
		return excel;
	}

	public void setExcel(File excel)
	{
		this.excel = excel;
	}

	public String getExcelContentType()
	{
		return excelContentType;
	}

	public void setExcelContentType(String excelContentType)
	{
		this.excelContentType = excelContentType;
	}

	public String getExcelFileName()
	{
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName)
	{
		this.excelFileName = excelFileName;
	}
	 public long getContentLength() {
	        return contentLength;
	    }
	 
	    public String getFileName() {
	        return fileName;
	    }
	 
	    public InputStream getInputStream() {
	        return inputStream;
	    }  
	

}
