package com.lodestar.edupath.dataaccessobject.dao.stuinduocchoice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;

public class MyShortListDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(MyShortListDAO.class);

	public List<ShortListDTO> getShortListDetailsByStudentId(int studentId) throws Exception
	{

		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SHORT_LIST_DETAILS_BY_STUDENT_ID, studentId);
	}

	public void updateMyShortListById(SqlSession session, ShortListDTO shortListDTO) throws Exception
	{
		session.update(MyBatisMappingConstants.UPDATE_SHORT_LIST_BY_ID, shortListDTO);
	}

	public void insertShortList(ShortListDTO shortListDTO) throws Exception
	{
		MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_SHORT_LIST, shortListDTO);
	}

	public void deleteShortListById(int shortListId) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_SHORT_LIST_BY_ID, shortListId);
	}
	
	public List<ShortListDTO> getTopShortListDetailsByStudentId(int studentId) throws Exception
	{
        OUT.debug("Student Id:" + studentId);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_TOP_SHORTLIST_BY_STUDENTID, studentId);
	}
}
