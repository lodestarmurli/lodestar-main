package com.lodestar.edupath.review.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.review.service.ReviewService;
import com.opensymphony.xwork2.ActionContext;

public class ReviewSummaryAction extends BaseAction
{
	private static final long		serialVersionUID	= 1L;
	private static final Logger		OUT					= LoggerFactory.getLogger(ReviewSummaryAction.class);

	private List<StudentDetailsDTO>	studentDetailsList	= new ArrayList<StudentDetailsDTO>();

	@Override
	public String execute()
	{
		OUT.debug("ReviewSummaryAction : inside execute method");
		try
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			sessionMap.put(ApplicationConstants.SUB_MENU_REF_NAME, ApplicationConstants.APP_MENU_REVIEW_REFNAME);
			setSelectedId(SessionTypeEnum.PRESESSION_FACI.getSessionName(), sessionMap);
			sessionMap.remove(ApplicationConstants.SUB_MENU_REF_NAME);
			StudentSessionObject studentSessionObject = getStudentSessionObject();
			studentSessionObject.setFullName(null);
			studentSessionObject.setId(0);
			studentSessionObject.setLoginId(null);
			studentSessionObject.setUserId(0);
			studentSessionObject.setCityId(0);
			studentSessionObject.setEmailId(null);
			studentSessionObject.setIsCanChangeCart(true);

			studentDetailsList = new ReviewService().getStudentSessionDetails();
			request.getSession().setAttribute("isReview", false);
			OUT.debug("student details : {}", studentDetailsList);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return SUCCESS;
	}

	public List<StudentDetailsDTO> getStudentDetailsList()
	{
		return studentDetailsList;
	}

}
