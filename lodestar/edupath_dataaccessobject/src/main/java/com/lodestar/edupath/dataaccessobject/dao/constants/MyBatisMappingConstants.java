package com.lodestar.edupath.dataaccessobject.dao.constants;

public interface MyBatisMappingConstants {
	String INSERT_MESSAGE_QUEUE = "MessageQueue.insertMessageQueue";
	String UPDATE_MESSAGE_QUEUE = "MessageQueue.updateMessageQueue";
	String GET_MESSAGE_QUEUE_BY_ID = "MessageQueue.getMessageQueueById";
	String GET_ALL_INPROGRESS_MESSAGE = "MessageQueue.getAllInProgressMessages";

	String GET_EMAIL_SETTINGS = "EmailSettingMaster.getEmailSettings";

	String INSERT_NEW_MSG_TEMPLATE = "MessageTemplate.insertMessageTemplate";
	String GET_TEMPLATE_BY_NOTIFICATION_N_MESSAGE_TYPE = "MessageTemplate.getTemplateByNotifyAndMsgType";

	String GET_ALL_GLOBAL_SETTINGS = "GlobalSettings.getAllGlobalSetting";

	String GET_GLOBAL_SETTINGS_BY_PROPERTY = "GlobalSettings.getSettingsByKey";

	// SubAdmin Details
	String GET_ALL_SUBADMIN = "SubAdmin.getAllSubAdmin";
	String GET_SUBADMIN_BY_ID = "SubAdmin.getAllSubAdminById";
	String INSERT_SUBADMIN = "SubAdmin.insertSubAdminDetails";
	String DELETE_SUBADMIN_BY_ID = "SubAdmin.deleteSubAdminById";
	String UPDATE_SUBADMIN_BY_ID = "SubAdmin.updateSubAdmin";
	String GET_SUBADMIN_BY_EMAIL_ID = "SubAdmin.getSubAdminByEmailId";

	// Facilitator Details
	String GET_ALL_Facilitator_Details = "FacilitatorDetails.getAllFacilitatorDetails";
	String GET_Facilitator_Details_BY_ID = "FacilitatorDetails.getFacilitatorDetailsById";
	String INSERT_Facilitator_Details = "FacilitatorDetails.insertFacilitatorDetails";
	String DELETE_Facilitator_Details_BY_ID = "FacilitatorDetails.deleteFacilitatorDetailsById";
	String UPDATE_Facilitator_Details_BY_ID = "FacilitatorDetails.updateFacilitatorDetailsById";
	String GET_Facilitator_Details_BY_EMAIL_ID = "FacilitatorDetails.getFacilitatorDetailsByEmailId";
	String GET_ACTIVE_FACILITATOR = "FacilitatorDetails.getActiveFacilitator";
	String GET_SESSION_COUNT_FOR_DELETE = "SessionDetails.getSessionCountForDelete";
	String GET_FACILITATOR_BY_STUDENT_ID = "FacilitatorDetails.getFacilitatorByStudentId";

	// UserDetails
	String GET_USER_BY_LOGINID = "UserDetail.getUserDetailsByLoginId";
	String GET_USER_BY_LOGINID_AND_ID = "UserDetail.getUserDetailsByLoginIdAndID";
	String ADD_USER_DETAILS = "UserDetail.addUserDetail";
	String UPDATE_USER_DETAILS_BYID = "UserDetail.updateUserDetailById";
	String DELETE_USER_DETAILS_BYID = "UserDetail.deleteUserDetailById";
	String DELETE_USER_DETAILS_BY_ID = "UserDetail.deleteUserDetailsById";
	String DELETE_STUDENT_DETAIL_BY_LOGINID = "UserDetail.deleteUserDetailsByloginId";
	String GET_MAX_USER_DETAILS_ID = "UserDetail.getMaxIdValue";
	String UPDATE_PASSWORD_FOR_USER_DETAILS_BYID = "UserDetail.updatePasswordForUserDetailById";
	String GET_USER_DETAILS_BY_ID_AND_ROLEID = "UserDetail.getUserDetailsByIdAndRoleId";
	String UPDATE_USER_DETAILS_ISACTIVE_BY_ID = "UserDetail.updateUserDetailIsActiveById";

	String UPDATE_USER_DETAILS_Achirve_BY_ID = "UserDetail.updateUserDetailIsArchiveById";

	// StudentDetails
	String GET_STUDENT_DETAIL_BY_USERID = "StudentDetails.getStundentDetailsByUserId";
	String GET_STUDENT_ID = "StudentDetails.getStudentId";
	String GET_STUDENT_ID_FOR_CDD = "StudentDetails.getStudentIdforCDD";
	String GET_STUDENT_DETAIL_BY_STUDENTID = "StudentDetails.getStundentDetailsByStudentId";
	String GET_STUDENT_BY_USERID = "StudentDetails.getStundentDetailsByUsersId";
	String GET_STUDENT_BY_USERID_FOR_CDD_BULK = "StudentDetails.getStundentDetailsByUsersIdforCDDBulk";
	String GET_ALL_STUDENT_DETAILS_BY_FATHER_EMAIL_ID = "StudentDetails.getAllStundentDetailsByFaherEmailId";
	String GET_FACILITATOR_DETAIL_BY_USERID = "FacilitatorDetails.getFacilitatorDetailsByUserId";
	String GET_SUBADMIN_DETAIL_BY_USERID = "SubAdmin.getSubadminDetailsByUserId";
	String GET_STUDENT_DETAIL_AND_SESSION_BY_USERID = "StudentDetails.getStundentDetailsAndSessionByUserId";
	String GET_STUDENT_DETAIL_BY_ID = "StudentDetails.getStudentDetailById";
	String DELETE_STUDENT_BY_ID = "StudentDetails.deleteStudentById";
	String UPDATE_STUDENT_BY_ID = "StudentDetails.updateStudentById";
	String GET_ALL_STUDENT_DETAILS_BY_FACILITATOR_ID = "StudentDetails.getAllStundentDetailsByFacilitatorId";

	

	// Start Sasedeve Edited By Mrutyunjya on Date 17-11-2017

	// String GET_ALL_STUDENT_DETAILS_BY_FACILITATOR_ID_NEW =
	// "StudentDetails.getAllStundentDetailsByFacilitatorIdnew";

	// String GET_TUM_ANSWER_BY_STUDENT = "StudentDetails.gettumanswerbystudentid";

	// String GET_CGT_ANSWER_BY_STUDENT = "StudentDetails.getcgtanswerbystudentid";

	// END Sasedeve Edited By Mrutyunjya on Date 17-11-2017

	String GET_ALL_STUDENT_DETAILS_BY_DATE = "StudentDetails.getAllStundentDetailsByDate";
	String GET_STUDENT_INFO_DETAILS_BY_ID = "StudentDetails.getStudentInfoDetailsById";
	String GET_STUDENT_LIST_BY_SERCH_VALUE = "StudentDetails.getAllStudentDetailsBySearchValue";
	String GET_STUDENT_DETAILS_BY_STUDENT_LOGINID = "StudentDetails.getStundentDetailsByStudentLoginId";
	String STUDENT_DETAILS_GET_BY_STUDENT_ID = "StudentDetails.getStudentDetailsByStudentId";
	String GET_ALL_STUDENT_DETAILS_FOR_REVIEW_BY_FACILITATOR_ID = "StudentDetails.getStundentDetailsForReviewByFacilitatorId";
	String GET_FEEDBACK_DETAILS = "StudentDetails.getFeedbackDetails";
	String IS_EXIST_BY_SCHOOL_ID = "StudentDetails.isExistBySchoolId";
	String STUDENT_GET_FOR_INTEREST_TEST_BULK = "StudentDetails.getStudentForInterestBulk";
	String STUDENT_GET_FOR_TRIAL_REPORT_BYID = "StudentDetails.getStudentForTrailReportById";
	String STUDENT_GET_SUMMARY = "StudentDetails.getStundentDetailsForSummary";
	String STUDENT_GET_SUMMARY_FOR_EXCEL = "StudentDetails.getStundentDetailsForSummaryForExcel";
	String STUDENT_GET_SUMMARY_BY_ID = "StudentDetails.getStundentDetailsForSummaryById";

	// Added by bharath on Date 10-9-2019
	String TRAIL_STUDENT_GET_SUMMARY = "StudentDetails.getTrailStundentDetailsForSummary";
	String TRAIL_STUDENT_GET_SUMMARY_COUNT = "StudentDetails.getTrailStundentDetailsSummaryCount";

	// Added by bharath on Date 12-9-2019
	String COMPLETED_STUDENT_GET_SUMMARY = "StudentDetails.getCompletedStundentDetailsForSummary";
	String COMPLETED_STUDENT_GET_SUMMARY_COUNT = "StudentDetails.getCompletedStundentDetailsSummaryCount";
	// Added by bharath on Date 22-9-2019
	String UPDATE_COMPLETE_STUDENT = "StudentDetails.completeStudentService";

