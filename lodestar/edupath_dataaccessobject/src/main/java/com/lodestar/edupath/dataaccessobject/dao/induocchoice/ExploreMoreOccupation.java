package com.lodestar.edupath.dataaccessobject.dao.induocchoice;

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


public class ExploreMoreOccupation
{
	private static Logger	OUT	= LoggerFactory.getLogger(ExploreMoreOccupation.class); 

	public Map<String, List<OccupationDTO>> getSearchResult(String selectedAnswer,int studentId) throws JSONException, Exception
	{
		OUT.debug("bharath inside ExploreMoreOccupation getSearchResult selectedAnswer:{}",selectedAnswer);
		Map<String, List<OccupationDTO>> searchResult = null;
		List<OccupationDTO> occList = null;
		JSONObject jsonObject = new JSONObject(selectedAnswer);
		if (null != jsonObject && jsonObject.has("selectedAnswer"))
		{
			JSONArray jsonArray = jsonObject.getJSONArray("selectedAnswer");
			JSONObject eachTransferData = null;
			for (int i = 0; i < jsonArray.length(); i++)
			{
				eachTransferData = jsonArray.getJSONObject(i);
				OUT.debug("bharath inside ExploreMoreOccupation getSearchResult eachTransferData:{}",eachTransferData);
				if (eachTransferData.has("slNo") && eachTransferData.has("correctAnswer"))
				{
					//START SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
					occList = getOccList(eachTransferData.getInt("slNo"), eachTransferData.getString("correctAnswer"),studentId);
					OUT.debug("bharath inside ExploreMoreOccupation getSearchResult occList:{}",occList);
					//END SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
					if (null != occList && !occList.isEmpty())
					{
						searchResult = createSearchMap(occList);
						OUT.debug("bharath inside ExploreMoreOccupation getSearchResult searchResult:{}",searchResult);
					}
				}
			}
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

	private List<OccupationDTO> getOccList(int slNo, String stringAns,int studentId) throws Exception
	{
		List<OccupationDTO> occList = null;
		List<String> ansList = null;
		String queryName = null;
		OUT.debug("bharath inside ExploreMoreOccupation getOccList slNo:{}, stringAns:{}",slNo,stringAns);
		switch (slNo)
		{
			case 1:
				ansList = CommonUtil.convertCommaSeperatedToList(stringAns);
				if (null != ansList && !ansList.isEmpty())
				{
					queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_SUBJECTS;
					OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_SUBJECTS ansList:{}, queryName:{}",ansList,queryName);
				}
				break;

			case 2:
				ansList = CommonUtil.convertCommaSeperatedToList(stringAns);
				if (null != ansList && !ansList.isEmpty())
				{
					queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_INTERESTS_BY_AREA_ID;
					OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_INTERESTS_BY_AREA_ID ansList:{}, queryName:{}",ansList,queryName);
				}
				break;

			case 3:
				ansList = CommonUtil.convertSpaceNOthersSeperatedToList(CommonUtil.replaceDBQuotes(stringAns));
				if (isvalidAspirationSearch(ansList))
				{
					queryName = MyBatisMappingConstants.GET_OCCUPATION_BY_ASPIRATIONS;
					OUT.debug("bharath inside ExploreMoreOccupation getOccList GET_OCCUPATION_BY_ASPIRATIONS ansList:{}, queryName:{}",ansList,queryName);
				}
				break;
		}
		if (null != queryName && !queryName.isEmpty())
		{
			occList = MyBatisManager.getInstance().getResultList(queryName, ansList);
			OUT.debug("bharath inside ExploreMoreOccupation getOccList  occList:{}",occList);
			
			//START SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
			
			
			
			//modified start by bharath for Hobby API on date 25-09-2021
			if(studentId != 0)
			{
				CalculateFitmentColor calfit=new CalculateFitmentColor();
				occList=calfit.GetFitementForManualSearch(occList,studentId);
			}
			//modified end by bharath for Hobby API on date 25-09-2021
			
			
			
			//END SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
			
		}
		else
		{
			OUT.warn("There is no  correct answer to search for question no: {}", slNo);
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
