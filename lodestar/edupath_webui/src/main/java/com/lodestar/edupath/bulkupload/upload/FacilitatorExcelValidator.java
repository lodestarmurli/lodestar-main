package com.lodestar.edupath.bulkupload.upload;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.ApplicationProperties;

public class FacilitatorExcelValidator
{
	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorExcelValidator.class);

	/**
	 * @param xlsEngine
	 * @param excelRowObjList
	 * @return
	 */
	public boolean validate(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList)
	{
		// EduPathFDExcelUploadDBUtil dbUtil = new EduPathFDExcelUploadDBUtil();
		FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
		List<FacilitatorDetailsDTO> facilitatorDetailsList = facilitatorDetailsDAO.getAllFacilitatorsDetailsList();
		Set<String> emailSet = getAllEmailSet(facilitatorDetailsList);
		boolean validationSuccess = true;
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		int rowNumber = 0;
		for (NetValenceExcelRowObject excelRowObject : excelRowObjList)
		{
			Map<String, NetValenceExcelCellVO> rowDataMap = excelRowObject.getRowData();
			rowNumber = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.summary.label.status")).getRowIndex();
			OUT.debug("RowNumber : {}", rowNumber);
			String name = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.name")).getValue().trim();
			String phoneNumber = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.phonenumber")).getValue().trim();
			String dobStr = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.dob")).getValue().trim();
			String city = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.city")).getValue().trim();
			String qualification = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.qualification")).getValue().trim();
			if (name == null || name.isEmpty())
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.name"), "REQUIRED_NAME");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}

			if (phoneNumber == null || phoneNumber.isEmpty())
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.phonenumber"), "REQUIRED_PHONE_NUMBER");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}

			if (city == null || city.isEmpty())
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.city"), "REQUIRED_CITY");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}

			if (qualification == null || qualification.isEmpty())
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.qualification"), "REQUIRED_QUALIFICATION");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}

			if (!emailValid(rowDataMap, xlsEngine, propInstance, emailSet))
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}
			
			if (!altEmailValid(rowDataMap, xlsEngine, propInstance))
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}

			if (!dobValid(rowDataMap, xlsEngine, propInstance, dobStr))
			{
				OUT.warn("Validation failed in facilitator at Row: " + rowNumber);
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}

		}
		return validationSuccess;
	}
	
	private boolean dobValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, String dobStr)
	{
		if (dobStr != null && !dobStr.equals(""))
		{
			try
			{
				if (TimeUtil.isValidFormat(dobStr, TimeUtil.EXCEL_DATE_FORMAT))
				{
					Date date = TimeUtil.getDate(dobStr, TimeUtil.EXCEL_DATE_FORMAT);
					if (date.after(new Date()))
					{
						setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.dob"), "INVALID_DATE");
						return false;
					}
				}
				else
				{
					setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.dob"), "INVALID_DATE");
					return false;
				}
			}
			catch (ParseException e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.dob"), "INVALID_DATE");
				return false;
			}
		}

		return true;
	}
	
	private boolean altEmailValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance)
	{
		String altEmailId = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.altemail")).getValue().trim();
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (altEmailId != null && !altEmailId.isEmpty())
		{
			if (!altEmailId.matches(EMAIL_REGEX))
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.altemail"), "INVALID_EMAIL");
				return false;
			}
		}
		return true;
	}
	
	private boolean emailValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, ApplicationProperties propInstance, Set<String> emailSet)
	{
		String emailId = rowDataMap.get(propInstance.getProperty("com.edupath.facilitator.details.email")).getValue().trim();
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (emailId == null || emailId.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.email"), "REQUIRED_EMAIL");
			return false;
		}
		else if (emailSet.contains(emailId))
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.email"), "EMAIL_ID_EXISTS");
			return false;
		}
		else if (!emailId.matches(EMAIL_REGEX))
		{
			setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.facilitator.details.email"), "INVALID_EMAIL");
			return false;
		}

		return true;
	}

	private Set<String> getAllEmailSet(List<FacilitatorDetailsDTO> facilitatorDetailsList)
	{
		Set<String> emailSet = null;
		if (facilitatorDetailsList != null)
		{
			emailSet = new HashSet<String>();
			for (FacilitatorDetailsDTO facilitatorDTO : facilitatorDetailsList)
			{
				emailSet.add(facilitatorDTO.getEmailId());
			}
		}
		return emailSet;
	}

	/**
	 * @param rowDataMap
	 * @param engine
	 * @param headerName
	 * @param errorTitle
	 */
	protected void setErrorComment(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine engine, String headerName, String errorTitle)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(headerName);
		if (errorTitle.contains("INVALID_OPERATION"))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Operation");
		}
		if (errorTitle.contains("REQUIRED_"))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "This is a required information");
		}
		if ("INVALID_DATE".equals(errorTitle))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "invalid date");
		}
		if ("EMAIL_ID_EXISTS".equals(errorTitle))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Email id alredy exist");
		}
		if ("INVALID_EMAIL".equals(errorTitle))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "invalid Email id");
		}
	}

	protected void setExternalValue(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine engine, String headerName, String errorTitle)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(headerName);
		if (errorTitle.contains("INVALID_OPERATION"))
		{
			engine.setCellValueExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "FAILURE");
		}
	}
}
