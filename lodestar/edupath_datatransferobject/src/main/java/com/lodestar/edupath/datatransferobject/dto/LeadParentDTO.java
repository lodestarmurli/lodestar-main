package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class LeadParentDTO implements Serializable, IModel{

	private static final long	serialVersionUID			= 1L;
	
	private int	id;
	private String	ParentEmailID;
	private int	StudentRegister;
	private int	StudentTestTaken;
	private String	ParentRegisterTime;
	private String	StudentRegisterTime;
	private String	StudentTestTakenTime;
	private String	LDID;
	private int	StudentID;
	private String	ParentName;
	private String	StudentEmailID;
	private String	ParentContactNo;
	private String	StudentContactNo;
	private String  Token;
	private int  ParentAppointmentBook;
	private String  AppointmentDateTime;
	private String Paymentid;
	private int  isFivedaymailsent;
	private int  isTendaymailsent;
	
	
	public String getPaymentid() {
		return Paymentid;
	}
	public void setPaymentid(String paymentid) {
		Paymentid = paymentid;
	}
	public int getIsFivedaymailsent() {
		return isFivedaymailsent;
	}
	public void setIsFivedaymailsent(int isFivedaymailsent) {
		this.isFivedaymailsent = isFivedaymailsent;
	}
	public int getIsTendaymailsent() {
		return isTendaymailsent;
	}
	public void setIsTendaymailsent(int isTendaymailsent) {
		this.isTendaymailsent = isTendaymailsent;
	}
	
	public int getParentAppointmentBook() {
		return ParentAppointmentBook;
	}
	public void setParentAppointmentBook(int parentAppointmentBook) {
		ParentAppointmentBook = parentAppointmentBook;
	}
	public String getAppointmentDateTime() {
		return AppointmentDateTime;
	}
	public void setAppointmentDateTime(String appointmentDateTime) {
		AppointmentDateTime = appointmentDateTime;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getParentName() {
		return ParentName;
	}
	public void setParentName(String parentName) {
		ParentName = parentName;
	}
	public String getStudentEmailID() {
		return StudentEmailID;
	}
	public void setStudentEmailID(String studentEmailID) {
		StudentEmailID = studentEmailID;
	}
	public String getParentContactNo() {
		return ParentContactNo;
	}
	public void setParentContactNo(String parentContactNo) {
		ParentContactNo = parentContactNo;
	}
	public String getStudentContactNo() {
		return StudentContactNo;
	}
	public void setStudentContactNo(String studentContactNo) {
		StudentContactNo = studentContactNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParentEmailID() {
		return ParentEmailID;
	}
	public void setParentEmailID(String parentEmailID) {
		ParentEmailID = parentEmailID;
	}
	public int getStudentRegister() {
		return StudentRegister;
	}
	public void setStudentRegister(int studentRegister) {
		StudentRegister = studentRegister;
	}
	public int getStudentTestTaken() {
		return StudentTestTaken;
	}
	public void setStudentTestTaken(int studentTestTaken) {
		StudentTestTaken = studentTestTaken;
	}
	public String getParentRegisterTime() {
		return ParentRegisterTime;
	}
	public void setParentRegisterTime(String parentRegisterTime) {
		ParentRegisterTime = parentRegisterTime;
	}
	public String getStudentRegisterTime() {
		return StudentRegisterTime;
	}
	public void setStudentRegisterTime(String studentRegisterTime) {
		StudentRegisterTime = studentRegisterTime;
	}
	public String getStudentTestTakenTime() {
		return StudentTestTakenTime;
	}
	public void setStudentTestTakenTime(String studentTestTakenTime) {
		StudentTestTakenTime = studentTestTakenTime;
	}
	public String getLDID() {
		return LDID;
	}
	public void setLDID(String lDID) {
		LDID = lDID;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	
	
	
	
	
}
