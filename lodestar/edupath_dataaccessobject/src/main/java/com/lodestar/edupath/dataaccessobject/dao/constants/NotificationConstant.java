/*
 * @(#) NotificationConstant.java  
 * 
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0 
 * @Date Mar 12, 2013
 * @Author Smrutiranjan Behera
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.dataaccessobject.dao.constants;

public class NotificationConstant
{

	public static final String	NOTIFICATION_TEMPLATE		= "notificationTemplate";
	public static final String	USER_ID						= "UserId";
	public static final String	USER_NAME					= "UserName";

	public static final String	SYSTEM_GEN_PASSWORD			= "SystemGeneratedPassword";
	public static final String	WEB_URL						= "WebURL";
	public static final String	WAID_TITLE					= "WAIDTitle";

	public static final String	RECIPIENT_MAIL_IDS			= "RecipientMailIds";
	public static final String	RECIPIENT_MOBILE_NUMBERS	= "RecipientMobileNumbers";
	
	public static final String	PARENT_NAME					= "parentName";

	public static final Integer	MAX_RETRAY_COUNT_INTERVAL	= 5;

	public enum MessageTemplateNameAndType
	{
		NEW_USER("NEWUSER"), FORGOT_PASSWORD("Forgot Password"), SESSION1_REMINDER("Session Reminder"), SESSION2_REMINDER("Session Reminder"), SESSION3_REMINDER(
				"Session Reminder"), NEW_STUDENT("NEWSTUDENT"), UPDATE_STUDENT("UPDATESTUDENT"), SESSION_RESCHEDULED("SESSIONRESCHEDULED"), UPDATE_FACILITATOR(
				"UPDATEFACILITATOR"), SESSION3_COMPLETED("SESSION3COMPLETED"), REPORT_READY("REPORTREADY"), REPORT_SENT_BACK("REPORTSENTBACK"), TRIAL_NEW_STUDENT(
				"TRIAL_NEWSTUDENT"), TRIAL_STUDENT_REPORT("TRIAL_STUDENTREPORT"), TRIAL_STUDENT_TEST_TAKEN("TRIAL_STUDENTTEST"), TRIAL_TO_FULL_STUDENT(
				"TRIAL_TO_FULLSTUDENT"),TRIAL_STUDENT_REPORT_PARENT("TRIAL_STUDENTREPORT_PARENT"),TRIAL_STUDENT_REPORT_NEW_TEMP("TRIAL_STUDENTREPORT_NEW_TEMP"),
		        TRIAL_NEW_STUDENT_NEW_TEMP("TRIAL_NEWSTUDENT_NEW_TEMP"),TRIAL_NEW_STUDEN_PARENT("TRIAL_NEWSTUDENT_PARENT"),TRIAL_STUDENT_FINAL_REPORT("TRIAL_STUDENTFINALREPORT"),TRIAL_STUDENT_FINAL_REPORT_PARENT("TRIAL_STUDENTFINALREPORT_PARENT"),TRIAL_STUDENT_REPORT_PARENT_BULK("TRIAL_STUDENTREPORT_PARENT_BULK"),TRIAL_STUDENT_REPORT_NEW_TEMP_BULK("TRIAL_STUDENTREPORT_NEW_TEMP_BULK"),Admin_EMAIL_BULK_UPLOAD_RESULT("Admin_EMAIL_BULK_UPLOAD"),
		        NEW_TRIAL_TO_FULL_STUDENT_PARENT("NEW_TRIAL_TO_FULLSTUDENT_PARENT"),NEW_TRIAL_TO_FULL_STUDENT_FOR_STUDENT("NEW_TRIAL_TO_FULLSTUDENT_FOR_STUDENT"),NEW_USER_12_Plus_For_Student("NEWUSER_12_Plus_For_Student"),NEW_USER_12_Plus_For_Parent("NEWUSER_12_Plus_For_Parent"),
		        NEW_USER_11_12_For_Parent("NEWUSER_11_12_For_Parent"),NEW_USER_11_12_For_Student("NEWUSER_11_12_For_Student"),UPDATE_STUDENT_11_12_12Plus_for_Parent("UPDATESTUDENT_11_12_12Plus_for_Parent"),
		        UPDATE_STUDENT_9_10_for_Parent("UPDATESTUDENT_9_10_for_Parent"),NEW_TRIAL_TO_FULL_STUDENT_11plus_PARENT("NEW_TRIAL_TO_FULLSTUDENT_11plus_PARENT"),NEW_TRIAL_TO_FULL_STUDENT_11plus_FOR_STUDENT("NEW_TRIAL_TO_FULLSTUDENT_11plus_FOR_STUDENT"),
		        UPDATE_STUDENT_11_12_12Plus_for_Student("UPDATESTUDENT_11_12_12Plus_for_Student"),UPDATE_STUDENT_9_10_for_Student("UPDATESTUDENT_9_10_for_Student"),NEW_USER_9_10_For_Student("NEWUSER_9_10_For_Student"),NEW_USER_9_10_For_Parent("NEWUSER_9_10_For_Parent"),
		        Lead_Parent_Message("LeadParentMessage"),SIAT_TEST_REPORT_FOR_PARENT("SIATTESTREPORTFORPARENT"),SIAT_TEST_REPORT_FOR_STUDENT("SIATTESTREPORTFORSTUDENT"),Admin_LeadParent("AdminLeadParent"),
		        Admin_LeadParent_AppointmentBook("AdminLeadParentAppointmentBook"),SIATTest_Five_Day_Reminder_Parent("SIATTestFiveDayReminderParent"),SIATTest_Five_Day_Reminder_Student("SIATTestFiveDayReminderStudent"),
		        SIATTest_Ten_Day_Reminder_Parent("SIATTestTenDayReminderParent"),SIATTest_Ten_Day_Reminder_Student("SIATTestTenDayReminderStudent"),Lead_Parent_Message_HDFC("LeadParentMessageHDFC"),
		        Admin_LeadParent_HDFC("AdminLeadParentHDFC"),Admin_LeadParent_AppointmentBook_HDFC("AdminLeadParentAppointmentBookHDFC"),Parent_Booking_Slots("ParentBookingSlots"),Student_Booking_Slots("StudentBookingSlots"),
		        Admin_LeadStudent("AdminLeadStudent"),Admin_LeadChild("AdminLeadChild"),Admin_LeadParent_Campaign("AdminLeadParentCampaign"),Admin_LeadParent_Campaign1("AdminLeadParentCampaign1"),TYE_Prog_Test("TYEProgTest"),Admin_TYEProgTest("AdminTYEProgTest"),DH_LodestarStudent("DH_LODESTARSTUDENT"), 
		        StreamSelector("STREAMSELECTOR"), EngineeringBranchSelector("ENGINEERINGBRANCHSELECTOR"), CareerDegreeDiscovery("CAREERDEGREEDISCOVERY"), CareerFitment("CAREERFITMENT"), AdminCareerFitment("ADMINCAREERFITMENT"), LearningStyleTest("LEARNINGSTYLETEST") ;

		private String	property;

		private MessageTemplateNameAndType(String property)
		{
			this.property = property;
		}

		public String getProperty()
		{
			return property;
		}
	}

	public enum MessageNotificationType
	{
		EMAIL, SMS;
	}

	public enum MessageNotificationStatus
	{
		INPROGRESS, FAILED, SUCCESS;
	}
}
