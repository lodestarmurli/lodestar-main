package com.lodestar.edupath.datatransferobject.dto.APIS.client;

import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ClientStudentExtraDetailDTO implements IModel
{
	private static final long			serialVersionUID	= 1L;
	
	private int							id;
	private String 						name;
	private int							studentId;
	private String						studentemailId;
	private Date						session1Date;
	private Date						session2Date;
	private String						session1DateStr;
	private String						session2DateStr;
	private String						studentcontactNumber;
	private String						motherName;
	private String						motheremailId;
	private String						mothercontactno;
	private String						fatherName;
	private String						fatherEmailId;
	private String						fathercontactno;
	private int 						cityId;
	private String 						cityName;
	private String						section;
	private String 						schoolName;
	private int							schoolId;
	private int							isConverted;
	private String						className;
	private String 						gender;
	private int 						classId;
	
	
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
	public Date getSession1Date() {
		return session1Date;
	}
	public void setSession1Date(Date session1Date) {
		this.session1Date = session1Date;
	}
	public Date getSession2Date() {
		return session2Date;
	}
	public void setSession2Date(Date session2Date) {
		this.session2Date = session2Date;
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
	public String getFatherEmailId() {
		return fatherEmailId;
	}
	public void setFatherEmailId(String fatherEmailId) {
		this.fatherEmailId = fatherEmailId;
	}
	public String getFathercontactno() {
		return fathercontactno;
	}
	public void setFathercontactno(String fathercontactno) {
		this.fathercontactno = fathercontactno;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsConverted() {
		return isConverted;
	}
	public void setIsConverted(int isConverted) {
		this.isConverted = isConverted;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	@Override
	public String toString() {
		return "DHStudentExtraDetailDTO [id=" + id + ", name=" + name + ", studentId=" + studentId + ", studentemailId="
				+ studentemailId + ", session1Date=" + session1Date + ", session2Date=" + session2Date
				+ ", session1DateStr=" + session1DateStr + ", session2DateStr=" + session2DateStr
				+ ", studentcontactNumber=" + studentcontactNumber + ", motherName=" + motherName + ", motheremailId="
				+ motheremailId + ", mothercontactno=" + mothercontactno + ", fatherName=" + fatherName
				+ ", fatherEmailId=" + fatherEmailId + ", fathercontactno=" + fathercontactno + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", section=" + section + ", schoolName=" + schoolName + ", schoolId="
				+ schoolId + ", isConverted=" + isConverted + ", className=" + className + ", gender=" + gender
				+ ", classId=" + classId + "]";
	}
	
	 
	
	

}
