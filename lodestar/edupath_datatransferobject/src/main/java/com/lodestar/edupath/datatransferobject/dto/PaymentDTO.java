package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.util.Date;

public class PaymentDTO implements Serializable, IModel {

	private static final long serialVersionUID = 1L;

	private int id;

	private int studentId;
	private String loginId;
	private String agreedAmount;
	private String dueAmount;
	private String paidAmount;
	private Date createdOn;
	private Date updatedOn;

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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAgreedAmount() {
		return agreedAmount;
	}

	public void setAgreedAmount(String agreedAmount) {
		this.agreedAmount = agreedAmount;
	}

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentDTO [id=" + id + ", studentId=" + studentId + ", loginId=" + loginId + ", agreedAmount="
				+ agreedAmount + ", dueAmount=" + dueAmount + ", paidAmount=" + paidAmount + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + "]";
	}

//	public Double setAgreedAmount(Double agreedAmount2) {
//		// TODO Auto-generated method stub
//		return agreedAmount2;
//	}
//
//	public Double setPaidAmount(Double paidAmount2) {
//		// TODO Auto-generated method stub
//		return paidAmount2;
//	}
//
//	public Double setDueAmount(Double dueAmount2) {
//		// TODO Auto-generated method stub
//		return dueAmount2;
//	}

	

}
