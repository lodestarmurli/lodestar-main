package com.lodestar.edupath.datatransferobject.enumtype;

public enum VenueTypeEnum
{
   ATOFFICE("AT OFFICE"), ATSCHOOL("your school"), OTHER("OTHER") , AT_SCHOOL_EMAIL("school");
   
   private String value;
   VenueTypeEnum(String value)
   {
	   this.value = value;
   }
   
   public String getValue()
   {
	   return value;
   }
}
