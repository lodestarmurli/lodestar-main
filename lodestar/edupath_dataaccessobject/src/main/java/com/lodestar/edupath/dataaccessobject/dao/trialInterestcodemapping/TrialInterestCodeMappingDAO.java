package com.lodestar.edupath.dataaccessobject.dao.trialInterestcodemapping;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.TrialInterestCodeMappingDTO;

public class TrialInterestCodeMappingDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(TrialInterestCodeMappingDAO.class);
//===============Start Sasedeve edited by Mrutyunjaya on date 01-04-2017===========================
	public List<String> getAllTrialInterestMapping(int isClass) throws Exception
	{
		if(isClass==1)
		{
			List<String> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.TRIAL_INTREST_CODE_GET_ALL_10, null);
			OUT.debug("Founded Trial Interest Code Mapping Size : {}", (null != list ? list.size() : 0));
			return (null != list ? list : new ArrayList<String>(5));
		}
		else
		{
			List<String> list = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.TRIAL_INTREST_CODE_GET_ALL_11, null);
			OUT.debug("Founded Trial Interest Code Mapping Size : {}", (null != list ? list.size() : 0));
			return (null != list ? list : new ArrayList<String>(5));
		}
	}

	public TrialInterestCodeMappingDTO getTrialMappingByRAISECCode(String raisecCode, int isClass) throws Exception
	{
		if(isClass==1)
		{
			OUT.debug("Get Trial Interest Code Mapping with raisecCode : {} ", raisecCode);
			return (TrialInterestCodeMappingDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.TRIAL_INTREST_CODE_BY_RE_10, raisecCode);
		}
		else
		{
			OUT.debug("Get Trial Interest Code Mapping with raisecCode : {} ", raisecCode);
			return (TrialInterestCodeMappingDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.TRIAL_INTREST_CODE_BY_RE_11, raisecCode);
		}
	}
	//===============END Sasedeve edited by Mrutyunjaya on date 01-04-2017===========================
}
