package com.lodestar.edupath.finalsummary;

import java.io.PrintWriter;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.enumtype.SessionTypeEnum;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.facilitatorstudent.FacilitatorStudentSummaryAction;
import com.lodestar.edupath.facilitatorstudent.service.FacilitatorStudentService;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.lodestar.edupath.finalsummary.service.FinalSummaryService;
import com.lodestar.edupath.util.ApplicationMenuUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FinalSummaryAction extends BaseAction implements ModelDriven<ReportSummaryBean>
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(FinalSummaryAction.class);
	private ReportSummaryBean	bean				= new ReportSummaryBean();

	public String execute()
	{
		try
		{
			int studentId = getStudentSessionObject().getId();
			FinalSummaryService finalSummaryService = new FinalSummaryService();
			finalSummaryService.doFinalizeSession3(getStudentSessionObject());
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			setSelectedId(SessionTypeEnum.SESSION_SUMMARY_FACI.getSessionName(), sessionMap);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		return showSummary();
	}

	public String showSummary()
	{
		try
		{
			Boolean isReview = (Boolean) request.getSession().getAttribute("isReview");
			int studentId = getStudentSessionObject().getId();
			OUT.debug("Show summary report for student Id: {} and isReviewer : {} ", studentId, isReview);
			bean = new FinalSummaryService().getSummaryReport(studentId, isReview);
			bean.setIsReviewer(isReview);
			
			//start by bharath on 19/9/2019 
			FacilitatorDetailsDTO facilitatorDetailsdto = getfacilitatorDetails();
			FacilitatorDetailsDTO studentfacilitatordto = getfacilitatorDetailsByStudentId();
			if(studentfacilitatordto.getId()!=facilitatorDetailsdto.getId())
			{
				bean.setIsSentForReview(true);
			}
			//end by bharath on 19/9/2019 
			
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String showSummaryForStudent()
	{
		try
		{
			UserSessionObject userSessionObject = getUserSessionObject();
			int userId = userSessionObject.getId();
			OUT.debug("Show summary report for student user Id: {}", userId);
			bean = new FinalSummaryService().getSummaryReportForStudent(userId);
			ApplicationMenuUtils.changeMenu(userSessionObject, ActionContext.getContext().getSession());
		}
		catch (EdupathException e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, getText(e.getMessage()));
			return ERROR;
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String saveComment()
	{
		PrintWriter writer = null;
		JSONObject responsBuilder = new JSONObject();
		try
		{
			writer = response.getWriter();
			int studentId = 0;
			int userId = 0;
			StudentSessionObject studentObject = getStudentSessionObject();
			UserSessionObject userObject = getUserSessionObject();
			OUT.debug("Saving comment : {}, studentId: {}, userId : {}", bean.getComments(), (studentId = studentObject.getId()), (userId = userObject.getId()));
			bean.setStudentId(studentId);
			bean.setUserId(userId);

			new FinalSummaryService().saveComment(bean, studentObject.getFullName(), userObject.getLoginId());
			responsBuilder.put(ApplicationConstants.STATUS, "SUCCESS");
			responsBuilder.put(ApplicationConstants.MESSAGE, getText("com.edupath.summary.report.comment.success.msg"));
		}
		catch (final EdupathException e)
		{
			responsBuilder.put(ApplicationConstants.STATUS, "ERROR");
			String message = getText(e.getMessage(), e.getErrorCode());
			responsBuilder.put(ApplicationConstants.MESSAGE, message);
			OUT.error(ApplicationConstants.EXCEPTION, message);
		}
		catch (Exception e)
		{
			responsBuilder.put(ApplicationConstants.STATUS, "ERROR");
			String message = getText("com.edupath.action.internal.error");
			responsBuilder.put(ApplicationConstants.MESSAGE, message);
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (writer != null)
			{
				writer.write(responsBuilder.toString());
				writer.close();
			}
		}
		return null;
	}

	
	private FacilitatorDetailsDTO getfacilitatorDetails()
	{
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(getUserSessionObject().getId());
		return new FinalSummaryService().getFacilitatorDetailsByUserId(userDetailDTO);
	}
	private FacilitatorDetailsDTO getfacilitatorDetailsByStudentId()
	{
		StudentSessionObject studentSessionObject = getStudentSessionObject();
		int id = studentSessionObject.getId();
		return new FinalSummaryService().getfacilitatorDetailsByStudentId(id);
	}
	@Override
	public ReportSummaryBean getModel()
	{
		return bean;
	}

	public ReportSummaryBean getBean()
	{
		return bean;
	}

	public void setBean(ReportSummaryBean bean)
	{
		this.bean = bean;
	}

}
