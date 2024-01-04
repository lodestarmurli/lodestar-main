package com.lodestar.edupath.sessionfeedback.action;

import java.io.Serializable;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class SessionFeedBackFromBean implements Serializable{
	private static final long	serialVersionUID	= 1L;
	private List<IModel>		feedbackQuestions;
	private String				feedbackAnswered;
	public String getFeedbackAnswered() {
		return feedbackAnswered;
	}
	public void setFeedbackAnswered(String feedbackAnswered) {
		this.feedbackAnswered = feedbackAnswered;
	}
	public List<IModel> getFeedbackQuestions() {
		return feedbackQuestions;
	}
	public void setFeedbackQuestions(List<IModel> feedbackQuestions) {
		this.feedbackQuestions = feedbackQuestions;
	}
}
