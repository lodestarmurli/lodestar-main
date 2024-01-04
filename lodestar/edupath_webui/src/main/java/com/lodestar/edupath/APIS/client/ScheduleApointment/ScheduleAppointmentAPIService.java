package com.lodestar.edupath.APIS.client.ScheduleApointment;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentExtraDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.datatable.DataTableVO;

public class ScheduleAppointmentAPIService {
	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ScheduleAppointmentAPIService.class);
	private SqlSession session = null;
	

	public DHStudentDetailsDTO getStudentByDHID(String DHid)
	{
		OUT.debug("Inside ScheduleAppointmentAPIService getStudentByDHID id:{}",DHid);
		DHStudentDetailsDTO dhstudentDTO = new DHStudentDetailsDTO();
		DHStudentDAO dhStudentDAO = new DHStudentDAO();
		session = MyBatisManager.getInstance().getSession();
		DHStudentDetailsDTO studentDTO =null;
		try {
			dhstudentDTO.setDHID(DHid.trim());
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
	
	
	public DHStudentExtraDetailDTO getStudentAppointmentByStudentID(int studentId)
	{
		OUT.debug("Inside ScheduleAppointmentAPIService getStudentAptitudeByDHID id:{}",studentId);
		DHStudentExtraDetailDTO dhstudentExtraDetailDTO = null;
		DHStudentExtraDetailsDAO dhStudentExtraDetailDAO = new DHStudentExtraDetailsDAO();
		session = MyBatisManager.getInstance().getSession();
		try {
			//dhstudentDTO.setDHID(id.trim());
			session = MyBatisManager.getInstance().getSession();
			dhstudentExtraDetailDTO = dhStudentExtraDetailDAO.getDHStudentExtraDetailsByStudentId(session, studentId);
			
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
		return dhstudentExtraDetailDTO;
	}
	
	public int insertDHStudentExtraDetails(DHStudentExtraDetailDTO studentExtraDetailDTO)
	{
		OUT.debug("Inside ScheduleAppointmentAPIService insertDHStudentExtraDetails studentExtraDetailDTO:{}",studentExtraDetailDTO);
		int result=0;
		session = MyBatisManager.getInstance().getSession();
		DHStudentExtraDetailsDAO dhstudentExtraDetailsDAO= new DHStudentExtraDetailsDAO();
		
		try {
			result = dhstudentExtraDetailsDAO.insertDHStudentExtraDetails(session, studentExtraDetailDTO);
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
	
	public List<DHStudentExtraDetailDTO>  getAllDHStudentExtraDetails()
	{
		OUT.debug("Inside ScheduleAppointmentAPIService getStudentAppointmentSummary ");
		List<DHStudentExtraDetailDTO> dhstudentExtraDetailDTO = new ArrayList<DHStudentExtraDetailDTO>();
		List<DHStudentExtraDetailDTO> resultDTO = new ArrayList<DHStudentExtraDetailDTO>();
		DHStudentExtraDetailsDAO dhStudentExtraDetailDAO = new DHStudentExtraDetailsDAO();
		session = MyBatisManager.getInstance().getSession();
		try {
			//dhstudentDTO.setDHID(id.trim());
			session = MyBatisManager.getInstance().getSession();
			resultDTO = dhStudentExtraDetailDAO.getAllDHStudentExtraDetails();
			
			session.commit();
			for(DHStudentExtraDetailDTO studentdto : resultDTO)
			{
				if (null != studentdto.getSession1Date()) {

					studentdto.setSession1DateStr(TimeUtil.getDateFromMillis(studentdto.getSession1Date().getTime(),TimeUtil.REPORT_DATE_FORMAT));
				} else {
					studentdto.setSession2DateStr("-");
				}
				if (null != studentdto.getSession2Date()) {

					studentdto.setSession2DateStr(TimeUtil.getDateFromMillis(studentdto.getSession2Date().getTime(),TimeUtil.REPORT_DATE_FORMAT));
				} else {
					studentdto.setSession2DateStr("-");
				}
				
				dhstudentExtraDetailDTO.add(studentdto);
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
		return dhstudentExtraDetailDTO;
	}

}
