CREATE TABLE AuditTrail (
   id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
   module varchar(1000) NOT NULL,
   message longtext NOT NULL,
   performedBy varchar(1000) NOT NULL,
   actionTime timestamp NOT NULL
);

CREATE TABLE SubAdmin
(
   id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
   name varchar(255),
   contactNumber varchar(15),
   emailId varchar(255),
   userId  int(10)
);

CREATE TABLE MessageTemplate
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
   	name varchar(255),
   	roleTypeId int,
   	notificationType varchar(255),
   	messageType varchar(255),
   	messageSubject varchar(5000),
   	templateString text,
   	templateParams	varchar(5000),
   	isActive boolean
);

CREATE TABLE EmailSettingMaster
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
   	username varchar(500),
   	password varchar(500),
   	server varchar(500),
   	port int,
    fromAddress varchar(500),
   	encryptiontype varchar(500),
   	isSMTPAuthenticationRequired boolean
);

CREATE TABLE MessageQueue
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
   	queuedTime datetime,
	messageSubject text,
	messageData text,
	toAddress varchar(500),
	status varchar(500),
	retryCount int,
	notifierType varchar(500),
	notifiedTime datetime,
	filePath VARCHAR(500)
);

CREATE TABLE CronScheduler (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  taskName varchar(45) NOT NULL,
  taskGroupName varchar(45) NOT NULL,
  taskClass varchar(500) NOT NULL,
  taskParam text,
  taskCronExp varchar(200) NOT NULL,
  componentId int(10) unsigned NOT NULL,
  trigerOnStartUp tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
);

