package com.lodestar.edupath.bulkupload.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;

import com.lodestar.edupath.bulkupload.EActionStatus;

import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.PaymentMapDTO;

import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;


import com.lodestar.edupath.payment.service.ManagePaymentService;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;
import netvalence.commons.excel.utils.NetValenceExcelRowObject;

public class PaymenttExcelUploadDBUtil {
	private static final Logger		OUT						= LoggerFactory.getLogger(FacilitatorExcelUploadDBUtil.class);
	public static final int			excelNumberOfRows		= 1;
	private String					loginID;
	private Map<String, Integer>	loginIDMap					= null;

	public List<NetValenceExcelRowObject> prepareTemplate() {
		List<NetValenceExcelRowObject> rowObjectList = new ArrayList<NetValenceExcelRowObject>(5);
		try
		{

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return rowObjectList;
	}
	
	public EActionStatus persistExcelObjects(XLSEngine xlsEngine, List<NetValenceExcelRowObject> excelRowObjList, String loggedInUserLoginId)
	{
		ApplicationProperties properties = ApplicationProperties.getInstance();
		ManagePaymentDAO managePaymentDAO=new ManagePaymentDAO();
		
		
		int totalRecords = 0;
		int successfull = 0;
		int failed = 0;
		try
		{
			

			NetValenceExcelRowObject excelRowObject;
			Map<String, NetValenceExcelCellVO> rowDataMap;
			for (int i = 0; i < excelRowObjList.size(); i++)
			{
				excelRowObject = excelRowObjList.get(i);
				rowDataMap = excelRowObject.getRowData();
				System.out.println(rowDataMap);
				totalRecords++;
				
				List<PaymentMapDTO> Maplist= managePaymentDAO.getLoginidwithId();
				loginIDMap=new HashMap<String, Integer>();
				for (PaymentMapDTO paymentMapDTO : Maplist) {
					loginIDMap.put(paymentMapDTO.getLoginId(), paymentMapDTO.getId());
					
				}
				
				
				if (addDetails(excelRowObject, xlsEngine, rowDataMap, properties, loggedInUserLoginId))
				{
					
					OUT.debug("Payment details added successfully");
					successfull++;
				}
				else
				{
					OUT.debug("Payment details insertion failed");
					failed++;
				}
			}

			OUT.debug("Total Records:" + totalRecords + ", Successful:" + successfull + ", Failed:" + failed);
			if (totalRecords == successfull)
			{
				return EActionStatus.SUCCESS;
			}
			else
			{
				return EActionStatus.FAILURE;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return EActionStatus.FAILURE;
	}
	private boolean addDetails(NetValenceExcelRowObject excelRowObject, XLSEngine xlsEngine, Map<String, NetValenceExcelCellVO> rowDataMap,
			ApplicationProperties properties, String loggedInUserLoginId)
	{
		
		ManagePaymentDAO managePaymentDAO=new ManagePaymentDAO();
		boolean isInsertedorUpdate = false;
		PaymentDTO paymentDTO=preparePayment(rowDataMap, properties);
		ManagePaymentService service = new ManagePaymentService();
		
		
		List<PaymentDTO> StudentIDlist= managePaymentDAO.getAllStudentIDFromPayment();
		
		Set<String> StudentIDSet = getAllStudentIDSet(StudentIDlist);
		
		if(StudentIDSet.contains(paymentDTO.getLoginId()))
		{
			isInsertedorUpdate = service.updatePaymentDetails(paymentDTO);
		}
		else
		{
			isInsertedorUpdate = service.insertPaymentDetails(paymentDTO);
		}
		
		
		
		
		return isInsertedorUpdate;
	}

	private Set<String> getAllStudentIDSet(List<PaymentDTO> studentIDlist) {
		// TODO Auto-generated method stub
		Set<String> studentIDSet = null;
		if (studentIDlist != null)
		{
			studentIDSet = new HashSet<String>();
			for (PaymentDTO PaymentDTO : studentIDlist) {
				studentIDSet.add(PaymentDTO.getLoginId());
			}
		}
		return studentIDSet;
	}

	private PaymentDTO preparePayment(Map<String, NetValenceExcelCellVO> rowDataMap, ApplicationProperties properties) {
		// TODO Auto-generated method stub
		PaymentDTO paymentsDTO =new PaymentDTO();
		
	
		paymentsDTO.setLoginId(rowDataMap.get(properties.getProperty("com.edupath.loginscreen.user.name.label")).getValue().trim());
		paymentsDTO.setDueAmount(rowDataMap.get(properties.getProperty("com.edupath.payment.details.dueAmount")).getValue().trim());
		paymentsDTO.setAgreedAmount(rowDataMap.get(properties.getProperty("com.edupath.payment.details.agreedAmount")).getValue().trim());
		paymentsDTO.setPaidAmount(rowDataMap.get(properties.getProperty("com.edupath.payment.details.paidAmount")).getValue().trim());
		
		String loginID = rowDataMap.get(properties.getProperty("com.edupath.loginscreen.user.name.label")).getValue().trim();
		if (loginID != null && !loginID.equals(""))
		{
			int studentId = getStudentID(loginID);
			paymentsDTO.setStudentId(studentId);
		}
		
		return paymentsDTO;
	}
	private int getStudentID(String loginID)
	{
		return loginIDMap.get(loginID);
	}
	

}
