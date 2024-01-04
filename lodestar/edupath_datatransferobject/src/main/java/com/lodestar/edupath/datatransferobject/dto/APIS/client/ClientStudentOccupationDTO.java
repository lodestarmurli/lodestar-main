package com.lodestar.edupath.datatransferobject.dto.APIS.client;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ClientStudentOccupationDTO implements IModel
{
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private int					studentId;
	private int					occ1Id;
	private int					occ2Id;
	private String				occList;
	private String				token1;
	private String				token2;
	private String 				personalityCode;
	private String				pdfToken;
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
	public int getOcc1Id() {
		return occ1Id;
	}
	public void setOcc1Id(int occ1Id) {
		this.occ1Id = occ1Id;
	}
	public int getOcc2Id() {
		return occ2Id;
	}
	public void setOcc2Id(int occ2Id) {
		this.occ2Id = occ2Id;
	}
	public String getOccList() {
		return occList;
	}
	public void setOccList(String occList) {
		this.occList = occList;
	}
	
	public String getPersonalityCode() {
		return personalityCode;
	}
	public void setPersonalityCode(String personalityCode) {
		this.personalityCode = personalityCode;
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
		return "DHStudentOccupationDTO [id=" + id + ", studentId=" + studentId + ", occ1Id=" + occ1Id + ", occ2Id="
				+ occ2Id + ", occList=" + occList + ", token1=" + token1 + ", token2=" + token2 + ", personalityCode="
				+ personalityCode + ", pdfToken=" + pdfToken + "]";
	}
	
	
	
}
