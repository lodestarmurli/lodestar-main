package com.lodestar.edupath.dataaccessobject.dao.APIS.client;

import org.apache.ibatis.session.SqlSession;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.APIS.client.ClientRaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.RaisecCodeDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RaisecCodeDAO {
	private static final Logger	OUT	= LoggerFactory.getLogger(RaisecCodeDAO.class);
	
	
	public  RaisecCodeDTO getRaisecCode(String raisecCode)
	{
		RaisecCodeDTO dto = null;
			try
			{
				dto = (RaisecCodeDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_RAISEC_CODE, raisecCode);
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
			return dto;	
	}
	
	public  ClientRaisecCodeDTO getRaisecCodeForClient(String raisecCode)
	{
		ClientRaisecCodeDTO dto = null;
			try
			{
				dto = (ClientRaisecCodeDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_RAISEC_CODE_FOR_CLIENT, raisecCode);
			}
			catch (Exception e)
			{
				OUT.error(ApplicationConstants.EXCEPTION, e);
			}
			return dto;	
	}
	
}
