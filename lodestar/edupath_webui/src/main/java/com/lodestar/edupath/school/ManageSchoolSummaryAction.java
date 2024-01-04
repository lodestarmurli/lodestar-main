package com.lodestar.edupath.school;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.school.service.ManageSchoolService;

public class ManageSchoolSummaryAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(ManageSchoolSummaryAction.class);
	private List<SchoolDTO>		schoolList			= new ArrayList<SchoolDTO>();
	private int					id;

	@Override
	public String execute()
	{
		OUT.debug("ManageSchoolSummaryAction : inside execute method");
		try
		{
			schoolList = new ManageSchoolService().getAllSchools();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public String delete()
	{
		OUT.debug("ManageSchoolSummaryAction : inside delete method");
		try
		{
			ManageSchoolService manageSchoolService = new ManageSchoolService();
			boolean isDependent = manageSchoolService.checkDependency(id);
			if (isDependent)
			{
				addActionError(getText("com.edupath.action.delete.dependency.msg.failed", new String[]
				{
					"school"
				}));
			}
			else
			{
				manageSchoolService.delete(id, getUserSessionObject().getLoginId());
				addActionMessage(getText("com.edupath.action.delete.msg.successful", new String[]
				{
					"School"
				}));
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		execute();
		return SUCCESS;
	}

	public List<SchoolDTO> getSchoolList()
	{
		return schoolList;
	}

	public void setSchoolList(List<SchoolDTO> schoolList)
	{
		this.schoolList = schoolList;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

}
