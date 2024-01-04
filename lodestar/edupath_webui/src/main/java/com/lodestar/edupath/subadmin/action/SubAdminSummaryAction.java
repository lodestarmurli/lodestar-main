package com.lodestar.edupath.subadmin.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.SubAdminDTO;
import com.lodestar.edupath.subadmin.service.SubAdminService;
import com.opensymphony.xwork2.ModelDriven;

public class SubAdminSummaryAction extends BaseAction implements ModelDriven<SubAdminDTO>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(SubAdminSummaryAction.class);

	private SubAdminDTO			subAdminDTO			= new SubAdminDTO();
	
	private List<SubAdminDTO>   subAdminList;
	
	private  SubAdminService     service;

	@Override
	public String execute() 
	{
		OUT.debug("Inside SubAdminSummaryAction");
		try
		{
			service = new SubAdminService();
			subAdminList = service.getAllSubAdminList();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String addScreen()
	{
		OUT.debug("Inside SubAdminSummaryAction (addScreen method)");
		try
		{
			subAdminDTO.setId(0);
			//subAdminDTO = null;
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return "ADDSCREEN";
	}
	
	public String editScreen()
	{
		OUT.debug("Inside SubAdminSummaryAction (editScreen method)");
		try
		{
			service = new SubAdminService();
			subAdminDTO = service.getSubadminById(subAdminDTO);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return "ADDSCREEN";
	}

	@Override
	public SubAdminDTO getModel()
	{

		return subAdminDTO;
	}

	public List<SubAdminDTO> getSubAdminList()
	{
		return subAdminList;
	}

	public void setSubAdminList(List<SubAdminDTO> subAdminList)
	{
		this.subAdminList = subAdminList;
	}

	public SubAdminDTO getSubAdminDTO()
	{
		return subAdminDTO;
	}

	public void setSubAdminDTO(SubAdminDTO subAdminDTO)
	{
		this.subAdminDTO = subAdminDTO;
	}

}
