package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.Date;

public class FacilitatorStatsReportDTO implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	private int 		id;
	private String 		name;
	private String 		type;
	private Date 		dob;
	private String 		emailId;
	private String 		phoneNumber;
	private Boolean 	isReviewer;
	private int 		userId;
	private Boolean 	isActive;
	private String 		gender;
	private int 		preSessionCompleted;
	private int 		session1Completed;
	private int 		session2Completed;
	private int 		session3Completed;
	private int 		sentForReview;
	private int 		reportGenerated;
	private String 		schoolName;
	private int 		schoolId;
	private int 		year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsReviewer() {
		return isReviewer;
	}

	public void setIsReviewer(Boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPreSessionCompleted() {
		return preSessionCompleted;
	}

	public void setPreSessionCompleted(int preSessionCompleted) {
		this.preSessionCompleted = preSessionCompleted;
	}

	public int getSession1Completed() {
		return session1Completed;
	}

	public void setSession1Completed(int session1Completed) {
		this.session1Completed = session1Completed;
	}

	public int getSession2Completed() {
		return session2Completed;
	}

	public void setSession2Completed(int session2Completed) {
		this.session2Completed = session2Completed;
	}

	public int getSession3Completed() {
		return session3Completed;
	}

	public void setSession3Completed(int session3Completed) {
		this.session3Completed = session3Completed;
	}

	public int getSentForReview() {
		return sentForReview;
	}

	public void setSentForReview(int sentForReview) {
		this.sentForReview = sentForReview;
	}

	public int getReportGenerated() {
		return reportGenerated;
	}

	public void setReportGenerated(int reportGenerated) {
		this.reportGenerated = reportGenerated;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "FacilitatorStatsReportDTO [id=" + id + ", name=" + name + ", type=" + type + ", dob=" + dob
				+ ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", isReviewer=" + isReviewer + ", userId="
				+ userId + ", isActive=" + isActive + ", gender=" + gender + ", preSessionCompleted="
				+ preSessionCompleted + ", session1Completed=" + session1Completed + ", session2Completed="
				+ session2Completed + ", session3Completed=" + session3Completed + ", sentForReview=" + sentForReview
				+ ", reportGenerated=" + reportGenerated + ", schoolName=" + schoolName + ", schoolId=" + schoolId
				+ ", year=" + year + "]";
	}

	
}