CREATE TABLE GlobalSetting(
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    propertyName varchar(255) NOT NULL,
    propertyValue varchar(255) DEFAULT NULL,
    isProtected char(1) NOT NULL,
    protectedValue varbinary(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ApplicationMenu(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   	refName  VARCHAR(100) NOT NULL,
   	displayName  VARCHAR(200) NOT NULL,
   	actionPath  VARCHAR(200) NOT NULL,
   	menuLevel  INTEGER UNSIGNED,
   	parentId  INTEGER UNSIGNED,
   	menuOrder  INTEGER UNSIGNED,
   	roleWeight INTEGER unsigned NOT NULL,
   	enablePermission tinyint(1) unsigned NOT NULL DEFAULT 1,
   	allowCreate tinyint(1) unsigned NOT NULL DEFAULT 1,
   	allowRead tinyint(1) unsigned NOT NULL DEFAULT 1,
   	allowUpdate tinyint(1) unsigned NOT NULL DEFAULT 1,
   	allowDelete tinyint(1) unsigned NOT NULL DEFAULT 1,
   	iconPath VARCHAR(500) ,
  	PRIMARY KEY (id)
);

CREATE TABLE  RoleType (
   	id  INTEGER unsigned NOT NULL AUTO_INCREMENT,
   	name  varchar(100) NOT NULL,
   	weight INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE  UserRole (
	id  INTEGER unsigned NOT NULL AUTO_INCREMENT,
	name  varchar(100) NOT NULL,
	roleTypeId  INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (id)) ;

CREATE TABLE RolePermission (
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   	menuId  INTEGER UNSIGNED NOT NULL,
   	roleId  INTEGER UNSIGNED NOT NULL,
   	allowCreate TINYINT(1) NOT NULL DEFAULT 1,
   	allowRead TINYINT(1) NOT NULL DEFAULT 1,
   	allowUpdate TINYINT(1) NOT NULL DEFAULT 1,
   	allowDelete TINYINT(1) NOT NULL DEFAULT 1,
  	PRIMARY KEY (id)
);

CREATE TABLE UserDetail (
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    loginId varchar(100) NOT NULL,
    password varbinary(255) NULL,
    roleId INTEGER unsigned NOT NULL,
    userType varchar(100) NOT NULL,
    isActive char(1) NOT NULL DEFAULT 'Y',
    createdOn datetime DEFAULT NULL,
  	updatedOn datetime DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE FacilitatorDetails(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   	name  VARCHAR(500) NOT NULL,
   	type  char(1) NOT NULL DEFAULT 'F',
   	dob  DATE NULL,
   	panNumber VARCHAR(500) NULL,
   	highestQualificationId  INTEGER NOT NULL,
   	emailId  VARCHAR(500) NOT NULL,
   	altEmailId VARCHAR(500)  NULL,
   	phoneNumber VARCHAR(15) NOT NULL,
   	altPhoneNumber VARCHAR(15) NULL,
   	streetAddr VARCHAR(500) NULL,
   	areaAddr VARCHAR(500) NULL,
   	cityId INTEGER NOT NULL ,
    isReviewer TINYINT(1) NOT NULL DEFAULT 0,
    userId INTEGER NOT NULL,
    isActive TINYINT(1) NOT NULL DEFAULT 1,
    additionalLanguages VARCHAR(500) NULL,
  	PRIMARY KEY (id)
);

CREATE TABLE City(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name  VARCHAR(1000) NOT NULL,
  	PRIMARY KEY (id)
);

CREATE TABLE EducationLevel(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name  VARCHAR(1000) NOT NULL,
    orderNo INTEGER DEFAULT NULL,
  	PRIMARY KEY (id)
);

CREATE TABLE  SessionScheduleDetails (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  studentId int(10) unsigned NOT NULL,
  facilitatorId int(10) unsigned NOT NULL,
  session1Date datetime,
  session2Date datetime,
  session3Date datetime,
  preSessionCompleted tinyint(3) unsigned NOT NULL DEFAULT '0',
  session1FaciCompleted tinyint(3) unsigned NOT NULL DEFAULT '0',
  session2FaciCompleted tinyint(3) unsigned NOT NULL DEFAULT '0',
  session3FaciCompleted tinyint(3) unsigned NOT NULL DEFAULT '0',
  reportGenerated tinyint(3) unsigned NOT NULL DEFAULT '0',
  sentForReview tinyint(3) unsigned NOT NULL DEFAULT '0',
  session1Reminder datetime DEFAULT NULL,
  session2Reminder datetime DEFAULT NULL,
  session3Reminder datetime DEFAULT NULL,
  studentFeedback DATETIME DEFAULT NULL,
  parentFeedback DATETIME DEFAULT NULL,
  venue text,
  PRIMARY KEY (id)
);


CREATE TABLE  StudentDetails (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(500) NOT NULL,
  gender CHAR(1) NOT NULL,
  cityId int(10) unsigned NOT NULL,
  classId int(10) unsigned NOT NULL,
  section varchar(25),
  schoolId int(10) unsigned NOT NULL,
  contactNumber varchar(45) NOT NULL,
  fatherName varchar(500) NOT NULL,
  fatheremailId varchar(500) NOT NULL,
  computerFacility tinyint(3) unsigned DEFAULT '0',
  userId int(10) unsigned NOT NULL,
  studentType varchar(50),
  source VARCHAR(100),
  testTaken VARCHAR(100),
  otherSchool VARCHAR(200),
  PRIMARY KEY (id)
);


CREATE TABLE TUMQuestions (
  id int(11) NOT NULL AUTO_INCREMENT,
  question varchar(500) DEFAULT NULL,
  sectionName varchar(100) DEFAULT NULL,
  slNo int(11) DEFAULT NULL,
  isActive char(1) DEFAULT 'Y',
  PRIMARY KEY (id)
);

CREATE TABLE StudentTUM (
  id int(11) NOT NULL AUTO_INCREMENT,
  studentId int(11) DEFAULT NULL,
  questionSlNo int(11) DEFAULT NULL,
  answer TEXT,
  PRIMARY KEY (id)
); 

CREATE TABLE DoYouKnow (
  id int(11) NOT NULL AUTO_INCREMENT,
  message mediumtext,
  PRIMARY KEY (id)
);

CREATE TABLE Industry (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(500),
  PRIMARY KEY (id)
);

CREATE TABLE Occupation (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(500),
  rollOverContent varchar(500),
  description text,
  image LONGBLOB,
  isActive tinyInt,
  PRIMARY KEY (id)
);

CREATE TABLE Pathway (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(500),
  industryId int,
  PRIMARY KEY (id)
);

CREATE TABLE OccupationIndustryMapping (
   id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   occupationId INTEGER UNSIGNED NOT NULL,
   industryId INTEGER UNSIGNED NOT NULL,
   pathwayId INTEGER UNSIGNED NOT NULL,
   isPrimary TINYINT UNSIGNED NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE OccupationRelatedOccupation (
  id int(11) NOT NULL AUTO_INCREMENT,
  occupationId int,
  relatedOccupationId int,
  PRIMARY KEY (id)
);

CREATE TABLE Alerts (
  id int(11) NOT NULL AUTO_INCREMENT,
  alertType varchar(500),
  name varchar(500),
  alertValue varchar(500),
  isActive tinyInt,
  PRIMARY KEY (id)
);

CREATE TABLE Area (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(500),
  categoryId int,
  subCategoryId int,
  PRIMARY KEY (id)
);

CREATE TABLE Category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(500),
  catType varchar(500),
  PRIMARY KEY (id)
);

CREATE TABLE SubCategory (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(500),
  categoryId int,
  PRIMARY KEY (id)
);

CREATE TABLE Tags (
  	id int(11) NOT NULL AUTO_INCREMENT,
	occupationId int,
	subjects varchar(500),
	eduLevelId int,
	categoryId int,
	subcatId int,
	areaId int,
	tagvalue VARCHAR(45) NOT NULL DEFAULT 'YES',
	isActive tinyint,
  PRIMARY KEY (id)
);

CREATE TABLE OccupationAlertMapping (
  id int(11) NOT NULL AUTO_INCREMENT,
  occupationId int,
  alertId int,
  PRIMARY KEY (id)
);

CREATE TABLE StrengthsAreaTagging (
  id int(11) NOT NULL AUTO_INCREMENT,
  areaId int,
  strengthName varchar(500),
  PRIMARY KEY (id)
);

CREATE TABLE OccupationTagging (
  id int(11) NOT NULL AUTO_INCREMENT,
  occupationId int,
  occupationTag varchar(500),
  PRIMARY KEY (id)
);

CREATE TABLE BulkUploadStatus(
	 id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
	  module VARCHAR(100),
	  fileName VARCHAR(100) NOT NULL,
	  uploadedAt DATETIME NOT NULL,
	  uploadedBy VARCHAR(100) NOT NULL,
	  completedAt DATETIME,
	  status VARCHAR(50) NOT NULL,
	  message VARCHAR (100)
);

CREATE TABLE Subject
(
  id int(11) NOT NULL AUTO_INCREMENT Primary key,
  name varchar(100)
);

CREATE TABLE CGTQuestioneries
(
	id int(11) NOT NULL AUTO_INCREMENT,
	question text not null,
	section varchar(500) not null,
	slNo varchar(10) not null,
	factor varchar(500) not null,
	correctAnswer varchar(500),
	PRIMARY KEY (id)
);

CREATE TABLE StudentCGT
(
	id int(11) NOT NULL AUTO_INCREMENT,
	studentId int,
	questionId int,
	answer varchar(500),
	PRIMARY KEY (id)
);

CREATE TABLE RAISECCodeOccupation
(
	id int(11) NOT NULL AUTO_INCREMENT,
	occupationId int not null,
	raiseCode varchar(500) not null,
	PRIMARY KEY (id)
);


CREATE TABLE AptitudeTestFactor
(
	id int(11) NOT NULL AUTO_INCREMENT,
	occupationId int not null,
	RA_DI int,
	RA_ID char(1),
	RA_AC char(1),
	RA_NA char(1),
	NA_DI int,
	NA_ID char(1),
	NA_AC char(1),
	NA_NA char(1),
	VA_DI int,
	VA_ID char(1),
	VA_AC char(1),
	VA_NA char(1),
	SA_DI int,
	SA_ID char(1),
	SA_AC char(1),
	SA_NA char(1), 
	MA_DI int,
	MA_ID char(1),
	MA_AC char(1),
	MA_NA char(1),
	PRIMARY KEY (id)
);

CREATE TABLE TableReferenceNorms
(
	id int(11) NOT NULL AUTO_INCREMENT,
	aggreementScore int not null,
	maleScore int not null,
	femaleScore int not null,
	PRIMARY KEY (id)
);

CREATE TABLE Class(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name  VARCHAR(1000) NOT NULL,
    gap VARCHAR(500),
  	PRIMARY KEY (id)
);

CREATE TABLE School(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name  VARCHAR(1000) NOT NULL,
    code  VARCHAR(500) NOT NULL,
  	PRIMARY KEY (id)
);

CREATE TABLE StudentCGTResult (
  studentId int not null,
  personalityCode VARCHAR(45),
  score decimal,
  appScore VARCHAR(100),
  ssFactor VARCHAR(500),
  occupationIds VARCHAR(5000),
  aptitudeComplete VARCHAR(100),
  remainigTime BIGINT,
  PRIMARY KEY (studentId)
);

CREATE TABLE WishList (
  id int(11) NOT NULL AUTO_INCREMENT,
  studentId int not null,
  occupationId int,
  occIndustryId int,
  isSystemRecommended tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
);

CREATE TABLE ManualSearchQuestionMapping (
  id int(11) NOT NULL AUTO_INCREMENT,
  questionSlNo DECIMAL(10,2) not null,
  valueId int,
  lookupColumn varchar(100),
  tagValue varchar(100),
  paramJSON text,
  displayString varchar(100),
  PRIMARY KEY (id)
);

CREATE TABLE ForgotPasswordRequest (
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	userId int(10) unsigned NOT NULL,
	code varchar(250) NOT NULL,
	startTime datetime NOT NULL,
	endTime datetime NOT NULL,
	used tinyint(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
);

CREATE TABLE InterestAreaTagging
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	areaId int not null,
	areaTag varchar(500),
	PRIMARY KEY (id)
);

CREATE TABLE Document
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT,
	industryId int ,
	occupationId int ,
	subjectId int ,
	documentPath varchar(500),
	PRIMARY KEY (id)
);

CREATE TABLE VisitedDocument
(
	studentId int ,
	documentId int ,
	pageNumber int ,
	lastVisitedTime DATETIME,
	PRIMARY KEY (studentId, documentId)
);

CREATE TABLE DocumentHighlights
(
	studentId int,
	documentId int ,
	highlights text,
	PRIMARY KEY (studentId, documentId)
);

CREATE TABLE FacilitatorNotes
(
  id int(11) not null auto_increment primary key,
  userId int(11),
  studentId int(11),
  noteText  text
);

CREATE TABLE StudentNotes
(
  id int(11) not null auto_increment primary key,
  studentId int(11),
  noteText  text
);

CREATE TABLE ShortList(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	studentId INTEGER NOT NULL,
	industryId INTEGER NULL,
	occupationId INTEGER NULL,
	occIndustryId INTEGER NULL,
	orderNo INTEGER NOT NULL DEFAULT 10,
	isSystemRecommended TINYINT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
);

CREATE TABLE Languages
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(255)
);

-----------------------------------------------------------------------------------------------------------------------------
---------- Milestone 2 [14-Aug-2015]
-----------------------------------------------------------------------------------------------------------------------------

CREATE TABLE College
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100),
	zone VARCHAR(100),
	address VARCHAR(500),
	locality VARCHAR(500),
	cityId INTEGER UNSIGNED,
	mobno VARCHAR(100),
	offno VARCHAR(100),
	email VARCHAR(500),
	website VARCHAR(500),
	distFromRailway DECIMAL(10,2),
	distFromCentalBustop DECIMAL(10,2),
	yearofEst int,
	collegeType VARCHAR(100),
	recogByKA TINYINT,
	recogByOth VARCHAR(100),
	affiliatedToBoardId INT,
	addFormSubmTo VARCHAR(500),
	addFormSubmAddr VARCHAR(500),
	addFormSubmOnline VARCHAR(500),
	addFormSubmDate VARCHAR(100),
	sexPref VARCHAR(100),
	noSeatsState INT default -1,
	noSeatsICSE INT default -1,
	noSeatsCBSE INT default -1,
	noSeatsIGSE INT default -1,
	noSeatsISC INT default -1,
	coachingFacility TINYINT,
	carrerCounselling TINYINT,
	coachingCentreName VARCHAR(500),
	latitude DECIMAL(10,10),
	longitude DECIMAL(10,10),
	tuitionFee DECIMAL(10,10),
	booksAndSupplies DECIMAL(10,10),
	coachingFee DECIMAL(10,10),
	labFee DECIMAL(10,10),
	whetherMaintainQuota TINYINT,
	saleOfAppFormStart VARCHAR(100),
	admAppFromAdmOffice TINYINT,
	isActive TINYINT
);

