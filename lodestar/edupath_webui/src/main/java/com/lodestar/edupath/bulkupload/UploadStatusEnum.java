/*
 * @(#) UploadStatusEnum.java   
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
 * @Date Mar 18, 2013	
 * @Author Pushpinder Singh
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.bulkupload;

public enum UploadStatusEnum
{
	INPROGRESS("IN PROGRESS"), CANCELED("CANCELED"), FAILED("FAILED"), UNKNOWN("UNKNOWN"), COMPLETED("COMPLETED"), COMPLETED_WITH_ERROR("COMPLETED WITH ERRORS");

	private String	type;

	/**
	 * @param type
	 */
	UploadStatusEnum(String type)
	{
		this.type = type;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		return this.type;
	}
}
