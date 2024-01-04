package com.lodestar.edupath.base;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.student.service.TrailStudentDetailService;
import com.lodestar.edupath.util.datatable.DataTableEnumInterface;
import com.lodestar.edupath.util.datatable.DataTableUtils;
import com.lodestar.edupath.util.datatable.DataTableVO;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware
{

	private static final long		serialVersionUID	= 1L;
	public static Logger OUT = LoggerFactory.getLogger(BaseAction.class);
	protected HttpServletRequest	request;
	protected HttpServletResponse	response;
	protected int					parentSelectedSidebarMenuId;
	protected int					childSelectedSidebarMenuId;

	protected DataTableVO			dataTableVO			= new DataTableVO();

	protected String				dataTableColumns	= "";
	protected String				actionPermission	= "";

	protected Map<String, Object>	dataTableQueryMap;

	protected String				subAction;

	protected String				authKey;

	protected String				recaptchaSiteKey;

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;

	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public int getParentSelectedSidebarMenuId()
	{
		return parentSelectedSidebarMenuId;
	}

	public void setParentSelectedSidebarMenuId(int parentSelectedSidebarMenuId)
	{
		this.parentSelectedSidebarMenuId = parentSelectedSidebarMenuId;
	}

	public int getChildSelectedSidebarMenuId()
	{
		return childSelectedSidebarMenuId;
	}

	public void setChildSelectedSidebarMenuId(int childSelectedSidebarMenuId)
	{
		this.childSelectedSidebarMenuId = childSelectedSidebarMenuId;
	}

	protected UserSessionObject getUserSessionObject()
	{
		return (UserSessionObject) request.getSession().getAttribute(ApplicationConstants.SessionProperty.USER_SESSION_DETAILS_PROPERTY.getProperty());
	}

	protected StudentSessionObject getStudentSessionObject()
	{
		return (StudentSessionObject) request.getSession().getAttribute(ApplicationConstants.SessionProperty.STUDENT_SESSION_OBJECT.getProperty());
	}

	@SuppressWarnings("unchecked")
	protected void setSelectedId(String sessionName, Map<String, Object> sessionMap)
	{

		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> headerMenuMap = (Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>) sessionMap
				.get(ApplicationConstants.SessionProperty.HEADER_SESSION_MENU_LIST.getProperty());
		Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> sidebarMenuMap = (Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>) sessionMap
				.get(ApplicationConstants.SessionProperty.SIDEBAR_SESSION_MENU_LIST.getProperty());
		int headerSessionId = 0;
		for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> appMenu : headerMenuMap.entrySet())
		{
			if (appMenu.getKey().getRefName().equalsIgnoreCase(sessionName))
			{
				headerSessionId = appMenu.getKey().getId();
				break;
			}
		}
		sessionMap.put(ApplicationConstants.SessionProperty.SELECTED_HEADER_ID.getProperty(), headerSessionId);

		if (sessionMap.containsKey(ApplicationConstants.SUB_MENU_REF_NAME))
		{
			for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> applicationMap : sidebarMenuMap.entrySet())
			{
				if (applicationMap.getKey().getRefName().equalsIgnoreCase((String) sessionMap.get(ApplicationConstants.SUB_MENU_REF_NAME)))
				{
					setParentSelectedSidebarMenuId(applicationMap.getKey().getId());
					break;
				}
			}
		}
		else
		{
			for (Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> applicationMap : sidebarMenuMap.entrySet())
			{
				if (null != applicationMap.getKey().getParentId() && applicationMap.getKey().getParentId() == headerSessionId)
				{
					setParentSelectedSidebarMenuId(applicationMap.getKey().getId());
					break;
				}
			}
		}
	}

	/**
	 * This method create data table field columns name
	 * 
	 * @param clsName
	 */
	protected void createDataTableColumn(Class<?> clsName)
	{
		dataTableColumns = DataTableUtils.getDataTableFieldName(clsName).toString();
	}

	/**
	 * This method set Action permission
	 */
	protected void setActionPermission()
	{
		JSONObject actionJSON = new JSONObject();
		Object reqObj = null;
		for (DataTableEnumInterface.ActionPermissionEnum action : DataTableEnumInterface.ActionPermissionEnum.values())
		{
			reqObj = request.getAttribute(action.name());
			if (reqObj != null)
			{
				actionJSON.put(action.getJSONKey(), ((Boolean) reqObj ? true : false));
			}
		}
		actionPermission = actionJSON.toString();
	}

	/**
	 * @param list
	 * @return
	 * @throws Exception
	 */
	protected JSONArray convertCollectionToJSONArray(Collection<?> list) throws Exception
	{
		ObjectMapper mapper = DataTableUtils.getCustomObjectMapper();
		JSONArray dataArr = new JSONArray(mapper.writeValueAsString(list));
		return dataArr;
	}

	/**
	 * This method create JSON Response
	 * 
	 * @param jsonObj
	 * @throws Exception
	 */
	protected void writeToJSONResponse(Object jsonObj) throws Exception
	{
		response.setContentType("application/json");
		PrintWriter resopnseWriter = response.getWriter();
		resopnseWriter.write(jsonObj.toString());
		if (null != resopnseWriter)
		{
			resopnseWriter.close();
		}
	}

	public String getDataTableColumns()
	{
		return dataTableColumns;
	}

	public String getActionPermission()
	{
		return actionPermission;
	}

	public String getSubAction()
	{
		return subAction;
	}

	public void setSubAction(String subAction)
	{
		this.subAction = subAction;
	}

	public String getAuthKey()
	{
		return authKey;
	}

	public void setAuthKey(String authKey)
	{
		this.authKey = authKey;
	}

	public String getRecaptchaSiteKey()
	{
		return recaptchaSiteKey;
	}

	public void setRecaptchaSiteKey(String recaptchaSiteKey)
	{
		this.recaptchaSiteKey = recaptchaSiteKey;
	}
}
