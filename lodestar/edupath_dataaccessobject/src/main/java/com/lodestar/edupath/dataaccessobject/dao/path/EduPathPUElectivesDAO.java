package com.lodestar.edupath.dataaccessobject.dao.path;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPUElectivesDTO;

public class EduPathPUElectivesDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EduPathPUElectivesDAO.class);
	
	public List<EduPathPUElectivesDTO> getPuElective(EduPathPUElectivesDTO dto) throws Exception
	{
		List<EduPathPUElectivesDTO> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_PU_ELECTIVES_BY_IDS, dto);
		OUT.debug("EduPathPUElectives Details founded : ", (list != null ? list.size() : 0));
		return list;
	}
	
	public EduPathPUElectivesDTO getPuElectiveByEduPathId(EduPathPUElectivesDTO dto) throws Exception
	{
		EduPathPUElectivesDTO list = (EduPathPUElectivesDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_PU_ELECTIVES_BY_IDS, dto);
		OUT.debug("EduPathPUElectives Details founded : ", list);
		return list;
	}
}
