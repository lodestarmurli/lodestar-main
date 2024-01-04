package com.lodestar.edupath.datatransferobject.dto.session;

import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class SessionScheduleDetailsDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					facilitatorId;
	private Date				session1Date;
	private Date				session2Date;
	private Date				session3Date;

	private boolean				preSessionCompleted;
	private boolean				session1FaciCompleted;
	private boolean				session2FaciCompleted;
	private boolean				session3FaciCompleted;
	private boolean				sentForReview;
	private boolean				reportGenerated;
	private Date				studentFeedback;
	private Date				parentFeedback;

	private Date				session1Reminder;
	private Date				session2Reminder;
	private Date				session3Reminder;
	private String				venue;

	// none table
	private String				name;
	private String				phoneNumber;
	private String				studentName;
	private Date				session1FaciCompletedDate;
	private Date				session2FaciCompletedDate;
	private Date				session3FaciCompletedDate;
	private Date				sentForReviewDate;
	private Date				reportGeneratedDate;
	private int 				schoolId;
	private int 				year;
	
	//Start sasedeve edited by vyankatesh on date 1-2-2017
	private Double				dueAmount;
	//End sasedeve edited by vyankatesh on date 1-2-2017
	
	
	//start by bharath on 22/9/2019
	private String 				loginId;

	//end by bharath on 22/9/2019
	
	//start by bharath on 14/11/2019
	private String 				facilitatorName;
	private String				session1DateStr;
	private String				session2DateStr;
	private String				session3DateStr;
	private String				session1FaciCompletedDateStr;
	private String				session2FaciCompletedDateStr;
	private String				session3FaciCompletedDateStr;
	private String				sentForReviewDateStr;
	private String				reportGeneratedDateStr;

	//end by bharath on 14/11/2019

	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getSession1Date()
	{
		return session1Date;
	}

	public void setSession1Date(Date session1Date)
	{
		this.session1Date = session1Date;
	}

	public Date getSession2Date()
	{
		return session2Date;
	}

	public void setSession2Date(Date session2Date)
	{
		this.session2Date = session2Date;
	}

	public Date getSession3Date()
	{
		return session3Date;
	}

	public void setSession3Date(Date session3Date)
	{
		this.session3Date = session3Date;
	}

	public boolean isPreSessionCompleted()
	{
		return preSessionCompleted;
	}

	public void setPreSessionCompleted(boolean preSessionCompleted)
	{
		this.preSessionCompleted = preSessionCompleted;
	}

	public boolean isSession1FaciCompleted()
	{
		return session1FaciCompleted;
	}

	public void setSession1FaciCompleted(boolean session1FaciCompleted)
	{
		this.session1FaciCompleted = session1FaciCompleted;
	}

	public boolean isSession2FaciCompleted()
	{
		return session2FaciCompleted;
	}

	public void setSession2FaciCompleted(boolean session2FaciCompleted)
	{
		this.session2FaciCompleted = session2FaciCompleted;
	}

	public boolean isSession3FaciCompleted()
	{
		return session3FaciCompleted;
	}

	public void setSession3FaciCompleted(boolean session3FaciCompleted)
	{
		this.session3FaciCompleted = session3FaciCompleted;
	}

	public boolean isReportGenerated()
	{
		return reportGenerated;
	}

	public void setReportGenerated(boolean reportGenerated)
	{
		this.reportGenerated = reportGenerated;
	}

	public Date getSession1Reminder()
	{
		return session1Reminder;
	}

	public void setSession1Reminder(Date session1Reminder)
	{
		this.session1Reminder = session1Reminder;
	}

	public Date getSession2Reminder()
	{
		return session2Reminder;
	}

	public void setSession2Reminder(Date session2Reminder)
	{
		this.session2Reminder = session2Reminder;
	}

	public Date getSession3Reminder()
	{
		return session3Reminder;
	}

	public void setSession3Reminder(Date session3Reminder)
	{
		this.session3Reminder = session3Reminder;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public int getFacilitatorId()
	{
		return facilitatorId;
	}

	public void setFacilitatorId(int facilitatorId)
	{
		this.facilitatorId = facilitatorId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public boolean isSentForReview()
	{
		return sentForReview;
	}

	public void setSentForReview(boolean sentForReview)
	{
		this.sentForReview = sentForReview;
	}
	
	public Date getStudentFeedback()
	{
		return studentFeedback;
	}

	public void setStudentFeedback(Date studentFeedback)
	{
		this.studentFeedback = studentFeedback;
	}

	public Date getParentFeedback()
	{
		return parentFeedback;
	}

	public void setParentFeedback(Date parentFeedback)
	{
		this.parentFeedback = parentFeedback;
	}


	//Start sasedeve Edited by vyankatesh date on 1-2-2017
	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}
	//Start sasedeve Edited by vyankatesh date on 1-2-2017
	
	//start by bharath on 229/2019
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	//end by bharath on 229/2019
	
	public Date getSession1FaciCompletedDate() {
		return session1FaciCompletedDate;
	}

	public void setSession1FaciCompletedDate(Date session1FaciCompletedDate) {
		this.session1FaciCompletedDate = session1FaciCompletedDate;
	}

	public Date getSession2FaciCompletedDate() {
		return session2FaciCompletedDate;
	}

	public void setSession2FaciCompletedDate(Date session2FaciCompletedDate) {
		this.session2FaciCompletedDate = session2FaciCompletedDate;
	}

	public Date getSession3FaciCompletedDate() {
		return session3FaciCompletedDate;
	}

	public void setSession3FaciCompletedDate(Date session3FaciCompletedDate) {
		this.session3FaciCompletedDate = session3FaciCompletedDate;
	}

	public Date getSentForReviewDate() {
		return sentForReviewDate;
	}

	public void setSentForReviewDate(Date sentForReviewDate) {
		this.sentForReviewDate = sentForReviewDate;
	}

	public Date getReportGeneratedDate() {
		return reportGeneratedDate;
	}

	public void setReportGeneratedDate(Date reportGeneratedDate) {
		this.reportGeneratedDate = reportGeneratedDate;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getFacilitatorName() {
		return facilitatorName;
	}

	public void setFacilitatorName(String facilitatorName) {
		this.facilitatorName = facilitatorName;
	}

	public String getSession1DateStr() {
		return session1DateStr;
	}

	public void setSession1DateStr(String session1DateStr) {
		this.session1DateStr = session1DateStr;
	}

	public String getSession2DateStr() {
		return session2DateStr;
	}

	public void setSession2DateStr(String session2DateStr) {
		this.session2DateStr = session2DateStr;
	}

	public String getSession3DateStr() {
		return session3DateStr;
	}

	public void setSession3DateStr(String session3DateStr) {
		this.session3DateStr = session3DateStr;
	}

	public String getSession1FaciCompletedDateStr() {
		return session1FaciCompletedDateStr;
	}

	public void setSession1FaciCompletedDateStr(String session1FaciCompletedDateStr) {
		this.session1FaciCompletedDateStr = session1FaciCompletedDateStr;
	}

	public String getSession2FaciCompletedDateStr() {
		return session2FaciCompletedDateStr;
	}

	public void setSession2FaciCompletedDateStr(String session2FaciCompletedDateStr) {
		this.session2FaciCompletedDateStr = session2FaciCompletedDateStr;
	}

	public String getSession3FaciCompletedDateStr() {
		return session3FaciCompletedDateStr;
	}

	public void setSession3FaciCompletedDateStr(String session3FaciCompletedDateStr) {
		this.session3FaciCompletedDateStr = session3FaciCompletedDateStr;
	}

	public String getSentForReviewDateStr() {
		return sentForReviewDateStr;
	}

	public void setSentForReviewDateStr(String sentForReviewDateStr) {
		this.sentForReviewDateStr = sentForReviewDateStr;
	}

	public String getReportGeneratedDateStr() {
		return reportGeneratedDateStr;
	}

	public void setReportGeneratedDateStr(String reportGeneratedDateStr) {
		this.reportGeneratedDateStr = reportGeneratedDateStr;
	}

	@Override
	public String toString() {
		return "SessionScheduleDetailsDTO [id=" + id + ", studentId=" + studentId + ", facilitatorId=" + facilitatorId
				+ ", session1Date=" + session1Date + ", session2Date=" + session2Date + ", session3Date=" + session3Date
				+ ", preSessionCompleted=" + preSessionCompleted + ", session1FaciCompleted=" + session1FaciCompleted
				+ ", session2FaciCompleted=" + session2FaciCompleted + ", session3FaciCompleted="
				+ session3FaciCompleted + ", sentForReview=" + sentForReview + ", reportGenerated=" + reportGenerated
				+ ", studentFeedback=" + studentFeedback + ", parentFeedback=" + parentFeedback + ", session1Reminder="
				+ session1Reminder + ", session2Reminder=" + session2Reminder + ", session3Reminder=" + session3Reminder
				+ ", venue=" + venue + ", name=" + name + ", phoneNumber=" + phoneNumber + ", studentName="
				+ studentName + ", session1FaciCompletedDate=" + session1FaciCompletedDate
				+ ", session2FaciCompletedDate=" + session2FaciCompletedDate + ", session3FaciCompletedDate="
				+ session3FaciCompletedDate + ", sentForReviewDate=" + sentForReviewDate + ", reportGeneratedDate="
				+ reportGeneratedDate + ", schoolId=" + schoolId + ", year=" + year + ", dueAmount=" + dueAmount
				+ ", loginId=" + loginId + ", facilitatorName=" + facilitatorName + ", session1DateStr="
				+ session1DateStr + ", session2DateStr=" + session2DateStr + ", session3DateStr=" + session3DateStr
				+ ", session1FaciCompletedDateStr=" + session1FaciCompletedDateStr + ", session2FaciCompletedDateStr="
				+ session2FaciCompletedDateStr + ", session3FaciCompletedDateStr=" + session3FaciCompletedDateStr
				+ ", sentForReviewDateStr=" + sentForReviewDateStr + ", reportGeneratedDateStr="
				+ reportGeneratedDateStr + "]";
	}

	
	
}