	// Added by bharath on Date 11-10-2019
	String GET_STUDENT_DETAILS_BY_LOGINID = "StudentDetails.getStundentDetailsByLoginId";
	String GET_STUDENT_DETAILS_BY_REPORT_GENERATED = "StudentDetails.getStundentDetailsByReportGenerated";

	// Start SASEDEVE edited by Mrutyunjaya On Date 25-07-2018

	String STUDENT_GET_SUMMARY_Archive = "StudentDetails.getStundentDetailsForSummaryArchive";
	String STUDENT_GET_SUMMARY_COUNT_Archive = "StudentDetails.getStundentDetailsSummaryCountArchive";

	// Start SASEDEVE edited by Mrutyunjaya On Date 25-07-2018

	String STUDENT_GET_SUMMARY_COUNT = "StudentDetails.getStundentDetailsSummaryCount";

	// UserRole
	String GET_USERROLE_BY_NAME = "UserRole.getUserRoleByName";
	String INSERT_STUDENT_DETAILS = "StudentDetails.insertSStudentDetail";
	String GET_SESSION_REMAINDER1 = "SessionDetails.getAllSession1Reminder";
	String GET_SESSION_REMAINDER2 = "SessionDetails.getAllSession2Reminder";
	String GET_SESSION_REMAINDER3 = "SessionDetails.getAllSession3Reminder";
	String UPDATE_SESSION_REMAINDER1 = "SessionDetails.updateSession1Reminder";
	String UPDATE_SESSION_REMAINDER2 = "SessionDetails.updateSession2Reminder";
	String UPDATE_SESSION_REMAINDER3 = "SessionDetails.updateSession3Reminder";

	// Education Levels Details
	String GET_ALL_Education_Level_NAME_WITH_ID = "EducationLevel.getAllEducationLevelNameWithId";

	// City Details
	String GET_ALL_CITY_NAME_WITH_ID = "City.getAllCityNameWithId";
	// Class Details
	String GET_ALL_CLASS_NAME_WITH_ID = "Class.getAllClassNameWithId";
	// added by bharath on 20-03-2020
	String GET_CLASS_ID_BY_NAME = "Class.getClassByName";
	// School Details
	String GET_ALL_SCHOOL_NAME_WITH_ID = "School.getAllSchoolNameWithId";
	String GET_SCHOOL_BY_ID = "School.getSchoolById";
	String GET_ALL_SCHOOL = "School.getAllSchool";
	String IS_SCHOOL_EXIST = "School.isSchoolExist";
	String INSERT_SCHOOL_DETAILS = "School.insertSchoolDetails";
	String UPDATE_SCHOOL_DETAILS = "School.updateSchoolDetails";
	String DELETE_SCHOOL_DETAILS = "School.deleteSchoolDetails";
	// added by bharath on 23-05-2019
	String UPDATE_SCHOOL_DETAILS_WO_PATH = "School.updateSchoolDetailsWOPath";
	// added by bharath on 23-05-2019
	String GET_SCHOOL_REPORT_BY_SCHOOL_ID = "School.getReportBySchoolId";
	// added by bharath on 20-03-2020
	String GET_SCHOOL_BY_NAME = "School.getSchoolByName";

	// Student TUM
	String GET_STUDENT_TUM_FORSTUDENT = "StudentTUM.getStudentSavedTUM";
	String GET_STUDENT_TUM_FORSTUDENT_BY_SLNO = "StudentTUM.getStudentSavedTUMBySlNo";
	String INSERT_UPDATE_STUDENT_TUM = "StudentTUM.addStudentTUMDetail";
	String UPDATE_STUDENT_TUM = "StudentTUM.updateStudentTUMById";
	String DELETE_STUDENT_TUM_BYID = "StudentTUM.deleteStudentTUMById";
	String GET_STUDENTTUM_BY_ID = "StudentTUM.getStudentTUMBySlNo";
	String GET_STUDENTTUM_BY_STUDENT_ID = "StudentTUM.getStudentTUMByStudentId";
	String DELETE_STUDENT_TUM_BY_QuestionSlNo = "StudentTUM.deleteStudentTUMByQuestionSlNo";
	// added by bharath on 5/7/2019
	String GET_STUDENTTUM_LIST_BY_ID = "StudentTUM.getStudentTUMById";
	String UPDATE_STUDENT_TUM_CGT = "StudentTUMCGT.updatetumcgt";
	String INSERT_STUDENT_TUM_CGT = "StudentTUMCGT.inserttumcgt";
	String GET_STUDENTTUMCGT_BY_ID = "StudentTUMCGT.getTUMCGTById";

	// added by bharath on 6/7/2019
	String UPDATE_TUMCGT_APTITUDE = "StudentTUMCGT.updateTUMCGTAptitude";
	String INSERT_TUMCGT_APTITUDE = "StudentTUMCGT.insertTUMCGTAptitude";
	String UPDATE_TUMCGT_INTEREST = "StudentTUMCGT.updateTUMCGTinterset";
	String INSERT_TUMCGT_INTEREST = "StudentTUMCGT.insertTUMCGTinterset";

	// SessionScheduleDetails
	String GET_STUDENT_SESSION_BY_USERID = "SessionDetails.getStudentSessionByUserId";
	String GET_DETAILS_BY_FACILITATORID = "SessionDetails.getDetailsByFacilitatorId";
	String INSERT_SESSION_SCHEDULE_DETAILS = "SessionDetails.insertSessionSchedule";
	String DELETE_SESSION_SCHEDULE_BY_STUDENT_ID = "SessionDetails.deleteSessionByStudentId";
	String UNIQUE_COUNT_BY_SESSION_1_DATE = "SessionDetails.countBySession1Date";
	String UNIQUE_COUNT_BY_SESSION_2_DATE = "SessionDetails.countBySession2Date";
	String UNIQUE_COUNT_BY_SESSION_3_DATE = "SessionDetails.countBySession3Date";
	String UPDATE_SESSION_SCHEDULE_BY_STUDENT_ID = "SessionDetails.updateSessionScheduleByStudentId";
	String UPDATE_PRE_N_SESS1_COMPLETE_BY_STUDENT_ID = "SessionDetails.updatePreNFaci1CompleteByStudentId";
	String GET_STUDENT_SESSION_Details_BY_STUDENT_ID = "SessionDetails.getStudentSessionDetailsByStudentId";
	String GET_ALL_SESSION_DETAIL = "SessionDetails.getAllSessionDetails";
	String UPADTE_PRE_AND_SESSION_ONE_BY_STUDENT_ID = "SessionDetails.updatePreANDFaci1CompleteByStudentId";
	String UPADTE_SESSION_TWO_THREE_INCOMPLETE_BY_STUDENT_ID = "SessionDetails.updateSession2_3_IncompleteByStudentId";
	String GET_SESSION_DETAIL_WITH_FACILITATOR = "SessionDetails.getSessionDeatilsWithFacilitatorName";
	String UPDATE_SESSION_TWO_BY_STUDENTID = "SessionDetails.updateFaci2CompleteByStudentId";
	String UPDATE_SESSION3_FACICOMPLETED_BY_STUDENT_ID = "SessionDetails.updateSession3FaciCompletedByStudentId";
	String UPDATE_SENT_FOR_REVIEW_BY_STUDENT_ID = "SessionDetails.updateSentForReviewByStudentId";
	String UPDATE_REPORT_GENERATED_BY_STUDENT_ID = "SessionDetails.updateReportGeneratedByStudentId";
	String CHECK_IS_SENT_FOR_REVIEW_BY_STUDENT_ID = "SessionDetails.checkIsSentForReview";
	String UPDATE_SESSIONS_BY_STUDENT_ID = "SessionDetails.updateSessionsByStudentId";
	String UPDATE_STUDENT_FEED_BACK_TIME = "SessionDetails.updateStudentFeedBackTime";
	String UPDATE_PARENT_FEED_BACK_TIME = "SessionDetails.updateParentFeedBackTime";
	// Added by bharath on 22/9/2019
	String GET_FACILITATOR_SCHEDULE_BY_ID = "SessionDetails.getFacilitatorScheduleById";
	// Added by bharath on 14/11/2019
	String GET_STUDENT_DETAIL_REPORT_BY_SCHOOL = "SessionDetails.getCompleteStudentDetailsBySchoolId";

