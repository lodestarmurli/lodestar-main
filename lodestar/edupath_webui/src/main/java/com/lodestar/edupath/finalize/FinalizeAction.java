package com.lodestar.edupath.finalize;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.collegeshortlist.service.CollegeShortListService;
import com.lodestar.edupath.datatransferobject.dto.StudentCollegeShortListDTO;
import com.lodestar.edupath.datatransferobject.dto.StudentTutorialCentreShortListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.finalize.service.FinalizeService;
import com.lodestar.edupath.tutorialshortlist.TutorialCentersShortListService;
import com.opensymphony.xwork2.ActionContext;

public class FinalizeAction extends BaseAction
{
	private static final long						serialVersionUID				= 1L;
	private static final Logger						OUT								= LoggerFactory.getLogger(FinalizeAction.class);
	private List<StudentTutorialCentreShortListDTO>	tutorialCenterShortListDetails	= new ArrayList<StudentTutorialCentreShortListDTO>();
	private List<StudentCollegeShortListDTO>		collegeShortListDetails			= new ArrayList<StudentCollegeShortListDTO>();
	private int										tutCenterShortListId;
	private int										colShortListId;
	String											studAddr;

	@Override
	public String execute()
	{
		OUT.debug("FinalizeAction : inside execute method");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SUB_MENU_REF_NAME, ApplicationConstants.APP_MENU_FINALIZE_REFNAME);
			setSelectedId(SessionTypeEnum.SESSION_3_FACI.getSessionName(), sessionMap);
			sessionMap.remove(ApplicationConstants.SUB_MENU_REF_NAME);

			int studentId = getStudentSessionObject().getId();
			tutorialCenterShortListDetails = new TutorialCentersShortListService().getShortListedTutorialCenters(studentId);
			collegeShortListDetails = new CollegeShortListService().getShortListedCollege(studentId);
			studAddr = new FinalizeService().getStudentAddress(studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String deleteColShortList()
	{
		try
		{
			JSONObject jsonObject = new JSONObject();
			PrintWriter printWriter = response.getWriter();
			new CollegeShortListService().deleteColShortListById(colShortListId, getStudentSessionObject().getId(), getUserSessionObject().getLoginId());
			response.setContentType("application/json");
			jsonObject.put("status", "success");
			printWriter.write(jsonObject.toString());
			printWriter.close();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String deleteTutCenterShortList()
	{
		try
		{
			JSONObject jsonObject = new JSONObject();
			PrintWriter printWriter = response.getWriter();
			new TutorialCentersShortListService().deleteTutCenterShortListById(tutCenterShortListId, getStudentSessionObject().getId(), getUserSessionObject()
					.getLoginId());
			response.setContentType("application/json");
			jsonObject.put("status", "success");
			printWriter.write(jsonObject.toString());
			printWriter.close();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public List<StudentTutorialCentreShortListDTO> getTutorialCenterShortListDetails()
	{
		return tutorialCenterShortListDetails;
	}

	public void setTutorialCenterShortListDetails(List<StudentTutorialCentreShortListDTO> tutorialCenterShortListDetails)
	{
		this.tutorialCenterShortListDetails = tutorialCenterShortListDetails;
	}

	public List<StudentCollegeShortListDTO> getCollegeShortListDetails()
	{
		return collegeShortListDetails;
	}

	public void setCollegeShortListDetails(List<StudentCollegeShortListDTO> collegeShortListDetails)
	{
		this.collegeShortListDetails = collegeShortListDetails;
	}

	public int getTutCenterShortListId()
	{
		return tutCenterShortListId;
	}

	public void setTutCenterShortListId(int tutCenterShortListId)
	{
		this.tutCenterShortListId = tutCenterShortListId;
	}

	public int getColShortListId()
	{
		return colShortListId;
	}

	public void setColShortListId(int colShortListId)
	{
		this.colShortListId = colShortListId;
	}

	public String getStudAddr()
	{
		return studAddr;
	}

	public void setStudAddr(String studAddr)
	{
		this.studAddr = studAddr;
	}
}
