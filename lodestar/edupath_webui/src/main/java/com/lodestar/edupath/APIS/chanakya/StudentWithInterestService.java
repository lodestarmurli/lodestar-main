package com.lodestar.edupath.APIS.chanakya;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.chanakya.ChanakyaStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.chanakya.ChanakyaStudentCGTDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StudentWithInterestService {
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentWithInterestService.class);
	
	private JSONObject studentDeatils = new  JSONObject();
	
	private String									Data;
	private String									UIN;
	
	private String   								Status="ERROR";
	
	private String   								Message;
	
	public static String							Studentuin="";
	
	private SqlSession session = null;


	public ChanakyaStudentDetailsDTO studentCreation(ChanakyaStudentDetailsDTO studentDTO)
	{
		OUT.debug("Inside StudentWithInterestService studentCreation studentDTO:{}",studentDTO);
		
		ChanakyaStudentDetailsDTO dhStudentDTO = new ChanakyaStudentDetailsDTO();
		ChanakyaStudentDAO dhStudentDAO = new ChanakyaStudentDAO();
		try
		{
			session = MyBatisManager.getInstance().getSession();
			ChanakyaStudentDetailsDTO searchResultDTO = dhStudentDAO.getChanakyaStudentByCHNKID(session, studentDTO);
			session.commit();
			if (searchResultDTO == null) 
			{
				dhStudentDTO = dhStudentDAO.insertStudent(session, studentDTO);
				session.commit();
			}
			else
			{
				dhStudentDTO.setId(0);
				OUT.info("Chanakya student Id found, can not upload duplicate record");
			}
			
		}catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		
		return dhStudentDTO;
		
	}
	
	
	
	public int uploadInterestTest(List<ChanakyaStudentCGTDTO> studentcgtList)
	{
		
		try
		{
			session = MyBatisManager.getInstance().getSession();
			
			for(ChanakyaStudentCGTDTO studentcgt: studentcgtList)
			{
				ChanakyaStudentCGTDTO ResultCGTDTO = ChanakyaStudentCGTDAO.insertForBulkStudentCGT(session, studentcgt);
			}
			session.commit();
			
		}catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		
		return 1;
		
	}
	
}
