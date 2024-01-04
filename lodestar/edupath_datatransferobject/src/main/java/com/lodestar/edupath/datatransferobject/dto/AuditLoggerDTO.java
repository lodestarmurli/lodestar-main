/*
 * @(#) AuditLoggerDTO.java   
 * 
 * Licensed Materials - Property of JaMocha Tech
 * (c) Copyright JaMochaTech Private Limited 2009, 2009. ALL RIGHTS RESERVED
 *
 * #730, 2nd Floor, 3rd Block, Koramangala, Bengaluru-560034, India 
 *
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 5.3 
 * @Date Dec 20, 2012	
 * @Author Pushpinder Singh
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.datatransferobject.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


/**
 * @author nilam.k
 */
//@PartitionModule(tableName = PartitionTableEnum.AUDIT_TRAIL, period = PartitionPeriodEnum.MONTHLY)
public class AuditLoggerDTO implements Serializable, IModel
{

	private static final long	serialVersionUID			= -8561212292948705135L;

	public static final String	INSER_AUDIT_DETAILS			= "AuditTrail.insertAuditDetails";
	public static final String	GET_ALL_AUDIT_DETAILS		= "AuditTrail.getAllAuditDetails";
	public static final String	GET_AUDIT_DETAIL_BY_DATE	= "AuditTrail.getAuditDetailByDate";

	private Integer				id;
	private String				module;
	private Integer				modifiedId;
	private String				message;
	private String				actionPerformed;
	private Timestamp			actionTime;
	private String				performedBy;
	private String				fromDate;
	private String				toDate;
	private Set<String>			moduleNameSet;
	private Set<String>			actionTypeSet;
	private Set<String>			performedBySet;
	private String 				auditMessageLike;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getActionPerformed()
	{
		return actionPerformed;
	}

	public void setActionPerformed(String actionPerformed)
	{
		this.actionPerformed = actionPerformed;
	}

	public Timestamp getActionTime()
	{
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime)
	{
		this.actionTime = actionTime;
	}

	public String getPerformedBy()
	{
		return performedBy;
	}

	public void setPerformedBy(String performedBy)
	{
		this.performedBy = performedBy;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public Set<String> getModuleNameSet()
	{
		return moduleNameSet;
	}

	public void setModuleNameSet(Set<String> moduleNameSet)
	{
		this.moduleNameSet = moduleNameSet;
	}

	public Set<String> getActionTypeSet()
	{
		return actionTypeSet;
	}

	public void setActionTypeSet(Set<String> actionTypeSet)
	{
		this.actionTypeSet = actionTypeSet;
	}

	public Set<String> getPerformedBySet()
	{
		return performedBySet;
	}

	public void setPerformedBySet(Set<String> performedBySet)
	{
		this.performedBySet = performedBySet;
	}

	public Integer getModifiedId()
	{
		return modifiedId;
	}

	public void setModifiedId(Integer modifiedId)
	{
		this.modifiedId = modifiedId;
	}

	public String getAuditMessageLike()
	{
		return auditMessageLike;
	}

	public void setAuditMessageLike(String auditMessageLike)
	{
		this.auditMessageLike = auditMessageLike;
	}

	public String getModule()
	{
		return module;
	}

	public void setModule(String module)
	{
		this.module = module;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}