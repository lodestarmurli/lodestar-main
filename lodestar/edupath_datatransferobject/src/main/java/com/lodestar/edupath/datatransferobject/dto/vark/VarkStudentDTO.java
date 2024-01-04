package com.lodestar.edupath.datatransferobject.dto.vark;

import java.util.Date;

public class VarkStudentDTO {
	
	private int 		id;
	private String		LDid;
	private int			studentId;
	private String		token;
	private Date		studentRegisterTime;
	private Date		studentTestTime;
	private int			testTaken;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLdId() {
		return LDid;
	}
	public void setLdId(String ldId) {
		this.LDid = ldId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getStudentRegisterTime() {
		return studentRegisterTime;
	}
	public void setStudentRegisterTime(Date studentRegisterTime) {
		this.studentRegisterTime = studentRegisterTime;
	}
	public Date getStudentTestTime() {
		return studentTestTime;
	}
	public void setStudentTestTime(Date studentTestTime) {
		this.studentTestTime = studentTestTime;
	}
	public int getTestTaken() {
		return testTaken;
	}
	public void setTestTaken(int testTaken) {
		this.testTaken = testTaken;
	}
	@Override
	public String toString() {
		return "VarkStudentDTO [id=" + id + ", LDid=" + LDid + ", studentId=" + studentId + ", token=" + token
				+ ", studentRegisterTime=" + studentRegisterTime + ", studentTestTime=" + studentTestTime
				+ ", testTaken=" + testTaken + "]";
	}
	
	
	

}
