package com.lodestar.edupath.fileupload.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class FileUploadService
{
	private static Logger	OUT	= LoggerFactory.getLogger(FileUploadService.class);

	public EActionStatus doUpdate(OccupationDTO occupationDTO)
	{
		try
		{
			Integer count = -1;
			count = new OccupationDAO().doUpdateImage(occupationDTO);
			if (count > 0)
			{
				return EActionStatus.SUCCESS;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return EActionStatus.FAILURE;
	}

	public List<OccupationDTO> getOccDataList()
	{
		List<OccupationDTO> list = null;
		try
		{
			list = new OccupationDAO().getOccupationNameAndId();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return list;
	}

}
