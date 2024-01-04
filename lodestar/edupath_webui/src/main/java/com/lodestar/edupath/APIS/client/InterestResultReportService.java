package com.lodestar.edupath.APIS.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.client.ClientStudentOccupationDAO;
import com.lodestar.edupath.dataaccessobject.dao.newscoringlogic.NewScoringLogicForTrafficLight;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentOccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.partner.PartnerDeatilsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.EncryptionDecryptionData;

public class InterestResultReportService {
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(InterestResultReportService.class);
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
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDTO;
	}
	
	public List<OccupationDTO> getOccupationlist(ClientStudentDetailsDTO studentDTO) throws Exception
	{
		List<OccupationDTO> allocclist = new ArrayList<OccupationDTO>();
		List<OccupationDTO> occlistwithfitment = new ArrayList<OccupationDTO>();
		OccupationDAO occDAO = new OccupationDAO();
		NewScoringLogicForTrafficLight NewScoringLogicForTrafficLightObj=new NewScoringLogicForTrafficLight();
		
		try {	
			
				if (studentDTO.getClassStr().equalsIgnoreCase("8") || studentDTO.getClassStr().equalsIgnoreCase("9") || studentDTO.getClassStr().equalsIgnoreCase("10")) 
				{
					allocclist = occDAO.getOccupationListForDH();
				} 
				else if ( studentDTO.getClassStr().equalsIgnoreCase("11") || studentDTO.getClassStr().equalsIgnoreCase("12") ) 
				{
					allocclist = occDAO.getOccupationListBasedStreamForClient(studentDTO);
				}
				else if ( studentDTO.getClassStr().equalsIgnoreCase("12 plus") ) 
				{
//					allocclist=null;
					List<OccupationDTO> occlist = new ArrayList<OccupationDTO>();
					occlist= occDAO.getOccupationForTwelvePlusSCIENCE_MATH(studentDTO.getRaisecCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(1);
						allocclist.add(occdto);
					}
					occlist= occDAO.getOccupationForTwelvePlusSCIENCE_WO_MATH(studentDTO.getRaisecCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(6);
						allocclist.add(occdto);
					}
					occlist= occDAO.getOccupationForTwelvePlusCOMMERCE(studentDTO.getRaisecCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(7);
						allocclist.add(occdto);
					}
					occlist= occDAO.getOccupationForTwelvePlusARTS(studentDTO.getRaisecCode());
					for(OccupationDTO occdto:occlist)
					{
						occdto.setDhStream(5);
						allocclist.add(occdto);
					}
				}
		
				for (OccupationDTO occupationDTO : allocclist) 
				{
					occupationDTO = NewScoringLogicForTrafficLightObj.GetNewTrafficLight(occupationDTO, studentDTO.getRaisecCode());
					occlistwithfitment.add(occupationDTO);
				}
				Collections.sort(occlistwithfitment, new OccupationFitmentSorter());
		
				if (occlistwithfitment.size() > 10) 
				{
					occlistwithfitment = occlistwithfitment.subList(0, 10);
				}
		}catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return occlistwithfitment;
	}
	
	
	public String createToken(Integer studentId, Integer occupationId)
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
	
	public String createPDFToken(Integer studentId)
	{
		Date date = new Date();
		PartnerDeatilsDTO partnerDeatilsDTO = Util.getPartnerFromHeader();
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("StudentId", studentId.toString());
		jsonobj.put("Stage", "1");
		jsonobj.put("validateHeader",Integer.toString(partnerDeatilsDTO.getIsActive()));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		jsonobj.put("date",formatter.format(date));
		return new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobj));
		
	}
	
	
	public int insertORUpdateStudentOcc(ClientStudentOccupationDTO studentOccDTO) throws Exception
	{
		session = MyBatisManager.getInstance().getSession();
		int id = 0;
		try
		{
			ClientStudentOccupationDAO studentOccDAO = new ClientStudentOccupationDAO();
			id = studentOccDAO.insertORUpdateStudentOcc(session, studentOccDTO);
			session.commit();
		}catch (Exception e)
		{
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
		
	}
	
	
	
}
