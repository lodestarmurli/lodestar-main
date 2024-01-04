package com.lodestar.edupath.vark.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.APIS.dailyHunt.Util;
import com.lodestar.edupath.audit.AuditTrailLogger;
import com.lodestar.edupath.auth.service.UserSessionObject;
import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.dataaccessobject.dao.CGT.StudentCGTDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.ClassDAO;
import com.lodestar.edupath.dataaccessobject.dao.StudentDetails.StudentDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.city.CityDAO;
import com.lodestar.edupath.dataaccessobject.dao.constants.MyBatisMappingConstants;
import com.lodestar.edupath.dataaccessobject.dao.constants.NotificationConstant;
import com.lodestar.edupath.dataaccessobject.dao.facilitator.FacilitatorDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.globalsetting.GlobalSttingDAO;
import com.lodestar.edupath.dataaccessobject.dao.induocchoice.SystemRecommendation;
import com.lodestar.edupath.dataaccessobject.dao.payment.ManagePaymentDAO;
import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.dataaccessobject.dao.sessionscheduledetails.SessionScheduleDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialCityCentersDAO;
import com.lodestar.edupath.dataaccessobject.dao.userdetails.UserDetailsDAO;
import com.lodestar.edupath.dataaccessobject.dao.userrole.UserRoleDAO;
import com.lodestar.edupath.dataaccessobject.dao.vark.VarkStudentDAO;
import com.lodestar.edupath.datatransferobject.dto.CityDTO;
import com.lodestar.edupath.datatransferobject.dto.ClassDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorCityMapDTO;
import com.lodestar.edupath.datatransferobject.dto.FacilitatorDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.GlobalSettingDTO;
import com.lodestar.edupath.datatransferobject.dto.PaymentDTO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;
import com.lodestar.edupath.datatransferobject.dto.TrialStudentExtraDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.VenueDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.partner.PartnerDeatilsDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.role.UserRoleDTO;
import com.lodestar.edupath.datatransferobject.dto.session.SessionScheduleDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.dto.tutorial.TutorialCityCentersDTO;
import com.lodestar.edupath.datatransferobject.dto.vark.VarkStudentDTO;
import com.lodestar.edupath.datatransferobject.enumtype.ModuleNameEnum;
import com.lodestar.edupath.datatransferobject.enumtype.StudentTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.UserTypeEnum;
import com.lodestar.edupath.datatransferobject.enumtype.VenueTypeEnum;
import com.lodestar.edupath.datatransferobject.util.AESCipher;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;
import com.lodestar.edupath.datatransferobject.util.TimeUtil;
import com.lodestar.edupath.student.bean.StudentIndividualVO;
import com.lodestar.edupath.student.bean.StudentVO;
import com.lodestar.edupath.util.EncryptionDecryptionData;
import com.lodestar.edupath.util.PasswordGeneratorService;
import com.lodestar.edupath.util.datatable.DataTableVO;

public class StudentRegistrationService {
	public static Logger OUT = LoggerFactory.getLogger(StudentRegistrationService.class);
	private String auditDateStr;
	List<FacilitatorDetailsDTO> facilitatorList = new ArrayList<FacilitatorDetailsDTO>();

