package com.lodestar.edupath.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import com.lodestar.edupath.auth.service.UserSessionObject;

public class SessionManagementEngine
{
	private static volatile SessionManagementEngine	sessionManagementEngine	= null;

	private Map<Integer, UserSessionObject>			userSessionInfoMap		= null;
	private Map<String, HttpSession>				sessionMap				= null;

	private SessionManagementEngine()
	{
		sessionMap = new ConcurrentHashMap<String, HttpSession>();
		userSessionInfoMap = new ConcurrentHashMap<Integer, UserSessionObject>();
	}

	/**
	 * @return
	 */
	public static SessionManagementEngine getInstance()
	{
		if (sessionManagementEngine == null)
		{
			synchronized (SessionManagementEngine.class)
			{
				sessionManagementEngine = new SessionManagementEngine();
			}
		}
		return sessionManagementEngine;
	}

	/**
	 * This method is to add session object
	 * 
	 * @param userId
	 * @param sessionObj
	 */
	public void addUserSessionInfoObject(Integer userId, UserSessionObject userSessionVO)
	{
		userSessionInfoMap.put(userId, userSessionVO);
	}

	/**
	 * This method is to add session object
	 * 
	 * @param sessionId
	 * @param sessionObj
	 */
	public void addSessionObject(String sessionId, HttpSession sessionObj)
	{
		sessionMap.put(sessionId, sessionObj);
	}

	/**
	 * This method is to add session object
	 * 
	 * @param sessionId
	 * @param sessionObj
	 */
	public void removeSessionObject(String sessionId)
	{
		sessionMap.remove(sessionId);
	}

	/**
	 * This method is to remove session object
	 * 
	 * @param userId
	 */
	public void removeUserSessionInfoObject(Integer userId)
	{
		userSessionInfoMap.remove(userId);
	}

	/**
	 * This method returns session object bases on sessionId
	 * 
	 * @param sessionId
	 * @return
	 */
	public HttpSession getSessionObject(String sessionId)
	{
		HttpSession session = sessionMap.get(sessionId);
		return session;
	}

	/**
	 * This method returns session object bases on sessionId
	 * 
	 * @param userId
	 * @return
	 */
	public UserSessionObject getUserSessionInfoObject(Integer userId)
	{
		UserSessionObject userVO = userSessionInfoMap.get(userId);
		return userVO;
	}

	/**
	 * This method returns the sessionMap
	 * 
	 * @return
	 */
	public Map<String, HttpSession> getSessionMap()
	{
		return sessionMap;
	}

	/**
	 * This method returns the sessionMap
	 * 
	 * @return
	 */
	public Map<Integer, UserSessionObject> getUserSessionInfoMap()
	{
		return userSessionInfoMap;
	}

}
