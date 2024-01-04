package com.lodestar.edupath.datatransferobject.util;

public class ApplicationConstants
{
	public static final String	EXCEPTION							= "Exception";

	public static final String	SUCCESS								= "success";

	public static final String	FAILURE								= "failure";

	public static final String	MESSAGE								= "MESSAGE";

	public static final String	STATUS								= "STATUS";

	public static final String	HEADER_LIST							= "HeaderList";

	public static final String	SIDEBAR_LIST						= "SidebarList";

	public static final String	SUB_MENU_REF_NAME					= "SubMenuRefName";

	public static final String	APP_MENU_REVIEW_REFNAME				= "Review";

	public static final String	APP_MENU_FINALIZE_REFNAME			= "Finalize";

	public static final String	APP_MENU_COLLEGE_REFNAME			= "SelectColleges";

	public static final Object	APP_MENU_TUTORIAL_REFNAME			= "SelectTutorials";

	public static final String	APP_MENU_TELLUS_MORE_FORM_REFNAME	= "TellUsMoreform";

	public static final int		PASSWORD_LENGTH						= 5;

	public static final int		NUMERIC_LENGTH						= 10;

	public static final int		ADD_HOURS							= 6;

	public static final int		SESSION_HOURS						= 1;

	public static final int		OCCUPATION_GLOSS_COUNT				= 50;

	public static final int		APTITUDE_TEST_TIME					= 60;					// in minute

	public static final int		DAYS								= 7;

	public static final int		TUTORIAL_COUNT						= 5;

	public final static String	OTHER_SCHHOL						= "Other";
	public final static String	OTHER_SCHHOL_CODE					= "-1";
	
	//added by bharath on 15-10-2019
	public static final String	APP_MENU_COMPLETED_REPORT_REFNAME	= "Completed Reports";
	public static final String 	PRESESSION							="PreSession";
	public static final String 	EBFAVOURITESUBJECT					="EBFavouriteSubject";
	public static final String 	CCDSTREAMSELECT						="CCDStreamSelect";

	public enum SessionProperty
	{
		USER_SESSION_DETAILS_PROPERTY("UserSessionObject"),
		HEADER_SESSION_MENU_LIST("HeaderMenus"),
		SIDEBAR_SESSION_MENU_LIST("SideBarMenus"),
		SELECTED_HEADER_ID("SelectedHeaderId"),
		HEADER__SESSION_SUB_TITLE("HeaderSubTitle"),
		SELECTED_SESSION_STUDENT_ID("SelectedStudentId"),
		IS_SESSION_STUDENT("IsStudent"),
		STUDENT_SESSION_OBJECT("StudentSessionObject"),
		ACTIVE_HEADER("ActiveHeaderId"),
		//Start Sasedeve edited by Mrutyunjaya on Date 20-07-2017

		Session_One_FeedBack("IsSessionOneFeedBack"),
		Session_Two_FeedBack("IsSessiontwoFeedBack"),
		Is_IntresttestCompleted("IsIntresttestCompleted"),
		Is_ApptitudetestCompleted("IsApptitudetestCompleted"),
		Is_Twelve_Plus("Is12plus"),
		
		
		Is_Session1CompleteFaci("IsSession1CompleteFaci"),
		Is_Session2CompleteFaci("IsSession2CompleteFaci");
		
		//End Sasedeve edited by Mrutyunjaya on Date 20-07-2017
		private String	property;

		private SessionProperty(String property)
		{
			this.property = property;
		}

		public String getProperty()
		{
			return property;
		}
	}