CREATE TABLE CollegeStreamDetails
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId INT,
	streamId INT,
	applicationFormFees INT,
	noTeachingStaff INT,
	noLabIncharge INT,
	admBasisOffCutOff TINYINT,
	admEntranceExam  TINYINT,
	admPersonalInterview  TINYINT,
	admFirstComeFirstServe TINYINT,
	lastCutOff INT,
	perceptionRanking VARCHAR(5000),
	noStudents INT,
	noStudentsPassed INT,
	scored75Percent INT,
	numOfStudScoreGtr80Percent INT,
	numOfStudentsScoreGtr90Percent INT
);

CREATE TABLE CollegeStreamCombination
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId int(11),
	streamId int(11),
	combinationId int(11),
	mgmtCutOff int(11),
	mgmtSeats int(11),
	govtSeats int(11),
	totalSeats INT
);

CREATE TABLE CollegeCourseFeeSeats
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId int(11),
	streamId int(11),
	courseId int(11),
	combinationId int(11),
	reservationId int(11),
	fees int(11),
	seats int(11),
	cutoff int(11)
);

CREATE TABLE CollegeInfra
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(500),
	count INT NULL DEFAULT -1,
	capacity VARCHAR(500),
	collegeId int(11),
	type VARCHAR(100)
);

