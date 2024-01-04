package com.lodestar.edupath.auth.service;

public class UserSessionObject
{
	private int		id;

	private String	loginId;

	private String	fullName;

	private String	emailId;

	private int		roleId;

	private int		roleTypeId;

	private int		roleWeight;

	private String	userRoleName;

	private String	mobile;

	private String	sessionId;

	private boolean	reviewer;

	private boolean	trial;
	
	private boolean	streamSelector;
	
	private boolean engineeringBranchSelector;
	
	private boolean programTestStudent;
	
	private boolean careerDegreeDiscovery;
	
	private boolean careerFitment;
	
	private boolean careerFitmentOccSelection;
	
	private boolean aptitudeCompleted;
	
	private int classId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public int getRoleTypeId()
	{
		return roleTypeId;
	}

	public void setRoleTypeId(int roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getUserRoleName()
	{
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName)
	{
		this.userRoleName = userRoleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserSessionObject [id=");
		builder.append(id);
		builder.append(", ");
		if (loginId != null)
		{
			builder.append("loginId=");
			builder.append(loginId);
			builder.append(", ");
		}
		if (fullName != null)
		{
			builder.append("Name=");
			builder.append(fullName);
			builder.append(", ");
		}
		if (emailId != null)
		{
			builder.append("emailId=");
			builder.append(emailId);
			builder.append(", ");
		}
		builder.append("roleId=");
		builder.append(roleId);
		builder.append(", ");
		builder.append("reviewer=");
		builder.append(reviewer);
		builder.append(", ");
		builder.append("trial=");
		builder.append(trial);
		builder.append(", ");
		builder.append("programTestStudent=");
		builder.append(programTestStudent);
		builder.append(", ");
		builder.append("streamSelector=");
		builder.append(streamSelector);
		builder.append(", ");
		builder.append("engineeringBranchSelector=");
		builder.append(engineeringBranchSelector);
		builder.append(", ");
		builder.append("careerDegreeDiscovery=");
		builder.append(careerDegreeDiscovery);
		builder.append(", ");
		builder.append("careerFitment=");
		builder.append(careerFitment);
		builder.append(", ");
		builder.append("careerFitmentOccSelection=");
		builder.append(careerFitmentOccSelection);
		builder.append(", ");
		builder.append("aptitudeCompleted=");
		builder.append(aptitudeCompleted);
		
		builder.append("]");
		return builder.toString();
	}

	public int getRoleWeight()
	{
		return roleWeight;
	}

	public void setRoleWeight(int roleWeight)
	{
		this.roleWeight = roleWeight;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public boolean getReviewer()
	{
		return reviewer;
	}

	public void setReviewer(boolean reviewer)
	{
		this.reviewer = reviewer;
	}

	public boolean isTrial()
	{
		return trial;
	}

	public void setTrial(boolean trial)
	{
		this.trial = trial;
	}

	public boolean isStreamSelector() {
		return streamSelector;
	}

	public void setStreamSelector(boolean streamSelector) {
		this.streamSelector = streamSelector;
	}

	public boolean isAptitudeCompleted() {
		return aptitudeCompleted;
	}

	public void setAptitudeCompleted(boolean aptitudeCompleted) {
		this.aptitudeCompleted = aptitudeCompleted;
	}

	public boolean isEngineeringBranchSelector() {
		return engineeringBranchSelector;
	}

	public void setEngineeringBranchSelector(boolean engineeringBranchSelector) {
		this.engineeringBranchSelector = engineeringBranchSelector;
	}

	public boolean isProgramTestStudent() {
		return programTestStudent;
	}

	public void setProgramTestStudent(boolean programTestStudent) {
		this.programTestStudent = programTestStudent;
	}

	public boolean isCareerDegreeDiscovery() {
		return careerDegreeDiscovery;
	}

	public void setCareerDegreeDiscovery(boolean careerDegreeDiscovery) {
		this.careerDegreeDiscovery = careerDegreeDiscovery;
	}

	public boolean isCareerFitment() {
		return careerFitment;
	}

	public void setCareerFitment(boolean careerFitment) {
		this.careerFitment = careerFitment;
	}

	public boolean isCareerFitmentOccSelection() {
		return careerFitmentOccSelection;
	}

	public void setCareerFitmentOccSelection(boolean careerFitmentOccSelection) {
		this.careerFitmentOccSelection = careerFitmentOccSelection;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	
	
	

}
