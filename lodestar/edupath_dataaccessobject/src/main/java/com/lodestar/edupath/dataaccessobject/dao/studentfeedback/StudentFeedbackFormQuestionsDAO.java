package com.lodestar.edupath.dataaccessobject.dao.studentfeedback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StudentFeedbackFormQuestionsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(StudentFeedbackFormQuestionsDAO.class);

	/**
	 * @return
	 * @throws Exception
	 */
	public List<IModel> getStudentFeedbackFormQuestion() throws Exception
	{
		OUT.debug("Getting sudent feed back questions");
		List<IModel> feedbackFormQuestionDTOs = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.STUDENT_FEED_BACK_FORM_QUESTION_GET, null);
		OUT.debug("Number of questions found: {}", feedbackFormQuestionDTOs != null ? feedbackFormQuestionDTOs.size() : 0);
		return feedbackFormQuestionDTOs;
	}
}
