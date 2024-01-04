package com.lodestar.edupath.APIS.chanakya.AptitudeResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.dailyHunt.Util;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.partner.PartnerDeatilsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class AptitudeResultService {

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(AptitudeResultService.class);
	private SqlSession session = null;
	
	public DHStudentDetailsDTO getStudentByDHID(String id)
	{
		DHStudentDetailsDTO dhstudentDTO = new DHStudentDetailsDTO();
		DHStudentDAO dhStudentDAO = new DHStudentDAO();
		session = MyBatisManager.getInstance().getSession();
		DHStudentDetailsDTO studentDTO =null;
		try {
			dhstudentDTO.setDHID(id.trim());
			studentDTO = dhStudentDAO.getDHStudentByDHID(session, dhstudentDTO);
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
	
	
	public DHStudentCGTResultDTO getDHStudentCGTResultByStudentId(DHStudentDetailsDTO studentDTO)
	{
		session = MyBatisManager.getInstance().getSession();
		DHStudentCGTResultDTO studentCGTResultDTO = new DHStudentCGTResultDTO();
		DHStudentCGTResultDAO studentCGTResultDAO = new DHStudentCGTResultDAO();
		try {
			studentCGTResultDTO = studentCGTResultDAO.getDHStudentCGTResultByStudentId(session, studentDTO.getId());
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
		
		return studentCGTResultDTO;
	}
	
	
	public String createToken(Integer studentId, String occupationId)
	{
		Date date = new Date();
		PartnerDeatilsDTO partnerDeatilsDTO = Util.getPartnerFromHeader();
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("StudentId", studentId.toString());
		jsonobj.put("Occupation", occupationId.toString());
		jsonobj.put("validateHeader",Integer.toString(partnerDeatilsDTO.getIsActive()));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		jsonobj.put("date",formatter.format(date));
		return new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobj));
		
	}
	
	public String createPDFToken(DHStudentDetailsDTO studentDTO)
	{
		Date date = new Date();
		PartnerDeatilsDTO partnerDeatilsDTO = Util.getPartnerFromHeader();
		Integer id = studentDTO.getId();
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("StudentId", id.toString());
		jsonobj.put("studentType", studentDTO.getStudentType());
		jsonobj.put("validateHeader",Integer.toString(partnerDeatilsDTO.getIsActive()));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		jsonobj.put("date",formatter.format(date));
		return new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobj));
		
	}
	
	public void updateToken(DHStudentCGTResultDTO studentCGTResultDTO)
	{
		session = MyBatisManager.getInstance().getSession();
		 
		DHStudentCGTResultDAO studentCGTResultDAO = new DHStudentCGTResultDAO();
		try {
			int result = studentCGTResultDAO.updateStudentCGTToken(session, studentCGTResultDTO);
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
	}
	
	
}
