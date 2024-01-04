package com.lodestar.edupath.student.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.ClassDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.ArchiveStudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialCityCentersDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorCityMapDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.TrialStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.VenueDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.VenueTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.bean.StudentArchiveVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.util.PasswordGeneratorService;
import com.lodestar.edupath.util.datatable.DataTableVO;

public class ArchiveStudentDetailService {
	public static Logger OUT = LoggerFactory.getLogger(ArchiveStudentDetailService.class);
	private String auditDateStr;
	List<FacilitatorDetailsDTO> facilitatorList = new ArrayList<FacilitatorDetailsDTO>();

	public List<StudentDetailsDTO> getAllStudentDetails(UserSessionObject userSessionObject) {
		List<StudentDetailsDTO> finalList = new ArrayList<StudentDetailsDTO>();
		try {
			finalList = ArchiveStudentDetailsDAO.getStudentList(userSessionObject.getId());
			if (null != finalList && !finalList.isEmpty()) {
				for (StudentDetailsDTO studentDetailsDTO : finalList) {
					if (null != studentDetailsDTO.getSeDetailsDTO()) {

						if (null != studentDetailsDTO.getSeDetailsDTO().getSession1Date()) {

							studentDetailsDTO.setSession1DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSeDetailsDTO().getSession1Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession1DateStr("-");
						}
						if (null != studentDetailsDTO.getSeDetailsDTO().getSession2Date()) {

							studentDetailsDTO.setSession2DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSeDetailsDTO().getSession2Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession2DateStr("-");
						}
						if (null != studentDetailsDTO.getSeDetailsDTO().getSession3Date()) {

							studentDetailsDTO.setSession3DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSeDetailsDTO().getSession3Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession3DateStr("-");
						}
						if (studentDetailsDTO.getSeDetailsDTO().isSession1FaciCompleted()) {
							studentDetailsDTO.setDelete(false);
						}
					}

					byte[] password = studentDetailsDTO.getPassword();
					studentDetailsDTO.setPasswordStr(new String(AESCipher.decrypt(password)));
					if (null != studentDetailsDTO.getSchoolCode()
							&& ApplicationConstants.OTHER_SCHHOL_CODE
									.equalsIgnoreCase(studentDetailsDTO.getSchoolCode())
							&& ApplicationConstants.OTHER_SCHHOL.equalsIgnoreCase(studentDetailsDTO.getSchoolName())) {
						studentDetailsDTO.setSchoolName(studentDetailsDTO.getOtherSchool());
					}
				}
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;
	}

	public List<StudentArchiveVO> getAllStudentSummary(DataTableVO dataTableVO) {
		List<StudentArchiveVO> finalList = new ArrayList<StudentArchiveVO>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>(5);

			ArchiveStudentDetailsDAO studentDetailsDAO = new ArchiveStudentDetailsDAO();
			int totalCount = studentDetailsDAO.getStudentSummaryCount(paramMap);
			dataTableVO.setRecordsTotalCount(totalCount);
			paramMap.put("searchValue", null);
			paramMap.put("orderColumn", null);
			paramMap.put("orderBy", null);

			boolean isSerach = false;
			if (null != dataTableVO.getSearchValue() && !dataTableVO.getSearchValue().isEmpty()) {
				paramMap.put("searchValue", dataTableVO.getSearchValue());
				isSerach = true;
			}
			if ((null != dataTableVO.getOrderColumnName() && !dataTableVO.getOrderColumnName().isEmpty())
					&& (null != dataTableVO.getOrderBy() && !dataTableVO.getOrderBy().isEmpty())) {
				paramMap.put("orderColumn", dataTableVO.getOrderColumnName().trim());
				paramMap.put("orderBy", dataTableVO.getOrderBy().trim());
				isSerach = true;
			}
			if (isSerach) {
				int filteredCount = studentDetailsDAO.getStudentSummaryCount(paramMap);
				dataTableVO.setRecordsFilteredCount(filteredCount);
			} else {
				dataTableVO.setRecordsFilteredCount(totalCount);
			}
			List<StudentDetailsDTO> list = studentDetailsDAO.getStudentListForSummary(paramMap, dataTableVO.getStart(),
					dataTableVO.getPageLength());
			if (null != list && !list.isEmpty()) {
				StudentArchiveVO studentVO = null;
				for (StudentDetailsDTO studentDetailsDTO : list) {
					if (null != studentDetailsDTO.getSeDetailsDTO()) {
						studentVO = new StudentArchiveVO();

						// Start sasedeve edited by vyankatesh on 27-01-2017
						if (null == studentDetailsDTO.getSeDetailsDTO().getVenue()) {

							studentDetailsDTO.setVenue("-");
						}
						// End sasedeve edited by vyankatesh on 27-01-2017

						if (null != studentDetailsDTO.getSeDetailsDTO().getSession1Date()) {

							studentDetailsDTO.setSession1DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSeDetailsDTO().getSession1Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession1DateStr("-");
						}
						if (null != studentDetailsDTO.getSeDetailsDTO().getSession2Date()) {

							studentDetailsDTO.setSession2DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSeDetailsDTO().getSession2Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession2DateStr("-");
						}
						if (null != studentDetailsDTO.getSeDetailsDTO().getSession3Date()) {

							studentDetailsDTO.setSession3DateStr(TimeUtil.getDateFromMillis(
									studentDetailsDTO.getSeDetailsDTO().getSession3Date().getTime(),
									TimeUtil.REPORT_DATE_FORMAT));
						} else {
							studentDetailsDTO.setSession3DateStr("-");
						}
						if (studentDetailsDTO.getSeDetailsDTO().isSession1FaciCompleted()) {
							studentDetailsDTO.setDelete(false);
						}
					}

					byte[] password = studentDetailsDTO.getPassword();
					studentDetailsDTO.setPasswordStr(new String(AESCipher.decrypt(password)));

					if (null != studentDetailsDTO.getSchoolCode()
							&& ApplicationConstants.OTHER_SCHHOL_CODE
									.equalsIgnoreCase(studentDetailsDTO.getSchoolCode())
							&& ApplicationConstants.OTHER_SCHHOL.equalsIgnoreCase(studentDetailsDTO.getSchoolName())) {
						studentDetailsDTO.setSchoolName(studentDetailsDTO.getOtherSchool());
					}

					BeanUtils.copyProperties(studentVO, studentDetailsDTO);

					studentVO.setStudentTypeStr(studentDetailsDTO.getStudentType() == null ? ""
							: studentDetailsDTO.getStudentType().getText());
					studentVO.setActive((studentDetailsDTO.getIsActive().equalsIgnoreCase("Y") ? true : false));
					studentVO.setReportGenerated(studentDetailsDTO.getSeDetailsDTO().isReportGenerated());
					studentVO.setPreSessionCompleted(studentDetailsDTO.getSeDetailsDTO().isPreSessionCompleted());
					studentVO.setIsDelete((studentDetailsDTO.isDelete()));

					studentVO.setSession1FaciCompleted(studentDetailsDTO.getSeDetailsDTO().isSession1FaciCompleted());
					studentVO.setSession2FaciCompleted(studentDetailsDTO.getSeDetailsDTO().isSession2FaciCompleted());
					studentVO.setSession3FaciCompleted(studentDetailsDTO.getSeDetailsDTO().isSession3FaciCompleted());

					finalList.add(studentVO);
				}
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;
	}

	public List<CityDTO> getCityList() {
		List<CityDTO> cityList = new ArrayList<CityDTO>();
		try {
			cityList = new CityDAO().getAllCityList();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return cityList;
	}

	public List<ClassDTO> getClassList() {
		List<ClassDTO> classList = new ArrayList<ClassDTO>();
		try {
			classList = new ClassDAO().getAllClassList();
			classList = MyBatisManager.getInstance().getResultAsList(MyBatisMappingConstants.GET_ALL_CLASS_NAME_WITH_ID,
					new ClassDTO());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return classList;
	}

	public List<SchoolDTO> getSchoolList() {
		List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
		try {
			schoolList = new SchoolDAO().getAllSchoolList();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return schoolList;
	}

	public List<FacilitatorDetailsDTO> getFacilitatorlist() {
		List<FacilitatorDetailsDTO> facilitatorList = new ArrayList<FacilitatorDetailsDTO>();
		try {
			facilitatorList = new FacilitatorDetailsDAO().getActiveFacilitatorList();
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return facilitatorList;
	}

	public EActionStatus insertStudent(StudentDetailsDTO studentDTO) {
		StudentDetailsDTO studentDetailsDTO = null;
		ArchiveStudentDetailsDAO studentDetailsDAO = new ArchiveStudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		// sasedeve added by vyankatesh
		Double dueAmount = null;
		// sasedeve added by vyankatesh
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
				studentDTO.setSource(studentDTO.getStudentType().name());

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());

					paymentDTO.setLoginId(studentLoginId.toString());
					paymentDTO.setStudentId(studentId);
					paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
					paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
					paymentDTO.setDueAmount(String.valueOf(dueAmount));
					new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;

				StringBuilder headerMessage = new StringBuilder();
				headerMessage.append("Your Session Details are as below");

				if (isTrial && StudentTypeEnum.StudentTestTakenEnum.YES.name()
						.equalsIgnoreCase(studentDTO.getTestTaken())) {
					sendNotificationForTrialStudent(studentDetailsDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
							studentLoginId.toString(), password, isNew, headerMessage.toString(), true);
				} else {

					sendNotification(studentDetailsDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
							studentLoginId.toString(), password, isNew, headerMessage.toString(), isTrial);
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
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

	private void sendNotification(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,
			int roleTypeId, String loginId, String password, boolean isNew, String headerMessage, boolean isTrial)
					throws Exception {
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO()
				.getFacilitatorDetailsById(seDetailsDTO.getFacilitatorId());
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT.name();
		String tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT.name();
		parameterMap.put("parentName", studentDetailsDTO.getFathername());
		
		//Start SASEDEVE edited by Mrutyunjaya on date 1-08-2017
		
		parameterMap.put("studentname", studentDetailsDTO.getName());
		
		
		if (isNew) {
			parameterMap.put("loginId", loginId);
			parameterMap.put("userPassword", password);
			
			if(studentDetailsDTO!=null && studentDetailsDTO.getClassId()==5)
			{
				tempalteName = NotificationConstant.MessageTemplateNameAndType.NEW_USER_12_Plus_For_Student.name();
				tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.NEW_USER_12_Plus_For_Parent.name();
			}
			else if(studentDetailsDTO!=null && (studentDetailsDTO.getClassId()==3 || studentDetailsDTO.getClassId()==4))
			{
				tempalteName = NotificationConstant.MessageTemplateNameAndType.NEW_USER_11_12_For_Student.name();
				tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.NEW_USER_11_12_For_Parent.name();
			}
			else
			{
				tempalteName = NotificationConstant.MessageTemplateNameAndType.NEW_USER_9_10_For_Student.name();
				tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.NEW_USER_9_10_For_Parent.name();
			}
			
			

		}
		
		
		
		//END SASEDEVE edited by Mrutyunjaya on date 1-08-2017
		
		if (isTrial) {

			tempalteName = NotificationConstant.MessageTemplateNameAndType.TRIAL_NEW_STUDENT_NEW_TEMP.name();
			tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.TRIAL_NEW_STUDEN_PARENT.name();
		}
		parameterMap.put("headerMessage", headerMessage);
		if (StudentTypeEnum.FULL == studentDetailsDTO.getStudentType()) {
			parameterMap.put("session1Date",
					TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), TimeUtil.EMAIL_FORMAT) + " at "
							+ TimeUtil.getAMOrPM(seDetailsDTO.getSession1Date(), TimeUtil.AM_PM));
			parameterMap.put("session2Date",
					TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TimeUtil.EMAIL_FORMAT) + " at "
							+ TimeUtil.getAMOrPM(seDetailsDTO.getSession2Date(), TimeUtil.AM_PM));
			if (seDetailsDTO.getSession3Date() != null) {
				parameterMap.put("session3Date",
						TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), TimeUtil.EMAIL_FORMAT) + " at "
								+ TimeUtil.getAMOrPM(seDetailsDTO.getSession3Date(), TimeUtil.AM_PM));
			}
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
		if(studentDetailsDTO!=null && studentDetailsDTO.getAgreedAmount()!=null)
		{
			parameterMap.put("AgreedAmount", studentDetailsDTO.getAgreedAmount());
			parameterMap.put("DueAmount",  (studentDetailsDTO.getAgreedAmount() - studentDetailsDTO.getPaidAmount()));
		}
		else
		{
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

		if (ParentEmailID != null) {
			PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(), ParentEmailID,
					roleTypeId, tempalteNameParent, "");
		}

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

	private void sendNotificationForTrialStudent(StudentDetailsDTO studentDetailsDTO,
			SessionScheduleDetailsDTO seDetailsDTO, int roleTypeId, String loginId, String password, boolean isNew,
			String headerMessage, boolean testTaken) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_TEST_TAKEN.name();
		String tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.TRIAL_NEW_STUDEN_PARENT.name();
		parameterMap.put("loginId", loginId);
		parameterMap.put("userPassword", password);
		parameterMap.put("parentName", studentDetailsDTO.getFathername());
		
		
		
		
		
		
		
		
		
		
		//Start SASEDEVE Edited by Mrutyunjaya on Date 12-07-2017
		
		if (isNew) {
			
			
			//Start Sasedeve edited by Mrutyunjaya on Date 25-07-2017
			FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO()
					.getFacilitatorDetailsById(seDetailsDTO.getFacilitatorId());
			parameterMap.put("studentName", studentDetailsDTO.getName());
			
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
			
			parameterMap.put("facilitatorName", facilitatorDetailsDTO.getName());
			parameterMap.put("faciliatorPhone", facilitatorDetailsDTO.getPhoneNumber());
			
			parameterMap.put("AgreedAmount", studentDetailsDTO.getAgreedAmount());
			parameterMap.put("DueAmount",  (studentDetailsDTO.getAgreedAmount() - studentDetailsDTO.getPaidAmount()));
			
			String DATE_FORMAT = "dd-MMM-yyyy";
			String TIME_FORMAT = "hh:mm a";
			if(seDetailsDTO!=null && seDetailsDTO.getSession1Date()!=null)
			{
			parameterMap.put("session1Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), DATE_FORMAT));
			parameterMap.put("session1Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), TIME_FORMAT));
			
			
			}
			
			if(seDetailsDTO!=null && seDetailsDTO.getSession2Date()!=null)
			{
				parameterMap.put("session2Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), DATE_FORMAT));
				parameterMap.put("session2Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TIME_FORMAT));
			
			
			}
			
			
			
			if(seDetailsDTO!=null && seDetailsDTO.getSession3Date()!=null)
			{
				parameterMap.put("session3Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), DATE_FORMAT));
				parameterMap.put("session3Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), TIME_FORMAT));
			
			
			}
			
			
			
			
			
			//End Sasedeve edited by Mrutyunjaya on Date 25-07-2017
			
			
			
			
			if(studentDetailsDTO!=null && studentDetailsDTO.getClassId()!=1 && studentDetailsDTO.getClassId()!=2)
			{
				tempalteName =NotificationConstant.MessageTemplateNameAndType.NEW_TRIAL_TO_FULL_STUDENT_11plus_FOR_STUDENT.name();
				tempalteNameParent=NotificationConstant.MessageTemplateNameAndType.NEW_TRIAL_TO_FULL_STUDENT_11plus_PARENT.name();
			}
			else
			{
				tempalteName =NotificationConstant.MessageTemplateNameAndType.NEW_TRIAL_TO_FULL_STUDENT_FOR_STUDENT.name();
				tempalteNameParent=NotificationConstant.MessageTemplateNameAndType.NEW_TRIAL_TO_FULL_STUDENT_PARENT.name();
				
			}
			
			
			
			
		}
		if (testTaken) {
			tempalteName = NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_TEST_TAKEN.name();
			tempalteNameParent=NotificationConstant.MessageTemplateNameAndType.TRIAL_STUDENT_TEST_TAKEN.name();
		}
		
		//END SASEDEVE Edited by Mrutyunjaya on Date 12-07-2017
		parameterMap.put("headerMessage", headerMessage);

//		PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(),
//				studentDetailsDTO.getFatheremailId(), roleTypeId, tempalteName, "");
		
		
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

		if (ParentEmailID != null) {
			PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(), ParentEmailID,
					roleTypeId, tempalteNameParent, "");
		}

	}

	private void sendNotification(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,
			int roleTypeId, int sessionNo) throws Exception {
		String DATE_FORMAT = "dd-MMM-yyyy";
		String TIME_FORMAT = "hh:mm a";

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.SESSION_RESCHEDULED.name();
		parameterMap.put("sessionNo", sessionNo);
		if (sessionNo == 1) {
			parameterMap.put("sessionDate", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), DATE_FORMAT));
			parameterMap.put("sessionTime", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), TIME_FORMAT));
		}
		if (sessionNo == 2) {
			parameterMap.put("sessionDate", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), DATE_FORMAT));
			parameterMap.put("sessionTime", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TIME_FORMAT));
		}
		if (sessionNo == 3 && seDetailsDTO.getSession3Date()!=null) {
			parameterMap.put("sessionDate", TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), DATE_FORMAT));
			parameterMap.put("sessionTime", TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), TIME_FORMAT));
		}

		// send mail
		PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(),
				studentDetailsDTO.getFatheremailId(), roleTypeId, tempalteName, "");

	}

	private void sendNotification(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,
			int roleTypeId) throws Exception {
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO()
				.getFacilitatorDetailsById(seDetailsDTO.getFacilitatorId());
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.UPDATE_FACILITATOR.name();

		parameterMap.put("facilitatorName", facilitatorDetailsDTO.getName());

		// send mail
		PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(),
				studentDetailsDTO.getFatheremailId(), roleTypeId, tempalteName, "");
	}

	public StudentDetailsDTO getStudentDTO(Integer studentId) {
		StudentDetailsDTO studentDetailsDTO = null;
		try {
			studentDetailsDTO = new ArchiveStudentDetailsDAO().getStudentObjectById(studentId);
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return studentDetailsDTO;
	}

	public EActionStatus doDelete(StudentDetailsDTO studentDTO) {
		try {
			new SessionScheduleDetailsDAO().deleteByStudentId(studentDTO.getId());

			UserDetailDTO userDetailDTO = new UserDetailDTO();
			userDetailDTO.setId(studentDTO.getUserId());
			new UserDetailsDAO().deleteUserDetailsById(userDetailDTO);

			new ArchiveStudentDetailsDAO().deleteUserDetailsById(studentDTO.getId());
			auditDateStr = TimeUtil.getDateFromMillis(System.currentTimeMillis(), TimeUtil.QUERY_DATE_FORMAT);
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return EActionStatus.FAILURE;
		}
		return EActionStatus.SUCCESS;
	}

	public EActionStatus updateStudent(StudentDetailsDTO studentDTO, List<SchoolDTO> schoolList,
			UserSessionObject userSessionObject) {
		StudentDetailsDTO oldStudentDTO = new StudentDetailsDTO();
		ArchiveStudentDetailsDAO studentDetailsDAO = new ArchiveStudentDetailsDAO();
		ManagePaymentDAO managePaymentDAO = new ManagePaymentDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		// sasedeve added by vyankatesh Update payment
		Double dueAmount = null;
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		// sasedeve added by vyankatesh Update payment
		try {
			session = MyBatisManager.getInstance().getSession();
			oldStudentDTO = getStudentDTO(studentDTO.getId());
			if (null == oldStudentDTO) {
				return EActionStatus.FAILURE;
			}
			boolean isOldTrial = false;

			if (null != oldStudentDTO.getStudentType() && (StudentTypeEnum.FULL == studentDTO.getStudentType()
					&& StudentTypeEnum.TRIAL == oldStudentDTO.getStudentType())) {
				isOldTrial = true;
				studentDTO.setSource(oldStudentDTO.getStudentType().name() + "_" + studentDTO.getStudentType().name());
			}
			// Update Student
			
			//////////////////////Vyankatesh   
			TrialStudentExtraDetailDTO.setMothercontactno(studentDTO.getMothercontactno());
			TrialStudentExtraDetailDTO.setMotheremailId(studentDTO.getMotheremailId());
			TrialStudentExtraDetailDTO.setMotherName(studentDTO.getMotherName());
			TrialStudentExtraDetailDTO.setStudentcontactNumber(studentDTO.getStudentcontactNumber());
			TrialStudentExtraDetailDTO.setStudentemailId(studentDTO.getStudentemailId());
			TrialStudentExtraDetailDTO.setFatherEmailId(studentDTO.getFatheremailId());
			TrialStudentExtraDetailDTO.setFathercontactno(studentDTO.getContactNumber());
			TrialStudentExtraDetailDTO.setFatherName(studentDTO.getFathername());
			TrialStudentExtraDetailDTO.setStudentId(studentDTO.getId());
			
			
			if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
				studentDTO.setFatheremailId(studentDTO.getMotheremailId());
				studentDTO.setFathername(studentDTO.getMotherName());
			}
			if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
				studentDTO.setContactNumber(studentDTO.getMothercontactno());
			}

			/////////////////vyankatesh
			
			
			
			
			studentDetailsDAO.updateStudent(studentDTO);

			SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();
			// sasedeve added by vyankatesh Update payment
			PaymentDTO paymentDTO = new PaymentDTO();
			// sasedeve added by vyankatesh Update payment

		//	System.out.println("====================>"+studentDTO.getVenue());
			
			if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
			{
				seDetailsDTO.setVenue(studentDTO.getVenue());
			}
			else
			{
				seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
			}
			seDetailsDTO.setStudentId(studentDTO.getId());
			seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());
			if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
				setSessionSchedule(studentDTO, seDetailsDTO);
			} else {
				seDetailsDTO.setFacilitatorId(0);
			}
			// Update Student Session Schedule

			// sasedeve added by vyankatesh Update payment

			
			List<PaymentDTO> StudentIDlist = managePaymentDAO.getAllStudentIDFromPayment();
			List<TrialStudentExtraDetailDTO> StudentIDlistOfTrial =studentDetailsDAO.getAllStudentIDFromTrialStudentExtraDetail();
			
			
			
			Set<String> StudentLoginIDSet = getAllStudentIDSet(StudentIDlist);
			Set<String> AllTrialExtraStudentIDSet = getAllTrialExtraStudentIDSet(StudentIDlistOfTrial);

			studentLoginId = new StringBuilder();
			StringBuilder append = studentLoginId.append("LD").append(studentDTO.getUserId());
			dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());

			new SessionScheduleDetailsDAO().updateSessionSchedule(seDetailsDTO);
			// userDetailDTO.setLoginId(studentLoginId.toString());
			paymentDTO.setLoginId(studentLoginId.toString());
			paymentDTO.setStudentId(studentDTO.getId());
			paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
			paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
			paymentDTO.setDueAmount(String.valueOf(dueAmount));

			
		
			
			
			if (StudentLoginIDSet.contains(String.valueOf(append))) {
				new ManagePaymentDAO().updatePaymentDetails(paymentDTO);
				session.commit();
			} else {
				new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
				session.commit();
			}
			
			if (AllTrialExtraStudentIDSet.contains(String.valueOf(studentDTO.getId()))) {
				
				studentDetailsDAO.UpdateTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);
			}
			else
			
			{
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);
				session.commit();
				
			}
			// sasedeve added by vyankatesh Update payment

			// Get User Role for email send
			UserRoleDTO roleDTO = new UserRoleDTO();
			roleDTO.setName(UserTypeEnum.USER.getDisplayName());
			roleDTO = new UserRoleDAO().getUserRoleByName(roleDTO);

			// Send email (loginId, password) when old student type is TRIAL and
			// New Student Type FULL
			if (isOldTrial) {
				UserDetailDTO paramuserDTO = new UserDetailDTO();
				paramuserDTO.setId(oldStudentDTO.getUserId());
				paramuserDTO.setRoleId(roleDTO.getId());
				UserDetailDTO userDTO = new UserDetailsDAO().getUserDetailByIdAndRoleId(paramuserDTO);
				if (null != userDTO) {
					StringBuilder headerMessage = new StringBuilder();
					headerMessage.append("Your Session Details are as below");
					if (StudentTypeEnum.StudentTestTakenEnum.YES.name().equalsIgnoreCase(studentDTO.getTestTaken())
							&& StudentTypeEnum.StudentTestTakenEnum.NO.name()
									.equalsIgnoreCase(oldStudentDTO.getTestTaken())) {
						sendNotificationForTrialStudent(studentDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
								userDTO.getLoginId(), new String(AESCipher.decrypt(userDTO.getPassword())), true,
								headerMessage.toString(), true);
					} else {
						sendNotificationForTrialStudent(studentDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
								userDTO.getLoginId(), new String(AESCipher.decrypt(userDTO.getPassword())), true,
								headerMessage.toString(), false);
					}
					return EActionStatus.SUCCESS;
				}
			}
			if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
				
				
				String MessageString="We would like to inform you, that there is a change in Session Plan.";
				
				boolean facilitatorChanged = null != oldStudentDTO.getSeDetailsDTO()
						&& oldStudentDTO.getSeDetailsDTO().getFacilitatorId() == studentDTO.getFacilitatorId() ? false
								: true;
				if (facilitatorChanged) {
					//MessageString=MessageString+"Guidance Specialist ";
				}
				int sessionNo=0;
				boolean session1Changed = null != oldStudentDTO.getSeDetailsDTO().getSession1Date()
						? sessionChanged(oldStudentDTO.getSeDetailsDTO().getSession1Date().getTime(), TimeUtil
								.getDate(studentDTO.getSession1DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT).getTime())
						: true;
				boolean session2Changed = null != oldStudentDTO.getSeDetailsDTO().getSession2Date()
						? sessionChanged(oldStudentDTO.getSeDetailsDTO().getSession2Date().getTime(), TimeUtil
								.getDate(studentDTO.getSession2DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT).getTime())
						: true;
				boolean session3Changed = null != oldStudentDTO.getSeDetailsDTO().getSession3Date()
						? sessionChanged(oldStudentDTO.getSeDetailsDTO().getSession3Date().getTime(), TimeUtil
								.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT).getTime())
						: true;
				if (session1Changed) {
					
					if(sessionNo==0)
					{
						sessionNo = 1;
						if(facilitatorChanged)
						{
							//MessageString=MessageString+"";
						}
						else
						{
							//MessageString=MessageString+"there is change is session schedule. Please find the update schedule below.";
						}
					}
					
					
					//sendNotification(studentDTO, seDetailsDTO, roleDTO.getRoleTypeId(), sessionNo);
				}
				if (session2Changed) {
					if(sessionNo==0)
					{
						sessionNo = 1;
						if(facilitatorChanged)
						{
							//MessageString=MessageString+"";
						}
						else
						{
							//MessageString=MessageString+"there is change is session schedule. Please find the update schedule below.";
						}
					}
					//sendNotification(studentDTO, seDetailsDTO, roleDTO.getRoleTypeId(), sessionNo);
				}
				if (session3Changed) {
					if(sessionNo==0)
					{
						sessionNo = 1;
						if(facilitatorChanged)
						{
							//MessageString=MessageString+"";
						}
						else
						{
							//MessageString=MessageString+"there is change is session schedule. Please find the update schedule below.";
						}
					}
					//sendNotification(studentDTO, seDetailsDTO, roleDTO.getRoleTypeId(), sessionNo);
				}
				
				
				boolean changevenu=false;
				
				
				if(oldStudentDTO.getVenue()!=null && oldStudentDTO.getVenue()!=studentDTO.getVenue())
				{
					changevenu=true;
				}
				
				if(changevenu)
				{
					if(!facilitatorChanged && sessionNo==0)
					{
						//MessageString=MessageString+"venue for your session has been changed. Please find the updated venue & address details below.";
					}
					else
					{
						//MessageString=MessageString+"";
					}
				}
				
				
				
				
				if(sessionNo==0 && !changevenu)
				{
					//MessageString=MessageString+"for your session is been changed. Please find the updated Guidance specialist information below.";
				}
				else
				{
					//MessageString=MessageString+"";
				}
				
				
				
				
				
				sendNotificationForUpdate(studentDTO, seDetailsDTO, roleDTO.getRoleTypeId(),MessageString);
				
				
				
				
			}

			return EActionStatus.SUCCESS;
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return EActionStatus.FAILURE;
	}

	//Start Sasedeve Edited by Mrutyunjaya on date 08-08-2017
	
	private void sendNotificationForUpdate(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,int roleTypeId,String MessageString) throws Exception
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = null;
		String tempalteNameParent = null;
		
		if(studentDetailsDTO!=null && studentDetailsDTO.getClassId()==5)
		{
			tempalteName=NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Student.name();
			tempalteNameParent=NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Parent.name();
			
		}
		else if(studentDetailsDTO!=null && (studentDetailsDTO.getClassId()==3 || studentDetailsDTO.getClassId()==4))
		{
			tempalteName=NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Student.name();
			tempalteNameParent=NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Parent.name();
		}
		else
		{
			tempalteName=NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_9_10_for_Student.name();
			tempalteNameParent=NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_9_10_for_Parent.name();
		}
		
		parameterMap.put("messagestring", MessageString);
		
		parameterMap.put("loginId", studentDetailsDTO.getLoginId());
		parameterMap.put("userPassword", studentDetailsDTO.getPasswordStr());
		parameterMap.put("parentName", studentDetailsDTO.getFathername());
		
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO()
				.getFacilitatorDetailsById(seDetailsDTO.getFacilitatorId());
		parameterMap.put("studentName", studentDetailsDTO.getName());
		
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
		
		parameterMap.put("facilitatorName", facilitatorDetailsDTO.getName());
		parameterMap.put("faciliatorPhone", facilitatorDetailsDTO.getPhoneNumber());
		
		parameterMap.put("AgreedAmount", studentDetailsDTO.getAgreedAmount());
		parameterMap.put("DueAmount",  (studentDetailsDTO.getAgreedAmount() - studentDetailsDTO.getPaidAmount()));
		
		String DATE_FORMAT = "dd-MMM-yyyy";
		String TIME_FORMAT = "hh:mm a";
		if(seDetailsDTO!=null && seDetailsDTO.getSession1Date()!=null)
		{
		parameterMap.put("session1Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), DATE_FORMAT));
		parameterMap.put("session1Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), TIME_FORMAT));
		
		
		}
		
		if(seDetailsDTO!=null && seDetailsDTO.getSession2Date()!=null)
		{
			parameterMap.put("session2Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), DATE_FORMAT));
			parameterMap.put("session2Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TIME_FORMAT));
		
		
		}
		
		
		
		if(seDetailsDTO!=null && seDetailsDTO.getSession3Date()!=null)
		{
			parameterMap.put("session3Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), DATE_FORMAT));
			parameterMap.put("session3Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession3Date(), TIME_FORMAT));
		
		
		}
		
		
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

		if (ParentEmailID != null) {
			PasswordGeneratorService.sendNewNotification(parameterMap, studentDetailsDTO.getName(), ParentEmailID,
					roleTypeId, tempalteNameParent, "");
		}
		
	}
	
	
	
	
	
	//end Sasedeve Edited by Mrutyunjaya on date 08-08-2017

 	private void setSessionSchedule(StudentDetailsDTO studentDTO, SessionScheduleDetailsDTO seDetailsDTO)
			throws ParseException {
		seDetailsDTO
				.setSession1Date(TimeUtil.getDate(studentDTO.getSession1DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
		seDetailsDTO
				.setSession2Date(TimeUtil.getDate(studentDTO.getSession2DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
		if (studentDTO.getSession3DateStr() != null) {
			seDetailsDTO.setSession3Date(
					TimeUtil.getDate(studentDTO.getSession3DateStr(), TimeUtil.FILTER_DATE_TIME_FORMAT));
		}
	}

	private boolean sessionChanged(long oldTime, long newTime) throws ParseException {
		boolean changed = false;
		if (!changed && oldTime != newTime) {
			changed = true;
		}
		return changed;
	}

	public List<String> getTimeList() {
		List<String> timeList = new ArrayList<String>();
		int startTime = 9;
		for (; startTime <= 20; startTime++) {
			if (startTime < 10) {
				timeList.add("0" + startTime + ":00");
				timeList.add("0" + startTime + ":30");
			} else {
				timeList.add(startTime + ":00");
				if (startTime != 20) {
					timeList.add(startTime + ":30");
				}
			}
		}
		return timeList;
	}

	public String vanueGlobalAddress() {
		String address = "";
		OUT.debug("Inside StudentDetailService");
		try {
			GlobalSttingDAO dao = new GlobalSttingDAO();
			GlobalSettingDTO dto = new GlobalSettingDTO();
			dto.setPropertyName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_LODESTAR_ADDRESS.getProperty());
			dto = dao.getPropertesValueByName(dto);

			if (null != dto) {
				address = dto.getPropertyValue();
			}
		} catch (Exception e) {
			OUT.error("Exception", e);
		}
		return address;
	}

	public int sessionValidGap() {
		int minDays = 0;
		try {
			GlobalSettingDTO globalDTO = new GlobalSettingDTO();
			globalDTO.setPropertyName(ApplicationConstants.GlobalSettings.SESSION_TIME_GAP_IN_DAYS.getProperty());
			GlobalSettingDTO globalSettingDTO = new GlobalSttingDAO().getPropertesValueByName(globalDTO);
			minDays = null != globalSettingDTO.getPropertyValue()
					? Integer.parseInt(globalSettingDTO.getPropertyValue()) : -1;
		} catch (Exception e) {
			OUT.error("Exception", e);
		}
		return minDays;
	}

	public void getGlobalSettingMap(Map<String, String> globalSettingMap) {
		try {
			List<GlobalSettingDTO> list = new GlobalSttingDAO().getAllGlobalSetting();
			if (null != list && !list.isEmpty()) {
				for (GlobalSettingDTO globalSettingDTO : list) {
					globalSettingMap.put(globalSettingDTO.getPropertyName(),
							(null != globalSettingDTO.getPropertyValue() ? globalSettingDTO.getPropertyValue() : "-1"));
				}
			}

		} catch (Exception e) {
			OUT.error("Exception", e);
		}
	}

	public boolean modifyStudentSessionByOpenSession(Integer studentId, int userId, String openSessionType,
			String userLoginId) {
		boolean status = false;
		SqlSession session = null;
		try {
			if (studentId != null && studentId > 0 && userId > 0 && openSessionType != null
					&& !openSessionType.isEmpty()) {
				session = MyBatisManager.getInstance().getSession();
				SessionScheduleDetailsDAO sessionScheduleDetailsDAO = new SessionScheduleDetailsDAO();
				SessionScheduleDetailsDTO sessionScheduleDetailsDTO = sessionScheduleDetailsDAO
						.getSessionDetailsByStudentId(studentId);
				SessionScheduleDetailsDTO scheduleDetailsDTO = new SessionScheduleDetailsDTO();
				UserDetailDTO userDetailDTO = new UserDetailDTO();
				StringBuilder sessionAuditMessage = new StringBuilder();
				if (openSessionType.equalsIgnoreCase("inActive") || openSessionType.equalsIgnoreCase("active")) {
					if (openSessionType.equalsIgnoreCase("inActive")) {
						userDetailDTO.setId(userId);
						userDetailDTO.setIsActive("N");
						sessionAuditMessage.append(" is de-activated");
					} else if (openSessionType.equalsIgnoreCase("active")) {
						userDetailDTO.setId(userId);
						userDetailDTO.setIsActive("Y");
						sessionAuditMessage.append(" is activated");
					}
					new UserDetailsDAO().updateUserDetailsIsActiveById(session, userDetailDTO);
				} else {
					scheduleDetailsDTO.setStudentId(studentId);
					if (openSessionType.equalsIgnoreCase("pre")) {
						scheduleDetailsDTO.setPreSessionCompleted(true);
						sessionAuditMessage.append("Pre-Session");
						if (sessionScheduleDetailsDTO.isSession1FaciCompleted()) {
							sessionAuditMessage.append(", ");
							sessionAuditMessage.append("Session1");
						}
						if (sessionScheduleDetailsDTO.isSession2FaciCompleted()) {
							sessionAuditMessage.append(", ");
							sessionAuditMessage.append("Session2");
						}
						if (sessionScheduleDetailsDTO.isSession3FaciCompleted()) {
							sessionAuditMessage.append(", ");
							sessionAuditMessage.append("Session3");
						}
						sessionAuditMessage.append(" opened");
						int deletedId = new StudentCGTDAO().deleteStudentCGTResultsByStudentId(session, studentId);
						if (deletedId > 0) {
							AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.MANAGE_STUDENT,
									"StudentCGT Results deleted for student id : " + studentId, userLoginId);
						}
					} else if (openSessionType.equalsIgnoreCase("one")) {
						scheduleDetailsDTO.setSession1FaciCompleted(true);
						sessionAuditMessage.append("Session1");
						if (sessionScheduleDetailsDTO.isSession2FaciCompleted()) {
							sessionAuditMessage.append(", ");
							sessionAuditMessage.append("Session2");
						}
						if (sessionScheduleDetailsDTO.isSession3FaciCompleted()) {
							sessionAuditMessage.append(", ");
							sessionAuditMessage.append("Session3");
						}
						sessionAuditMessage.append(" opened");
					} else if (openSessionType.equalsIgnoreCase("two")) {
						scheduleDetailsDTO.setSession2FaciCompleted(true);
						sessionAuditMessage.append("Session2");
						if (sessionScheduleDetailsDTO.isSession3FaciCompleted()) {
							sessionAuditMessage.append(", ");
							sessionAuditMessage.append("Session3");
						}
						sessionAuditMessage.append(" opened");
					} else if (openSessionType.equalsIgnoreCase("three")) {
						scheduleDetailsDTO.setSession3FaciCompleted(true);
						sessionAuditMessage.append("Session3");
						sessionAuditMessage.append(" opened");
					}
					sessionScheduleDetailsDAO.updateSessionsByStudentId(session, scheduleDetailsDTO);

				}
				AuditTrailLogger.addAuditInfo(session, ModuleNameEnum.MANAGE_STUDENT,
						"For Student id " + studentId + " : " + sessionAuditMessage, userLoginId);
				session.commit();
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			if (session != null) {
				session.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return status;
	}

	public Map<String, StudentDetailsDTO> getStudentMapForIntersetTestBulk() throws Exception {
		Map<String, StudentDetailsDTO> studentMap = new HashMap<String, StudentDetailsDTO>();
		List<StudentDetailsDTO> list = new ArchiveStudentDetailsDAO().getStudentForInterestTestBulk();
		for (StudentDetailsDTO studentDetailsDTO : list) {
			studentMap.put(null != studentDetailsDTO.getLoginId() ? studentDetailsDTO.getLoginId().toLowerCase()
					: studentDetailsDTO.getLoginId(), studentDetailsDTO);
		}
		OUT.debug("Interest Test Student Bulk MAP SIZE : {}", studentMap.size());
		return studentMap;
	}

	public String getTrialAuthKey() throws Exception {
		return new GlobalSttingDAO().getPropertesValueByName(
				ApplicationConstants.GlobalSettings.EXTERNAL_URL_AUT_KEY_TRIAL_STUDENT.getProperty());
	}

	public String getRecaptchaKey() {
		try {
			return new GlobalSttingDAO()
					.getPropertesValueByName(ApplicationConstants.GlobalSettings.APP_RECAPTCHA_SITE_KEY.getProperty());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getAppWebUrl() {
		try {
			return new GlobalSttingDAO()
					.getPropertesValueByName(ApplicationConstants.GlobalSettings.GLOBAL_SETTINGS_WEB_URL.getProperty());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return null;
	}

	public String getAuditDateStr() {
		return auditDateStr;
	}

	private Set<String> getAllStudentIDSet(List<PaymentDTO> studentIDlist) {
		Set<String> studentIDSet = null;
		if (studentIDlist != null) {
			studentIDSet = new HashSet<String>();
			for (PaymentDTO PaymentDTO : studentIDlist) {
				studentIDSet.add(PaymentDTO.getLoginId());
			}
		}
		return studentIDSet;
	}

	public Map<Integer, ArrayList<Map<String, String>>> getallvenuedetail(List<CityDTO> cityList) throws Exception {
		Map<Integer, ArrayList<Map<String, String>>> VenueAdressMap = new HashMap<Integer, ArrayList<Map<String, String>>>();

		List<VenueDetailDTO> list = new ArchiveStudentDetailsDAO().getAllvenueDetail();
		if (list != null && !list.isEmpty()) {
			ArrayList<Map<String, String>> VenueMapList;
			for (CityDTO cityDTO : cityList) {
				VenueMapList = new ArrayList<Map<String, String>>();
				Iterator<VenueDetailDTO> iterator = list.iterator();
				while (iterator.hasNext()) {
					Map<String, String> product = new HashMap<String, String>();
					VenueDetailDTO VenueDetailDTO = (VenueDetailDTO) iterator.next();

					if (cityDTO.getId() == VenueDetailDTO.getCityId()) {

						product.put("venue", VenueDetailDTO.getVenue().replaceAll("'", "'+'"));
						product.put("adress", (VenueDetailDTO.getAddress()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
						VenueMapList.add(product);

					}
					if (list != null && !list.isEmpty()) {
						// citySchoolMap.put(cityDTO.getId(), SchoolMapList);
						VenueAdressMap.put(cityDTO.getId(), VenueMapList);
					}

				}

			}
		}
		return VenueAdressMap;
	}

	public Map<String, String> getallAddressvenuedetail(List<VenueDetailDTO> venuelist) throws Exception {
		Map<String, String> VenueAdressDtailMap = new HashMap<String, String>();
		if (venuelist != null && !venuelist.isEmpty()) {

			for (VenueDetailDTO venueDetailDTO : venuelist) {

				VenueAdressDtailMap.put(venueDetailDTO.getVenue().replaceAll("'", "'+'"), venueDetailDTO.getAddress().replaceAll("'", "'+'"));
			}

		}

		return VenueAdressDtailMap;
	}

	public Map<Integer, ArrayList<Map<String, String>>> getallcitySchool(List<CityDTO> cityList) throws Exception {

		Map<Integer, ArrayList<Map<String, String>>> citySchoolMap = new HashMap<Integer, ArrayList<Map<String, String>>>();

		List<SchoolDTO> schoolList = new SchoolDAO().getAllSchoolList();

		if (schoolList != null && !schoolList.isEmpty()) {

			// ArrayList<Map<String, String>> products = new
			// ArrayList<Map<String, String>>();
			ArrayList<Map<String, String>> SchoolMapList;
			for (CityDTO cityDTO : cityList) {
				SchoolMapList = new ArrayList<Map<String, String>>();
				Iterator<SchoolDTO> iterator = schoolList.iterator();
				while (iterator.hasNext()) {
					Map<String, String> product = new HashMap<String, String>();
					SchoolDTO schoolDTO = (SchoolDTO) iterator.next();

					if (cityDTO.getId() == schoolDTO.getCityId()) {
						//TODO : to fix ' issue
						product.put("Name", schoolDTO.getName().replaceAll("'", "'+'"));
						product.put("code", schoolDTO.getCode());
						product.put("id", String.valueOf(schoolDTO.getId()));
						SchoolMapList.add(product);

					}
					if(cityDTO.getId() == 326)
					{
						if( schoolDTO.getCityId() == 0)
						{
							//TODO : to fix ' issue
							product.put("Name", schoolDTO.getName().replaceAll("'", "'+'"));
							product.put("code", schoolDTO.getCode());
							product.put("id", String.valueOf(schoolDTO.getId()));
							SchoolMapList.add(product);
							
						}
						
						
					}

				}
				if (schoolList != null && !schoolList.isEmpty()) {
					// citySchoolMap.put(cityDTO.getId(), SchoolMapList);
					citySchoolMap.put(cityDTO.getId(), SchoolMapList);
				}
			}
		}
		return citySchoolMap;
	}

	public Map<Integer, ArrayList<Map<String, String>>> getallFacilitatorListCity(List<CityDTO> cityList)
			throws Exception {
		Map<Integer, ArrayList<Map<String, String>>> cityFacilitatorMap = new HashMap<Integer, ArrayList<Map<String, String>>>();
		List<FacilitatorCityMapDTO> facilitatorcityList = new FacilitatorDetailsDAO().getActiveFacilitatorcityList();
		if (facilitatorcityList != null && !facilitatorcityList.isEmpty()) {

			// ArrayList<Map<String, String>> products = new
			// ArrayList<Map<String, String>>();
			ArrayList<Map<String, String>> FaiclitatorCityMapList;
			for (CityDTO cityDTO : cityList) {
				FaiclitatorCityMapList = new ArrayList<Map<String, String>>();
				Iterator<FacilitatorCityMapDTO> iterator = facilitatorcityList.iterator();
				while (iterator.hasNext()) {
					Map<String, String> product = new HashMap<String, String>();
					FacilitatorCityMapDTO FacilitatorCityMapDTO = (FacilitatorCityMapDTO) iterator.next();
					
					
					
					if (cityDTO.getId() == FacilitatorCityMapDTO.getCityId()) {
						product.put("faceToface", String.valueOf(FacilitatorCityMapDTO.getFaceToFaceCity()));
						product.put("onCall", String.valueOf(FacilitatorCityMapDTO.getOncallCity()));
						product.put("facilitatorId", String.valueOf(FacilitatorCityMapDTO.getFacilitatorId()));
						product.put("Name", FacilitatorCityMapDTO.getName());

						FaiclitatorCityMapList.add(product);

					}
					
					
					facilitatorList = new FacilitatorDetailsDAO().getActiveFacilitatorList();
					Set<Integer> ALLPreviousFacititorIDSet = getAllPreviousFacititator(facilitatorList);
					
					if(!ALLPreviousFacititorIDSet.contains(String.valueOf(FacilitatorCityMapDTO.getFacilitatorId())))
					{
						
					
					if(cityDTO.getId() == 326)
					{
						if( FacilitatorCityMapDTO.getCityId() == 0)
						{
							product.put("faceToface", String.valueOf(1));
							product.put("onCall", String.valueOf(0));
							product.put("facilitatorId", String.valueOf(FacilitatorCityMapDTO.getOldid()));
							product.put("Name", FacilitatorCityMapDTO.getName());
							FaiclitatorCityMapList.add(product);
							
						}
						
						
					}
					}
					
					

				}
				if (facilitatorcityList != null && !facilitatorcityList.isEmpty()) {
					// citySchoolMap.put(cityDTO.getId(), SchoolMapList);
					cityFacilitatorMap.put(cityDTO.getId(), FaiclitatorCityMapList);
				}
			}
		}

		return cityFacilitatorMap;

	}
	
	private Set<Integer> getAllPreviousFacititator(List<FacilitatorDetailsDTO> facilitatorList2) {
		Set<Integer> PreviousFacititatorIDSet = null;
		if (facilitatorList2 != null) {
			PreviousFacititatorIDSet = new HashSet<Integer>();
			for (FacilitatorDetailsDTO FacilitatorDetailsDTO : facilitatorList2) {
				PreviousFacititatorIDSet.add(FacilitatorDetailsDTO.getId());
			}
		}
		return PreviousFacititatorIDSet;
	}

	private Set<String> getAllTrialExtraStudentIDSet(List<TrialStudentExtraDetailDTO> studentIDlistOfTrial) {
		Set<String> TrialExtraStudentIDSet = null;
		if (studentIDlistOfTrial != null) {
			TrialExtraStudentIDSet = new HashSet<String>();
			for (TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO : studentIDlistOfTrial) {
				TrialExtraStudentIDSet.add(String.valueOf(TrialStudentExtraDetailDTO.getStudentId()));
			}
		}
		return TrialExtraStudentIDSet;
	}

	public String insertStudentDetailForInterstBulkUplode(StudentDetailsDTO studentDTO) {
		

		StudentDetailsDTO studentDetailsDTO = null;
		ArchiveStudentDetailsDAO studentDetailsDAO = new ArchiveStudentDetailsDAO();
		TrialStudentExtraDetailDTO TrialStudentExtraDetailDTO = new TrialStudentExtraDetailDTO();
		StringBuilder studentLoginId = null;
		SqlSession session = null;
		// sasedeve added by vyankatesh
		Double dueAmount = null;
		// sasedeve added by vyankatesh
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
					return null;
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
				studentDTO.setSource(studentDTO.getStudentType().name());

				if (null == studentDTO.getFatheremailId() || studentDTO.getFatheremailId().isEmpty()) {
					studentDTO.setFatheremailId(studentDTO.getMotheremailId());
					studentDTO.setFathername(studentDTO.getMotherName());
				}
				if (null == studentDTO.getContactNumber() || studentDTO.getContactNumber().isEmpty()) {
					studentDTO.setContactNumber(studentDTO.getMothercontactno());
				}

				studentDetailsDTO = studentDetailsDAO.insertStudent(session, studentDTO);
				// studentDetailsDAO.inserttrialStudent(session, studentDTO);

				SessionScheduleDetailsDTO seDetailsDTO = new SessionScheduleDetailsDTO();

				// sasedeve added by vyankatesh
				PaymentDTO paymentDTO = new PaymentDTO();
				// sasedeve added by vyankatesh
				
				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty())
				{
					seDetailsDTO.setVenue(studentDTO.getVenue());
				}
				else
				{
					seDetailsDTO.setVenue((studentDTO.getVenue()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
				}
				
				seDetailsDTO.setStudentId(studentDTO.getId());
				seDetailsDTO.setFacilitatorId(studentDTO.getFacilitatorId());

				// Vyankatesh edit add Trialstudent extra Field
				TrialStudentExtraDetailDTO.setStudentId(studentDetailsDTO.getId());

				boolean isTrial = false;
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					setSessionSchedule(studentDetailsDTO, seDetailsDTO);

				} else {
					isTrial = true;
					seDetailsDTO.setFacilitatorId(0);

				}
				// Insert Student Session Schedule
				int studentId = new SessionScheduleDetailsDAO().insertSessionSchedule(session, seDetailsDTO);
				studentDetailsDAO.insertTrialStudentExtraDetail(session, TrialStudentExtraDetailDTO);

				// sasedeve added by vyankatesh
				if (StudentTypeEnum.FULL == studentDTO.getStudentType()) {
					dueAmount = (studentDTO.getAgreedAmount() - studentDTO.getPaidAmount());

					paymentDTO.setLoginId(studentLoginId.toString());
					paymentDTO.setStudentId(studentId);
					paymentDTO.setAgreedAmount(String.valueOf(studentDTO.getAgreedAmount()));
					paymentDTO.setPaidAmount(String.valueOf(studentDTO.getPaidAmount()));
					paymentDTO.setDueAmount(String.valueOf(dueAmount));
					new ManagePaymentDAO().insertPaymentDetails(session, paymentDTO);
				}
				// sasedeve added by vyankatesh

				boolean isNew = true;

				StringBuilder headerMessage = new StringBuilder();
				headerMessage.append("Your Session Details are as below");

				if (isTrial && StudentTypeEnum.StudentTestTakenEnum.YES.name()
						.equalsIgnoreCase(studentDTO.getTestTaken())) {
					sendNotificationForTrialStudent(studentDetailsDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
							studentLoginId.toString(), password, isNew, headerMessage.toString(), true);
				} else {

					sendNotification(studentDetailsDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
							studentLoginId.toString(), password, isNew, headerMessage.toString(), isTrial);
				}

				session.commit();
				if (userDetailDTO.getCreatedOn() != null) {
					auditDateStr = TimeUtil.getDateFromMillis(userDetailDTO.getCreatedOn().getTime(),
							TimeUtil.QUERY_DATE_FORMAT);
				}
				//EActionStatus.
				return studentLoginId.toString();
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
		return null;
	}
	

}
