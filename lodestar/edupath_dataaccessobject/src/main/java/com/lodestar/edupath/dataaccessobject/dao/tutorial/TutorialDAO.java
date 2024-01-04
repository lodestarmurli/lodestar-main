package com.lodestar.edupath.dataaccessobject.dao.tutorial;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialRankDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialsDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorialsearch.TutorialSearchVO;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class TutorialDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(TutorialDAO.class);

	public List<TutorialsDTO> getAllTutorial() throws Exception
	{
		List<TutorialsDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIALS_GET_ALL, null);
		OUT.debug("Tutorials  founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<TutorialCombinationVO> getTutorialsDetailsById(TutorialsDTO tutorialsDTO) throws Exception
	{
		OUT.debug("Getting tutorial details by id : {}", tutorialsDTO.getId());
		List<TutorialCombinationVO> tutorialsList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIALS_GET_DETAILS_BY_ID, tutorialsDTO);
		OUT.debug("Number of tutorials found : {}", tutorialsList != null ? tutorialsList.size() : 0);
		return tutorialsList;
	}

	/**
	 * @param tutorialSearchFilter
	 * @param pageNumber
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public List<TutorialSearchVO> getTutorialsDetails(Map<Object, Object> tutorialSearchFilter, int pageNumber, int maxCount) throws Exception
	{
		OUT.debug("Getting tutorial result for filter: {}", tutorialSearchFilter);
		List<TutorialSearchVO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIALS_GET_BY_FILTER, tutorialSearchFilter,
				CommonUtil.getSkipResult(pageNumber, maxCount), maxCount);
		OUT.debug("Number of tutorial result found for filter: {}, size: {}", tutorialSearchFilter, resultList != null ? resultList.size() : 0);
		return resultList;
	}
	
	public List<TutorialSearchVO> getTutorialsDetailsByTutorialIds(Map<Object, Object> tutorialSearchFilter) throws Exception
	{
		OUT.debug("Getting tutorial result for filter: {}", tutorialSearchFilter);
		List<TutorialSearchVO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIALS_GET_BY_FILTER, tutorialSearchFilter);
		OUT.debug("Number of tutorial result found for filter: {}, size: {}", tutorialSearchFilter, resultList != null ? resultList.size() : 0);
		return resultList;
	}

	/**
	 * @param tutorialSearchFilter
	 * @return
	 * @throws Exception
	 */
	public int getTutorialsDetailsTotalSize(Map<Object, Object> tutorialSearchFilter) throws Exception
	{
		int totalSize = 0;
		OUT.debug("Getting tutorial result for filter: {}", tutorialSearchFilter);
		List<TutorialSearchVO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.TUTORIALS_GET_BY_FILTER, tutorialSearchFilter);
		OUT.debug("Number of tutorial result found for filter: {}, size: {}", tutorialSearchFilter, (totalSize = resultList != null ? resultList.size() : 0));
		return totalSize;
	}

	public List<TutorialsDTO> getAllTutorialDetails(TutorialsDTO tutorialsDTO) throws Exception
	{
		List<TutorialsDTO> list = null;
		MyBatisManager instance = MyBatisManager.getInstance();
		if (tutorialsDTO.getExamId() != 0)
		{
			int examId = tutorialsDTO.getExamId();
			list = instance.getResultList(MyBatisMappingConstants.TUTORIALS_GET_BY_EXAM_ID, examId);
		}
		else if (tutorialsDTO.getCityId() != 0)
		{
			list = instance.getResultList(MyBatisMappingConstants.TUTORIALS_GET_BY_CITY_ID, tutorialsDTO);
		}
		OUT.debug("Number of tutorial found : {}", list != null ? list.size() : 0);
		return list;
	}

	public int getHighestRankByTutorialId(int tutorialId, int examId) throws Exception
	{
		TutorialRankDTO tutorialRankDTO = new TutorialRankDTO();
		tutorialRankDTO.setTutorialId(tutorialId);
		tutorialRankDTO.setExamId(examId);
		TutorialRankDTO rankDTO = (TutorialRankDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.TUTORIAL_RANK_GET_BY_TUTORIAL_ID,
				tutorialRankDTO);
		return rankDTO.getClassroom();
	}

}
