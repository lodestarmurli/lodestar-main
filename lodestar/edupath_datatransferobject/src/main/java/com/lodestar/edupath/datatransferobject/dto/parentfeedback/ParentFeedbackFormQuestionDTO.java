package com.lodestar.edupath.datatransferobject.dto.parentfeedback;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ParentFeedbackFormQuestionDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					questionNo;
	private String				question;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getQuestionNo()
	{
		return questionNo;
	}

	public void setQuestionNo(int questionNo)
	{
		this.questionNo = questionNo;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

}
