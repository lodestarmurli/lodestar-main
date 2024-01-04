package com.lodestar.edupath.datatransferobject.dto.schoolreport;

public class SchoolReportDTO {

	private int id;
	private String sessionDate;
	private String  loginId;
	private String name;
	private int classLable;
	private String stream;
	private String career1;
	private String career2;
	
	private String ugDegreePlanA;
	private String ugDegreePlanB;
	private String examRecommendations;
	private String elective;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClassLable() {
		return classLable;
	}
	public void setClassLable(int classLable) {
		this.classLable = classLable;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getCareer1() {
		return career1;
	}
	public void setCareer1(String career1) {
		this.career1 = career1;
	}
	public String getCareer2() {
		return career2;
	}
	public void setCareer2(String career2) {
		this.career2 = career2;
	}
	public String getUgDegreePlanA() {
		return ugDegreePlanA;
	}
	public void setUgDegreePlanA(String ugDegreePlanA) {
		this.ugDegreePlanA = ugDegreePlanA;
	}
	public String getUgDegreePlanB() {
		return ugDegreePlanB;
	}
	public void setUgDegreePlanB(String ugDegreePlanB) {
		this.ugDegreePlanB = ugDegreePlanB;
	}
	public String getExamRecommendations() {
		return examRecommendations;
	}
	public void setExamRecommendations(String examRecommendations) {
		this.examRecommendations = examRecommendations;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}
	public String getElective() {
		return elective;
	}
	public void setElective(String elective) {
		this.elective = elective;
	}
	@Override
	public String toString() {
		return "SchoolReportDTO [id=" + id + ", sessionDate=" + sessionDate
				+ ", loginId=" + loginId + ", name=" + name + ", classLable="
				+ classLable + ", stream=" + stream + ", career1=" + career1
				+ ", career2=" + career2 + ", ugDegreePlanA=" + ugDegreePlanA
				+ ", ugDegreePlanB=" + ugDegreePlanB + ", examRecommendations="
				+ examRecommendations + ", elective=" + elective + "]";
	}

}
