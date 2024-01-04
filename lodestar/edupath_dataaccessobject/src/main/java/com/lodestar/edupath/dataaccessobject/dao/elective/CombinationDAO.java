package com.lodestar.edupath.dataaccessobject.dao.elective;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.elective.CombinationDTO;

public class CombinationDAO
{
	private static Logger	OUT	= LoggerFactory.getLogger(CombinationDAO.class);

	public List<CombinationDTO> getCombinationListByStudentId(Integer studentId) throws Exception
	{
		List<CombinationDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_COMBINATION_BY_STUDENTID, studentId.intValue());
		return list;
	}

	public List<CombinationDTO> getCombinationListForElectiveScreen(int streamId, int cityId, int subjectId) throws Exception
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("streamId", streamId);
		parameters.put("cityId", cityId);
		parameters.put("subjectId", subjectId);
		
		OUT.debug("Getting Combination for Elective Screen {}", parameters);
		
		List<CombinationDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_COMBINATION_FOR_ELECTIVE_SCREEN, parameters);
		return list;
	}

	public List<CombinationDTO> getAllCombinations() throws Exception
	{
		List<CombinationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_COMBINATION, null);
		OUT.debug("Combinations  founded :{}", (list != null ? list.size() : 0));
		return list;
	}

}