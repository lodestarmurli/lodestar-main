package com.lodestar.edupath.datatransferobject.dto.role;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class ApplicationMenuDTO implements Serializable, IModel
{
	private static final long	serialVersionUID					= 4626449583303163830L;
	public final static String	GET_APPLICATIONMENU_DETAILS_BY_ROLE	= "ApplicationMenu.getAllApplicationMenuDetailsByRoleWeight";
	private int					id;
	private String				refName;
	private String				displayName;
	private String				actionPath;
	private int					menuLevel;
	private Integer				parentId;
	private int					menuOrder;
	private String				enablePermission;
	private String				allowCreate;
	private String				allowRead;
	private String				allowUpdate;
	private String				allowDelete;
	private int					roleWeight;
	private String				iconPath;

	private Boolean				enableClick							= true;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getRefName()
	{
		return refName;
	}

	public void setRefName(String refName)
	{
		this.refName = refName;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getActionPath()
	{
		return actionPath;
	}

	public void setActionPath(String actionPath)
	{
		this.actionPath = actionPath;
	}

	public int getMenuLevel()
	{
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel)
	{
		this.menuLevel = menuLevel;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public int getMenuOrder()
	{
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder)
	{
		this.menuOrder = menuOrder;
	}

	public String getEnablePermission()
	{
		return enablePermission;
	}

	public void setEnablePermission(String enablePermission)
	{
		this.enablePermission = enablePermission;
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

	public int getRoleWeight()
	{
		return roleWeight;
	}

	public void setRoleWeight(int roleWeight)
	{
		this.roleWeight = roleWeight;
	}

	@Override
	public String toString()
	{
		return "ApplicationMenuDTO [id=" + id + ", refName=" + refName + ", displayName=" + displayName + ", actionPath=" + actionPath + ", menuLevel=" + menuLevel
				+ ", parentId=" + parentId + ", menuOrder=" + menuOrder + ", enablePermission=" + enablePermission + ", allowCreate=" + allowCreate + ", allowRead="
				+ allowRead + ", allowUpdate=" + allowUpdate + ", allowDelete=" + allowDelete + ", roleWeight=" + roleWeight + ", iconPath=" + iconPath + "]";
	}

	public String getIconPath()
	{
		return iconPath;
	}

	public void setIconPath(String iconPath)
	{
		this.iconPath = iconPath;
	}

	public Boolean getEnableClick()
	{
		return enableClick;
	}

	public void setEnableClick(Boolean enableClick)
	{
		this.enableClick = enableClick;
	}

}
