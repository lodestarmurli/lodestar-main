package com.lodestar.edupath.APIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.APIAuthentication;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.AuthCodeValidationDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.PartnerDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.util.ApplicationProperties;

import netvalence.commons.excel.XLSEngine;
import netvalence.commons.excel.utils.NetValenceExcelCellVO;


public class SendingEmails extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(SendingEmails.class);
	
	private String									emailids;
	

	private int											id;
	private String   								Status="ERROR";
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
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
		OUT.debug("Inside SendingEmails class: execute Method");
		Status="ERROR";
		try
		{
			
			OUT.debug("Data Receive=>"+emailids);
			OUT.debug("Data Receive=>"+id);
			
			if(emailids!=null && !emailids.trim().equals(""))
			{
				
				MessageQueueDTO getfailedemail=new APISService().GetFailedEmailsbyid(id);
				
				if(getfailedemail!=null)
				{
					MessageQueueDTO msgque=new MessageQueueDTO();
					
					msgque.setMessageSubject(getfailedemail.getMessageSubject());
					msgque.setMessageData(getfailedemail.getMessageData());
					msgque.setStatus(NotificationConstant.MessageNotificationStatus.INPROGRESS.name());
					msgque.setQueuedTime(new Timestamp(System.currentTimeMillis()));
					msgque.setNotifiedTime(new Timestamp(System.currentTimeMillis()));
					msgque.setToAddress(emailids.trim());
					msgque.setNotifierType(getfailedemail.getNotifierType());
					msgque.setRetryCount(0);
					msgque.setFilePath(getfailedemail.getFilePath());
					
					if (MyBatisManager.getInstance().insert(MyBatisMappingConstants.INSERT_MESSAGE_QUEUE, msgque) == 1)
					{
						MessageQueueDTO updatemsg=new MessageQueueDTO();
						
						updatemsg.setId(getfailedemail.getId());
						
						updatemsg.setStatus(NotificationConstant.MessageNotificationStatus.SUCCESS.name());
						
						updatemsg.setToAddress(emailids);
						updatemsg.setQueuedTime(new Timestamp(System.currentTimeMillis()));
						APISService apiservice=new APISService();
						
						apiservice.updateFailedEmailsbyid(updatemsg);
						Status="SUCCESS";
					}
					
					
					
					
					
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
