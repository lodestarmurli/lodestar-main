package com.lodestar.edupath.datatransferobject.dto.sessionfeedback;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class SessionFeedBackAnswerDTO implements IModel{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					questionNo;
	private String				answer;
	private int				    Forsession;

	public int getForsession() {
		return Forsession;
	}

	public void setForsession(int forsession) {
		Forsession = forsession;
	}

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

	@Override
	public String toString() {
		return "SessionFeedBackAnswerDTO [id=" + id + ", studentId=" + studentId + ", questionNo=" + questionNo
				+ ", answer=" + answer + ", Forsession=" + Forsession + "]";
	}
	
}
