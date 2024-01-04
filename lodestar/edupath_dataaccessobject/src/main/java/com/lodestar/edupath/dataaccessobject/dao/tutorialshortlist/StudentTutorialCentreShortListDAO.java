package com.lodestar.edupath.dataaccessobject.dao.tutorialshortlist;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;

public class StudentTutorialCentreShortListDAO
{
	private static final Logger OUT = LoggerFactory.getLogger(StudentTutorialCentreShortListDAO.class);

	public void insertShortListTutorialCenter(SqlSession session, StudentTutorialCentreShortListDTO centreShortListDTO)
	{
		session.insert(MyBatisMappingConstants.STUDENT_TUTORIAL_CENTRE_SHORTLIST_INSERT, centreShortListDTO);
	}

	public List<StudentTutorialCentreShortListDTO> getShortListedTutorialCentersByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting shortlisted tutorial centers for student id : {}", studentId);
		List<StudentTutorialCentreShortListDTO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_TUTORIAL_CENTRE_SHORTLIST_GET_BY_STUDENTID, studentId);
		OUT.debug("Number of shortlisted tutorial centers found : {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}

	public void deleteTutCenterShortListById(SqlSession session, int id) throws Exception
	{
		OUT.debug("deleting shortlisted tutorial centers for id : {}", id);
		session.delete(MyBatisMappingConstants.STUDENT_TUTORIAL_CENTRE_SHORTLIST_DELETE_BY_ID, id);
	}

	public void deleteTutCenterShortListById(int id) throws Exception
	{
		OUT.debug("deleting shortlisted tutorial centers for id : {}", id);
		MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.STUDENT_TUTORIAL_CENTRE_SHORTLIST_DELETE_BY_ID, id);
	}

	public List<StudentTutorialCentreShortListDTO> getShortListedTutorialCountByStudentId(int studentId) throws Exception
	{
		List<StudentTutorialCentreShortListDTO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_TUTORIAL_CENTRE_SHORTLIST_GET_ALL_BY_STUDENTID, studentId);
		OUT.debug("Number of shortlisted tutorial centers found : {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}
	
	
}
