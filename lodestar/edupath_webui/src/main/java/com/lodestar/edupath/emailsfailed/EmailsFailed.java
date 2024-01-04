package com.lodestar.edupath.emailsfailed;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.APISService;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.MessageQueueDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;



public class EmailsFailed extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	public static Logger OUT = LoggerFactory.getLogger(EmailsFailed.class);
	
	private List<MessageQueueDTO>	failedemails	= new ArrayList<MessageQueueDTO>();
	
	
	public List<MessageQueueDTO> getFailedemails() {
		return failedemails;
	}


	public void setFailedemails(List<MessageQueueDTO> failedemails) {
		this.failedemails = failedemails;
	}


	public String execute()
	{
		OUT.debug("Inside EmailsFailed class: execute Method");
		
		try
		{
			failedemails= new APISService().GetFailedEmails();
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		
		return SUCCESS;
	}
}
