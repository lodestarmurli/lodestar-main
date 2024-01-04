package com.lodestar.edupath.datatransferobject.dto.role;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class UserRoleDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= 9193374160697079504L;
	private int					id;
	private String				name;
	private int					roleTypeId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
		return "UserRoleDTO [id=" + id + ", name=" + name + ", roleTypeId=" + roleTypeId + "]";
	}

}