	// Student CGT
	String GET_STUDENT_CGT_BY_SESSION_FACTOR = "StudentCGT.getStudentCGTBySectionNFactor";
	String INSERT_STUDENT_CGT = "StudentCGT.insertStudentCGTAnswer";
	String UPDATE_STUDENT_CGT = "StudentCGT.updateStudentCGTAnswer";
	String GET_STUDENT_CGT_BY_STUDENTID = "StudentCGT.getStudentCGTByStudentId";
	String GET_ANSWERED_COUNT_BY_QUESTIONIDS = "StudentCGT.getAnsweredCountByQuestionIds";
	String STUDENTCGT_GET_ALL_FOR_BULK = "StudentCGT.getAllStudentCGTForBulk";

	// Student CGT Result
	String GET_STUDENT_CGT_RESULT = "StudentCGTResult.getStudentCGTResult";
	// START Sasedeve by Mrutyunjaya on date 26-04-2017
	String GET_STUDENT_CGT_RESULT_USERID = "StudentCGTResult.getStudentCGTResultUserID";
	// END Sasedeve by Mrutyunjaya on date 26-04-2017
	String INSERT_OR_UPDATE_STUDENT_CGT_RESULT = "StudentCGTResult.insertOrUpdateStudentCGTResult";
	String INSERT_OR_UPDATE_STUDENT_CGT_OCC_RESULT = "StudentCGTResult.insertOrUpdateStudentCGTOccResult";
	String DELETE_STUDENT_CGT_RESULT_BY_STUDENT_ID = "StudentCGTResult.deleteStudentCGTResultByStudentId";

	// CGT
	String GET_OCCUPATION_RAISEC_MAPPING = "RAISECCodeOccupation.getAllOccupationRAISEC";
	String GET_OCCUPATION_BY_RAISEC = "RAISECCodeOccupation.getOccupationsBYRAISEC";
	String GET_OCCUPATION_ABILITY_MAPPING = "OccupationAbilityMapping.getAllOccupationAbility";
	String GET_CGTQUESTIONNAIE_DEATILS_BY_SECTION = "CGTQuestioneries.getCGTQuestioneriesBySection";
	String GET_TABLE_REFERENCE_NORMS = "TableReferenceNorms.getTableReferenceNorms";

	String GET_APTITUDE_TEST_FACTOR = "AptitudeTestFactor.getAptitudeTestFactor";
	String GET_APTITUDE_TEST_FACTOR_BYFACTOR = "AptitudeTestFactor.getAptitudeTestFactorByFactor";

	// Tag search
	String GET_OCCUPATION_BY_SUBJECTS = "Tags.getOccupationBasedOnSubject";
	String GET_OCCUPATION_BY_STRENGTHS = "Tags.getOccupationBasedOnStrengths";
	String GET_OCCUPATION_BY_INTERESTS = "Tags.getOccupationBasedOnInterests";
	String GET_OCCUPATION_BY_ASPIRATIONS = "Tags.getOccupationBasedOnAspiration";
	String GET_OCCUPATION_BY_OCCPATION_IDS = "Tags.getOccupationBasedOnOccupationIds";
	String GET_OCCUPATION_BY_QUERY = "Tags.getOccupationBasedOnQuery";
	String GET_OCCUPATION_BY_INTERESTS_BY_AREA_ID = "Tags.getOccupationBasedOnInterestsByAreaId";
	String GET_ALL_MANUAL_SEARCH_MAP = "ManualSearchQuestionMapping.getManualSearchQuestionMap";
	String GET_OCCUPATION_BY_KEYWORD_FOR_MANUAL_SEARCH = "Tags.getOccupationByKeyword";

	// Bulk Upload
	String INSERT_UPLOAD_DETAILS = "BulkUpload.insertUploadDetails";
	String UPDATE_UPLOAD_DETAILS = "BulkUpload.updateUploadDetails";
	String UPDATE_STATUS_TIME = "BulkUpload.updateStatusAndCompletedTime";
	String GET_ALL_DETAILS = "BulkUpload.getAll";
	String UPDATE_IN_PROGRESS_RECORDS = "BulkUpload.updateInProgressStatus";

	// Area
	String GET_AREA_DETAIL_BY_INTERESTS = "Area.getAreaDetailsByInterests";
	String GET_RESULT_BY_CATEGORY_NAME = "Area.getDetailsByCategoryName";

	// Occupation
	String GET_OCCUPATION_DETAIL = "Occupation.getOccupationDetails";
	String GET_OCCUPATION_DETAIL_BY_ID = "Occupation.getOccupationDetailsById";
	String GET_OCCUPATION_BASIC_DETAIL_BY_ID = "Occupation.getOccupationBasicDetailsById";
	String GET_ALL_OCCUPATION_GLOSSARY_DETAILS = "Occupation.getAllOccupationGlossaryDetails";
	String GET_OCCUPATION_COUNT = "Occupation.getOccupationCount";
	String UPDATE_OCCUPATION_IMAGE = "Occupation.updateOccImage";
	String GET_OCCUPATION_NAME_AND_ID = "Occupation.getOccupationNameAndId";
	String GET_OCCUPATION_WITH_EXAMS_BY_INDUSTRY_ID = "Occupation.getOccWithExamsByIndustryid";
	String GET_OCCUPATION_WITH_COURSE_BY_INDUSTRY_ID = "Occupation.getOccWithCourseByIndustryid";
	String GET_OCCUPATION_NAME = "Occupation.getOccupationName";
	// added by bharath on 05-08-2020
	String GET_OCCUPATION_GLOSSARY_FOR_CLUSTEROCC = "Occupation.getOccupationGlossaryForClusterOCC";

	// added by bharath on 27-03-2020
	String GET_OCCUPATION_LIST_FOR_DH = "Occupation.getOccupationListForDH";

	String GET_OCC_DETAILS_BY_STUDENTID = "Occupation.getOccDetailsByStudentId";

	// OccpationAlerts
	String GET_OCCUPATION_ALERTS = "Alerts.getOccupationAlertsByOccId";

	//
	String GET_INDUSTRIES_BYOCCUPATION = "Industry.getIndustriesForOccupation";
	String GET_INDUSTRY_FROM_WISH_PRIMARY_BYOCCUPATION = "Industry.getIndustryFromWishOrPriForOcc";
	String GET_INDUSTRIES_BYID = "Industry.getIndustryById";

	String GET_INDUSTRY_DETAILS_BY_STUDENTID = "Industry.getIndustryDetailsByStudentId";

	// WishList
	String GET_WISHLIST_BY_STUDENTID = "WishList.getWishListByStudent";

	String GET_WISHLIST_BY_USERID = "WishList.getWishListByUserId";

	String INSERT_WISHLIST = "WishList.insertWishList";
	String DELETE_WISHLIST_BY_ID = "WishList.deleteById";
	String DELETE_WISHLIST_BY_OCCID = "WishList.deleteByOccupationId";
	String STUDENT_OCCS_N_INDS_GET_BY_STUDENT_ID = "WishList.getStudentOccsNIndsByStudentId";

	// DoYouKnow
	String GET_DOYOUKNOW_DETAIL = "DoYouKnow.getDoYouKnowDetails";

	// Forgot Password
	String INSERT_FORGOT_PASSWORD_Details = "ForgotPasswordRequest.insertForgotPasswordDetails";
	String GET_FORGOT_PASSWORD_Details_BY_CODE = "ForgotPasswordRequest.getForgotPasswordDetailsByCode";
	String UPDATE_FORGOT_PASSWORD_DETAILS_BY_USERID = "ForgotPasswordRequest.updateForgotPasswordDetailsByUserId";

	// Documents
	String GET_DOCUMENT_N_SHORTLISTID_BY_DOCID_BY_USERID = "Documents.getDocumentNShortListIdByIdNUserId";
	String GET_DOCUMENT_BY_OCCID = "Documents.getDocumentByOccId";
	String GET_DOCUMENT_BY_INDUSID = "Documents.getDocumentByIndustryId";
	String GET_DOCUMENT_BY_SUBJECTID = "Documents.getDocumentBySubjectId";
	String GET_LAST_WISH_LIST_VISITED_DOCUMENT = "Documents.getLastVisitedWishListDocument";
	String INSERT_DOCUMENT = "Documents.insertDocumentPath";
	String GET_ALL_DOCUMENT = "Documents.getAllDocument";

	String UPDATE_DOCUMENT_LAST_VISITED = "Documents.updateLastVisited";
	String UPDATE_DOCUMENT_HIGHLIGHTS = "Documents.updateDocumentHighlights";

	String GET_DOCUMENT_HIGHLIGHTS = "Documents.getDocumentsHighlights";
	String GET_VISITED_DOCUMENT_BYUSER_BYDOCID = "Documents.getVisitedDocumentByUserNDocId";
	String GET_VISITED_OCC_DOCUMENT_BYUSER = "Documents.getVisitedOccupationDocument";

	String GET_SUBJECT_DETAIL_BY_DOC_ID = "Documents.getDocumentNSubjectDetailById";

