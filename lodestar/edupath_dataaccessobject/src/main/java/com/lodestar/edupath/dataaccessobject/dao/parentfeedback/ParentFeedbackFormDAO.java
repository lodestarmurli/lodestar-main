package com.lodestar.edupath.dataaccessobject.dao.parentfeedback;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.parentfeedback.ParentFeedbackFormDTO;

public class ParentFeedbackFormDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ParentFeedbackFormDAO.class);

	/**
	 * @param feedbackFormDTO
	 * @return
	 * @throws Exception
	 */
	public List<IModel> getParentFeedbackFormByStudentId(ParentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Getting parent feedback questions for student id: {}", feedbackFormDTO.getStudentId());
		List<IModel> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_GET_BY_STUDENT_ID, feedbackFormDTO);
		OUT.debug("Number of Parent feedback questions found : {} for student id: {}", resultList != null ? resultList.size() : 0, feedbackFormDTO.getStudentId());
		return resultList;

	}

	/**
	 * @param sqlSession
	 * @param feedbackFormDTO
	 * @throws Exception
	 */
	public void insertParentFeedbackForm(SqlSession sqlSession, ParentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Inserting parent feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
		if (sqlSession != null)
		{
			sqlSession.insert(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_INSERT, feedbackFormDTO);
		}
		else
		{
			MyBatisManager.getInstance().insert(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_INSERT, feedbackFormDTO);
		}
		OUT.debug("Inserted parent feedback questions insertid: {} for student id: {}, questionNo: {}", feedbackFormDTO.getId(), feedbackFormDTO.getStudentId(),
				feedbackFormDTO.getQuestionNo());
	}

	/**
	 * @param sqlSession
	 * @param feedbackFormDTO
	 * @throws Exception
	 */
	public void updateParentFeedbackForm(SqlSession sqlSession, ParentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Updating parent feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
		if (sqlSession != null)
		{
			sqlSession.update(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_UPDATE, feedbackFormDTO);
		}
		else
		{
			MyBatisManager.getInstance().update(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_UPDATE, feedbackFormDTO);
		}
		OUT.debug("Updated parent feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
	}

	/**
	 * @param sqlSession
	 * @param feedbackFormDTO
	 * @return
	 * @throws Exception
	 */
	public boolean isAnsweredParentFeedbackForm(SqlSession sqlSession, ParentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Checking is answered parent feedback question student id: {}, questionNo: {}", feedbackFormDTO.getId(), feedbackFormDTO.getStudentId(),
				feedbackFormDTO.getQuestionNo());
		boolean isAnswered = false;
		Integer uniqueCount = sqlSession.selectOne(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_IS_ANSWERED, feedbackFormDTO);
		if (uniqueCount != null && uniqueCount.intValue() > 0)
		{
			isAnswered = true;
		}
		return isAnswered;
	}
}
