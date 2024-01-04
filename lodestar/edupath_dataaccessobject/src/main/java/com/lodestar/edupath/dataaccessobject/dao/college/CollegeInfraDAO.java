package com.lodestar.edupath.dataaccessobject.dao.college;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeInfraDTO;

public class CollegeInfraDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(CollegeInfraDAO.class);

	public List<CollegeInfraDTO> getAllCollegeInfra() throws Exception
	{
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_INFRA_GET_ALL, null);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getDistinctInfra() throws Exception
	{
		OUT.debug("Getting distinct Infa");
		List<String> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_DISTINCT_INFRA, null);
		OUT.debug("Number of records found: {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}
}
