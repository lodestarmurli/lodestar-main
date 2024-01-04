package com.lodestar.edupath.auth.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;

public class UserInfoLoggingFilter implements Filter
{
	private static final Logger	OUT				= LoggerFactory.getLogger(UserInfoLoggingFilter.class); //$NON-NLS-1$

	private static final String	unknown			= "Unknown";
	private static final String	timeStr			= "userLoginTime";

	private String				userVariable	= null;
	private boolean				doLog			= true;

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		String userVariable = config.getInitParameter("userVariable");
		if (userVariable != null)
		{
			this.userVariable = userVariable;
		}
		else
		{
			doLog = false;
			OUT.error("User information logging is not available as 'userVariable' init param is not set");
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		if (doLog)
		{
			HttpServletRequest request = (HttpServletRequest) req;
			HttpSession session = request.getSession();

			String threadName = Thread.currentThread().getName();

			try
			{
				UserSessionObject user = (UserSessionObject) session.getAttribute(userVariable);
				StringBuilder newThreadName;
				if (user == null)
				{
					newThreadName = new StringBuilder(threadName).append(" - ").append(unknown);
				}
				else
				{
					newThreadName = new StringBuilder(threadName).append(" - ").append(user.getLoginId());
				}
				if (request.getRemoteAddr().equals("0:0:0:0:0:0:0:1"))
				{
					newThreadName.append(" : ").append("127.0.0.1");
				}
				else
				{
					newThreadName.append(" : ").append(request.getRemoteHost());
				}

				Thread.currentThread().setName(newThreadName.toString());
				chain.doFilter(request, res);
				if (user == null && session.getAttribute(userVariable) != null)
					session.setAttribute(timeStr, new Date());
			}
			catch (ClassCastException cce)
			{
				OUT.error("User information logging is not available as '{}' does not implement {}", session.getAttribute(userVariable),
						UserSessionObject.class.getName());
				doLog = false;
			}
			catch (IllegalStateException ise)
			{
				// occurs when we access the session when it is invalidated.
			}
			finally
			{
				Thread.currentThread().setName(threadName);
			}
		}
		else
		{
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy()
	{
		// Empty block
	}
}
