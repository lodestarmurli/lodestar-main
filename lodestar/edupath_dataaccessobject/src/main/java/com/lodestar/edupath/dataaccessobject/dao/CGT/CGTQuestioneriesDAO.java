package com.lodestar.edupath.dataaccessobject.dao.CGT;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.cgt.CGTQuestioneriesDTO;

public class CGTQuestioneriesDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(CGTQuestioneriesDAO.class);
	
	public List<CGTQuestioneriesDTO> getCGTQuestioneriesDetailsBySection(CGTQuestioneriesDTO questioneriesDTO) throws Exception
	{
	   List<CGTQuestioneriesDTO> listResult = 	MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_CGTQUESTIONNAIE_DEATILS_BY_SECTION, questioneriesDTO);
	   OUT.debug("Founded CGTQuestioneriesDetails : {}", (listResult != null ? listResult.size() : 0));
	   return listResult;
	}
}
