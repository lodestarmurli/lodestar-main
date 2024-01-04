package com.lodestar.edupath.datatransferobject.dto.streamselector;

public class StreamSelectorStudentDTO {


	private int			id;
	private String		ParentEmailID;   
	private String		StudentRegisterTime;
	private String		LDID;
	private int			StudentID;
	private String		StudentEmailID;
	private String		ParentContactNo;
	private String		StudentContactNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParentEmailID() {
		return ParentEmailID;
	}
	public void setParentEmailID(String parentEmailID) {
		ParentEmailID = parentEmailID;
	}
	public String getStudentRegisterTime() {
		return StudentRegisterTime;
	}
	public void setStudentRegisterTime(String studentRegisterTime) {
		StudentRegisterTime = studentRegisterTime;
	}
	public String getLDID() {
		return LDID;
	}
	public void setLDID(String lDID) {
		LDID = lDID;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getStudentEmailID() {
		return StudentEmailID;
	}
	public void setStudentEmailID(String studentEmailID) {
		StudentEmailID = studentEmailID;
	}
	public String getParentContactNo() {
		return ParentContactNo;
	}
	public void setParentContactNo(String parentContactNo) {
		ParentContactNo = parentContactNo;
	}
	public String getStudentContactNo() {
		return StudentContactNo;
	}
	public void setStudentContactNo(String studentContactNo) {
		StudentContactNo = studentContactNo;
	}
	@Override
	public String toString() {
		return "StreamSelectorStudentDTO [id=" + id + ", ParentEmailID=" + ParentEmailID + ", StudentRegisterTime="
				+ StudentRegisterTime + ", LDID=" + LDID + ", StudentID=" + StudentID + ", StudentEmailID="
				+ StudentEmailID + ", ParentContactNo=" + ParentContactNo + ", StudentContactNo=" + StudentContactNo
				+ "]";
	}
	
	 
	
	
	
	
	
}
