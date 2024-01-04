package com.lodestar.edupath.datatransferobject.dto.APIS;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class AuthCodeValidationDTO implements IModel{
	private static final long	serialVersionUID	= 1L;
	
	private String EmailID;
	private String Password;
	private String AuthCode;
	
	private String UIN;
	private String EncryptionCode;
	
	
	
	public String getUIN() {
		return UIN;
	}
	public void setUIN(String uIN) {
		UIN = uIN;
	}
	public String getEncryptionCode() {
		return EncryptionCode;
	}
	public void setEncryptionCode(String encryptionCode) {
		EncryptionCode = encryptionCode;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getAuthCode() {
		return AuthCode;
	}
	public void setAuthCode(String authCode) {
		AuthCode = authCode;
	}

}
