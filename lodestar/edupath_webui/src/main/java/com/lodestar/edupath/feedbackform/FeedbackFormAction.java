package com.lodestar.edupath.feedbackform;

import java.io.PrintWriter;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.base.BaseAction;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.feedbackform.bean.FeedbackFormBean;
import com.lodestar.edupath.feedbackform.service.FeedbackFormService;
import com.opensymphony.xwork2.ModelDriven;

public class FeedbackFormAction extends BaseAction implements ModelDriven<FeedbackFormBean>
{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	OUT					= LoggerFactory.getLogger(FeedbackFormAction.class);

	private FeedbackFormBean	formBean			= new FeedbackFormBean();

	@Override
	public String execute()
	{
		try
		{
			int studentUserId = 0;
			OUT.debug("Redirect to session 3 welcome screen userId: {}", (studentUserId = getUserSessionObject().getId()));
			formBean.setStudentUserId(studentUserId);
			new FeedbackFormService().getSessionByStudentUserId(formBean);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String showStudent()
	{
		try
		{
			int studentUserId = 0;
			OUT.debug("Show student feedback form studentId: {}", (studentUserId = getUserSessionObject().getId()));
			formBean.setStudentUserId(studentUserId);
			new FeedbackFormService().getStudentQuestions(formBean);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		return "ShowStudentForm";
	}

	public String showParent()
	{
		try
		{
			int studentUserId = 0;
			OUT.debug("Show parent feedback form studentId: {}", (studentUserId = getUserSessionObject().getId()));
			formBean.setStudentUserId(studentUserId);
			new FeedbackFormService().getParentQuestions(formBean);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return ERROR;
		}
		return "ShowParentForm";
	}

	public String saveStudentForm()
	{
		try
		{
			int userId = 0;
			UserSessionObject userSessionObject = getUserSessionObject();
			OUT.debug("Saving student feedback for studentId :{}", (userId = userSessionObject.getId()));
			formBean.setStudentUserId(userId);
			new FeedbackFormService().saveStudentForm(formBean, userSessionObject.getLoginId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String finalizeStudentForm()
	{
		PrintWriter printWriter = null;
		JSONObject respJSON = new JSONObject();
		try
		{
			printWriter = response.getWriter();
			int userId = 0;
			UserSessionObject userSessionObject = getUserSessionObject();
			OUT.debug("Finalizing student feedback for studentId :{}", (userId = userSessionObject.getId()));
			formBean.setStudentUserId(userId);
			new FeedbackFormService().finalizeStudentForm(formBean, userSessionObject.getLoginId());
			respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
			respJSON.put("PARENT_ALREADY_FILLED", formBean.getParentFeedbackStatus());
		}
		catch (Exception e)
		{
			if (printWriter != null)
			{
				respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.FAILURE);
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (printWriter != null)
			{
				printWriter.write(respJSON.toString());
				printWriter.close();
			}
		}
		return null;
	}

	public String saveParentForm()
	{
		try
		{
			int userId = 0;
			UserSessionObject userSessionObject = getUserSessionObject();
			OUT.debug("Saving parent feedback for studentId :{}", (userId = userSessionObject.getId()));
			formBean.setStudentUserId(userId);
			new FeedbackFormService().saveParentForm(formBean, userSessionObject.getLoginId());
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String finalizeParentForm()
	{
		PrintWriter printWriter = null;
		JSONObject respJSON = new JSONObject();
		try
		{
			printWriter = response.getWriter();
			int userId = 0;
			UserSessionObject userSessionObject = getUserSessionObject();
			OUT.debug("Finalizing student feedback for studentId :{}", (userId = userSessionObject.getId()));
			formBean.setStudentUserId(userId);
			new FeedbackFormService().finalizeParentForm(formBean, userSessionObject.getLoginId());
			respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
			respJSON.put("STUDENT_ALREADY_FILLED", formBean.getStudentFeedbackStatus());
		}
		catch (EdupathException e)
		{
			if (printWriter != null)
			{
				respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.FAILURE);
			}
			OUT.error(ApplicationConstants.EXCEPTION, getText(e.getMessage()));
		}
		catch (Exception e)
		{
			if (printWriter != null)
			{
				respJSON.put(ApplicationConstants.STATUS, ApplicationConstants.FAILURE);
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (printWriter != null)
			{
				printWriter.write(respJSON.toString());
				printWriter.close();
			}
		}
		return null;
	}

	@Override
	public FeedbackFormBean getModel()
	{
		return formBean;
	}

}
