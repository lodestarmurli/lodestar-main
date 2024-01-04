package com.lodestar.edupath.datatransferobject.dto.role;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class RolePermissionDTO implements Serializable, IModel
{

	private static final long	serialVersionUID	= -7694367502429623313L;
	private int					id;
	private int					menuId;
	private int					roleId;
	private String				allowCreate;
	private String				allowRead;
	private String				allowUpdate;
	private String				allowDelete;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getAllowCreate()
	{
		return allowCreate;
	}

	public void setAllowCreate(String allowCreate)
	{
		this.allowCreate = allowCreate;
	}

	public String getAllowRead()
	{
		return allowRead;
	}

	public void setAllowRead(String allowRead)
	{
		this.allowRead = allowRead;
	}

	public String getAllowUpdate()
	{
		return allowUpdate;
	}

	public void setAllowUpdate(String allowUpdate)
	{
		this.allowUpdate = allowUpdate;
	}

	public String getAllowDelete()
	{
		return allowDelete;
	}

	public void setAllowDelete(String allowDelete)
	{
		this.allowDelete = allowDelete;
	}

	@Override
	public String toString()
	{
		return "RolePermissionDTO [id=" + id + ", menuId=" + menuId + ", roleId=" + roleId + ", allowCreate=" + allowCreate + ", allowRead=" + allowRead
				+ ", allowUpdate=" + allowUpdate + ", allowDelete=" + allowDelete + "]";
	}

}
