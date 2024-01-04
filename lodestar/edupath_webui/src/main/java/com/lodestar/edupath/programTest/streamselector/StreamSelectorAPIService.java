package com.lodestar.edupath.programTest.streamselector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.APIS.dailyHunt.DHStudentCGTResultDAO;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.streamselector.RiasecStreamSelectorDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.TrialStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.APIS.dailyHunt.DHStudentCGTResultDTO;
import com.lodestar.edupath.datatransferobject.dto.cgt.StudentCGTResult;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.programTest.streamselector.StreamSelectorStudentDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentCGTDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.VenueTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.util.PasswordGeneratorService;

public class StreamSelectorAPIService {
	
	
	private static final Logger OUT = LoggerFactory.getLogger(StreamSelectorAPIService.class);
	private String auditDateStr;

	public EActionStatus insertStudent(StudentDetailsDTO studentDTO ) {
		OUT.debug("inside StreamSelectorAPIService studentDTO:{}",studentDTO);
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		 
		Double dueAmount = null; 
	 
		try {
			session = MyBatisManager.getInstance().getSession();

			int lastUserDetailId = new UserDetailsDAO().lastUserDetailId();
			studentLoginId = new StringBuilder();
			studentLoginId.append("LD").append(++lastUserDetailId);
			if (null != studentLoginId.toString() && !studentLoginId.toString().isEmpty()) {
				UserRoleDTO roleDTO = new UserRoleDTO();
				roleDTO.setName(UserTypeEnum.USER.getDisplayName());

				UserRoleDAO roleDAO = new UserRoleDAO();
				roleDTO = roleDAO.getUserRoleByName(roleDTO);

				UserDetailDTO userDetailDTO = new UserDetailDTO();
				String password = PasswordGeneratorService.getRandomAlphanumeric();
				userDetailDTO.setLoginId(studentLoginId.toString());
				userDetailDTO.setPassword(AESCipher.encrypt(password.getBytes()));
				userDetailDTO.setRoleId(roleDTO.getId());
				userDetailDTO.setUserType(UserTypeEnum.USER.getDisplayName());
				userDetailDTO.setIsActive("Y");

				// Insert User Details
				int userId = new UserDetailsDAO().insertUserDetail(session, userDetailDTO);

				if (userId < 0) {
					return EActionStatus.FAILURE;
				}
				// Insert Student Detail

				TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
				TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
				TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
				TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
				TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
				TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
				TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
				TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());

				studentDTO.setUserId(userId);
				studentDTO.setSource(studentDTO.getSource());

//				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
//					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
//					studentDTO.setFathername(studentDTO.getMotherName());
//				}
//				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
//					studentDTO.setContactNumber(studentDTO.getMothercontactno());
//				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				PaymentDTO paymentDTO = new PaymentDTO();

				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty()) {
					seDetailsDTO.setVenue(studentDTO.getVenue());
				} else {
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}

				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = true;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());

