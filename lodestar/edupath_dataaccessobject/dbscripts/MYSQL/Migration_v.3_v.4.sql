
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
 
CREATE TABLE StudentsTopRankInExams
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	collegeId int,
 	examId int, 
 	rankValue int
);
 
CREATE TABLE Board
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name varchar(500)
);
 
CREATE TABLE Reservation
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name varchar(500)
);
 
 ALTER TABLE CollegeParameters RENAME StudentCollegeParameters;
 
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
 
CREATE TABLE ReportReviewComments
(
 	id int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	studentId INT NOT NULL,
 	facilitatorId INT NOT NULL,
 	review TEXT NOT NULL,
 	lastUpdatedDate DATETIME
);
 
INSERT INTO CollegeParameters (parameter, description, filterDisplayName, paramName, isRangeParam, displayOrder, isMultiSelect) 
VALUES ('Affiliation to Board', 'The right education board is important as it lays the foundation for your higher education. Affiliation with the right board ensures that there is no discontinuity in education.','Affiliation Board', 'affiliationIds', 0, 1, 0),
('Number of years in existence', 'The numbers of years a college has been in existence can be an important factor in short-listing the college. A PU college/school that has been established for at least 6-7 years will be good to take admission in. Otherwise, you may be unsure of the quality of education. A college/school that has been established fairly recently may not have experienced faculty and well set up infrastructure. A recently started college/school may not have developed a reputation among students and parents.','Years in existence', 'yoe', 1, 2, 0),
('Admission Cut-Off', 'Admission cut-off is important because a higher cut-off can mean that the college/ school have higher standards of education. Based on the cut-off, you can prepare for your board exams to aim for the right college/school.','Admission Cut-Off', 'admCutOff', 1, 3, 0),
('Fees Structure details', 'A PU/+2 course from a good college/school maybe expensive when you compare the fee structure with other colleges. This information will help you compare the fees structure with other colleges/school and manage your budget accordingly.','Fee', 'fee', 1, 4, 0),
('Student Pass Percentage', 'Statistics about academic track record of a college will help you understand the quality of education imparted by the institution. In the absence of this, you may incorrectly gauge the academic quality of the college and get influenced by other facilities provided by the college.','Student pass %', 'studentPass', 1, 5, 0),
('Entrance Exam Coaching Facility', 'Some colleges provide additional coaching facilities for various entrance exams such as CET and JEE. This will help you prepare for the exams along with the syllabus. In the absence of coaching facility within the school/ college, you may have to spend additional time to look for a good coaching centre and spend more time travelling to attend classes.','Entrance Exam Coaching', 'coachingFacility', 0, 6, 0),
('Sports and other extra curricular activities', 'This information will help you understand the kinds of sports and extra-curricular activities the college encourages. This will help you choose a college that has sports quota for admission. If you have not considered this, you may choose a college/school that does not provide opportunities for extracurricular activities or foster your talent in sports. Extracurricular activities and sports are important for well-rounded development of a student.','Extra curricular', 'infrastructureIds', 0, 7, 1);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('CBSE', (SELECT id FROM Board WHERE name = 'CBSE'), (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Affiliation to Board'), 1), 
('Karnataka PU', (SELECT id FROM Board WHERE name = 'Karnataka PU'), (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Affiliation to Board'), 2), 
('ICSE', (SELECT id FROM Board WHERE name = 'ICSE'), (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Affiliation to Board'), 3), 
('IGCSE', (SELECT id FROM Board WHERE name = 'IGCSE'), (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Affiliation to Board'), 4), 
('IB', (SELECT id FROM Board WHERE name = 'IB'), (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Affiliation to Board'), 5);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('0-3', '0-3', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Number of years in existence'), 1), 
('4-6', '4-6', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Number of years in existence'), 2), 
('7-10', '7-10', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Number of years in existence'), 3), 
('11-20', '11-20', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Number of years in existence'), 4), 
('over 20', '20-100000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Number of years in existence'), 5);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('<=50%', '0-50', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Admission Cut-Off'), 1), 
('50% to 60%', '50-60', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Admission Cut-Off'), 2), 
('60% to 70%', '60-70', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Admission Cut-Off'), 3), 
('70% to 80%', '70-80', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Admission Cut-Off'), 4), 
('80% to 90%', '80-90', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Admission Cut-Off'), 5),
('above 90%', '90-100', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Admission Cut-Off'), 6);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('less than 20000', '0-20000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 1), 
('between 21000-30000', '21000-30000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 2), 
('between 31000-40000', '31000-40000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 3), 
('between 41000-50000', '41000-50000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 4), 
('more than 50000', '50000-50000000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 5);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('<=50%', '0-50', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Student Pass Percentage'), 1), 
('50% to 60%', '50-60', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Student Pass Percentage'), 2), 
('60% to 70%', '60-70', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Student Pass Percentage'), 3), 
('70% to 80%', '70-80', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Student Pass Percentage'), 4), 
('80% to 90%', '80-90', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Student Pass Percentage'), 5),
('above 90%', '90-100', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Student Pass Percentage'), 6);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('NCC', 'activities:::NCC', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 1), 
('NSS', 'activities:::NSS', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 2), 
('Scouting', 'infrastructures:::Scouting', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 3), 
('Cricket', 'infrastructures:::Cricket', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 4), 
('Football', 'infrastructures:::Football', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 5),
('Hockey', 'infrastructures:::Hockey', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 6),
('Activity/Hobby Club', 'infrastructures:::Activity/Hobby Club', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Sports and other extra curricular activities'), 7);

INSERT INTO CollegeParametersValues (displayValue, actualValue, parameterId, displayOrder) 
VALUES ('YES', 'true', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Entrance Exam Coaching Facility'), 1), 
('NO', 'false', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Entrance Exam Coaching Facility'), 2);

INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('tutorial.page.size', '6', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('college.page.size', '6', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('tutorial.centre.max.select', '6', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('college.compare.max.select', '3', 'N', null);

ALTER TABLE SessionScheduleDetails ADD COLUMN sentForReview tinyint(3) unsigned NOT NULL DEFAULT '0';

ALTER TABLE College CHANGE status zone VARCHAR(100);
ALTER TABLE College CHANGE yearofEst yearOfEst INT;

UPDATE College SET recogByKA = 1 WHERE recogByKA = 'YES';
UPDATE College SET recogByKA = 0 WHERE recogByKA = 'NO';
ALTER TABLE College CHANGE COLUMN recogByKA recogByKA TINYINT(2) NULL DEFAULT 0;
ALTER TABLE College CHANGE affiliatedTo affiliatedToBoardId INT;
ALTER TABLE College CHANGE addFormsubmto addFormSubmTo VARCHAR(500);
ALTER TABLE College CHANGE addFormsubmaddr addFormSubmAddr VARCHAR(500);
ALTER TABLE College CHANGE addFormsubmonline addFormSubmOnline TINYINT;
ALTER TABLE College CHANGE COLUMN addFormSubmDate addFormSubmDate VARCHAR(100);
ALTER TABLE College CHANGE COLUMN noSeatsState noSeatsState INT default -1;
ALTER TABLE College CHANGE COLUMN noSeatsICSE noSeatsICSE INT default -1;
ALTER TABLE College CHANGE COLUMN noSeatsCBSE noSeatsCBSE INT default -1;
ALTER TABLE College CHANGE COLUMN noSeatsIGSE noSeatsIGSE INT default -1;
ALTER TABLE College CHANGE COLUMN noSeatsISC noSeatsISC INT default -1;
ALTER TABLE College CHANGE COLUMN coachingFacility coachingFacility TINYINT;
ALTER TABLE College CHANGE COLUMN carrerCounselling carrerCounselling TINYINT;
ALTER TABLE College CHANGE COLUMN isActive isActive TINYINT;

ALTER TABLE College ADD COLUMN coachingCentreName VARCHAR(500);
ALTER TABLE College ADD COLUMN latitude DECIMAL(10,10);
ALTER TABLE College ADD COLUMN longitude DECIMAL(10,10);
ALTER TABLE College ADD COLUMN tuitionFee DECIMAL(10,10);
ALTER TABLE College ADD COLUMN booksAndSupplies DECIMAL(10,10);
ALTER TABLE College ADD COLUMN coachingFee DECIMAL(10,10);
ALTER TABLE College ADD COLUMN labFee DECIMAL(10,10);
ALTER TABLE College ADD COLUMN locality VARCHAR(500);
ALTER TABLE College CHANGE COLUMN addFormSubmOnline addFormSubmOnline VARCHAR(500);

ALTER TABLE CollegeStreamDetails CHANGE applicationFormfees applicationFormFees INT;
ALTER TABLE CollegeStreamDetails CHANGE noteachingStaff noTeachingStaff INT;
ALTER TABLE CollegeStreamDetails CHANGE nolabincharge noLabIncharge INT;
ALTER TABLE CollegeStreamDetails CHANGE admbasisOffCutOff admBasisOffCutOff TINYINT;
ALTER TABLE CollegeStreamDetails CHANGE COLUMN admEntranceExam admEntranceExam TINYINT;
ALTER TABLE CollegeStreamDetails CHANGE COLUMN admPersonalInterview admPersonalInterview TINYINT;
ALTER TABLE CollegeStreamDetails CHANGE COLUMN admFirstComeFirstServe admFirstComeFirstServe TINYINT;
ALTER TABLE CollegeStreamDetails CHANGE lastCutoff lastCutOff INT;
ALTER TABLE CollegeStreamDetails ADD COLUMN noStudents INT;
ALTER TABLE CollegeStreamDetails ADD COLUMN noStudentsPassed INT; 

ALTER TABLE CollegeStreamCombination CHANGE mgmtCutoff mgmtCutOff INT(11);
ALTER TABLE CollegeStreamCombination CHANGE GovtSeats govtSeats INT(11);

ALTER TABLE CollegeCourseFeeSeats CHANGE COLUMN fees fees INT(11);
ALTER TABLE CollegeCourseFeeSeats CHANGE COLUMN courseId courseId INT(11);
ALTER TABLE CollegeCourseFeeSeats ADD COLUMN combinationId int(11);

ALTER TABLE StudentPlacementInInstitutes CHANGE instituename instituteName VARCHAR(500);

CREATE TABLE ReportComments
(
	id int(10) unsigned NOT NULL AUTO_INCREMENT primary key,
  	studentId int NOT NULL,
  	facilitatorId int NOT NULL,
  	comments VARCHAR(5000),
  	commentTime DATETIME NOT NULL
);
  
CREATE TABLE StudentCollegeParameters_old SELECT * FROM StudentCollegeParameters;
  
DROP PROCEDURE IF EXISTS UPDATE_SelectedParamIds;
DELIMITER //
CREATE PROCEDURE UPDATE_SelectedParamIds()
BEGIN

DECLARE p_done1 INT DEFAULT FALSE;
DECLARE p_done2 INT DEFAULT FALSE;
DECLARE SP_id int;
DECLARE SP_selectedParameters varchar(5000);
DECLARE CUR_Params CURSOR FOR
  SELECT sp.id, sp.selectedParameters sp
  FROM StudentCollegeParameters sp;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET p_done1 = TRUE;

OPEN CUR_Params;

  	read_loop1: LOOP
   	FETCH CUR_Params INTO SP_Id, SP_selectedParameters;
  	IF p_done1 THEN
        LEAVE read_loop1;
    END IF;

    UPDATE StudentCollegeParameters SET selectedParameters = (SELECT group_concat(cp.id) FROM CollegeParameters cp
    WHERE FIND_IN_SET(cp.parameter, replace(SP_selectedParameters,', ', ',')))
    WHERE id = SP_Id;

   SET p_done1 = p_done2;
  END LOOP;
CLOSE CUR_Params;
END //
DELIMITER;
CALL UPDATE_SelectedParamIds();
DROP PROCEDURE IF EXISTS UPDATE_SelectedParamIds;

ALTER TABLE SessionScheduleDetails ADD COLUMN studentFeedback DATETIME DEFAULT NULL, ADD COLUMN parentFeedback DATETIME DEFAULT NULL;

INSERT INTO ApplicationMenu (refName, displayName, actionPath, parentId, menuLevel, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('FeedbackFormAction','com.lodestar.edupath.navigation.tab.Welcome','FeedbackFormAction', (select c.id from ApplicationMenu AS c where c.refName = 'Session3'), 1, 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');

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

INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (1, 1, 'How would you rate your overall experience with the program?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (2, 2, 'Which aspect of the program did you like the most?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (3, 3, 'On a scale of 1 to 10 how will you rate the program on the following aspects? (1 is the lowest score i.e., it was not systematic or fact based at all and 10 is the highest score i.e.,it was completely systematic or fact based)?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (4, 4, 'Did the program help you and your child to arrive at a career decision?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (5, 5, 'Did this program help you and your child to decide their education path?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (6, 6, 'To what extent were your career related questions answered by the program?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (7, 7, 'Which of your questions were not answered even after completion of the program?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (8, 8, 'Did the process of career guidance, increase your awareness regarding careers, industries, education paths, exams and courses?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (9, 9, 'How useful did you find the information related to PU Colleges/schools while making a decision?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (10, 10, 'In this program you had 3face-to-face (in person) interactions with our guidance specialist. In case you had an option of other formats, which format would you have chosen from the below');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (11, 11, 'Any other suggestions to make this program better?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (12, 12, 'This is a premium program and has been offered at a discounted fee. After going through the program – what price do you think is appropriate for such a program?');
INSERT INTO ParentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES (13, 13, 'Thank you for completing the feedback. If you would like to recommend other parents/ students who you think will benefit from our program, kindly provide their details below. You can help us guide more students to find their true career paths');

INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(1, 1, 'How would you rate your overall experience with the program?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(2, 2, 'How will you rate the Guidance Specialist’s interaction with you on the following parameters?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(3, 3, 'Which aspect of the program did you like the most?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(4, 4, 'Did the program help you to arrive at a career decision?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(5, 5, 'Did this program help you to decide your education path?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(6, 6, 'To what extent were your career related questions answered by the program?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(7, 7, 'Which of your questions were not answered even after the program?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(8, 8, 'Did our career guidance program increase your awareness regarding careers, industries, education paths, exams, and courses?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(9, 9, 'Did the information provided in the Industry and Occupation Reports help to create an awareness in you about career and industries?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(10, 10, 'How did you find the information related to subjects and electives at +2 level?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(11, 11, 'How did you find the information related to PU Colleges/+2 school?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(12, 12, 'How interested are YOU in the career path/occupation that has been finalized during this program ?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(13, 13, 'How confident do you feel about your future career direction? Please rate separately for Before and After the program?');
INSERT INTO StudentFeedbackFormQuestion (questionNo, questionOrder, question) VALUES(14, 14, 'Now that you have decided on a career path and have the information about it, how motivated do you feel to work towards achieving it?');

INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('feedback.autosave.timer.in.sec', '30', 'N', null);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('SESSION3COMPLETED', (SELECT id from RoleType where name = 'User'), 'EMAIL', 'SESSION3_COMPLETED', 'Session3 Completed', 'Dear Parent,<br><p>Congratulations on completing the Lodestar career guidance program for your child''s better future!</p><br><p>Before we can generate the summary report customised for your child''s career decision and education pathway, you and your child will have to log in to the Lodestar System and provide feedback about the experience with Lodestar.</p><br><p>We are working on your summary report. Your report will be available to you within 7 working days of completing the feedback.</p><br><p>Your login details are :</p><br><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><br/><p>Click <a href="${webURL}">here</a> to login </p><br/><p>Thank you and look forward to getting your valuable feedback. For any other queries you can also contact us at <a href="mailto:Guidance@lodestar.guru">(Guidance@lodestar.guru)</a> or  call Kalpana at 080 26714555</p><br><br><p>Warm Regards,</p><p>Team Lodestar</p>', 'webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('REPORTREADY', (SELECT id from RoleType where name = 'User'), 'EMAIL', 'REPORT_READY', 'Report Available', 'Dear Parent,<br><p>Thank you for your valuable feedback.</p><br><p>The Lodestar Summary Report for <b>${UserName}</b> is now available for viewing and download. You can login in to the Lodestar System and view the summary of career decision, education pathway and download the report for future reference</p><br><p>Your login details are :</p><br><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><br/><p>Click <a href="${webURL}">here</a> to login </p><br/><p>Thank you and look forward to getting your valuable feedback. For any other queries you can also contact us at <a href="mailto:Guidance@lodestar.guru">(Guidance@lodestar.guru)</a> or  call Kalpana at 080 26714555</p><br><br><p>Warm Regards,</p><p>Team Lodestar</p>', 'UserName,webURL', true );

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('REPORTSENTBACK', (SELECT id from RoleType where name = 'Facilitator'), 'EMAIL',
'REPORT_SENT_BACK', 'Report sent back', 'Dear Guidance Specialist, <br><p>Student report has been reviewed and sent back.</p><br><p>Login ID : &nbsp;${loginId}</p><br><br>Thank You', 'UserName', true);

truncate table ApplicationMenu;

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('ManageStudent','com.lodestar.edupath.navigation.tab.manageStudent','ManageStudentAction', 1, null, 1, (select weight from RoleType where name = 'Admin'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('ManageFacilitator','com.lodestar.edupath.navigation.tab.managefacilitator','ManageFacilitatorAction', 1, null,2, (select weight from RoleType where name = 'Admin'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('ManageSubAdmin','com.lodestar.edupath.navigation.tab.managesubadmin','ManageSubAdminAction', 1, null, 4, (select weight from RoleType where name = 'Admin'), 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('PreSession','com.lodestar.edupath.navigation.tab.Presession','WelcomeStudentAction', 1, null, 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('Welcome','com.lodestar.edupath.navigation.tab.Welcome','WelcomeStudentAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'PreSession'), 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, 'welcome');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('TellUsMoreform','com.lodestar.edupath.navigation.tab.tellusmoreform','', 1, (select c.id from ApplicationMenu AS c where c.refName = 'PreSession'), 2, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, 'TUM');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('PersonalAndAcademicInfo','com.lodestar.edupath.navigation.tab.personalandacademicinfo','PersonalAndAcademicInfoAction', 2, (select c.id from ApplicationMenu AS c where c.refName = 'TellUsMoreform'), 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('HobbiesAndInterest','com.lodestar.edupath.navigation.tab.hobbiesandinterest','HobbiesAndInterestAction', 2, (select c.id from ApplicationMenu AS c where c.refName = 'TellUsMoreform'), 2, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('Careerinterest ','com.lodestar.edupath.navigation.tab.careerinterest','CareerinterestAction', 2, (select c.id from ApplicationMenu AS c where c.refName = 'TellUsMoreform'), 3, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('Questionnaire','com.lodestar.edupath.navigation.tab.questionnaire','QuestionnaireAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'PreSession'), 3, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, 'questionnaire');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('Session1','com.lodestar.edupath.navigation.tab.session1','Session1', 1, null, 2, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('WelcomeFirstSession','com.lodestar.edupath.navigation.tab.Welcome','WelcomeSessionFirstAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1'), 1, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, 'welcome');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('MyWishlist','com.lodestar.edupath.navigation.tab.mywishlist','MyWishlistAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1'), 2, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, 'my-wishlist');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Exploremoreoccupations','com.lodestar.edupath.navigation.tab.exploremoreoccupations','ExploremoreoccupationsAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1'), 3, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, 'explore-more-options');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('MyShortList','com.lodestar.edupath.navigation.tab.myshortlist','MyShortListAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1'), 5, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, 'my-shortlist');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Session2','com.lodestar.edupath.navigation.tab.session2','Session2', 1, null, 3, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, '');


INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('ReviewElectives','com.lodestar.edupath.navigation.tab.reviewelectives','ReviewElectivesAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session2'), 2, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Session3','com.lodestar.edupath.navigation.tab.session3','Session3', 1, null, 4, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Summary','com.lodestar.edupath.navigation.tab.Summary','Summary', 1, null, 5, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, '');


INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('PreSessionFacilitator','','PreSessionFacilitator', 1, null, 1, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('StudentSessionDetails','com.lodestar.edupath.navigation.tab.studentsessiondetails','FacilitatorStudentSummaryAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'PreSessionFacilitator'), 1, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, 'TUM');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Session1Facilitator','com.lodestar.edupath.navigation.tab.Session1Facilitator','SessionFirstFacilitatorAction', 1, null, 2, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Systemrecommendation','com.lodestar.edupath.navigation.tab.Systemrecommendation','SystemrecommendationAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1Facilitator'), 1, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, 'system-reco');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Manualsearch','com.lodestar.edupath.navigation.tab.Manualsearch','ManualsearchAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1Facilitator'), 2, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, 'manual-search');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Occupationglossary','com.lodestar.edupath.navigation.tab.Occupationglossary','OccupationglossaryAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1Facilitator'), 3, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, 'occupation-glossary');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Occupationwishlist','com.lodestar.edupath.navigation.tab.Occupationwishlist','OccupationwishlistAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session1Facilitator'), 4, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, 'occupation-wishlist');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Session2Facilitator','com.lodestar.edupath.navigation.tab.Session2Facilitator','SessionSecondFacilitator', 1, null, 3, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('EduPath','com.lodestar.edupath.navigation.tab.EduPath','EduPath', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session2Facilitator'), 2, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, 'edupath');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Session3Facilitator','com.lodestar.edupath.navigation.tab.Session3Facilitator','SessionThirdFacilitator', 1, null, 4, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

-- INSERT INTO ApplicationMenu
-- (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
-- VALUES ('Finaliseelectives','com.lodestar.edupath.navigation.tab.Finaliseelectives','FinaliseelectivesAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session3Facilitator'), 1, (select weight from RoleType where name = 'Facilitator'),
-- 1, 1, 1, 1, 1, '');


-- INSERT INTO ApplicationMenu
-- (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
-- VALUES ('Exams','com.lodestar.edupath.navigation.tab.Exams','ExamsAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session3Facilitator'), 3, (select weight from RoleType where name = 'Facilitator'),
-- 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('SummaryFacilitator','com.lodestar.edupath.navigation.tab.SummaryFacilitator','SummaryFacilitator', 1, null, 5, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('Interest','com.lodestar.edupath.navigation.tab.interest','InterestAction', 2, (select c.id from ApplicationMenu AS c where c.refName = 'Questionnaire'), 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('Aptitude','com.lodestar.edupath.navigation.tab.aptitude','AptitudeAction', 2, (select c.id from ApplicationMenu AS c where c.refName = 'Questionnaire'), 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('ManageStudent','com.lodestar.edupath.navigation.tab.manageStudent','ManageStudentAction', 1, null, 1, (select weight from RoleType where name = 'SubAdmin'), 1, 1, 1, 1, 1, '');
INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES ('ManageFacilitator','com.lodestar.edupath.navigation.tab.managefacilitator','ManageFacilitatorAction', 1, null,2, (select weight from RoleType where name = 'SubAdmin'), 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES 
('View Log','com.lodestar.edupath.navigation.tab.viewlog','ViewLogAction', 1, null, 5, (select weight from RoleType where name = 'Admin'), 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('College Parametes','com.lodestar.edupath.navigation.tab.college.parameter','CollegeParameter', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session2'), 3, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Welcome','com.lodestar.edupath.navigation.tab.student.sessiontwo','StudentSessionTwoAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session2'), 1, (select weight from RoleType where name = 'User'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('SelectColleges','com.lodestar.edupath.navigation.tab.selectcolleges','CollegeSelectionAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session3Facilitator'), 1, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('SelectTutorials','com.lodestar.edupath.navigation.tab.selecttutorials','SelectTutorialsAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session3Facilitator'), 2, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Finalize','com.lodestar.edupath.navigation.tab.finalize','FinalizeAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'Session3Facilitator'), 3, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('Review','com.lodestar.edupath.navigation.tab.review','ReviewSummaryAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'PreSessionFacilitator'), 2, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) VALUES 
('ViewFeedback','com.lodestar.edupath.navigation.tab.viewfeedback','ViewFeedbackAction', 1, null, 6, (select weight from RoleType where name = 'Admin'), 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu
(refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('ViewFeedback','com.lodestar.edupath.navigation.tab.viewfeedback','ViewFeedbackAction', 1, (select c.id from ApplicationMenu AS c where c.refName = 'PreSessionFacilitator'), 3, (select weight from RoleType where name = 'Facilitator'),
1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, parentId, menuLevel, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath)
VALUES ('FeedbackFormAction','com.lodestar.edupath.navigation.tab.Welcome','FeedbackFormAction', (select c.id from ApplicationMenu AS c where c.refName = 'Session3'), 1, 1, (select weight from RoleType where name = 'User'), 1, 1, 1, 1, 1, '');

INSERT INTO ApplicationMenu (refName, displayName, actionPath, menuLevel, parentId, menuOrder, roleWeight, enablePermission, allowCreate, allowread, allowUpdate, allowDelete, iconPath) 
VALUES ('ManageSchool','com.lodestar.edupath.navigation.tab.manageschool','ManageSchoolSummaryAction', 1, null, 3, (select weight from RoleType where name = 'Admin'), 1, 1, 1, 1, 1, '');

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

CREATE TABLE CollegeFeatures
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	collegeId INT NOT NULL,
	feature VARCHAR(5000)
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

ALTER TABLE College ADD COLUMN whetherMaintainQuota TINYINT, ADD COLUMN saleOfAppFormStart VARCHAR(100), ADD COLUMN admAppFromAdmOffice TINYINT;
ALTER TABLE CollegeStreamDetails ADD COLUMN scored75Percent INT, ADD COLUMN	numOfStudScoreGtr80Percent INT, ADD COLUMN	numOfStudentsScoreGtr90Percent INT;
ALTER TABLE CollegeStreamCombination ADD COLUMN totalSeats INT;
ALTER TABLE CollegeStreamDetails CHANGE COLUMN perceptionRanking perceptionRanking VARCHAR(5000);
ALTER TABLE CollegeInfra CHANGE COLUMN capacity capacity VARCHAR(500);

INSERT INTO Board VALUES (1,'CBSE'),(2,'Karnataka PU'),(3,'ISC'),(4,'IGCSE'),(5,'IB');
ALTER TABLE Reservation ADD COLUMN isDefault tinyint default 0;

ALTER TABLE CollegeCourseFeeSeats ADD INDEX IDX_COLLEGE_ID(collegeId), ADD INDEX IDX_COMBINATION_ID(combinationId), ADD INDEX IDX_STREAM_ID(streamId),
 ADD INDEX IDX_RESERVATION_ID(reservationId);
 
 ALTER TABLE CollegeInfra ADD INDEX IDX_COLLEGE_ID(collegeId);
 ALTER TABLE CollegeAchievements ADD INDEX IDX_COLLEGE_ID(collegeId);
 ALTER TABLE CollegeActivities ADD INDEX IDX_COLLEGE_ID(collegeId);
 
 ALTER TABLE CollegeStreamDetails ADD INDEX IDX_COLLEGE_ID(collegeId),
 ADD INDEX IDX_STREAM_ID(streamId);
 
ALTER TABLE CollegeStreamCombination ADD INDEX IDX_COLLEGE_ID(collegeId),
 ADD INDEX IDX_STREAM_ID(streamId),
 ADD INDEX IDX_COMBINATION_ID(combinationId);

 CREATE TABLE CollegeBoardMapping
(
  	id int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	collegeId INT NOT NULL,
   	boardId INT NOT NULL
);
INSERT INTO Reservation (name, isDefault) VALUES ('General', 1), ('OBC', 0), ('SC/ST', 0), ('Quota1', 0);