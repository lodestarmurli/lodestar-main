package com.lodestar.edupath.tutorialsearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialCityCentersDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.EntranceExamsDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorialsearch.TutorialSearchVO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class TutorialSearchService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(TutorialSearchService.class);

	public List<EntranceExamsDTO> getExamsByStudentId(int studentId) throws Exception
	{
		return new EntranceExamsDAO().getShortListExamByStudentId(studentId);
	}

	public List<CityDTO> getAllCity() throws Exception
	{
		return new CityDAO().getAllCityList();
	}

	public List<TutorialSearchVO> getTutorialsByFilter(Map<Object, Object> convertValue, int pageNumber, int pageSizeCount) throws Exception
	{

		return new TutorialDAO().getTutorialsDetails(convertValue, pageNumber, pageSizeCount);
	}

	public int getTutorialCountByFilter(Map<Object, Object> convertValue) throws Exception
	{
		return new TutorialDAO().getTutorialsDetailsTotalSize(convertValue);
	}

	public int getTutorialPageCount() throws Exception
	{
		int pazeSize = 0;
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.TUTORIAL_PAGE_SIZE.getProperty());
		GlobalSettingDTO globalSettingDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
		pazeSize = (null != globalSettingDTO.getPropertyValue() ? Integer.parseInt(globalSettingDTO.getPropertyValue()) : -1);
		return pazeSize;
	}

	public Map<Integer, List<String>> getCityLocalityMap(List<CityDTO> cityList) throws Exception
	{
		Map<Integer, List<String>> cityLocalityMap = new HashMap<Integer, List<String>>();
		List<TutorialCityCentersDTO> list = new TutorialCityCentersDAO().getAllTutorialCityCenters();
		if (list != null && !list.isEmpty())
		{
			List<String> localityList;
			for (CityDTO cityDTO : cityList)
			{
				localityList = new ArrayList<String>();
				for (TutorialCityCentersDTO tutorialCityCentersDTO : list)
				{
					if (cityDTO.getId() == tutorialCityCentersDTO.getCityId())
					{
						localityList.add(tutorialCityCentersDTO.getLocality());
					}
				}
				if (localityList != null && !localityList.isEmpty())
				{
					cityLocalityMap.put(cityDTO.getId(), localityList);
				}
			}
		}
		return cityLocalityMap;
	}

	public List<TutorialSearchVO> getTutorialCityCentersByTutorialAndExamId(Map<Object, Object> tutorialCityCentersValue) throws Exception
	{
		return new TutorialDAO().getTutorialsDetailsByTutorialIds(tutorialCityCentersValue);
	}

	public int getTutorialCentreSelectCount() throws Exception
	{
		int pazeSize = 0;
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.TUTORIAL_CENTRE_MAX_SELECT.getProperty());
		GlobalSettingDTO globalSettingDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
		pazeSize = (null != globalSettingDTO.getPropertyValue() ? Integer.parseInt(globalSettingDTO.getPropertyValue()) : -1);
		return pazeSize;
	}

}
