package com.lodestar.edupath.facilitator.action;

import java.io.Serializable;

import com.lodestar.edupath.util.datatable.DataTableAnnotation;

public class SessionscheduleVO implements Serializable {

//	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private static final long	serialVersionUID	= 1L;
	
	//@DataTableAnnotation(exclude = false, displayColumnName = "", tableColumnName = "")
	private String			loginId;
	private String			studentName;
	private String			start;
	private String			title;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SessionscheduleVO [loginId=" + loginId + ", studentName="
				+ studentName + ", start=" + start + ", title=" + title + "]";
	}
	
	
	
	

	
}
