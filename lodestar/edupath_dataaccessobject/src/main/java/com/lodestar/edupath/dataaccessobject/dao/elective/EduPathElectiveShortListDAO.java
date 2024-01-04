package com.lodestar.edupath.dataaccessobject.dao.elective;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathElectiveShortListDTO;

public class EduPathElectiveShortListDAO
{
	private static Logger	OUT	= LoggerFactory.getLogger(EduPathShortListDAO.class);

	public void updateShortListById(SqlSession session, EduPathElectiveShortListDTO shortListDTO)
	{
		session.update(MyBatisMappingConstants.UPDATE_ELECTIVE_SHORT_LIST_BY_ID, shortListDTO);
	}

	public void removeEdupathElectiveShortList(SqlSession session, int studentId) throws Exception
	{
		OUT.debug("Removing Elective ShortList for studentId: {}", studentId);
		session.delete(MyBatisMappingConstants.DELETE_EDUPATH_ELECTIVE_SHORTLIST_BY_STUDENTID, studentId);
	}

	public List<EduPathElectiveShortListDTO> getEduPathElectiveShortListByStudentId(int studentId) throws Exception
	{
		OUT.debug("EduPathElectiveShortList student id : {}", studentId);
		List<EduPathElectiveShortListDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_EDUPATH_ELECTIVE_SHORTLIST_BYSTUDENTID,
				studentId);
		OUT.debug("EduPathElectiveShortList result  : {}", (list != null ? list.size() : 0));
		return list;
	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getCombinationsByStudentId(int studentId) throws Exception
	{
		OUT.debug("EduPathElectiveShortList student id : {}", studentId);
		List<Integer> combinationIds = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_COMBINATIONS_BY_STUDENT_ID, studentId);
		OUT.debug("EduPathElectiveShortList result  : {}", (combinationIds != null ? combinationIds.size() : 0));
		return combinationIds;
	}
}
