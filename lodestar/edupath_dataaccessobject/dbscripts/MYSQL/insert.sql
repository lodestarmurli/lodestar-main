INSERT INTO CronScheduler(taskName,taskGroupName,taskClass,taskCronExp,componentId,trigerOnStartUp) VALUES ('EmailSenderService','EmailSender','com.lodestar.edupath.service.EmailNotificationEngineImpl','0 0/5 * 1/1 * ? *','1','1');
INSERT INTO CronScheduler(taskName,taskGroupName,taskClass,taskCronExp,componentId,trigerOnStartUp) VALUES ('SessionReminderService','SessionReminderService','com.lodestar.edupath.service.SessionReminderService','0 1 12 1/1 * ? *','1','1');

INSERT INTO RoleType (name, weight) VALUES ('Admin', 1);
INSERT INTO RoleType (name, weight) VALUES ('User', 2);
INSERT INTO RoleType (name, weight) VALUES ('Facilitator', 3);
INSERT INTO RoleType (name, weight) VALUES ('SubAdmin', 4);

INSERT INTO UserRole (name, roleTypeId) VALUES ('Admin', 1);
INSERT INTO UserRole (name, roleTypeId) VALUES ('User', 2);
INSERT INTO UserRole (name, roleTypeId) VALUES ('Facilitator', 3);
INSERT INTO UserRole (name, roleTypeId) VALUES ('SubAdmin', 4);



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

INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('app.url', 'http://app.lodestar.guru', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('session1.remainder.in.days', '1', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('session2.remainder.in.days', '3', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('session3.remainder.in.days', '3', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue)  VALUE  ('lodestar.address', 'Lodestar Education Services Pvt. Ltd. 3rd Floor, "Oceana Chambers", No.757/11, K.R. Road, 13th Cross, Jayanagar, 7th Block-West, Bangalore : 560082, Landmark: Above Kotak Mahindra Bank on KR Road-13th Cross Junction.', 'N', '');
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('session.gap1.in.days', '7', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('session.gap2.in.days', '4', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('tutorial.page.size', '6', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('college.page.size', '6', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('tutorial.centre.max.select', '6', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('college.compare.max.select', '3', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isProtected, protectedValue) VALUES('feedback.autosave.timer.in.sec', '30', 'N', null);
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('email.attachment.folder.path', '/opt/softwares/datauploadengine/EmailPro', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('admin.email', '', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('external.url.auth.key.trial.student', 'jtlodestarpro2@16', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('app.recaptcha.site.key', '6Lc3SgcUAAAAAKAR4fNN4JMLKhndy-YOxkBcFZjB', 'N');
INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('NEWUSER', (SELECT id from RoleType where name = 'Admin'), 'EMAIL',
'NEW_USER', 'Welcome to LodeStar', 'Hello ${UserName}<br> Password: ${userPassword} <br><br>Welcome to LodeStar click on the link to login <a href=\'${webURL}\'>Login</a>', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('NEWUSER', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'NEW_USER', 'Welcome to LodeStar', 'Hello ${UserName}<br> Password: ${userPassword} <br><br>Welcome to LodeStar click on the link to login <a href=\'${webURL}\'>Login</a>', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('NEWUSER', (SELECT id from RoleType where name = 'Facilitator'), 'EMAIL',
'NEW_USER', 'Welcome to LodeStar', 'Hello ${UserName}<br> Password: ${userPassword} <br><br> Welcome to LodeStar click on the link to login <a href=\'${webURL}\'>Login</a>', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('NEWUSER', (SELECT id from RoleType where name = 'SubAdmin'), 'EMAIL',
'NEW_USER', 'Welcome to LodeStar', 'Hello ${UserName}<br> Password: ${userPassword} <br><br> Welcome to LodeStar click on the link to login <a href=\'${webURL}\'>Login</a>', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('Forgot Password', 0, 'EMAIL',
'FORGOT_PASSWORD', 'Reset Password', 'Hello ${UserName}<br> Request for Reset Password<br>Click <a href=\'${webURL}\'>here</a> to reset your password<br>If you have  not requested please ignore', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('NEWSTUDENT', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'NEW_STUDENT', 'Welcome to LodeStar','Dear Parent <p>Thank you for enrolling for the <b>Lodestar Career Guidance Program!</b> Your Smart decision to enroll for this program will enable ${UserName} to discover ${userGender} True Career Path through a scientific and data driven process. </p><br><br><span>Your three guidance sessions are scheduled on</span> <p>Session 1:&nbsp;&nbsp;<b>${session1Date}</b><p>Session 2:&nbsp;&nbsp;<b>${session2Date}</b><p>Session 3:&nbsp;&nbsp;<b>${session3Date}</b><p>These sessions will be conducted at ${venue} </p><br><p>Your Guidance Specialist for these three sessions will be ${facilitatorName}. You can contact him/her on  ${faciliatorPhone}</p><p>Before your first session on &nbsp;<b>${session1Date}</b> your child ${UserName} will have to log in to the Lodestar System and complete the following key pre-session activities<p>&nbsp;&nbsp; <b>.</b>&nbsp;&nbsp;Fill the <b>"Tell Us More"</b> form (TUM)</p><p>&nbsp;&nbsp; <b>.</b>&nbsp;&nbsp;Take the <b>Career Guidance Test</b> (CGT). The test is a very scientific assessment and the results of this  is an important input that will be used to help determine the best career suited for your child. Please ensure that the student takes this test in all seriousness and follows the instructions accurately. Answers should not be guessed. All questions in the first section are mandatory. In the second section, the student should try an attempt all questions to the best of his/her ability but in case any answers are doubtful, we encourage to NOT guess.</p><p>Please note that it is mandatory for ${UserName} to complete this before your first session on <b>${session1Date}</b>. </p><br/><p>Your login details are :</p><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><p>Click <a href="${webURL}">here</a> to login </p><br/><p>Thank you and look forward to seeing you at the first session. For any other queries you can also contact us at <a href="mailto:Guidance@lodestar.guru">(Guidance@lodestar.guru)</a> or  call Kalpana at 080 26714555</p><p>Warm Regards,</p><p>Team Lodestar</p>', 'UserName,webURL', true);


INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('UPDATESTUDENT', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'UPDATE_STUDENT', 'Welcome to LodeStar', 'Hello ${UserName}<br><br>Welcome to LodeStar click on the link to login <a href=\'${webURL}\'>Login</a><br>${headerMessage}<br>Session 1: ${session1Date}<br>Session 2: ${session2Date}<br>Session 3: ${session3Date}<br>Facilitator Details<br>Name: ${facilitatorName}<br>Email: ${faciliatorEmail}<br>Phone: ${faciliatorPhone}', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('SESSION1_REMINDER', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'SESSION1_REMINDER', 'LodeStar Session Reminder', '<span style="font-size:12px">Hello ${UserName}<br><br>This is a gentle reminder for the first session of the Lodestar Career Guidance Program which is scheduled on <label style="font-size:13px"><b>${sessionDate}</b></label> at <label style="font-size:13px"><b>${sessionTime}</b></label>.<br> Kindly complete filling up the TUM and taking up the Career Guidance Test before you come for this session.<br><br>Lodestar Team</span>', 'UserName,sessionDate,sessionTime', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('SESSION2_REMINDER', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'SESSION2_REMINDER', 'LodeStar Session Reminder', '<span style="font-size:12px">Hello ${UserName}<br><br>Your next session of the Lodestar Career Guidance Program is on <label style="font-size:13px"><b>${sessionDate}</b></label> at <label style="font-size:13px"><b>${sessionTime}</b></label>.<br> Kindly complete all the activities assigned to you by your Guidance Specialist in the previous session before you come for the next session.<br><br>Lodestar Team</span>', 'UserName,sessionDate,sessionTime', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('SESSION3_REMINDER', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'SESSION3_REMINDER', 'LodeStar Session Reminder', '<span style="font-size:12px">Hello ${UserName}<br><br>Your next session of the Lodestar Career Guidance Program is on <label style="font-size:13px"><b>${sessionDate}</b></label> at <label style="font-size:13px"><b>${sessionTime}</b></label>.<br> Kindly complete all the activities assigned to you by your Guidance Specialist in the previous session before you come for the next session.<br><br>Lodestar Team</span>', 'UserName,sessionDate,sessionTime', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('SESSIONRESCHEDULED', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'SESSION_RESCHEDULED', 'Session re-scheduled', 'Hello ${UserName}<br>Your session ${sessionNo} has been rescheduled to <b>${sessionDate}</b> at <b>${sessionTime}</b><br><br>Lodestar Team' , 'UserName,sessionNo,sessionDate,sessionTime', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('UPDATEFACILITATOR', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'UPDATE_FACILITATOR', 'Update Guidance Specialist', 'Hello ${UserName}<br>We would like to inform you, that your  Guidance specialist for the next session will be ${facilitatorName}<br><br>Lodestar Team' , 'UserName,facilitatorName', true);

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

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('TRIAL_NEWSTUDENT', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'TRIAL_NEW_STUDENT', 'Welcome to LodeStar','Dear ${parentName}, <p>Welcome to <b>Lodestar Career Guidance Program!</b> Thank you for registering for a free Occupational Interest Assessment for your child. This assessment will analyze the Occupational Interest Area your child is most suited to. The test will take just 20~25 minutes of time to complete</p><br/><p>The login details for the test are:</p><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><p>Click <a href="${webURL}">here</a> to login </p><p><b>Note:</b> Your child must complete the test within <b>7 days</b> of registration.</b></p><br/><p>To know more about Lodestar, or if you have any queries, you can contact us at <a href="mailto:guidance@lodestar.guru">(guidance@lodestar.guru)</a> or call us on +91 8971520007.</p><p>Regards,</p><p>Team Lodestar</p>', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('TRIAL_STUDENTREPORT', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'TRIAL_STUDENT_REPORT', 'Download your child''s personalised Occupational Interest Assessment','Dear ${parentName}, <p>Congratulations! Your child has completed the Lodestar Occupational Interest Assessment.</p><p>${UserName} personalised <b>Interest Assessment</b> report is attached in this mail.</p><p>Remember, your child''s interests are just one aspect of their personality. You must also consider their strengths, passion, and abilities to choose best education and career pathway for them! Did you know that there are 250+ career options that your child can choose from - to match their interests, strengths, and passion?</p><p>At Lodestar Career Guidance, we help identify the <u>right career path</u> for your child before making decisions about <u>subject stream</u> after class 10 <br>-  "The Lodestar Way" https://www.youtube.com/watch?v=9zNeeH8XXas</p><p>Discover what careers suit your child''s interest type today and create a successful tomorrow for them! </p><br/><p><p>To know more about Lodestar, or if you have any queries, you can contact us at <a href="mailto:guidance@lodestar.guru">(guidance@lodestar.guru)</a> or call us on +91 8971520007.</p><p>Regards,</p><p>Team Lodestar</p>', 'UserName', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('TRIAL_STUDENTTEST', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'TRIAL_STUDENT_TEST_TAKEN', 'Welcome to the Lodestar Career Guidance Program!','Dear ${parentName}, <p>Welcome to <b>Lodestar Career Guidance Program!</b></p> <p> Congratulations! Your child ${UserName} has successfully completed the Interest Assessment test of the comprehensive career guidance program.  This was taken offline on paper, administered by the school.</p> <p>Test Report will be either made available in a subsequent e-mail or through the school. You may log into Lodestar Career Guidance web platform to explore further.</p><br/><p>The login details for the test are:</p><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><p>Click <a href="${webURL}">here</a> to login </p><br/><p>To know how Lodestar can help you, or if you have any queries, you can contact us at <a href="mailto:Guidance@lodestar.guru">(Guidance@lodestar.guru)</a> or call us on +91 8971520007.</p><p>Regards,</p><p>Team Lodestar</p>', 'UserName,webURL', true);

INSERT INTO MessageTemplate
(name, roleTypeId, notificationType, messageType, messageSubject, templateString, templateParams, isActive)
values
('TRIAL_TO_FULLSTUDENT', (SELECT id from RoleType where name = 'User'), 'EMAIL',
'TRIAL_TO_FULL_STUDENT', 'Welcome to LodeStar','Dear ${UserName}, <p>Welcome to <b>Lodestar Career Guidance Program!</b> This test will help you discover your True Career Path. Your account is upgraded to Full version.</p><br/><p>Your login details are :</p><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><p>Click <a href="${webURL}">here</a> to login </p><br/><p>For any other queries you can also contact us at <a href="mailto:Guidance@lodestar.guru">(Guidance@lodestar.guru)</a> or  call Kalpana at 080 26714555</p><p>Warm Regards,</p><p>Team Lodestar</p>', 'UserName,webURL', true);


INSERT INTO City (name) VALUES ('Shivmoga');
INSERT INTO City (name) VALUES ('Mysore');
INSERT INTO City (name) VALUES ('Mangalore');
INSERT INTO City (name) VALUES ('Bangalore');

INSERT INTO EducationLevel (name, orderNo) VALUES ('PU', 1);
INSERT INTO EducationLevel (name, orderNo) VALUES ('Diploma', 2);
INSERT INTO EducationLevel (name, orderNo) VALUES ('Degree', 3);
INSERT INTO EducationLevel (name, orderNo) VALUES ('Masters', 4);
INSERT INTO EducationLevel (name, orderNo) VALUES('MPhil', 5);
INSERT INTO EducationLevel (name, orderNo) VALUES ('PHD', 6);

INSERT INTO Languages (name) VALUES ('Kannada');
INSERT INTO Languages (name) VALUES ('Hindi');
INSERT INTO Languages (name) VALUES ('Tamil');
INSERT INTO Languages (name) VALUES ('Malayalam');

INSERT INTO TUMQuestions (question, sectionName, slNo, isActive)
VALUES
('Father''s profession', 'Personal & Academic Info', 1, 'Y'),
('Mother''s profession', 'Personal & Academic Info', 2, 'Y'),
('Your current school board', 'Personal & Academic Info', 3, 'Y'),
('Which school board are you considering in class 11?', 'Personal & Academic Info', 4, 'Y'),
('Which Stream would you like to choose in Class11?', 'Personal & Academic Info', 5, 'Y'),
('What subject combination are you considering in Class 11?', 'Personal & Academic Info', 6, 'Y'),
('Academic Record: Enter total % OR Grade obtained in the last appeared final exam', 'Personal & Academic Info', 7, 'Y'),
('What are your strengths as a person?', 'Hobbies & Interest', 8, 'Y'),
('What kind of activities are you good at and like doing? What do you do in your free time – what are you hobbies?', 'Hobbies & Interest', 9, 'Y'),
('What subjects or area of education interests you most?', 'Hobbies & Interest', 10, 'Y'),
('What do you want to become in life ? What kind of work do you want to do when you grow up?', 'Career Interest', 11, 'Y'),
('Name three things that you would like to know in order to make a decision about your career / next education', 'Career Interest', 12, 'Y'),
('Where would you like to be geographically located once you start working?', 'Career Interest', 13, 'Y');

INSERT INTO DoYouKnow (message) 
VALUES
('"If people are not laughing at your goals, your goals are too small." <div class="author_name"> - Azim Premji </div>'),

('"If you just work on stuff that you like and you are passionate about, you don’t have to worry about how things will play out." <div class="author_name"> - Mark Zuckerberg</div>'),

('"Climbing on the top demands focus, whether it''s top of Mount Everest or top of your career." <div class="author_name">- APJ Kalam </div>'),

('"Your work is to discover your work and then with all your heart to give yourself to it." <div class="author_name">- Buddha </div>'),

('"Everybody is a genius, but if you judge a fish by its ability to climb a tree, it will live its whole life believing it’s stupid." <div class="author_name">- Albert Einstein</div>'), 

('"Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. And the only way to do great work is to love what you do." <div class="author_name"> - Steve Jobs</div>');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
Values(1, null, null, null,
'{"listseperator":"&&", "valuesseperator":":","valuelistseperator":",","valuecolumns":["subjects"],"subjects":{"valuedatatype":"string","multicondition":true,"multioperator":"OR","operator":"like","valueappend":"%","valuepreappend":"%"}}', 'Subjects');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
Values(2, null, null, null,
'{"listseperator":"&&", "valuesseperator":":","valuelistseperator":",","valuecolumns":["eduLevelId"],"eduLevelId":{"valuedatatype":"integer","operator":"IN", "multicondition":false}}', 'Education Level');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 3,id, 'tag.areaId', '${param}', null, 'people interaction' FROM Area WHERE name = 'Interactions');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 4,id, 'tag.areaId', '${param}', null, 'Team Work' FROM Area WHERE name = 'Team Work');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
Values(5, null, null, null,
'{"listseperator":"&&", "valuesseperator":":","valuelistseperator":",","valuecolumns":["subcatId", "areaId"],"subcatId":{"valuedatatype":"integer","operator":"IN", "multicondition":false},"areaId":{"valuedatatype":"integer","operator":"IN", "multicondition":false}}', 'Strengths');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
Values(6, null, null, null,
'{"listseperator":"&&", "valuesseperator":":","valuelistseperator":",","valuecolumns":["subcatId", "areaId"],"subcatId":{"valuedatatype":"integer","operator":"IN", "multicondition":false},"areaId":{"valuedatatype":"integer","operator":"IN", "multicondition":false}}', 'Interests');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 7, id , 'tag.categoryId', '${param}', null, 'Salary' FROM Category WHERE name = 'Salary');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 8, id , 'tag.categoryId', '${param}', null, 'Self Employment' FROM Category WHERE name = 'Self Employment');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 9, id , 'tag.categoryId', '${param}', null, 'Travel' FROM Category WHERE name = 'Travel');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 10, id , 'tag.categoryId', '${param}', null, 'Job Security' FROM Category WHERE name = 'Job Security');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 11, id , 'tag.categoryId', '${param}', null, 'Work Environment' FROM Category WHERE name = 'Work Environment');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 12, id , 'tag.categoryId', '${param}', null, 'Work Hours' FROM Category WHERE name = 'Work Hours');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 13.1,id, 'tag.areaId', '${param}', null, 'Impact & Influence' FROM Area WHERE name = 'Social');

INSERT INTO ManualSearchQuestionMapping (questionSlNo, valueId, lookupColumn, tagValue, paramJSON, displayString)
(SELECT 13.2,id, 'tag.areaId', '${param}', null, 'Impact & Influence' FROM Area WHERE name = 'Prestige');

INSERT INTO CGTQuestioneries 
VALUES (1,'','Interest Inventory Test','1','R',''),(2,'','Interest Inventory Test','2','I',''),(3,'','Interest Inventory Test','3','A',''),

(4,'','Interest Inventory Test','4','S',''),(5,'','Interest Inventory Test','5','E',''),(6,'','Interest Inventory Test','6','C',''),

(7,'','Interest Inventory Test','7','R',''),(8,'','Interest Inventory Test','8','I',''),(9,'','Interest Inventory Test','9','A',''),

(10,'','Interest Inventory Test','10','S',''),(11,'','Interest Inventory Test','11','E',''),(12,'','Interest Inventory Test','12','C',''),

(13,'','Interest Inventory Test','13','R',''),(14,'','Interest Inventory Test','14','I',''),(15,'','Interest Inventory Test','15','A',''),

(16,'','Interest Inventory Test','16','S',''),(17,'','Interest Inventory Test','17','E',''),(18,'','Interest Inventory Test','18','C',''),

(19,'','Interest Inventory Test','19','R',''),(20,'','Interest Inventory Test','20','I',''),(21,'','Interest Inventory Test','21','A',''),

(22,'','Interest Inventory Test','22','S',''),(23,'','Interest Inventory Test','23','E',''),(24,'','Interest Inventory Test','24','C',''),

(25,'','Interest Inventory Test','25','R',''),(26,'','Interest Inventory Test','26','I',''),(27,'','Interest Inventory Test','27','A',''),

(28,'','Interest Inventory Test','28','S',''),(29,'','Interest Inventory Test','29','E',''),(30,'','Interest Inventory Test','30','C',''),

(31,'','Interest Inventory Test','31','R',''),(32,'','Interest Inventory Test','32','I',''),(33,'','Interest Inventory Test','33','A',''),

(34,'','Interest Inventory Test','34','S',''),(35,'','Interest Inventory Test','35','E',''),(36,'','Interest Inventory Test','36','C',''),

(37,'','Interest Inventory Test','37','R',''),(38,'','Interest Inventory Test','38','I',''),(39,'','Interest Inventory Test','39','A',''),

(40,'','Interest Inventory Test','40','S',''),(41,'','Interest Inventory Test','41','E',''),(42,'','Interest Inventory Test','42','C',''),

(43,'','Interest Inventory Test','43','R',''),(44,'','Interest Inventory Test','44','I',''),(45,'','Interest Inventory Test','45','A',''),

(46,'','Interest Inventory Test','46','S',''),(47,'','Interest Inventory Test','47','E',''),(48,'','Interest Inventory Test','48','C',''),

(49,'','Interest Inventory Test','49','R',''),(50,'','Interest Inventory Test','50','I',''),(51,'','Interest Inventory Test','51','A',''),

(52,'','Interest Inventory Test','52','S',''),(53,'','Interest Inventory Test','53','E',''),(54,'','Interest Inventory Test','54','C',''),

(55,'','Interest Inventory Test','55','R',''),(56,'','Interest Inventory Test','56','I',''),(57,'','Interest Inventory Test','57','A',''),

(58,'','Interest Inventory Test','58','S',''),(59,'','Interest Inventory Test','59','E',''),(60,'','Interest Inventory Test','60','C','');

INSERT INTO CGTQuestioneries (question, section, slNo, factor, correctAnswer)
VALUES
('', 'Aptitude Test', 61, 'NA', '(52-10)2-3X12'),
('', 'Aptitude Test', 62, 'NA', '1'),
('', 'Aptitude Test', 63, 'NA', '8'),
('', 'Aptitude Test', 64, 'NA', '3.6'),
('', 'Aptitude Test', 65, 'NA', '1/12'),
('', 'Aptitude Test', 66, 'NA', '1.5'),
('', 'Aptitude Test', 67, 'NA', '3:2'),
('', 'Aptitude Test', 68, 'NA', 'None of the above'),
('', 'Aptitude Test', 69, 'NA', '176'),
('', 'Aptitude Test', 70, 'NA', '34'),
('', 'Aptitude Test', 71, 'NA', '15'),
('', 'Aptitude Test', 72,'NA', '30%'),

('', 'Aptitude Test', 73, 'VA', 'tragedy'),
('', 'Aptitude Test', 74, 'VA', 'Saddle'),
('', 'Aptitude Test', 75, 'VA', 'Wrap the wire with electrical tape'),
('', 'Aptitude Test', 76, 'VA', 'B'),
('', 'Aptitude Test', 77, 'VA', 'B'),
('', 'Aptitude Test', 78, 'VA', 'C'),
('', 'Aptitude Test', 79, 'VA', 'C'),
('', 'Aptitude Test', 80, 'VA', 'B'),
('', 'Aptitude Test', 81, 'VA', 'SRPQT'),
('', 'Aptitude Test', 82, 'VA', 'Speech'),
('', 'Aptitude Test', 83, 'VA', 'C'),
('', 'Aptitude Test', 84, 'VA', 'B'),

('', 'Aptitude Test', 85, 'MA', 'A pump'),
('', 'Aptitude Test', 86, 'MA', ' with forging a hammer to manipulate hot metal'), 
('', 'Aptitude Test', 87, 'MA', 'A'),  
('', 'Aptitude Test', 88, 'MA', 'All of the above'), 
('', 'Aptitude Test', 89, 'MA', 'B'), 
('', 'Aptitude Test', 90, 'MA', 'B'),
('', 'Aptitude Test', 91, 'MA', 'Short drive'),
('', 'Aptitude Test', 92, 'MA', 'convex mirror'),
('', 'Aptitude Test', 93, 'MA', 'to change the direction of a pulling force'),
('', 'Aptitude Test', 94, 'MA', 'It will be propelled backwards in the opposite direction'),
('', 'Aptitude Test', 95, 'MA', '25 pounds'),
('', 'Aptitude Test', 96, 'MA', 'A spring'),

('', 'Aptitude Test',  97, 'RA', 'DCBA'),
('', 'Aptitude Test',  98, 'RA', 'Punishment'),
('', 'Aptitude Test',  99, 'RA', '56'),
('', 'Aptitude Test', 100, 'RA', '12 years'),
('', 'Aptitude Test', 101, 'RA', '8'),
('', 'Aptitude Test', 102, 'RA', 'IWX'),
('', 'Aptitude Test', 103, 'RA', 'South-East'),
('', 'Aptitude Test', 104, 'RA', '863903'),
('', 'Aptitude Test', 105, 'RA', 'West'),
('', 'Aptitude Test', 106, 'RA', 'Sole'),
('', 'Aptitude Test', 107, 'RA', 'Both I and II follow'),
('', 'Aptitude Test', 108, 'RA', 'When Emily opens the door in tears, Robert guesses that she''s had a death in her family.'),

('', 'Aptitude Test', 109, 'SA', 'C'),
('', 'Aptitude Test', 100, 'SA', 'A'),
('', 'Aptitude Test', 111, 'SA', 'B'),
('', 'Aptitude Test', 112, 'SA', 'C'),
('', 'Aptitude Test', 113, 'SA', 'A'), 
('', 'Aptitude Test', 114, 'SA', 'B'),
('', 'Aptitude Test', 115, 'SA', 'A'),
('', 'Aptitude Test', 116, 'SA', 'B'), 
('', 'Aptitude Test', 117, 'SA', 'C'),
('', 'Aptitude Test', 118, 'SA', 'B'),
('', 'Aptitude Test', 119, 'SA', 'B'),
('', 'Aptitude Test', 120, 'SA', 'D');

INSERT INTO CollegeParameters (parameter, description, filterDisplayName, paramName, isRangeParam, displayOrder) 
VALUES ('Affiliation to Board', 'The right education board is important as it lays the foundation for your higher education. Affiliation with the right board ensures that there is no discontinuity in education.','Affiliation Board', 'affiliationIds', 0, 1),
('Number of years in existence', 'The numbers of years a college has been in existence can be an important factor in short-listing the college. A PU college/school that has been established for at least 6-7 years will be good to take admission in. Otherwise, you may be unsure of the quality of education. A college/school that has been established fairly recently may not have experienced faculty and well set up infrastructure. A recently started college/school may not have developed a reputation among students and parents.','Years in existence', 'yoe', 1, 2),
('Admission Cut-Off', 'Admission cut-off is important because a higher cut-off can mean that the college/ school have higher standards of education. Based on the cut-off, you can prepare for your board exams to aim for the right college/school.','Admission Cut-Off', 'admCutOff', 1, 3),
('Fees Structure details', 'A PU/+2 course from a good college/school maybe expensive when you compare the fee structure with other colleges. This information will help you compare the fees structure with other colleges/school and manage your budget accordingly.','Fee', 'fee', 1, 4),
('Student Pass Percentage', 'Statistics about academic track record of a college will help you understand the quality of education imparted by the institution. In the absence of this, you may incorrectly gauge the academic quality of the college and get influenced by other facilities provided by the college.','Student pass %', 'studentPass', 1, 5),
('Entrance Exam Coaching Facility', 'Some colleges provide additional coaching facilities for various entrance exams such as CET and JEE. This will help you prepare for the exams along with the syllabus. In the absence of coaching facility within the school/ college, you may have to spend additional time to look for a good coaching centre and spend more time travelling to attend classes.','Entrance Exam Coaching', 'coachingFacility', 0, 6),
('Sports and other extra curricular activities', 'This information will help you understand the kinds of sports and extra-curricular activities the college encourages. This will help you choose a college that has sports quota for admission. If you have not considered this, you may choose a college/school that does not provide opportunities for extracurricular activities or foster your talent in sports. Extracurricular activities and sports are important for well-rounded development of a student.','Extra curricular', 'infrastructureIds', 0, 7);

INSERT INTO Board VALUES (1,'CBSE'),(2,'Karnataka PU'),(3,'ISC'),(4,'IGCSE'),(5,'IB');

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
VALUES ('<=20000', '0-20000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 1), 
('21000-30000', '21000-30000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 2), 
('31000-40000', '31000-40000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 3), 
('41000-50000', '41000-50000', (SELECT id FROM CollegeParameters cp WHERE cp.parameter = 'Fees Structure details'), 4), 
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

INSERT INTO Reservation (name, isDefault) VALUES ('General', 1), ('OBC', 0), ('SC/ST', 0), ('Quota1', 0);

INSERT INTO School (name, code) VALUES ('Other', '-1');

--Start sasedeve Edited by vyankatesh on 04-04-2017
INSERT INTO `ApplicationMenu` (`id`, `refName`, `displayName`, `actionPath`, `menuLevel`, `parentId`, `menuOrder`, `roleWeight`, `enablePermission`, `allowCreate`, `allowRead`, `allowUpdate`, `allowDelete`, `iconPath`) VALUES 
(48, 'ManagePayment', 'com.lodestar.edupath.navigation.tab.managePayment', 'ManagePaymentAction', 1, NULL, 7, 1, 1, 1, 1, 1, 1, ''),
(49, 'ManagePayment', 'com.lodestar.edupath.navigation.tab.managePayment', 'ManagePaymentAction', 1, NULL, 3, 4, 1, 1, 1, 1, 1, '');
--End sasedeve Edited by vyankatesh on 04-04-2017
--Start sasedeve Edited by Mrutyunjaya on 04-04-2017
INSERT INTO `MessageTemplate` (`name`, `roleTypeId`, `notificationType`, `messageType`, `messageSubject`, `templateString`, `templateParams`, `isActive`) VALUES
('TRIAL_NEWSTUDENT_PARENT', 2, 'EMAIL', 'TRIAL_NEW_STUDEN_PARENT', 'Registration for  Student Interest Assessment Test (SIAT) for your child.', 'Dear Parent, <br><p>Welcome to the <b>Lodestar Career Guidance Program! </b> <br><br>Your child has been registered for a FREE <b>Student Interest Assessment Test.</b> This assessment test will analyze the Occupational Interest Area that suits your child best.  The test will take just 20~25 minutes of time to complete.</p>\r\n<p>Have your child start the Student Interest Assessment Test (SIAT) now to discover a True Career Path!</p>\r\n<p>SIAT Login details are:</p>\r\n<p>Login ID : &nbsp;${loginId}</p>\r\n<p>Login Password : &nbsp;${userPassword}</p>\r\n<p>Click <a href="${webURL}">here</a> to login </p>\r\n<p><b>Note:</b> Please ensure your child completes the SIAT within <b> 7 days </b>of registration.</p>\r\n<p>To know more about Lodestar, or if you have any queries, you can write us at <a href="mailto:guidance@lodestar.guru">guidance@lodestar.guru</a> or call us at +91 8026714555 or +91 8971520007.</p>\r\n<br><p>Regards,</p>\r\n<p>Team Lodestar</p>', 'UserName,webURL', 1),
('TRIAL_STUDENTREPORT_PARENT', 2, 'EMAIL', 'TRIAL_STUDENT_REPORT_PARENT', 'Personalised “Student Interest Assessment Test Report” of your child.', 'Dear Parent, \r\n<br><br><p>Congratulations! Your child has completed Lodestar&rsquo;s unique Student Interest Assessment Test (SIAT).  A personalised <b> Student Interest Assessment Test Report </b> is attached in this mail. </p>\r\n<p>This report will help you understand your child&rsquo;s overall Career Interests and a few related career choices.  This report is an indicative assessment and not an exhaustive list of all the possible career choices.</p>\r\n<p>Student Interest Assessment Test is the first step in identifying the True Career Potential for any child.  Interest Assessment is just one aspect of a child&rsquo;s overall personality.  One must also consider the child&rsquo;s Strengths, Passion and Abilities to choose suitable Careers and relevant Education Paths.</p>\r\n<p>At Lodestar Career Guidance, we have researched and scientifically mapped more than 250+ Career choices with different combinations of Student&rsquo;s Interests , Strengths, Passion and Abilities.  Lodestar has a unique 3-Step Career decision making process - 3D Approach - Discover, Determine and Decide.</p>\r\n<p>Having completed the Step-1 of student assessment, we recommend taking up the comprehensive 3-Step program (3D Approach).</p>\r\n<p>To know more about Lodestar, or if you have any queries, you can write us at <a href="mailto:guidance@lodestar.guru">guidance@lodestar.guru</a> or call us at +91 8026714555 or +91 8971520007.</p>\r\n<p>Know more about Lodestar 3 D Approach &#45; &ldquo;The Lodestar Way&rdquo; <a href="https://www.youtube.com/watch?v=6BHMjQruFBc&feature=youtu.be">https://www.youtube.com/watch?v=6BHMjQruFBc&feature=youtu.be</a></p>\r\n<br><p>Regards,</p>\r\n<p>Team Lodestar</p>', 'UserName', 1),
('TRIAL_NEWSTUDENT_NEW_TEMP', 2, 'EMAIL', 'TRIAL_NEW_STUDENT_NEW_TEMP', 'Registration for Student Interest Assessment Test (SIAT).', 'Dear ${UserName}, <br><p>Welcome to the <b> Lodestar Career Guidance Program! </b></p><p>You have been registered for a FREE <b>Student Interest Assessment Test.</b> This assessment test will analyze the Occupational Interest Area that suits you best. The test will take just 20~25 minutes of time to complete.</p><p>Start the Student Interest Assessment Test (SIAT) now to discover a True Career Path!</p><p>SIAT Login details are:</p><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><p>Click <a href="${webURL}">here</a> to login </p><p><b>Note:</b> Please ensure that you complete the SIAT within <b>7 days</b> of registration.</p><p>To know more about Lodestar, or if you have any queries, you may contact us at <a href="mailto:guidance@lodestar.guru">guidance@lodestar.guru</a> or call us at +91 8026714555 or +91 8971520007.</p><br><p>Regards,</p><p>Team Lodestar</p>', 'UserName,webURL', 1),
('TRIAL_STUDENTREPORT_NEW_TEMP', 2, 'EMAIL', 'TRIAL_STUDENT_REPORT_NEW_TEMP', 'Your personalised “Student Interest Assessment Test Report”.', 'Dear ${UserName}, <br><p>Congratulations! You have completed the Lodestar&rsquo;s unique Student Interest Assessment Test (SIAT). The personalised <b>Student Interest Assessment Test Report </b> is attached in this mail.</p><p>This report will help you understand your overall Career Interests and a few related career choices.  This report is an indicative assessment and not an exhaustive list of all the possible Career choices</p><p>Students Interest Assessment Test is the first step in identifying your True Career Potential. Interest Assessment is just one aspect of your overall personality.  One must also consider Strengths, Passion, and Abilities to choose suitable Careers and relevant Education Paths.</p><p>At Lodestar Career Guidance, we have researched and scientifically mapped more than 250+ Career choices with different combinations of Student&rsquo;s Interests, Strengths, Passion and Abilities.  Lodestar has a unique 3-Step Career decision making process - 3D Approach - Discover, Determine and Decide. </p><p>Having completed Step-1 of student assessment, we recommend taking up the comprehensive 3-Step program (3D Approach).</p><p>To know more about Lodestar, or if you have any queries, you can write us at <a href="mailto:guidance@lodestar.guru">guidance@lodestar.guru</a> or call us at +91 8026714555 or +91 8971520007.</p><p>Know more about Lodestar 3 D Approach &#45; &ldquo;The Lodestar Way&rdquo; <a href="https://www.youtube.com/watch?v=6BHMjQruFBc&feature=youtu.be">https://www.youtube.com/watch?v=6BHMjQruFBc&feature=youtu.be</a></p><br><p>Regards,</p><p>Team Lodestar</p>', 'UserName', 1);
--END sasedeve Edited by Mrutyunjaya on 04-04-2017

--START sasedeve Edited by Mrutyunjaya on 18-04-2017
INSERT INTO `VenueDetails` (`cityId`, `venue`, `address`) VALUES
(326, 'Indiranagar (HEAD HELD HIGH)', '#643, 11th main, Opp Indiranagar park, Indiranagar'),
(326, 'Indiranagar (CMH)', 'Domain, 1st Floor,Shyam Complex, Opp Mother Hood Hospital, CMH Road. Near Metro Station'),
(326, 'Kammanahalli', 'Simplilearn, 3rd floor, Above DOORS Prayer hall, Opp Kammanahalli Church, Kammanahalli Main Road'),
(326, 'Vijaynagar', 'Systems Domain Pvt. Ltd. 54/54, 2nd Floor, Above Hotel Aramane, P.R Complex, 17th Cross, Next to Canara Bank, M.C. Road, Vijaynagar, Bangalore- 560040'),
(326, 'Malleshwaram', 'Systems Domain Pvt. Ltd. # 150; Malleswaram Arcade, S-3,2nd Floor B/W 7th & 8th Cross, Margosa Road, Near Malleshwaram club, Bangalore- 560003'),
(326, 'Sahakar Nagar', 'NMIMS, 2735, BDA House, EBlock, Sahakarnagar, Behind Swathi Gardenia restaurant, Bangalore'),
(326, 'RT Nagar', 'Aptech Training Center, 80Ft Rd, Opp New Shanthi Sagar, RT Nagar');

INSERT INTO `Examcitymapping` (`examId`, `cityId`) VALUES
(22, 326),
(47, 326),
(48, 326),
(59, 326),
(61, 326),
(62, 326);

INSERT INTO `CollegeParametersValues` (`displayValue`, `actualValue`, `parameterId`, `displayOrder`) VALUES ('Board of Intermediate Education', '6', '1', '6');

--END sasedeve Edited by Mrutyunjaya on 18-04-2017


