/*
 * @(#) ActionStatus.java
 *
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0
 * @Date Nov 07,2011
 * @Author Adish K
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */

package com.lodestar.edupath.bulkupload;

/** enum , will be used as an return type for the action status */
public enum EActionStatus
{
	SUCCESS(1000), FAILURE(1001),

	IS_EXIST(1020), IS_EXIST_P1(1021), IS_EXIST_P2(1022),

	HAS_DEPENDENCY(1040), HAS_FIXED_DEPENDENCY(1041), UN_AUTHORIZED(1060);

	int	value;

	/**
	 * @param returnCode
	 */
	private EActionStatus(int returnCode)
	{
		this.value = returnCode;
	}

	public int getValue()
	{
		return this.value;
	}
}
