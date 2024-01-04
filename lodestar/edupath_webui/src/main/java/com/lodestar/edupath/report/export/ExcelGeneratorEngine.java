package com.lodestar.edupath.report.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.VenueTypeEnum;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;

public class ExcelGeneratorEngine
{
	private static final Logger	OUT					= LoggerFactory.getLogger(ExcelGeneratorEngine.class);

	private static final String	FILTER_DATE_FORMAT	= "MM/dd/yyyy";

	private CellStyle			dateCellStyle;

	private CellStyle			cellStyle;

	private HSSFWorkbook		workbook;

	private Sheet				sheet;

	private Row					row;

	private Cell				cell;

	private int					rowIndex;

	private HSSFCreationHelper	createHelper;

	public void createWorkBook(String sheetName)
	{
		try
		{
			this.workbook = new HSSFWorkbook();
			this.createHelper = workbook.getCreationHelper();
			this.sheet = this.workbook.createSheet(sheetName);
			this.sheet.setDisplayGridlines(false);
			createCellStyle();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	private void createCellStyle()
	{
		cellStyle = this.workbook.createCellStyle();
		HSSFFont bold;
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		bold = this.workbook.createFont();
		bold.setFontName("Verdana");
		bold.setFontHeightInPoints((short) 8);
		cellStyle.setFont(bold);

		// Date cell style
		dateCellStyle = this.workbook.createCellStyle();
		dateCellStyle.cloneStyleFrom(cellStyle);
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat(FILTER_DATE_FORMAT));
	}

	private void createHeaders(List<String> headerList)
	{
		if (this.workbook != null)
		{
			HSSFFont bold;
			HSSFCellStyle headerStyle = this.workbook.createCellStyle();

			headerStyle = this.workbook.createCellStyle();
			headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			int i = 1;
			bold = this.workbook.createFont();
			bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			bold.setColor(HSSFColor.WHITE.index);
			bold.setFontHeightInPoints((short) 8);
			bold.setFontName("Verdana");
			headerStyle.setFont(bold);
			row = this.sheet.createRow(rowIndex++);
			for (int j = 0; j < headerList.size(); j++)
				setCellValuesForRow(headerList.get(j), headerStyle, i++);
		}
	}

	private void createCaptionCell(String caption, List<String> columnList)
	{
		if (this.workbook != null)
		{
			createRow();
			HSSFFont bold;
			HSSFCellStyle captionStyle = this.workbook.createCellStyle();

			captionStyle = this.workbook.createCellStyle();
			captionStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			captionStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			bold = this.workbook.createFont();
			bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			bold.setColor(HSSFColor.WHITE.index);
			bold.setFontHeightInPoints((short) 8);
			bold.setFontName("Verdana");
			captionStyle.setFont(bold);
			int i = 1;

			createRow();
			cell = row.createCell(i++);
			cell.setCellValue(caption);
			cell.setCellStyle(captionStyle);
			for (int rowNumber = 1; rowNumber < columnList.size(); rowNumber++)
			{
				this.sheet.setColumnWidth(rowNumber, (256 * 30));
				setCellValuesForRow(null, captionStyle, i++);
			}
		}
	}

	private void createEmptySummaryRow()
	{
		setCellValuesForRow("No records to display", cellStyle, 1);
	}

	public void createRow()
	{
		if (this.sheet != null)
		{
			row = this.sheet.createRow(rowIndex++);
		}
	}

	public void setCellValuesForRow(Object cellVal, CellStyle currentCellStyle, int columnIndex)
	{
		if (this.sheet != null)
		{
			cell = row.createCell(columnIndex);
			if (cellVal instanceof String)
			{
				cell.setCellValue((String) cellVal);
			}
			else if (cellVal instanceof Integer)
			{
				cell.setCellValue((Integer) cellVal);
			}
			else if (cellVal instanceof Long)
			{
				cell.setCellValue((Long) cellVal);
			}
			else if (cellVal instanceof Double)
			{
				cell.setCellValue((Double) cellVal);
			}
			else if (cellVal instanceof Boolean)
			{
				cell.setCellValue((Boolean) cellVal);
			}
			else if (cellVal instanceof Date)
			{
				cell.setCellValue((Date) cellVal);
			}
			else if (cellVal != null)
			{
				cell.setCellValue(cellVal.toString());
			}
			cell.setCellStyle(currentCellStyle);
		}
	}

	private File writeToWorkBook(String destFilePath, String destinationPath)
	{
		File file = null;
		FileOutputStream out = null;
		try
		{
			StringBuilder path = new StringBuilder(destinationPath + File.separator);
			File dirs = new File(path.toString());
			if (!dirs.exists())
			{
				dirs.mkdirs();
			}
			file = new File(path.append(File.separator).append(destFilePath).toString());
			if (!file.exists())
			{
				file.createNewFile();
			}

			out = new FileOutputStream(file);
			this.workbook.write(out);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.flush();
					out.close();
				}
				catch (IOException e)
				{
					OUT.error("Exception", e);
				}
			}
		}
		return file;
	}

	public File reportExcel(List<StudentDetailsDTO> reportList, List<String> columnList, String headerName, String destFilePath, String destinationPath,
			String moduleName)
	{
		createWorkBook("Student_Report_Details");
		createCaptionCell("Student Report", columnList);
		boolean isValidSummary = false;
		int columnIndex = 0;
		if (null != reportList && reportList.size() > 0 && !reportList.isEmpty())
		{
			createRow();
			createHeaders(columnList);
			// Iterate the List to add rows

			for (StudentDetailsDTO studentDetailsDTO : reportList)
			{
				columnIndex = 1;
				createRow();
				setCellValuesForRow(studentDetailsDTO.getLoginId(), cellStyle, columnIndex++);
				setCellValuesForRow(studentDetailsDTO.getName(), cellStyle, columnIndex++);
				setCellValuesForRow(studentDetailsDTO.getStudentType() != null ? studentDetailsDTO.getStudentType().getText() : "", cellStyle, columnIndex++);
				setCellValuesForRow(studentDetailsDTO.getSchoolName(), cellStyle, columnIndex++);
				setCellValuesForRow(studentDetailsDTO.getClassName(), cellStyle, columnIndex++);
				setCellValuesForRow(studentDetailsDTO.getSection() != null ? studentDetailsDTO.getSection() : "", cellStyle, columnIndex++);

				if (null != studentDetailsDTO.getSeDetailsDTO().getSession1Date())
				{
					setCellValuesForRow(TimeUtil.getDateFromMillis(studentDetailsDTO.getSeDetailsDTO().getSession1Date().getTime(), TimeUtil.REPORT_DATE_FORMAT),
							cellStyle, columnIndex++);
					setCellValuesForRow(TimeUtil.getDateFromMillis(studentDetailsDTO.getSeDetailsDTO().getSession2Date().getTime(), TimeUtil.REPORT_DATE_FORMAT),
							cellStyle, columnIndex++);
					if(studentDetailsDTO.getSeDetailsDTO()!=null) {
						setCellValuesForRow(TimeUtil.getDateFromMillis(studentDetailsDTO.getSeDetailsDTO().getSession3Date().getTime(), TimeUtil.REPORT_DATE_FORMAT),
							cellStyle, columnIndex++);
					}
					else {
						setCellValuesForRow("-", cellStyle, columnIndex++);
					}
				}
				else
				{
					setCellValuesForRow("-", cellStyle, columnIndex++);
					setCellValuesForRow("-", cellStyle, columnIndex++);
					setCellValuesForRow("-", cellStyle, columnIndex++);
				}

				if (null != studentDetailsDTO.getSeDetailsDTO().getVenue() && !studentDetailsDTO.getSeDetailsDTO().getVenue().isEmpty())
				{
					if (studentDetailsDTO.getSeDetailsDTO().getVenue().equalsIgnoreCase(VenueTypeEnum.ATOFFICE.getValue()))
					{
						setCellValuesForRow(studentDetailsDTO.getSeDetailsDTO().getVenue(), cellStyle, columnIndex++);
						setCellValuesForRow("", cellStyle, columnIndex++);
					}
					else if (studentDetailsDTO.getSeDetailsDTO().getVenue().equalsIgnoreCase(VenueTypeEnum.ATSCHOOL.toString()))
					{
						setCellValuesForRow("AT SCHOOL", cellStyle, columnIndex++);
						setCellValuesForRow("", cellStyle, columnIndex++);
					}
					else
					{
						setCellValuesForRow(VenueTypeEnum.OTHER.getValue(), cellStyle, columnIndex++);
						setCellValuesForRow(studentDetailsDTO.getSeDetailsDTO().getVenue(), cellStyle, columnIndex++);
					}
				}
				else
				{
					setCellValuesForRow("", cellStyle, columnIndex++);
					setCellValuesForRow("", cellStyle, columnIndex++);
				}
				setCellValuesForRow(studentDetailsDTO.getDueAmount(), cellStyle, columnIndex++);
			}
			createRow();
			isValidSummary = true;
		}
		if (!isValidSummary)
		{
			createRow();
			createRow();
			createEmptySummaryRow();
		}
		return writeToWorkBook(destFilePath, destinationPath);
	}
}