	public enum GlobalSettings
	{
		GLOBAL_SETTINGS_WEB_URL("app.url"),
		SESSION1_REMAINDER_IN_DAYS("session1.remainder.in.days"),
		SESSION2_REMAINDER_IN_DAYS("session2.remainder.in.days"),
		SESSION3_REMAINDER_IN_DAYS("session3.remainder.in.days"),
		GLOBAL_SETTINGS_LODESTAR_ADDRESS("lodestar.address"),
		SESSION_TIME_GAP_IN_DAYS("session.timegap.in.days"),
		SESSION_TIME_GAP_NORMAL_IN_DAYS("session.gap1.in.days"),
		SESSION_TIME_GAP_SPECIAL_IN_DAYS("session.gap2.in.days"),
		TUTORIAL_PAGE_SIZE("tutorial.page.size"),
		COLLEGE_PAGE_SIZE("college.page.size"),
		TUTORIAL_CENTRE_MAX_SELECT("tutorial.centre.max.select"),
		COLLEGE_COMPARE_MAX_SELECT("college.compare.max.select"),
		COLLEGE_SHORTLIST_MAX_SELECT("college.shortlist.max.select"),
		FEEDBACK_AUTOSAVE_TIMER_IN_SEC("feedback.autosave.timer.in.sec"),
		EMAIL_FOLDER_PATH("email.attachment.folder.path"),
		ADMIN_EMAIL("admin.email"),
		EXTERNAL_URL_AUT_KEY_TRIAL_STUDENT("external.url.auth.key.trial.student"),
		APP_RECAPTCHA_SITE_KEY("app.recaptcha.site.key"),
		Razorpay_key("Razorpaykey"),
		Razorpay_secret_key("Razorpaysecretkey"),
		Offer_ID("OfferID"),
		ZohoCRMAPIURl("ZohoCRMAPIURlSearch"),
		ZohoCRMInsertAPIURl("ZohoCRMInsertAPIURl"),
		ZohoCRMUpdateAPIURl("ZohoCRMUpdateAPIURl"),
		ZohoCRMAPIKey("ZohoCRMAPIKey");

		private String	property;

		private GlobalSettings(String property)
		{
			this.property = property;
		}

		public String getProperty()
		{
			return property;
		}

	}

	public enum SystemRecommendation
	{
		PESONALITY_CODE, PESONALITY_ANSWERED, SCORE, APP_SCORE, OCCUPATION_IDS, STRENGTHS, SUBJECTS, ASPIRATIONS, INTERESTS, CGT, SS_FACTOR, DISCLAIMER
	}

	public enum SSFactor
	{
		H("H"), M("M"), L("L");

		private String	property;

		private SSFactor(String property)
		{
			this.property = property;
		}

		public String getProperty()
		{
			return property;
		}
	}

	public enum ABILITY
	{
		MA("MA"), NA("NA"), RA("RA"), SA("SA"), VA("VA");

		private String	name;

		private ABILITY(String property)
		{
			this.name = property;
		}

		public String getName()
		{
			return name;
		}
	}

	public enum PERSONALITY
	{
		R("R"), A("A"), I("I"), S("S"), E("E"), C("C");

		private String	name;

		private PERSONALITY(String property)
		{
			this.name = property;
		}

		public String getName()
		{
			return name;
		}
	}

	public enum FitMent
	{
		HIG("High", 4, "fitment_high"), A_AVG("Above_Average", 3, "fitment_above_average"), AVG("Average", 2, "fitment_average"), LOW("Low", 1, "fitment_low");

		private String	property;
		private int		score;
		private String	color;

		private FitMent(String property, int index, String color)
		{
			this.property = property;
			this.score = index;
			this.color = color;
		}

		public String getProperty()
		{
			return property;
		}

		public int getScore()
		{
			return score;
		}

		public String getColor()
		{
			return color;
		}
	}
	
	
	public enum STREAM
	{
		 SCIENCEWITHBIO("SCIENCEWITHBIO",1,"SCIENCE","Reasoning","Mechanical","Science with Biology"), 
		 SCIENCEWITHMATH("SCIENCEWITHMATH",2,"SCIENCE","Numerical",	"Reasoning","Science with Maths"), 
		 SCIENCEWITHMATHDESIGN("SCIENCEWITHMATHDESIGN",3,"SCIENCE","Spatial",	"Numerical","Science with design"), 
		 COMMERCEWITHMATH("COMMERCEWITHMATH",4,"COMMERCE","Reasoning",	"Numerical","Commerce with Maths"), 
		 COMMERCEWITHOUTMATH("COMMERCEWITHOUTMATH",5,"COMMERCE","Reasoning",	"Numerical","Commerce without Maths"),
		 ARTSWITHLANGUAGES("ARTSWITHLANGUAGES",6,"ARTS","Verbal",	"Reasoning","Arts with Languages"), 
		 ARTSWITHDESIGN("ARTSWITHDESIGN",7,"ARTS","Spatial",	"Reasoning","Arts with Design"), 
		 ARTSWITHPSYCHOLOGY("ARTSWITHPSYCHOLOGY",8,"ARTS","Reasoning",	"Verbal","Arts with Psychology"), 
		 GENERALARTS("GENERALARTS",9,"ARTS","Reasoning",	"Verbal","General Arts");

