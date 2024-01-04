package com.lodestar.edupath.dataaccessobject.dao.APIS.client;

import org.apache.ibatis.session.SqlSession;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientStudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientStudentDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(ClientStudentDAO.class);
	
	public  ClientStudentDetailsDTO insertStudent(SqlSession session, ClientStudentDetailsDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.INSERT_CLIENT_STUDENT, studentDTO);
		OUT.debug("Insert {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		return studentDTO;
		
	}
	public  ClientStudentDetailsDTO getDHStudentByDHID(SqlSession session, ClientStudentDetailsDTO studentDTO)
	{
		ClientStudentDetailsDTO dto = null;
		try
		{
			dto = (ClientStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLIENTSTUDENT_DETAILS_BY_CLIENTID, studentDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public  ClientStudentDetailsDTO getDHStudentByID(ClientStudentDetailsDTO studentDTO)
	{
		ClientStudentDetailsDTO dto = null;
		try
		{
			dto = (ClientStudentDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_CLIENTSTUDENT_DETAILS_BY_ID, studentDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public int updateDHStudentType(SqlSession session, ClientStudentDetailsDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.UPDATE_CLIENT_STUDENT_TYPE, studentDTO);
		OUT.debug("Update {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		
		return primaryKeyId;
	}

}
