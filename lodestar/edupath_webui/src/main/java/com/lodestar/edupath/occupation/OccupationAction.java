package com.lodestar.edupath.occupation;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;

public class OccupationAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;

	private int					occupationId;
	private OccupationDTO		occupationDetails;

	public String execute() throws Exception
	{

		occupationDetails = new OccupationDAO().getOccupationDetails(occupationId);

		return SUCCESS;
	}

	public int getOccupationId()
	{
		return occupationId;
	}

	public void setOccupationId(int occupationId)
	{
		this.occupationId = occupationId;
	}

	public OccupationDTO getOccupationDetails()
	{
		return occupationDetails;
	}

}
