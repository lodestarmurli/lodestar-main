package com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery;

public class CareerDegreeDiscoveryResultDTO {

	private int 		studentId;
	private String 		personalityCode;
	private String		finalOcc;
	private int			occOne;
	private int			occTwo;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getPersonalityCode() {
		return personalityCode;
	}
	public void setPersonalityCode(String personalityCode) {
		this.personalityCode = personalityCode;
	}
	public String getFinalOcc() {
		return finalOcc;
	}
	public void setFinalOcc(String finalOcc) {
		this.finalOcc = finalOcc;
	}
	public int getOccOne() {
		return occOne;
	}
	public void setOccOne(int occOne) {
		this.occOne = occOne;
	}
	public int getOccTwo() {
		return occTwo;
	}
	public void setOccTwo(int occTwo) {
		this.occTwo = occTwo;
	}
	@Override
	public String toString() {
		return "CareerDegreeDiscoveryResultDTO [studentId=" + studentId + ", personalityCode=" + personalityCode
				+ ", finalOcc=" + finalOcc + ", occOne=" + occOne + ", occTwo=" + occTwo + "]";
	}
	
	
}
