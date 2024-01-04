package com.lodestar.edupath.dataaccessobject.dao.college;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeActivitiesDTO;

public class CollegeActivitiesDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(CollegeActivitiesDAO.class);

	public List<CollegeActivitiesDTO> getAllCollegeActivities() throws Exception
	{
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_ACTIVITIES_GET_ALL, null);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getDistinctActivities() throws Exception
	{
		OUT.debug("Getting distinct activities");
		List<String> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_DISTINCT_ACTIVITIES, null);
		OUT.debug("Number of records found: {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}
}
