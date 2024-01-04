package com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment;

public class CareerFitmentOccNotFoundDTO { 

	private int 		studentId;
	private String 		OccName;
	private int			locked;
 
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getOccName() {
		return OccName;
	}
	public void setOccName(String occName) {
		OccName = occName;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	@Override
	public String toString() {
		return "CareerFitmentOccNotFoundDTO [studentId=" + studentId + ", OccName=" + OccName + ", locked=" + locked
				+ "]";
	}
	 
	
	
	
}
