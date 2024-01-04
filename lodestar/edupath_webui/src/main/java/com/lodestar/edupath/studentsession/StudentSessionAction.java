package com.lodestar.edupath.studentsession;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.opensymphony.xwork2.ActionContext;

public class StudentSessionAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(StudentSessionAction.class);

	public String execute() throws Exception
	{
		OUT.debug("Inside StudentSessionAction");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SESSION1.getSessionName(), sessionMap);

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String sessionTwo()
	{
		OUT.debug("Inside StudentSessionAction2");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SESSION2.getSessionName(), sessionMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String sessionThree()
	{
		OUT.debug("Inside StudentSessionAction3");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SESSION3.getSessionName(), sessionMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

	public String summary()
	{
		OUT.debug("Inside StudentSessionAction");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			if (null == sessionMap)
			{
				return "SessionExpired";
			}
			setSelectedId(SessionTypeEnum.SUMMARY.getSessionName(), sessionMap);
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return SUCCESS;
	}

}
