package com.jamochatech.tools.fileupload.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.excelengine.dao.DBManager;
import com.jamochatech.tools.fileupload.dto.OccupationDTO;

public class OccupationDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(OccupationDAO.class);
	
	public boolean doUpdateOccupationImage(OccupationDTO occupationDTO)
	{
		boolean updateStatus = true;
		try
		{
			 DBManager.getInstance().update(DBOperations.UPDATE_OCCUPATION_IMAGE, occupationDTO);
		}
		catch (Exception e)
		{
			updateStatus = false;
			OUT.error("Exception", e);
		}
		return updateStatus;
	}
	
	public List<OccupationDTO> getOccupationNameAndId() throws Exception
	{
		try
		{

			List<OccupationDTO> list = DBManager.getInstance().getResultAsList(DBOperations.GET_OCCUPATION_NAME_AND_ID, null);
			OUT.debug("Occupation fetched from DB :{}", (list != null ? list.size() : 0));
			return list;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return null;
	}
}
