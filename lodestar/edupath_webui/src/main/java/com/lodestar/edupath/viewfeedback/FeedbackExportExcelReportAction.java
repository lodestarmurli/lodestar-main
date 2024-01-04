package com.lodestar.edupath.viewfeedback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.viewfeedback.service.ViewFeedbackService;

public class FeedbackExportExcelReportAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(FeedbackExportExcelReportAction.class);

	private static final String	EXCEL_FILE_NAME		= "Feedback_Report_Details";

	private HttpServletResponse	response;
	private String				fromDate;
	private String				toDate;
	private String				feedbackType;

	public String getReport()
	{
		OUT.debug("FeedbackExportExcelReportAction : inside getReport method");
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
			StudentDetailsDTO studentDetailsDto = new StudentDetailsDTO();
			boolean isAdmin = true;
			if (RoleTypeEnum.FACILITATOR.getWeight() == getUserSessionObject().getRoleWeight())
			{
				isAdmin = false;
				studentDetailsDto.setFacilitatorId(1);
			}
			studentDetailsDto.setFeedbackFromDate(TimeUtil.getDate(fromDate, TimeUtil.AM_PM_REPORT_FORMAT));
			studentDetailsDto.setFeedbackToDate(TimeUtil.getDate(toDate, TimeUtil.AM_PM_REPORT_FORMAT));
			studentDetailsDto.setFeedbackType(feedbackType);
			ViewFeedbackService viewFeedbackService = new ViewFeedbackService();

			File file = viewFeedbackService.reportExcel(fileName + ".xls", destination.getAbsolutePath(), isAdmin, studentDetailsDto);
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

	/**
	 * @param file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
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

	public String getFeedbackType()
	{
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType)
	{
		this.feedbackType = feedbackType;
	}

}
