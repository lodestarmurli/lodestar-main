package com.lodestar.edupath.exploremoreoccupation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.induocchoice.ExploreMoreOccupation;
import com.lodestar.edupath.dataaccessobject.dao.tags.AreaDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AreaDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class ExploreMoreOccupationService
{
	private static Logger	OUT	= LoggerFactory.getLogger(ExploreMoreOccupationService.class);

	public void getCategoryMap(Map<String, Map<String, List<AreaDTO>>> categoryMap)
	{
		OUT.debug("In ExploreMoreOccupationService getCategoryMap ");
		List<AreaDTO> areaList = new ArrayList<AreaDTO>();
		try
		{
			AreaDAO areaDAO = new AreaDAO();
			areaList = areaDAO.getListByCategoryName("Interests");
			createCategoryMap(categoryMap, areaList);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

	private void createCategoryMap(Map<String, Map<String, List<AreaDTO>>> categoryMap, List<AreaDTO> areaList)
	{
		Map<String, List<AreaDTO>> subCategoryCheckMap = null;
		List<AreaDTO> areaCheckList = null;
		if (null != areaList && !areaList.isEmpty())
		{
			for (AreaDTO areaDTO : areaList)
			{
				if (categoryMap.containsKey(areaDTO.getCategoryDTO().getName()))
				{
					subCategoryCheckMap = categoryMap.get(areaDTO.getCategoryDTO().getName());
				}
				else
				{
					subCategoryCheckMap = new HashMap<String, List<AreaDTO>>();
					categoryMap.put(CommonUtil.replaceXMLEntities(areaDTO.getCategoryDTO().getName()), subCategoryCheckMap);
				}
				if (null != subCategoryCheckMap && subCategoryCheckMap.containsKey(areaDTO.getSubCategoryDTO().getName()))
				{
					areaCheckList = subCategoryCheckMap.get(areaDTO.getSubCategoryDTO().getName());
				}
				else
				{
					areaCheckList = new ArrayList<AreaDTO>();
					subCategoryCheckMap.put(CommonUtil.replaceXMLEntities(areaDTO.getSubCategoryDTO().getName()), areaCheckList);
				}
				areaCheckList.add(areaDTO);
			}
		}
	}

	public Map<String, List<OccupationDTO>> getOccupationSearch(String selectedAnswer,int studentId)
	{
		Map<String, List<OccupationDTO>> searchResult = null;
		
		try
		{
			OUT.debug("inside ExploreMoreOccupationService getOccupationSearch selectedAnswer:{}",selectedAnswer);
			//START SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
			searchResult = new ExploreMoreOccupation().getSearchResult(selectedAnswer,studentId);
			OUT.debug("inside ExploreMoreOccupationService getOccupationSearch searchResult:{}",searchResult);
			//END SASEDEVE Edited by Mrutyunjaya on date 15-05-2017
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION,e);
		}
		return searchResult;
	}
}
