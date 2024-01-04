package com.lodestar.edupath.dataaccessobject.dao.studentfeedback;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.studentfeedback.StudentFeedbackFormDTO;

public class StudentFeedbackFormDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentFeedbackFormDAO.class);

	/**
	 * @param feedbackFormDTO
	 * @return
	 * @throws Exception
	 */
	public List<IModel> getStudentFeedbackFormByStudentId(StudentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Getting student feedback questions for student id: {}", feedbackFormDTO.getStudentId());
		List<IModel> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_GET_BY_STUDENT_ID,
				feedbackFormDTO);
		OUT.debug("Number of Student feedback questions found : {} for student id: {}", resultList != null ? resultList.size() : 0, feedbackFormDTO.getStudentId());
		return resultList;

	}

	/**
	 * @param sqlSession
	 * @param feedbackFormDTO
	 * @throws Exception
	 */
	public void insertStudentFeedbackForm(SqlSession sqlSession, StudentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Inserting student feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
		if (sqlSession != null)
		{
			sqlSession.insert(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_INSERT, feedbackFormDTO);
		}
		else
		{
			MyBatisManager.getInstance().insert(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_INSERT, feedbackFormDTO);
		}
		OUT.debug("Inserted student feedback questions insertid: {} for student id: {}, questionNo: {}", feedbackFormDTO.getId(), feedbackFormDTO.getStudentId(),
				feedbackFormDTO.getQuestionNo());
	}

	/**
	 * @param sqlSession
	 * @param feedbackFormDTO
	 * @throws Exception
	 */
	public void updateStudentFeedbackForm(SqlSession sqlSession, StudentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Updating student feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
		if (sqlSession != null)
		{
			sqlSession.update(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_UPDATE, feedbackFormDTO);
		}
		else
		{
			MyBatisManager.getInstance().update(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_UPDATE, feedbackFormDTO);
		}
		OUT.debug("Updated student feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
	}

	/**
	 * @param feedbackFormDTO
	 * @return
	 * @throws Exception
	 */
	public boolean isAnsweredStudentFeedbackForm(StudentFeedbackFormDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Checking is answered student feedback question student id: {}, questionNo: {}", feedbackFormDTO.getId(), feedbackFormDTO.getStudentId(),
				feedbackFormDTO.getQuestionNo());
		boolean isAnswered = false;
		Integer uniqueCount = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_IS_ANSWERED, feedbackFormDTO);
		if (uniqueCount != null && uniqueCount.intValue() > 0)
		{
			isAnswered = true;
		}
		return isAnswered;
	}
}