CREATE TABLE CollegeActivities
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId int(11),
	name VARCHAR(500),
	type VARCHAR(100)
);

CREATE TABLE StudentPlacementInInstitutes
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId int(11),
	instituteName VARCHAR(500),
	countOfStudents int(11)
);

CREATE TABLE StudentsTopRankInexams
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId int(11),
	examId int(11),
	rankValue int(11)
);

CREATE TABLE EduPath (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  industryId int(11) DEFAULT NULL,
  pathwayId int(11) DEFAULT NULL,
  occupationId int(11) DEFAULT NULL,
  pucompulsoryStreamId int(11) DEFAULT '-1',
  pupreferredStreamId int(11) DEFAULT '-1',
  isActive tinyint(1) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE EduPathPUElectives
(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  eduPathId int(11) DEFAULT NULL,
  type varchar(100) DEFAULT NULL,
  electiveId int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE EduPathDegrees (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  eduPathId int(11) DEFAULT NULL,
  type varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE EduPathDegreeSpecialization (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  eduPathId int(11) DEFAULT NULL,
  type varchar(100) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE EduPathPG (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  eduPathId int(11) DEFAULT NULL,
  name varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE EduPathPGSpecialization
(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  eduPathId int(11) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE EntranceExams
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	examName varchar(500) NOT NULL,
	examWhen varchar(500) NULL,
	aboutExam text NULL,
	eligibility text NULL,
	subNMarks text NULL,
	monthOfExam varchar(500) NULL,
	insititutesAcceptScore text NULL,
	noOfSeats int(11) NULL,
	candidateAppearing int(11) NULL,
	annualFee varchar(1000) NULL,
	ratio varchar(250) NULL,
	examLevel varchar(250) NULL,
	isActive TINYINT(1) NOT NULL DEFAULT 1 
);

CREATE TABLE EntranceExamsOccupationMapping
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	entranceExamId int(11) NOT NULL,
	occupationId int(11) NOT NULL,
	required varchar(5) NOT NULL DEFAULT 'P'
);

CREATE TABLE IntegratedCourse
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	programName varchar(5000),
	description text,
	institute   text,
	location    text,
	eligibility text,
	entrance varchar(500),
	selectionProcess  text,
	noOfSeats varchar(500),
	feeStructure varchar(500),
	programType varchar(500),
	courseDuration varchar(500),
	isActive TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE IntegratedCourseOccupationMapping
(
	id  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,    
	integratedCourseId  int(11),     
	occupationId int(11)
);

CREATE TABLE ShortListExams
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	studentId int(11) NOT NULL,
	entranceExamId int(11) NOT NULL,
	occupationId int(11) NOT NULL,
	occIndustryId int(11) NOT NULL
);

CREATE TABLE EduPathShortList (
  id int not null AUTO_INCREMENT,
  studentId INTEGER UNSIGNED NOT NULL,
  industryId INTEGER,
  occupationId INTEGER,
  puStreamId INTEGER NOT NULL,
  priority INTEGER NOT NULL,
  degreeStream VARCHAR(500) DEFAULT NULL,
  edupathId INTEGER DEFAULT NULL,
  occIndustryId INTEGER DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE EduPathElectiveShortList (
  id int not null AUTO_INCREMENT,
  combinationId INTEGER NOT NULL,
  orderNo INTEGER NOT NULL DEFAULT 0,
  studentId INTEGER NOT NULL,
  noOfCollege INTEGER DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE CombinationElectivesMapping (
  id int not null AUTO_INCREMENT,
  combinationId INTEGER NOT NULL,
  electiveId INTEGER NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Combination (
  id int not null AUTO_INCREMENT,
  name VARCHAR(500) NOT NULL,
  streamId INTEGER NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Streams (
  id int not null AUTO_INCREMENT,
  name VARCHAR(500) NOT NULL,
  eduLevelId INTEGER NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE ShortListCourse
(
	id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	studentId int(11) NOT NULL,
	integratedCourseId int(11) NOT NULL,
	occupationId int(11) NOT NULL,
	occIndustryId int(11) NOT NULL
);
CREATE TABLE EduPathPUStreams
(
	id  INT UNSIGNED NOT NULL auto_increment Primary KEY,
    eduPathId INT,
    type VARCHAR(100),
    streamId INT
);

CREATE TABLE StudentCollegeParameters
 (
	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
	studentId int,
    address text,
    selectedParameters text
 );
 
---28-APR-2016--------
 
CREATE TABLE Tutorials
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name VARCHAR(500) NOT NULL,
 	headOfficeAddress VARCHAR(5000),
 	contactNumbers VARCHAR(5000),
 	website VARCHAR(5000),
 	nrankInst varchar(500),
 	noFaculty int,
 	qualificationOfFaculty varchar(500),
 	yearOfEst int,
 	centerAllIndia int,
 	noOfCities int,
 	specialFeatures text,
 	isActive tinyInt
 );
 
CREATE TABLE TutorialRank
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	tutorialId int,
 	examId int,
 	year int,
 	classroom int,
 	correspondance int
 );
 
CREATE TABLE TutorialExamMapping
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	tutorialId int,
 	examId int,
 	admissionCriteria varchar(5000),
 	noClassEnroll int,
 	noCorresEnroll int,
 	topRankCutOff varchar(500),
 	clearClass varchar(500),
 	clearCorres varchar(500)
 );
 
CREATE TABLE TutorialProgram
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	tutorialId int,
 	programName varchar(500),
 	examId int,
 	hasClassroom tinyInt,
 	hasCorrespondence tinyInt,
 	totalMocks varchar(500)
 );
 
CREATE TABLE TutorialCityCenters
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	tutorialId int,
 	cityId int,
 	locality varchar(500),
 	address varchar(5000),
 	centreType varchar(500),
 	yearofEst int,
 	timings varchar(500),
 	contactNumbers varchar(500),
 	noOfFaculty int,
 	noOffEnrollments int,
 	latitude DECIMAL(10,10),
 	longitude DECIMAL(10,10)
 );

CREATE TABLE CollegeAchievements
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	collegeId int,
 	achievement varchar(500),
 	type varchar(500)
 );
 
CREATE TABLE Board
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name varchar(500)
 );
 
CREATE TABLE Reservation
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name varchar(500),
 	isDefault tinyint default 0
 );
 
