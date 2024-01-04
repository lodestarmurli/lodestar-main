package com.lodestar.edupath.util;

import java.util.List;
import java.util.Map;

import com.lodestar.edupath.auth.service.AuthenticationService;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class ApplicationMenuUtils
{
	/**
	 * @param userSessionObject
	 * @param sessionMap
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public static boolean disableHeaderMenu(UserSessionObject userSessionObject, Map<String, Object> sessionMap, int studentId) throws Exception
	{
		AuthenticationService service = new AuthenticationService();
		Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> appMenu = service.getTabbedMenuItemsByRole(userSessionObject.getRoleId(),
				userSessionObject.getRoleWeight(), userSessionObject.getReviewer(), userSessionObject.isTrial(),userSessionObject);
		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap = appMenu.get(ApplicationConstants.HEADER_LIST);
		boolean canChangeCart = service.disableHeaderMenu(userSessionObject, headerMenuMap, userSessionObject.getRoleWeight(), studentId);
		sessionMap.put(ApplicationConstants.SessionProperty.HEADER_SESSION_MENU_LIST.getProperty(), headerMenuMap);
		sessionMap.put(ApplicationConstants.SessionProperty.SIDEBAR_SESSION_MENU_LIST.getProperty(), appMenu.get(ApplicationConstants.SIDEBAR_LIST));
		return canChangeCart;
	}

	/**
	 * @param userSessionObject
	 * @param sessionMap
	 * @throws Exception
	 */
	public static void changeMenu(UserSessionObject userSessionObject, Map<String, Object> sessionMap) throws Exception
	{
		AuthenticationService service = new AuthenticationService();
		Map<String, Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>> appMenu = service.getTabbedMenuItemsByRole(userSessionObject.getRoleId(),
				userSessionObject.getRoleWeight(), userSessionObject.getReviewer(), userSessionObject.isTrial(),userSessionObject);
		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap = appMenu.get(ApplicationConstants.HEADER_LIST);
		int headerSessionId = service.getHeaderId(userSessionObject, headerMenuMap, userSessionObject.getRoleWeight());
		sessionMap.put(ApplicationConstants.SessionProperty.HEADER_SESSION_MENU_LIST.getProperty(), headerMenuMap);
		sessionMap.put(ApplicationConstants.SessionProperty.SIDEBAR_SESSION_MENU_LIST.getProperty(), appMenu.get(ApplicationConstants.SIDEBAR_LIST));
		sessionMap.put(ApplicationConstants.SessionProperty.SELECTED_HEADER_ID.getProperty(), headerSessionId);
		sessionMap.put(ApplicationConstants.SessionProperty.ACTIVE_HEADER.getProperty(), headerSessionId);
	}
}
