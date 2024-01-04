package com.lodestar.edupath.bulkupload.interest;

import java.util.List;
import java.util.Map;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.util.ApplicationProperties;

public class InterestTestExcelValidator
{
	private static Logger	OUT	= LoggerFactory.getLogger(InterestTestExcelValidator.class);

	public boolean validate(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList, Map<String, StudentDetailsDTO> userLoginIdNIdMap)
	{
		boolean validationSuccess = true;
		boolean isStatusSuccess;
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		int rowNumber = 0;
		String status = propInstance.getProperty("com.edupath.bulkupload.column.status");
		String loginIdHeader = propInstance.getProperty("com.edupath.bulkupload.column.loginid");
		if (null != excelRowObjList && !excelRowObjList.isEmpty())
		{

			for (NetValenceExcelRowObject excelRowObject : excelRowObjList)
			{
				Map<String, NetValenceExcelCellVO> rowDataMap = excelRowObject.getRowData();
				rowNumber = rowDataMap.get(status).getRowIndex();
				OUT.debug("RowNumber : {}", rowNumber);
				isStatusSuccess = true;
				if (!isLoginIdValid(rowDataMap, xlsEngine, loginIdHeader, userLoginIdNIdMap, propInstance))
				{
					OUT.warn("Validation failed in login Id at Row: " + rowNumber);
					validationSuccess = false;
					isStatusSuccess = false;
				}
				if (!isStatusSuccess)
				{
					setExternalValue(rowDataMap, xlsEngine, status, "INVALID_OPERATION");
				}
			}
		}
		else
		{
			validationSuccess = false;
		}
		return validationSuccess;
	}

	private void setExternalValue(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, String status, String errorTitle)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(status);
		if (errorTitle.contains("INVALID_OPERATION"))
		{
			xlsEngine.setCellValueExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "FAILURE");
		}

	}

	private boolean isLoginIdValid(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, String logionIdHeader,
			Map<String, StudentDetailsDTO> userLoginIdNIdMap, ApplicationProperties propInstance)
	{
		String loginId = rowDataMap.get(logionIdHeader).getValue().trim();
		if (loginId == null || loginId.isEmpty())
		{
			setErrorComment(rowDataMap, xlsEngine, logionIdHeader, propInstance.getProperty("com.edupath.bulkupload.column.empty.error"));
			return false;
		}
		if (!userLoginIdNIdMap.containsKey(loginId.toLowerCase()))
		{
			setErrorComment(rowDataMap, xlsEngine, logionIdHeader, propInstance.getProperty("com.edupath.bulkupload.column.loginid.invalid.error"));
			return false;
		}
		return true;
	}

	private void setErrorComment(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine, String property, String errorMsg)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(property);
		xlsEngine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), errorMsg);
	}

}
