package com.lodestar.edupath.datatransferobject.dto.partner;

public class PartnerDeatilsDTO {

	private int id;
	private String PartnerName;
	private String APIkey;
	private int isActive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartnerName() {
		return PartnerName;
	}
	public void setPartnerName(String partnerName) {
		PartnerName = partnerName;
	}
	public String getAPIkey() {
		return APIkey;
	}
	public void setAPIkey(String aPIkey) {
		APIkey = aPIkey;
	}
	
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "PartnerDeatilsDTO [id=" + id + ", PartnerName=" + PartnerName + ", APIkey=" + APIkey + ", isActive="
				+ isActive + "]";
	}
	 
	
	
	
}
