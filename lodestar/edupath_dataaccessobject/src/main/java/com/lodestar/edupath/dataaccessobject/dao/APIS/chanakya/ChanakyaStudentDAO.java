package com.lodestar.edupath.dataaccessobject.dao.APIS.chanakya;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.chanakya.ChanakyaStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ChanakyaStudentDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(ChanakyaStudentDAO.class);
	
	public  ChanakyaStudentDetailsDTO insertStudent(SqlSession session, ChanakyaStudentDetailsDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.INSERT_CHANAKYA_STUDENT, studentDTO);
		OUT.debug("Insert {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		return studentDTO;
		
	}
	public  ChanakyaStudentDetailsDTO getChanakyaStudentByCHNKID(SqlSession session, ChanakyaStudentDetailsDTO studentDTO)
	{
		ChanakyaStudentDetailsDTO dto = null;
		try
		{
			dto = (ChanakyaStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CHANAKYASTUDENT_DETAILS_BY_CHNKID, studentDTO.getCHNKID());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public  ChanakyaStudentDetailsDTO getChanakyaStudentByID(ChanakyaStudentDetailsDTO studentDTO)
	{
		ChanakyaStudentDetailsDTO dto = null;
		try
		{
			dto = (ChanakyaStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CHANAKYASTUDENT_DETAILS_BY_ID, studentDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public int updateChanakyaStudentType(SqlSession session, ChanakyaStudentDetailsDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.UPDATE_CHANAKYA_STUDENT_TYPE, studentDTO);
		OUT.debug("Update {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		
		return primaryKeyId;
	}

}
