package com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.RoleTypeEnum;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class SessionScheduleDetailsDAO
{
	private static Logger	OUT	= LoggerFactory.getLogger(SessionScheduleDetailsDAO.class);

	public SessionScheduleDetailsDTO getSessionByUserId(int roleWeight, int userId) throws Exception
	{
		SessionScheduleDetailsDTO sessionScheduleDetailsDTO = new SessionScheduleDetailsDTO();
		if (roleWeight == RoleTypeEnum.USER.getWeight())
		{
			sessionScheduleDetailsDTO = (SessionScheduleDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(
					MyBatisMappingConstants.GET_STUDENT_SESSION_BY_USERID, userId);
		}
		else if (roleWeight == RoleTypeEnum.FACILITATOR.getWeight())
		{
			// sessionScheduleDetailsDTO = (SessionScheduleDetailsDTO)
			// MyBatisManager.getInstance().getResultAsObjectById(
			// MyBatisMappingConstants.GET_FACILITATOR_SESSION_BY_USERID,
			// userId);
		}
		return sessionScheduleDetailsDTO;
	}

	/**
	 * @param studentUserId
	 * @return
	 * @throws Exception
	 */
	public SessionScheduleDetailsDTO getSessionByStudentUserId(int studentUserId) throws Exception
	{
		OUT.debug("Getting student session details by student user id: {}", studentUserId);
		SessionScheduleDetailsDTO sessionScheduleDetailsDTO = (SessionScheduleDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(
				MyBatisMappingConstants.GET_STUDENT_SESSION_BY_USERID, studentUserId);
		OUT.debug("Student session details {} for student user id: {}", sessionScheduleDetailsDTO != null ? " FOUND " : " NOT FOUND ", studentUserId);
		return sessionScheduleDetailsDTO;
	}

	public List<SessionScheduleDetailsDTO> getDetailsByFacilitatorId(int facilitatorId) throws Exception
	{
		List<SessionScheduleDetailsDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_DETAILS_BY_FACILITATORID, facilitatorId);
		return list;
	}

	//Sasedeve change by vyankatesh add int
	
//	public SessionScheduleDetailsDTO insertSessionSchedule(SqlSession session, SessionScheduleDetailsDTO seDetailsDTO) throws Exception
//	{
//		Integer id = -1;
//		id = session.insert(MyBatisMappingConstants.INSERT_SESSION_SCHEDULE_DETAILS, seDetailsDTO);
//		seDetailsDTO.setId(id);
//		return seDetailsDTO;
//	}
	
	public int insertSessionSchedule(SqlSession session, SessionScheduleDetailsDTO seDetailsDTO) throws Exception
	{
		Integer id = -1;
		id = session.insert(MyBatisMappingConstants.INSERT_SESSION_SCHEDULE_DETAILS, seDetailsDTO);
		seDetailsDTO.setId(id);
		return seDetailsDTO.getStudentId();
	}

	//Sasedeve change by vyankatesh add int for stuent id
	public void deleteByStudentId(Integer studentId) throws Exception
	{
		MyBatisManager.getInstance().deleteObjectById(MyBatisMappingConstants.DELETE_SESSION_SCHEDULE_BY_STUDENT_ID, studentId.intValue());
	}

	public int getcountBySessionByDate(int studentId, int facilitatorId, Date date, int key) throws Exception
	{
		Integer count = -1;
		SessionScheduleDetailsDTO sessionScheduleDetailsDTO = new SessionScheduleDetailsDTO();
		sessionScheduleDetailsDTO.setStudentId(studentId);
		sessionScheduleDetailsDTO.setFacilitatorId(facilitatorId);
		String query = "";
		switch (key)
		{
			case 1:
				sessionScheduleDetailsDTO.setSession1Date(date);
				query = MyBatisMappingConstants.UNIQUE_COUNT_BY_SESSION_1_DATE;
				break;
			case 2:
				sessionScheduleDetailsDTO.setSession2Date(date);
				query = MyBatisMappingConstants.UNIQUE_COUNT_BY_SESSION_2_DATE;
				break;
			case 3:
				sessionScheduleDetailsDTO.setSession3Date(date);
				query = MyBatisMappingConstants.UNIQUE_COUNT_BY_SESSION_3_DATE;
				break;
		}
		count = MyBatisManager.getInstance().getUniqueCount(query, sessionScheduleDetailsDTO);
		return count;
	}

	public void updateSessionSchedule(SessionScheduleDetailsDTO seDetailsDTO) throws Exception
	{
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_SESSION_SCHEDULE_BY_STUDENT_ID, seDetailsDTO);

	}

	public void updatePreNSession1Complete(int studentId) throws Exception
	{
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_PRE_N_SESS1_COMPLETE_BY_STUDENT_ID, studentId);
	}

	public SessionScheduleDetailsDTO getSessionDetailsByStudentId(int studentId)
	{
		SessionScheduleDetailsDTO dto = null;
		try
		{
			dto = (SessionScheduleDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(MyBatisMappingConstants.GET_STUDENT_SESSION_Details_BY_STUDENT_ID,
					studentId);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return dto;
	}

	public List<SessionScheduleDetailsDTO> getAllsesionList() throws Exception
	{
		List<SessionScheduleDetailsDTO> sessionList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_SESSION_DETAIL,
				new SessionScheduleDetailsDTO());
		return sessionList;
	}

	public void updatePreANDSessionOne(int studentId) throws Exception
	{
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPADTE_PRE_AND_SESSION_ONE_BY_STUDENT_ID, studentId);
	}

	public void updateSessionTwoThreeIncomplete(SqlSession session, int studentId) throws Exception
	{
		session.update(MyBatisMappingConstants.UPADTE_SESSION_TWO_THREE_INCOMPLETE_BY_STUDENT_ID, studentId);
	}

	public SessionScheduleDetailsDTO getSessionWithFacilitator(int id) throws Exception
	{
		SessionScheduleDetailsDTO scheduleDetailsDTO = (SessionScheduleDetailsDTO) MyBatisManager.getInstance().getResultAsObjectById(
				MyBatisMappingConstants.GET_SESSION_DETAIL_WITH_FACILITATOR, id);
		return scheduleDetailsDTO;
	}

	public void updateSessionTwoByStudentId(int studentId) throws Exception
	{
		OUT.debug("Update student Id:" + studentId);
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_SESSION_TWO_BY_STUDENTID, studentId);
	}

	/**
	 * @param studentId
	 * @throws Exception
	 */
	public void updateSession3FaciCompletedByStudentId(int studentId) throws Exception
	{
		OUT.debug("Updating Session3FaciCompleted for studentId : {}", studentId);
		MyBatisManager.getInstance().update(MyBatisMappingConstants.UPDATE_SESSION3_FACICOMPLETED_BY_STUDENT_ID, studentId);
		OUT.debug("Updated Session3FaciCompleted for studentId : {}", studentId);
	}

	/**
	 * @param session
	 * @param scheduleDetailsDTO
	 */
	public void updateSentForReviewByStudentId(SqlSession session, SessionScheduleDetailsDTO scheduleDetailsDTO)
	{

		OUT.debug("Updating SentForReview for studentId : {}, sentForReview: {}", scheduleDetailsDTO.getStudentId(), scheduleDetailsDTO.isSentForReview());
		session.update(MyBatisMappingConstants.UPDATE_SENT_FOR_REVIEW_BY_STUDENT_ID, scheduleDetailsDTO);
	}

	public void updateReportGeneratedByStudentId(SqlSession session, int studentId)
	{
		OUT.debug("Updating ReportGenerated for studentId : {}", studentId);
		session.update(MyBatisMappingConstants.UPDATE_REPORT_GENERATED_BY_STUDENT_ID, studentId);
	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public boolean chechIsSentForReview(int studentId) throws Exception
	{
		boolean isSentForReview = false;
		OUT.debug("Checking is sent for review by student Id : {}", studentId);
		int uniqueCount = MyBatisManager.getInstance().getUniqueCount(MyBatisMappingConstants.CHECK_IS_SENT_FOR_REVIEW_BY_STUDENT_ID, studentId);
		if (uniqueCount > 0)
		{
			isSentForReview = true;
		}
		OUT.debug("Is sent for review is: {} for student Id : {}", isSentForReview, studentId);
		return isSentForReview;
	}

	public void updateSessionsByStudentId(SqlSession session, SessionScheduleDetailsDTO scheduleDetailsDTO)
	{
		OUT.debug("Updating session schedule details for studentId : {}", scheduleDetailsDTO.getStudentId());
		session.update(MyBatisMappingConstants.UPDATE_SESSIONS_BY_STUDENT_ID, scheduleDetailsDTO);
	}

	public List<StudentDetailsDTO> getFeedbackDetails(StudentDetailsDTO studentDetailsDTO) throws Exception
	{
		List<StudentDetailsDTO> resultList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_FEEDBACK_DETAILS, studentDetailsDTO);
		OUT.debug("Number of feedback details found for {}  is {}", studentDetailsDTO.getFeedbackType(), resultList != null ? resultList.size() : 0);
		return resultList;
	}

	/**
	 * @param session
	 * @param studentId
	 */
	public void updateStudentFeedbackTime(SqlSession session, int studentId)
	{
		OUT.debug("Updating student feedback time student id: {}", studentId);
		session.update(MyBatisMappingConstants.UPDATE_STUDENT_FEED_BACK_TIME, studentId);
		OUT.debug("Updated student feedback time student id: {}", studentId);
	}

	/**
	 * @param session
	 * @param studentId
	 */
	public void updateParentFeedbackTime(SqlSession session, int studentId)
	{
		OUT.debug("Updating parent feedback time student id: {}", studentId);
		session.update(MyBatisMappingConstants.UPDATE_PARENT_FEED_BACK_TIME, studentId);
		OUT.debug("Updated parent feedback time student id: {}", studentId);
	}
	
	//start by bharath on 22/9/2019
	public List<SessionScheduleDetailsDTO> getFacilitatorScheduleById(int facilitatorId) throws Exception
	{
		List<SessionScheduleDetailsDTO> list = null;
		list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_FACILITATOR_SCHEDULE_BY_ID, facilitatorId);
		return list;
	}

	//end by bharath on 22/9/2019
	
	//start by bharath on 14/11/2019
		public List<SessionScheduleDetailsDTO> getStudentStatsReportBySchool(SessionScheduleDetailsDTO sessionScheduleDetailsDTO) throws Exception
		{
			List<SessionScheduleDetailsDTO> list = null;
			list = MyBatisManager.getInstance().getResultList(MyBatisMappingConstants.GET_STUDENT_DETAIL_REPORT_BY_SCHOOL, sessionScheduleDetailsDTO);
			return list;
		}

		//end by bharath on 14/11/2019 
	
}
