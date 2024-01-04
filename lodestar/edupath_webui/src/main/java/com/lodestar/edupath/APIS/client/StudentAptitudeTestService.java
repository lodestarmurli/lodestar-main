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
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentCGTResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentAptitudeTestService {
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentAptitudeTestService.class);
	private Map<String, Object>				systemRecommendation	= new HashMap<String, Object>();
	
	private JSONObject studentDeatils = new  JSONObject();
	
	private String									Data;
	private String									UIN;
	
	private String   								Status="ERROR";
	
	private String   								Message;
	
	public static String							Studentuin="";
	
	private SqlSession session = null;


	
	public ClientStudentDetailsDTO getStudentAptitudeByDHID(String id,String partnerName)
	{
		ClientStudentDetailsDTO dhstudentDTO = new ClientStudentDetailsDTO();
		ClientStudentCGTResultDAO dhStudentDAO = new ClientStudentCGTResultDAO();
		session = MyBatisManager.getInstance().getSession();
		ClientStudentDetailsDTO studentDTO =null;
		try {
			dhstudentDTO.setClientID(id.trim());
			dhstudentDTO.setClientName(partnerName.trim());
			studentDTO = dhStudentDAO.getStudentAptitudeByDHID(session, dhstudentDTO);
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
		return studentDTO;
	}
	
	
	
	
	public int uploadAptitudeTest(List<ClientStudentCGTDTO> studentcgtList, int studentId)
	{
		int result=0;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			ClientStudentCGTResultDTO dhstudentCGTResultDTO = new ClientStudentCGTResultDTO();
			ClientStudentCGTResultDAO dhstudentCGTResultDAO = new ClientStudentCGTResultDAO();
			
			for(ClientStudentCGTDTO studentcgt: studentcgtList)
			{
				ClientStudentCGTDTO ResultCGTDTO = ClientStudentCGTDAO.insertForBulkStudentCGT(session, studentcgt);
			}
			
			dhstudentCGTResultDTO.setStudentId(studentId);
			dhstudentCGTResultDTO.setAptitudeComplete("COMPLETED");
			result = dhstudentCGTResultDAO.insertStudentCGTResult(session,dhstudentCGTResultDTO);
			session.commit();
			InterestResultUtilService utilService = new InterestResultUtilService();
			systemRecommendation = utilService.getSystemRecommendation(studentId,false);
			
			
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
		
		
		return result;
		
	}
	
	public int updateStudentType(ClientStudentDetailsDTO studentDTO)
	{
		int result=0;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			ClientStudentDAO dhtudentDAO = new ClientStudentDAO();
			result=dhtudentDAO.updateDHStudentType(session, studentDTO);
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
		
		return result;
	}

	
	
}
