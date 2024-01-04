package com.lodestar.edupath.APIS.client.AptitudeResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.client.Util;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentCGTResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.partner.PartnerDeatilsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class AptitudeResultService {

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(AptitudeResultService.class);
	private SqlSession session = null;
	
	public ClientStudentDetailsDTO getStudentByDHID(String id, String partnerName)
	{
		ClientStudentDetailsDTO dhstudentDTO = new ClientStudentDetailsDTO();
		ClientStudentDAO dhStudentDAO = new ClientStudentDAO();
		session = MyBatisManager.getInstance().getSession();
		ClientStudentDetailsDTO studentDTO =null;
		try {
			dhstudentDTO.setClientID(id.trim());
			dhstudentDTO.setClientName(partnerName);
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
	
	
	public ClientStudentCGTResultDTO getDHStudentCGTResultByStudentId(ClientStudentDetailsDTO studentDTO)
	{
		session = MyBatisManager.getInstance().getSession();
		ClientStudentCGTResultDTO studentCGTResultDTO = new ClientStudentCGTResultDTO();
		ClientStudentCGTResultDAO studentCGTResultDAO = new ClientStudentCGTResultDAO();
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
	
	public String createPDFToken(ClientStudentDetailsDTO studentDTO)
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
	
	public void updateToken(ClientStudentCGTResultDTO studentCGTResultDTO)
	{
		session = MyBatisManager.getInstance().getSession();
		 
		ClientStudentCGTResultDAO studentCGTResultDAO = new ClientStudentCGTResultDAO();
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
