package com.lodestar.edupath.dataaccessobject.dao.SessionFeedBack;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.IModel;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackAnswerDTO;
import com.lodestar.edupath.datatransferobject.dto.sessionfeedback.SessionFeedBackStatusDTO;



public class SessionFeedBackFrom {
	private static final Logger	OUT	= LoggerFactory.getLogger(SessionFeedBackFrom.class);
	
	public List<IModel> getStudentFeedbackFormQuestion(int session) throws Exception
	{
		OUT.debug("Getting Session one feed back questions");
		List<IModel> feedbackFormQuestionDTOs = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.SessionOneQuestion, session);
		OUT.debug("Number of questions found: {}", feedbackFormQuestionDTOs != null ? feedbackFormQuestionDTOs.size() : 0);
		return feedbackFormQuestionDTOs;
	}
	
	public void insertsessionFeedbackForm(SqlSession sqlSession, SessionFeedBackAnswerDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Inserting Session one feedback questions for student id: {}, questionNo: {}", feedbackFormDTO.getStudentId(), feedbackFormDTO.getQuestionNo());
		if (sqlSession != null)
		{
			sqlSession.insert(MyBatisMappingConstants.SessionFeedBackAnswerInsert, feedbackFormDTO);
		}
		else
		{
			MyBatisManager.getInstance().insert(MyBatisMappingConstants.SessionFeedBackAnswerInsert, feedbackFormDTO);
		}
		OUT.debug("Inserted Session one feedback questions insertid: {} for student id: {}, questionNo: {}", feedbackFormDTO.getId(), feedbackFormDTO.getStudentId(),
				feedbackFormDTO.getQuestionNo());
	}
	
	
	
	public SessionFeedBackStatusDTO GetSessionFeedBackFromDB(int studentid) throws Exception
	{
		OUT.debug("GetingSession feed back for student id: {}, questionNo: {}", studentid);
		
		SessionFeedBackStatusDTO Sessionfeedbackstatus = new SessionFeedBackStatusDTO();
		SessionFeedBackStatusDTO Sessionfeedbackstatus1 = new SessionFeedBackStatusDTO();
		SessionFeedBackStatusDTO Sessionfeedbackstatus2 = new SessionFeedBackStatusDTO();
		Sessionfeedbackstatus1 =(SessionFeedBackStatusDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.SessionOneFeedBackCount, studentid);
		Sessionfeedbackstatus2 =(SessionFeedBackStatusDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.SessionTwoFeedBackCount, studentid);
		
		
		if(Sessionfeedbackstatus1!=null && Sessionfeedbackstatus1.getSessiononefeedbackCount()>0)
		{
			Sessionfeedbackstatus.setSessionOneFeedBackStatus(0);
		}
		else
		{
			Sessionfeedbackstatus.setSessionOneFeedBackStatus(1);
		}
		
		
		if(Sessionfeedbackstatus2!=null && Sessionfeedbackstatus2.getSessiontwofeedbackCount()>0)
		{
			Sessionfeedbackstatus.setSessionTwoFeedBackStatus(0);
		}
		else
		{
			Sessionfeedbackstatus.setSessionTwoFeedBackStatus(1);
		}

		
		
		return Sessionfeedbackstatus;
	}
	
	
	
	
	public UserDetailDTO GetUserDetailsByUserId(int userId) throws Exception
	{
		
		UserDetailDTO userDetailDTo=new UserDetailDTO();
		
		userDetailDTo=(UserDetailDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.Get_User_Details_UserID, userId);
		
		return userDetailDTo;
	}
	
	//start bharath on 11/7/2019
	
	public SessionFeedBackAnswerDTO GetDeatils(SessionFeedBackAnswerDTO feedbackFormDTO) throws Exception
	{
		OUT.debug("Checking for records with feedbackFormDTO:{} ",feedbackFormDTO);
		SessionFeedBackAnswerDTO Sessionfeedback=(SessionFeedBackAnswerDTO) MyBatisManager.getInstance().getResultAsObject(MyBatisMappingConstants.GET_DEATILS, feedbackFormDTO);
		OUT.debug("Result of Checking duplicate values Sessionfeedback:{} ",Sessionfeedback);
		return Sessionfeedback;
		
	}
	//End bharath on 11/7/2019
	
}
