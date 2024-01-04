package com.lodestar.edupath.dataaccessobject.dao.tum;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class StudentTUMDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentTUMDAO.class);
	
	public int insertORUpdateStudentTUM(SqlSession session, StudentTUMDTO studentTUM) throws Exception
	{
		int id = session.insert(MyBatisMappingConstants.INSERT_UPDATE_STUDENT_TUM, studentTUM);
		OUT.debug("Insert StudentTUM Id: {}" , studentTUM.getId());
		return studentTUM.getId();
	}
	
	public int updateStudentTUM(SqlSession session, StudentTUMDTO studentTUM) throws Exception
	{
		int id = session.update(MyBatisMappingConstants.UPDATE_STUDENT_TUM, studentTUM);
		OUT.debug("Insert StudentTUM Id: {}" , studentTUM.getId());
		return id;
	}
	
	public void deleteStudentTUMById(StudentTUMDTO studentTUM) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_STUDENT_TUM_BYID, studentTUM);
	}
	
	public List<StudentTUMDTO> getSuduntTUMDetailsById(StudentTUMDTO studentTUM) throws Exception
	{
		 List<StudentTUMDTO> result = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENTTUM_BY_ID, studentTUM);
		 OUT.debug("GET StudentTUM size: {}" , (result != null ? result.size() : 0));
		 return result;
	}
	
	//start by bharath 5/7/2019
	
		public List<StudentTUMDTO> getSuduntTUMListById(StudentTUMDTO studentTUM) throws Exception
		{
			 List<StudentTUMDTO> result = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENTTUM_LIST_BY_ID, studentTUM);
			 OUT.debug("GET getSuduntTUMListById StudentTUM size: {}" , (result != null ? result.size() : 0));
			 return result;
		}
		
		
	//end by bharath 5/7/2019

	public List<StudentTUMDTO> getStudentTUMDetailsByStudentId(StudentTUMDTO tumdto)
	{
		List<StudentTUMDTO> resultAsList = null;
		try
		{
			resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_STUDENT_TUM_FORSTUDENT, tumdto);
			OUT.debug("Student List Size {}", null != resultAsList ? resultAsList.size() : 0);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultAsList;
	}

	public void deleteByQuestionSlNo(StudentTUMDTO tumdto) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_STUDENT_TUM_BY_QuestionSlNo, tumdto);
	}
	
	/*public List<StudentTUMDTO> getSuduntTUMDetailsByStudentId(StudentTUMDTO tumdto)
	{
		List<StudentTUMDTO> result = null;
		try
		{
			result = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENTTUM_BY_STUDENT_ID, tumdto);
			 OUT.debug("GET StudentTUM size: " , (result != null ? result.size() : 0));
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return result;
		
	}*/
}
