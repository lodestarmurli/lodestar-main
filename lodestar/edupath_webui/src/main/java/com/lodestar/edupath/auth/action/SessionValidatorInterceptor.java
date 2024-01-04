/*
 * @(#) SessionValidatorInterceptor.java  
 * 
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */

package com.lodestar.edupath.auth.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class SessionValidatorInterceptor extends AbstractInterceptor
{
	private static final Logger	OUT		= LoggerFactory.getLogger(SessionValidatorInterceptor.class.getName());
	public HttpServletRequest	request	= null;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> sessionMap = context.getSession();

		request = ServletActionContext.getRequest();

		if (isSessionExpired(sessionMap))
		{
			OUT.debug("Session expired redirecting to login screen.");
			addActionError(invocation, "Your session has expired.");
			return "SessionExpired";
		}

		return invocation.invoke();
	}

	private void addActionError(ActionInvocation invocation, String message)
	{
		Object action = invocation.getAction();
		if (action instanceof ValidationAware)
		{
			((ValidationAware) action).addActionError(message);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private boolean isSessionExpired(Map<String, Object> sessionMap)
	{
		if (sessionMap.get(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty()) == null)
		{
			return true;
		}
		return false;
	}
}