					paymentDTO.setLoginId(studentLoginId.toString());
					paymentDTO.setStudentId(studentId);
					paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
					paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
					paymentDTO.setDueAmount(String.valueOf(dueAmount));
					new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
				}
				

				boolean isNew = true;

				StringBuilder headerMessage = new StringBuilder();
				headerMessage.append("Your Session Details are as below");
				StreamSelectorStudentDTO streamSelectorStudentDTO = new StreamSelectorStudentDTO();
				streamSelectorStudentDTO.setStudentID(studentDetailsDTO.getId());
				streamSelectorStudentDTO.setLDID(studentLoginId.toString());
				streamSelectorStudentDTO.setParentContactNo(studentDTO.getFathercontactno());
				streamSelectorStudentDTO.setParentEmailID(studentDTO.getFatherEmailId());
				streamSelectorStudentDTO.setStudentContactNo(studentDTO.getStudentcontactNumber());
				streamSelectorStudentDTO.setStudentEmailID(studentDTO.getStudentemailId());
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
//				streamSelectorStudentDTO.setStudentRegisterTime(formatter.format(date));
				RiasecStreamSelectorDAO riasecStreamSelectorDAO=new RiasecStreamSelectorDAO();
				int result = riasecStreamSelectorDAO.insertStreamSelectorStudent(session,streamSelectorStudentDTO);

				sendNotification(studentDetailsDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
							studentLoginId.toString(), password, isNew, headerMessage.toString(), isTrial);
			

				
				
				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				// EActionStatus.
				return EActionStatus.SUCCESS;
			}
			// }

		} catch (Exception e) {
			if (session != null) {
				session.rollback();
			}
			OUT.error(ApplicationConstants.EXCEPTION, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return EActionStatus.FAILURE;
	}

	
	
	private void setSessionSchedule(StudentDetailsDTO studentDTO, SessionScheduleDetailsDTO seDetailsDTO)
			throws ParseException {
		seDetailsDTO
				.setSession1Date(TimeUtil.getDate(studentDTO.getSession1DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
		seDetailsDTO
				.setSession2Date(TimeUtil.getDate(studentDTO.getSession2DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
		if ((studentDTO.getClassId() == 1 || studentDTO.getClassId() == 2) && studentDTO.getSession3DateStr()!=null) {
			seDetailsDTO.setSession3Date(
					TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
		}
	}

	

	private void sendNotification(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,
			int roleTypeId, String loginId, String password, boolean isNew, String headerMessage, boolean isTrial)
			throws Exception {
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO()
				.getFacilitatorDetailsById(seDetailsDTO.getFacilitatorId());
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT.name();
		String tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT.name();
		parameterMap.put("parentName", studentDetailsDTO.getFathername());


		parameterMap.put("studentname", studentDetailsDTO.getName());

		if (isNew) {
			parameterMap.put("loginId", loginId);
			parameterMap.put("userPassword", password);

			if (studentDetailsDTO != null && studentDetailsDTO.getClassId() == 5) {
				tempalteName = NotificationConstant.MessageTemplateNameAndType.StreamSelector.name();
				 
			} else if (studentDetailsDTO != null
					&& (studentDetailsDTO.getClassId() == 3 || studentDetailsDTO.getClassId() == 4)) {
				tempalteName = NotificationConstant.MessageTemplateNameAndType.StreamSelector.name();
				 
			} else {
				tempalteName = NotificationConstant.MessageTemplateNameAndType.StreamSelector.name();
				 
			}

		}


		if (isTrial) {

			tempalteName = NotificationConstant.MessageTemplateNameAndType.StreamSelector.name();
			tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.StreamSelector.name();
		}
		parameterMap.put("headerMessage", headerMessage);
		if (StudentTypeEnum.FULL == studentDetailsDTO.getStudentType()) {
			parameterMap.put("session1Date",
					TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), TimeUtil.EMAIL_FORMAT) + " at "
							+ TimeUtil.getAMOrPM(seDetailsDTO.getSession1Date(), TimeUtil.AM_PM));
			parameterMap.put("session2Date",
					TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TimeUtil.EMAIL_FORMAT) + " at "
							+ TimeUtil.getAMOrPM(seDetailsDTO.getSession2Date(), TimeUtil.AM_PM));
			if((studentDetailsDTO.getClassId()==1 ||studentDetailsDTO.getClassId()==2) && seDetailsDTO.getSession3Date()!=null) {
				parameterMap.put("session3Date",
						TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TimeUtil.EMAIL_FORMAT) + " at "
								+ TimeUtil.getAMOrPM(seDetailsDTO.getSession2Date(), TimeUtil.AM_PM));
			}
			parameterMap.put("session3Date",
					TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TimeUtil.EMAIL_FORMAT) + " at "
							+ TimeUtil.getAMOrPM(seDetailsDTO.getSession2Date(), TimeUtil.AM_PM));
			parameterMap.put("facilitatorName", facilitatorDetailsDTO.getName());
			parameterMap.put("faciliatorEmail", facilitatorDetailsDTO.getEmailId());
			parameterMap.put("faciliatorPhone", facilitatorDetailsDTO.getPhoneNumber());

			if (seDetailsDTO.getVenue().equals(VenueTypeEnum.ATSCHOOL)) {
				parameterMap.put("venue", VenueTypeEnum.AT_SCHOOL_EMAIL);
			} else if (seDetailsDTO.getVenue().equals(VenueTypeEnum.ATOFFICE.getValue())) {
				GlobalSttingDAO globalDAO = new GlobalSttingDAO();
				GlobalSettingDTO globalDTO = new GlobalSettingDTO();
				globalDTO.setPropertyName(
						ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_LODESTAR_ADDRESS.getProperty());
				globalDTO = globalDAO.getPropertesValueByName(globalDTO);

				parameterMap.put("venue", globalDTO.getPropertyValue());
			} else {
				parameterMap.put("venue", studentDetailsDTO.getVenue());
			}
		}

		if (studentDetailsDTO.getGender().equalsIgnoreCase("F")) {
			parameterMap.put("userGender", "her");
		} else {
			parameterMap.put("userGender", "his");
		}
		if (studentDetailsDTO != null && studentDetailsDTO.getAgreedAmount() != null) {
			parameterMap.put("AgreedAmount", studentDetailsDTO.getAgreedAmount());
			parameterMap.put("DueAmount", (studentDetailsDTO.getAgreedAmount() - studentDetailsDTO.getPaidAmount()));
		} else {
			parameterMap.put("AgreedAmount", 0);
			parameterMap.put("DueAmount", 0);
		}

		// send mail

		String StudentEmailID = null;
		String ParentEmailID = null;

		if (studentDetailsDTO.getFatheremailId() != null && !studentDetailsDTO.getFatheremailId().trim().isEmpty()) {
			ParentEmailID = studentDetailsDTO.getFatheremailId();
		}
		if (studentDetailsDTO.getMotheremailId() != null && !studentDetailsDTO.getMotheremailId().trim().isEmpty()) {
			if (ParentEmailID != null) {
				ParentEmailID = ParentEmailID + "," + studentDetailsDTO.getMotheremailId();
			} else {
				ParentEmailID = studentDetailsDTO.getMotheremailId();
			}
		}
		if (studentDetailsDTO.getStudentemailId() != null && !studentDetailsDTO.getStudentemailId().trim().isEmpty()) {
			StudentEmailID = studentDetailsDTO.getStudentemailId();
		}

		if (StudentEmailID != null) {
			PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(), StudentEmailID,
					roleTypeId, tempalteName, "");
		}
//		if (ParentEmailID != null) {
//			PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(), ParentEmailID,
//					roleTypeId, tempalteNameParent, "");
//		}


		 

	}

	public String joinNonBlankStringArray(String s[], String separator) {
		StringBuilder sb = new StringBuilder();
		if (s != null && s.length > 0) {
			for (String w : s) {
				if (w != null && !w.trim().isEmpty()) {
					sb.append(w);
					sb.append(separator);
				}
			}
		}
		return sb.substring(0, sb.length() - 1);// length() - 1 to cut-down last
												// extra separator
	}


}
