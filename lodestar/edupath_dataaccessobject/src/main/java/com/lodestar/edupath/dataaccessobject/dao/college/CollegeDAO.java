package com.lodestar.edupath.dataaccessobject.dao.college;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeCombinationVO;
import com.lodestar.edupath.datatransferobject.dto.college.CollegeDTO;
import com.lodestar.edupath.datatransferobject.dto.collegedetails.CollegeDetailsVO;
import com.lodestar.edupath.datatransferobject.dto.collegesearch.CollegeSearchVO;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class CollegeDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(CollegeDAO.class);

	public List<CollegeDTO> getAllCollege() throws Exception
	{
		List<CollegeDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_ALL, null);
		OUT.debug("College  founded :{}", (list != null ? list.size() : 0));
		return list;
	}

	public List<CollegeCombinationVO> getCollegeDetailsById(int id) throws Exception
	{
		OUT.debug("Getting college details for Id : {}", id);
		List<CollegeCombinationVO> collegeList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_DETAILS_BY_ID, id);
		OUT.debug("Number of College details found {} for id : {}", collegeList != null ? collegeList.size() : 0, id);
		return collegeList;
	}

	/**
	 * @param collegeSearchFilter
	 * @param pageSize
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public List<CollegeSearchVO> getCollegeDetails(Map<Object, Object> collegeSearchFilter, int pageSize, int maxResults) throws Exception
	{
		OUT.debug("Getting college result for filter: {}", collegeSearchFilter);
		int skipResult = CommonUtil.getSkipResult(pageSize, maxResults);
		// Getting college ids grouping by college ids
		List<CollegeSearchVO> collegeIdsVOs = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_BY_FILTER, collegeSearchFilter,
				skipResult, maxResults);
		List<CollegeSearchVO> resultList = null;
		if (collegeIdsVOs != null)
		{
			List<Integer> collegeIds = new ArrayList<Integer>();
			for (CollegeSearchVO collegeSearchVO : collegeIdsVOs)
			{
				collegeIds.add(collegeSearchVO.getCollegeId());
			}
			collegeSearchFilter.put("collegeIds", collegeIds);
			collegeSearchFilter.put("getTotalCount", false);
			// based on college ids getting result because pagination wont work with left join
			resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_BY_FILTER, collegeSearchFilter);
		}
		OUT.debug("Number of college result found for filter: {}, size: {}", collegeSearchFilter, resultList != null ? resultList.size() : 0);
		return resultList;
	}

	/**
	 * @param collegeSearchFilter
	 * @return
	 * @throws Exception
	 */
	public int getCollegeDetailsTotalSize(Map<Object, Object> collegeSearchFilter) throws Exception
	{
		OUT.debug("Getting college result for filter: {}", collegeSearchFilter);
		List<CollegeSearchVO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_BY_FILTER, collegeSearchFilter);
		int totalSize = 0;
		OUT.debug("Number of college result found for filter: {}, size: {}", collegeSearchFilter, (totalSize = resultList != null ? resultList.size() : 0));
		return totalSize;
	}

	/**
	 * @param collegeSearchFilter
	 * @param pageSize
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public List<CollegeDetailsVO> getCollegeDetailsByCollegeIds(Map<Object, Object> collegeSearchFilter) throws Exception
	{
		OUT.debug("Getting college details result for filter: {}", collegeSearchFilter);
		List<CollegeDetailsVO> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_DETAILS_BY_IDS, collegeSearchFilter);
		OUT.debug("Number of college details result found for filter: {}, size: {}", collegeSearchFilter, resultList != null ? resultList.size() : 0);
		return resultList;
	}

	public List<CollegeDTO> getAllCollegeDetails(CollegeDTO collegeDTO) throws Exception
	{
		List<CollegeDTO> list = null;
		MyBatisManager instance = MyBatisManager.getInstance();
		if (collegeDTO.getStreamId() != 0)
		{
			int streamId = collegeDTO.getStreamId();
			list = instance.getResultList(MyBatisMappingConstants.COLLEGE_GET_DETAILS_BY_STREAM_ID, streamId);
		}
		else if (collegeDTO.getCombinationId() != 0)
		{
			int combinationId = collegeDTO.getCombinationId();
			list = instance.getResultList(MyBatisMappingConstants.COLLEGE_GET_DETAILS_BY_COMBINATION_ID, combinationId);
		}
		else if (collegeDTO.getAffiliatedToBoardId() != 0)
		{
			int affiliatedToBoardId = collegeDTO.getAffiliatedToBoardId();
			list = instance.getResultList(MyBatisMappingConstants.COLLEGE_GET_DETAILS_BY_BOARD_ID, affiliatedToBoardId);
		}
		else if (collegeDTO.getCityId() != 0)
		{
			list = instance.getResultList(MyBatisMappingConstants.COLLEGE_GET_DETAILS_BY_CITY_ID, collegeDTO);
		}
		OUT.debug("College details found :{}", (list != null ? list.size() : 0));
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<String> getCollegeZones() throws Exception
	{
		OUT.debug("Geting college zones");
		List<String> resultList = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.COLLEGE_GET_ZONES, null);
		OUT.debug("Number of zones found : {}", resultList != null ? resultList.size() : 0);
		return resultList;
	}
}
