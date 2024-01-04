/*
 * @(#) NotificationEntity.java  
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
 * @Version 1.0 
 * @Date Nov 13, 2009	
 * @Author Nikhil.R
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.notification;

import java.io.Serializable;

import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;



public abstract class NotificationEntity implements Serializable
{
	public MessageQueueDTO	msgQueue;

	public NotificationEntity(MessageQueueDTO msgQueue)
	{
		this.msgQueue = msgQueue;
	}
}
