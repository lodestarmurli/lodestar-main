package com.lodestar.edupath.datatransferobject.dto.student;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class TumCgtResultDTO implements IModel
{
	private static final long			serialVersionUID	= 1L;

	private int  						studentId;
	private int  						TUMResult;
	private int  						InterestResult;
	private int  						AptitudeResult;
	
	public int getTUMResult() {
		return TUMResult;
	}
	public void setTUMResult(int tUMResult) {
		TUMResult = tUMResult;
	}
	public int getInterestResult() {
		return InterestResult;
	}
	public void setInterestResult(int interestResult) {
		InterestResult = interestResult;
	}
	public int getAptitudeResult() {
		return AptitudeResult;
	}
	public void setAptitudeResult(int aptitudeResult) {
		AptitudeResult = aptitudeResult;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString() {
		return "TumCgtResultDTO [studentId=" + studentId + ", TUMResult=" + TUMResult + ", InterestResult="
				+ InterestResult + ", AptitudeResult=" + AptitudeResult + "]";
	}
	
	
	
	
	
}
