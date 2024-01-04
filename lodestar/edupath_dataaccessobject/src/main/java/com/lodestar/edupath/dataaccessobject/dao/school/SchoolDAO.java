package com.lodestar.edupath.dataaccessobject.dao.school;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.schoolreport.SchoolReportDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class SchoolDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(SchoolDAO.class);

	public List<SchoolDTO> getAllSchoolList() throws Exception
	{
		List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
		schoolList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_SCHOOL_NAME_WITH_ID, new SchoolDTO());
		return schoolList;
	}
	
	public SchoolDTO getSchoolById(int id) throws Exception
	{
		SchoolDTO schooldto = (SchoolDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_SCHOOL_BY_ID, id);
		return schooldto;
	}

	public boolean isExist(SchoolDTO schoolDTO) throws Exception
	{
		int count = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.IS_SCHOOL_EXIST, schoolDTO);
		if (count > 0)
		{
			return true;
		}
		return false;
	}

	public int insert(SqlSession session, SchoolDTO schoolDTO)
	{
		int insertId = 0;
		
		try
		{
			insertId =session.insert(MyBatisMappingConstants.INSERT_SCHOOL_DETAILS, schoolDTO);
			OUT.debug("Insert School id: {}", insertId);

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return schoolDTO.getId();
	}
	
	public void update(SqlSession session, SchoolDTO schoolDTO)
	{
		session.update(MyBatisMappingConstants.UPDATE_SCHOOL_DETAILS, schoolDTO);
	}
	
	public void updateWOPath(SqlSession session, SchoolDTO schoolDTO)
	{
		session.update(MyBatisMappingConstants.UPDATE_SCHOOL_DETAILS_WO_PATH, schoolDTO);
	}

	public void delete(SqlSession session, int id)
	{
		OUT.debug("Deleteing School details for id : {}", id);
		session.delete(MyBatisMappingConstants.DELETE_SCHOOL_DETAILS, id);
	}

	public List<SchoolDTO> getAllSchool() throws Exception
	{
		List<SchoolDTO> resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_SCHOOL, new SchoolDTO());
		OUT.debug("Total number of school details found : {}", resultAsList != null ? resultAsList.size() : 0);
		return resultAsList;
	}

	public List<CityDTO> getCityNameWithId() {
		// TODO Auto-generated method stub
		List<CityDTO> resultList = null;
		try
		{
			OUT.debug("SchoolsDAO: Getting all City Details");
			resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_CITY_NAME_WITH_ID, null);
			OUT.debug("SchoolsDAO: Number of City Details found: {}", resultList != null ? resultList.size() : 0);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return resultList;
	}

	public int insertSchoolMapping(SqlSession session, SchoolDTO schoolDTO) {
		int insertId = 0;
		
		try
		{
			insertId =session.insert(MyBatisMappingConstants.INSERT_SCHOOLMAPING_CITY, schoolDTO);
			OUT.debug("Insert SchoolMapping id: {}", insertId);

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return schoolDTO.getId();
		
	}
	
	public List<SchoolReportDTO> getReportBySchoolId(SchoolReportDTO schoolReportdto) throws Exception
	{
		List<SchoolReportDTO> schoolReport = new ArrayList<SchoolReportDTO>();
		schoolReport = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_SCHOOL_REPORT_BY_SCHOOL_ID,schoolReportdto);
		return schoolReport;
	}
	
	// STARTED BY BHARATH ON 20-03-2020
	public static SchoolDTO getSchoolByName(SchoolDTO schoolDTO) throws Exception {
		SchoolDTO schoolDTOResult = null;
		try {

			schoolDTOResult = (SchoolDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_SCHOOL_BY_NAME, schoolDTO);

		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return schoolDTOResult;
	}
	// END BY BHARATH ON 20-03-2020

}
