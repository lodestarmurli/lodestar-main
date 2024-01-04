package com.lodestar.edupath.worldofjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.sessionfeedback.service.SessionFeedBackFromService;


public class WorldOfJobs extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(WorldOfJobs.class);
	
	
	
	public String execute()
	{
		OUT.debug("Inside WorldOfJobs class execute method");
		
		
		return "success";
	}
	
}
