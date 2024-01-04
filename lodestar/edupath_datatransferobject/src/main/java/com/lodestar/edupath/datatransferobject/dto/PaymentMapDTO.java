package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;

public class PaymentMapDTO implements Serializable, IModel{
	
	private static final long	serialVersionUID	= 1L;

	private int					id;
	private String				loginId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	@Override
	public String toString() {
		return "PaymentMapDTO [id=" + id + ", loginId=" + loginId + "]";
	}
	
}
