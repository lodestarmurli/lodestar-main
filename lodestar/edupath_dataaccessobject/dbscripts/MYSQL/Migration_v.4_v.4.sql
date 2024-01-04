ALTER TABLE UserDetail ADD COLUMN createdOn DATETIME DEFAULT NULL;
ALTER TABLE UserDetail ADD COLUMN updatedOn DATETIME DEFAULT NULL;

ALTER TABLE SessionScheduleDetails CHANGE COLUMN session1Date session1Date DATETIME NULL;
ALTER TABLE SessionScheduleDetails CHANGE COLUMN session2Date session2Date DATETIME NULL;
ALTER TABLE SessionScheduleDetails CHANGE COLUMN session3Date session3Date DATETIME NULL;

ALTER TABLE StudentDetails ADD COLUMN studentType VARCHAR(50);
ALTER TABLE StudentDetails ADD COLUMN source VARCHAR(100);
ALTER TABLE StudentDetails ADD COLUMN section varchar(25);
ALTER TABLE StudentDetails ADD COLUMN testTaken VARCHAR(100);
ALTER TABLE StudentDetails ADD COLUMN otherSchool VARCHAR(200);

UPDATE StudentDetails SET studentType = 'FULL' WHERE studentType IS NULL;
UPDATE StudentDetails SET source = 'FULL' WHERE source IS NULL;

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

CREATE TABLE TrialInterestCodeMapping 
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  raisecCode VARCHAR(100) NULL,
  fileName VARCHAR (200),
  content LONGBLOB NULL
);

ALTER TABLE MessageQueue ADD COLUMN filePath VARCHAR(500);

TRUNCATE table CGTQuestioneries;
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

INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('email.attachment.folder.path', '/opt/softwares/datauploadengine/EmailPro', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('admin.email', '', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('external.url.auth.key.trial.student', 'jtlodestarpro2@16', 'N');
INSERT INTO GlobalSetting (propertyName, propertyValue, isprotected) values ('app.recaptcha.site.key', '6Lc3SgcUAAAAAKAR4fNN4JMLKhndy-YOxkBcFZjB', 'N');
INSERT INTO School (name, code) VALUES ('Other', '-1');

-- Modification for System Recommendation
CREATE TABLE  OccupationAbilityMapping 
(
  id int(11) NOT NULL AUTO_INCREMENT,
  ra tinyint(1) NOT NULL,
  va tinyint(1) NOT NULL,
  sa tinyint(1) NOT NULL,
  na tinyint(1) NOT NULL,
  ma tinyint(1) NOT NULL,
  occupationId int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
);

ALTER TABLE StudentCGTResult ADD COLUMN ssFactor VARCHAR(500) AFTER appScore;
ALTER TABLE StudentCGTResult MODIFY COLUMN `occupationIds` VARCHAR(5000);