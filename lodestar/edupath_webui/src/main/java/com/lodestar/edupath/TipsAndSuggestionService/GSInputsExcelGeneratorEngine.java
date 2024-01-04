package com.lodestar.edupath.TipsAndSuggestionService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions.ViewGSIputsDTO;


public class GSInputsExcelGeneratorEngine
{
	private static final Logger	OUT	= LoggerFactory.getLogger(GSInputsExcelGeneratorEngine.class);

	private CellStyle			cellStyle;

	private HSSFWorkbook		workbook;

	private Sheet				sheet;

	private Row					row;

	private Cell				cell;

	private int					rowIndex;

	public void createWorkBook(String sheetName)
	{
		try
		{
			this.workbook = new HSSFWorkbook();
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

	/**
	 * @param headerSize
	 */
	private void mergeCurrentAndPreviousRows(int headerSize)
	{
		CellRangeAddress cellRangeAddress = new CellRangeAddress(rowIndex - 2, rowIndex - 1, 1, headerSize);
		sheet.addMergedRegion(cellRangeAddress);
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

	public File reportExcel(List<ViewGSIputsDTO> reportList, Map<Integer, List<String>> headerMap, String destFilePath, String destinationPath)
	{
		createWorkBook("GSInputs_Report_Details");
		createCaptionCell("GSInputs Report", headerMap.get(1));
		boolean isValidSummary = false;
		int columnIndex = 0;
		if (null != reportList && reportList.size() > 0 && !reportList.isEmpty())
		{
			createRow();
			createHeaders(headerMap.get(1));
			createHeaders(headerMap.get(2));
			// mergeCurrentAndPreviousRows(headerMap.get(1).size());
			// Iterate the List to add rows
			int studentId = 0;
			for (ViewGSIputsDTO studentDetailsDTO : reportList)
			{
				if (studentId != studentDetailsDTO.getStudentid())
				{
					columnIndex = 1;
					createRow();
					//Start Sasedeve edited by Mrutyunjaya on date 08-09-2017
					
					setCellValuesForRow(studentDetailsDTO.getGsinputdate(), cellStyle, columnIndex++);
					
					//End Sasedeve edited by Mrutyunjaya on date 08-09-2017
					
					setCellValuesForRow(studentDetailsDTO.getStudentname(), cellStyle, columnIndex++);
					setCellValuesForRow(studentDetailsDTO.getLdid(), cellStyle, columnIndex++);
					
					setCellValuesForRow(studentDetailsDTO.getGsname(), cellStyle, columnIndex++);
					
					
					setCellValuesForRow(studentDetailsDTO.getStudentclass(), cellStyle, columnIndex++);
					
				}
				studentId = studentDetailsDTO.getStudentid();
				try
				{
					StringBuilder answer = null;
					JSONArray jsonArray = new JSONArray(studentDetailsDTO.getAnswer());
					for (int i = 0; i < jsonArray.length(); i++)
					{
						answer = new StringBuilder();
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						for (String key : JSONObject.getNames(jsonObject))
						{
							answer.append(key + " = " + jsonObject.getString(key));
							setCellValuesForRow(answer, cellStyle, columnIndex++);
						}
					}
				}
				catch (Exception e)
				{
					setCellValuesForRow(studentDetailsDTO.getAnswer(), cellStyle, columnIndex++);
				}
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
