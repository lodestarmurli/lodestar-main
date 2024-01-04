package com.lodestar.edupath.student.bean;

import java.io.Serializable;

import com.lodestar.edupath.util.datatable.DataTableAnnotation;

public class TrailStudentVO implements Serializable
{
	//IF query is JOIN tableColumnName should be with table alias name  [ Ex - st.name ]
	
	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private static final long	serialVersionUID	= 1L;

	@DataTableAnnotation(exclude = false, displayColumnName = "", tableColumnName = "")
	private int					id;

	@DataTableAnnotation(exclude = false, displayColumnName = "", tableColumnName = "")
	private int					userId;

	@DataTableAnnotation(exclude = false, displayColumnName = "", tableColumnName = "")
	private Boolean				isDelete			= true;

	@DataTableAnnotation(exclude = false, displayColumnName = "studentTypeStr", tableColumnName = "st.studentType")
	private String				studentTypeStr;

	@DataTableAnnotation(exclude = false, displayColumnName = "name", tableColumnName = "st.name")
	private String				name;

	@DataTableAnnotation(exclude = false, displayColumnName = "fatheremailId", tableColumnName = "st.fatheremailId")
	private String				fatheremailId;
	@DataTableAnnotation(exclude = false, displayColumnName = "schoolName", tableColumnName = "scl.name")
	private String				schoolName;
//
//	@DataTableAnnotation(exclude = false, displayColumnName = "session1DateStr", tableColumnName = "sc.session1Date")
//	private String				session1DateStr;
//	@DataTableAnnotation(exclude = false, displayColumnName = "session2DateStr", tableColumnName = "sc.session2Date")
//	private String				session2DateStr;
//	@DataTableAnnotation(exclude = false, displayColumnName = "session3DateStr", tableColumnName = "sc.session3Date")
//	private String				session3DateStr;

	// Start Sasedeve Edited by Vyankatesh on date:- 27-01-2017
	
//	@DataTableAnnotation(exclude = false, displayColumnName = "venue", tableColumnName = "sc.venue")
//	private String				venue;
//	
	 //End Sasedeve Edited by Vyankatesh on date:- 27-01-2017 
	
	@DataTableAnnotation(exclude = false, displayColumnName = "loginId", tableColumnName = "ud.loginId")
	private String				loginId;

	@DataTableAnnotation(exclude = false, displayColumnName = "passwordStr", tableColumnName = "ud.password")
	private String				passwordStr;

	// Start Sasedeve Edited by Vyankatesh on date:- 30-01-2017
	
//	@DataTableAnnotation(exclude = false, displayColumnName = "dueAmount", tableColumnName = "py.dueAmount")
//	private String				dueAmount;
//	
	 //End Sasedeve Edited by Vyankatesh on date:- 30-01-2017 
	
	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private int					facilitatorId;

	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private boolean				reportGenerated;
	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private boolean				isActive;

	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private boolean				preSessionCompleted;
	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private boolean				session1FaciCompleted;
	@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private boolean				session2FaciCompleted;
	//@DataTableAnnotation(exclude = false, displayColumnName = "", tableColumnName = "")
	//private boolean				session3FaciCompleted;
	
	//start by bharath on Date 10/9/2019
	
	private int 						completedStudent; 
		
	//start by bharath on Date 12/9/2019
	//@DataTableAnnotation(exclude = true, displayColumnName = "", tableColumnName = "")
	private String 						personalityCode; 
		

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

//	public String getSession1DateStr()
//	{
//		return session1DateStr;
//	}
//
//	public void setSession1DateStr(String session1DateStr)
//	{
//		this.session1DateStr = session1DateStr;
//	}
//
//	public String getSession2DateStr()
//	{
//		return session2DateStr;
//	}
//
//	public void setSession2DateStr(String session2DateStr)
//	{
//		this.session2DateStr = session2DateStr;
//	}
//
//	public String getSession3DateStr()
//	{
//		return session3DateStr;
//	}
//
//	public void setSession3DateStr(String session3DateStr)
//	{
//		this.session3DateStr = session3DateStr;
//	}

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

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getPasswordStr()
	{
		return passwordStr;
	}

	public void setPasswordStr(String passwordStr)
	{
		this.passwordStr = passwordStr;
	}

	public String getFatheremailId()
	{
		return fatheremailId;
	}

	public void setFatheremailId(String fatheremailId)
	{
		this.fatheremailId = fatheremailId;
	}

	public String getStudentTypeStr()
	{
		return studentTypeStr;
	}

	public void setStudentTypeStr(String studentTypeStr)
	{
		this.studentTypeStr = studentTypeStr;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public boolean isReportGenerated()
	{
		return reportGenerated;
	}

	public void setReportGenerated(boolean reportGenerated)
	{
		this.reportGenerated = reportGenerated;
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

//	public boolean isSession3FaciCompleted()
//	{
//		return session3FaciCompleted;
//	}
//
//	public void setSession3FaciCompleted(boolean session3FaciCompleted)
//	{
//		this.session3FaciCompleted = session3FaciCompleted;
//	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public Boolean getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete)
	{
		this.isDelete = isDelete;
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

	@Override
	public String toString() {
		return "StudentVO [id=" + id + ", userId=" + userId + ", isDelete=" + isDelete + ", studentTypeStr="
				+ studentTypeStr + ", name=" + name + ", fatheremailId=" + fatheremailId + ", schoolName=" + schoolName
				+ ", loginId=" + loginId + ", passwordStr=" + passwordStr + ", facilitatorId=" + facilitatorId
				+ ", reportGenerated=" + reportGenerated + ", isActive=" + isActive + ", preSessionCompleted="
				+ preSessionCompleted + ", session1FaciCompleted=" + session1FaciCompleted + ", session2FaciCompleted="
				+ session2FaciCompleted + ", completedStudent="
				+ completedStudent + ", personalityCode=" + personalityCode + "]";
	}


	
	// Start Sasedeve Edited by Vyankatesh on date:- 27-01-2017
//	public String getVenue() {
//		return venue;
//	}
//
//	public void setVenue(String venue) {
//		this.venue = venue;
//	}
	// End Sasedeve Edited by Vyankatesh on date:- 27-01-2017
	// Start Sasedeve Edited by Vyankatesh on date:- 30-01-2017

//	public String getDueAmount() {
//		return dueAmount;
//	}
//
//	public void setDueAmount(String dueAmount) {
//		this.dueAmount = dueAmount;
//	}
	// End Sasedeve Edited by Vyankatesh on date:- 30-01-2017
	
	
	

}