	// FacilitatorNotes
	String INSERT_FACILITATOR_NOTES = "FacilitatorNotes.insertNotes";
	String UPDATE_FACILITATOR_NOTES = "FacilitatorNotes.updateNotes";
	String GET_FACILITATOR_NOTES = "FacilitatorNotes.getNotes";

	// Student Notes
	String INSERT_STUDENT_NOTES = "StudentNotes.insertNotes";
	String UPDATE_STUDENT_NOTES = "StudentNotes.updateNotes";
	String GET_STUDENT_NOTES = "StudentNotes.getNotes";

	// ShortList
	String GET_SHORT_LIST_DETAILS_BY_STUDENT_ID = "ShortList.getShortListDetailsByStudentId";
	String INSERT_SHORT_LIST = "ShortList.insertShortList";
	String DELETE_SHORT_LIST_BY_ID = "ShortList.deleteShortListById";

	String GET_RELATED_OCCPATION_BY_OCC_ID = "OccupationRelatedOccupation.getRelatedOccupationDetails";

	String UPDATE_SHORT_LIST_BY_ID = "ShortList.updateShortListById";

	String GET_TOP_SHORTLIST_BY_STUDENTID = "ShortList.getTopShortListDetailsByStudentId";

	String GET_TOP_TWO_SHORTLIST_BY_STUDENTID = "ShortList.getTopTwoShortListDetailsByStudentId";

	// Languages
	String GET_LANGUAGES = "Languages.getAllLanguages";

	// View Log
	String GET_VIEW_LOG_BY_DATE = "AuditTrail.getAuditDetailByDate";
	String GET_VIEW_LOG_BY_DATE_WITH_MODULE = "AuditTrail.getAuditDetailByDateWithModule";
	String GET_MODULE_NAME = "AuditTrail.getModuleName";

	// Entrance Exams
	String GET_ENTRANCE_EXAMS_BY_OCC_ID = "EntranceExams.getEntranceExamsByOccId";
	String GET_ALL_ENTRANCE_EXAMS = "EntranceExams.getAllEntranceExams";
	String GET_SHORTLIST_ENTRANCE_EXAM_BY_STUDENTID = "EntranceExams.getShortListExamByStudentId";
	String GET_ENTRANCE_EXAMS_BY_WHEN = "EntranceExams.getEntranceExamsByWhen";
	String GET_ENTRANCE_EXAMS_BY_ID = "EntranceExams.getEntranceExamsById";
	String GET_ENTRANCE_EXAMSCOUNT_OCCID = "EntranceExams.getEntranceExamsCountByOccId";

	// short list exams
	String INSERT_SHORTLIST_EXAMS = "ShortListExams.insertShortListExams";
	String GET_COUNT_FOR_INSERT = "ShortListExams.getCountForInsert";
	String DELETE_SHORTLIST_EXAM_BY_EXAMID_N_STUDENTID = "ShortListExams.deleteshortListExamByExamIdNStudentId";
	String DELETE_SHORTLIST_EXAM_BY_STUDENTID = "ShortListExams.deleteshortListExamByStudentId";
	String GET_SHORT_LIST_EXAM_COUNT_BY_OCCID = "ShortListExams.getShortListExamsCountByoccId";
	String GET_SHORT_LIST_EXAM_COUNT = "ShortListExams.getShortListExamsCount";

	// Edupath ShortList
	String GET_EDUPATH_SHORTLIST_BY_STUDENTID = "EdupathShortList.getEduPathShortListByStudentId";
	String GET_EDUPATH_SHORTLIST_FOR_CART_BY_STUDENTID = "EdupathShortList.getEduPathForCartByStudentId";
	String DELETE_EDUPATH_SHORTLIST_BY_STUDENTID = "EdupathShortList.deleteEdupathShortListByStudentId";
	String GET_EDUPATH_SHORTLIST_ACTIVE_BY_STUDENTID = "EdupathShortList.getEduPathShortListByStudentIdWithActive";
	String GET_STREAM_ID_BY_STUDENT_ID = "EdupathShortList.getStreamIdByStudentId";
	String GET_OCC_N_INDUSTRY_DETAILS_BY_STUDENT_ID = "EdupathShortList.getOccNIndustryDetailsByStudentId";

	String INSERT_EDUPATH_SHORTLIST = "EdupathShortList.insertEdupathShortList";
	String INSERT_EDUPATH_ELECTIVE_SHORTLIST = "EdupathElectiveShortList.insertElectiveShortList";
	String GET_EDUPATH_ELECTIVE_SHORTLIST_BYSTUDENTID = "EdupathElectiveShortList.getElectiveShortListByStudentId";

	// Subject
	String GET_SUBJECT_BY_STUDENTID = "Subject.getSubjectListByStudentId";
	String GET_SUBJECT_BY_IDS = "Subject.getSubjectListByIds";
	String GET_SUB_STREAMN_COMBINATION_BY_STUDENT_ID = "Subject.getSubStreamNCombinationByStudentId";
	String GET_UNIQUE_SUB_BY_STUDENT_ID = "Subject.getUniqueSubByStudentId";

	// Combination
	String GET_COMBINATION_BY_STUDENTID = "Combination.getCombinationListByStudentId";

	String GET_COMBINATION_FOR_ELECTIVE_SCREEN = "Combination.getCombinationForElectives";
	String GET_ALL_COMBINATION = "Combination.getAllCombination";

	// Edupath ElectiveShortList
	String UPDATE_ELECTIVE_SHORT_LIST_BY_ID = "EdupathElectiveShortList.updateOrderNoForCombinationId";
	String DELETE_EDUPATH_ELECTIVE_SHORTLIST_BY_STUDENTID = "EdupathElectiveShortList.deleteEdupathElectiveByStudentId";
	String GET_COMBINATIONS_BY_STUDENT_ID = "EdupathElectiveShortList.combinationIdsByStudentId";

	// integrated course
	String GET_INTEGRATED_COURSE_BY_OCC_ID = "IntegratedCourse.getIntegratedCourseByOccId";
	String GET_SHORTLIST_INTEGRATED_COURSE_BY_STUDENTID = "IntegratedCourse.getShortListIntegratedCourseByStudentId";
	String GET_ALL_INTEGRATED_COURSE = "IntegratedCourse.getAllIntegratedCourse";
	String GET_INTEGRATED_COURSE_BY_ID = "IntegratedCourse.getIntegratedCourseById";
	String GET_INTEGRATED_COURSE_BY_INSTITUTE = "IntegratedCourse.getIntegratedCourseByInstitute";
	String GET_INTEGRATED_COURSE_COUNT_BY_OCC_ID = "IntegratedCourse.getAllIntegratedCourseCountByoccId";

	// short list course
	String INSERT_SHORTLIST_COURSE = "ShortListCourse.insertShortListCourse";
	String GET_COUNT_FOR_INSERT_COURSE = "ShortListCourse.getCountForInsertCourse";
	String DELETE_SHORTLIST_COURSE_BY_COURSEID_N_STUDENTID = "ShortListCourse.deleteCourseByCourseIdNStudentId";
	String DELETE_SHORTLIST_COURSE_BY_STUDENTID = "ShortListCourse.deleteCourseByStudentId";
	String GET_SHORT_COURSE_LIST_COUNT_BY_OCC_ID = "ShortListCourse.getCountForIntegratedCourse";
	String GET_SHORT_LIST_COURSE_COUNT = "ShortListCourse.getShortListCourseCount";

	// EduPath
	String GET_EDUPATH_BY_IDS = "EduPath.getEduPathDetailByIds";
	String GET_EDU_PATH_STREAM = "EduPath.eduPathStreamsByIds";
	String GET_EDU_PATH_COUNT_PU_STREAM_BY_INDUS = "EduPath.getCountsForPUStreamByIndustry";
	String GET_EDU_PATH_DEGREE_STREAM_BY_INDUS = "EduPath.getDegreeByIndustry";
	String GET_EDU_PATH_DEGREE_SPECIAL_BY_INDUS = "EduPath.getDegreeSpecializationByIndustry";
	String PROC_EDU_PATH_DEGREE = "EduPath.executeDegreeProcedure";
	String PROC_EDU_PATH_DEGREE_SPECIAL = "EduPath.executeDegreeSpecializationProcedure";

	// EduPath El
	String GET_PU_ELECTIVES_BY_IDS = "EduPathPUElectives.getPUElectiveDetailByIds";

	// EduPath Degrees
	String GET_EDUPATH_DEGREES_BY_ID = "EduPathDegrees.getEduPathDegreesByIds";
	String GET_DEGREE_BY_EDUPATHID = "EduPathDegrees.getEdupathDegreesByEdupathIds";

