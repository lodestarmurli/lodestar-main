package com.lodestar.edupath.reviewelective.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.reviewelective.service.ReviewElectivesService;

public class ReviewElectivesDetailsAction extends BaseAction
{

	private static final long		serialVersionUID	= 8602070455016788482L;
	private static Logger			OUT					= LoggerFactory.getLogger(ReviewElectivesDetailsAction.class);
	private Integer					stdId;
	private Map<String, Object>		electiveMap			= new HashMap<String, Object>();
	private String					shortListIds;
	private ReviewElectivesService	service;

	public ReviewElectivesDetailsAction()
	{
		service = new ReviewElectivesService();
	}

	@Override
	public String execute() throws Exception
	{
		OUT.debug("Inside of Review Elective");
		UserSessionObject sessionObject = getUserSessionObject();
		stdId = service.getStudentIdByUserId(sessionObject.getId());
		if (null == stdId || stdId <= 0)
		{
			addActionError("Invalid Student");
			return ERROR;
		}
		electiveMap = service.getElectiveMap(stdId);
		return SUCCESS;
	}

	public String saveCombinationOrder() throws Exception
	{
		if (null == stdId || stdId <= 0)
		{
			addActionError("Invalid Student");
			return ERROR;
		}
		if(null != shortListIds && !shortListIds.isEmpty())
		{
			service.updateShortlistElectiveById(shortListIds,stdId);
		}
		electiveMap = service.getElectiveMap(stdId);
		return SUCCESS;
	}

	public Integer getStdId()
	{
		return stdId;
	}

	public void setStdId(Integer stdId)
	{
		this.stdId = stdId;
	}

	public Map<String, Object> getElectiveMap()
	{
		return electiveMap;
	}

	public void setElectiveMap(Map<String, Object> electiveMap)
	{
		this.electiveMap = electiveMap;
	}

	public String getShortListIds()
	{
		return shortListIds;
	}

	public void setShortListIds(String shortListIds)
	{
		this.shortListIds = shortListIds;
	}

}
