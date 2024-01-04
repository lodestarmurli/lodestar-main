package com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DHStudentExtraDetailsDAO {

	private static final Logger	OUT	= LoggerFactory.getLogger(DHStudentExtraDetailsDAO.class);
	
	public  int insertDHStudentExtraDetails(SqlSession session, DHStudentExtraDetailDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.INSERT_DH_STUDENT_EXTRA_DETAILS, studentDTO);
		OUT.debug("Insert {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		return primaryKeyId;
		
	}
	public  DHStudentExtraDetailDTO getDHStudentExtraDetailsByStudentId(SqlSession session, int studentId)
	{
		DHStudentExtraDetailDTO dto = null;
		try
		{
			dto = (DHStudentExtraDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DHSTUDENT_EXTRA_DETAILS_BY_STUDENTID, studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public  DHStudentExtraDetailDTO getDHStudentExtraDetailsByID(DHStudentExtraDetailDTO studentDTO)
	{
		DHStudentExtraDetailDTO dto = null;
		try
		{
			dto = (DHStudentExtraDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DHSTUDENT_DETAILS_BY_ID, studentDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public int DHStudentExtraDetailDTO(SqlSession session, DHStudentExtraDetailDTO studentDTO)
	{
		int primaryKeyId = -1;
		primaryKeyId = session.insert(MyBatisMappingConstants.UPDATE_DH_STUDENT_TYPE, studentDTO);
		OUT.debug("Update {}", primaryKeyId <= 0 ? "FAILUER" : "SUCESSFULLY");
		
		return primaryKeyId;
	}
	
	public List<DHStudentExtraDetailDTO> getAllDHStudentExtraDetails()
	{
		List<DHStudentExtraDetailDTO> dhstudentExtraDetailDTO = new ArrayList<DHStudentExtraDetailDTO>();
		
		try
		{
			dhstudentExtraDetailDTO = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_DHSTUDENT_EXTRA_DETAILS, null);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		return dhstudentExtraDetailDTO;
		
	}
	
	public  DHStudentExtraDetailDTO getDHSEDByStudentIdForConversion(SqlSession session, int studentId)
	{
		DHStudentExtraDetailDTO dto = null;
		try
		{
			dto = (DHStudentExtraDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DHSTUDENT_EXTRA_DETAILS_BY_STUDENTID, studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	
	public  DHStudentExtraDetailDTO getCompleteStudentDetailsByStudentId(int studentId)
	{
		DHStudentExtraDetailDTO dto = null;
		try
		{
			dto = (DHStudentExtraDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_COMPLETE_DHSTUDENT_DETAILS_BY_STUDENTID, studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}
	
	public int updateDHStudentConverted(SqlSession session, int dhStudentId) throws Exception
	{
		int id = session.update(MyBatisMappingConstants.UPDATE_DHSTUDENT_CONVERSION, dhStudentId);
		OUT.debug("Insert StudentTUM Id: {}" , dhStudentId);
		return id;
	}
	

}
