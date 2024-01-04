package com.lodestar.edupath.datatransferobject.dto.programTest.engineeringSelector;

public class EBFavouriteSelectorDTO {

	private int studentId;
	private int physics;
	private int mathematics;
	private int chemistry;
	private int biology;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getPhysics() {
		return physics;
	}
	public void setPhysics(int physics) {
		this.physics = physics;
	}
	public int getMathematics() {
		return mathematics;
	}
	public void setMathematics(int mathematics) {
		this.mathematics = mathematics;
	}
	public int getChemistry() {
		return chemistry;
	}
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}
	public int getBiology() {
		return biology;
	}
	public void setBiology(int biology) {
		this.biology = biology;
	}
	@Override
	public String toString() {
		return "EBSelectorDTO [studentId=" + studentId + ", physics=" + physics + ", mathematics=" + mathematics
				+ ", chemistry=" + chemistry + ", biology=" + biology + "]";
	}
	
	
	
	
	
	
}
