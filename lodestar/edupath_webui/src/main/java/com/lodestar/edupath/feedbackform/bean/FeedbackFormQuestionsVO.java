package com.lodestar.edupath.feedbackform.bean;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.parentfeedback.ParentFeedbackFormQuestionDTO;
import com.lodestar.edupath.datatransferobject.dto.studentfeedback.StudentFeedbackFormQuestionDTO;

public class FeedbackFormQuestionsVO implements Serializable
{
	private static final long				serialVersionUID	= 1L;

	private ParentFeedbackFormQuestionDTO	parentFormQuestion;
	private StudentFeedbackFormQuestionDTO	studentFormQuestion;
	private Boolean							isRadioBox;

	public Boolean getIsRadioBox()
	{
		return isRadioBox;
	}

	public void setIsRadioBox(Boolean isRadioBox)
	{
		this.isRadioBox = isRadioBox;
	}

	public ParentFeedbackFormQuestionDTO getParentFormQuestion()
	{
		return parentFormQuestion;
	}

	public void setParentFormQuestion(ParentFeedbackFormQuestionDTO parentFormQuestion)
	{
		this.parentFormQuestion = parentFormQuestion;
	}

	public StudentFeedbackFormQuestionDTO getStudentFormQuestion()
	{
		return studentFormQuestion;
	}

	public void setStudentFormQuestion(StudentFeedbackFormQuestionDTO studentFormQuestion)
	{
		this.studentFormQuestion = studentFormQuestion;
	}
}