		private String	name;
		private int		priority;
		private String	mainStream;
		private String 	abililtyRequired1;
		private String 	abililtyRequired2;
		private String 	commonName;
		
		private STREAM(String property,int priority, String mainStream, String abililtyRequired1, String abililtyRequired2, String commonName)
		{
			this.name = property;
			this.priority=priority;
			this.mainStream=mainStream;
			this.abililtyRequired1=abililtyRequired1;
			this.abililtyRequired2=abililtyRequired2;
			this.commonName=commonName;
		}

		public String getName()
		{
			return name;
		}

		public int getPriority() {
			return priority;
		}

		public String getMainStream() {
			return mainStream;
		}

		public String getAbililtyRequired1() {
			return abililtyRequired1;
		}

		public String getAbililtyRequired2() {
			return abililtyRequired2;
		}

		public String getCommonName() {
			return commonName;
		}
 
	}
	

	public enum PRIORITY
	{
		HIGH("HIGH",3), MEDIUM("MEDIUM",2), LOW("LOW",1);

		private String	name;
		private int		score;
		private PRIORITY(String property, int score)
		{
			this.name = property;
			this.score=score;
		}

		public String getName()
		{
			return name;
		}
		public int getScore()
		{
			return score;
		}
	}
	public enum StreamSelectorRecommendation
	{
		streamFitment, streamOccupation 
	}

	public enum FAVSUBJECTS
	{
		PHYSICS("PHYSICS"), CHEMISTRY("CHEMISTRY"), MATHEMATICS("MATHEMATICS"), BIOLOGY("BIOLOGY");
		
		private String	name;

		private FAVSUBJECTS(String property)
		{
			this.name = property;
		}

		public String getName()
		{
			return name;
		}
	}
	public enum PROGRAMTEST
	{
		STREAMSELECTOR("StreamSelector","STREAMSELECTOR_TRAIL","StreamSelector"),  
		ENGINEERINGBRANCHSELECTOR("EngineeringBranchSelector","ENGINEERINGBRANCHSELECTOR_TRAIL","EngineeringBranchSelector"),
		CAREERDEGREEDISCOVERY("CareerDegreeDiscovery","CAREERDEGREEDISCOVERY_TRAIL","CareerDegreeDiscovery"),
		CAREERFITMENT("CareerFitment","CAREERFITMENT_TRAIL","CareerFitment");
		/*STRENGTHFINDER("StrengthFinder","STRENGTHFINDER_TRAIL","StrengthFinder"),
		FOUNDATIONBUILDINGPLUS("FoundationBuildingPlus","FOUNDATIONBUILDINGPLUS_TRAIL","FoundationBuildingPlus"),
		FOUNDATIONBUILDING("FoundationBuilding","FOUNDATIONBUILDING_TRAIL","FoundationBuilding"),
		COREDECISION("CoreDecision","COREDECISION_TRAIL","CoreDecision"),
		FINALIZINGDECISION("FinalizingDecision","FINALIZINGDECISION_TRAIL","FinalizingDecision")*/		
		
		String packageName;
		String source;
		String emailTemplate;
		
		private PROGRAMTEST(String packageName, String source, String emailTemplate)
		{
			this.packageName=packageName;
			this.source=source;
			this.emailTemplate=emailTemplate;
			
		}

		public String getPackageName() {
			return packageName;
		}

		public String getSource() {
			return source;
		}

		public String getEmailTemplate() {
			return emailTemplate;
		}


	}
	
	public enum CLIENTNAME
	{
		UNIFORMJUNCTION("UniformJunction");
		
		private CLIENTNAME(String name)
		{
			this.name=name;
		}
		private String name;

		public String getName() {
			return name;
		}

			
	}
	
}
