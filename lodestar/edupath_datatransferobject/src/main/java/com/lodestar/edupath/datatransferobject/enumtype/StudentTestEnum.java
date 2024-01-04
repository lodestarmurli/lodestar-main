package com.lodestar.edupath.datatransferobject.enumtype;

public enum StudentTestEnum
{
   STARTED("STARTED"), COMPLETED("COMPLETED");
   
   private String	value;

   StudentTestEnum(String value)
   {
		this.value = value;
   }

	public String getValue()
	{
		return value;
	}
   
}
