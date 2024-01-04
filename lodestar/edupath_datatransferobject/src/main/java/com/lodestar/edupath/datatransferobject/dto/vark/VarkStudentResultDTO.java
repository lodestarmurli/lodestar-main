package com.lodestar.edupath.datatransferobject.dto.vark;

public class VarkStudentResultDTO {

	private String 		LDID;
	private String 		name;
	private int 		studentId;
	private int 		visual;
	private int 		aural;
	private int 		readWrite;
	private int 		kinesthetic;
	private String		classLable;
	private int			schoolId;
	private String			yearId;
	
	
	public String getLDID() {
		return LDID;
	}
	public void setLDID(String lDID) {
		LDID = lDID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getVisual() {
		return visual;
	}
	public void setVisual(int visual) {
		this.visual = visual;
	}
	public int getAural() {
		return aural;
	}
	public void setAural(int aural) {
		this.aural = aural;
	}
	public int getReadWrite() {
		return readWrite;
	}
	public void setReadWrite(int readWrite) {
		this.readWrite = readWrite;
	}
	public int getKinesthetic() {
		return kinesthetic;
	}
	public void setKinesthetic(int kinesthetic) {
		this.kinesthetic = kinesthetic;
	}
	public String getClassLable() {
		return classLable;
	}
	public void setClassLable(String classLable) {
		this.classLable = classLable;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getYearId() {
		return yearId;
	}
	public void setYearId(String yearId) {
		this.yearId = yearId;
	}
	@Override
	public String toString() {
		return "VarkStudentResultDTO [LDID=" + LDID + ", name=" + name + ", studentId=" + studentId + ", visual="
				+ visual + ", aural=" + aural + ", readWrite=" + readWrite + ", kinesthetic=" + kinesthetic
				+ ", classLable=" + classLable + ", schoolId=" + schoolId + ", yearId=" + yearId + "]";
	} 
	
	
	
}
