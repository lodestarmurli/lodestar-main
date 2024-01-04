package com.lodestar.edupath.dataaccessobject.dao.ShortListExams;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.ShortListExamsDTO;

public class ShortListExamsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ShortListExamsDAO.class);

	public void insertShortListEntrExams(SqlSession session, ShortListExamsDTO shortListExamsDTO) throws Exception
	{
		// MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_ALL_SHORTLIST_EXAMS, shortListExamsDTO);
		session.insert(MyBatisMappingConstants.INSERT_SHORTLIST_EXAMS, shortListExamsDTO);
	}

	public boolean isIdExist(ShortListExamsDTO shortListExamsDTO)
	{
		try
		{
			int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_COUNT_FOR_INSERT, shortListExamsDTO);
			OUT.debug("count value:{}", count);
			if (count > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return false;
	}

	public void removeShortListExamByExamIdNStudentId(ShortListExamsDTO shortListExamsDTO) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_SHORTLIST_EXAM_BY_EXAMID_N_STUDENTID, shortListExamsDTO);
	}
	
	public void removeShortListExamByStudentId(SqlSession session, ShortListExamsDTO shortListExamsDTO) throws Exception
	{
		session.delete(MyBatisMappingConstants.DELETE_SHORTLIST_EXAM_BY_STUDENTID, shortListExamsDTO);
	}
	
	public int getShortListExamCountByOccId(ShortListExamsDTO shortListExamDTO) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_SHORT_LIST_EXAM_COUNT_BY_OCCID, shortListExamDTO);
		OUT.debug("Short list exam count: {}", count);
		return count;
	}

	public int getShortListExamsCount(int studentId) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_SHORT_LIST_EXAM_COUNT, studentId);
		OUT.debug("Short list exam count: {}", count);
		return count;
	}

	
//EXTRRA ADDED
	public String getExamStudentCityId(int i) throws Exception {
		OUT.debug("Student Exam student Id: {}", i);
		//String count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_SHORT_LIST_EXAM_CITY_BY_STUDENTID, i);
//		OUT.debug("Student Exam Id: {}", count);
		
		String count= (String) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_SHORT_LIST_EXAM_CITY_BY_STUDENTID, i);
		return count;
	}

	public void removeFromShortListExams(ShortListExamsDTO shortListExamsDTO) throws Exception {
		// TODO Auto-generated method stub
		OUT.debug("Student Exam student Id: {}=============================================================");
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_PREVIOUS_EXAM_BY_CITY_N_STUDENTID, shortListExamsDTO);
	}

	
}
