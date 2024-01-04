CREATE TABLE IF NOT EXISTS `SessionFeedBackAnswers` (
`id` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `questionNo` int(11) NOT NULL,
  `answer` varchar(5000) NOT NULL,
  `Forsession` int(11) NOT NULL,
  `Createdon` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `SessionFeedBackQuestionParent` (
`id` int(11) NOT NULL,
  `questionNo` int(11) NOT NULL,
  `question` varchar(5000) NOT NULL,
  `questionOrder` int(11) DEFAULT NULL,
  `ForSession` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

INSERT INTO `SessionFeedBackQuestionParent` (`id`, `questionNo`, `question`, `questionOrder`, `ForSession`) VALUES
(1, 1, 'Were the results of the assessments (Interest & Aptitude) shared and discussed?', 1, 1),
(2, 2, 'How do you rate your interaction with the Guidance Specialist?', 2, 1),
(3, 3, 'Was your child able to identify occupations that seemed interesting to him/her from the ‘World of Jobs’?', 3, 1),
(4, 4, 'How satisfied were you with the session 1 activity? ', 4, 1),
(5, 1, 'Was you / your child being able to shortlist occupations and arrive at the top two careers? ', 1, 2),
(6, 2, 'Is the edu-path clearly planned and discussed thoroughly - stream, electives, special courses? ', 2, 2),
(7, 3, 'What more information about the finalized careers and the edu-path would you want to discuss in the final session?', 3, 2);


ALTER TABLE `SessionFeedBackAnswers`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `SessionFeedBackQuestionParent`
 ADD PRIMARY KEY (`id`);
ALTER TABLE `SessionFeedBackAnswers`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;
ALTER TABLE `SessionFeedBackQuestionParent`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;

INSERT INTO `ApplicationMenu` (`refName`, `displayName`, `actionPath`, `menuLevel`, `parentId`, `menuOrder`, `roleWeight`, `enablePermission`, `allowCreate`, `allowRead`, `allowUpdate`, `allowDelete`, `iconPath`) VALUES
('SessionOneFeedBack', 'com.lodestar.edupath.navigation.tab.sessiononefeedback', 'SessionOneFeedBack', 1, 11, 6, 2, 1, 1, 1, 1, 1, 'feedback'),
('SessionTwoFeedBack', 'com.lodestar.edupath.navigation.tab.sessiononefeedback', 'SessionTwoFeedBack', 1, 16, 4, 2, 1, 1, 1, 1, 1, 'feedback');


INSERT INTO `MessageTemplate` (`id`, `name`, `roleTypeId`, `notificationType`, `messageType`, `messageSubject`, `templateString`, `templateParams`, `isActive`) VALUES (NULL, 'NEW_TRIAL_TO_FULLSTUDENT_FOR_STUDENT', '2', 'EMAIL', 'NEW_TRIAL_TO_FULL_STUDENT_FOR_STUDENT', 'Welcome to Lodestar', 'Dear ${studentName},
<p>Thank you for enrolling for the Lodestar Career Guidance Program! Your Smart decision to enroll for this program will enable you to discover your True Career Path through a scientific and data driven process.<b>Your account is upgraded to Full version.</b></p>
<p><b>Please go through session details</b></p>
<table >
  <tr>
    <td style="border: 1px solid black;">Session Details</td>
    <td style="border: 1px solid black;">Session Date & Timings</td>
    <td style="border: 1px solid black;">Guidance Specialist</td>
	<td style="border: 1px solid black;">Phone Number</td>
	<td style="border: 1px solid black;">Venue & Address</td>
  </tr>
  <tr>
    <td style="border: 1px solid black;">Session 1</td>
    <td style="border: 1px solid black;">${session1Date}&nbsp;${session1Time}</td>
    <td style="border: 1px solid black;" rowspan="3" valign="top">${facilitatorName}</td>
	 <td style="border: 1px solid black;" rowspan="3" valign="top">${faciliatorPhone}</td>
	  <td style="border: 1px solid black;" rowspan="3" valign="top">${venue}</td>
  </tr>
  <tr>
    <td style="border: 1px solid black;">Session 2</td>
    <td style="border: 1px solid black;">${session2Date}&nbsp;${session2Time}</td>
  </tr>
  <tr>
    <td style="border: 1px solid black;">Session 3</td>
    <td style="border: 1px solid black;">${session3Date}&nbsp;${session3Time}</td>
  </tr>
</table>
<p>Before your first session on ${session1Date} you will have to log in to the Lodestar System and complete the following key pre-session activities.</p>
<p>a.&nbsp;&nbsp;Fill the &ldquo;Tell Us More&rdquo; form (TUM) </p>
<p>b.&nbsp;&nbsp;b.	Please take the aptitude test only, as you have already taken the interest test. The test is a very scientific assessment and the results of this is an important input that will be used to help determine the best career suited for you. Please ensure that you take this test in all seriousness and follow the instructions accurately. In the aptitude test, you should try and attempt all questions to the best of your ability but in case any answers are doubtful, we encourage to NOT guess.</p>
<p>Please note that it is mandatory for you to complete this before your first session on ${session1Date}.</p>
<p>Your login details are:</p>
<p>Login ID:&nbsp;${loginId}</p>
<p>Login Password:&nbsp;${userPassword}</p>
<p><a href="${webURL}" >Click here to login</a></p>
<p>Thank you and look forward to seeing you at the first session. For any other queries, you can also contact us at guidance@lodestar.guru or speak with our Customer Relations at 080 26714555</p>
<p>Warm Regards,</p>
<p>Team Lodestar</p>', 'UserName', '1')


INSERT INTO `MessageTemplate` (`id`, `name`, `roleTypeId`, `notificationType`, `messageType`, `messageSubject`, `templateString`, `templateParams`, `isActive`) VALUES (NULL, 'NEW_TRIAL_TO_FULLSTUDENT_PARENT', '2', 'EMAIL', 'NEW_TRIAL_TO_FULL_STUDENT_PARENT', 'Welcome to Lodestar', 'Dear Parent,<p>Thank you for enrolling for the Lodestar Career Guidance Program! Your Smart decision to enroll for this program will enable ${studentName} to discover his/her True Career Path through a scientific and data driven process. <b>Your account is upgraded to Full version.</b></p><p><b>Please go through session details</b></p><table >  <tr>    <td style="border: 1px solid black;">Session Details</td>    <td style="border: 1px solid black;">Session Date & Timings</td>    <td style="border: 1px solid black;">Guidance Specialist</td>	<td style="border: 1px solid black;">Phone Number</td>	<td style="border: 1px solid black;">Venue & Address</td>  </tr>  <tr>    <td style="border: 1px solid black;">Session 1</td>    <td style="border: 1px solid black;">${session1Date}&nbsp;${session1Time}</td>    <td style="border: 1px solid black;" rowspan="3" valign="top">${facilitatorName}</td>	 <td style="border: 1px solid black;" rowspan="3" valign="top">${faciliatorPhone}</td>	  <td style="border: 1px solid black;" rowspan="3" valign="top">${venue}</td>  </tr>  <tr>    <td style="border: 1px solid black;">Session 2</td>    <td style="border: 1px solid black;">${session2Date}&nbsp;${session2Time}</td>  </tr>  <tr>    <td style="border: 1px solid black;">Session 3</td>    <td style="border: 1px solid black;">${session3Date}&nbsp;${session3Time}</td>  </tr></table><p>Before your child&rsquo;s first session on ${session1Date}, ${studentName} will have to log in to the Lodestar System and complete the following key pre-session activities.</p><p>a.&nbsp;&nbsp;Fill the &ldquo;Tell Us More&rdquo; form (TUM) </p><p>b.&nbsp;&nbsp;Please take the aptitude test only, as your child have already taken the interest test. The test is a very scientific assessment and the results of this is an important input that will be used to help determine the best career suited for your child. Please ensure that ${studentName} takes this test in all seriousness and follow the instructions accurately. In the aptitude test, your child should try and attempt all questions to the best of his/her ability but in case any answers are doubtful, we encourage to NOT guess</p><p>Please note that it is mandatory for your child to complete this before his/her first session on ${session1Date}.</p><p>Your child&rsquo;s login details are:</p><p>Login ID:&nbsp;${loginId}</p><p>Login Password:&nbsp;${userPassword}</p><p><b>Total Program Fee:&nbsp;${AgreedAmount}</b></p><p><b>Balance Due:&nbsp;${DueAmount}</b></p><p><a href="${webURL}" >Click here to login</a></p><p>Thank you and look forward to seeing you at the first session. For any other queries, you can also contact us at guidance@lodestar.guru or speak with our Customer Relations at 080 26714555</p><p>Warm Regards,</p><p>Team Lodestar</p>', 'UserName', '1');




