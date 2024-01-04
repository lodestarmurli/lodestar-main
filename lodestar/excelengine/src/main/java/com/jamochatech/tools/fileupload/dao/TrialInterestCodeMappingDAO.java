package com.jamochatech.tools.fileupload.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.dao.DBManager;
import com.jamochatech.tools.fileupload.dto.TrialInterestCodeMappingDTO;

public class TrialInterestCodeMappingDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(TrialInterestCodeMappingDAO.class);

	public List<TrialInterestCodeMappingDTO> getAllTrailInterestMapping() throws Exception
	{
		List<TrialInterestCodeMappingDTO> list = DBManager.getInstance().getResultAsList(DBOperations.GET_ALL_TRIAL_INTREST_CODE, null);
		OUT.debug("Founded Trail Insert Size : {}", (null != list ? list.size() : 0));
		return (null != list ? list : new ArrayList<TrialInterestCodeMappingDTO>(10));
	}

	public int insertTrailInterestMapping(TrialInterestCodeMappingDTO dto) throws Exception
	{
		OUT.debug("Insert Trail Interest with  raisecCode: {} ", dto.getRaisecCode());
		DBManager.getInstance().insert(DBOperations.INSERT_TRIAL_INTREST_CODE, dto);
		OUT.debug("Inserted Trail Interest with Id : {} ", dto.getId());
		return dto.getId();
	}

	public int updateTrailInterestMapping(TrialInterestCodeMappingDTO dto) throws Exception
	{
		OUT.debug("Update Trail Interest with raisecCode: {} and Id : {} ", dto.getRaisecCode(), dto.getId());
		DBManager.getInstance().updateObject(DBOperations.UPDATE_TRIAL_INTREST_CODE, dto);
		return dto.getId();
	}
}
