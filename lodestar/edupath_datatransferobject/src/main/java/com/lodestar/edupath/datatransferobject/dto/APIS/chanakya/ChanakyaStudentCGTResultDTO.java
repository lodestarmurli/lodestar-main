package com.lodestar.edupath.datatransferobject.dto.APIS.chanakya;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ChanakyaStudentCGTResultDTO implements IModel {
	private static final long serialVersionUID = 1L;

	private int 		id;
	private int 		studentId;
	private String 		DHID;
	private String 		personalityCode;
	private String 		score;
	private String 		aptitudeComplete;
	private String 		appScore;
	private String 		ssFactor;
	private String 		occupationIds;
	private int 		occId1;
	private int 		occId2;
	private String      token1;
	private String      token2;
	private String      pdfToken;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAptitudeComplete() {
		return aptitudeComplete;
	}

	public void setAptitudeComplete(String aptitudeComplete) {
		this.aptitudeComplete = aptitudeComplete;
	}

	public String getAppScore() {
		return appScore;
	}

	public void setAppScore(String appScore) {
		this.appScore = appScore;
	}

	public String getSsFactor() {
		return ssFactor;
	}

	public void setSsFactor(String ssFactor) {
		this.ssFactor = ssFactor;
	}

	public String getOccupationIds() {
		return occupationIds;
	}

	public void setOccupationIds(String occupationIds) {
		this.occupationIds = occupationIds;
	}

	public int getOccId1() {
		return occId1;
	}

	public void setOccId1(int occId1) {
		this.occId1 = occId1;
	}

	public int getOccId2() {
		return occId2;
	}

	public void setOccId2(int occId2) {
		this.occId2 = occId2;
	}

	public String getDHID() {
		return DHID;
	}

	public void setDHID(String dHID) {
		DHID = dHID;
	}

	public String getToken1() {
		return token1;
	}

	public void setToken1(String token1) {
		this.token1 = token1;
	}

	public String getToken2() {
		return token2;
	}

	public void setToken2(String token2) {
		this.token2 = token2;
	}

	public String getPdfToken() {
		return pdfToken;
	}

	public void setPdfToken(String pdfToken) {
		this.pdfToken = pdfToken;
	}

	@Override
	public String toString() {
		return "ChanakyaStudentCGTResultDTO [id=" + id + ", studentId=" + studentId + ", DHID=" + DHID + ", personalityCode="
				+ personalityCode + ", score=" + score + ", aptitudeComplete=" + aptitudeComplete + ", appScore="
				+ appScore + ", ssFactor=" + ssFactor + ", occupationIds=" + occupationIds + ", occId1=" + occId1
				+ ", occId2=" + occId2 + ", token1=" + token1 + ", token2=" + token2 + ", pdfToken=" + pdfToken + "]";
	}

	 

 
}
