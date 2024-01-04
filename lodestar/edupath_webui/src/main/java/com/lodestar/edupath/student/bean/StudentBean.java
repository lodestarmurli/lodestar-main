package com.lodestar.edupath.student.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;

public class StudentBean implements Serializable
{

	private static final long			serialVersionUID	= 1L;

	private int							id;
	private String						name;
	private int							cityId;
	private int							classId;
	private int							schoolId;
	private String						contactNumber;
	private String						fathername;
	private String						fatheremailId;
	private boolean						computerFacility;
	private int							userId;
	private String						gender;
	private int							roleTypeId;
	private SessionScheduleDetailsDTO	seDetailsDTO;
	private StudentTypeEnum				studentType			= StudentTypeEnum.FULL;
	private String						testTaken;
	private String						otherSchool;

	// Summary Table Column
	private String						session1DateStr;
	private String						session2DateStr;
	private String						session3DateStr;
	private String						schoolName;
	private int							facilitatorId;
	private boolean						delete				= true;
	private String						loginId;
	private String						className;
	private Date						selectedDate;
	private String						section;
	private byte[]						password;
	private String						passwordStr;

	// none table
	private String						phoneNumber;
	private int							defaultDays;
	private String						venue;
	private String						facilitatorName;
	private boolean						sentForReview;
	private boolean						reportGenerated;
	private String						isActive;
	private String						question;
	private int							questionNo;
	private String						answer;
	private String						facilitatorLoginId;
	
	//Start Sasedeve edited by vyankatesh  on date 30-01-2017
		private Double						dueAmount;
		private Double						agreedAmount;
		private Double						paidAmount;
		
		private String						studentemailId;
		private String						studentcontactNumber;
		private String						motherName;
		private String						motheremailId;
		private String						mothercontactno;
		private String						fatherName;
		private String						fatherEmailId;
		private String						fathercontactno;
		
	//End Sasedeve edited by vyankatesh  on date 30-01-2017

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public int getClassId()
	{
		return classId;
	}

	public void setClassId(int classId)
	{
		this.classId = classId;
	}

	public int getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(int schoolId)
	{
		this.schoolId = schoolId;
	}

