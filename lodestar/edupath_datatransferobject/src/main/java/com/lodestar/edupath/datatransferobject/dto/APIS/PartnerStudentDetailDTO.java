package com.lodestar.edupath.datatransferobject.dto.APIS;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class PartnerStudentDetailDTO implements IModel{
	private static final long	serialVersionUID	= 1L;
	
	private int 			id;
	private String 			partnerUIN;
	private String 			studenUIN;
	private int 			studentid;
	private String 			LDID;
	private String 			CityString;
	private int				IsRegister;
	
	
	public String getCityString() {
		return CityString;
	}
	public void setCityString(String cityString) {
		CityString = cityString;
	}
	public int getIsRegister() {
		return IsRegister;
	}
	public void setIsRegister(int isRegister) {
		IsRegister = isRegister;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartnerUIN() {
		return partnerUIN;
	}
	public void setPartnerUIN(String partnerUIN) {
		this.partnerUIN = partnerUIN;
	}
	public String getStudenUIN() {
		return studenUIN;
	}
	public void setStudenUIN(String studenUIN) {
		this.studenUIN = studenUIN;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getLDID() {
		return LDID;
	}
	public void setLDID(String lDID) {
		LDID = lDID;
	}
	
	
	
}
