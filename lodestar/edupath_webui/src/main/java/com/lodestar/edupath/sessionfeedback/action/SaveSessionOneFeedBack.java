package com.lodestar.edupath.sessionfeedback.action;

import java.io.PrintWriter;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.sessionfeedback.service.SessionFeedBackFromService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;



public class SaveSessionOneFeedBack extends BaseAction implements ModelDriven<SessionFeedBackFromBean>{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(SaveSessionOneFeedBack.class);
	private SessionFeedBackFromBean	sessionformBean			= new SessionFeedBackFromBean();
	public String execute()
	{
		PrintWriter printWriter = null;
		JSONObject respJSON = new JSONObject();
		
		try
		{
			printWriter = response.getWriter();
			int userId = 0;
			UserSessionObject userSessionObject = getUserSessionObject();
			OUT.debug("SaveSessionOneFeedBack for studentId :{}", (userId = userSessionObject.getId()));
			OUT.debug("SaveSessionOneFeedBack answer data==>",sessionformBean.getFeedbackAnswered());
			new SessionFeedBackFromService().saveSessiononefeedback(sessionformBean, userId);
			respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SessionProperty.Session_One_FeedBack.getProperty(),false);
			
		}
		catch (Exception e)
		{
			if (printWriter != null)
			{
				respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.FAILURE);
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (printWriter != null)
			{
				printWriter.write(respJSON.toString());
				printWriter.close();
			}
		}
		
		
		
		
		return null;
	}
	
	public SessionFeedBackFromBean getModel()
	{
		return sessionformBean;
	}
}
