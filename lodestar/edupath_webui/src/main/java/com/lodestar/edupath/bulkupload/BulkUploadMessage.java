/*
 * @(#) BulkUploadMessage.java   
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

public enum BulkUploadMessage
{
	SUCCESS("Excel Uploaded successfully"),
	FAILURE_NO_DATA_TO_PROCESS("No data to process"),
	FAILURE_IN_PROGRESS("Upload is already in progress"),
	FAILURE_VALIDATION("Validation failure"),
	FAILURE_COLUMN_MISMATCH("Column name mismatch"),
	FAILURE_COLUMNNUM("Invalid number of columns"),
	FAILURE_GENERAL("Upload completed with error(s)"),
	FAILURE_EXCEPTION("Upload failed"),
	FAILURE_SERVER("Upload failed due server failure");

	private String	displayValue;

	private BulkUploadMessage(String displayValue)
	{
		this.displayValue = displayValue;
	}

	public String getDisplayValue()
	{
		return displayValue;
	}
}
