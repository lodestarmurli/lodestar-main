package com.lodestar.edupath.datatransferobject.enumtype;

public enum SessionTypeEnum
{
	PRESESSION("PreSession"), SESSION1("Session1"), SESSION2("Session2"), SESSION3("Session3"), SUMMARY("Summary"),
	PRESESSION_FACI("PreSessionFacilitator"),SESSION_1_FACI("Session1Facilitator"), SESSION_2_FACI("Session2Facilitator"), SESSION_3_FACI("Session3Facilitator"),
	SESSION_SUMMARY_FACI("SummaryFacilitator");
	private String	sessionName;

	SessionTypeEnum(String sessionName)
	{
		this.sessionName = sessionName;
	}

	public String getSessionName()
	{
		return sessionName;
	}
}
