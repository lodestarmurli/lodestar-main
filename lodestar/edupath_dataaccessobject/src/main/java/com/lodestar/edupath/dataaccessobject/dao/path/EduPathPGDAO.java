package com.lodestar.edupath.dataaccessobject.dao.path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPGDTO;
import com.lodestar.edupath.datatransferobject.dto.path.EduPathPGSpecializationDTO;

public class EduPathPGDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(EduPathPGDAO.class);
	
	public EduPathPGDTO getEduPathPGById(EduPathPGDTO dto) throws Exception
	{
		EduPathPGDTO result =  (EduPathPGDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_EDUPATH_PG_BY_ID, dto);
		OUT.debug("PG Founded:", result);
		return result;
	}
	
	public EduPathPGSpecializationDTO getEduPathPGSpecializationById(EduPathPGSpecializationDTO dto) throws Exception
	{
		EduPathPGSpecializationDTO result = (EduPathPGSpecializationDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_EDUPATH_PG_SP_BY_ID, dto);
		OUT.debug("PG SP Founded:", result);
		return result;
	}
}
