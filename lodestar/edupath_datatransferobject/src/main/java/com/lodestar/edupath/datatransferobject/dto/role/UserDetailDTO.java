package com.lodestar.edupath.datatransferobject.dto.role;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class UserDetailDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 6682663992475484959L;
	public final static String	GET_USER_BY_LOGINID	= "UserDetail.getUserDetailsByLoginId";
	public final static String	ADD_USER_DETAILS	= "UserDetail.addUserDetail";
	private int					id;
	private String				loginId;
	private byte[]				password;
	private int					roleId;
	private String				userType;
	private String				isActive;
	private int					roleWeight;
	private int					roleTypeId;
	private Date				createdOn;
	private Date				updatedOn;

	public int getId()
	{
		return id;
	}

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

	public byte[] getPassword()
	{
		return password;
	}

	public void setPassword(byte[] password)
	{
		this.password = password;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public int getRoleWeight()
	{
		return roleWeight;
	}

	public void setRoleWeight(int roleWeight)
	{
		this.roleWeight = roleWeight;
	}

	public int getRoleTypeId()
	{
		return roleTypeId;
	}

	public void setRoleTypeId(int roleTypeId)
	{
		this.roleTypeId = roleTypeId;
	}

	@Override
	public String toString()
	{
		return "UserDetailDTO [id=" + id + ", loginId=" + loginId + ", password=" + Arrays.toString(password) + ", roleId=" + roleId + ", userType=" + userType
				+ ", isActive=" + isActive + ", roleWeight=" + roleWeight + ", roleTypeId=" + roleTypeId + "]";
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn)
	{
		this.updatedOn = updatedOn;
	}

}
