/*
 * @(#) EMailSettings.java  
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
 * @Date Jul 24, 2009	
 * @Author Nikhil.R
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.email;

import java.util.List;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.EmailSettingMasterDTO;

public class EMailSettings
{
	private static volatile EMailSettings	emailSettings	= null;
	private boolean							isSMTPAuthenticationRequired;
	private String							smtpUserName;
	private String							smtpPassword;
	private String							smtpServer;
	private int								smtpServerPort;
	private String							smtpFromAddress;
	private String							encryptionType;

	/**
	 * @return
	 */
	public static EMailSettings getInstance()
	{
		if (emailSettings == null)
		{
			synchronized (EMailSettings.class)
			{
				if (emailSettings == null)
				{
					emailSettings = new EMailSettings();
				}
			}
		}
		return emailSettings;
	}

	/**
	 * 
	 */
	private EMailSettings()
	{
		loadSMTPDetails();
	}

	/**
	 * @return the smtpServer
	 */
	public String getSmtpServer()
	{
		return smtpServer;
	}

	/**
	 * @return the smtpServerPort
	 */
	public int getSmtpServerPort()
	{
		return smtpServerPort;
	}

	/**
	 * @return the smtpFromAddress
	 */
	public String getSmtpFromAddress()
	{
		return smtpFromAddress;
	}

	/**
	 * @return the isSMTPAuthenticationRequired
	 */
	public boolean isSMTPAuthenticationRequired()
	{
		return isSMTPAuthenticationRequired;
	}

	/**
	 * @return the smtpUserName
	 */
	public String getSmtpUserName()
	{
		return smtpUserName;
	}

	/**
	 * @return the smtpPassword
	 */
	public String getSmtpPassword()
	{
		return smtpPassword;
	}

	public String getEncryptionType()
	{
		return encryptionType;
	}

	/**
	 * @throws Exception
	 */
	public void loadSMTPDetails()
	{
		List<EmailSettingMasterDTO> resultAsList = null;
		try
		{
			resultAsList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_EMAIL_SETTINGS, null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (resultAsList != null && !resultAsList.isEmpty())
		{

			EmailSettingMasterDTO emailSettingMasterDTO = resultAsList.get(0);
			smtpFromAddress = emailSettingMasterDTO.getFromAddress();
			encryptionType = emailSettingMasterDTO.getEncryptiontype();
			isSMTPAuthenticationRequired = emailSettingMasterDTO.getIsSMTPAuthenticationRequired();
			smtpPassword = emailSettingMasterDTO.getPassword();
			smtpServerPort = emailSettingMasterDTO.getPort();
			smtpServer = emailSettingMasterDTO.getServer();
			smtpUserName = emailSettingMasterDTO.getUsername();

		}

		// isSMTPAuthenticationRequired = true;
		// smtpUserName = "sujit063@gmail.com";
		// smtpPassword = "mymobileno03";
		// smtpServer = "smtp.gmail.com";
		// smtpServerPort = 465;
		// smtpFromAddress = "sujit063@gmail.com";
		// encryptionType = "SSL";
	}
}
