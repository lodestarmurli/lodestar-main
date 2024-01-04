package com.lodestar.edupath.stuinduocchoice.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.student.ShortListDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.stuinduocchoice.service.MyShortListService;

public class MyShortListSubAction extends BaseAction
{

	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(MyShortListSubAction.class);
	private String				occIndId;
	private Integer				occIndustryId;
	private int					shortListId;
	private String				status;

	public String insert()
	{
		try
		{
			OUT.debug("Adding ShortList for UserId:{},  occIndId:{}, occIndustryId: {}", getUserSessionObject().getId(), occIndId, occIndustryId);
			MyShortListService myShortListService = new MyShortListService();
			int studentId = new StudentDetailsDAO().getStudentDetailsByUserId(getUserSessionObject().getId()).getId();
			List<ShortListDTO> shortListByStudentId = myShortListService.getShortListByStudentId(studentId);

			OUT.debug("Number of ShortList Items {}", (shortListByStudentId == null ? 0 : shortListByStudentId.size()));
			if (shortListByStudentId == null || shortListByStudentId.isEmpty() || shortListByStudentId.size() < 6)
			{
				
				//Start Sasedeve edited By Mrutyunjaya on 19-12-2017
				
				int orderno=1;
				
				if(shortListByStudentId!=null && !shortListByStudentId.isEmpty() && shortListByStudentId.size() < 6)
				{
					orderno=shortListByStudentId.size() + 1;
				}
				
				
				
				//End Sasedeve edited By Mrutyunjaya on 19-12-2017
				
				shortListId = myShortListService.insertMyShortList(studentId, occIndId, occIndustryId,orderno);
				status = "SUCCESS";
				StringBuilder auditMessage = new StringBuilder();
				auditMessage.append("Added ShortList occIndId: ").append(occIndId).append(", industryId: ").append(occIndustryId);
				insertAudit(auditMessage.toString(), getUserSessionObject());
			}
			else
			{
				OUT.warn("Exceeded the number of the shortlist items");
				status = "ERROR";
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			status = "FAILURE";
		}
		return "insertresult";
	}

	public String delete()
	{
		try
		{
			new MyShortListService().deleteShortList(shortListId);
			StringBuilder auditMessage = new StringBuilder();
			auditMessage.append("Deleted ShortList shortListId: ").append(shortListId);
			insertAudit(auditMessage.toString(), getUserSessionObject());
			status = "SUCCESS";
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "insertresult";
	}

	private void insertAudit(String auditMessage, UserSessionObject userSessionObject)
	{
		AuditTrailLogger.addAuditInfo(ModuleNameEnum.OCCUPATION_SHORT_LIST, auditMessage, userSessionObject.getLoginId());
	}
	
	public String getOccIndId()
	{
		return occIndId;
	}

	public void setOccIndId(String occIndId)
	{
		this.occIndId = occIndId;
	}

	public String getStatus()
	{
		return status;
	}

	public int getShortListId()
	{
		return shortListId;
	}

	public void setShortListId(int shortListId)
	{
		this.shortListId = shortListId;
	}

	public Integer getOccIndustryId()
	{
		return occIndustryId;
	}

	public void setOccIndustryId(Integer occIndustryId)
	{
		this.occIndustryId = occIndustryId;
	}

}
