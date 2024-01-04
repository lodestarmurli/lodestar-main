package com.lodestar.edupath.bulkupload.payment;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class PaymentExcelValidator {
	
	private static Logger	OUT	= LoggerFactory.getLogger(PaymentExcelValidator.class);
	
	public boolean validate(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList)
	{
		ManagePaymentDAO managePaymentDAO=new ManagePaymentDAO();
		List<PaymentDTO> LoginIdList = managePaymentDAO.getAllLoginidList();
		Set<String> loginIdset= getallloginId(LoginIdList);
		boolean validationSuccess = true;
		ApplicationProperties propInstance = ApplicationProperties.getInstance();
		int rowNumber = 0;
		for (NetValenceExcelRowObject excelRowObject : excelRowObjList)
		{
			Map<String, NetValenceExcelCellVO> rowDataMap = excelRowObject.getRowData();
			rowNumber = rowDataMap.get(propInstance.getProperty("com.edupath.bulkupload.summary.label.status")).getRowIndex();
			OUT.debug("RowNumber : {}", rowNumber);
			String paidamount = rowDataMap.get(propInstance.getProperty("com.edupath.payment.details.paidAmount")).getValue().trim();
			String agreedamount = rowDataMap.get(propInstance.getProperty("com.edupath.payment.details.agreedAmount")).getValue().trim();
			String dueamount = rowDataMap.get(propInstance.getProperty("com.edupath.payment.details.dueAmount")).getValue().trim();
			String loginid = rowDataMap.get(propInstance.getProperty("com.edupath.payment.details.loginId")).getValue().trim();
			
			
			if (paidamount == null || paidamount.isEmpty() )
			{
				OUT.warn("Validation failed in payment at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.paidAmount"), "REQUIRED_PAIDAMOUNT");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}
			else
			{
				try
				{
					Double.parseDouble(paidamount);
				}
				catch (Exception e)
				{
					setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.paidAmount"), "INVALID_FORMAT");
					setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
					validationSuccess = false;
				}
				
				
			}
			
			
			if (agreedamount == null || agreedamount.isEmpty() )
			{
				OUT.warn("Validation failed in payment at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.agreedAmount"), "REQUIRED_AGREEDAMOUNT");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}
			else
			{
				try
				{
					Double.parseDouble(agreedamount);
				}
				catch (Exception e)
				{
					setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.agreedAmount"), "INVALID_FORMAT");
					setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
					validationSuccess = false;
				}
			}
			if (dueamount == null || dueamount.isEmpty() )
			{
				OUT.warn("Validation failed in payment at Row: " + rowNumber);
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.dueAmount"), "REQUIRED_DUEAMOUNT");
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}
			else
			{
				try
				{
					Double.parseDouble(dueamount);
				}
				catch (Exception e)
				{
					setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.dueAmount"), "INVALID_FORMAT");
					setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
					validationSuccess = false;
				}
			}
			if (loginid == null || loginid.isEmpty())
			{
				OUT.warn("Validation failed in payment at Row: " + rowNumber);
				
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "REQUIRED_LOGINID");
				validationSuccess = false;
			}
			if (!checkLoginId(rowDataMap, xlsEngine, propInstance, loginIdset))
			{
				OUT.warn("Validation failed in payment at Row: " + rowNumber);
				setExternalValue(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.bulkupload.summary.label.status"), "INVALID_OPERATION");
				validationSuccess = false;
			}
			
		}
		return validationSuccess;
		
	}
	
	
	private boolean checkLoginId(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine xlsEngine,
			ApplicationProperties propInstance, Set<String> loginIdset) {
			String loginid = rowDataMap.get(propInstance.getProperty("com.edupath.payment.details.loginId")).getValue().trim();
			if (loginIdset.contains(loginid))
			{
				return true;
				
			}
			else
			{
				setErrorComment(rowDataMap, xlsEngine, propInstance.getProperty("com.edupath.payment.details.loginId"), "LOGIN_ID_NOT_EXISTS");
				return false;
			}
	
	}


	private Set<String> getallloginId(List<PaymentDTO> loginIdList) {
		
		Set<String> emailSet = null;
		if (loginIdList != null)
		{
			emailSet = new HashSet<String>();
			for (PaymentDTO facilitatorDTO : loginIdList)
			{
				emailSet.add(facilitatorDTO.getLoginId());
			}
		}
		return emailSet;
		
	}


	protected void setErrorComment(Map<String, NetValenceExcelCellVO> rowDataMap, XLSEngine engine, String headerName, String errorTitle)
	{
		NetValenceExcelCellVO excelCellVO = rowDataMap.get(headerName);
		
		if (errorTitle.contains("REQUIRED_"))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "This is a required information");
		}
		if ("LOGIN_ID_NOT_EXISTS".equals(errorTitle))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Login id not exist");
		}
		if ("INVALID_FORMAT".equals(errorTitle))
		{
			engine.setCellCommentExternally(excelCellVO.getRowIndex(), excelCellVO.getColumnIndex(), "Invalid Format");
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