CREATE TABLE CollegeParameters
 (
 	id INT(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	parameter VARCHAR(500) 	NOT NULL, 
 	description VARCHAR(50000),
 	filterDisplayName VARCHAR(500) NOT NULL, 
 	paramName VARCHAR(50) NOT NULL, 
 	displayOrder INT NOT NULL, 
 	isRangeParam TINYINT DEFAULT 0,
 	isMultiSelect TINYINT DEFAULT 0
 );
 
CREATE TABLE CollegeParametersValues
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	displayValue VARCHAR(500) NOT NULL,
 	actualValue VARCHAR(100) NOT NULL,
 	parameterId INT NOT NULL,
 	displayOrder INT NOT NULL
 );
 
CREATE TABLE StudentTutorialCentreShortList
 (
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	studentId INT NOT NULL,
 	tutorialId INT NOT NULL,
 	tutorialCityCentersId INT NOT NULL
 );
 
CREATE TABLE StudentCollegeShortList
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	studentId INT NOT NULL,
 	collegeId INT NOT NULL
 );
 
CREATE TABLE ReportReviewComments
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	studentId INT NOT NULL,
 	facilitatorId INT NOT NULL,
 	review MEDIUMTEXT NOT NULL,
 	lastUpdatedDate DATETIME
 );
 
