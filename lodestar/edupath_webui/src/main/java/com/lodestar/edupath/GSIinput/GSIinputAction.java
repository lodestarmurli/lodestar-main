package com.lodestar.edupath.GSIinput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.TipsAndSuggestionService.TipsAndSuggestionService;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class GSIinputAction extends BaseAction{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(GSIinputAction.class);
	private String									TipsAnswered;
	
	public String execute()
	{
		OUT.debug("Inside GSIinputAction class execute method");
		return "success";
	}

	
	public String GSinputSubmit()
	{
		OUT.debug("Inside GSIinputAction class GSinputSubmit method");
		
		
	
		
		try
		{
			OUT.debug("GSInput answer data==>"+TipsAnswered);
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			UserSessionObject userSessionObject = getUserSessionObject();
			new TipsAndSuggestionService().insergsinputdata(TipsAnswered,studentSessionObject.getId(),userSessionObject.getId());
			
			
			
			
		}
		catch (Exception e)
		{
			
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		
		
		return "success";
	}


	public String getTipsAnswered() {
		return TipsAnswered;
	}


	public void setTipsAnswered(String tipsAnswered) {
		TipsAnswered = tipsAnswered;
	}
	
	
}
