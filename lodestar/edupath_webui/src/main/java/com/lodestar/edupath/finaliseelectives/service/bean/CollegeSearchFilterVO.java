package com.lodestar.edupath.finaliseelectives.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;

public class CollegeSearchFilterVO implements Serializable
{
	private static final long			serialVersionUID	= 1L;

	private List<Integer>				affiliationIds;
	// Year of existence
	private int							yoeFrom;
	private int							yoeTo;
	// Admission cut-off
	private int							admCutOffFrom;
	private int							admCutOffTo;
	// Fee
	private int							feeFrom;
	private int							feeTo;
	// Student Pass
	private int							studentPassFrom;
	private int							studentPassTo;
	// Sports and extra curricular
	private String						activities;
	// infrastructure
	private String						infrastructures;
	// Coaching
	private Boolean						coachingFacility;

	// Additional param's
	private String						zone;
	private List<Integer>				combinationIds;
	private int							streamId;
	private List<Integer>				reservationIds;
	private int							studentId;
	private List<Integer>				cityIds;
	private String						locality;
	// Pagination
	private int							pageNo;
	private boolean						getTotalCount;

	private List<CollegeParametersDTO>	allCollegeParams	= new ArrayList<CollegeParametersDTO>();
	private Boolean						isBackAction;
	
	//===Start Sasedeve edited by Mrutyunjaya on date 17-04-2017
	private int 						HomeCityId;
	
	
	
	

	public int getHomeCityId() {
		return HomeCityId;
	}

	public void setHomeCityId(int homeCityId) {
		HomeCityId = homeCityId;
	}

	//===END Sasedeve edited by Mrutyunjaya on date 17-04-2017
	
	
	public int getYoeFrom()
	{
		return yoeFrom;
	}

	public void setYoeFrom(int yoeFrom)
	{
		this.yoeFrom = yoeFrom;
	}

	public int getYoeTo()
	{
		return yoeTo;
	}

	public void setYoeTo(int yoeTo)
	{
		this.yoeTo = yoeTo;
	}

	public int getAdmCutOffTo()
	{
		return admCutOffTo;
	}

	public void setAdmCutOffTo(int admCutOffTo)
	{
		this.admCutOffTo = admCutOffTo;
	}

	public int getFeeFrom()
	{
		return feeFrom;
	}

	public void setFeeFrom(int feeFrom)
	{
		this.feeFrom = feeFrom;
	}

	public int getFeeTo()
	{
		return feeTo;
	}

	public void setFeeTo(int feeTo)
	{
		this.feeTo = feeTo;
	}

	public int getStudentPassFrom()
	{
		return studentPassFrom;
	}

	public void setStudentPassFrom(int studentPassFrom)
	{
		this.studentPassFrom = studentPassFrom;
	}

	public int getStudentPassTo()
	{
		return studentPassTo;
	}

	public void setStudentPassTo(int studentPassTo)
	{
		this.studentPassTo = studentPassTo;
	}

	public String getZone()
	{
		return zone;
	}

	public void setZone(String zone)
	{
		this.zone = zone;
	}

	public List<Integer> getCombinationIds()
	{
		return combinationIds;
	}

	public void setCombinationIds(List<Integer> combinationIds)
	{
		this.combinationIds = combinationIds;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public List<Integer> getAffiliationIds()
	{
		return affiliationIds;
	}

	public void setAffiliationIds(List<Integer> affiliationIds)
	{
		this.affiliationIds = affiliationIds;
	}

	public int getAdmCutOffFrom()
	{
		return admCutOffFrom;
	}

	public void setAdmCutOffFrom(int admCutOffFrom)
	{
		this.admCutOffFrom = admCutOffFrom;
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public List<Integer> getReservationIds()
	{
		return reservationIds;
	}

	public void setReservationIds(List<Integer> reservationIds)
	{
		this.reservationIds = reservationIds;
	}

	public boolean isGetTotalCount()
	{
		return getTotalCount;
	}

	public void setGetTotalCount(boolean getTotalCount)
	{
		this.getTotalCount = getTotalCount;
	}

	public Boolean getCoachingFacility()
	{
		return coachingFacility;
	}

	public void setCoachingFacility(Boolean coachingFacility)
	{
		this.coachingFacility = coachingFacility;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}

	public List<Integer> getCityIds()
	{
		return cityIds;
	}

	public void setCityIds(List<Integer> cityIds)
	{
		this.cityIds = cityIds;
	}

	public String getLocality()
	{
		return locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public List<CollegeParametersDTO> getAllCollegeParams()
	{
		return allCollegeParams;
	}

	public void setAllCollegeParams(List<CollegeParametersDTO> allCollegeParams)
	{
		this.allCollegeParams = allCollegeParams;
	}

	public String getActivities()
	{
		return activities;
	}

	public void setActivities(String activities)
	{
		this.activities = activities;
	}

	public String getInfrastructures()
	{
		return infrastructures;
	}

	public void setInfrastructures(String infrastructures)
	{
		this.infrastructures = infrastructures;
	}

	public Boolean getIsBackAction()
	{
		return isBackAction;
	}

	public void setIsBackAction(Boolean isBackAction)
	{
		this.isBackAction = isBackAction;
	}

}
