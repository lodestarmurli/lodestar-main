/*
 * @(#) EmailPacket.java  
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
 * @Date 23-Jul-2009
 * @Author Nikhl.R
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.email;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmailPacket
{

	private String[]		to				= null;
	private String[]		cc				= null;
	private String			subject			= null;
	private String			body			= null;
	public String			mime			= "TEXT";
	private File[]			attachments		= null;
	private List<String>	imageFileList	= new ArrayList<String>();
	private String			filePath;

	/**
	 * @param to
	 * @param cc
	 * @param subject
	 * @param body
	 * @param mimeType
	 * @param attachments
	 */
	public EmailPacket(String[] to, String[] cc, String subject, String body, String mimeType, File[] attachments)
	{
		String[] localTo = to;
		this.to = localTo;
		this.cc = cc;
		this.subject = subject;
		this.body = body;
		if (mimeType != null)
			this.mime = mimeType;
		File[] localattachments = attachments;
		this.attachments = localattachments;
	}

	/**
	 * @param to
	 * @param cc
	 * @param subject
	 * @param body
	 */
	public EmailPacket(String to, String cc, String subject, String body)
	{
		this(new String[]
		{
			to
		}, new String[]
		{
			cc
		}, subject, body, null, null);
	}

	@Override
	public String toString()
	{
		StringBuffer display = new StringBuffer();
		display.append("To : " + (this.to != null ? "null" : Arrays.toString(to)) + "\n");
		display.append("Cc : " + (this.cc != null ? "null" : Arrays.toString(cc)) + "\n");
		display.append("Subject : " + this.subject + "\n\n");
		display.append("Content : " + this.body + "\n");
		display.append("Attachments : " + (this.attachments != null ? this.attachments.length : 0) + "\n");

		return display.toString();
	}

	/**
	 * @return
	 */
	public String getBody()
	{
		return this.body;
	}

	/**
	 * @return
	 */
	public String[] getCc()
	{
		return this.cc;
	}

	/**
	 * @return
	 */
	public String getSubject()
	{
		return this.subject;
	}

	/**
	 * @return
	 */
	public String[] getTo()
	{
		String[] localTo = this.to;
		return localTo;
	}

	/**
	 * @return
	 */
	public File[] getAttachments()
	{
		File[] localAttachments = attachments;
		return localAttachments;
	}

	public List<String> getImageFileList()
	{
		return imageFileList;
	}

	public void setImageFileList(List<String> imageFileList)
	{
		this.imageFileList = imageFileList;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
}