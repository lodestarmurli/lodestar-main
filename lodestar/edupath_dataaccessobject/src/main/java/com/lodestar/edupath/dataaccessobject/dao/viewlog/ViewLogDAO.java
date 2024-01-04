package com.lodestar.edupath.dataaccessobject.dao.viewlog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.AuditLoggerDTO;

public class ViewLogDAO
{
	private static final Logger	OUT	= LoggerFactory.getLogger(ViewLogDAO.class);
	
	public List<AuditLoggerDTO> getViewLogByDate(AuditLoggerDTO auditLoggerDTO) throws Exception
	{
		OUT.debug("From Date : {}, To Date {}:", auditLoggerDTO.getFromDate(), auditLoggerDTO.getToDate());
		List<AuditLoggerDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_VIEW_LOG_BY_DATE_WITH_MODULE, auditLoggerDTO);
		OUT.debug("View Log Count :{}", (resultList != null ? resultList.size() : 0));
		return resultList;
	}
	
	public List<AuditLoggerDTO> getModuleName() throws Exception
	{
		List<AuditLoggerDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_MODULE_NAME, null);
		OUT.debug("Module Count : {}", (resultList != null ? resultList.size() : 0));
		return resultList;
	}
	
	public List<AuditLoggerDTO> getViewLogByDateWithModule(AuditLoggerDTO auditLoggerDTO) throws Exception
	{
		OUT.debug("From Date : {}, To Date :{}", auditLoggerDTO.getFromDate(), auditLoggerDTO.getToDate());
		List<AuditLoggerDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_VIEW_LOG_BY_DATE, auditLoggerDTO);
		OUT.debug("View Log Count :{}", (resultList != null ? resultList.size() : 0));
		return resultList;
	}

}
