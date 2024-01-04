package com.lodestar.edupath.tum.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.role.ApplicationMenuDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.tum.service.StudentTUMService;

public class StudentTUMSubmitAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static final Logger	OUT					= LoggerFactory.getLogger(StudentTUMSubmitAction.class);

	private String				studentTUMList;
	private StudentTUMService	service;
	private int					serialNum;
	private int					pageAction			= 1;
	private String				actionNameValue		= "";
	private String				deleteQusestionList;
	private int 				questionCount		=0;

	@Override
	public String execute() throws Exception
	{
		setResopns();
		setSelectedMenu();
		return SUCCESS;
	}

	public String welcome() throws Exception
	{
		return SUCCESS;
	}

	public String insert()
	{
		OUT.debug("Inside StudentTUMSubmitAction (insert method)");
		try
		{

			service = new StudentTUMService();
			if ((null != deleteQusestionList && !deleteQusestionList.isEmpty()) && (null != studentTUMList && !studentTUMList.isEmpty()))
			{
				service.deleteQusetion(deleteQusestionList, getUserSessionObject().getId(), studentTUMList);
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.TUM_DELETE, " Deleted TUM Questions oldJSON ID :  "
						+ deleteQusestionList + " newJSON ID : " + studentTUMList, getUserSessionObject().getLoginId());
			}
			boolean isMatch = service.insertORUpdateStudentTUM(studentTUMList, getUserSessionObject().getId(), serialNum);
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.ANSWER_STUDENT, getUserSessionObject().getFullName() + " answer the TUM question. Answers: "
					+ studentTUMList + ".", getUserSessionObject().getLoginId());
			studentTUMList = null;
			deleteQusestionList = null;
			setResopns();
			setSelectedMenu();
			if (pageAction > 3)
			{
				service.updateTUMSession(getUserSessionObject().getId());
				return "QUESTION";
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return "NEXT";
	}

	public String autoSave()
	{
		OUT.debug("Inside StudentTUMSubmitAction (autoSave method)");
		try
		{

			service = new StudentTUMService();

			if ((null != deleteQusestionList && !deleteQusestionList.isEmpty()) && (null != studentTUMList && !studentTUMList.isEmpty()))
			{
				service.deleteQusetion(deleteQusestionList, getUserSessionObject().getId(), studentTUMList);
				AuditTrailLogger.addAuditInfo(ModuleNameEnum.TUM_DELETE, " Deleted TUM Questions oldJSON ID :  "
						+ deleteQusestionList + " newJSON ID : " + studentTUMList, getUserSessionObject().getLoginId());
			}
			boolean isMatch = service.insertORUpdateStudentTUM(studentTUMList, getUserSessionObject().getId(), serialNum);
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.ANSWER_STUDENT, getUserSessionObject().getFullName() + " answer the TUM question. Answers: "
					+ studentTUMList + ".", getUserSessionObject().getLoginId());
			studentTUMList = null;
			deleteQusestionList = null;
			
			service.savetoReferenceTable(getUserSessionObject().getId());
			
			
		}
		catch (Exception e)
		{
			OUT.error("Exception ", e);
		}
		return null;
	}

	public String back()
	{
		OUT.debug("Inside StudentTUMSubmitAction (back method)");
		try
		{
			setResopns();
			setSelectedMenu();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return "BACK";
	}

	void setResopns()
	{
		service = new StudentTUMService();
		JSONArray arraylist = service.getStutentTUM(getUserSessionObject().getId(), serialNum);
		JSONArray hobbies = service.getHobbiesOrInterest();
		JSONArray occupation = service.getOccupation();
		JSONArray DoYouKnow = service.getDoYouKnow();
		if (null != arraylist)
		{
			deleteQusestionList = arraylist.toString();
		}
		request.setAttribute("studentTUMArraylist", arraylist);
		request.setAttribute("HOBBIES", hobbies);
		request.setAttribute("OCCUPATION", occupation);
		request.setAttribute("DoYouKnow", DoYouKnow);

		if (serialNum == 7)
		{
			serialNum = 10;
		}
	}

	private void setSelectedMenu()
	{
		try
		{
			HttpSession mySession = request.getSession();
			Map<ApplicationMenuDTO, List<ApplicationMenuDTO>> menuMap = (Map<ApplicationMenuDTO, List<ApplicationMenuDTO>>) mySession.getAttribute("SideBarMenus");
			if (null != menuMap && !menuMap.isEmpty())
			{
				for (Map.Entry<ApplicationMenuDTO, List<ApplicationMenuDTO>> mapValue : menuMap.entrySet())
				{
					List<ApplicationMenuDTO> list = mapValue.getValue();
					if (null != list && !list.isEmpty())
					{
						for (ApplicationMenuDTO menu : list)
						{
							if (menu.getActionPath().equals(actionNameValue))
							{
								setChildSelectedSidebarMenuId(menu.getId());
								setParentSelectedSidebarMenuId(mapValue.getKey().getId());
								break;
							}
						}
					}
				}
			}

		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
	}

	public String getStudentTUMList()
	{
		return studentTUMList;
	}

	public void setStudentTUMList(String studentTUMList)
	{
		this.studentTUMList = studentTUMList;
	}

	public int getSerialNum()
	{
		return serialNum;
	}

	public void setSerialNum(int serialNum)
	{
		this.serialNum = serialNum;
	}

	public int getPageAction()
	{
		return pageAction;
	}

	public void setPageAction(int pageAction)
	{
		this.pageAction = pageAction;
	}

	public String getActionNameValue()
	{
		return actionNameValue;
	}

	public void setActionNameValue(String actionNameValue)
	{
		this.actionNameValue = actionNameValue;
	}

	public String getDeleteQusestionList()
	{
		return deleteQusestionList;
	}

	public void setDeleteQusestionList(String deleteQusestionList)
	{
		this.deleteQusestionList = deleteQusestionList;
	}

}
