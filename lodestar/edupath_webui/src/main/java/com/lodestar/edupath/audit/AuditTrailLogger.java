/*
 * @(#) AuditTrailLogger.java   
 * 
 * Licensed Materials - Property of JaMocha Tech
 * (c) Copyright JaMochaTech Private Limited 2009, 2013. ALL RIGHTS RESERVED
 *
 * #730, 2nd Floor, 3rd Block, Koramangala, Bengaluru-560034, India 
 *
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0
 * @Date Mar 01, 2013	
 * @Author Pushpinder Singh
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.audit;

import java.sql.Timestamp;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.datatransferobject.dto.AuditLoggerDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;

public class AuditTrailLogger
{
	public static final Logger	OUT	= LoggerFactory.getLogger(AuditTrailLogger.class);

	/**
	 * @param moduleName
	 * @param actionPerformed
	 * @param auditMessage
	 * @param performedBy
	 */
	public static void addAuditInfo(ModuleNameEnum moduleName, String auditMessage, String performedBy)
	{
		AuditLoggerDTO auditLogger = new AuditLoggerDTO();
		auditLogger.setModule(moduleName.getDisplayName());
		auditLogger.setPerformedBy(performedBy);
		auditLogger.setMessage(auditMessage);
		auditLogger.setActionTime(new Timestamp(System.currentTimeMillis()));
		try
		{
			int auditId = MyBatisManager.getInstance().insert(AuditLoggerDTO.INSER_AUDIT_DETAILS, auditLogger);
			if (auditId > 0)
			{
				OUT.debug("Audit info. added successfully for : " + moduleName.getDisplayName());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public static void addAuditInfo(SqlSession session, ModuleNameEnum moduleName, String auditMessage, String performedBy)
	{
		AuditLoggerDTO auditLogger = new AuditLoggerDTO();
		auditLogger.setModule(moduleName.getDisplayName());
		auditLogger.setPerformedBy(performedBy);
		auditLogger.setMessage(auditMessage);
		auditLogger.setActionTime(new Timestamp(System.currentTimeMillis()));
		try
		{
			int auditId = session.insert(AuditLoggerDTO.INSER_AUDIT_DETAILS, auditLogger);
			if (auditId > 0)
			{
				OUT.debug("Audit info. added successfully for : " + moduleName.getDisplayName());
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}
}
