package com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt;

public class DHEntExamDTO {

	private static final long	serialVersionUID	= 1L;

	private int 	id;
	private String	exam;
	private	String	description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "DHEntExamDTO [id=" + id + ", exam=" + exam + ", description=" + description + "]";
	}
	
	
	
	
}
