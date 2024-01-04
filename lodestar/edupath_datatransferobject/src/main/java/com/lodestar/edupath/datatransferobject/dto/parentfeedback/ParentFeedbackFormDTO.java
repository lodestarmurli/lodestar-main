package com.lodestar.edupath.datatransferobject.dto.parentfeedback;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ParentFeedbackFormDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					questionNo;
	private String				answer;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getQuestionNo()
	{
		return questionNo;
	}

	public void setQuestionNo(int questionNo)
	{
		this.questionNo = questionNo;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
}
