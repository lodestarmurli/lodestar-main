package com.lodestar.edupath.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class StudentSessionObject
{
	private int							id;

	private String						loginId;

	private String						fullName;

	private String						emailId;

	private int							userId;

	private int							sessionOneCompleted;

	private Map<Integer, Integer>		occIndIdMap		= new HashMap<Integer, Integer>();

	private SortedMap<Integer, String>	orderNameMap	= new TreeMap<Integer, String>();

	private int							cityId;
	private Boolean						isCanChangeCart	= true;

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public int getSessionOneCompleted()
	{
		return sessionOneCompleted;
	}

	public void setSessionOneCompleted(int sessionOneCompleted)
	{
		this.sessionOneCompleted = sessionOneCompleted;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public Map<Integer, Integer> getOccIndIdMap()
	{
		return occIndIdMap;
	}

	public void setOccIndIdMap(Map<Integer, Integer> occIndIdMap)
	{
		this.occIndIdMap = occIndIdMap;
	}

	public SortedMap<Integer, String> getOrderNameMap()
	{
		return orderNameMap;
	}

	public void setOrderNameMap(SortedMap<Integer, String> orderNameMap)
	{
		this.orderNameMap = orderNameMap;
	}

	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}

	public int getCityId()
	{
		return cityId;
	}

	public int getId()
	{
		return id;
	}

	public Boolean getIsCanChangeCart()
	{
		return isCanChangeCart;
	}

	public void setIsCanChangeCart(Boolean isCanChangeCart)
	{
		this.isCanChangeCart = isCanChangeCart;
	}

	@Override
	public String toString() {
		return "StudentSessionObject [id=" + id + ", loginId=" + loginId + ", fullName=" + fullName + ", emailId="
				+ emailId + ", userId=" + userId + ", sessionOneCompleted=" + sessionOneCompleted + ", occIndIdMap="
				+ occIndIdMap + ", orderNameMap=" + orderNameMap + ", cityId=" + cityId + ", isCanChangeCart="
				+ isCanChangeCart + "]";
	}
	
}
