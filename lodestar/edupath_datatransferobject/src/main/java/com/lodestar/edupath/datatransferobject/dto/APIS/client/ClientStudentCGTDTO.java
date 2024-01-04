package com.lodestar.edupath.datatransferobject.dto.APIS.client;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ClientStudentCGTDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					questionId;
	private String				questionIdStr;
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

	public int getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public String getQuestionIdStr() {
		return questionIdStr;
	}

	public void setQuestionIdStr(String questionIdStr) {
		this.questionIdStr = questionIdStr;
	}

	@Override
	public String toString() {
		return "StudentCGTDTO [id=" + id + ", studentId=" + studentId + ", questionId=" + questionId
				+ ", questionIdStr=" + questionIdStr + ", answer=" + answer + "]";
	}

	
}
