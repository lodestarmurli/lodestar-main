package com.lodestar.edupath.dataaccessobject.dao.payment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.PaymentMapDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ManagePaymentDAO 
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ManagePaymentDAO.class);

	public int insertPaymentDetails(SqlSession session, PaymentDTO paymentDTO) throws Exception {
		int insertId = 0;
		try
		{
			OUT.debug("ManagePaymentDAO: Insert all Mapping Details");
			insertId = session.insert(MyBatisMappingConstants.INSERT_Payment_Details, paymentDTO);
			OUT.debug("Insert Payment id: {}", insertId);

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		// TODO Auto-generated method stub
		return insertId;
		
	}

	public List<PaymentMapDTO> getLoginidwithId() {
		// TODO Auto-generated method stub
		List<PaymentMapDTO> resultList = null;
		try
		{
			OUT.debug("ManagePaymentDAO: Getting all Mapping Details");
			resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_STUDENTID_WITH_ID, null);
			OUT.debug("ManagePaymentDAO: Number of Mapping Details found: {}", resultList != null ? resultList.size() : 0);
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultList;
	}

	public List<PaymentDTO> getAllStudentIDFromPayment() {
		// TODO Auto-generated method stub
		try
		{
			OUT.debug("ManagePaymentDAO: Getting all Facilitator Details");
			List<PaymentDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_StudentID, null);
			OUT.debug("ManagePaymentDAO: Number of Facilitator Details found: {}", resultList != null ? resultList.size() : 0);
			return resultList;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public int updatePaymentDetails(SqlSession session, PaymentDTO paymentDTO) {
		int id = 0;
		try
		{
			id = session.update(MyBatisMappingConstants.UPDATE_PaymentDetail_BY_StudentID, paymentDTO);
			OUT.debug("Update PaymentDetail id:{} ", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
	}

	public List<PaymentDTO> getAllLoginidList() {
		// TODO Auto-generated method stub
		try
		{
			OUT.debug("ManagePaymentDAO: Getting all Payment Details");
			List<PaymentDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_LoginID_Details, null);
			OUT.debug("ManagePaymentDAO: Number of Payment Details found: {}", resultList != null ? resultList.size() : 0);
			return resultList;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

//	public void insertPaymentDetailfromUI(SqlSession session, PaymentDTO paymentDTO) {
//		// TODO Auto-generated method stub
//		
//	}

	public void updatePaymentDetails(PaymentDTO paymentDTO) throws Exception {
		// TODO Auto-generated method stub
		OUT.debug("ManagePaymentDAO: Getting all Payment Details");
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_PAYMENT_STUDENT_BY_ID, paymentDTO);
	}

}