CREATE TABLE ReportComments
(
  	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
  	studentId int NOT NULL,
  	facilitatorId int NOT NULL,
  	comments MEDIUMTEXT,
  	commentTime DATETIME NOT NULL
);
  
CREATE TABLE ParentFeedbackFormQuestion
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
  	questionNo INT NOT NULL,
  	question VARCHAR(5000) NOT NULL,
  	questionOrder int
);

CREATE TABLE StudentFeedbackFormQuestion
(
  	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
  	questionNo INT NOT NULL,
  	question VARCHAR(5000) NOT NULL,
  	questionOrder int
);
 
CREATE TABLE ParentFeedbackForm
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
  	studentId INT NOT NULL,
  	questionNo INT NOT NULL,
  	answer VARCHAR(5000) NOT NULL
);

CREATE TABLE StudentFeedbackForm
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
  	studentId INT NOT NULL,
  	questionNo INT NOT NULL,
  	answer VARCHAR(5000) NOT NULL
);

CREATE TABLE CollegeFeatures
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId INT NOT NULL,
	feature VARCHAR(5000)
);

CREATE TABLE CollegeBoardMapping
(
  	id int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	collegeId INT NOT NULL,
   	boardId INT NOT NULL
);

CREATE TABLE TrialInterestCodeMapping 
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  raisecCode VARCHAR(100) NULL,
  fileName VARCHAR (200),
  content LONGBLOB NULL
);

