package com.lodestar.edupath.feedbackform.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.parentfeedback.ParentFeedbackFormDAO;
import com.lodestar.edupath.dataaccessobject.dao.parentfeedback.ParentFeedbackFormQuestionsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.studentfeedback.StudentFeedbackFormDAO;
import com.lodestar.edupath.dataaccessobject.dao.studentfeedback.StudentFeedbackFormQuestionsDAO;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.parentfeedback.ParentFeedbackFormDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.studentfeedback.StudentFeedbackFormDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.CommonUtil;
import com.lodestar.edupath.feedbackform.bean.FeedbackFormBean;

public class FeedbackFormService
{
	private static final String	STUDENT			= "STUDENT";
	private static final String	PARENT			= "PARENT";
	private static final String	QUESTION_ANS	= "QUESTION_ANS";
	private static final String	QUESTION_NO		= "QUESTION_NO";
	private static final String	FEED_BACK		= "FEED_BACK";
	private static final Logger	OUT				= LoggerFactory.getLogger(FeedbackFormService.class);

	/**
	 * @param formBean
	 * @return
	 * @throws Exception
	 */
	public void getSessionByStudentUserId(FeedbackFormBean formBean) throws Exception
	{
		SessionScheduleDetailsDTO sessionByStudentUserId = new SessionScheduleDetailsDAO().getSessionByStudentUserId(formBean.getStudentUserId());
		if (sessionByStudentUserId != null)
		{
			formBean.setParentFeedbackStatus(sessionByStudentUserId.getParentFeedback() != null ? true : false);
			formBean.setStudentFeedbackStatus(sessionByStudentUserId.getStudentFeedback() != null ? true : false);
		}

	}

	/**
	 * @param formBean
	 * @throws Exception
	 */
	public void getStudentQuestions(FeedbackFormBean formBean) throws Exception
	{
		formBean.setFeedbackQuestions(new StudentFeedbackFormQuestionsDAO().getStudentFeedbackFormQuestion());

		StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(formBean.getStudentUserId());
		StudentFeedbackFormDTO feedbackFormDTO = new StudentFeedbackFormDTO();
		feedbackFormDTO.setStudentId(studentDetailsByUserId.getId());
		List<IModel> studentFeedbackFormByStudentId = new StudentFeedbackFormDAO().getStudentFeedbackFormByStudentId(feedbackFormDTO);
		if (studentFeedbackFormByStudentId != null)
		{
			formBean.setFeedbackAnswered(CommonUtil.replaceJSONEntities(new JSONArray(studentFeedbackFormByStudentId).toString()));
		}
		setAutoSaveInterval(formBean);
	}

	private void setAutoSaveInterval(FeedbackFormBean formBean) throws Exception
	{
		String propertesValueByName = new GlobalSttingDAO()
				.getPropertesValueByName(ApplicationConstants.GlobalSettings.FEEDBACK_AUTOSAVE_TIMER_IN_SEC.getProperty());
		if (propertesValueByName != null && !propertesValueByName.isEmpty())
		{
			formBean.setAutoSaveInterval(Integer.parseInt(propertesValueByName));
		}
	}

	/**
	 * @param formBean
	 * @throws Exception
	 */
	public void getParentQuestions(FeedbackFormBean formBean) throws Exception
	{
		formBean.setFeedbackQuestions(new ParentFeedbackFormQuestionsDAO().getParentFeedbackFormQuestion());

		StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(formBean.getStudentUserId());
		ParentFeedbackFormDTO feedbackFormDTO = new ParentFeedbackFormDTO();
		feedbackFormDTO.setStudentId(studentDetailsByUserId.getId());
		List<IModel> parentFeedbackFormByStudentId = new ParentFeedbackFormDAO().getParentFeedbackFormByStudentId(feedbackFormDTO);
		if (parentFeedbackFormByStudentId != null)
		{
			formBean.setFeedbackAnswered(CommonUtil.replaceJSONEntities(new JSONArray(parentFeedbackFormByStudentId).toString()));
		}
		setAutoSaveInterval(formBean);
	}

