package com.lodestar.edupath.finalsummary.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeparameter.StudentCollegeParametersDAO;
import com.lodestar.edupath.dataaccessobject.dao.collegeshortlist.StudentCollegeShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.elective.EduPathShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.elective.SubjectDAO;
import com.lodestar.edupath.dataaccessobject.dao.entranceexams.EntranceExamsDAO;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.integratedcourse.IntegratedCourseDAO;
import com.lodestar.edupath.dataaccessobject.dao.reportcomments.ReportCommentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.review.ReportReviewCommentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorialshortlist.StudentTutorialCentreShortListDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.wishlist.WishListDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.ReportReviewCommentsDTO;
import com.lodestar.edupath.datatransferobject.dto.reportcomments.ReportCommentsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.exception.EdupathException;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.finalsummary.bean.ReportSummaryBean;
import com.lodestar.edupath.studentcart.service.StudentCartDetailService;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class FinalSummaryService
{
	private static final Logger	OUT	= LoggerFactory.getLogger(FinalSummaryService.class);

	public static void doFinalizeSession3(StudentSessionObject studentSessionObject) throws Exception
	{
		new SessionScheduleDetailsDAO().updateSession3FaciCompletedByStudentId(studentSessionObject.getId());

		// email
		sendNotification(studentSessionObject);
	}

	private static void sendNotification(StudentSessionObject studentSessionObject) throws Exception
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.SESSION3_COMPLETED.name();
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setLoginId(studentSessionObject.getLoginId());
		UserDetailDTO userdetails = new UserDetailsDAO().getUserDetailByLoginId(userDetailDTO);
		String password = new String(AESCipher.decrypt(userdetails.getPassword()));
		parameterMap.put("loginId", userdetails.getLoginId());
		parameterMap.put("userPassword", password);
		PasswordGeneratorService.sendNewNotification(parameterMap, studentSessionObject.getFullName(), studentSessionObject.getEmailId(),
				userdetails.getRoleTypeId(), tempalteName, "");
	}

	/**
	 * @param studentId
	 * @param isReviewer
	 * @return
	 * @throws Exception
	 */
	public ReportSummaryBean getSummaryReport(int studentId, Boolean isReviewer) throws Exception
	{
		OUT.debug("Getting summary report details for studentId: {}", studentId);
		ReportSummaryBean summaryBean = new ReportSummaryBean();
		summaryBean.setStudentId(studentId);
		summaryBean.setIsReviewer(isReviewer);
		// Section 1: Get Profile of student
		summaryBean.setSection1(getStudentDetails(studentId));
		// Section 2: Occupation and industry from wish list
		summaryBean.setSection2OccNInd(new WishListDAO().getOccNIndDetailsByStudentId(studentId));
		// Section 2: Course read
		SubjectDAO subjectDAO = new SubjectDAO();
		summaryBean.setSection2CourseRead(subjectDAO.getSubjectListByStudentId(studentId));
		// Section 2: College parameters
		summaryBean.setSelectedParam(new StudentCollegeParametersDAO().getStudentCollegeParametersByStudentId(studentId));
		// Section 3: EduPath
		summaryBean.setSection3Edupath(new StudentCartDetailService().getEdupathShortList(studentId));
		// Section 3 : Occupation and industry from eduPath
		summaryBean.setSection3OccNInd(new EduPathShortListDAO().getOccNIndustryDetail(studentId));
		// Section 3 : Stream and elective
		summaryBean.setSection3StreamNElective(subjectDAO.getSubStreamCombinationByStudentId(studentId));
		// Section 3 : Exam
		summaryBean.setSection3Exams(new EntranceExamsDAO().getShortListExamByStudentId(studentId));
		// Section 3 : Course
		summaryBean.setSection3Courses(new IntegratedCourseDAO().getShortListIntegratedCourseByStudentId(studentId));
		// Section 3 : Tutorial shortList
		summaryBean.setSection3Tutorials(new StudentTutorialCentreShortListDAO().getShortListedTutorialCentersByStudentId(studentId));
		// Section 3: College shortList
		summaryBean.setSection3Colleges(new StudentCollegeShortListDAO().getShortListedCollegeByStudentId(studentId));
		// Setting is sent for review
		summaryBean.setIsSentForReview(new SessionScheduleDetailsDAO().chechIsSentForReview(studentId));
		setCommments(summaryBean);
		return summaryBean;
	}

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ReportSummaryBean getSummaryReportForStudent(int userId) throws Exception
	{
		StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		if (studentDetailsByUserId == null)
		{
			throw new EdupathException("com.edupath.action.internal.error", new String[] {});
		}
		SessionScheduleDetailsDTO sessionByStudent = new SessionScheduleDetailsDAO().getSessionByStudentUserId(userId);
		if (!sessionByStudent.isReportGenerated())
		{
			ReportSummaryBean summaryReport = new ReportSummaryBean();
			summaryReport.setUserId(userId);
			summaryReport.setIsReportGenerated(false);
			return summaryReport;
		}
		int studentId = studentDetailsByUserId.getId();
		ReportSummaryBean summaryReport = getSummaryReport(studentId, false);
		summaryReport.setIsStudent(true);
		return summaryReport;
	}
	
	
	//start bharath on 24-05-2019
	public ReportSummaryBean getSummaryReportForfacilitator(int userId) throws Exception
	{
		StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		if (studentDetailsByUserId == null)
		{
			throw new EdupathException("com.edupath.action.internal.error", new String[] {});
		}
		int studentId = studentDetailsByUserId.getId();
		ReportSummaryBean summaryReport = getSummaryReport(studentId, false);
		summaryReport.setIsStudent(true);
		return summaryReport;
	}
	//end bharath on 24-05-2019
	
	
	
	public boolean checkFinalFeedBackAvailable(int userId) throws Exception
	{
		StudentDetailsDTO studentDetailsByUserId = new StudentDetailsDAO().getStudentDetailsByUserId(userId);
		if (studentDetailsByUserId == null)
		{
			throw new EdupathException("com.edupath.action.internal.error", new String[] {});
		}
		SessionScheduleDetailsDTO sessionByStudent = new SessionScheduleDetailsDAO().getSessionByStudentUserId(userId);
		if (sessionByStudent!=null && sessionByStudent.getStudentFeedback()!=null && sessionByStudent.getParentFeedback() !=null)
		{
			
			return true;
		}
		
		return false;
	}
	
	
	

	/**
	 * @param summaryBean
	 * @throws Exception
	 */
	private void setCommments(ReportSummaryBean summaryBean) throws Exception
	{
		ReportReviewCommentsDTO reportReviewCommentsDTO = new ReportReviewCommentsDAO().getCommentByStudentId(summaryBean.getStudentId());
		ReportCommentsDTO commentsDTO = new ReportCommentsDTO();
		commentsDTO.setStudentId(summaryBean.getStudentId());
		ReportCommentsDTO reportComment = new ReportCommentsDAO().getReportComment(commentsDTO);
		if (summaryBean.getIsReviewer() != null && summaryBean.getIsReviewer())
		{
			if (reportReviewCommentsDTO != null)
			{
				summaryBean.setComments(reportReviewCommentsDTO.getReview());
			}
			if (reportComment != null)
			{
				summaryBean.setReviewComment(reportComment.getComments());
				
				
				if(reportComment.getChallenges()!=null)
				{
					summaryBean.setReviewchallenges(reportComment.getChallenges());
				}
				
				if(reportComment.getApproachtopathway1()!=null)
				{
					summaryBean.setReviewapproachtopathway1(reportComment.getApproachtopathway1());
				}
				
				if(reportComment.getApproachtopathway2()!=null)
				{
					summaryBean.setReviewapproachtopathway2(reportComment.getApproachtopathway2());
				}
				
				if(reportComment.getFinalnotes()!=null)
				{
					summaryBean.setReviewfinalnotes(reportComment.getFinalnotes());
				}
				
				if(reportComment.getSupplementaryinformation()!=null)
				{
					summaryBean.setReviewsupplementaryinformation(reportComment.getSupplementaryinformation());
				}
				
				
				
				
				
			}
		}
		else
		{
			if (reportReviewCommentsDTO != null)
			{
				summaryBean.setReviewComment(reportReviewCommentsDTO.getReview());
			}
			if (null != reportComment)
			{
				summaryBean.setComments(reportComment.getComments());
				
				if(reportComment.getChallenges()!=null)
				{
					summaryBean.setChallenges(reportComment.getChallenges());
				}
				
				if(reportComment.getApproachtopathway1()!=null)
				{
					summaryBean.setApproachtopathway1(reportComment.getApproachtopathway1());
				}
				
				if(reportComment.getApproachtopathway2()!=null)
				{
					summaryBean.setApproachtopathway2(reportComment.getApproachtopathway2());
				}
				
				if(reportComment.getFinalnotes()!=null)
				{
					summaryBean.setFinalnotes(reportComment.getFinalnotes());
				}
				
				if(reportComment.getSupplementaryinformation()!=null)
				{
					summaryBean.setSupplementaryinformation(reportComment.getSupplementaryinformation());
				}
				
				
				
			}
		}
	}

	/**
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	private StudentDetailsDTO getStudentDetails(int studentId) throws Exception
	{
		StudentDetailsDTO studentDetailsDTO = new StudentDetailsDAO().getStudentFaciNSessionDetailsById(studentId);
		studentDetailsDTO.setSession1DateStr(TimeUtil.convertDateToString(studentDetailsDTO.getSession1Date(), TimeUtil.SUMMARY_REPORT_FORMAT));
		studentDetailsDTO.setSession2DateStr(TimeUtil.convertDateToString(studentDetailsDTO.getSession2Date(), TimeUtil.SUMMARY_REPORT_FORMAT));
		if(studentDetailsDTO.getSession3Date() != null) {
			studentDetailsDTO.setSession3DateStr(TimeUtil.convertDateToString(studentDetailsDTO.getSession3Date(), TimeUtil.SUMMARY_REPORT_FORMAT));
		}
		return studentDetailsDTO;
	}

	/**
	 * @param bean
	 * @throws Exception
	 */
	public void saveComment(ReportSummaryBean bean, String studentFullName, String userLoginId) throws Exception
	{
		validateComment(bean.getComments());
		SqlSession session = null;
		try
		{
			// Getting facilitator id by user id
			FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
			UserDetailDTO userDetailDTO = new UserDetailDTO();
			userDetailDTO.setId(bean.getUserId());
			FacilitatorDetailsDTO facilitatorDetailsDTO = facilitatorDetailsDAO.getFacilitatorDetailsByUserId(userDetailDTO);

			session = MyBatisManager.getInstance().getSession();
			// insert/update comment
			ReportCommentsDTO commentsDTO = new ReportCommentsDTO();

			commentsDTO.setStudentId(bean.getStudentId());
			commentsDTO.setComments(bean.getComments());
			
			//Start SASEDEVE Edited By Mrutyunjaya on Date 06-06-2017
			
			commentsDTO.setChallenges(bean.getChallenges());
			commentsDTO.setApproachtopathway1(bean.getApproachtopathway1());
			commentsDTO.setApproachtopathway2(bean.getApproachtopathway2());
			commentsDTO.setFinalnotes(bean.getFinalnotes());
			commentsDTO.setSupplementaryinformation(bean.getSupplementaryinformation());
			
			//END SASEDEVE Edited By Mrutyunjaya on Date 06-06-2017
			
			
			// Checking whether already commented
			ReportCommentsDAO reportCommentsDAO = new ReportCommentsDAO();
			ReportCommentsDTO reportComment = reportCommentsDAO.getReportComment(commentsDTO);

			commentsDTO.setFacilitatorId(facilitatorDetailsDTO.getId());
			if (reportComment == null)
			{
				reportCommentsDAO.insertReportComment(session, commentsDTO);
			}
			else
			{
				reportCommentsDAO.updateReportComment(session, commentsDTO);
			}
			// Update sent foe review
			SessionScheduleDetailsDTO scheduleDetailsDTO = new SessionScheduleDetailsDTO();
			scheduleDetailsDTO.setStudentId(bean.getStudentId());
			scheduleDetailsDTO.setSentForReview(true);
			new SessionScheduleDetailsDAO().updateSentForReviewByStudentId(session, scheduleDetailsDTO);
			// Audit for summary counsellor commented
			AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.SUMMARY, "Counsellor commented for student : " + studentFullName, userLoginId);
			session.commit();
		}
		catch (final Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			throw e;
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	/**
	 * @param comment
	 */
	public void validateComment(String comment)
	{
		if (comment == null || comment.isEmpty())
		{
			throw new EdupathException("com.edupath.summary.report.comment.required.error", new String[] {});
		}
	}

	
	//Start SASEDEVE Edited By Mrutyunjaya on Date 11-05-2018
	
	public void AutosaveComment_GS(ReportCommentsDTO commentsDTO,int userid) throws Exception
	{
		SqlSession session = null;
		try
		{
			// Getting facilitator id by user id
			FacilitatorDetailsDAO facilitatorDetailsDAO = new FacilitatorDetailsDAO();
			UserDetailDTO userDetailDTO = new UserDetailDTO();
			userDetailDTO.setId(userid);
			FacilitatorDetailsDTO facilitatorDetailsDTO = facilitatorDetailsDAO.getFacilitatorDetailsByUserId(userDetailDTO);
			session = MyBatisManager.getInstance().getSession();
			
			
			ReportCommentsDAO reportCommentsDAO = new ReportCommentsDAO();
			ReportCommentsDTO reportComment = reportCommentsDAO.getReportComment(commentsDTO);
			commentsDTO.setFacilitatorId(facilitatorDetailsDTO.getId());
			
			if (reportComment == null)
			{
				reportCommentsDAO.insertReportComment(session, commentsDTO);
			}
			else
			{
				commentsDTO.setId(reportComment.getId());
				reportCommentsDAO.updateReportComment(session, commentsDTO);
			}
			
			session.commit();
		}
		catch (final Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			throw e;
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	
	
	//END SASEDEVE Edited By Mrutyunjaya on Date 11-05-2018
	
	//start by bharath on 19/9/2019
	public FacilitatorDetailsDTO getFacilitatorDetailsByUserId(UserDetailDTO userDetailDTO)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = null;
		try
		{
			facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorDetailsByUserId(userDetailDTO);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorDetailsDTO;
	}
	
	public FacilitatorDetailsDTO getfacilitatorDetailsByStudentId(int id)
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = null;
		try
		{
			facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorByStudentId(id);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorDetailsDTO;
	}
	//end by bharath on 19/9/2019
	
	
}
