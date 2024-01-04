package com.lodestar.edupath.induoccchoice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.tags.AreaDAO;
import com.lodestar.edupath.datatransferobject.dto.EducationLevelDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AreaDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;

public class ManualSearchService
{
	private static Logger	OUT	= LoggerFactory.getLogger(ManualSearchService.class);

	public List<EducationLevelDTO> getQualification()
	{
		List<EducationLevelDTO> finalList = null;
		try
		{
			finalList = new FacilitatorDetailsDAO().getEducationLevelNameWithId();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;
	}

	public void getCategoryMap(Map<String, Map<String, List<AreaDTO>>> categoryMap)
	{
		List<AreaDTO> areaList = new ArrayList<AreaDTO>();
		try
		{
			AreaDAO areaDAO = new AreaDAO();
			areaList = areaDAO.getListByCategoryName(CategoryTypeEnum.SKILLS.getCategoryName());
			createCategoryMap(categoryMap, areaList);
			areaList = areaDAO.getListByCategoryName(CategoryTypeEnum.INTERESTS.getCategoryName());
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

	public Map<Long, List<AlertsDTO>> getAlertDetails(Map<String, List<OccupationDTO>> manualSearchMap)
	{
		Map<Long, List<AlertsDTO>> alertMap = null;
		List<OccupationDTO> finalList = null;
		List<Integer> occIds = new ArrayList<>();
		if (null != manualSearchMap && !manualSearchMap.isEmpty())
		{
			finalList = new ArrayList<OccupationDTO>();
			for (Entry<String, List<OccupationDTO>> entry : manualSearchMap.entrySet())
			{
				if (null != entry.getValue() && !entry.getValue().isEmpty())
				{
					finalList.addAll(entry.getValue());
				}
			}
			if (null != finalList && !finalList.isEmpty())
			{
				for (OccupationDTO dto : finalList)
				{
					occIds.add(dto.getId());
				}
			}
			if (!occIds.isEmpty())
			{
				try
				{
					alertMap = new SystemRecommendationService().getAlertsByOccupation(occIds);
				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
			}
		}

		return alertMap;
	}
}

enum CategoryTypeEnum
{
	SKILLS("Skills"), INTERESTS("Interests");
	private String	categoryName;

	private CategoryTypeEnum(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public String getCategoryName()
	{
		return categoryName;
	}
}
