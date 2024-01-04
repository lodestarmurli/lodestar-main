package com.lodestar.edupath.dataaccessobject.dao.tutorial;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersVO;

public class TutorialCityCentersDAO
{
	private static final Logger OUT = LoggerFactory.getLogger(TutorialCityCentersDAO.class);

	public List<TutorialCityCentersDTO> getAllTutorialCityCenters() throws Exception
	{
		List<TutorialCityCentersDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIAL_CITY_CENTERS_GET_ALL, null);
		OUT.debug("Tutorial City Centers  founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	/**
	 * @param tutorialCityCentersValue
	 * @return
	 * @throws Exception 
	 */
	public List<TutorialCityCentersVO> getTutorialCentersByTutExamId(Map<Object, Object> tutorialCityCentersValue) throws Exception
	{
		OUT.debug("Getting tutorial result for filter: {}", tutorialCityCentersValue);
		List<TutorialCityCentersVO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIAL_CITY_CENTERS_GET_ALL_BY_TUTORIAL_IDS, tutorialCityCentersValue);
		OUT.debug("Number of tutorial result found for filter: {}, size: {}", tutorialCityCentersValue, resultList != null ? resultList.size() : 0);
		return resultList;
	}
}
