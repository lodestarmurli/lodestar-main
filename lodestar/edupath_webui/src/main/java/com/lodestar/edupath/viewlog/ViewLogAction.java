package com.lodestar.edupath.viewlog;

import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.AuditLoggerDTO;
import com.lodestar.edupath.viewlog.service.ViewLogService;
import com.opensymphony.xwork2.ModelDriven;

public class ViewLogAction extends BaseAction implements ModelDriven<AuditLoggerDTO>
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	private static final Logger		OUT					= LoggerFactory.getLogger(ViewLogAction.class);
	private AuditLoggerDTO			auditLoggerDTO		= new AuditLoggerDTO();
	private ViewLogService			service;
	private List<AuditLoggerDTO>	viewLogList;
	private String					moduleName;
	private TreeMap<String, String>	moduleMap			= null;

	public String execute()
	{
		OUT.debug("Inside ViewLogAction");
		try
		{
			service = new ViewLogService();
			viewLogList = service.getViewLog(auditLoggerDTO);
			moduleMap = service.getModuleName();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String getViewLogByAjax()
	{
		OUT.debug("Inside ViewLogAction");
		try
		{
			service = new ViewLogService();
			viewLogList = service.getViewLogByAjax(auditLoggerDTO, moduleName);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "TABLESCREEN";
	}

	@Override
	public AuditLoggerDTO getModel()
	{
		return auditLoggerDTO;
	}

	public AuditLoggerDTO getAuditLoggerDTO()
	{
		return auditLoggerDTO;
	}

	public void setAuditLoggerDTO(AuditLoggerDTO auditLoggerDTO)
	{
		this.auditLoggerDTO = auditLoggerDTO;
	}

	public List<AuditLoggerDTO> getViewLogList()
	{
		return viewLogList;
	}

	public void setViewLogList(List<AuditLoggerDTO> viewLogList)
	{
		this.viewLogList = viewLogList;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public TreeMap<String, String> getModuleMap()
	{
		return moduleMap;
	}

	public void setModuleMap(TreeMap<String, String> moduleMap)
	{
		this.moduleMap = moduleMap;
	}

}