	// EduPath Degrees Specialization
	String GET_EDUPATH_DEGREES_SP_BY_ID = "EduPathDegreeSpecialization.getEduPathDegreesSpecializationByIds";
	String GET_DEGREE_SP__BY_EDUPATHIDS = "EduPathDegreeSpecialization.getEdupathDegreesSpecializationByEdupathIds";

	// EduPath PG
	String GET_EDUPATH_PG_BY_ID = "EduPathPG.getEduPathPGByIds";

	// EduPath PG Specialization
	String GET_EDUPATH_PG_SP_BY_ID = "EduPathPGSpecialization.getEduPathPGSpecializationByIds";

	// Stream
	String GET_ALL_STREAMS = "Stream.getAllStream";

	// PUStreams
	String GET_PUSTREAMS_BY_OCCID = "EduPathPUStreams.getPUStreamsByEduPathId";

	// StudentCollegeParameters
	String GET_STUDENT_PARAMETERS = "StudentCollegeParameters.getStudentCollegeParametersDetails";
	String INSERT_STUDENT_PARAMETERS = "StudentCollegeParameters.addStudentCollegeParametersDetail";
	String GET_STUDENT_PARAMETERS_BY_STUDENTID = "StudentCollegeParameters.getStudentCollegeParametersDetailsByStudentId";
	String UPDATE_STUDENT_PARAMETERS_BY_STUDENTID = "StudentCollegeParameters.updateStudentCollegeParametersByStudentId";

	// Tutorials
	String TUTORIALS_GET_ALL = "Tutorials.getAllTutorials";
	String TUTORIALS_GET_DETAILS_BY_ID = "Tutorials.getTutorialsDetailsById";
	String TUTORIALS_GET_BY_FILTER = "Tutorials.getTutorialsByFilter";
	String TUTORIALS_GET_BY_EXAM_ID = "Tutorials.getTutorialsByExamId";
	String TUTORIALS_GET_BY_CITY_ID = "Tutorials.getTutorialsByCityId";

	// College
	String COLLEGE_GET_ALL = "College.getAllCollege";
	String COLLEGE_GET_DETAILS_BY_ID = "College.getCollegeDetailsById";
	String COLLEGE_GET_BY_FILTER = "College.getCollegeByFilter";
	String COLLEGE_GET_DETAILS_BY_STREAM_ID = "College.getCollegeDetailsByStreamId";
	String COLLEGE_GET_DETAILS_BY_COMBINATION_ID = "College.getCollegeDetailsByCombinationId";
	String COLLEGE_GET_DETAILS_BY_BOARD_ID = "College.getCollegeDetailsByBoardId";
	String COLLEGE_GET_DETAILS_BY_CITY_ID = "College.getCollegeDetailsByCityId";
	String COLLEGE_GET_DETAILS_BY_IDS = "College.getCollegeDetailsByIds";
	String COLLEGE_GET_ZONES = "College.getCollegeZones";

	// Board
	String BOARD_GET_ALL = "Board.getAllBoard";

	// Tutorial City Centers
	String TUTORIAL_CITY_CENTERS_GET_ALL = "TutorialCityCenters.getAllTutorialCityCenters";
	String TUTORIAL_CITY_CENTERS_GET_ALL_BY_TUTORIAL_IDS = "TutorialCityCenters.getTutorialCentersByTutorialId";

	// College Activities
	String COLLEGE_ACTIVITIES_GET_ALL = "CollegeActivities.getAllCollegeActivities";
	String GET_DISTINCT_ACTIVITIES = "CollegeActivities.getDistinctActivities";

	// College Infra
	String COLLEGE_INFRA_GET_ALL = "CollegeInfra.getAllCollegeInfra";
	String GET_DISTINCT_INFRA = "CollegeInfra.getDistinctInfra";

	// tutorial rank
	String TUTORIAL_RANK_GET_BY_TUTORIAL_ID = "TutorialRank.getTutorialRankByTutorialId";

	// College parameters
	String COLLEGE_PARAMETERS_GET = "CollegeParameters.getCollegeParameters";

	// Start Sasedeve Edited By Mrutyunjaya On Date 17-04-2017

	String BoardNotListed = "CollegeParameters.getNotlistedBoard";
	String BoardListed = "CollegeParameters.getlistedBoard";

	// End Sasedeve Edited By Mrutyunjaya On Date 17-04-2017

	// tutorial center shortlist
	String STUDENT_TUTORIAL_CENTRE_SHORTLIST_INSERT = "StudentTutorialCentreShortList.insertShortListTutorialCenters";
	String STUDENT_TUTORIAL_CENTRE_SHORTLIST_GET_BY_STUDENTID = "StudentTutorialCentreShortList.getShortListedTutorialCentersByStudentId";
	String STUDENT_TUTORIAL_CENTRE_SHORTLIST_DELETE_BY_ID = "StudentTutorialCentreShortList.deleteShortListedTutorialCentersById";
	String STUDENT_TUTORIAL_CENTRE_SHORTLIST_GET_ALL_BY_STUDENTID = "StudentTutorialCentreShortList.getAllByStudentId";

	// college shortlist
	String STUDENT_COLLEGE_SHORTLIST_GET_BY_STUDENTID = "StudentCollegeShortList.getShortListedCollegeByStudentId";
	String STUDENT_COLLEGE_SHORTLIST_DELETE_BY_ID = "StudentCollegeShortList.deleteShortListedCollegeById";
	String STUDENT_COLLEGE_SHORTLIST_INSERT = "StudentCollegeShortList.insertStudentCollegeShortlist";

	// Start SASEDEVE Edited by Mrutyunjaya on date 07-06-2017

	String STUDENT_COLLEGE_SHORTLIST_Seat = "StudentCollegeShortList.Seat";
	String STUDENT_COLLEGE_SHORTLIST_Free = "StudentCollegeShortList.Free";

	String STUDENT_COLLEGE_SHORTLIST_appcost_pass_teaching_Cutof = "StudentCollegeShortList.appcost_pass_teaching_Cutof";

	String List_Combination_report = "StudentCollegeShortList.ListCombinationreport";

	// END SASEDEVE Edited by Mrutyunjaya on date 07-06-2017

	// Report review comments
	String REPORT_REVIEW_COMMENTS_INSERT = "ReportReviewComments.insertReportReviewComments";
	String REPORT_REVIEW_COMMENTS_GET = "ReportReviewComments.getReportReviewComments";
	String REPORT_REVIEW_COMMENTS_UPDATE = "ReportReviewComments.updateReportReviewComments";

	// Report comments
	String REPORT_COMMENTS_INSERT = "ReportComments.insertReportComments";
	String REPORT_COMMENTS_UPDATE = "ReportComments.updateReportComments";
	String REPORT_COMMENTS_GET_BY_STUD_FACI_ID = "ReportComments.getReportCommentsStudFaciId";

	// Parent feedback form question
	String PARENT_FEED_BACK_FORM_QUESTION_GET = "ParentFeedbackFormQuestion.getParentFeedbackFormQuestion";

	// Student feedback form question
	String STUDENT_FEED_BACK_FORM_QUESTION_GET = "StudentFeedbackFormQuestion.getStudentFeedbackFormQuestion";

	// Parent feedback form
	String PARENT_FEED_BACK_FORM_GET_BY_STUDENT_ID = "ParentFeedbackForm.getParentFeedbackFormByStudentId";
	String PARENT_FEED_BACK_FORM_INSERT = "ParentFeedbackForm.insertParentFeedbackForm";
	String PARENT_FEED_BACK_FORM_UPDATE = "ParentFeedbackForm.updateParentFeedbackForm";
	String PARENT_FEED_BACK_FORM_IS_ANSWERED = "ParentFeedbackForm.isAnsweredParentFeedbackForm";

	// Student feedback form
	String STUDENT_FEED_BACK_FORM_GET_BY_STUDENT_ID = "StudentFeedbackForm.getStudentFeedbackFormByStudentId";
	String STUDENT_FEED_BACK_FORM_INSERT = "StudentFeedbackForm.insertStudentFeedbackForm";
	String STUDENT_FEED_BACK_FORM_UPDATE = "StudentFeedbackForm.updateStudentFeedbackForm";
	String STUDENT_FEED_BACK_FORM_IS_ANSWERED = "StudentFeedbackForm.isAnsweredStudentFeedbackForm";

