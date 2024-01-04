package com.lodestar.edupath.datatransferobject.dto.TipsAndSuggestions;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class GSInputDTO implements IModel{

	
	private static final long	serialVersionUID	= 1L;
	
	private int 			id;
	private int				studentid;
	private int				questionno;
	private String				answer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getQuestionno() {
		return questionno;
	}
	public void setQuestionno(int questionno) {
		this.questionno = questionno;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