	/**
	 * @param formBean
	 * @param loginId
	 */
	public void saveStudentForm(FeedbackFormBean formBean, String loginId)
	{
		SqlSession sqlSession = null;
		try
		{

			JSONObject feedback = new JSONObject(formBean.getFeedback());
			JSONArray feedbackAnswers = feedback.getJSONArray(FEED_BACK);
			JSONObject feedbackAns = null;
			StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(formBean.getStudentUserId());

			sqlSession = MyBatisManager.getInstance().getSession();
			StudentFeedbackFormDTO feedbackFormDTO = null;
			StudentFeedbackFormDAO studentFeedbackFormDAO = new StudentFeedbackFormDAO();
			for (int i = 0; i < feedbackAnswers.length(); i++)
			{
				feedbackFormDTO = new StudentFeedbackFormDTO();
				feedbackAns = feedbackAnswers.getJSONObject(i);
				feedbackFormDTO.setStudentId(studentDetailsByUserId.getId());
				feedbackFormDTO.setQuestionNo(feedbackAns.getInt(QUESTION_NO));
				feedbackFormDTO.setAnswer(feedbackAns.getString(QUESTION_ANS));
				if (studentFeedbackFormDAO.isAnsweredStudentFeedbackForm(feedbackFormDTO))
				{
					studentFeedbackFormDAO.updateStudentFeedbackForm(sqlSession, feedbackFormDTO);
				}
				else
				{
					studentFeedbackFormDAO.insertStudentFeedbackForm(sqlSession, feedbackFormDTO);
				}
			}
			// addAuditMessage(loginId, sqlSession, feedbackAnswers, studentDetailsByUserId);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
			{
				sqlSession.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param formBean
	 * @param loginId
	 */
	public void saveParentForm(FeedbackFormBean formBean, String loginId)
	{
		SqlSession sqlSession = null;
		try
		{
			JSONObject feedback = new JSONObject(formBean.getFeedback());
			JSONArray feedbackAnswers = feedback.getJSONArray(FEED_BACK);
			StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(formBean.getStudentUserId());

			sqlSession = MyBatisManager.getInstance().getBatchSession();
			JSONObject feedbackAns = null;
			ParentFeedbackFormDTO feedbackFormDTO = null;
			ParentFeedbackFormDAO parentFeedbackFormDAO = new ParentFeedbackFormDAO();
			for (int i = 0; i < feedbackAnswers.length(); i++)
			{
				feedbackFormDTO = new ParentFeedbackFormDTO();
				feedbackAns = feedbackAnswers.getJSONObject(i);
				feedbackFormDTO.setQuestionNo(feedbackAns.getInt(QUESTION_NO));
				feedbackFormDTO.setAnswer(feedbackAns.getString(QUESTION_ANS));
				feedbackFormDTO.setStudentId(studentDetailsByUserId.getId());
				if (parentFeedbackFormDAO.isAnsweredParentFeedbackForm(sqlSession, feedbackFormDTO))
				{
					parentFeedbackFormDAO.updateParentFeedbackForm(sqlSession, feedbackFormDTO);
				}
				else
				{
					parentFeedbackFormDAO.insertParentFeedbackForm(sqlSession, feedbackFormDTO);
				}
				sqlSession.flushStatements();
			}
			// addAuditMessage(loginId, sqlSession, feedbackAnswers, studentDetailsByUserId);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
			{
				sqlSession.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param loginId
	 * @param sqlSession
	 * @param feedbackAnswers
	 * @param studentDetailsByUserId
	 * @param ofType
	 */
	private void addAuditMessage(String loginId, SqlSession sqlSession, String feedbackAnswers, StudentDetailsDTO studentDetailsByUserId, String ofType)
	{
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append(ofType);
		messageBuilder.append(" feedback for student id: ");
		messageBuilder.append(studentDetailsByUserId.getId()).append(" ");
		messageBuilder.append(feedbackAnswers);
		AuditTrailLogger.addAuditInfo(sqlSession, ModuleNameEnum.FEED_BACK, messageBuilder.toString(), loginId);
	}

	/**
	 * @param formBean
	 * @param loginId
	 */
	public void finalizeParentForm(FeedbackFormBean formBean, String loginId)
	{
		SqlSession sqlSession = null;
		try
		{
			StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(formBean.getStudentUserId());
			if (studentDetailsByUserId != null)
			{
				sqlSession = MyBatisManager.getInstance().getSession();
				SessionScheduleDetailsDAO scheduleDetailsDAO = new SessionScheduleDetailsDAO();
				scheduleDetailsDAO.updateParentFeedbackTime(sqlSession, studentDetailsByUserId.getId());
				addAuditMessage(loginId, sqlSession, formBean.getFeedback(), studentDetailsByUserId, PARENT);
				sqlSession.commit();
				getSessionByStudentUserId(formBean);
			}
			else
			{
				throw new EdupathException("com.edupath.action.internal.error", new String[] {});
			}
		}
		catch (Exception e)
		{
			if (sqlSession != null)
			{
				sqlSession.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param formBean
	 * @param loginId
	 */
	public void finalizeStudentForm(FeedbackFormBean formBean, String loginId)
	{
		SqlSession sqlSession = null;
		try
		{
			StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(formBean.getStudentUserId());
			if (studentDetailsByUserId != null)
			{
				sqlSession = MyBatisManager.getInstance().getSession();
				SessionScheduleDetailsDAO scheduleDetailsDAO = new SessionScheduleDetailsDAO();
				scheduleDetailsDAO.updateStudentFeedbackTime(sqlSession, studentDetailsByUserId.getId());
				addAuditMessage(loginId, sqlSession, formBean.getFeedback(), studentDetailsByUserId, STUDENT);
				sqlSession.commit();
				getSessionByStudentUserId(formBean);
			}
			else
			{

			}
		}
		catch (Exception e)
		{
			if (sqlSession != null)
			{
				sqlSession.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}

	}
}
