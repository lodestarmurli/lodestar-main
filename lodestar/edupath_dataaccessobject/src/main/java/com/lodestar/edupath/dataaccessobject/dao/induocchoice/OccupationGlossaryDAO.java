package com.lodestar.edupath.dataaccessobject.dao.induocchoice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class OccupationGlossaryDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(OccupationGlossaryDAO.class);

	public List<OccupationDTO> getOccupationGlossaryDetailsByPageNumber(int pageNumber) throws Exception
	{
		OUT.debug("OccupationGlossaryDAO : getting occupation Glossary details for page number{} -", pageNumber);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_OCCUPATION_GLOSSARY_DETAILS, null,
				pageNumber * ApplicationConstants.OCCUPATION_GLOSS_COUNT, ApplicationConstants.OCCUPATION_GLOSS_COUNT);
	}

	
	public List<OccupationDTO> getOccupationGlossaryForClusterOcc(int pageNumber) throws Exception
	{
		OUT.debug("OccupationGlossaryDAO : getOccupationGlossaryForClusterOcc occupation Glossary details for page number{} -", pageNumber);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_OCCUPATION_GLOSSARY_FOR_CLUSTEROCC, 0);
	}
	
	
	public int getOccupationCount() throws Exception
	{

		return MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.GET_OCCUPATION_COUNT, null);
	}
	
	public List<OccupationDTO> getCFOccupationGlossaryDetailsByPageNumber(int pageNumber) throws Exception
	{
		OUT.debug("OccupationGlossaryDAO : getting CF occupation Glossary details for page number{} -", pageNumber);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_ALL_CF_OCCUPATION_GLOSSARY_DETAILS, null,
				pageNumber * ApplicationConstants.OCCUPATION_GLOSS_COUNT, ApplicationConstants.OCCUPATION_GLOSS_COUNT);
	}
	
	public List<OccupationDTO> getSearchCFOccupationGlossaryDetails(int pageNumber, List<String> occNames) throws Exception
	{
		OUT.debug("OccupationGlossaryDAO : getting Search CF occupation Glossary details for page number{} - OccNames:{}", pageNumber,occNames);
		return MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_SEARCH_CF_OCCUPATION_GLOSSARY_DETAILS, occNames );
	}
}
