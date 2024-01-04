package com.jamochatech.tools.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.utils.ExcelUtil;
import com.jamochatech.tools.fileupload.dao.TrialInterestCodeMappingDAO;
import com.jamochatech.tools.fileupload.dto.TrialInterestCodeMappingDTO;

public class TrialInterestCodeUpload
{

	private static final Logger				OUT			= LoggerFactory.getLogger(TrialInterestCodeUpload.class);
	private static TrialInterestCodeUpload	INSTANCE	= new TrialInterestCodeUpload();

	private TrialInterestCodeUpload()
	{
		// do nothing
	}

	public static TrialInterestCodeUpload getInstance()
	{
		return INSTANCE;
	}

	private static void parseExcel(String excelFilePath, String sheetName, String pdfFolder)
	{
		Workbook wb = null;
		try
		{
			TrialInterestCodeMappingDAO codeMappingDAO = new TrialInterestCodeMappingDAO();
			List<TrialInterestCodeMappingDTO> list = codeMappingDAO.getAllTrailInterestMapping();
			wb = WorkbookFactory.create(new File(excelFilePath));
			Sheet xlSheet = wb.getSheet(sheetName);
			int firstRow = xlSheet.getFirstRowNum() + 1;
			int lastRow = xlSheet.getLastRowNum();
			String raisecCode, fileName;
			Row row;
			String contentType, uploadFile;
			TrialInterestCodeMappingDTO codeMappingDTO = null;
			int updateId = 0, uploadRowCount = 0;
			for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++)
			{
				updateId = 0;
				codeMappingDTO = null;
				row = xlSheet.getRow(rowIndex);
				raisecCode = row.getCell(0).toString();
				fileName = row.getCell(1).toString();
				if (ExcelUtil.isNullOrEmpty(raisecCode))
				{
					OUT.debug("Invalid RaisecCode : {} for Trial Interest Code ", raisecCode);
					continue;
				}
				if (ExcelUtil.isNullOrEmpty(fileName))
				{
					OUT.debug("Invalid File Name : {} for Trial Interest Code ", fileName);
					continue;
				}
				fileName = fileName.trim();
				contentType = FilenameUtils.getExtension(fileName);
				if (contentType.equalsIgnoreCase("pdf"))
				{
					uploadFile = pdfFolder + File.separator + fileName;
					if (list.isEmpty())
					{
						codeMappingDTO = doFileUpload(raisecCode, uploadFile, fileName, codeMappingDAO, 0);
						if (null != codeMappingDTO && codeMappingDTO.getId() > 0)
						{
							updateId = codeMappingDTO.getId();
							list.add(codeMappingDTO);
						}
					}
					else
					{
						updateId = isExits(raisecCode, list);
						if (updateId > 0)
						{
							doFileUpload(raisecCode, uploadFile, fileName, codeMappingDAO, updateId);
						}
						else
						{
							codeMappingDTO = doFileUpload(raisecCode, uploadFile, fileName, codeMappingDAO, 0);
							if (null != codeMappingDTO && codeMappingDTO.getId() > 0)
							{
								updateId = codeMappingDTO.getId();
								list.add(codeMappingDTO);
							}
						}

					}
					uploadRowCount++;
					OUT.debug(" {} upload status: {}", raisecCode, (updateId > 0 ? true : false));
				}
				else
				{
					OUT.debug("Unsupported PDF file type {} for Trial Interest Code {}", contentType, raisecCode);
				}
			}
			if (uploadRowCount > 0)
			{
				OUT.debug("TrialInterestCode Uploaded successfully, Total Rows COUNT: {}, Uploaded Rows COUNT : {}", lastRow, uploadRowCount);
			}
			else
			{
				OUT.debug("TrialInterestCode Upload Failed, Total Rows COUNT: {}, Uploaded Rows COUNT : {}", lastRow, uploadRowCount);
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		try
		{
			if (wb != null)
			{
				wb.close();
			}
		}
		catch (IOException e)
		{
			OUT.error("exception", e);
		}
	}

	private static TrialInterestCodeMappingDTO doFileUpload(String raisecCode, String uploadFilePath, String fileName, TrialInterestCodeMappingDAO codeMappingDAO,
			int updateId)
	{
		try
		{
			OUT.debug("File upload action for Trial Interest Code: {}, pdfFile: {}", raisecCode, uploadFilePath);
			File file = new File(uploadFilePath);
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			if (null == fileContent)
			{
				OUT.error("File content is null for Trial Interest Code: {}, pdfFile: {}", raisecCode, uploadFilePath);
				return null;
			}
			TrialInterestCodeMappingDTO codeMappingDTO = new TrialInterestCodeMappingDTO();
			codeMappingDTO.setRaisecCode(raisecCode);
			codeMappingDTO.setContent(fileContent);
			codeMappingDTO.setFileName(fileName);
			if (updateId > 0)
			{
				codeMappingDTO.setId(updateId);
				codeMappingDAO.updateTrailInterestMapping(codeMappingDTO);
			}
			else
			{
				codeMappingDAO.insertTrailInterestMapping(codeMappingDTO);
			}
			return codeMappingDTO;
		}
		catch (Exception e)
		{
			OUT.error("exception", e);
		}
		return null;
	}

	private static int isExits(String raisecCode, List<TrialInterestCodeMappingDTO> list) throws Exception
	{
		int newCharCodeSum = getSumOfChar(raisecCode);
		OUT.debug("NEW raisecCode SUM : {}", newCharCodeSum);
		for (TrialInterestCodeMappingDTO codeMappingDTO : list)
		{
			if (!ExcelUtil.isNullOrEmpty(codeMappingDTO.getRaisecCode()))
			{
				int dbCharCodeSum = getSumOfChar(codeMappingDTO.getRaisecCode());
				OUT.debug("DB raisecCode SUM : {}", dbCharCodeSum);
				if (newCharCodeSum == dbCharCodeSum)
				{
					return codeMappingDTO.getId();
				}
			}
		}
		return 0;
	}

	public static int getSumOfChar(String raisecCode) throws Exception
	{
		char[] raisecCodeArr = raisecCode.toLowerCase().toCharArray();
		int sum = 0;
		for (char c : raisecCodeArr)
		{
			sum += c;
		}
		return sum;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		if (args == null || args.length < 3)
		{
			OUT.error("USAGES : TrialInterestCodeUpload <Excel file> <Sheet name> <PDF Folder Path> ");
		}
		else
		{
			TrialInterestCodeUpload.getInstance().parseExcel(args[0], args[1], args[2]);
		}

		/*
		 * try
		 * {
		 * String excelFilePath = "E://TrialInterestReport.xls";
		 * String sheetName = "Sheet1";
		 * String pdfFolder = "E:\\Edupath_Report\\23_SEP_2016\\Career guidance - basic evaluation report_FOR Kiran";
		 * TrialInterestCodeUpload.getInstance().parseExcel(excelFilePath, sheetName, pdfFolder);
		 * }
		 * catch (Exception e)
		 * {
		 * e.printStackTrace();
		 * }
		 */

	}

}
