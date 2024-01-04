package com.lodestar.edupath.dataaccessobject.dao.path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDegreeSpecializationDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathDegreesDTO;

public class EduPathDegreesDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EduPathDegreesDAO.class);

	public List<EduPathDegreesDTO> getEduPathDegreesById(EduPathDegreesDTO dto) throws Exception
	{
		List<EduPathDegreesDTO> result = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_EDUPATH_DEGREES_BY_ID, dto);
		OUT.debug("Degree Founded:", (result != null ? result.size() : 0));
		return result;
	}

	public List<EduPathDegreeSpecializationDTO> getEduPathDegreesSpecializationById(EduPathDegreeSpecializationDTO dto) throws Exception
	{
		List<EduPathDegreeSpecializationDTO> result = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_EDUPATH_DEGREES_SP_BY_ID, dto);
		OUT.debug("Degree SP Founded:", (result != null ? result.size() : 0));
		return result;
	}

	public List<EduPathDegreesDTO> getDegreesByEdupathId(String edupathIds) throws Exception
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("edupathIds", edupathIds);
		List<EduPathDegreesDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_DEGREE_BY_EDUPATHID, param);
		OUT.debug("Degree Founded:", (list != null ? list.size() : 0));
		return list;
	}
	
	public List<EduPathDegreeSpecializationDTO> getDegreesSpecializationByEdupathIds(String edupathIds) throws Exception
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("edupathIds", edupathIds);
		List<EduPathDegreeSpecializationDTO> list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_DEGREE_SP__BY_EDUPATHIDS, param);
		OUT.debug("Degree specialization Founded:", (list != null ? list.size() : 0));
		return list;
	}
	
}
