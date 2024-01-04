CREATE TABLE IF NOT EXISTS `LeadParent` (
`id` int(11) NOT NULL,
  `ParentEmailID` varchar(265) DEFAULT NULL,
  `ParentName` varchar(265) DEFAULT NULL,
  `StudentEmailID` varchar(265) DEFAULT NULL,
  `StudentRegister` tinyint(1) NOT NULL DEFAULT '0',
  `StudentTestTaken` tinyint(1) NOT NULL DEFAULT '0',
  `ParentRegisterTime` datetime DEFAULT NULL,
  `StudentRegisterTime` datetime DEFAULT NULL,
  `StudentTestTakenTime` datetime DEFAULT NULL,
  `LDID` varchar(265) DEFAULT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `ParentContactNo` varchar(265) DEFAULT NULL,
  `StudentContactNo` varchar(265) DEFAULT NULL,
  `Token` text
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


ALTER TABLE `LeadParent`
 ADD PRIMARY KEY (`id`);


ALTER TABLE `LeadParent`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;


ALTER TABLE  `LeadParent` ADD  `ParentAppointmentBook` TINYINT( 1 ) NULL DEFAULT  '0' AFTER  `Token` ,
ADD  `AppointmentDateTime` VARCHAR( 256 ) NULL AFTER  `ParentAppointmentBook` ;







INSERT INTO `MessageTemplate` ( `name`, `roleTypeId`, `notificationType`, `messageType`, `messageSubject`, `templateString`, `templateParams`, `isActive`) VALUES
('LeadParentMessage', 2, 'EMAIL', 'Lead_Parent_Message', 'Your parent has registered you for a free Vocational Personality Assessment', 'Dear Student,<br/><p>Welcome to the Lodestar Career Guidance Program! You have been registered by your <b>parent</b> to take a free Vocational Personality Assessment.</p> <p>Click here&nbsp;${link}&nbsp;to take the test</p><p><b>Please complete the Assessment within 15 days of registration.</b></p><p>As a student you will be making important decisions about your future career and education. In doing this it is very important to find out what options suit you the best. The Lodestar Vocational Personality Assessment will help you to find that out. Finding out what suits you is the first step to success and happiness in professional life.</p> <p>This is a personality assessment and not a subject or general aptitude test. This test will help you find out your true personality and interest and then match it with the right Career and Education choice.  No preparation is required to take the test.  The test will only take 20-25 mins to complete.</p> <p>If you are unable to open the link or have any queries while taking the test please call us at 080-26714555 or mail us at guidance@lodestar.guru</p><br/><p>Regards,</p><p>Team Lodestar</p> ', 'UserName,webURL', 1);


INSERT INTO `MessageTemplate` (`name`, `roleTypeId`, `notificationType`, `messageType`, `messageSubject`, `templateString`, `templateParams`, `isActive`) VALUES
('SIATTESTREPORTFORPARENT', 2, 'EMAIL', 'SIAT_TEST_REPORT_FOR_PARENT', 'Download ${StudentName}'s Occupational Interest Assessment report!', 'Dear Parent,
<br/>
<p>Congratulations! ${StudentName} has completed the Lodestar Occupational Interest Assessment.</p>
<p>${StudentName}&rsquo;s personalized Interest Assessment report is attached in this mail.</p>
<p>Remember, your child&rsquo;s vocational personality test is one of the important parameters in the career decision. You must also consider ${StudentName}&rsquo;s strengths, passion, favorite subjects, work aspiration, abilities etc. to choose best career & education pathway!</p>
<p>Did you know that there are 250+ career options that you can choose from - to match his/her interests, strengths, passion, aspirations, aptitude and many more parameters like this?</p>
<p><b>For a free detailed discussion of the report of your child please book a slot</b></p>
<p>"API integration for scheduling slots" – Two preferable slots.</p>
<p>If you have any queries, you can contact us at guidance@lodestar.guru or call us on 080-26714555.<p>
<br/>
<p>Warm Regards,</p>
<p>Team Lodestar</p>', 'UserName,webURL', 1),
('SIATTESTREPORTFORSTUDENT', 2, 'EMAIL', 'SIAT_TEST_REPORT_FOR_STUDENT', 'Download your Vocational Personality Test report!', 'Dear ${StudentName},
<br/>
<p>Congratulations! You have completed the Lodestar Vocational Personality Assessment.</p>
<p>Your report is attached in this mail. </p>
<p>This report talks about the suitable occupations according to your personality and interest. Apart from this it tells you the organizations which recruits from these occupations.</p>
<p>Remember, your Vocational Personality is one important aspect of your career decision. You must also consider your strengths, passion, favorite subjects, work aspiration, abilities etc. to choose best career & education pathway!</p>
<p>Did you know that there are 250+ career options that you can choose from - to match your interests, strengths, passion, aspirations, aptitude and many more parameters like this?</p>
<p>Discover what careers suit your interest type today and ensure a bright tomorrow!</p>
<p>If you have any queries, you can contact us at guidance@lodestar.guru or call us on 080-26714555.<p>
<br/>
<p>Warm Regards,</p>
<p>Team Lodestar</p>', 'UserName,webURL', 1);

INSERT INTO  `jt_test`.`MessageTemplate` (
`id` ,
`name` ,
`roleTypeId` ,
`notificationType` ,
`messageType` ,
`messageSubject` ,
`templateString` ,
`templateParams` ,
`isActive`
)
VALUES (
NULL ,  'AdminLeadParent',  '2',  'EMAIL',  'Admin_LeadParent',  'SIAT Test Data', 'Dear Admin,<br/><p>Below are the SIAT Test Data</p><br/><p>Child''s Email Address: ${semail}</p><p>Child''s Phone Number: ${sphno}</p><p>Parent Email Address: ${pemail}</p><br/><p>Warm Regards,</p><p>Team Lodestar</p>', 'UserName,webURL',  '1'
);



ALTER TABLE  `SessionScheduleDetails` ADD  `allsessionscompleted` TINYINT( 1 ) NULL DEFAULT  '0' AFTER  `session3FaciCompleted` ;




CREATE TABLE IF NOT EXISTS `leadStudent` (
`id` int(11) NOT NULL,
  `ParentEmailID` varchar(265) DEFAULT NULL,
  `ParentName` varchar(265) DEFAULT NULL,
  `StudentEmailID` varchar(265) DEFAULT NULL,
  `StudentRegister` tinyint(1) NOT NULL DEFAULT '0',
  `StudentTestTaken` tinyint(1) NOT NULL DEFAULT '0',
  `ParentRegisterTime` datetime DEFAULT NULL,
  `StudentRegisterTime` datetime DEFAULT NULL,
  `StudentTestTakenTime` datetime DEFAULT NULL,
  `LDID` varchar(265) DEFAULT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `ParentContactNo` varchar(265) DEFAULT NULL,
  `StudentContactNo` varchar(265) DEFAULT NULL,
  `Token` text,
  `ParentAppointmentBook` tinyint(1) DEFAULT '0',
  `AppointmentDateTime` varchar(256) DEFAULT NULL,
  `isFivedaymailsent` tinyint(1) NOT NULL DEFAULT '0',
  `isTendaymailsent` tinyint(1) NOT NULL DEFAULT '0',
  `Paymentid` text
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;


ALTER TABLE `leadStudent`
 ADD PRIMARY KEY (`id`);


ALTER TABLE `leadStudent`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;


