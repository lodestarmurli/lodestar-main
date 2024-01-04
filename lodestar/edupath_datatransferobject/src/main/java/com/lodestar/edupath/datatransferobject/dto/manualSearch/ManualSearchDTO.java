package com.lodestar.edupath.datatransferobject.dto.manualSearch;

import java.util.LinkedHashMap;
import java.util.Map;

public class ManualSearchDTO
{
	Map<Double, String>	searchQuestions	= new LinkedHashMap<Double, String>();
	private String			mandatoryQuestions;									// should says 1,5

	public Map<Double, String> getSearchQuestions()
	{
		return searchQuestions;
	}

	public void setSearchQuestions(Map<Double, String> searchQuestions)
	{
		this.searchQuestions = searchQuestions;
	}

	public String getMandatoryQuestions()
	{
		return mandatoryQuestions;
	}

	public void setMandatoryQuestions(String mandatoryQuestions)
	{
		this.mandatoryQuestions = mandatoryQuestions;
	}

}
