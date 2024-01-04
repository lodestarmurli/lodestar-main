package com.lodestar.edupath.viewlog.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.viewlog.ViewLogDAO;
import com.lodestar.edupath.datatransferobject.dto.AuditLoggerDTO;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;

public class ViewLogService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ViewLogService.class);

	public List<AuditLoggerDTO> getViewLog(AuditLoggerDTO audLoggerDTO)
	{
		List<AuditLoggerDTO> resultList = null;
		try
		{
			if((audLoggerDTO.getFromDate() == null || audLoggerDTO.getFromDate().isEmpty()) && (audLoggerDTO.getToDate() == null || audLoggerDTO.getToDate().isEmpty()) )
			{
				String fromDate = TimeUtil.getDateFromMillis(System.currentTimeMillis(), TimeUtil.QUERY_DATE_FORMAT);
				audLoggerDTO.setFromDate(TimeUtil.getFromDate(fromDate, TimeUtil.QUERY_DATE_FORMAT));
				audLoggerDTO.setToDate(TimeUtil.getToDate(fromDate, TimeUtil.QUERY_DATE_FORMAT));
			}
			ViewLogDAO logDAO = new ViewLogDAO();
			resultList = logDAO.getViewLogByDate(audLoggerDTO);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return resultList;
	}
	
	public TreeMap<String, String> getModuleName()
	{
		TreeMap<String, String> resultMap = null;
		try
		{
			ViewLogDAO logDAO = new ViewLogDAO();
			List<AuditLoggerDTO> resultList = logDAO.getModuleName();
			
			if(null != resultList && !resultList.isEmpty())
			{
				resultMap = new TreeMap<String, String>();
				for(AuditLoggerDTO dto : resultList)
				{
					resultMap.put(dto.getModule(), dto.getModule());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return resultMap;
	}
	
	public List<AuditLoggerDTO> getViewLogByAjax(AuditLoggerDTO audLoggerDTO, String moduleName)
	{
		List<AuditLoggerDTO> resultList = null;
		try
		{
			if(null != moduleName && !moduleName.isEmpty())
			{
				Set<String> moduleSet = new HashSet<String>();
				for(String name : moduleName.split(","))
				{
					moduleSet.add(name.trim());
				}
				audLoggerDTO.setModuleNameSet(moduleSet);
			}
			String fromStr = audLoggerDTO.getFromDate();
			fromStr = TimeUtil.getDateInString(fromStr, TimeUtil.DISPLAY_DATE_MONTH_YEAR_FORMAT, TimeUtil.QUERY_DATE_FORMAT);
			audLoggerDTO.setFromDate(TimeUtil.getFromDate(fromStr, TimeUtil.QUERY_DATE_FORMAT));
			
			String toStr = audLoggerDTO.getToDate();
			toStr = TimeUtil.getDateInString(toStr, TimeUtil.DISPLAY_DATE_MONTH_YEAR_FORMAT, TimeUtil.QUERY_DATE_FORMAT);
			audLoggerDTO.setToDate(TimeUtil.getToDate(toStr, TimeUtil.QUERY_DATE_FORMAT));
			
			ViewLogDAO logDAO = new ViewLogDAO();
			resultList = logDAO.getViewLogByDateWithModule(audLoggerDTO);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return resultList;
		
	}
}
