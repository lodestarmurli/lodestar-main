package com.lodestar.edupath.sessionfeedback.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.sessionfeedback.service.SessionFeedBackFromService;
import com.opensymphony.xwork2.ModelDriven;





public class SessionTwoFeedBack extends BaseAction implements ModelDriven<SessionFeedBackFromBean>{

	
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(SessionTwoFeedBack.class);
	private SessionFeedBackFromBean	sessionformBean			= new SessionFeedBackFromBean();
	public String execute()
	{
		OUT.debug("Inside SessionOne FeedBack From");
		try
		{
			
			new SessionFeedBackFromService().getSessionOneQuestions(sessionformBean,2);
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return "error";
		}
		
		return "success";
	}
	public SessionFeedBackFromBean getModel()
	{
		return sessionformBean;
	}

}
