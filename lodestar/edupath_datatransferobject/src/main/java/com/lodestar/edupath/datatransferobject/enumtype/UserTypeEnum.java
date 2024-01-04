package com.lodestar.edupath.datatransferobject.enumtype;

public enum UserTypeEnum
{
	ADMIN("Admin"), SUBADMIN("SubAdmin"), FACILITATOR("Facilitator"), USER("User");

	private String	userType;

	UserTypeEnum(String userType)
	{
		this.userType = userType;
	}

	public String getDisplayName()
	{
		return userType;
	}
}
