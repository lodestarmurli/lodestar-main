package com.lodestar.edupath.guidance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.guidance.service.GuidanceService;
import com.opensymphony.xwork2.ModelDriven;

public class GuidanceAction extends BaseAction implements ModelDriven<FacilitatorDetailsDTO>
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	OUT					= LoggerFactory.getLogger(GuidanceAction.class);
	private FacilitatorDetailsDTO guidanceDTO = new FacilitatorDetailsDTO();
	private GuidanceService service;
	
	public String execute() throws Exception
	{
		try
		{
			service = new GuidanceService();
			guidanceDTO = service.getGuidanceDetails(getUserSessionObject().getId());
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}


	@Override
	public FacilitatorDetailsDTO getModel()
	{
		return guidanceDTO;
	}


	public FacilitatorDetailsDTO getGuidanceDTO()
	{
		return guidanceDTO;
	}


	public void setGuidanceDTO(FacilitatorDetailsDTO guidanceDTO)
	{
		this.guidanceDTO = guidanceDTO;
	}

}