	// Trial Interest Code Mapping
	// ================Start Sasedeve edited by Mrutyunjaya on Date
	// 1-04-2017==================================
	String TRIAL_INTREST_CODE_GET_ALL_10 = "TrialInterestCodeMapping.getTrialInterest_10";
	String TRIAL_INTREST_CODE_GET_ALL_11 = "TrialInterestCodeMapping.getTrialInterest_11";
	String TRIAL_INTREST_CODE_BY_RE_10 = "TrialInterestCodeMapping.getTrialIntestCodeByRaisecCode_10";
	String TRIAL_INTREST_CODE_BY_RE_11 = "TrialInterestCodeMapping.getTrialIntestCodeByRaisecCode_11";
	// ================END Sasedeve edited by Mrutyunjaya on Date
	// 1-04-2017==================================
	// vyankatesh payment mapping
	String INSERT_Payment_Details = "PaymentDetails.insertPaymentDetails";
	String GET_ALL_STUDENTID_WITH_ID = "PaymentMapDetails.getallstudentidwithid";
	String GET_ALL_StudentID = "PaymentDetails.getallstudentID";
	String UPDATE_PaymentDetail_BY_StudentID = "PaymentDetails.updatePaymentDetailbyStudentId";
	String GET_ALL_LoginID_Details = "PaymentDetails.getallLoginId";
	String UPDATE_PAYMENT_STUDENT_BY_ID = "PaymentDetails.updatePaymentbyStudentId";

	// vyankatesh Venue mapping
	String VENUE_ADDRESS_GET_ALL = "VenueDetails.getAllVenueAdress";

	// vyankatesh Facilitator city mapping
	String INSERT_FacilitatorCity_Map_Details = "FacilitatorDetails.insertFacilitatorcityDetails";
	String INSERT_SCHOOLMAPING_CITY = "School.insertSchoolMappingCity";
	String GET_ALL_FACILITATOR_CITY = "FacilitatorDetails.getAllFacilitaorcityList";
	String ADD_TRIAL_STUDENT_EXTRA_DETAIL = "StudentDetails.addTrialStudentExtraDetail";
	String GET_ALL_FACETOFACEID_FACILITATOR_ID = "FacilitatorDetails.getAllFacetofaceIdFacilitatorId";
	String GET_ALL_ONCALL_FACILITATOR_ID = "FacilitatorDetails.getAllonCallCityIdFacilitatorId";
	String GET_ALL_StudentID_FROM_TRIAL_EXTRA_STUDENT = "StudentDetails.gellALLStudentIDFromTrislExtraStudent";
	String UPDATE_TRIAL_EXTRA_STUDENT_BY_ID = "StudentDetails.updateTrialExtraStudentById";
	String UPDATE_FacilitatorCity_Map_Details_BY_ID = "FacilitatorDetails.updateFacilitatorCitymapDetailBYId";
	// Added by bharath on Date 10-9-2019
	String GET_FACILITATOR_STATS_REPORT = "FacilitatorDetails.getFacilitatorStatsReport";

	// Session 2 Vyankatesh National Exams
	String GET_ENTRANCE_EXAMS_BY_OCC_ID_National = "EntranceExams.getEntranceExamsByOccIdNational";
	String GET_ENTRANCE_EXAMS_BY_OCC_ID_State = "EntranceExams.getEntranceExamsByOccIdState";
	String GET_ALL_StudentIDLOCK_CITY = "StudentCityLock.getAllStudentId";
	String INSERT_LOCK_VALUE = "StudentCityLock.insertlockdetail";
	String UPDATE_LOCK_VALUE = "StudentCityLock.updatelockdetail";
	String GET_CITYID_BY_STUDENTID = "StudentCityLock.getCityIdByStudentId";
	String GET_SHORT_LIST_EXAM_CITY_BY_STUDENTID = "ShortListExams.getShortListExamsbyStudenId";
	String DELETE_PREVIOUS_EXAM_BY_CITY_N_STUDENTID = "ShortListExams.deletePriviousExamBycitynStudentId";
	// ================start Sasedeve edited by vyankatesh on
	// =================================6-5-2017
	String GET_ALL_FACILITAOR_MAPPING_ID = "FacilitatorDetails.getFacilitatorMappingId";
	// ================End Sasedeve edited by vyankatesh on
	// =================================6-5-2017

	// Start SASEDEVE edited by Mrutyunjaya On Date 12-06-2017

	String InsertTransactionDetails = "TransactionDetails.insertDetails";
	String AutoUpdatePayment = "TransactionDetails.AutoUpdatePaymentbyStudentID";

	// End SASEDEVE edited by Mrutyunjaya On Date 12-06-2017

	// Start SASEDEVE edited by Mrutyunjaya On Date 19-07-2017

	String SessionOneQuestion = "SessionFeedBack.SessionOneFeedBackQuestion";
	String SessionFeedBackAnswerInsert = "SessionFeedBack.insertsessionfeedback";
	String GET_DEATILS = "SessionFeedBack.GetDeatils";

	String SessionOneFeedBackCount = "SessionFeedBack.CountSessionOneFeedBack";
	String SessionTwoFeedBackCount = "SessionFeedBack.CountSessionTwoFeedBack";

	String Get_User_Details_UserID = "SessionFeedBack.GEtuserdetailbyuserid";

	String ADD_PARTNER_STUDENT_DETAILS = "APIS.addpartnerstudentDetail";
	String Update_STUDENTUIN = "APIS.updatestudentuin";

	String GetPartnetStudentDetails = "APIS.partnerstudentdetails";
	String GetPartnetDetails = "APIS.partnerdetail";
	String APIValidate = "APIS.ValidateAPI";
	String DuplicateValidate = "APIS.DuplicateionCheck";

	String INSERT_MESSAGE_QUEUE_FAILED = "MessageQueue.insertMessageQueueFailed";

	String GET_MESSAGE_QUEUE_FAILED = "MessageQueue.getMessageQueueFailed";
	String GET_MESSAGE_QUEUE_FAILED_BYID = "MessageQueue.getMessageQueueFailedbyid";
	String UPDATE_MESSAGE_QUEUE_FAILED_BYID = "MessageQueue.updateMessageQueueFailedbyid";

	String Register_For_Call = "APIS.RegisterForCall";

	// End SASEDEVE edited by Mrutyunjaya On Date 19-07-2017

	// Start SASEDEVE edited by Mrutyunjaya On Date 14-09-2017

	String GetSessionTipsAndSuggestionFaciComplete = "TipsAndSuggestions.getsession1tipsandsuggestionsfacicomplete";

	String Insert_Tips_Data_Session1 = "TipsAndSuggestions.InsertTipsDataSession1";
	String Update_Tips_Data_Session1 = "TipsAndSuggestions.UpdateTipsDataSession1";

	String GS_Input_Answer_Data = "TipsAndSuggestions.GSInputAnswerData";

	String Insert_Tips_Data_Session2 = "TipsAndSuggestions.InsertTipsDataSession2";
	String Update_Tips_Data_Session2 = "TipsAndSuggestions.UpdateTipsDataSession2";

	String Get_GS_Iput_Data = "TipsAndSuggestions.GetGSIputData";

	String Student_UserDetails_By_Student_id = "TipsAndSuggestions.StudentUserDetailsByStudentid";

	String Insert_Lead_Parent = "LeadParent.InsertLeadParent";

	String Update_Lead_Token = "LeadParent.UpdateLeadToken";

	String Get_Lead_Details = "LeadParent.GetLeadDetails";

	String Update_Lead_Parent_Student = "LeadParent.UpdateLeadParentStudent";

	String Student_Test_Taken_SIAT = "LeadParent.StudentTestTakenSIAT";

	String Lead_Parent_Appointment_Booking = "LeadParent.LeadParentAppointmentBooking";

	String Lead_Parent_For_Reminder_email = "LeadParent.LeadParentForReminderemail";

	String Lead_Parent_For_Reminder_Update_Mail_Sent = "LeadParent.LeadParentForReminderUpdateMailSent";

	String Insert_New_Lead_Student = "LeadParent.InsertNewLeadStudent";

	String Get_Lead_Student = "LeadParent.GetLeadStudent";

	String LEad_Student_Test_Taken_SIAT = "LeadParent.LeadStudentTestTakenSIAT";

	// End SASEDEVE edited by Mrutyunjaya On Date 14-09-2017

	// Start SASEDEVE edited by Mrutyunjaya On Date 10-01-2018

	String Get_List_Occupation_Read = "LeadParent.GetListOccupationRead";

	String Get_List_Document = "LeadParent.GetListDocument";

	String Get_Total_industry = "LeadParent.GetTotalindustry";

	String Get_Total_Occupation = "LeadParent.GetTotalOccupation";

	String Insert_New_Lead_Child = "LeadParent.InsertNewLeadChild";

	// End SASEDEVE edited by Mrutyunjaya On Date 10-01-2018

	// Start SASEDEVE edited by Mrutyunjaya On Date 31-07-2018

	String List_OF_Archive_Student_ID = "StudentDetails.ListOFArchiveStudentID";

	// End SASEDEVE edited by Mrutyunjaya On Date 31-07-2018

