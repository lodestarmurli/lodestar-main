package com.lodestar.edupath.programTest.CareerFitment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.calculatefitmentcolor.CalculateFitmentColor;
import com.lodestar.edupath.dataaccessobject.dao.careerFitment.CareerFitmentDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.OccupationGlossaryDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.careerFitment.ClusterDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.induoccchoice.service.SystemRecommendationService;

public class CareerFitmentClusterOccGlossayService {
	
	private static final Logger	OUT	= LoggerFactory.getLogger(CareerFitmentClusterOccGlossayService.class);
	
	
	public List<ClusterDTO> getClusterList()
	{
		List<ClusterDTO> clusterList = new ArrayList<ClusterDTO>();
		try {
			clusterList = new CareerFitmentDAO().getClusterList();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return clusterList;
	}
	
	public Map<String, Object> getOccupationGlossaryDetails(int pageNumber,int studentId) throws Exception
	{
		OUT.debug("OccupationGlossaryService : getting occupation glossary and alerts details");
		Map<String, Object> finalMap = new HashMap<>();

		List<Integer> occIds = new ArrayList<>();
		Map<String, Map<String, List<OccupationDTO>>> occupGlossMap = new LinkedHashMap<String, Map<String, List<OccupationDTO>>>();

		List<OccupationDTO> occupationGlossaryDetailsByPageNumber = new OccupationGlossaryDAO().getOccupationGlossaryForClusterOcc(pageNumber);

		Map<String, List<OccupationDTO>> map;
		List<OccupationDTO> list;
		 
		CalculateFitmentColor calfit=new CalculateFitmentColor();
		
		
		
		for (OccupationDTO occupationDTO : occupationGlossaryDetailsByPageNumber)
		{
			
//			occupationDTO=calfit.getOccupationFitmentForGlossary(occupationDTO, studentId);
			
			
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
		finalMap.put("alerts", new SystemRecommendationService().getAlertsByOccupation(occIds));
		OUT.debug("bharath OccupationGlossaryService finalMap:{}",finalMap);

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