	public List<StudentDetailsDTO> getAllStudentDetails(UserSessionObject userSessionObject) {
		List<StudentDetailsDTO> finalList = new ArrayList<StudentDetailsDTO>();
		try {
			finalList = StudentDetailsDAO.getStudentList(userSessionObject.getId());
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

	public List<StudentVO> getAllStudentSummary(DataTableVO dataTableVO) {
		List<StudentVO> finalList = new ArrayList<StudentVO>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>(5);

			StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
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
				StudentVO studentVO = null;
				studentVO = new StudentVO();
				for (StudentDetailsDTO studentDetailsDTO : list) {
					if (null != studentDetailsDTO.getSeDetailsDTO()) {

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

	// start by bharath 4/7/2019
	public List<StudentIndividualVO> getIndividualStudentSummary(int userId) {
		List<StudentIndividualVO> finalList = new ArrayList<StudentIndividualVO>();
		try {

			StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();

			StudentDetailsDTO studentDetailsDTO = studentDetailsDAO.getStudentForSummaryById(userId);
			if (true) {
				StudentIndividualVO studentVO = null;
				studentVO = new StudentIndividualVO();
				if (null != studentDetailsDTO.getSeDetailsDTO()) {

					if (null == studentDetailsDTO.getSeDetailsDTO().getVenue()) {

						studentDetailsDTO.setVenue("-");
					}

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
						&& ApplicationConstants.OTHER_SCHHOL_CODE.equalsIgnoreCase(studentDetailsDTO.getSchoolCode())
						&& ApplicationConstants.OTHER_SCHHOL.equalsIgnoreCase(studentDetailsDTO.getSchoolName())) {
					studentDetailsDTO.setSchoolName(studentDetailsDTO.getOtherSchool());
				}
				BeanUtils.copyProperties(studentVO, studentDetailsDTO);

				studentVO.setStudentTypeStr(
						studentDetailsDTO.getStudentType() == null ? "" : studentDetailsDTO.getStudentType().getText());
				studentVO.setActive((studentDetailsDTO.getIsActive().equalsIgnoreCase("Y") ? true : false));
				studentVO.setReportGenerated(studentDetailsDTO.getSeDetailsDTO().isReportGenerated());
				studentVO.setPreSessionCompleted(studentDetailsDTO.getSeDetailsDTO().isPreSessionCompleted());
				studentVO.setIsDelete((studentDetailsDTO.isDelete()));

				studentVO.setSession1FaciCompleted(studentDetailsDTO.getSeDetailsDTO().isSession1FaciCompleted());
				studentVO.setSession2FaciCompleted(studentDetailsDTO.getSeDetailsDTO().isSession2FaciCompleted());
				studentVO.setSession3FaciCompleted(studentDetailsDTO.getSeDetailsDTO().isSession3FaciCompleted());
				studentVO.setFacilitatorName(studentDetailsDTO.getFacilitatorName());
				finalList.add(studentVO);

			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;
	}

	public List<StudentVO> getAllStudentSummaryNew(DataTableVO dataTableVO) {
		List<StudentVO> finalList = new ArrayList<StudentVO>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>(5);

			StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
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
				StudentVO studentVO = null;

				for (StudentDetailsDTO studentDetailsDTO : list) {
					byte[] password = studentDetailsDTO.getPassword();
					studentDetailsDTO.setPasswordStr(new String(AESCipher.decrypt(password)));
					studentVO = null;
					studentVO = new StudentVO();
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
					studentVO.setIsDelete((studentDetailsDTO.isDelete()));
					finalList.add(studentVO);
				}
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;
	}
	// End by bharath 4/7/2019

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

	@SuppressWarnings("static-access")
	public EActionStatus insertStudent(StudentDetailsDTO studentDTO) {
		StudentDetailsDTO studentDetailsDTO = null;
		StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();
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
//				studentDTO.setSource(studentDTO.getStudentType().name());

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

				if (null == studentDTO.getVenue() || studentDTO.getVenue().isEmpty()) {
					seDetailsDTO.setVenue(studentDTO.getVenue());
				} else {
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
				
				String token=createToken(studentId,studentLoginId.toString(),password);
				OUT.debug("bharath vark insert student token:{}",token);
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				VarkStudentDTO _VSDTO = new VarkStudentDTO();
				VarkStudentDAO _VSDAO = new VarkStudentDAO();
				_VSDTO.setLdId(studentLoginId.toString());
				_VSDTO.setStudentId(studentId);
				_VSDTO.setToken(token);
				_VSDTO.setStudentRegisterTime(formatter.parse(formatter.format(date)));
				_VSDTO.setTestTaken(0);
				OUT.debug("bharath vark insert student VarkStudentDTO:{}",_VSDTO);
				int result=_VSDAO.insertVarkStudent(session, _VSDTO);
				
				
				StringBuilder headerMessage = new StringBuilder();
				headerMessage.append("Your Session Details are as below");

				 

					sendNotification(studentDetailsDTO, seDetailsDTO, roleDTO.getRoleTypeId(),
							studentLoginId.toString(), password, isNew, headerMessage.toString(), isTrial,token);

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

	private void sendNotification(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,
			int roleTypeId, String loginId, String password, boolean isNew, String headerMessage, boolean isTrial,String token)
			throws Exception {
		FacilitatorDetailsDTO facilitatorDetailsDTO = new FacilitatorDetailsDAO()
				.getFacilitatorDetailsById(seDetailsDTO.getFacilitatorId());
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
		String tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
		parameterMap.put("parentName", studentDetailsDTO.getFathername());

		// Start SASEDEVE edited by Mrutyunjaya on date 1-08-2017

		parameterMap.put("studentname", studentDetailsDTO.getName());

		if (isNew) {
			parameterMap.put("loginId", loginId);
			parameterMap.put("userPassword", password);

			if (studentDetailsDTO != null && studentDetailsDTO.getClassId() == 5) {
				tempalteName = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
				tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
			} else if (studentDetailsDTO != null
					&& (studentDetailsDTO.getClassId() == 3 || studentDetailsDTO.getClassId() == 4)) {
				tempalteName = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
				tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
			} else {
				tempalteName = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
				tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
			}

		}

		// END SASEDEVE edited by Mrutyunjaya on date 1-08-2017

		if (isTrial) {

			tempalteName = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
			tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.LearningStyleTest.name();
		}
		parameterMap.put("headerMessage", headerMessage);
 

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
			PasswordGeneratorService.sendNewNotificationLST(parameterMap, studentDetailsDTO.getName(), StudentEmailID,
					roleTypeId, tempalteName, "",token);
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

	 
	public StudentDetailsDTO getStudentDTO(Integer studentId) {
		StudentDetailsDTO studentDetailsDTO = null;
		try {
			studentDetailsDTO = new StudentDetailsDAO().getStudentObjectById(studentId);
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

			new StudentDetailsDAO().deleteUserDetailsById(studentDTO.getId());
			auditDateStr = TimeUtil.getDateFromMillis(System.currentTimeMillis(), TimeUtil.QUERY_DATE_FORMAT);
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return EActionStatus.FAILURE;
		}
		return EActionStatus.SUCCESS;
	}

	 // Start Sasedeve Edited by Mrutyunjaya on date 08-08-2017

	private void sendNotificationForUpdate(StudentDetailsDTO studentDetailsDTO, SessionScheduleDetailsDTO seDetailsDTO,
			int roleTypeId, String MessageString) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String tempalteName = null;
		String tempalteNameParent = null;

		if (studentDetailsDTO != null && studentDetailsDTO.getClassId() == 5) {
			tempalteName = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Student
					.name();
			tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Parent
					.name();

		} else if (studentDetailsDTO != null
				&& (studentDetailsDTO.getClassId() == 3 || studentDetailsDTO.getClassId() == 4)) {
			tempalteName = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Student
					.name();
			tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_11_12_12Plus_for_Parent
					.name();
		} else {
			tempalteName = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_9_10_for_Student.name();
			tempalteNameParent = NotificationConstant.MessageTemplateNameAndType.UPDATE_STUDENT_9_10_for_Parent.name();
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
		parameterMap.put("DueAmount", (studentDetailsDTO.getAgreedAmount() - studentDetailsDTO.getPaidAmount()));

		String DATE_FORMAT = "dd-MMM-yyyy";
		String TIME_FORMAT = "hh:mm a";
		if (seDetailsDTO != null && seDetailsDTO.getSession1Date() != null) {
			parameterMap.put("session1Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), DATE_FORMAT));
			parameterMap.put("session1Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession1Date(), TIME_FORMAT));

		}

		if (seDetailsDTO != null && seDetailsDTO.getSession2Date() != null) {
			parameterMap.put("session2Date", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), DATE_FORMAT));
			parameterMap.put("session2Time", TimeUtil.convertTimeAsString(seDetailsDTO.getSession2Date(), TIME_FORMAT));

		}

		if (seDetailsDTO != null && seDetailsDTO.getSession3Date() != null) {
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

	// end Sasedeve Edited by Mrutyunjaya on date 08-08-2017

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
				timeList.add("0" + startTime + ":15");
				timeList.add("0" + startTime + ":30");
				timeList.add("0" + startTime + ":45");
			} else {
				timeList.add(startTime + ":00");
				if (startTime != 20) {
					timeList.add(startTime + ":15");
					timeList.add(startTime + ":30");
					timeList.add(startTime + ":45");
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
					? Integer.parseInt(globalSettingDTO.getPropertyValue())
					: -1;
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
		List<StudentDetailsDTO> list = new StudentDetailsDAO().getStudentForInterestTestBulk();
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

		List<VenueDetailDTO> list = new StudentDetailsDAO().getAllvenueDetail();
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
						product.put("adress",
								(VenueDetailDTO.getAddress()).replaceAll("\\r|\\n", " ").replaceAll("'", "'+'"));
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

				VenueAdressDtailMap.put(venueDetailDTO.getVenue().replaceAll("'", "'+'"),
						venueDetailDTO.getAddress().replaceAll("'", "'+'"));
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
						// TODO : to fix ' issue
						product.put("Name", schoolDTO.getName().replaceAll("'", "'+'"));
						product.put("code", schoolDTO.getCode());
						product.put("id", String.valueOf(schoolDTO.getId()));
						SchoolMapList.add(product);

					}
					if (cityDTO.getId() == 326) {
						if (schoolDTO.getCityId() == 0) {
							// TODO : to fix ' issue
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

					if (!ALLPreviousFacititorIDSet.contains(String.valueOf(FacilitatorCityMapDTO.getFacilitatorId()))) {

						if (cityDTO.getId() == 326) {
							if (FacilitatorCityMapDTO.getCityId() == 0) {
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

 
	public List<StudentVO> getStudentDetailsByLoginId(String studentLoginId) 
	{
		List<StudentVO> finalList = new ArrayList<StudentVO>();
		StudentDetailsDTO detailsDTO = new StudentDetailsDTO();
		detailsDTO.setLoginId(studentLoginId);
		try {

			StudentDetailsDAO studentDetailsDAO = new StudentDetailsDAO();

			StudentDetailsDTO studentDetailsDTO = studentDetailsDAO.getStudentDetailsByLoginId(studentLoginId);
			if (true) {
				StudentVO studentVO = null;
				studentVO = new StudentVO();
				if (null != studentDetailsDTO.getSeDetailsDTO()) {

					if (null == studentDetailsDTO.getSeDetailsDTO().getVenue()) {

						studentDetailsDTO.setVenue("-");
					}

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
						&& ApplicationConstants.OTHER_SCHHOL_CODE.equalsIgnoreCase(studentDetailsDTO.getSchoolCode())
						&& ApplicationConstants.OTHER_SCHHOL.equalsIgnoreCase(studentDetailsDTO.getSchoolName())) {
					studentDetailsDTO.setSchoolName(studentDetailsDTO.getOtherSchool());
				}
				BeanUtils.copyProperties(studentVO, studentDetailsDTO);

				studentVO.setStudentTypeStr(
						studentDetailsDTO.getStudentType() == null ? "" : studentDetailsDTO.getStudentType().getText());
				studentVO.setActive((studentDetailsDTO.getIsActive().equalsIgnoreCase("Y") ? true : false));
				finalList.add(studentVO);

			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return finalList;

	}
	
	
	public String createToken(Integer studentId, String ldId,String password)
	{
		Date date = new Date();
		JSONObject jsonobj =new JSONObject();
		jsonobj.put("StudentId", studentId.toString());
		jsonobj.put("LdId", ldId);
		jsonobj.put("password",password);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		jsonobj.put("date",formatter.format(date));
		return new EncryptionDecryptionData().Encrypt(String.valueOf(jsonobj));
		
	}
	

}
