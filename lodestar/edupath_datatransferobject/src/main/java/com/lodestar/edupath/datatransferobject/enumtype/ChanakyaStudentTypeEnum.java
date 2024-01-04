package com.lodestar.edupath.datatransferobject.enumtype;

public enum ChanakyaStudentTypeEnum
{
	TYPE1("Type1"), TYPE2("Type2");
//	ORGROUP("ORGROUP");

	private String	text;

	private ChanakyaStudentTypeEnum(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return this.text;
	}

	public String getName()
	{
		return this.name();
	}

 
}
