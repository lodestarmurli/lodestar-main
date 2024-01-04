package com.lodestar.edupath.induoccchoice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendationV2;
import com.lodestar.edupath.datatransferobject.dto.occupation.IndustryDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.dto.tags.AlertsDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class SystemRecommendationService
{

	@SuppressWarnings("unchecked")
	public Map<String, Object> getSystemRecommendation(int studentId) throws Exception
	{
		Map<String, Object> systemRecommendation = new SystemRecommendationV2().getSystemRecommendation(studentId, false);

		List<Integer> occupationIds = new ArrayList<Integer>();
		List<OccupationDTO> cgtRecomOcc = (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.CGT.name());
		Map<String, List<OccupationDTO>> cgtRecomOccMap = new LinkedHashMap<String, List<OccupationDTO>>();
		String indusName = null;
		if (cgtRecomOcc != null)
		{
			for (OccupationDTO occupationDTO : cgtRecomOcc)
			{
				occupationIds.add(occupationDTO.getId());
				for (IndustryDTO industry : occupationDTO.getIndustries())
				{
					if (industry.isPrimary())
					{
						indusName = industry.getName();
						break;
					}
				}

				List<OccupationDTO> list;
				if (cgtRecomOccMap.containsKey(indusName))
				{
					list = cgtRecomOccMap.get(indusName);
				}
				else
				{
					list = new ArrayList<OccupationDTO>();
				}

				list.add(occupationDTO);
				cgtRecomOccMap.put(indusName, list);
			}
		}
		systemRecommendation.put("CGT_MAP", cgtRecomOccMap);
		List<OccupationDTO> subjectOcc = (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.SUBJECTS.name());
		if (subjectOcc != null)
		{
			for (OccupationDTO occupationDTO : subjectOcc)
			{
				occupationIds.add(occupationDTO.getId());
			}
		}

		List<OccupationDTO> strengthOcc = (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.STRENGTHS.name());
		if (strengthOcc != null)
		{
			for (OccupationDTO occupationDTO : strengthOcc)
			{
				occupationIds.add(occupationDTO.getId());
			}
		}

		List<OccupationDTO> interesOcc = (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.INTERESTS.name());
		if (interesOcc != null)
		{
			for (OccupationDTO occupationDTO : interesOcc)
			{
				occupationIds.add(occupationDTO.getId());
			}
		}

		List<OccupationDTO> aspiOcc = (List<OccupationDTO>) systemRecommendation.get(ApplicationConstants.SystemRecommendation.ASPIRATIONS.name());
		if (aspiOcc != null)
		{
			for (OccupationDTO occupationDTO : aspiOcc)
			{
				occupationIds.add(occupationDTO.getId());
			}
		}

		if (!occupationIds.isEmpty())
		{
			systemRecommendation.put("alerts", getAlertsByOccupation(occupationIds));
		}
		return systemRecommendation;
	}

	public Map<Long, List<AlertsDTO>> getAlertsByOccupation(List<Integer> occupationIds) throws Exception
	{
		List<AlertsDTO> alerts = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_ALERTS, occupationIds);
		Map<Long, List<AlertsDTO>> occupationAlerts = new HashMap<Long, List<AlertsDTO>>();
		List<AlertsDTO> list;
		for (AlertsDTO alertsDTO : alerts)
		{
			if (occupationAlerts.containsKey((long) alertsDTO.getOccupationId()))
			{
				list = occupationAlerts.get((long) alertsDTO.getOccupationId());
			}
			else
			{
				list = new ArrayList<>();
			}

			list.add(alertsDTO);
			occupationAlerts.put((long) alertsDTO.getOccupationId(), list);
		}
		return occupationAlerts;
	}

	public static void main(String[] args) throws Exception
	{
		// ManualSearchDTO search = new ManualSearchDTO();
		// Map<Integer, String> searchQuestions = new HashMap<Integer, String>();
		// searchQuestions.put(1, "maths,chemistry");
		// // searchQuestions.put(5, "1:1,2,3&&7:4,5,6");
		// searchQuestions.put(12, "OFFICE");
		// // search.setMandatoryQuestions("5,3");
		// search.setSearchQuestions(searchQuestions);
		// Map<String, List<OccupationDTO>> manualSearchResult = new ManualSearch().getManualSearchResult(search);
		// System.out.println(manualSearchResult);

		SystemRecommendationV2 systemRecommendation;

		systemRecommendation = new SystemRecommendationV2();
		systemRecommendation.getSystemRecommendation(2558, false);
		System.out.println(systemRecommendation.getResultMap());

		// systemRecommendation.getRecommendationBasedOnTUM(59);
		//
		// UserDetailDTO dto = new UserDetailDTO();
		// dto.setLoginId("LD12");
		// UserDetailDTO resultAsObject = (UserDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_USER_BY_LOGINID, dto);
		//
		// System.out.println(new String(AESCipher.decrypt(resultAsObject.getPassword())));

		// new SystemRecommendationService().getSystemRecommendation(1);
	}
}
