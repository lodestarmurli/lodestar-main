package com.lodestar.edupath.dataaccessobject.dao.parentfeedback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.parentfeedback.ParentFeedbackFormQuestionDTO;

public class ParentFeedbackFormQuestionsDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ParentFeedbackFormQuestionsDAO.class);

	/**
	 * @return
	 * @throws Exception
	 */
	public List<IModel> getParentFeedbackFormQuestion() throws Exception
	{
		OUT.debug("Getting parent feed back questions");
		List<IModel> feedbackFormQuestionDTOs = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.PARENT_FEED_BACK_FORM_QUESTION_GET, null);
		OUT.debug("Number of questions found: {}", feedbackFormQuestionDTOs != null ? feedbackFormQuestionDTOs.size() : 0);
		return feedbackFormQuestionDTOs;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<ParentFeedbackFormQuestionDTO> getAllParentFeedbackFormQuestion() throws Exception
	{
		OUT.debug("Getting parent feed back questions");
		List<ParentFeedbackFormQuestionDTO> feedbackFormQuestionDTOs = MyBatisManager.getInstance().getResultList(
				MyBatisMappingConstants.PARENT_FEED_BACK_FORM_QUESTION_GET, null);
		OUT.debug("Number of questions found: {}", feedbackFormQuestionDTOs != null ? feedbackFormQuestionDTOs.size() : 0);
		return feedbackFormQuestionDTOs;
	}
}
