package com.lodestar.edupath.APIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.APIS.APIAuthentication;
import com.lodestar.edupath.datatransferobject.dto.APIS.AuthCodeValidationDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;


public class EmailValidation extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(EmailValidation.class);
	
	private String									emailids;

	private String   								Status="ERROR";
	
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	

	
	public String getEmailids() {
		return emailids;
	}

	public void setEmailids(String emailids) {
		this.emailids = emailids;
	}

	public String execute()
	{
		OUT.debug("Inside EmailValidation class: execute Method");
		Status="ERROR";
		try
		{
			
			OUT.debug("Data Receive=>"+emailids);
			
			
			if(emailids!=null && !emailids.trim().equals(""))
			{
				
				String[] EMAILIDS = emailids.split(",");
				
				boolean allemailvalide=true;
				
				for(String email:EMAILIDS)
				{
				
					String url = "https://api.mailgun.net/v3/address/validate?api_key=pubkey-cc30cbd33bcb4738c52144b7b1310f3c&mailbox_verification=true&address="+email.trim();
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("Content-Type:", "application/json");
					InputStreamReader in = null;
					StringBuilder sb = new StringBuilder();
					in = new InputStreamReader(con.getInputStream(),Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
					in.close();
					
					JSONObject jsonDataObject = new JSONObject(sb.toString());
					
					if(jsonDataObject!=null && jsonDataObject.has("is_valid") && jsonDataObject.has("mailbox_verification"))
					{
						if(jsonDataObject.getBoolean("is_valid") && jsonDataObject.getBoolean("mailbox_verification"))
						{
							
						}
						else
						{
							allemailvalide=false;
						}
					}
				}
				
				
				if(allemailvalide)
				{
					Status="SUCCESS";
				}
			
			}
			
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			Status="ERROR";
			
		}
		
		
		
		return SUCCESS;
	}
	
}
