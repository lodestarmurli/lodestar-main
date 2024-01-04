package com.lodestar.edupath.dataaccessobject.dao.shortlistcourse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.ShortListCourseDTO;

public class ShortListCourseDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ShortListCourseDAO.class);

	public void insertShortListCourse(SqlSession session, ShortListCourseDTO shortListCourseDTO) throws Exception
	{
		session.insert(MyBatisMappingConstants.INSERT_SHORTLIST_COURSE, shortListCourseDTO);
	}

	public boolean isIdExist(ShortListCourseDTO shortListCourseDTO)
	{
		try
		{
			int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_COUNT_FOR_INSERT_COURSE, shortListCourseDTO);
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

	public void removeShortListCourseByCourseIdNStudentId(ShortListCourseDTO shortListCourseDTO) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectByModel(MyBatisMappingConstants.DELETE_SHORTLIST_COURSE_BY_COURSEID_N_STUDENTID, shortListCourseDTO);
	}

	public void removeShortListCourseByStudentId(SqlSession session, ShortListCourseDTO shortListCourseDTO) throws Exception
	{
		session.delete(MyBatisMappingConstants.DELETE_SHORTLIST_COURSE_BY_STUDENTID, shortListCourseDTO);
	}

	public int getShortListCount(ShortListCourseDTO shortListDTO) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_SHORT_COURSE_LIST_COUNT_BY_OCC_ID, shortListDTO);
		OUT.debug("Short List course count: {}", count);
		return count;
	}

	public int getCourseCount(int studentId) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_SHORT_LIST_COURSE_COUNT, studentId);
		OUT.debug("Short List course count: {}", count);
		return count;
	}
}
