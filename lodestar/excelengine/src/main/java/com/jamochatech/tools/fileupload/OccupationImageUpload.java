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

import com.jamochatech.tools.fileupload.dao.OccupationDAO;
import com.jamochatech.tools.fileupload.dto.OccupationDTO;

public class OccupationImageUpload
{
	private static final Logger				OUT			= LoggerFactory.getLogger(OccupationImageUpload.class);
	private static OccupationImageUpload	INSTANCE	= new OccupationImageUpload();

	private OccupationImageUpload()
	{
		// Do Nothing
	}

	public static OccupationImageUpload getInstance()
	{
		return INSTANCE;
	}

	private static void parseExcel(String excelFilePath, String sheetName, String imageFolder)
	{
		List<OccupationDTO> occupationList = getOccupationList();
		if (null != occupationList && !occupationList.isEmpty())
		{
			Workbook wb = null;
			try
			{
				wb = WorkbookFactory.create(new File(excelFilePath));
				Sheet xlSheet = wb.getSheet(sheetName);
				int firstRow = xlSheet.getFirstRowNum() + 1;
				int lastRow = xlSheet.getLastRowNum();
				String occupationName;
				Row row;
				String contentType;
				String imageFile;
				for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++)
				{
					row = xlSheet.getRow(rowIndex);
					occupationName = row.getCell(0).toString().trim();
					for (OccupationDTO occupationDTO : occupationList)
					{
						if (occupationName.equalsIgnoreCase(occupationDTO.getName().trim()))
						{
							contentType = FilenameUtils.getExtension(row.getCell(1).toString().trim());
							if (contentType.equalsIgnoreCase("jpg") || contentType.equalsIgnoreCase("png"))
							{
								imageFile = imageFolder + File.separator + row.getCell(1).toString().trim();
								OUT.debug("{} upload status: {}", occupationDTO.getName(), FileUpload(occupationDTO.getId(), imageFile));
							}
							else
							{
								OUT.debug("Unsupported image file type {} for Occupation {}", contentType, occupationDTO.getName());
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				OUT.error("exception", e);
			}
			finally
			{
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
			OUT.debug("Completed uploading Occupation image to database");
		}
	}

	private static boolean FileUpload(int id, String imageFile)
	{
		boolean uploadStatus = false;
		try
		{
			OUT.debug("File upload action for OccupationId: {}, image: {}", id, imageFile);
			File file = new File(imageFile);

			byte[] fileContent = FileUtils.readFileToByteArray(file);

			if (null == fileContent)
			{
				OUT.error("File content is null for OccupationId: {}, image: {}", id, imageFile);
				return false;
			}
			OccupationDTO occupationDTO = new OccupationDTO();
			occupationDTO.setImage(fileContent);
			occupationDTO.setId(id);
			uploadStatus = new OccupationDAO().doUpdateOccupationImage(occupationDTO);
		}
		catch (Exception e)
		{
			uploadStatus = false;
			OUT.error("exception", e);
		}
		return uploadStatus;
	}

	private static List<OccupationDTO> getOccupationList()
	{
		List<OccupationDTO> list = null;
		try
		{
			list = new OccupationDAO().getOccupationNameAndId();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return list;
	}

	public static void main(String[] args)
	{
		if (args == null || args.length < 3)
		{
			OUT.error("USAGES : OccupationImageUpload <Excel file> <Sheet name> <Image Folder Path> ");
		}
		else
		{
			OccupationImageUpload.getInstance().parseExcel(args[0], args[1], args[2]);
		}
		// String excelFilePath = "D://LSData//Occupation.xlsx";
		// String sheetName = "OccupationImage";
		// String imageFolder = "D://LSData//occupation_images";
		// OccupationImageUpload.getInstance().parseExcel(excelFilePath, sheetName, imageFolder);
	}
}
