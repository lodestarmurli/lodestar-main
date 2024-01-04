package com.lodestar.edupath.sessionfeedback.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.SessionFeedBack.SessionFeedBackFrom;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackAnswerDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackStatusDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTestEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.sessionfeedback.action.SessionFeedBackFromBean;
import com.lodestar.edupath.tum.questionnaire.service.AptitudeService;
import com.lodestar.edupath.tum.questionnaire.service.InterestService;



public class SessionFeedBackFromService {
	private static final Logger	OUT				= LoggerFactory.getLogger(SessionFeedBackFromService.class);
	private static final String	FEED_BACK		= "FEED_BACK";
	private static final String	QUESTION_ANS	= "QUESTION_ANS";
	private static final String	QUESTION_NO		= "QUESTION_NO";
	public void getSessionOneQuestions(SessionFeedBackFromBean sessionformBean,int session) throws Exception
	{
		sessionformBean.setFeedbackQuestions(new SessionFeedBackFrom().getStudentFeedbackFormQuestion(session));
		
	}
	
	
	public void saveSessiononefeedback(SessionFeedBackFromBean sessionformBean, int userId)
	{
		SqlSession sqlSession = null;
		try
		{
			JSONObject feedback = new JSONObject(sessionformBean.getFeedbackAnswered());
			JSONArray feedbackAnswers = feedback.getJSONArray(FEED_BACK);
			StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);

			sqlSession = MyBatisManager.getInstance().getBatchSession();
			JSONObject feedbackAns = null;
			SessionFeedBackAnswerDTO feedbackFormDTO = null;
			SessionFeedBackFrom FeedbackFormDAO = new SessionFeedBackFrom();
			for (int i = 0; i < feedbackAnswers.length(); i++)
			{
				feedbackFormDTO = new SessionFeedBackAnswerDTO();
				feedbackAns = feedbackAnswers.getJSONObject(i);
				feedbackFormDTO.setQuestionNo(feedbackAns.getInt(QUESTION_NO));
				feedbackFormDTO.setAnswer(feedbackAns.getString(QUESTION_ANS));
				feedbackFormDTO.setStudentId(studentDetailsByUserId.getId());
				feedbackFormDTO.setForsession(1);
				//start by bharath on 11-7-2019
				SessionFeedBackAnswerDTO sessionfeedback = FeedbackFormDAO.GetDeatils(feedbackFormDTO);
				if(sessionfeedback==null) {
					FeedbackFormDAO.insertsessionFeedbackForm(sqlSession, feedbackFormDTO);	
				}
				//end by bharath on 11-7-2019
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
	
	
	public void saveSessiontwofeedback(SessionFeedBackFromBean sessionformBean, int userId)
	{
		SqlSession sqlSession = null;
		try
		{
			JSONObject feedback = new JSONObject(sessionformBean.getFeedbackAnswered());
			JSONArray feedbackAnswers = feedback.getJSONArray(FEED_BACK);
			StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);

			sqlSession = MyBatisManager.getInstance().getBatchSession();
			JSONObject feedbackAns = null;
			SessionFeedBackAnswerDTO feedbackFormDTO = null;
			SessionFeedBackFrom FeedbackFormDAO = new SessionFeedBackFrom();
			for (int i = 0; i < feedbackAnswers.length(); i++)
			{
				feedbackFormDTO = new SessionFeedBackAnswerDTO();
				feedbackAns = feedbackAnswers.getJSONObject(i);
				feedbackFormDTO.setQuestionNo(feedbackAns.getInt(QUESTION_NO));
				feedbackFormDTO.setAnswer(feedbackAns.getString(QUESTION_ANS));
				feedbackFormDTO.setStudentId(studentDetailsByUserId.getId());
				feedbackFormDTO.setForsession(2);
				SessionFeedBackAnswerDTO sessionfeedback = FeedbackFormDAO.GetDeatils(feedbackFormDTO);
				//start by bharath on 11-7-2019
				if(sessionfeedback==null) 
				{
					FeedbackFormDAO.insertsessionFeedbackForm(sqlSession, feedbackFormDTO);
				}
				//end by bharath on 11-7-2019
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
	
	
	
	public SessionFeedBackStatusDTO GetSessionFeedBackStatus(int userId)
	{
		//System.out.println("======1===============>"+userId);
		
		SessionFeedBackStatusDTO sessionfeedback=null;
		UserDetailDTO userDetailDTo = null;
		
		try
		{
			SessionFeedBackFrom FeedbackFormDAO = new SessionFeedBackFrom();
			userDetailDTo=FeedbackFormDAO.GetUserDetailsByUserId(userId);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currntdate=format.parse("2017-07-28 00:00:00");
			
			
			
			
			if(userDetailDTo!=null && userDetailDTo.getCreatedOn()!=null && userDetailDTo.getCreatedOn().after(currntdate))
			{
				
				StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
				sessionfeedback=FeedbackFormDAO.GetSessionFeedBackFromDB(studentDetailsByUserId.getId());
				
			}
			else
			{
				sessionfeedback=new SessionFeedBackStatusDTO();
				
				
				sessionfeedback.setSessionOneFeedBackStatus(0);
				sessionfeedback.setSessionTwoFeedBackStatus(0);
				
			}
			
			
			
			
			
			
			
			//System.out.println("======1===============>"+sessionfeedback.getSessionOneFeedBackStatus());
			//System.out.println("======2===============>"+sessionfeedback.getSessionTwoFeedBackStatus());
		}
		catch (Exception e)
		{
			
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return sessionfeedback;
	}
	
	
	
	public boolean GetInteresttestcompletd(int userid) throws Exception
	{
		boolean completed=false;
		
		InterestService		service=new InterestService();
		
		completed=service.isIntrestCompleted(userid);
		
		
		return completed;
		
	}
	
	
	public boolean GetApptitudeTestCompleted(int userid)
	{
		boolean completed=false;
		String				aptitudeComplete=null;
		try
		{
			int pageAction = 0;
			AptitudeService service = new AptitudeService();
			String formatTime = null;
			StudentCGTResult cgtResult = service.getStudentCGTResult(userid);
			JSONArray jsonArray = service.getStudentCGTBySection(userid);
			int					remainigMinuteTime=0;
			int					remainigSecondTime;
			
			
			
			if (null != cgtResult)
			{
				double time = TimeUtil.getTotalMenutes(cgtResult.getRemainigTime());
				DecimalFormat df = new DecimalFormat("#.0");
				formatTime = df.format(time);
				String[] timeStr = formatTime.split("\\.");
				if (!timeStr[0].isEmpty())
				{
					remainigMinuteTime = Integer.valueOf(timeStr[0]);
				}
				remainigSecondTime = Integer.valueOf(timeStr[1]);
				if (remainigMinuteTime < ApplicationConstants.APTITUDE_TEST_TIME || remainigSecondTime > 0)
				{
					aptitudeComplete = StudentTestEnum.STARTED.getValue();
				}
				else
				{
					remainigMinuteTime = 0;
				}
			}
			else if (cgtResult == null)
			{
				remainigMinuteTime = 0;
				aptitudeComplete = StudentTestEnum.STARTED.getValue();
			}
			
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		
		
		if(aptitudeComplete==null)
		{
			completed=true;
		}
		
		
		//System.out.println("=========aptitudeComplete===============>"+aptitudeComplete);
		
		return completed;
		
	}
	
	
	
}
