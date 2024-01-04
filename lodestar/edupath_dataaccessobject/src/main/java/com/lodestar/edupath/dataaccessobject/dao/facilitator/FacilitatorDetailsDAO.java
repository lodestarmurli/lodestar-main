package com.lodestar.edupath.dataaccessobject.dao.facilitator;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorCityMapDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorStatsReportDTO;
import com.lodestar.edupath.datatransferobject.dto.LanguagesDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class FacilitatorDetailsDAO
{

	private static final Logger	OUT	= LoggerFactory.getLogger(FacilitatorDetailsDAO.class);

	public List<FacilitatorDetailsDTO> getAllFacilitatorsDetailsList()
	{
		try
		{
			OUT.debug("FacilitatorDetailsDAO: Getting all Facilitator Details");
			List<FacilitatorDetailsDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_Facilitator_Details, null);
			OUT.debug("FacilitatorDetailsDAO: Number of Facilitator Details found: {}", resultList != null ? resultList.size() : 0);
			return resultList;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public FacilitatorDetailsDTO getFacilitatorDetailsById(int id)
	{
		FacilitatorDetailsDTO dto = null;
		OUT.debug("FacilitatorDetailsDAO: Getting  Facilitator Details by id");
		try
		{
			dto = (FacilitatorDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_Facilitator_Details_BY_ID, id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}

	public List<EducationLevelDTO> getEducationLevelNameWithId()
	{
		List<EducationLevelDTO> resultList = null;
		try
		{
			OUT.debug("FacilitatorDetailsDAO: Getting all Education Level Details");
			resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_Education_Level_NAME_WITH_ID, null);
			OUT.debug("FacilitatorDetailsDAO: Number of Education Level Details found: {}", resultList != null ? resultList.size() : 0);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultList;
	}

	public List<CityDTO> getCityNameWithId()
	{
		List<CityDTO> resultList = null;
		try
		{
			OUT.debug("FacilitatorDetailsDAO: Getting all City Details");
			resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_CITY_NAME_WITH_ID, null);
			OUT.debug("FacilitatorDetailsDAO: Number of City Details found: {}", resultList != null ? resultList.size() : 0);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultList;
	}

	public int insertFacilitatorDetails(SqlSession session, FacilitatorDetailsDTO facilitatorDTO) throws Exception
	{
		int insertId = 0;
		try
		{
			insertId = session.insert(MyBatisMappingConstants.INSERT_Facilitator_Details, facilitatorDTO);
			OUT.debug("Insert FacilitatorDetails id: {}", insertId);

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorDTO.getId();
	}

	public int updateFacilitatorDetailsById(SqlSession session, FacilitatorDetailsDTO facilitatorDetailsDTO)
	{
		int id = 0;
		try
		{
			id = session.update(MyBatisMappingConstants.UPDATE_Facilitator_Details_BY_ID, facilitatorDetailsDTO);
			OUT.debug("Update FacilitatorDetails id:{} ", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;

	}

	public boolean isIdExist(int facilitatorId)
	{
		try
		{
			int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_SESSION_COUNT_FOR_DELETE, facilitatorId);
			OUT.debug("count value:{}", count);
			if (count > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return false;
	}

	public boolean deleteFacilitatorDetailsById(int id)
	{
		try
		{
			MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_Facilitator_Details_BY_ID, id);
			return true;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return false;
		}
	}

	public FacilitatorDetailsDTO getFacilitatorDetailsByUserId(UserDetailDTO userDetailDTO) throws Exception
	{
		FacilitatorDetailsDTO dto = null;
		dto = (FacilitatorDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_FACILITATOR_DETAIL_BY_USERID,
				userDetailDTO.getId());
		return dto;
	}
	
	//Start Sasedeve Edited By Mrutyunjaya on Date 15-09-2017
	
	public FacilitatorDetailsDTO getFacilitatorDetailsByUserIdTypeInt(int userid) throws Exception
	{
		FacilitatorDetailsDTO dto = null;
		dto = (FacilitatorDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_FACILITATOR_DETAIL_BY_USERID,
				userid);
		return dto;
	}
	//END Sasedeve Edited By Mrutyunjaya on Date 15-09-2017
	public FacilitatorDetailsDTO getFacilitatorDetailsByEmailId(FacilitatorDetailsDTO facilitatorDetailsDTO)
	{
		FacilitatorDetailsDTO dto = null;
		try
		{
			dto = (FacilitatorDetailsDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_Facilitator_Details_BY_EMAIL_ID,
					facilitatorDetailsDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}

	public List<FacilitatorDetailsDTO> getActiveFacilitatorList() throws Exception
	{
		List<FacilitatorDetailsDTO> activeFacilitatorList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ACTIVE_FACILITATOR,
				new FacilitatorDetailsDTO());
		return activeFacilitatorList;

	}
	
	public FacilitatorDetailsDTO getFacilitatorByStudentId(int id) throws Exception
	{
		FacilitatorDetailsDTO result = (FacilitatorDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_FACILITATOR_BY_STUDENT_ID, id);
		return result;
	}

	public List<LanguagesDTO> getAllLanguages() throws Exception
	{
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_LANGUAGES, null);
	}

	public int insertFacilitatorCityDetails(SqlSession session, FacilitatorCityMapDTO facilitatorCityMapDTO) {
		
		int insertId = 0;
		try
		{
			insertId = session.insert(MyBatisMappingConstants.INSERT_FacilitatorCity_Map_Details, facilitatorCityMapDTO);
			OUT.debug("Insert FacilitatorCityDetails id: {}", insertId);

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return insertId;
		
		
	}

	public List<FacilitatorCityMapDTO> getActiveFacilitatorcityList() throws Exception {
		List<FacilitatorCityMapDTO> FacilitatorCityList = new ArrayList<FacilitatorCityMapDTO>();
		FacilitatorCityList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_FACILITATOR_CITY, new FacilitatorCityMapDTO());
		return FacilitatorCityList;
	}

	public List<FacilitatorDetailsDTO> getAllfaceToFaceCityId(int id) throws Exception {
		// TODO Auto-generated method stub
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_FACETOFACEID_FACILITATOR_ID, id);
	}

	public List<FacilitatorDetailsDTO> getAllonCallCityId(int id) throws Exception {
		// TODO Auto-generated method stub
		 return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_ONCALL_FACILITATOR_ID, id);
	}

	public int updateFacilitatorCityDetails(SqlSession session, FacilitatorCityMapDTO facilitatorCityMapDTO) {
		int id = 0;
		try
		{
			id = session.update(MyBatisMappingConstants.UPDATE_FacilitatorCity_Map_Details_BY_ID, facilitatorCityMapDTO);
			OUT.debug("Update facilitatorCityMap id:{} ", id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return id;
		
	}
	//================start Sasedeve edited by vyankatesh on =================================6-5-2017
	public List<FacilitatorDetailsDTO> getFacilatormapping(int id) throws Exception {
		// TODO Auto-generated method stub
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_FACILITAOR_MAPPING_ID, id);
	}
	//================End Sasedeve edited by vyankatesh on =================================6-5-2017
	
	
	
//	started by bharath on 13-11-2019
	public List<FacilitatorStatsReportDTO> getFacilitatorStatsReport(FacilitatorStatsReportDTO facilitatorStatsReportDTO)
	{
		try
		{
			OUT.debug("FacilitatorStatsReportDTO: Getting all Facilitator stats");
			List<FacilitatorStatsReportDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_FACILITATOR_STATS_REPORT, facilitatorStatsReportDTO);
			OUT.debug("FacilitatorDetailsDAO: Number of Facilitator Details found: {}", resultList != null ? resultList.size() : 0);
			return resultList;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

//	end by bharath on 13-11-2019
	
	
}
