package com.lodestar.edupath.APIS.client.hobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class HobbyService {
	
	private static Logger	OUT	= LoggerFactory.getLogger(HobbyService.class); 
	
	public Map<String, List<OccupationDTO>> getSearchResult(String selectedAnswer) throws JSONException, Exception
	{
		OUT.debug("bharath inside ExploreMoreOccupation getSearchResult selectedAnswer:{}",selectedAnswer);
		Map<String, List<OccupationDTO>> searchResult = null;
		List<OccupationDTO> occList = null;
//		JSONObject jsonObject = new JSONObject(selectedAnswer);
		if (null != selectedAnswer)
		{
			
			occList = getOccList(selectedAnswer );
			OUT.debug("bharath inside ExploreMoreOccupation getSearchResult occList:{}",occList);
			if (null != occList && !occList.isEmpty())
			{
				searchResult = createSearchMap(occList);
				OUT.debug("bharath inside ExploreMoreOccupation getSearchResult searchResult:{}",searchResult);
			}
			
//			JSONArray jsonArray = jsonObject.getJSONArray("selectedAnswer");
//			JSONObject eachTransferData = null;
//			for (int i = 0; i < jsonArray.length(); i++)
//			{
//				eachTransferData = jsonArray.getJSONObject(i);
//				OUT.debug("bharath inside ExploreMoreOccupation getSearchResult eachTransferData:{}",eachTransferData);
//				if (eachTransferData.has("slNo") && eachTransferData.has("correctAnswer"))
//				{
//					occList = getOccList(eachTransferData.getInt("slNo"), eachTransferData.getString("correctAnswer"),studentId);
//					OUT.debug("bharath inside ExploreMoreOccupation getSearchResult occList:{}",occList);
//					if (null != occList && !occList.isEmpty())
//					{
//						searchResult = createSearchMap(occList);
//						OUT.debug("bharath inside ExploreMoreOccupation getSearchResult searchResult:{}",searchResult);
//					}
//				}
//			}
		}
		return searchResult;
	}

	
	private Map<String, List<OccupationDTO>> createSearchMap(List<OccupationDTO> occList)
	{
		
		OUT.debug("bharath inside ExploreMoreOccupation createSearchMap occList:{}",occList);
		Map<String, List<OccupationDTO>> searchResult = new HashMap<String, List<OccupationDTO>>();
		if (null != occList && !occList.isEmpty())
		{
			List<OccupationDTO> finalOccList = null;
			String industryName = null;
			for (OccupationDTO occupationDTO : occList)
			{
				OUT.debug("bharath inside ExploreMoreOccupation createSearchMap occupationDTO:{}",occupationDTO);
				for (IndustryDTO industry : occupationDTO.getIndustries())
				{
					if (industry.isPrimary())
					{
						industryName = industry.getName();
						break;
					}
				}

				if (searchResult.containsKey(industryName))
				{
					finalOccList = searchResult.get(industryName);
				}
				else
				{
					finalOccList = new ArrayList<OccupationDTO>();
					searchResult.put(industryName, finalOccList);
				}
				OUT.debug("bharath inside ExploreMoreOccupation createSearchMap occupationDTO:{}",occupationDTO);
				finalOccList.add(occupationDTO);
			}
		}
		OUT.debug("bharath inside ExploreMoreOccupation createSearchMap searchResult:{}",searchResult);
		return searchResult;
	}
	
	private List<OccupationDTO> getOccList(String stringAns) throws Exception
	{
		List<OccupationDTO> occList = null;
		List<String> ansList = null;
		String queryName = null;
		OUT.debug("bharath inside ExploreMoreOccupation getOccList stringAns:{}",stringAns);
		ansList = CommonUtil.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(stringAns));
		if (isvalidAspirationSearch(ansList))
		{
			queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_ASPIRATIONS;
			OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_ASPIRATIONS ansList:{}, queryName:{}",ansList,queryName);
		}
//		switch (slNo)
//		{
//			case 1:
//				ansList = CommonUtil.convertCommaSeperatedToList(stringAns);
//				if (null != ansList && !ansList.isEmpty())
//				{
//					queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_SUBJECTS;
//					OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_SUBJECTS ansList:{}, queryName:{}",ansList,queryName);
//				}
//				break;
//
//			case 2:
//				ansList = CommonUtil.convertCommaSeperatedToList(stringAns);
//				if (null != ansList && !ansList.isEmpty())
//				{
//					queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_INTERESTS_BY_AREA_ID;
//					OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_INTERESTS_BY_AREA_ID ansList:{}, queryName:{}",ansList,queryName);
//				}
//				break;
//
//			case 3:
//				ansList = CommonUtil.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(stringAns));
//				if (isvalidAspirationSearch(ansList))
//				{
//					queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_ASPIRATIONS;
//					OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_ASPIRATIONS ansList:{}, queryName:{}",ansList,queryName);
//				}
//				break;
//		}
		if (null != queryName && !queryName.isEmpty())
		{
			occList = MyBatisManager.getInstance().getResultList(queryName, ansList);
			OUT.debug("bharath inside ExploreMoreOccupation getOccList  occList:{}",occList);
		}
		
		return occList;
	}
	
	private boolean isvalidAspirationSearch(List<String> ansList)
	{
		boolean isValid = false;
		Iterator<String> iterator = ansList.iterator();
		while (iterator.hasNext())
		{
			String next = iterator.next();
			if (next.length() < 4)
			{
				iterator.remove();
			}
		}
		if (null != ansList && !ansList.isEmpty())
		{
			isValid = true;
		}
		return isValid;
	}
}
