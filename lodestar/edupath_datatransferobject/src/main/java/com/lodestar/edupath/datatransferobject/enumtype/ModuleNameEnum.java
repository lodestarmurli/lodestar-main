package com.lodestar.edupath.datatransferobject.enumtype;

public enum ModuleNameEnum
{
	USER_DEFINITION("USER DEFINITION"),
	FORGOT_PASSWORD("FORGOT PASSWORD"),
	ROLE_DEFINITION("ROLE DEFINITION"),
	WORK_ASSOCIATE("WORK ASSOCIATE"),
	LOGIN("LOGIN"),
	LOGOUT("LOGOUT"),
	MANAGE_STUDENT("MANAGE STUDENT"),
	BULK_UPLOAD_STUDENT("STUDENT BULK UPLOAD"),
	ANSWER_STUDENT("ANSWER STUDENT"),
	OCCUPATION_SHORT_LIST("OCCUPATION SHORT LIST"),
	OCCUPATION_WISH_LIST("OCCUPATION WISH LIST"),
	STUDENT_CART("STUDENT CART"),
	TUM_DELETE("TUM DELETE"),
	EDU_PATH("EDU PATH"),
	TUTORIAL_CART("TUTORIAL CART"),
	COLLEGE_CART("COLLEGE CART"),
	SUMMARY("SUMMARY"),
	OPEN_SESSION("OPEN SESSION"),
	FEED_BACK("FEED_BACK"),
	BULK_UPLOAD_INTEREST_TEST("INTEREST TEST BULK UPLOAD"),
	MANAGE_TRIAL_STUDENT("MANAGE TRIAL STUDENT");

	private String	moduleName;

	ModuleNameEnum(String moduleName)
	{
		this.moduleName = moduleName;
	}

	/**
	 * @return
	 */
	public String getDisplayName()
	{
		return moduleName;
	}
}
