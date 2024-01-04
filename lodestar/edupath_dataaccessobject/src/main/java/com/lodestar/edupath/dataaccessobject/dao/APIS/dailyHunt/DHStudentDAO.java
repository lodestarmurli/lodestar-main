package com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt;

import org.apache.ibatis.session.SqlSession;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DHStudentDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(DHStudentDAO.class);
	
	public  DHStudentDetailsDTO insertStudent(SqlSession session, DHStudentDetailsDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.INSERT_DH_STUDENT, studentDTO);
		OUT.debug("Insert {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		return studentDTO;
		
	}
	public  DHStudentDetailsDTO getDHStudentByDHID(SqlSession session, DHStudentDetailsDTO studentDTO)
	{
		DHStudentDetailsDTO dto = null;
		try
		{
			dto = (DHStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DHSTUDENT_DETAILS_BY_DHID, studentDTO.getDHID());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public  DHStudentDetailsDTO getDHStudentByID(DHStudentDetailsDTO studentDTO)
	{
		DHStudentDetailsDTO dto = null;
		try
		{
			dto = (DHStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DHSTUDENT_DETAILS_BY_ID, studentDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public int updateDHStudentType(SqlSession session, DHStudentDetailsDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.UPDATE_DH_STUDENT_TYPE, studentDTO);
		OUT.debug("Update {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		
		return primaryKeyId;
	}

}
