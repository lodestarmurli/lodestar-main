package com.lodestar.edupath.report.export;

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

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.ModuleEnum;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.report.export.service.StudentReportService;
import com.lodestar.edupath.util.ApplicationProperties;

public class EdupathExportReportAction extends BaseAction
{

	private static final long	serialVersionUID	= -8670202956997548614L;
	private static Logger		OUT					= LoggerFactory.getLogger(EdupathExportReportAction.class);

	private static final String	SUMMARY_SCREEN		= "Student Report";

	private static final String	EXCEL_FILE_NAME		= "Student_Report_Details";

	private int					filterRecord;

	private HttpServletRequest	request;

	private HttpServletResponse	response;

	private String				moduleName;

	private String				searchValue;

	private String				reportFileType;

	public String getReport() throws Exception
	{

		StudentReportService service = new StudentReportService();

		// String caption = request.getParameter("reportHeader");
		// OUT.debug("caption: {}", caption);
		List<String> columnList = new ArrayList<String>();
		String fileName = File.separator + EXCEL_FILE_NAME + "_" + System.currentTimeMillis();
		String destFilePath = "";
		// File destination = new File(System.getenv("CATALINA_HOME") + "/temp");
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
		ExcelGeneratorEngine excelGeneratorEngine = new ExcelGeneratorEngine();
		if (moduleName.equals(ModuleEnum.STUDENT.getModuleName()))
		{
			List<StudentDetailsDTO> reportList = null;
			reportList = service.getStudentReportList(searchValue, filterRecord);
			prepareColumnLists(columnList);
			try
			{
				File file = null;
				if (reportFileType.equals("Pdf"))
				{
					destFilePath = fileName + ".pdf";

				}
				else if (reportFileType.equals("Excel"))
				{
					destFilePath = fileName + ".xls";
					file = excelGeneratorEngine.reportExcel(reportList, columnList, getReportHeading(), destFilePath, destination.getAbsolutePath(), moduleName);
				}
				if (null != file)
				{
					sendFile(file);
				}
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		// finally
		// {
		// if (outStream != null)
		// {
		// outStream.close();
		// }
		// }
		return null;
	}

	/**
	 * @param columnList
	 */
	private void prepareColumnLists(List<String> columnList)
	{
		ApplicationProperties propInstance = ApplicationProperties.getInstance();

		columnList.add(propInstance.getProperty("com.edupath.report.student.loginid"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.studentname"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.type"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.school"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.class"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.section"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.session1"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.session2"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.session3"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.venue"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.venue.other"));
		columnList.add(propInstance.getProperty("com.edupath.report.student.dueAmonut"));
		

	}

	/**
	 * @return
	 * @throws Exception
	 */
	private String getReportHeading() throws Exception
	{
		String heading = SUMMARY_SCREEN;
		StringBuilder reportHeader = new StringBuilder();
		reportHeader.append(heading).append("#@# @#@");
		return reportHeader.toString();
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
		if (contentType.equals("xlsx"))
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		else if (contentType.equals("xls"))
			response.setContentType("application/vnd.ms-excel");
		else if (contentType.equals("pdf"))
			response.setContentType("application/pdf");
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

	@Override
	public void setServletRequest(HttpServletRequest arg0)
	{
		this.setRequest(arg0);
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

	public String getSearchValue()
	{
		return searchValue;
	}

	public void setSearchValue(String searchValue)
	{
		this.searchValue = searchValue;
	}

	public String getReportFileType()
	{
		return reportFileType;
	}

	public void setReportFileType(String reportFileType)
	{
		this.reportFileType = reportFileType;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public static void main(String[] args) throws Exception
	{
		EdupathExportReportAction assigned = new EdupathExportReportAction();
		assigned.getReport();
	}

	public int getFilterRecord()
	{
		return filterRecord;
	}

	public void setFilterRecord(int filterRecord)
	{
		this.filterRecord = filterRecord;
	}
}
