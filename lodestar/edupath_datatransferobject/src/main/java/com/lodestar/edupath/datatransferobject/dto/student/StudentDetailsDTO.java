package com.lodestar.edupath.datatransferobject.dto.student;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.StudentTUMDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;

public class StudentDetailsDTO implements IModel
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
	private StudentTypeEnum				studentType;
	private String						source;
	private String						testTaken;
	private String						otherSchool;

	// Summary Table Column
	private String						session1DateStr;
	private String						session2DateStr;
	private String						session3DateStr;
	private String						schoolName;
	private Date						session1Date;
	private Date						session2Date;
	private Date						session3Date;
	private int							facilitatorId;
	private boolean						delete				= true;
	private String						loginId;
	private String						className;
	private Date						selectedDate;
	private String						section;
	private byte[]						password;
	private String						passwordStr;
	private String						schoolCode;
//Start Sasedeve edited by vyankatesh  on date 30-01-2017
	private Double						dueAmount;
	private Double						agreedAmount;
	private Double						paidAmount;
	
	private String						studentemailId;
	private String						studentcontactNumber;
	private String						motherName;
	private String						motheremailId;
	private String						mothercontactno;
	private String						extrafatherName;
	private String						extrafatherEmailId;
	private String						fatherContactno;
	
	//Start Sasedeve edited by vyankatesh  on date 26-4-2017
	
	private String						cityName;
	
	//end Sasedeve edited by vyankatesh  
	
	
	
	
