package com.lodestar.edupath.datatransferobject.dto.APIS;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class PartnerDetailsDTO implements IModel{
	private static final long	serialVersionUID	= 1L;
	
	
	private int    id;
	private String    UIN;
	private String    PartneName;
	private String    EmailID;
	private String    CreationDate;
	
	private int 	  IsSendMail;
	private String    EmailTemplate;
	private String	  logpath;
	private String	  EncryptionCode;
	
	public String getEncryptionCode() {
		return EncryptionCode;
	}
	public void setEncryptionCode(String encryptionCode) {
		EncryptionCode = encryptionCode;
	}
	public int getIsSendMail() {
		return IsSendMail;
	}
	public void setIsSendMail(int isSendMail) {
		IsSendMail = isSendMail;
	}
	public String getEmailTemplate() {
		return EmailTemplate;
	}
	public void setEmailTemplate(String emailTemplate) {
		EmailTemplate = emailTemplate;
	}
	public String getLogpath() {
		return logpath;
	}
	public void setLogpath(String logpath) {
		this.logpath = logpath;
	}
	public int getID() {
		return id;
	}
	public void setID(int iD) {
		id = iD;
	}
	
	public String getUIN() {
		return UIN;
	}
	public void setUIN(String uIN) {
		UIN = uIN;
	}
	public String getPartneName() {
		return PartneName;
	}
	public void setPartneName(String partneName) {
		PartneName = partneName;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	
	

}
