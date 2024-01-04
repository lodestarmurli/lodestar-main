package com.lodestar.edupath.datatransferobject.dto.cgt;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CGTQuestioneriesDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				question;
	private String				section;
	private String				slNo;
	private String				factor;
	private String				correctAnswer;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getSection()
	{
		return section;
	}

	public void setSection(String section)
	{
		this.section = section;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getFactor()
	{
		return factor;
	}

	public void setFactory(String factory)
	{
		this.factor = factory;
	}

	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

}
