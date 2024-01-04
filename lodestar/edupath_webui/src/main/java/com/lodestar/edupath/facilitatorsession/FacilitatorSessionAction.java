package com.lodestar.edupath.facilitatorsession;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.TipsAndSuggestionService.TipsAndSuggestionService;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.opensymphony.xwork2.ActionContext;

public class FacilitatorSessionAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(FacilitatorSessionAction.class);

	public String execute() throws Exception
	{
		OUT.debug("Inside FacilitatorSessionAction : execute");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			
			
			setSelectedId(SessionTypeEnum.SESSION_1_FACI.getSessionName(), sessionMap);
			
			
			//Start Sasedeve Edited By Mrutyunjaya on Date 12-09-2017
			
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			
			boolean issession1completefacitips=new TipsAndSuggestionService().GetTipsSession1CompletedFaci(studentSessionObject.getId());
			sessionMap.put(ApplicationConstants.SessionProperty.Is_Session1CompleteFaci.getProperty(),issession1completefacitips);
			
			if(!issession1completefacitips)
			{
				return "tipsandsuggestionsession1";
			}
			
			
			
			//END Sasedeve Edited By Mrutyunjaya on Date 12-09-2017
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String sessionTwo()
	{
		OUT.debug("Inside FacilitatorSessionAction: sessionTwo");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SESSION_2_FACI.getSessionName(), sessionMap);
			
			
           //Start Sasedeve Edited By Mrutyunjaya on Date 12-09-2017
			
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			
			boolean issession2completefacitips=new TipsAndSuggestionService().GetTipsSession2CompletedFaci(studentSessionObject.getId());
			sessionMap.put(ApplicationConstants.SessionProperty.Is_Session2CompleteFaci.getProperty(),issession2completefacitips);
			
			
			if(!issession2completefacitips)
			{
				return "tipsandsuggestionsession2";
			}
			
			
			
			//END Sasedeve Edited By Mrutyunjaya on Date 12-09-2017
			
			
			
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String sessionThree()
	{
		OUT.debug("Inside FacilitatorSessionAction : sessionThree");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SESSION_3_FACI.getSessionName(), sessionMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String summary()
	{
		OUT.debug("Inside FacilitatorSessionAction : summary");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SESSION_SUMMARY_FACI.getSessionName(), sessionMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}
}
