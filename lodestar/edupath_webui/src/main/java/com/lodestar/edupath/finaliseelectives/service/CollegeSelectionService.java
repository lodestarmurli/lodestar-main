package com.lodestar.edupath.finaliseelectives.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.college.CollegeDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeparameter.CollegeParametersDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeparameter.StudentCollegeParametersDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathElectiveShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentCityLockDTO;
import com.lodestar.edupath.datatransferobject.dto.collegeparameter.CollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.dto.elective.EduPathElectiveShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.studentparameter.StudentCollegeParametersDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.finaliseelectives.service.bean.CollegeSearchFilterVO;

public class CollegeSelectionService
{
	private static final String	COLLEGE_RECORDS		= "COLLEGE_RECORDS";
	private static final String	TOTAL_SIZE			= "TOTAL_SIZE";
	private static final String	UN_SELECTED_PARAMS	= "UN_SELECTED_PARAMS";
	private static final String	SELECTED_PARAMS		= "SELECTED_PARAMS";
	private static final Logger	OUT					= LoggerFactory.getLogger(CollegeSelectionService.class);

	public Map<String, Object> getCollegeParameterAndElective(int studentId)
	{
		Map<String, Object> finalMap = new HashMap<String, Object>();
		try
		{
			OUT.debug("CollegeSelectionService student id: {}", studentId);
			List<EduPathElectiveShortListDTO> list = new EduPathElectiveShortListDAO().getEduPathElectiveShortListByStudentId(studentId);

			if (null != list && !list.isEmpty())
			{
				SortedMap<Integer, String> map = new TreeMap<Integer, String>();
				int order = 1;
				for (EduPathElectiveShortListDTO eduPathElectiveShortListDTO : list)
				{
					map.put(order + eduPathElectiveShortListDTO.getOrderNo(), eduPathElectiveShortListDTO.getName());
					order++;
				}
				finalMap.put("electiveMap", map);
			}
			else
			{
				finalMap.put("electiveMap", new TreeMap<Integer, String>());
			}

			setCollegeParameters(studentId, finalMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return finalMap;
	}

	/**
	 * @param studentId
	 * @param finalMap
	 * @throws Exception
	 */
	private void setCollegeParameters(int studentId, Map<String, Object> finalMap) throws Exception
	{
		StudentCollegeParametersDTO studentPDTO = new StudentCollegeParametersDAO().getStudentCollegeParametersByStudentId(studentId);
		if (null != studentPDTO)
		{
			if (null != studentPDTO.getAddress() && !studentPDTO.getAddress().isEmpty())
			{
				finalMap.put("venueAddress", studentPDTO.getAddress());
			}
			else
			{
				finalMap.put("venueAddress", "-");
			}
		}
		//====Start Sasedeve edited by Mrutyunjaya on date 17-04-2017
		Map<String, List<CollegeParametersVO>> paramsMap = removeSelectedParams(getAllCollegeParams(studentId), studentPDTO != null ? studentPDTO.getCollegeParams() : null);
		//====END Sasedeve edited by Mrutyunjaya on date 17-04-2017
		finalMap.put("collegeParameters", paramsMap.get(UN_SELECTED_PARAMS));
		finalMap.put("selectedCollegeParams", paramsMap.get(SELECTED_PARAMS));

		List<String> collegeZones = new CollegeDAO().getCollegeZones();
		finalMap.put("collegeZones", collegeZones);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	//====Start Sasedeve edited by Mrutyunjaya on date 17-04-2017
	public List<CollegeParametersDTO> getAllCollegeParams(int studentId) throws Exception
	{
		return new CollegeParametersDAO().getAllParameters(studentId,true);
	}
	//====End Sasedeve edited by Mrutyunjaya on date 17-04-2017
	/**
	 * @param allParameters
	 * @param selectedParams
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private Map<String, List<CollegeParametersVO>> removeSelectedParams(List<CollegeParametersDTO> allParameters, List<CollegeParametersDTO> selectedParams)
			throws Exception
	{
		Map<String, List<CollegeParametersVO>> collegeParamMap = new HashMap<String, List<CollegeParametersVO>>();
		collegeParamMap.put(SELECTED_PARAMS, new ArrayList<CollegeParametersVO>());
		collegeParamMap.put(UN_SELECTED_PARAMS, new ArrayList<CollegeParametersVO>());
		Set<Integer> selectedParamIds = null;

		if (selectedParams != null)
		{
			selectedParamIds = new HashSet<Integer>();
			for (CollegeParametersDTO collegeParametersDTO : selectedParams)
			{
				selectedParamIds.add(collegeParametersDTO.getId());
				collegeParamMap.get(SELECTED_PARAMS).add(copyFromDTOToVO(collegeParametersDTO));
			}
		}
		if (allParameters != null)
		{
			Iterator<CollegeParametersDTO> iterator = allParameters.iterator();
			CollegeParametersDTO collegeParametersDTO = null;
			while (iterator.hasNext() && (collegeParametersDTO = iterator.next()) != null)
			{
				if (selectedParamIds != null && selectedParamIds.contains(collegeParametersDTO.getId()))
				{
					iterator.remove();
				}
				else
				{
					collegeParamMap.get(UN_SELECTED_PARAMS).add(copyFromDTOToVO(collegeParametersDTO));
				}
			}
		}
		return collegeParamMap;
	}

	/**
	 * @param collegeParametersDTO
	 * @return
	 * @throws Exception
	 */
	private CollegeParametersVO copyFromDTOToVO(CollegeParametersDTO collegeParametersDTO) throws Exception
	{
		CollegeParametersVO collegeParametersVO = new CollegeParametersVO();
		BeanUtils.copyProperties(collegeParametersVO, collegeParametersDTO);
		if (collegeParametersDTO.getValues() != null)
		{
			collegeParametersVO.setParamValues(new JSONArray(collegeParametersDTO.getValues()));
		}
		return collegeParametersVO;
	}

	/**
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCollegeByFilters(CollegeSearchFilterVO bean) throws Exception
	{
		Map<String, Object> finalResultMap = new HashMap<String, Object>();
		EduPathElectiveShortListDAO electiveShortListDAO = new EduPathElectiveShortListDAO();
		bean.setCombinationIds(electiveShortListDAO.getCombinationsByStudentId(bean.getStudentId()));

		EduPathShortListDAO eduPathShortListDAO = new EduPathShortListDAO();
		bean.setStreamId(eduPathShortListDAO.getStreamIdByStudentId(bean.getStudentId()));

		bean.setGetTotalCount(true);

		ObjectMapper mapper = new ObjectMapper();
		Map<Object, Object> collegeSearchFilter = mapper.convertValue(bean, Map.class);
		if (bean.getActivities() != null && !bean.getActivities().isEmpty())
		{
			collegeSearchFilter.put("activities", CommonUtil.convertCommaSeperatedToList(bean.getActivities()));
		}

		if (bean.getInfrastructures() != null && !bean.getInfrastructures().isEmpty())
		{
			collegeSearchFilter.put("infrastructures", CommonUtil.convertCommaSeperatedToList(bean.getInfrastructures()));
		}

		//=========Start Sasedeve Edited By Mrutyunjaya on date 17-04-2017
		
		StudentCityLockDTO studentcityById = new EntranceExamsDAO().getCityIdByStudentId(bean.getStudentId());
		if(null != studentcityById  && !studentcityById.toString().isEmpty())
		{
			collegeSearchFilter.put("PCityId", studentcityById.getCityLockId());
		}
		else
		{
			collegeSearchFilter.put("PCityId", bean.getHomeCityId());
		}
		
		//=========END Sasedeve Edited By Mrutyunjaya on date 17-04-2017
		
		CollegeDAO collegeDAO = new CollegeDAO();
		finalResultMap.put(TOTAL_SIZE, collegeDAO.getCollegeDetailsTotalSize(collegeSearchFilter));
		finalResultMap.put(COLLEGE_RECORDS, collegeDAO.getCollegeDetails(collegeSearchFilter, bean.getPageNo(), getCollegePageCount()));
		return finalResultMap;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public int getCollegePageCount() throws Exception
	{
		String propertyValue = new GlobalSttingDAO().getPropertesValueByName(ApplicationConstants.GlobalSettings.COLLEGE_PAGE_SIZE.getProperty());
		return (propertyValue != null ? Integer.parseInt(propertyValue) : -1);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public int getMaxCompareCount() throws Exception
	{
		GlobalSettingDTO globalDTO = new GlobalSettingDTO();
		globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.COLLEGE_COMPARE_MAX_SELECT.getProperty());
		GlobalSettingDTO globalSettingDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
		int maxCompaerCount = (globalSettingDTO != null && null != globalSettingDTO.getPropertyValue() ? Integer.parseInt(globalSettingDTO.getPropertyValue()) : 0);
		return maxCompaerCount;
	}

	public static String getCollegeRecords()
	{
		return COLLEGE_RECORDS;
	}

	public static String getTotalSize()
	{
		return TOTAL_SIZE;
	}
	
	//vyankatesh 17-4-2017
	
	public List<CityDTO> getAllCity() throws Exception
	{
		return new CityDAO().getAllCityList();
	}
}