CREATE TABLE  OccupationAbilityMapping (
  id int(11) NOT NULL AUTO_INCREMENT,
  RA tinyint(1) NOT NULL,
  VA tinyint(1) NOT NULL,
  SA tinyint(1) NOT NULL,
  NA tinyint(1) NOT NULL,
  MA tinyint(1) NOT NULL,
  occupationId int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
);

--Start sasedeve Edited by vyankatesh on 30-1-2017

CREATE TABLE IF NOT EXISTS `Payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `loginId` varchar(100) NOT NULL,
  `agreedAmount` decimal(10,2) NOT NULL,
  `dueAmount` decimal(10,2) NOT NULL,
  `paidAmount` decimal(10,2) NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
--End sasedeve Edited by vyankatesh on 30-1-2017
--Start sasedeve Edited by vyankatesh on 04-04-2017
CREATE TABLE IF NOT EXISTS `TrialstudentextraDetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `studentEmailId` varchar(255) DEFAULT NULL,
  `studentContactno` varchar(45) DEFAULT NULL,
  `motherEmailId` varchar(255) DEFAULT NULL,
  `motherContactno` varchar(45) DEFAULT NULL,
  `motherName` varchar(255) DEFAULT NULL,
  `fatherEmailId` varchar(255) DEFAULT NULL,
  `fatherName` varchar(255) DEFAULT NULL,
  `fatherContactno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--End sasedeve Edited by vyankatesh on 04-04-2017

--START sasedeve Edited by Mrutyunjaya on 18-04-2017
CREATE TABLE IF NOT EXISTS `Facilitatorcitymap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `facilitatorId` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  `faceToFaceCity` int(11) NOT NULL,
  `oncallCity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `SchoolCity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolId` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `VenueDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityId` int(11) NOT NULL,
  `venue` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `Studentcitylock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentLockId` int(11) NOT NULL,
  `cityLockId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `Examcitymapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examId` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



--END sasedeve Edited by Mrutyunjaya on 18-04-2017
