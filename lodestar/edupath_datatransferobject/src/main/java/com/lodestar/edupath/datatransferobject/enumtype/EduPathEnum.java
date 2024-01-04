package com.lodestar.edupath.datatransferobject.enumtype;

public enum EduPathEnum
{
  ANY("Any"), PREFERRED("P"), COMPULSORY("C"), ISANY("disabled"), REQUIRED_P("P"), REQUIRED_C("C");
  
   String value;
  
  EduPathEnum(String value)
  {
	  this.value = value;
  }
  
  public String getValue()
  {
	  return value;
  }
}
