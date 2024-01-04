package com.jamochatech.tools.fileupload.dao;

public class DBOperations
{
	// occupation image upload
	public static final String	UPDATE_OCCUPATION_IMAGE		= "Occupation.updateOccupationImage";
	public static final String	GET_OCCUPATION_NAME_AND_ID	= "Occupation.getOccupationNameAndId";

	// document upload
	public static final String	GET_ALL_INDUSTRY			= "Industry.getAllIndustry";
	public static final String	GET_ALL_SUBJECT				= "Subject.getAllSubject";
	public static final String	GET_ALL_DOCUMENT			= "Documents.getAllDocument";
	public static final String	INSERT_DOCUMENT				= "Documents.insertDocumentPath";
	public static final String	UPDATE_DOCUMENT				= "Documents.updateDocumentPath";

	// TrailInterest Code Mapping Upload
	public static final String	GET_ALL_TRIAL_INTREST_CODE	= "TrialInterestCodeMapping.getTrialInterest";
	public static final String	INSERT_TRIAL_INTREST_CODE	= "TrialInterestCodeMapping.insertTrialInterest";
	public static final String	UPDATE_TRIAL_INTREST_CODE	= "TrialInterestCodeMapping.updateTrialInterest";
}
