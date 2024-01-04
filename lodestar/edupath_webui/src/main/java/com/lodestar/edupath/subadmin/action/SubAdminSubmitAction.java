package com.lodestar.edupath.subadmin.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.SubAdminDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.subadmin.service.SubAdminService;
import com.opensymphony.xwork2.ModelDriven;

public class SubAdminSubmitAction extends BaseAction implements ModelDriven<SubAdminDTO>
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	OUT					= LoggerFactory.getLogger(SubAdminSubmitAction.class);

	private SubAdminDTO			subAdminDTO			= new SubAdminDTO();
	private SubAdminService		service;

	@Override
	public String execute() throws Exception
	{
		return super.execute();
	}

	public String insert() throws Exception
	{
		OUT.debug("Inside SubAdminSubmitAction (insert method)");
		try
		{
			service = new SubAdminService();
			boolean isMatch = service.insertSubAdmin(subAdminDTO);

			if (!isMatch)
			{
				addActionError(getText("com.edupath.action.add.msg.error", new String[]
				{
						"SubAdmin",
						"email id allready exit"
				}));
				// subAdminDTO = service.getSubadminById(subAdminDTO);
				return "ADDSCREEN";
			}
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.ROLE_DEFINITION, "SubAdmin : " + subAdminDTO.getEmailId() + " created on : " + service.getAuditDateStr(),
					getUserSessionObject().getLoginId());
			addActionMessage(getText("com.edupath.action.add.msg.successful", new String[]
			{
				"SubAddmin"
			}));
			// service.
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return SUCCESS;
	}

	public String update()
	{
		OUT.debug("Inside SubAdminSubmitAction (update method)");
		try
		{
			service = new SubAdminService();
			boolean isMatch = service.updateSubAdminDetailsById(subAdminDTO);

			if (!isMatch)
			{
				addActionError(getText("com.edupath.action.modify.error", new String[]
				{
						"SubAdmin",
						"email id allready exit"
				}));
				subAdminDTO = service.getSubadminById(subAdminDTO);
				return "ADDSCREEN";
			}
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.ROLE_DEFINITION, "SubAdmin : " + subAdminDTO.getEmailId() + " updated on : " + service.getAuditDateStr(),
					getUserSessionObject().getLoginId());
			addActionMessage(getText("com.edupath.action.modify.successful", new String[]
			{
				"SubAddmin"
			}));
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return SUCCESS;
	}

	public String delete()
	{
		OUT.debug("Inside SubAdminSubmitAction (delete method)");
		try
		{
			service = new SubAdminService();
			subAdminDTO = service.getSubadminById(subAdminDTO);
			service.deleteSubAdminById(subAdminDTO);
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.ROLE_DEFINITION, "SubAdmin : " + subAdminDTO.getEmailId() + " deleted on : " + service.getAuditDateStr(),
					getUserSessionObject().getLoginId());
			addActionMessage(getText("com.edupath.action.delete.msg.successful", new String[]
			{
				"SubAddmin"
			}));

		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return SUCCESS;
	}

	@Override
	public SubAdminDTO getModel()
	{

		return subAdminDTO;
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
