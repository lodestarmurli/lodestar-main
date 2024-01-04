package com.lodestar.edupath.datatransferobject.dto.engineeringSelector;

public class EngineeringSelectorDTO {

	private int 		studentId;
	private String		raisecCode;
	private String		completeOccList;
	private String		favSubPriorityList;
	private String		shortlistOcc;
	private String		shortlistOccWithFavSub;
	private String		finalList;
	private String 		set1;
	private String		set2;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getRaisecCode() {
		return raisecCode;
	}
	public void setRaisecCode(String raisecCode) {
		this.raisecCode = raisecCode;
	}
	public String getCompleteOccList() {
		return completeOccList;
	}
	public void setCompleteOccList(String completeOccList) {
		this.completeOccList = completeOccList;
	}
	public String getFavSubPriorityList() {
		return favSubPriorityList;
	}
	public void setFavSubPriorityList(String favSubPriorityList) {
		this.favSubPriorityList = favSubPriorityList;
	}
	public String getShortlistOcc() {
		return shortlistOcc;
	}
	public void setShortlistOcc(String shortlistOcc) {
		this.shortlistOcc = shortlistOcc;
	}
	public String getShortlistOccWithFavSub() {
		return shortlistOccWithFavSub;
	}
	public void setShortlistOccWithFavSub(String shortlistOccWithFavSub) {
		this.shortlistOccWithFavSub = shortlistOccWithFavSub;
	}
	public String getSet1() {
		return set1;
	}
	public void setSet1(String set1) {
		this.set1 = set1;
	}
	public String getSet2() {
		return set2;
	}
	public void setSet2(String set2) {
		this.set2 = set2;
	}
	public String getFinalList() {
		return finalList;
	}
	public void setFinalList(String finalList) {
		this.finalList = finalList;
	}
	@Override
	public String toString() {
		return "EngineeringSelectorDTO [studentId=" + studentId + ", raisecCode=" + raisecCode + ", completeOccList="
				+ completeOccList + ", favSubPriorityList=" + favSubPriorityList + ", shortlistOcc=" + shortlistOcc
				+ ", shortlistOccWithFavSub=" + shortlistOccWithFavSub + ", finalList=" + finalList + ", set1=" + set1
				+ ", set2=" + set2 + "]";
	}  
	
	
	
	
	
	
	
	
}
