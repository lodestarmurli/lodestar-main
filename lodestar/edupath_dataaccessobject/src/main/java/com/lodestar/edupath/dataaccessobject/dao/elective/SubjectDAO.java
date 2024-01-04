package com.lodestar.edupath.dataaccessobject.dao.elective;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.SubjectStreamCombinationVO;

public class SubjectDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(SubjectDAO.class);

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<SubjectDTO> getSubjectListByStudentId(Integer studentId) throws Exception
	{
		OUT.debug("Getting subject for studentId : {}", studentId);
		List<SubjectDTO> subjectList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SUBJECT_BY_STUDENTID, studentId.intValue());
		OUT.debug("Subjects {} for studentId : {}", subjectList != null ? " FOUND " : " NOT FOUND ", studentId);
		return subjectList;
	}

	public List<SubjectDTO> getSubjectsByIds(List<Integer> subjectIds) throws Exception
	{
		List<SubjectDTO> resultList = new ArrayList<SubjectDTO>();
		if (subjectIds != null && !subjectIds.isEmpty())
		{
			resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SUBJECT_BY_IDS, subjectIds);
		}
		return resultList;
	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public SubjectStreamCombinationVO getSubStreamCombinationByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting subject for studentId : {}", studentId);
		SubjectStreamCombinationVO subStreamCombinationVO = (SubjectStreamCombinationVO) MyBatisManager.getInstance().getResultAsObject(
				MyBatisMappingConstants.GET_SUB_STREAMN_COMBINATION_BY_STUDENT_ID, studentId);
		OUT.debug("Subjects {} for studentId : {}", subStreamCombinationVO != null ? " FOUND " : " NOT FOUND ", studentId);
		return subStreamCombinationVO;
	}
	
	public List<Object> getSubjectDetailsByStudentId(int studentId) throws Exception
	{
		OUT.debug("Getting subject details by studentId : {}", studentId);
		List<Object> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_UNIQUE_SUB_BY_STUDENT_ID, studentId);
		OUT.debug("Number of subject details found : {}", resultList !=null ? resultList.size() : 0);
		return resultList;
	}

}
