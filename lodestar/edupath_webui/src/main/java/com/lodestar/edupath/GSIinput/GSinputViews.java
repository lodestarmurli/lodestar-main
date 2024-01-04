package com.lodestar.edupath.GSIinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.TipsAndSuggestionService.TipsAndSuggestionService;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.ViewGSIputsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.viewfeedback.service.ViewFeedbackService;



public class GSinputViews extends BaseAction{
	
	private static final long		serialVersionUID	= 1L;
	private static final Logger		OUT					= LoggerFactory.getLogger(GSinputViews.class);

	private String					feedbackType;
	private String					fromDate;
	private String					toDate;
	private List<ViewGSIputsDTO>	studentList			= new ArrayList<ViewGSIputsDTO>();
	private boolean					admin = true;
	
	private static final String	EXCEL_FILE_NAME		= "GSInputs_Report_Details";

	private HttpServletResponse	response;
	
	
	
	public String execute()
	{
		OUT.debug("GSinputViews : inside execute method");
		try
		{

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION);
		}
		return SUCCESS;
	}
	
	
	
	public String getGSInputsByFilter()
	{
		OUT.debug("GSinputViews : inside getGSInputsByFilter method");
		try
		{
			OUT.debug("feedbackType : {} , fromDate : {}, toDate : {}", feedbackType, fromDate, toDate);
			ViewGSIputsDTO gsinputsdto = new ViewGSIputsDTO();
			UserSessionObject userSessionObject = getUserSessionObject();
			if (RoleTypeEnum.FACILITATOR.getWeight() == userSessionObject.getRoleWeight())
			{
				admin = false;
			}
			gsinputsdto.setGsinputFromDate(TimeUtil.getDate(fromDate, TimeUtil.AM_PM_REPORT_FORMAT));
			gsinputsdto.setGsinputToDate(TimeUtil.getDate(toDate, TimeUtil.AM_PM_REPORT_FORMAT));
			
			studentList = new TipsAndSuggestionService().getGSIputsList(gsinputsdto, userSessionObject.getId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showGSInputsContent";
	}
	
	
	
	public String getReport()
	{
		OUT.debug("GSinputViews : inside getReport method");
		try
		{
			OUT.debug("Search value : {} : {} : {}", fromDate, toDate, feedbackType);

			String fileName = File.separator + EXCEL_FILE_NAME + "_" + System.currentTimeMillis();
			String currentDir = System.getProperty("java.io.tmpdir");
			if (currentDir.indexOf("bin") > 0)
			{
				currentDir = currentDir.substring(0, currentDir.indexOf("bin"));
			}
			currentDir += "upload_template";
			File destination = new File(currentDir + File.separator);
			if (!destination.exists())
				destination.mkdirs();

			OUT.debug("DestinationPath: {}", destination.getAbsolutePath());
			ViewGSIputsDTO gsinputsdto = new ViewGSIputsDTO();
			boolean isAdmin = true;
			
			gsinputsdto.setGsinputFromDate(TimeUtil.getDate(fromDate, TimeUtil.AM_PM_REPORT_FORMAT));
			gsinputsdto.setGsinputToDate(TimeUtil.getDate(toDate, TimeUtil.AM_PM_REPORT_FORMAT));
			
			TipsAndSuggestionService viewtipsandsuggestionsService = new TipsAndSuggestionService();

			File file = viewtipsandsuggestionsService.reportExcel(fileName + ".xls", destination.getAbsolutePath(), gsinputsdto);
			if (null != file)
			{
				sendFile(file);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	
	protected void sendFile(File file) throws IOException, FileNotFoundException
	{
		String fileName = file.getName().replace(" ", ":");
		String contentType = FilenameUtils.getExtension(fileName);
		OUT.debug("File name: {} contentType:{}", fileName, contentType);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
		response.setContentLength((int) file.length());

		// render the output file
		ServletOutputStream out = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		byte buff[] = new byte[2048];
		while (fis.read(buff) != -1)
		{
			out.write(buff);
		}
		fis.close();
		out.flush();
		out.close();
	}
	
	@Override
	public void setServletResponse(HttpServletResponse arg0)
	{
		setResponse(arg0);
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getFeedbackType()
	{
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType)
	{
		this.feedbackType = feedbackType;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public List<ViewGSIputsDTO> getStudentList()
	{
		return studentList;
	}

	public void setStudentList(List<ViewGSIputsDTO> studentList)
	{
		this.studentList = studentList;
	}

	public boolean isAdmin()
	{
		return admin;
	}

	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	
	
}