	public String getContactNumber()
	{
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public String getFathername()
	{
		return fathername;
	}

	public void setFathername(String fathername)
	{
		this.fathername = fathername;
	}

	public String getFatheremailId()
	{
		return fatheremailId;
	}

	public void setFatheremailId(String fatheremailId)
	{
		this.fatheremailId = fatheremailId;
	}

	public boolean isComputerFacility()
	{
		return computerFacility;
	}

	public void setComputerFacility(boolean computerFacility)
	{
		this.computerFacility = computerFacility;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public int getRoleTypeId()
	{
		return roleTypeId;
	}

	public void setRoleTypeId(int roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	public SessionScheduleDetailsDTO getSeDetailsDTO()
	{
		return seDetailsDTO;
	}

	public void setSeDetailsDTO(SessionScheduleDetailsDTO seDetailsDTO)
	{
		this.seDetailsDTO = seDetailsDTO;
	}

	public StudentTypeEnum getStudentType()
	{
		return studentType;
	}

	public void setStudentType(StudentTypeEnum studentType)
	{
		this.studentType = studentType;
	}

	public String getSession1DateStr()
	{
		return session1DateStr;
	}

	public void setSession1DateStr(String session1DateStr)
	{
		this.session1DateStr = session1DateStr;
	}

	public String getSession2DateStr()
	{
		return session2DateStr;
	}

	public void setSession2DateStr(String session2DateStr)
	{
		this.session2DateStr = session2DateStr;
	}

	public String getSession3DateStr()
	{
		return session3DateStr;
	}

	public void setSession3DateStr(String session3DateStr)
	{
		this.session3DateStr = session3DateStr;
	}

	public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}

	public int getFacilitatorId()
	{
		return facilitatorId;
	}

	public void setFacilitatorId(int facilitatorId)
	{
		this.facilitatorId = facilitatorId;
	}

	public boolean isDelete()
	{
		return delete;
	}

	public void setDelete(boolean delete)
	{
		this.delete = delete;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public Date getSelectedDate()
	{
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate)
	{
		this.selectedDate = selectedDate;
	}

	public String getSection()
	{
		return section;
	}

	public void setSection(String section)
	{
		this.section = section;
	}

	public byte[] getPassword()
	{
		return password;
	}

	public void setPassword(byte[] password)
	{
		this.password = password;
	}

	public String getPasswordStr()
	{
		return passwordStr;
	}

	public void setPasswordStr(String passwordStr)
	{
		this.passwordStr = passwordStr;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public int getDefaultDays()
	{
		return defaultDays;
	}

	public void setDefaultDays(int defaultDays)
	{
		this.defaultDays = defaultDays;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	public String getFacilitatorName()
	{
		return facilitatorName;
	}

	public void setFacilitatorName(String facilitatorName)
	{
		this.facilitatorName = facilitatorName;
	}

	public boolean isSentForReview()
	{
		return sentForReview;
	}

	public void setSentForReview(boolean sentForReview)
	{
		this.sentForReview = sentForReview;
	}

	public boolean isReportGenerated()
	{
		return reportGenerated;
	}

	public void setReportGenerated(boolean reportGenerated)
	{
		this.reportGenerated = reportGenerated;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
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

	public String getFacilitatorLoginId()
	{
		return facilitatorLoginId;
	}

	public void setFacilitatorLoginId(String facilitatorLoginId)
	{
		this.facilitatorLoginId = facilitatorLoginId;
	}

	public String getTestTaken()
	{
		return testTaken;
	}

	public void setTestTaken(String testTaken)
	{
		this.testTaken = testTaken;
	}

	public String getOtherSchool()
	{
		return otherSchool;
	}

	public void setOtherSchool(String otherSchool)
	{
		this.otherSchool = otherSchool;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Double getAgreedAmount() {
		return agreedAmount;
	}

	public void setAgreedAmount(Double agreedAmount) {
		this.agreedAmount = agreedAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getStudentemailId() {
		return studentemailId;
	}

	public void setStudentemailId(String studentemailId) {
		this.studentemailId = studentemailId;
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

	@Override
	public String toString() {
		return "StudentBean [id=" + id + ", name=" + name + ", cityId=" + cityId + ", classId=" + classId
				+ ", schoolId=" + schoolId + ", contactNumber=" + contactNumber + ", fathername=" + fathername
				+ ", fatheremailId=" + fatheremailId + ", computerFacility=" + computerFacility + ", userId=" + userId
				+ ", gender=" + gender + ", roleTypeId=" + roleTypeId + ", seDetailsDTO=" + seDetailsDTO
				+ ", studentType=" + studentType + ", testTaken=" + testTaken + ", otherSchool=" + otherSchool
				+ ", session1DateStr=" + session1DateStr + ", session2DateStr=" + session2DateStr + ", session3DateStr="
				+ session3DateStr + ", schoolName=" + schoolName + ", facilitatorId=" + facilitatorId + ", delete="
				+ delete + ", loginId=" + loginId + ", className=" + className + ", selectedDate=" + selectedDate
				+ ", section=" + section + ", password=" + Arrays.toString(password) + ", passwordStr=" + passwordStr
				+ ", phoneNumber=" + phoneNumber + ", defaultDays=" + defaultDays + ", venue=" + venue
				+ ", facilitatorName=" + facilitatorName + ", sentForReview=" + sentForReview + ", reportGenerated="
				+ reportGenerated + ", isActive=" + isActive + ", question=" + question + ", questionNo=" + questionNo
				+ ", answer=" + answer + ", facilitatorLoginId=" + facilitatorLoginId + ", dueAmount=" + dueAmount
				+ ", agreedAmount=" + agreedAmount + ", paidAmount=" + paidAmount + ", studentemailId=" + studentemailId
				+ ", studentcontactNumber=" + studentcontactNumber + ", motherName=" + motherName + ", motheremailId="
				+ motheremailId + ", mothercontactno=" + mothercontactno + ", fatherName=" + fatherName
				+ ", fatherEmailId=" + fatherEmailId + ", fathercontactno=" + fathercontactno + "]";
	}
	

}
