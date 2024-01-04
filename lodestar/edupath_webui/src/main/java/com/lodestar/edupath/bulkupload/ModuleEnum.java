package com.lodestar.edupath.bulkupload;

public enum ModuleEnum
{
	//start Sasede Edited by vyankatesh on 3-2-2017 
	//Original code
//	FACILITATORS("Facilitator"), STUDENT("Student"), INTEREST_TEST("Interest Test");

	//Original code
	
	// Start Change code
	
	FACILITATORS("Facilitator"), CDDSTUDENT("CDD Student"), STUDENT("Student"), INTEREST_TEST("Interest Test"), PAYMENT("Payment"), STUDENT_INTEREST_TEST("StudentInterest Test"), CHANAKYA_STUDENT_TEST("Chanakya Student Test");
	// end Change code
	//End Sasede Edited by vyankatesh on 3-2-2017 
	private String	moduleName;

	private ModuleEnum(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getModuleName()
	{
		return moduleName;
	}
}
