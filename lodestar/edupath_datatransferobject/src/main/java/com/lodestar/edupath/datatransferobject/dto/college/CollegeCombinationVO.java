package com.lodestar.edupath.datatransferobject.dto.college;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.StreamsDTO;

public class CollegeCombinationVO implements Serializable, IModel
{
	private static final long					serialVersionUID				= 1L;

	private int									id;
	private String								name;
	private int									ageOfTheInstitute;
	private String								collegeType;
	private Boolean								coachingFacility;
	private String								coachingCentreName;
	private String								address;
	private String								website;
	private String								addFormSubmDate;
	private String								appLastDateStr;

	// non table column

	private BoardDTO							boardDTO						= new BoardDTO();
	private List<StreamsDTO>					streamsList						= new ArrayList<StreamsDTO>();
	private List<CombinationDTO>				combinationList					= new ArrayList<CombinationDTO>();
	private List<CollegeInfraDTO>				collegeInfraList				= new ArrayList<CollegeInfraDTO>();
	private List<CollegeActivitiesDTO>			collegeActivitiesList			= new ArrayList<CollegeActivitiesDTO>();
	private List<CollegeCourseFeeSeatsDTO>		collegeCourseFeeSeatsList		= new ArrayList<CollegeCourseFeeSeatsDTO>();
	private List<CollegeStreamCombinationDTO>	collegeStreamCombinationList	= new ArrayList<CollegeStreamCombinationDTO>();
	private List<CollegeStreamDetailsDTO>		collegeStreamDetailsList		= new ArrayList<CollegeStreamDetailsDTO>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAgeOfTheInstitute()
	{
		return ageOfTheInstitute;
	}

	public void setAgeOfTheInstitute(int ageOfTheInstitute)
	{
		this.ageOfTheInstitute = ageOfTheInstitute;
	}

	public String getCollegeType()
	{
		return collegeType;
	}

	public void setCollegeType(String collegeType)
	{
		this.collegeType = collegeType;
	}

	public Boolean getCoachingFacility()
	{
		return coachingFacility;
	}

	public void setCoachingFacility(Boolean coachingFacility)
	{
		this.coachingFacility = coachingFacility;
	}

	public String getCoachingCentreName()
	{
		return coachingCentreName;
	}

	public void setCoachingCentreName(String coachingCentreName)
	{
		this.coachingCentreName = coachingCentreName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public BoardDTO getBoardDTO()
	{
		return boardDTO;
	}

	public void setBoardDTO(BoardDTO boardDTO)
	{
		this.boardDTO = boardDTO;
	}

	public List<StreamsDTO> getStreamsList()
	{
		return streamsList;
	}

	public void setStreamsList(List<StreamsDTO> streamsList)
	{
		this.streamsList = streamsList;
	}

	public List<CombinationDTO> getCombinationList()
	{
		return combinationList;
	}

	public void setCombinationList(List<CombinationDTO> combinationList)
	{
		this.combinationList = combinationList;
	}

	public List<CollegeInfraDTO> getCollegeInfraList()
	{
		return collegeInfraList;
	}

	public void setCollegeInfraList(List<CollegeInfraDTO> collegeInfraList)
	{
		this.collegeInfraList = collegeInfraList;
	}

	public List<CollegeActivitiesDTO> getCollegeActivitiesList()
	{
		return collegeActivitiesList;
	}

	public void setCollegeActivitiesList(List<CollegeActivitiesDTO> collegeActivitiesList)
	{
		this.collegeActivitiesList = collegeActivitiesList;
	}

	public List<CollegeCourseFeeSeatsDTO> getCollegeCourseFeeSeatsList()
	{
		return collegeCourseFeeSeatsList;
	}

	public void setCollegeCourseFeeSeatsList(List<CollegeCourseFeeSeatsDTO> collegeCourseFeeSeatsList)
	{
		this.collegeCourseFeeSeatsList = collegeCourseFeeSeatsList;
	}

	public List<CollegeStreamCombinationDTO> getCollegeStreamCombinationList()
	{
		return collegeStreamCombinationList;
	}

	public void setCollegeStreamCombinationList(List<CollegeStreamCombinationDTO> collegeStreamCombinationList)
	{
		this.collegeStreamCombinationList = collegeStreamCombinationList;
	}

	public List<CollegeStreamDetailsDTO> getCollegeStreamDetailsList()
	{
		return collegeStreamDetailsList;
	}

	public void setCollegeStreamDetailsList(List<CollegeStreamDetailsDTO> collegeStreamDetailsList)
	{
		this.collegeStreamDetailsList = collegeStreamDetailsList;
	}

	@Override
	public String toString()
	{
		return "CollegeCombinationVO [id=" + id + ", name=" + name + ", ageOfTheInstitute=" + ageOfTheInstitute + ", collegeType=" + collegeType
				+ ", coachingFacility=" + coachingFacility + ", coachingCentreName=" + coachingCentreName + ", address=" + address + ", website=" + website
				+ ", addFormSubmDate=" + addFormSubmDate + ", boardDTO=" + boardDTO + ", streamsList=" + streamsList + ", combinationList=" + combinationList
				+ ", collegeInfraList=" + collegeInfraList + ", collegeActivitiesList=" + collegeActivitiesList + ", collegeCourseFeeSeatsList="
				+ collegeCourseFeeSeatsList + ", collegeStreamCombinationList=" + collegeStreamCombinationList + ", collegeStreamDetailsList="
				+ collegeStreamDetailsList + "]";
	}

	public String getAppLastDateStr()
	{
		return appLastDateStr;
	}

	public void setAppLastDateStr(String appLastDateStr)
	{
		this.appLastDateStr = appLastDateStr;
	}

	public String getAddFormSubmDate()
	{
		return addFormSubmDate;
	}

	public void setAddFormSubmDate(String addFormSubmDate)
	{
		this.addFormSubmDate = addFormSubmDate;
	}

}
