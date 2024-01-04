package com.lodestar.edupath.datatransferobject.dto;

import java.util.ArrayList;
import java.util.List;

public class EntranceExamsDTO implements IModel
{

	private static final long		serialVersionUID	= 1L;
	private int						id;
	private String					examName;
	private String					examWhen;
	private String					aboutExam;
	private String					eligibility;
	private String					subNMarks;
	private String					monthOfExam;
	private String					insititutesAcceptScore;
	private Integer					noOfSeats;
	private Integer					candidateAppearing;
	private String					annualFee;
	private String					ratio;
	private String					examLevel;
	private boolean					isActive;

	// non table
	private String					occupationName;
	private String					occIndustryName;
	private int						occupationId;
	private int						entranceExamId;
	private String					required;
	private List<EntranceExamsDTO>	examList			= new ArrayList<EntranceExamsDTO>();
	private Integer					cityId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getExamName()
	{
		return examName;
	}

	public void setExamName(String examName)
	{
		this.examName = examName;
	}

	public String getExamWhen()
	{
		return examWhen;
	}

	public void setExamWhen(String examWhen)
	{
		this.examWhen = examWhen;
	}

	public String getAboutExam()
	{
		return aboutExam;
	}

	public void setAboutExam(String aboutExam)
	{
		this.aboutExam = aboutExam;
	}

	public String getEligibility()
	{
		return eligibility;
	}

	public void setEligibility(String eligibility)
	{
		this.eligibility = eligibility;
	}

	public String getSubNMarks()
	{
		return subNMarks;
	}

	public void setSubNMarks(String subNMarks)
	{
		this.subNMarks = subNMarks;
	}

	public String getMonthOfExam()
	{
		return monthOfExam;
	}

	public void setMonthOfExam(String monthOfExam)
	{
		this.monthOfExam = monthOfExam;
	}

	public String getInsititutesAcceptScore()
	{
		return insititutesAcceptScore;
	}

	public void setInsititutesAcceptScore(String insititutesAcceptScore)
	{
		this.insititutesAcceptScore = insititutesAcceptScore;
	}

	public Integer getNoOfSeats()
	{
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats)
	{
		this.noOfSeats = noOfSeats;
	}

	public Integer getCandidateAppearing()
	{
		return candidateAppearing;
	}

	public void setCandidateAppearing(Integer candidateAppearing)
	{
		this.candidateAppearing = candidateAppearing;
	}

	public String getAnnualFee()
	{
		return annualFee;
	}

	public void setAnnualFee(String annualFee)
	{
		this.annualFee = annualFee;
	}

	public String getRatio()
	{
		return ratio;
	}

	public void setRatio(String ratio)
	{
		this.ratio = ratio;
	}

	public String getExamLevel()
	{
		return examLevel;
	}

	public void setExamLevel(String examLevel)
	{
		this.examLevel = examLevel;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getOccupationName()
	{
		return occupationName;
	}

	public void setOccupationName(String occupationName)
	{
		this.occupationName = occupationName;
	}

	public String getOccIndustryName()
	{
		return occIndustryName;
	}

	public void setOccIndustryName(String occIndustryName)
	{
		this.occIndustryName = occIndustryName;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public int getEntranceExamId()
	{
		return entranceExamId;
	}

	public void setEntranceExamId(int entranceExamId)
	{
		this.entranceExamId = entranceExamId;
	}

	@Override
	public String toString()
	{
		return "EntranceExamsDTO [id=" + id + ", examName=" + examName + ", examWhen=" + examWhen + ", aboutExam=" + aboutExam + ", eligibility=" + eligibility
				+ ", subNMarks=" + subNMarks + ", monthOfExam=" + monthOfExam + ", insititutesAcceptScore=" + insititutesAcceptScore + ", noOfSeats=" + noOfSeats
				+ ", candidateAppearing=" + candidateAppearing + ", annualFee=" + annualFee + ", ratio=" + ratio + ", examLevel=" + examLevel + ", isActive="
				+ isActive + ", occupationName=" + occupationName + ", occIndustryName=" + occIndustryName + ", occupationId=" + occupationId + ", entranceExamId="
				+ entranceExamId + "]";
	}

	public List<EntranceExamsDTO> getExamList()
	{
		return examList;
	}

	public void setExamList(List<EntranceExamsDTO> examList)
	{
		this.examList = examList;
	}

	public String getRequired()
	{
		return required;
	}

	public void setRequired(String required)
	{
		this.required = required;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