	// DailyHunt Student details
	// start by bharath on 20-03-2020
	String INSERT_DH_STUDENT = "DHStudentDetails.insertStudentDetail";
	String GET_DHSTUDENT_DETAILS_BY_DHID = "DHStudentDetails.getDHStudentByDHID";
	String GET_DHSTUDENT_DETAILS_BY_ID = "DHStudentDetails.getDHStudentByID";
	String INSERT_DH_STUDENT_CGT = "DHStudentCGT.insertDHStudentCGTAnswer";
	String GET_DHSTUDENT_CGT_BY_SESSION_FACTOR = "DHStudentCGT.getStudentCGTBySectionNFactor";

	String GET_DH_STUDENT_CGT_RESULT = "DHStudentCGTResult.getDHStudentCGTResult";
	String INSERT_OR_UPDATE_DHSTUDENT_CGT_OCC_RESULT = "DHStudentCGTResult.insertOrUpdateDHStudentCGTOccResult";
	String GET_OCCUPATION_LIST_BASED_ON_STREAM = "Occupation.getOccupationForDHBasedOnStream";
	String GET_OCCUPATION_LIST_BASED_ON_STREAM_CHNK	="Occupation.getOccupationForChanakyaBasedOnStream";

	String INSERT_STUDENT_OCC = "DHStudentOccupation.insertorUpdateStudentOccupation";
	String GET_DH_STUDENT_OCC = "DHStudentOccupation.getDHStudentOccupation";
	String GET_OCCUPATION_FOR_TWELVE_PLUS_SCIENCE_MATH = "Occupation.getOccupationForTwelvePlusSCIENCE_MATH";
	String GET_OCCUPATION_FOR_TWELVE_PLUS_SCIENCE_WO_MATH = "Occupation.getOccupationForTwelvePlusSCIENCE_WO_MATH";
	String GET_OCCUPATION_FOR_TWELVE_PLUS_COMMERCE = "Occupation.getOccupationForTwelvePlusCOMMERCE";
	String GET_OCCUPATION_FOR_TWELVE_PLUS_ARTS = "Occupation.getOccupationForTwelvePlusARTS";
	String GET_RAISEC_CODE = "RaisecCode.getRaisecCode";
	String GET_PARTNER_DETAILS = "PartnerDeatils.getPartnerDeatils";
	String GET_PARTNER_DETAILS_BY_ID = "PartnerDeatils.getPartnerDeatilsById";
	// start by bharath on 21-05-2020
	String GET_DHSTUDENT_APTITUDE_DETAILS_BY_DHID = "DHStudentCGTResult.getDHStudentCGTResultBYDHID";
	String INSERT_DHSTUDENT_CGT_RESULT = "DHStudentCGTResult.insertDHStudentCGTResult";
	String GET_DH_STUDENT_PERSONALITYCODE_OCCUPATION = "DHStudentOccupation.getPersonalityCodeOccupationByStudentId";
	String GET_OCCUPATION_BY_OCCPATION_IDS_FOR_DAILYHUNT = "Tags.getOccupationBasedOnOccupationIdsForDailyHunt";
	String GET_DHSTUDENT_CGT_RESULT_BY_STUDENTID = "DHStudentCGTResult.getDHStudentCGTResultByStudentId";
	String UPDATE_DH_STUDENT_TYPE = "DHStudentDetails.updateDHStudentType";
	String INSERT_DH_STUDENT_EXTRA_DETAILS = "DHStudentExtraDetails.insertDHStudentExtraDetail";
	String GET_DHSTUDENT_EXTRA_DETAILS_BY_STUDENTID = "DHStudentExtraDetails.getDHStudentExtraDetailsByStudentID";
	String GET_ALL_DHSTUDENT_EXTRA_DETAILS = "DHStudentExtraDetails.getAllDHStudentExtraDetails";
	String GET_COMPLETE_DHSTUDENT_DETAILS_BY_STUDENTID = "DHStudentExtraDetails.getCompleteStudentDetailsByStudentId";
	String UPDATE_DHSTUDENT_CONVERSION = "DHStudentExtraDetails.updateDHStudentConversion";
	String UPDATE_DHSTUDENT_CGTRESULT_TOKEN = "DHStudentCGTResult.updateDhstudentCGTResultToken";
	String GET_DH_STUDENT_CGTRESULT_TOKEN = "DHStudentCGTResult.getStudentCGTResultByToken";
	String GET_DHEDUPATH = "DHEdupath.getDHEdupathbyOccId";
	String GET_DHEXAM_LIST = "DHEdupath.getDHExamList";
	String GET_DHSTUDENT_CGT_BY_STUDENTID = "DHStudentCGT.getDHstudentCGTByStudentId";
	String GET_DHSTUDENT_CGT_APTITUDE_BY_STUDENTID = "DHStudentCGT.getDHstudentCGTAptitudeByStudentId";
	String GET_STREAM_BY_RIASECCODE = "RiasecStreamSelector.getStreamByRiasecCode";
	String GET_STREAM_OCC_BY_RIASECCODE = "RiasecStreamSelector.getStreamSelectorOcc";
	String INSERT_STREAM_SELECTOR_STUDENT = "RiasecStreamSelector.insertStreamSelectorStudent";
	String INSERT_STREAM_SELECTOR_RESULT = "RiasecStreamSelector.insertOrUpdateStreamSelectorResult";
	String GET_STREAM_SELECTOR_RESULT = "RiasecStreamSelector.getStreamSelectorResult";
	String GET_STREAM_SELECTOR_DESCRIPTION = "RiasecStreamSelector.getStreamSelectorDescription";
	String GET_OCCUPATION_BY_OCCPATION_IDS_FOR_ENGINEERING_SELECTOR = "Tags.getOccupationBasedOnOccupationIdsForEngineeringSelector";
	String INSERT_OR_UPDATE_ENINEERING_SELECTOR_RESULT = "EngineeringSelector.insertOrUpdateEngineeringSelectorResult";
	String GET_STUDENT_CGT_COMPLETED_FOR_ENINEERING_SELECTOR_RESULT = "EngineeringSelector.getStudentCGTCCompletedList";
	String INSERT_EB_FAVOURITE_SUBJECT = "EngineeringSelector.insertEBFavouriteSubject";
	String GET_EB_FAVOURITE_SUBJECTRESULT = "EngineeringSelector.getEBFavouriteSubject";
	String GET_OCC_LIST_ENGINEER_S2_WITH_EXCEPTION = "Occupation.getOccupationListForEngineeringS2WithException";
	String INSERT_CDD_STREAM = "CareerDegreeDiscovery.insertCDDStream";
	String GET_CDD_STREAM = "CareerDegreeDiscovery.getCDDStream";
	String INSERT_OR_UPDATE_CDD_RESULT = "CareerDegreeDiscovery.insertOrUpdateCDDResult";
	String GET_CDD_RESULT = "CareerDegreeDiscovery.getCDDResult";
	String UPDATE_CLASSID_STUDENT_BY_ID = "StudentDetails.updateStudentClass";
	String INSERT_OR_UPDATE_STUDENT_CAREER_FITMENT = "CareerFitment.insertOrUpdateStudentCareerFitment";
	String GET_STUDENT_CAREER_FITMENT = "CareerFitment.getStudentCareerFitment";
	String GET_CLUSTER_BY_IDS = "CareerFitment.getClusterByIds";
	String GET_ALL_OCC_FOR_CAREER_FITMENT = "Occupation.getAllOccForCareerFitment";
	String INSERT_OR_UPDATE_JUST_FOR_LODESTAR = "CareerFitment.insertJustForLodestar";
	String GET_OCCUPATION_DEATILS_BY_NAME = "Occupation.getOccupationDeatilsByName";
	String GET_CLUSTER_BY_NAME = "CareerFitment.getClusterByName";
	String GET_CLUSTER_DETAILS = "CareerFitment.getClusterDetails";
	String GET_CF_WISH_LIST_BY_STUDENT_ID = "WishListCareerFitment.getWishListCareerFitmentByStudentId";
	String GET_ALL_CF_WISH_LIST_BY_STUDENT_ID = "WishListCareerFitment.getAllWishListCareerFitmentByStudentId";
	String GET_ALL_CF_OCCUPATION_GLOSSARY_DETAILS = "Occupation.getAllCFOccupationGlossaryDetails";
	String GET_SEARCH_CF_OCCUPATION_GLOSSARY_DETAILS = "Occupation.getSearchCFOccupationGlossaryDetails";

	String INSERT_CF_WISH_LIST_STUDENT_ID = "WishListCareerFitment.insertWishListCareerFitment";

	String DELETE_CF_WISH_LIST_BY_ID = "WishListCareerFitment.deleteWishListCareerFitmentById";
	String DELETE_CF_WISH_LIST_BY_OCCID = "WishListCareerFitment.deleteWishListCareerFitmentByOccId";
	String DELETE_CF_WISH_LIST_BY_CLUSTERID = "WishListCareerFitment.deleteWishListCareerFitmentByClusterId";

