package com.lodestar.edupath.collegeshortlist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.collegeshortlist.StudentCollegeShortListDAO;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class CollegeShortListService
{
	private static final Logger OUT = LoggerFactory.getLogger(CollegeShortListService.class);
	public List<StudentCollegeShortListDTO> getShortListedCollege(int studentId) throws Exception
	{
		return new StudentCollegeShortListDAO().getShortListedCollegeByStudentId(studentId);
	}

	public void deleteColShortListById(int colShortListId, int studentId, String userLoginId) throws Exception
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			new StudentCollegeShortListDAO().deleteColShortListById(session, colShortListId);
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.COLLEGE_CART, "College ShortList id : " + colShortListId +  " is deleted from CollegeShortList for student Id : " + studentId, userLoginId);
			session.commit();
		}
		catch (Exception e)
		{
			if(null != session)
			{
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (null != session)
			{
				session.close();
			}
		}
		
	}

}
