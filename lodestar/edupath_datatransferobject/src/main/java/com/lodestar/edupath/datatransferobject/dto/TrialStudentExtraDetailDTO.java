package com.lodestar.edupath.datatransferobject.dto;

public class TrialStudentExtraDetailDTO implements IModel
{
	private static final long			serialVersionUID	= 1L;
	
	private int							id;
	private String						studentemailId;
	private int							studentId;
	private String						studentcontactNumber;
	private String						motherName;
	private String						motheremailId;
	private String						mothercontactno;
	private String						fatherName;
	private String						fatherEmailId;
	private String						fathercontactno;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentemailId() {
		return studentemailId;
	}
	public void setStudentemailId(String studentemailId) {
		this.studentemailId = studentemailId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentcontactNumber() {
		return studentcontactNumber;
	}
	public void setStudentcontactNumber(String studentcontactNumber) {
		this.studentcontactNumber = studentcontactNumber;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotheremailId() {
		return motheremailId;
	}
	public void setMotheremailId(String motheremailId) {
		this.motheremailId = motheremailId;
	}
	public String getMothercontactno() {
		return mothercontactno;
	}
	public void setMothercontactno(String mothercontactno) {
		this.mothercontactno = mothercontactno;
	}
	@Override
	public String toString() {
		return "TrialStudentExtraDetailDTO [id=" + id + ", studentemailId=" + studentemailId + ", studentId="
				+ studentId + ", studentcontactNumber=" + studentcontactNumber + ", motherName=" + motherName
				+ ", motheremailId=" + motheremailId + ", mothercontactno=" + mothercontactno + "]";
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public String getFathercontactno() {
		return fathercontactno;
	}
	public void setFathercontactno(String fathercontactno) {
		this.fathercontactno = fathercontactno;
	}
	public String getFatherEmailId() {
		return fatherEmailId;
	}
	public void setFatherEmailId(String fatherEmailId) {
		this.fatherEmailId = fatherEmailId;
	}
	

}
