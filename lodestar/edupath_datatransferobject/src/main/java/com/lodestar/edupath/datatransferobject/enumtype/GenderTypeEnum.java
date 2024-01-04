package com.lodestar.edupath.datatransferobject.enumtype;

public enum GenderTypeEnum
{
	F("Female"), M("Male");

	private String	gender;

	GenderTypeEnum(String gender)
	{
		this.gender = gender;
	}

	public String getGender()
	{
		return gender;
	}
}
