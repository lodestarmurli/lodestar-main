package com.lodestar.edupath.fileupload.action;

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

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.fileupload.service.FileUploadService;
import com.lodestar.edupath.util.ApplicationProperties;

public class FileUploadAction extends BaseAction
{

	private static final long	serialVersionUID	= 240207937162603349L;
	private static Logger		OUT					= LoggerFactory.getLogger(FileUploadAction.class);
	private String				filePath;
	private String				fileName;
	private String				sheetName;
	private FileUploadService	service;
	private ApplicationProperties properties = ApplicationProperties.getInstance();

	public FileUploadAction()
	{
		service = new FileUploadService();
		filePath = properties.getProperty("com.edupath.occupation.excel.file.path");
		sheetName = properties.getProperty("com.edupath.occupation.excel.sheet.name");
	}

	public EActionStatus FileUpload(int id)
	{
		EActionStatus eStatus = EActionStatus.FAILURE;

		try
		{
			OUT.debug("File upload action");
			File file = new File(properties.getProperty("com.edupath.occupation.image.folder.path") + File.separator + fileName);
			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String occName = file.getName();
			
			OUT.debug("update image file for {}", occName);
				OccupationDTO occupationDTO = createDTO(fileContent, id);
				if (null == occupationDTO)
				{
					return eStatus;
				}
				eStatus = service.doUpdate(occupationDTO);
			
		}
		catch (Exception e)
		{
			OUT.debug(ApplicationConstants.EXCEPTION, e);
			eStatus = EActionStatus.FAILURE;
		}
		return eStatus;
	}

	private OccupationDTO createDTO(byte[] fileContent, int id)
	{
		OccupationDTO occupationDTO = null;
		if (null != fileContent)
		{
			occupationDTO = new OccupationDTO();
			occupationDTO.setImage(fileContent);
			occupationDTO.setId(id);
		}
		return occupationDTO;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public static void main(String[] args)
	{
		FileUploadAction filAction = new FileUploadAction();
		// filAction.fileName = "architect.jpg";

		List<OccupationDTO> occList = filAction.service.getOccDataList();
		if (null != occList && !occList.isEmpty())
		{
			Workbook wb = null;
			try
			{

				wb = WorkbookFactory.create(new File(filAction.getFilePath()));
				Sheet xlSheet = wb.getSheet(filAction.getSheetName());

				int firstRow = xlSheet.getFirstRowNum();
				int lastRow = xlSheet.getLastRowNum();
				String occupationName;
				for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++)
				{
					Row row = xlSheet.getRow(rowIndex);
					occupationName = row.getCell(0).toString().trim();
					for (OccupationDTO occupationDTO : occList)
					{
						// filAction.fileName = occupationDTO.getName().trim() + ".jpg";
						if (occupationName.equalsIgnoreCase(occupationDTO.getName().trim()))
						{
							String contentType = FilenameUtils.getExtension(row.getCell(1).toString().trim());
							if (contentType.equalsIgnoreCase("jpg") || contentType.equalsIgnoreCase("png"))
							{
								filAction.fileName = row.getCell(1).toString().trim();
								OUT.debug("{} upload status: {}", occupationDTO.getName(), filAction.FileUpload(occupationDTO.getId()));
							}
						}
					}

				}

			}
			catch (Exception e)
			{
				e.printStackTrace();

			}
			finally
			{
				if (wb != null)
					try
					{
						wb.close();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
			}
		
		}
	}
}
