package com.lodestar.edupath.APIS.dailyHunt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
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


	
	public DHStudentDetailsDTO getStudentAptitudeByDHID(String id)
	{
		DHStudentDetailsDTO dhstudentDTO = new DHStudentDetailsDTO();
		DHStudentCGTResultDAO dhStudentDAO = new DHStudentCGTResultDAO();
		session = MyBatisManager.getInstance().getSession();
		DHStudentDetailsDTO studentDTO =null;
		try {
			dhstudentDTO.setDHID(id.trim());
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
	
	
	
	
	public int uploadAptitudeTest(List<DHStudentCGTDTO> studentcgtList, int studentId)
	{
		int result=0;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			DHStudentCGTResultDTO dhstudentCGTResultDTO = new DHStudentCGTResultDTO();
			DHStudentCGTResultDAO dhstudentCGTResultDAO = new DHStudentCGTResultDAO();
			
			for(DHStudentCGTDTO studentcgt: studentcgtList)
			{
				DHStudentCGTDTO ResultCGTDTO = DHStudentCGTDAO.insertForBulkStudentCGT(session, studentcgt);
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
	
	public int updateStudentType(DHStudentDetailsDTO studentDTO)
	{
		int result=0;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			DHStudentDAO dhtudentDAO = new DHStudentDAO();
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
