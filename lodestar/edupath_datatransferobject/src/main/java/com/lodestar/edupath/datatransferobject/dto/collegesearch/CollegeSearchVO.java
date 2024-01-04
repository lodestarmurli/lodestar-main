package com.lodestar.edupath.datatransferobject.dto.collegesearch;

import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class CollegeSearchVO implements IModel
{
	private static final long				serialVersionUID	= 1L;

	private int								collegeId;
	private String							collegeName;
	private String							affiliationToBoard;
	private String							address;
	private String							zone;
	private int								ageOfTheInstitute;
	private String							sexPref;

	private boolean							integratedCoaching;
	private String							coachingCenterName;
	// Student stream cutoff
	private int								lastCutOff;
	private List<CustCollegeStreamVO>		collegeStreamDetails;
	private List<CustCollegeCombinationVO>	collegeCombinationDetails;
	private List<CustCollegeCombinationVO>	selectedCombinationDetails;

	public String getAffiliationToBoard()
	{
		return affiliationToBoard;
	}

	public void setAffiliationToBoard(String affiliationToBoard)
	{
		this.affiliationToBoard = affiliationToBoard;
	}

	public String getCoachingCenterName()
	{
		return coachingCenterName;
	}

	public void setCoachingCenterName(String coachingCenterName)
	{
		this.coachingCenterName = coachingCenterName;
	}

	public int getCollegeId()
	{
		return collegeId;
	}

	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}

	public String getCollegeName()
	{
		return collegeName;
	}

	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}

	public List<CustCollegeStreamVO> getCollegeStreamDetails()
	{
		return collegeStreamDetails;
	}

	public void setCollegeStreamDetails(List<CustCollegeStreamVO> collegeStreamDetails)
	{
		this.collegeStreamDetails = collegeStreamDetails;
	}

	public List<CustCollegeCombinationVO> getCollegeCombinationDetails()
	{
		return collegeCombinationDetails;
	}

	public void setCollegeCombinationDetails(List<CustCollegeCombinationVO> collegeCombinationDetails)
	{
		this.collegeCombinationDetails = collegeCombinationDetails;
	}

	public List<CustCollegeCombinationVO> getSelectedCombinationDetails()
	{
		return selectedCombinationDetails;
	}

	public void setSelectedCombinationDetails(List<CustCollegeCombinationVO> selectedCombinationDetails)
	{
		this.selectedCombinationDetails = selectedCombinationDetails;
	}

	public int getLastCutOff()
	{
		return lastCutOff;
	}

	public void setLastCutOff(int lastCutOff)
	{
		this.lastCutOff = lastCutOff;
	}

	public boolean isIntegratedCoaching()
	{
		return integratedCoaching;
	}

	public void setIntegratedCoaching(boolean integratedCoaching)
	{
		this.integratedCoaching = integratedCoaching;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getZone()
	{
		return zone;
	}

	public void setZone(String zone)
	{
		this.zone = zone;
	}

	public int getAgeOfTheInstitute()
	{
		return ageOfTheInstitute;
	}

	public void setAgeOfTheInstitute(int ageOfTheInstitute)
	{
		this.ageOfTheInstitute = ageOfTheInstitute;
	}

	@Override
	public String toString()
	{
		return "CollegeSearchVO [collegeId=" + collegeId + ", collegeName=" + collegeName + ", affiliationToBoard=" + affiliationToBoard + ", address=" + address
				+ ", zone=" + zone + ", ageOfTheInstitute=" + ageOfTheInstitute + ", integratedCoaching=" + integratedCoaching + ", coachingCenterName="
				+ coachingCenterName + ", lastCutOff=" + lastCutOff + ", collegeStreamDetails=" + collegeStreamDetails + ", collegeCombinationDetails="
				+ collegeCombinationDetails + ", selectedCombinationDetails=" + selectedCombinationDetails + "]";
	}

	public String getSexPref()
	{
		return sexPref;
	}

	public void setSexPref(String sexPref)
	{
		this.sexPref = sexPref;
	}

}