//End Sasedeve edited by vyankatesh  on date 30-01-2017
	
	
	// facilitator Student session details

	private List<StudentCGTForEvalDTO>	studentCGTList;
	private List<StudentTUMDTO>			studentTUMList;
	private Map<Long, String>			studentMap			= new HashMap<Long, String>();
	private Map<Long, String>			studentQuesAnsMap	= new HashMap<Long, String>();
	private String						studentAlertMessage;

	// none table
	private String						phoneNumber;
	private int							defaultDays;
	private String						venue;
	private String						facilitatorName;
	private boolean						sentForReview;
	private boolean						reportGenerated;
	private String						isActive;
	private Date						feedbackFromDate;
	private Date						feedbackToDate;
	private String						feedbackType;
	private String						question;
	private int							questionNo;
	private String						answer;
	private String						facilitatorLoginId;
	
	
	
	//Start Sasedeve Edited by Mrutyunjaya on Date 08-09-2017
	private String						feedbackdate;

	//start by bharath on Date 6/7/2019
	private List<TumCgtResultDTO>	tumcgtList;
	//end by bharath on Date 6/7/2019
	
	
	//start by bharath on Date 10/9/2019
	private int 						completedStudent; 
	//end by bharath on Date 10/9/2019
	
	
	//start by bharath on Date 12/9/2019
	private String 						personalityCode; 
	//end by bharath on Date 12/9/2019
	
	//start by bharath on Date 18/9/2019
	private String 						studentContact;
	private String 						fatherContact;
	private String 						motherContact;
	//end by bharath on Date 18/9/2019
		
	//start by bharath on Date 15/10/2019
	private Date						 session1FaciCompletedDate;
	private Date						 session2FaciCompletedDate;
	private Date						 session3FaciCompletedDate;
	private Date						 reportGeneratedDate;
	
	private String						session1FaciCompletedDateStr;
	private String						session2FaciCompletedDateStr;
	private String						session3FaciCompletedDateStr;
	private String						reportGeneratedDateStr;
	//End by bharath on Date 15/10/2019
	
	
	
	public String getFeedbackdate() {
		return feedbackdate;
	}

	public void setFeedbackdate(String feedbackdate) {
		this.feedbackdate = feedbackdate;
	}

	
	//END Sasedeve Edited by Mrutyunjaya on Date 08-09-2017
	
	
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

	public boolean isDelete()
	{
		return delete;
	}

	public void setDelete(boolean delete)
	{
		this.delete = delete;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public int getFacilitatorId()
	{
		return facilitatorId;
	}

	public void setFacilitatorId(int facilitatorId)
	{
		this.facilitatorId = facilitatorId;
	}

	public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
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

	public List<StudentCGTForEvalDTO> getStudentCGTList()
	{
		return studentCGTList;
	}

	public void setStudentCGTList(List<StudentCGTForEvalDTO> studentCGTList)
	{
		this.studentCGTList = studentCGTList;
	}

	public List<StudentTUMDTO> getStudentTUMList()
	{
		return studentTUMList;
	}

	public void setStudentTUMList(List<StudentTUMDTO> studentTUMList)
	{
		this.studentTUMList = studentTUMList;
	}

	public Map<Long, String> getStudentMap()
	{
		return studentMap;
	}

	public void setStudentMap(Map<Long, String> studentMap)
	{
		this.studentMap = studentMap;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public Map<Long, String> getStudentQuesAnsMap()
	{
		return studentQuesAnsMap;
	}

	public void setStudentQuesAnsMap(Map<Long, String> studentQuesAnsMap)
	{
		this.studentQuesAnsMap = studentQuesAnsMap;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getStudentAlertMessage()
	{
		return studentAlertMessage;
	}

	public void setStudentAlertMessage(String studentAlertMessage)
	{
		this.studentAlertMessage = studentAlertMessage;
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
	//Star Sasedeve edited by vyankatesh  on date 30-01-2017	
	
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
	//End Sasedeve edited by vyankatesh  on date 30-01-2017
	
	
	
	

	public void setFacilitatorName(String facilitatorName)
	{
		this.facilitatorName = facilitatorName;
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

	public Date getFeedbackFromDate()
	{
		return feedbackFromDate;
	}

	public void setFeedbackFromDate(Date feedbackFromDate)
	{
		this.feedbackFromDate = feedbackFromDate;
	}

	public Date getFeedbackToDate()
	{
		return feedbackToDate;
	}

	public void setFeedbackToDate(Date feedbackToDate)
	{
		this.feedbackToDate = feedbackToDate;
	}

	public String getFeedbackType()
	{
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType)
	{
		this.feedbackType = feedbackType;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
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

//	@Override
//	public String toString() {
//		return "StudentDetailsDTO [id=" + id + ", name=" + name + ", cityId=" + cityId + ", classId=" + classId
//				+ ", schoolId=" + schoolId + ", contactNumber=" + contactNumber + ", fathername=" + fathername
//				+ ", fatheremailId=" + fatheremailId + ", computerFacility=" + computerFacility + ", userId=" + userId
//				+ ", gender=" + gender + ", roleTypeId=" + roleTypeId + ", seDetailsDTO=" + seDetailsDTO
//				+ ", studentType=" + studentType + ", source=" + source + ", testTaken=" + testTaken + ", otherSchool="
//				+ otherSchool + ", session1DateStr=" + session1DateStr + ", session2DateStr=" + session2DateStr
//				+ ", session3DateStr=" + session3DateStr + ", schoolName=" + schoolName + ", session1Date="
//				+ session1Date + ", session2Date=" + session2Date + ", session3Date=" + session3Date
//				+ ", facilitatorId=" + facilitatorId + ", delete=" + delete + ", loginId=" + loginId + ", className="
//				+ className + ", selectedDate=" + selectedDate + ", section=" + section + ", password="
//				+ Arrays.toString(password) + ", passwordStr=" + passwordStr + ", schoolCode=" + schoolCode
//				+ ", dueAmount=" + dueAmount + ", agreedAmount=" + agreedAmount + ", paidAmount=" + paidAmount
//				+ ", studentemailId=" + studentemailId + ", studentcontactNumber=" + studentcontactNumber
//				+ ", motherName=" + motherName + ", motheremailId=" + motheremailId + ", mothercontactno="
//				+ mothercontactno + ", studentCGTList=" + studentCGTList + ", studentTUMList=" + studentTUMList
//				+ ", studentMap=" + studentMap + ", studentQuesAnsMap=" + studentQuesAnsMap + ", studentAlertMessage="
//				+ studentAlertMessage + ", phoneNumber=" + phoneNumber + ", defaultDays=" + defaultDays + ", venue="
//				+ venue + ", facilitatorName=" + facilitatorName + ", sentForReview=" + sentForReview
//				+ ", reportGenerated=" + reportGenerated + ", isActive=" + isActive + ", feedbackFromDate="
//				+ feedbackFromDate + ", feedbackToDate=" + feedbackToDate + ", feedbackType=" + feedbackType
//				+ ", question=" + question + ", questionNo=" + questionNo + ", answer=" + answer
//				+ ", facilitatorLoginId=" + facilitatorLoginId + "]";
//	}
	
	//-----------------------------------------------------End CHANGE CODE-----------------------------------------------------------
	
	
//-----------------------------------------------------Start ORIGINAL CODE-----------------------------------------------------------
//	public String toString()
//	{
//		return "StudentDetailsDTO [id=" + id + ", name=" + name + ", cityId=" + cityId + ", classId=" + classId + ", schoolId=" + schoolId + ", contactNumber="
//				+ contactNumber + ", fathername=" + fathername + ", fatheremailId=" + fatheremailId + ", computerFacility=" + computerFacility + ", userId="
//				+ userId + ", gender=" + gender + ", roleTypeId=" + roleTypeId + ", seDetailsDTO=" + seDetailsDTO + ", session1DateStr=" + session1DateStr
//				+ ", session2DateStr=" + session2DateStr + ", session3DateStr=" + session3DateStr + ", schoolName=" + schoolName + ", session1Date=" + session1Date
//				+ ", session2Date=" + session2Date + ", session3Date=" + session3Date + ", facilitatorId=" + facilitatorId + ", delete=" + delete + ", loginId="
//				+ loginId + ", className=" + className + ", selectedDate=" + selectedDate + ", section=" + section + ", password=" + Arrays.toString(password)
//				+ ", passwordStr=" + passwordStr + ", studentCGTList=" + studentCGTList + ", studentTUMList=" + studentTUMList + ", studentMap=" + studentMap
//				+ ", studentQuesAnsMap=" + studentQuesAnsMap + ", studentAlertMessage=" + studentAlertMessage + ", phoneNumber=" + phoneNumber + ", defaultDays="
//				+ defaultDays + ", venue=" + venue + ", facilitatorName=" + facilitatorName + ", sentForReview=" + sentForReview + ", reportGenerated="
//				+ reportGenerated + ", isActive=" + isActive + ", feedbackFromDate=" + feedbackFromDate + ", feedbackToDate=" + feedbackToDate + ", feedbackType="
//				+ feedbackType + ", question=" + question + ", answer=" + answer + ", facilitatorLoginId=" + facilitatorLoginId + "]";
//	}
//	
	//-----------------------------------------------------End ORIGINAL CODE-----------------------------------------------------------	
	//End Sasedeve edited by vyankatesh  on date 30-01-2017
	public int getQuestionNo()
	{
		return questionNo;
	}

	public void setQuestionNo(int questionNo)
	{
		this.questionNo = questionNo;
	}

	public StudentTypeEnum getStudentType()
	{
		return studentType;
	}

	public void setStudentType(StudentTypeEnum studentType)
	{
		this.studentType = studentType;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
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

	public String getSchoolCode()
	{
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode)
	{
		this.schoolCode = schoolCode;
	}

	//Vyankatesh addded
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
		return extrafatherName;
	}

	public void setFatherName(String fatherName) {
		this.extrafatherName = fatherName;
	}

	public String getFatherEmailId() {
		return extrafatherEmailId;
	}

	public void setFatherEmailId(String fatherEmailId) {
		this.extrafatherEmailId = fatherEmailId;
	}

	public String getFathercontactno() {
		return fatherContactno;
	}

	public void setFathercontactno(String fathercontactno) {
		this.fatherContactno = fathercontactno;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
	public List<TumCgtResultDTO> getTumcgtList() {
		return tumcgtList;
	}

	public void setTumcgtList(List<TumCgtResultDTO> tumcgtList) {
		this.tumcgtList = tumcgtList;
	}

	public int getCompletedStudent() {
		return completedStudent;
	}

	public void setCompletedStudent(int completedStudent) {
		this.completedStudent = completedStudent;
	}

	public String getPersonalityCode() {
		return personalityCode;
	}

	public void setPersonalityCode(String personalityCode) {
		this.personalityCode = personalityCode;
	}

	public String getStudentContact() {
		return studentContact;
	}

	public void setStudentContact(String studentContact) {
		this.studentContact = studentContact;
	}

	public String getFatherContact() {
		return fatherContact;
	}

	public void setFatherContact(String fatherContact) {
		this.fatherContact = fatherContact;
	}

	public String getMotherContact() {
		return motherContact;
	}

	public void setMotherContact(String motherContact) {
		this.motherContact = motherContact;
	}

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

	public Date getReportGeneratedDate() {
		return reportGeneratedDate;
	}

	public void setReportGeneratedDate(Date reportGeneratedDate) {
		this.reportGeneratedDate = reportGeneratedDate;
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

	public String getReportGeneratedDateStr() {
		return reportGeneratedDateStr;
	}

	public void setReportGeneratedDateStr(String reportGeneratedDateStr) {
		this.reportGeneratedDateStr = reportGeneratedDateStr;
	}



	@Override
	public String toString() {
		return "StudentDetailsDTO [id=" + id + ", name=" + name + ", cityId=" + cityId + ", classId=" + classId
				+ ", schoolId=" + schoolId + ", contactNumber=" + contactNumber + ", fathername=" + fathername
				+ ", fatheremailId=" + fatheremailId + ", computerFacility=" + computerFacility + ", userId=" + userId
				+ ", gender=" + gender + ", roleTypeId=" + roleTypeId + ", seDetailsDTO=" + seDetailsDTO
				+ ", studentType=" + studentType + ", source=" + source + ", testTaken=" + testTaken + ", otherSchool="
				+ otherSchool + ", session1DateStr=" + session1DateStr + ", session2DateStr=" + session2DateStr
				+ ", session3DateStr=" + session3DateStr + ", schoolName=" + schoolName + ", session1Date="
				+ session1Date + ", session2Date=" + session2Date + ", session3Date=" + session3Date
				+ ", facilitatorId=" + facilitatorId + ", delete=" + delete + ", loginId=" + loginId + ", className="
				+ className + ", selectedDate=" + selectedDate + ", section=" + section + ", password="
				+ Arrays.toString(password) + ", passwordStr=" + passwordStr + ", schoolCode=" + schoolCode
				+ ", dueAmount=" + dueAmount + ", agreedAmount=" + agreedAmount + ", paidAmount=" + paidAmount
				+ ", studentemailId=" + studentemailId + ", studentcontactNumber=" + studentcontactNumber
				+ ", motherName=" + motherName + ", motheremailId=" + motheremailId + ", mothercontactno="
				+ mothercontactno + ", extrafatherName=" + extrafatherName + ", extrafatherEmailId="
				+ extrafatherEmailId + ", fatherContactno=" + fatherContactno + ", cityName=" + cityName
				+ ", studentCGTList=" + studentCGTList + ", studentTUMList=" + studentTUMList + ", studentMap="
				+ studentMap + ", studentQuesAnsMap=" + studentQuesAnsMap + ", studentAlertMessage="
				+ studentAlertMessage + ", phoneNumber=" + phoneNumber + ", defaultDays=" + defaultDays + ", venue="
				+ venue + ", facilitatorName=" + facilitatorName + ", sentForReview=" + sentForReview
				+ ", reportGenerated=" + reportGenerated + ", isActive=" + isActive + ", feedbackFromDate="
				+ feedbackFromDate + ", feedbackToDate=" + feedbackToDate + ", feedbackType=" + feedbackType
				+ ", question=" + question + ", questionNo=" + questionNo + ", answer=" + answer
				+ ", facilitatorLoginId=" + facilitatorLoginId + ", feedbackdate=" + feedbackdate + ", tumcgtList="
				+ tumcgtList + ", completedStudent=" + completedStudent + ", personalityCode=" + personalityCode
				+ ", studentContact=" + studentContact + ", fatherContact=" + fatherContact + ", motherContact="
				+ motherContact + ", session1FaciCompletedDate=" + session1FaciCompletedDate
				+ ", session2FaciCompletedDate=" + session2FaciCompletedDate + ", session3FaciCompletedDate="
				+ session3FaciCompletedDate + ", reportGeneratedDate=" + reportGeneratedDate
				+ ", session1FaciCompletedDateStr=" + session1FaciCompletedDateStr + ", session2FaciCompletedDateStr="
				+ session2FaciCompletedDateStr + ", session3FaciCompletedDateStr=" + session3FaciCompletedDateStr
				+ ", reportGeneratedDateStr=" + reportGeneratedDateStr 
				+ "]";
	}

	


	

//	@Override
//	public String toString() {
//		return "StudentDetailsDTO [id=" + id + ", name=" + name + ", cityId=" + cityId + ", classId=" + classId
//				+ ", schoolId=" + schoolId + ", contactNumber=" + contactNumber + ", fathername=" + fathername
//				+ ", fatheremailId=" + fatheremailId + ", computerFacility=" + computerFacility + ", userId=" + userId
//				+ ", gender=" + gender + ", roleTypeId=" + roleTypeId + ", seDetailsDTO=" + seDetailsDTO
//				+ ", studentType=" + studentType + ", source=" + source + ", testTaken=" + testTaken + ", otherSchool="
//				+ otherSchool + ", session1DateStr=" + session1DateStr + ", session2DateStr=" + session2DateStr
//				+ ", session3DateStr=" + session3DateStr + ", schoolName=" + schoolName + ", session1Date="
//				+ session1Date + ", session2Date=" + session2Date + ", session3Date=" + session3Date
//				+ ", facilitatorId=" + facilitatorId + ", delete=" + delete + ", loginId=" + loginId + ", className="
//				+ className + ", selectedDate=" + selectedDate + ", section=" + section + ", password="
//				+ Arrays.toString(password) + ", passwordStr=" + passwordStr + ", schoolCode=" + schoolCode
//				+ ", dueAmount=" + dueAmount + ", agreedAmount=" + agreedAmount + ", paidAmount=" + paidAmount
//				+ ", studentemailId=" + studentemailId + ", studentcontactNumber=" + studentcontactNumber
//				+ ", motherName=" + motherName + ", motheremailId=" + motheremailId + ", mothercontactno="
//				+ mothercontactno + ", fatherName=" + extrafatherName + ", fatherEmailId=" + extrafatherEmailId
//				+ ", fathercontactno=" + fatherContactno + ", studentCGTList=" + studentCGTList + ", studentTUMList="
//				+ studentTUMList + ", studentMap=" + studentMap + ", studentQuesAnsMap=" + studentQuesAnsMap
//				+ ", studentAlertMessage=" + studentAlertMessage + ", phoneNumber=" + phoneNumber + ", defaultDays="
//				+ defaultDays + ", venue=" + venue + ", facilitatorName=" + facilitatorName + ", sentForReview="
//				+ sentForReview + ", reportGenerated=" + reportGenerated + ", isActive=" + isActive
//				+ ", feedbackFromDate=" + feedbackFromDate + ", feedbackToDate=" + feedbackToDate + ", feedbackType="
//				+ feedbackType + ", question=" + question + ", questionNo=" + questionNo + ", answer=" + answer
//				+ ", facilitatorLoginId=" + facilitatorLoginId + ", feedbackdate="+ feedbackdate +"]";
//	}

}
