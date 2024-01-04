package com.lodestar.edupath.payment.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;

import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;

import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;


public class ManagePaymentService {
	private static final Logger	OUT	= LoggerFactory.getLogger(ManagePaymentService.class);
	private String				auditDateStr;

	public boolean insertPaymentDetails(PaymentDTO paymentDTO) {
		// TODO Auto-generated method stub
		
		SqlSession session = null;
		try {
			
			session = MyBatisManager.getInstance().getSession();
			
			new ManagePaymentDAO().insertPaymentDetails(session,paymentDTO);
			session.commit();
		} catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;
	}

	public boolean updatePaymentDetails(PaymentDTO paymentDTO) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			
			session = MyBatisManager.getInstance().getSession();
			
			new ManagePaymentDAO().updatePaymentDetails(session,paymentDTO);
			session.commit();
		} catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return true;
	}

	

}
