package com.lodestar.edupath.review.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.StudentSessionObject;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.review.ReportReviewCommentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.ReportReviewCommentsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class ReviewService
{

	private static final Logger	OUT	= LoggerFactory.getLogger(ReviewService.class);

	public List<StudentDetailsDTO> getStudentSessionDetails()
	{
		List<StudentDetailsDTO> studentDetailsList = new ArrayList<StudentDetailsDTO>();
		try
		{
			StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
			studentDetailsDTO.setSentForReview(true);
			studentDetailsDTO.setReportGenerated(false);
			List<StudentDetailsDTO> studentDetailsList1 = new StudentDetailsDAO().getStudentSessionDetailsForReviewByFacilitatorId(studentDetailsDTO);
			for (StudentDetailsDTO studentDetailsDTO2 : studentDetailsList1)
			{
				if (studentDetailsDTO2.getSession1Date() != null)
				{
					String sessionDate1 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession1Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession1DateStr(sessionDate1);
				}
				if (studentDetailsDTO2.getSession2Date() != null)
				{
					String sessionDate2 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession2Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession2DateStr(sessionDate2);
				}
				if (studentDetailsDTO2.getSession3Date() != null)
				{
					String sessionDate3 = TimeUtil.convertTimeAsString(studentDetailsDTO2.getSession3Date().getTime(), TimeUtil.REPORT_DATE_FORMAT);
					studentDetailsDTO2.setSession3DateStr(sessionDate3);
				}
				studentDetailsList.add(studentDetailsDTO2);
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		Collections.sort(studentDetailsList, new SessionDateComparator());
		return studentDetailsList;
	}

	class SessionDateComparator implements Comparator<StudentDetailsDTO>
	{
		@Override
		public int compare(StudentDetailsDTO o1, StudentDetailsDTO o2)
		{
			int session3Day=-1;
			int session1Day = (int) ((o1.getSession1Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			int session2Day = (int) ((o1.getSession2Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			if(o1.getSession3Date() != null) {
				session3Day = (int) ((o1.getSession3Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			}
			if (session1Day < 0)
			{
				session1Day = Integer.MAX_VALUE;
			}
			if (session2Day < 0)
			{
				session2Day = Integer.MAX_VALUE;
			}
			if (session3Day < 0)
			{
				session3Day = Integer.MAX_VALUE;
			}

			Integer date1 = Math.min(session1Day, Math.min(session2Day, session3Day));

			session1Day = (int) ((o2.getSession1Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			session2Day = (int) ((o2.getSession2Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			if(o2.getSession3Date() != null) {
				session3Day = (int) ((o2.getSession3Date().getTime() - System.currentTimeMillis()) / (24 * 60 * 60 * 1000));
			}

			if (session1Day < 0)
			{
				session1Day = Integer.MAX_VALUE;
			}
			if (session2Day < 0)
			{
				session2Day = Integer.MAX_VALUE;
			}
			if (session3Day < 0)
			{
				session3Day = Integer.MAX_VALUE;
			}

			Integer date2 = Math.min(session1Day, Math.min(session2Day, session3Day));

			return date1.compareTo(date2);
		}
	}

	public FacilitatorDetailsDTO getFacilitatorDetailsByUserId(int id) throws Exception
	{
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(id);
		return new FacilitatorDetailsDAO().getFacilitatorDetailsByUserId(userDetailDTO);
	}

	public void insertReportReviewComments(ReportReviewCommentsDTO reportReviewCommentsDTO, String userLoginId, StudentSessionObject studentSessionObject)
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			ReportReviewCommentsDTO reviewCommentsDTO = new ReportReviewCommentsDAO().getCommentByStudentId(reportReviewCommentsDTO.getStudentId());
			if (null != reviewCommentsDTO)
			{
				new ReportReviewCommentsDAO().update(session, reportReviewCommentsDTO);
			}
			else
			{
				new ReportReviewCommentsDAO().insert(session, reportReviewCommentsDTO);
			}
			SessionScheduleDetailsDTO scheduleDetailsDTO = new SessionScheduleDetailsDTO();
			scheduleDetailsDTO.setStudentId(reportReviewCommentsDTO.getStudentId());
			scheduleDetailsDTO.setSentForReview(false);
			new SessionScheduleDetailsDAO().updateSentForReviewByStudentId(session, scheduleDetailsDTO);
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.SUMMARY, "Student : " + studentSessionObject.getLoginId() + " is sent back and COMMENTS - "
					+ reportReviewCommentsDTO.getReview(), userLoginId);
			sendSentBackNotification(studentSessionObject);
			session.commit();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (null != session)
			{
				session.rollback();
			}
		}
		finally
		{
			if (null != session)
			{
				session.close();
			}
		}

	}

	//Start SASEDEVE Edited By Mrutyunjaya on Date 11-05-2018
	
	public void AutoSaveReportReviewComments(ReportReviewCommentsDTO reportReviewCommentsDTO)
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			ReportReviewCommentsDTO reviewCommentsDTO = new ReportReviewCommentsDAO().getCommentByStudentId(reportReviewCommentsDTO.getStudentId());
			if (null != reviewCommentsDTO)
			{
				new ReportReviewCommentsDAO().update(session, reportReviewCommentsDTO);
			}
			else
			{
				new ReportReviewCommentsDAO().insert(session, reportReviewCommentsDTO);
			}
			
			session.commit();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (null != session)
			{
				session.rollback();
			}
		}
		finally
		{
			if (null != session)
			{
				session.close();
			}
		}

	}
	
	
	//END SASEDEVE Edited By Mrutyunjaya on Date 11-05-2018
	
	
	private void sendSentBackNotification(StudentSessionObject studentSessionObject) throws Exception
	{
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO().getFacilitatorByStudentId(studentSessionObject.getId());
		UserRoleDTO roleDTO = new UserRoleDTO();
		roleDTO.setName(UserTypeEnum.FACILITATOR.getDisplayName());
		roleDTO = new UserRoleDAO().getUserRoleByName(roleDTO);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.REPORT_SENT_BACK.name();
		parameterMap.put("loginId", studentSessionObject.getLoginId());
		PasswordGeneratorService.sendNewNotification(parameterMap, studentSessionObject.getFullName(), facilitatorDetailsDTO.getEmailId(), roleDTO.getRoleTypeId(),
				tempalteName, "");
	}

	public void finalizeReportReviewComments(ReportReviewCommentsDTO reportReviewCommentsDTO, String userLoginId, StudentSessionObject studentSessionObject)
	{
		SqlSession session = null;
		try
		{
			session = MyBatisManager.getInstance().getSession();
			new SessionScheduleDetailsDAO().updateReportGeneratedByStudentId(session, reportReviewCommentsDTO.getStudentId());
			AuditTrailLogger.addAuditInfo(ModuleNameEnum.SUMMARY, "Student : " + studentSessionObject.getFullName() + " is reviewed and finalized", userLoginId);

			// email
			sendFinalizeNotification(studentSessionObject);
			session.commit();
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (null != session)
			{
				session.rollback();
			}
		}
		finally
		{
			if (null != session)
			{
				session.close();
			}
		}
	}

	private void sendFinalizeNotification(StudentSessionObject studentSessionObject) throws Exception
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.REPORT_READY.name();
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setLoginId(studentSessionObject.getLoginId());
		UserDetailDTO userdetails = new UserDetailsDAO().getUserDetailByLoginId(userDetailDTO);
		String password = new String(AESCipher.decrypt(userdetails.getPassword()));
		parameterMap.put("loginId", userdetails.getLoginId());
		parameterMap.put("userPassword", password);
		PasswordGeneratorService.sendNewNotification(parameterMap, studentSessionObject.getFullName(), studentSessionObject.getEmailId(),
				userdetails.getRoleTypeId(), tempalteName, "");
	}

}
