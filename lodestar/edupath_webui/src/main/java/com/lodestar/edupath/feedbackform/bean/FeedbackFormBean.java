package com.lodestar.edupath.feedbackform.bean;

import java.io.Serializable;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class FeedbackFormBean implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private Boolean				parentFeedbackStatus;
	private Boolean				studentFeedbackStatus;
	private List<IModel>		feedbackQuestions;
	private String				feedbackAnswered;
	private String				feedback;
	private int					autoSaveInterval	= 60;

	private int					studentId;
	private int					studentUserId;

	public Boolean getParentFeedbackStatus()
	{
		return parentFeedbackStatus;
	}

	public void setParentFeedbackStatus(Boolean parentFeedbackStatus)
	{
		this.parentFeedbackStatus = parentFeedbackStatus;
	}

	public Boolean getStudentFeedbackStatus()
	{
		return studentFeedbackStatus;
	}

	public void setStudentFeedbackStatus(Boolean studentFeedbackStatus)
	{
		this.studentFeedbackStatus = studentFeedbackStatus;
	}

	public int getStudentUserId()
	{
		return studentUserId;
	}

	public void setStudentUserId(int studentUserId)
	{
		this.studentUserId = studentUserId;
	}

	public List<IModel> getFeedbackQuestions()
	{
		return feedbackQuestions;
	}

	public void setFeedbackQuestions(List<IModel> feedbackQuestions)
	{
		this.feedbackQuestions = feedbackQuestions;
	}

	public String getFeedback()
	{
		return feedback;
	}

	public void setFeedback(String feedback)
	{
		this.feedback = feedback;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public String getFeedbackAnswered()
	{
		return feedbackAnswered;
	}

	public void setFeedbackAnswered(String feedbackAnswered)
	{
		this.feedbackAnswered = feedbackAnswered;
	}

	public int getAutoSaveInterval()
	{
		return autoSaveInterval;
	}

	public void setAutoSaveInterval(int autoSaveInterval)
	{
		this.autoSaveInterval = autoSaveInterval;
	}

}
