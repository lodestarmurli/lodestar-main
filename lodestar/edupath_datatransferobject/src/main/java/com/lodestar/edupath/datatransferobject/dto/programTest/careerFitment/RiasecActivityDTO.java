package com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment;

public class RiasecActivityDTO {

	private int 		id;
	private String 		code;
	private String  	fullCode;
	private String 		activity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFullCode() {
		return fullCode;
	}
	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	@Override
	public String toString() {
		return "RiasecActivityDTO [id=" + id + ", code=" + code + ", fullCode=" + fullCode + ", activity=" + activity
				+ "]";
	}
	
	
	
}