	String DELETE_CF_WISH_LIST_OPTION_A_BY_STUDENT_ID = "WishListCareerFitment.deleteOptionAWishListCareerFitmentByStudentId";

	String DELETE_CF_WISH_LIST_OPTION_B_BY_STUDENT_ID = "WishListCareerFitment.deleteOptionBWishListCareerFitmentByStudentId";
	String GET_RIASEC_ACTIVITY_LIST = "CareerFitment.getRiasecActivityList";
	String INSERT_CF_OCC_NOT_FOUND_LOCK = "CareerFitment.insertOccnotFoundLock";
	String GET_CF_OCC_NOT_FOUND_LOCK = "CareerFitment.getOccnotFoundLock";

	// vark student
	String INSERT_VARK_STUDNET = "VarkStudent.insertVarkStudent";
	String GET_VARK_STUDENT_BY_STUDENTID = "VarkStudent.getVarkStudentByStudentId";
	String UPDATE_VARK_STUDENT_BY_STUDENTID = "VarkStudent.updateVarkStudentByStudentId";
	String GET_VARK_STUDENT_BY_TOKEN = "VarkStudent.getVarkStudentByToken";

	String INSERT_VARK_STUDNET_ANSWER = "VarkStudentAnswer.insertVarkStudentAnswer";
	String GET_NUMBER_OF_QUESTIONS_ANSWERED = "VarkStudentAnswer.getNumberOfQuestionsAnswered";
	String GET_VARK_STUDENT_ANSWER_BY_STUDENTID = "VarkStudentAnswer.getVarkStudentAnswerByStudentId";
	String DELETE_VARK_STUDENT__ANSWER = "VarkStudentAnswer.deleteVarkStudentAnswer";
	String INSERT_VARK_STUDNET_RESULT = "VarkStudentAnswer.insertOrUpdateVarkStudentResult";
	String GET_ALL_VARK_STUDENT_RESULT_BY_SCHOOLID = "VarkStudentAnswer.getAllVarkStudentResultBySchoolId";
	String GET_VARK_STUDENT_RESULT_BY_STUDENTID = "VarkStudentAnswer.getVarkStudentResultByStudentId";
	String GET_VARK_STUDENT_RESULT_BY_SCHOOLID = "VarkStudentAnswer.getVarkStudentResultBySchoolId";

	String INSERT_CLIENT_STUDENT = "ClientStudentDetails.insertStudentDetail";
	String GET_CLIENTSTUDENT_DETAILS_BY_CLIENTID = "ClientStudentDetails.getClientStudentByClientIdAndName";
	String GET_CLIENTSTUDENT_DETAILS_BY_ID = "ClientStudentDetails.getDHStudentByID";
	String INSERT_CLIENT_STUDENT_CGT = "ClientStudentCGT.insertDHStudentCGTAnswer";
	String GET_CLIENTSTUDENT_CGT_BY_SESSION_FACTOR = "ClientStudentCGT.getStudentCGTBySectionNFactor";

	String GET_CLIENT_STUDENT_CGT_RESULT = "ClientStudentCGTResult.getDHStudentCGTResult";
	String INSERT_OR_UPDATE_CLIENTSTUDENT_CGT_OCC_RESULT = "ClientStudentCGTResult.insertOrUpdateDHStudentCGTOccResult";
	String UPDATE_CLIENT_STUDENT_TYPE = "ClientStudentDetails.updateDHStudentType";
	String GET_CLIENTSTUDENT_CGT_BY_STUDENTID = "ClientStudentCGT.getDHstudentCGTByStudentId";
	String GET_CLIENTSTUDENT_CGT_APTITUDE_BY_STUDENTID = "ClientStudentCGT.getDHstudentCGTAptitudeByStudentId";

	String INSERT_CLIENTSTUDENT_OCC = "ClientStudentOccupation.insertorUpdateStudentOccupation";
	String GET_CLIENT_STUDENT_OCC = "ClientStudentOccupation.getClientStudentOccupation";
	String GET_CLIENT_STUDENT_PERSONALITYCODE_OCCUPATION = "ClientStudentOccupation.getPersonalityCodeOccupationByStudentId";
	String GET_OCCUPATION_LIST_BASED_ON_STREAM_FOR_CLIENT = "Occupation.getOccupationForClientBasedOnStream";
//		String GET_OCCUPATION_FOR_TWELVE_PLUS_SCIENCE_MATH					="Occupation.getOccupationForTwelvePlusSCIENCE_MATH";
//		String GET_OCCUPATION_FOR_TWELVE_PLUS_SCIENCE_WO_MATH				="Occupation.getOccupationForTwelvePlusSCIENCE_WO_MATH";
//		String GET_OCCUPATION_FOR_TWELVE_PLUS_COMMERCE						="Occupation.getOccupationForTwelvePlusCOMMERCE";
//		String GET_OCCUPATION_FOR_TWELVE_PLUS_ARTS							="Occupation.getOccupationForTwelvePlusARTS";
	String GET_RAISEC_CODE_FOR_CLIENT = "RaisecCode.getRaisecCodeForClient";
	String GET_CLIENT_STUDENT_APTITUDE_DETAILS_BY_DHID = "ClientStudentCGTResult.getDHStudentCGTResultBYDHID";
	String INSERT_CLIENT_STUDENT_CGT_RESULT = "ClientStudentCGTResult.insertDHStudentCGTResult";

//		String GET_OCCUPATION_BY_OCCPATION_IDS_FOR_DAILYHUNT				="Tags.getOccupationBasedOnOccupationIdsForDailyHunt";
	String GET_CLIENTSTUDENT_CGT_RESULT_BY_STUDENTID = "ClientStudentCGTResult.getDHStudentCGTResultByStudentId";
//		String UPDATE_DH_STUDENT_TYPE										="DHStudentDetails.updateDHStudentType";
//		String INSERT_DH_STUDENT_EXTRA_DETAILS								="DHStudentExtraDetails.insertDHStudentExtraDetail";
//		String GET_DHSTUDENT_EXTRA_DETAILS_BY_STUDENTID						="DHStudentExtraDetails.getDHStudentExtraDetailsByStudentID";
//		String GET_ALL_DHSTUDENT_EXTRA_DETAILS								="DHStudentExtraDetails.getAllDHStudentExtraDetails";
//		String GET_COMPLETE_DHSTUDENT_DETAILS_BY_STUDENTID					="DHStudentExtraDetails.getCompleteStudentDetailsByStudentId";
//		String UPDATE_DHSTUDENT_CONVERSION									="DHStudentExtraDetails.updateDHStudentConversion";
	String UPDATE_CLIENT_STUDENT_CGTRESULT_TOKEN = "ClientStudentCGTResult.updateDhstudentCGTResultToken";
	String GET_CLIENT_STUDENT_CGTRESULT_TOKEN = "ClientStudentCGTResult.getStudentCGTResultByToken";
	String GET_CLIENT_EDUPATH = "ClientEdupath.getEdupathbyOccId";
	String GET_CLIENT_EXAM_LIST = "ClientEdupath.getExamList";

	// for Chanakya client
	String INSERT_CHANAKYA_STUDENT_CGT = "ChanakyaStudentCGT.insertChanakyaStudentCGTAnswer";
	String INSERT_CHANAKYA_STUDENT = "ChanakyaStudentDetails.insertStudentDetail";
	String GET_CHANAKYASTUDENT_DETAILS_BY_CHNKID = "ChanakyaStudentDetails.getChanakyaStudentByCHNKID";
	String GET_CHANAKYASTUDENT_DETAILS_BY_ID = "ChanakyaStudentDetails.getChanakyaStudentByID";
	String UPDATE_CHANAKYA_STUDENT_TYPE = "ChanakyaStudentDetails.updateChanakyaStudentType";
	String GET_CHANAKYASTUDENT_CGT_BY_SESSION_FACTOR = "ChanakyaStudentCGT.getStudentCGTBySectionNFactor";
	String GET_CHANAKYASTUDENT_CGT_BY_STUDENTID = "ChanakyaStudentCGT.getStudentCGTByStudentId";
	String INSERT_CHNK_STUDENT_OCC = "ChanakyaStudentOccupation.insertorUpdateChanakyaStudentOccupation";
	String GET_CHNK_STUDENT_OCC = "ChanakyaStudentOccupation.getChanakyaStudentOccupation";
	String GET_CHNK_STUDENT_PERSONALITYCODE_OCCUPATION = "ChanakyaStudentOccupation.getPersonalityCodeOccupationByStudentId";
	String INSERT_DH_STUDENT_CHNK = "ChanakyaStudentCGT.insertChanakyaStudentCGTAnswer";

}