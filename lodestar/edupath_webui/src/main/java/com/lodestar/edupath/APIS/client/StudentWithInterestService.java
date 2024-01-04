package com.lodestar.edupath.APIS.client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


	public ClientStudentDetailsDTO studentCreation(ClientStudentDetailsDTO studentDTO)
	{
		OUT.debug("Inside StudentWithInterestService studentCreation studentDTO:{}",studentDTO);
		
		ClientStudentDetailsDTO dhStudentDTO = new ClientStudentDetailsDTO();
		ClientStudentDAO dhStudentDAO = new ClientStudentDAO();
		try
		{
			session = MyBatisManager.getInstance().getSession();
			ClientStudentDetailsDTO searchResultDTO = dhStudentDAO.getDHStudentByDHID(session, studentDTO);
			session.commit();
			if (searchResultDTO == null) 
			{
				dhStudentDTO = dhStudentDAO.insertStudent(session, studentDTO);
				session.commit();
			}
			else
			{
				dhStudentDTO.setId(0);
				OUT.info("DialyHunt student Id found, can not upload duplicate record");
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
	
	
	
	public int uploadInterestTest(List<ClientStudentCGTDTO> studentcgtList)
	{
		
		try
		{
			session = MyBatisManager.getInstance().getSession();
			
			for(ClientStudentCGTDTO studentcgt: studentcgtList)
			{
				ClientStudentCGTDTO ResultCGTDTO = ClientStudentCGTDAO.insertForBulkStudentCGT(session, studentcgt);
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
