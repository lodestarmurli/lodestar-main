package com.lodestar.edupath.viewfeedback;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.viewfeedback.service.ViewFeedbackService;

public class ViewFeedbackAction extends BaseAction
{
	private static final long		serialVersionUID	= 1L;
	private static final Logger		OUT					= LoggerFactory.getLogger(ViewFeedbackAction.class);

	private String					feedbackType;
	private String					fromDate;
	private String					toDate;
	private List<StudentDetailsDTO>	studentList			= new ArrayList<StudentDetailsDTO>();
	private boolean					admin = true;

	@Override
	public String execute()
	{
		OUT.debug("ViewFeedbackAction : inside execute method");
		try
		{

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION);
		}
		return SUCCESS;
	}

	public String getFeedbackByFilter()
	{
		OUT.debug("ViewFeedbackAction : inside getFeedbackByFilter method");
		try
		{
			OUT.debug("feedbackType : {} , fromDate : {}, toDate : {}", feedbackType, fromDate, toDate);
			StudentDetailsDTO studentDetailsDto = new StudentDetailsDTO();
			UserSessionObject userSessionObject = getUserSessionObject();
			if (RoleTypeEnum.FACILITATOR.getWeight() == userSessionObject.getRoleWeight())
			{
				admin = false;
			}
			studentDetailsDto.setFeedbackFromDate(TimeUtil.getDate(fromDate, TimeUtil.AM_PM_REPORT_FORMAT));
			studentDetailsDto.setFeedbackToDate(TimeUtil.getDate(toDate, TimeUtil.AM_PM_REPORT_FORMAT));
			studentDetailsDto.setFeedbackType(feedbackType);
			studentList = new ViewFeedbackService().getFeedbackList(studentDetailsDto, admin, userSessionObject.getId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return "showFeedbackContent";
	}

	public String getFeedbackType()
	{
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType)
	{
		this.feedbackType = feedbackType;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public List<StudentDetailsDTO> getStudentList()
	{
		return studentList;
	}

	public void setStudentList(List<StudentDetailsDTO> studentList)
	{
		this.studentList = studentList;
	}

	public boolean isAdmin()
	{
		return admin;
	}

	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
}
