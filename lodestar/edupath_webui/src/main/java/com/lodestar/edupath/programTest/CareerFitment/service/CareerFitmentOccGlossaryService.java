package com.lodestar.edupath.programTest.CareerFitment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.OccupationGlossaryDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.induoccchoice.service.SystemRecommendationService;

public class CareerFitmentOccGlossaryService {
	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentOccGlossaryService.class);

	public Map<String, Object> getOccupationGlossaryDetails(int pageNumber,int studentId) throws Exception
	{
		OUT.debug("CareerFitmentOccGlossaryService : getting occupation glossary and alerts details");
		Map<String, Object> finalMap = new HashMap<>();

		List<Integer> occIds = new ArrayList<>();
		Map<String, Map<String, List<OccupationDTO>>> occupGlossMap = new LinkedHashMap<String, Map<String, List<OccupationDTO>>>();

		List<OccupationDTO> occupationGlossaryDetailsByPageNumber = new OccupationGlossaryDAO().getOccupationGlossaryDetailsByPageNumber(pageNumber);

		Map<String, List<OccupationDTO>> map;
		List<OccupationDTO> list;
		//BEGIN Sasedeve Added by Mrutyunjaya on Date 04-05-2017
		
		CalculateFitmentColor calfit=new CalculateFitmentColor();
		
		
		//END Sasedeve Added by Mrutyunjaya on Date 04-05-2017
		
		for (OccupationDTO occupationDTO : occupationGlossaryDetailsByPageNumber)
		{
			
			if (occupGlossMap.containsKey(occupationDTO.getIndustryName()))
			{
				map = occupGlossMap.get(occupationDTO.getIndustryName());
			}
			else
			{
				map = new LinkedHashMap<String, List<OccupationDTO>>();
			}

			if (map.containsKey(occupationDTO.getPathWayName()))
			{
				list = map.get(occupationDTO.getPathWayName());
			}
			else
			{
				list = new ArrayList<>();
			}
			occIds.add(occupationDTO.getId());
			list.add(occupationDTO);

			map.put(occupationDTO.getPathWayName(), list);
			occupGlossMap.put(occupationDTO.getIndustryName(), map);
		}

		finalMap.put("occupation", occupGlossMap);
//		finalMap.put("alerts", new SystemRecommendationService().getAlertsByOccupation(occIds));
		OUT.debug("bharath CareerFitmentOccGlossaryService finalMap:{}",finalMap);

		return finalMap;
	}

	public Map<String, Object> getSearchCFOccupationGlossaryDetails(List<String> occlist) throws Exception
	{
		OUT.debug("CareerFitmentOccGlossaryService : getting occupation glossary occlist:{}",occlist);
		Map<String, Object> finalMap = new HashMap<>();

		List<Integer> occIds = new ArrayList<>();
		Map<String, Map<String, List<OccupationDTO>>> occupGlossMap = new LinkedHashMap<String, Map<String, List<OccupationDTO>>>();

		List<OccupationDTO> occupationGlossaryDetailsByPageNumber = new OccupationGlossaryDAO().getSearchCFOccupationGlossaryDetails(1,occlist);
		OUT.debug("bharath CareerFitmentOccGlossaryService occupationGlossaryDetailsByPageNumber:{}",occupationGlossaryDetailsByPageNumber);
		Map<String, List<OccupationDTO>> map;
		List<OccupationDTO> list;
		//BEGIN Sasedeve Added by Mrutyunjaya on Date 04-05-2017
		
		CalculateFitmentColor calfit=new CalculateFitmentColor();
		
		
		//END Sasedeve Added by Mrutyunjaya on Date 04-05-2017
		
		for (OccupationDTO occupationDTO : occupationGlossaryDetailsByPageNumber)
		{
			
			if (occupGlossMap.containsKey(occupationDTO.getIndustryName()))
			{
				map = occupGlossMap.get(occupationDTO.getIndustryName());
			}
			else
			{
				map = new LinkedHashMap<String, List<OccupationDTO>>();
			}

			if (map.containsKey(occupationDTO.getPathWayName()))
			{
				list = map.get(occupationDTO.getPathWayName());
			}
			else
			{
				list = new ArrayList<>();
			}
			occIds.add(occupationDTO.getId());
			list.add(occupationDTO);

			map.put(occupationDTO.getPathWayName(), list);
			occupGlossMap.put(occupationDTO.getIndustryName(), map);
		}

		finalMap.put("occupation", occupGlossMap);
//		finalMap.put("alerts", new SystemRecommendationService().getAlertsByOccupation(occIds));
		OUT.debug("bharath CareerFitmentOccGlossaryService finalMap:{}",finalMap);

		return finalMap;
	}

	
	public int getOccupCount()
	{
		int count = 0;
		try
		{
			count = new OccupationGlossaryDAO().getOccupationCount();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return count;
	}

}
