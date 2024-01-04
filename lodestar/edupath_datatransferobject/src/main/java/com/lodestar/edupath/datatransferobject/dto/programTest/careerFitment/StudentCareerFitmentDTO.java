package com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class StudentCareerFitmentDTO  implements IModel
{
	private static final long	serialVersionUID	= 1L;
	
	private int 		id;
	private int  		studentId;
	private int 		optionA;
	private int 		optionB;
	private String 		clusterId;
	private String 		occupationId;
	private int 		approved;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getOptionA() {
		return optionA;
	}
	public void setOptionA(int optionA) {
		this.optionA = optionA;
	}
	public int getOptionB() {
		return optionB;
	}
	public void setOptionB(int optionB) {
		this.optionB = optionB;
	}
	public String getClusterId() {
		return clusterId;
	}
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	public String getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "StudentCareerFitmentDTO [id=" + id + ", studentId=" + studentId + ", optionA=" + optionA + ", optionB="
				+ optionB + ", clusterId=" + clusterId + ", occupationId=" + occupationId + ", approved=" + approved
				+ "]";
	}
	
}
