ALTER TABLE EducationLevel ADD orderNo INTEGER DEFAULT NULL;
UPDATE EducationLevel SET orderNo = 1 WHERE name = 'PU';
UPDATE EducationLevel SET orderNo = 2 WHERE name = 'Diploma';
UPDATE EducationLevel SET orderNo = 3 WHERE name = 'Degree';
UPDATE EducationLevel SET orderNo = 4 WHERE name = 'PG';
UPDATE EducationLevel SET orderNo = 5 WHERE name = 'MPhil';
UPDATE EducationLevel SET orderNo = 6 WHERE name = 'PHD';

ALTER TABLE SessionScheduleDetails ADD COLUMN `venue` TEXT NULL;
UPDATE SessionScheduleDetails set venue = 'ATSCHOOL';

Update MessageTemplate
SET
templateString = 'Dear Parent <p>Thank you for enrolling for the <b>Lodestar Career Guidance Program!</b> Your Smart decision to enroll for this program will enable ${UserName} to discover ${userGender} True Career Path through a scientific and data driven process. </p><br><br><span>Your three guidance sessions are scheduled on</span> <p>Session 1:&nbsp;&nbsp;<b>${session1Date}</b><p>Session 2:&nbsp;&nbsp;<b>${session2Date}</b><p>Session 3:&nbsp;&nbsp;<b>${session3Date}</b><p>These sessions will be conducted at ${venue} </p><br><p>Your Guidance Specialist for these three sessions will be ${facilitatorName}. You can contact him/her on  ${faciliatorPhone}</p><p>Before your first session on &nbsp;<b>${session1Date}</b> your child ${UserName} will have to log in to the Lodestar System and complete the following key pre-session activities<p>&nbsp;&nbsp; <b>.</b>&nbsp;&nbsp;Fill the <b>"Tell Us More"</b> form (TUM)</p><p>&nbsp;&nbsp; <b>.</b>&nbsp;&nbsp;Take the <b>Career Guidance Test</b> (CGT)</p><p>Please note that it is mandatory for ${UserName} to complete this before your first session on <b>${session1Date}</b>. </p><br/><p>Your login details are :</p><p>Login ID : &nbsp;${loginId}</p><p>Login Password : &nbsp;${userPassword}</p><p>Click <a href="${webURL}">here</a> to login </p><br/><p>Thank you and look forward to seeing you at the first session. For any other queries you can also contact us at <a href="mailto:Guidance@lodestar.guru">(Guidance@lodestar.guru)</a> or  call Kalpana at 080 26714555</p><p>Warm Regards,</p><p>Team Lodestar</p>'
WHERE roleTypeId = (SELECT id from RoleType WHERE name = 'User') AND messageType = 'NEW_STUDENT';

ALTER TABLE ShortList MODIFY orderNo INTEGER NOT NULL DEFAULT 10;

ALTER TABLE EducationLevel ADD orderNo INTEGER DEFAULT NULL; INSERT INTO EducationLevel (name) VALUES('MPhil'); UPDATE EducationLevel SET orderNo = 1 WHERE name = 'PU'; UPDATE EducationLevel SET orderNo = 2 WHERE name = 'Diploma'; UPDATE EducationLevel SET orderNo = 3 WHERE name = 'Degree'; UPDATE EducationLevel SET orderNo = 4 WHERE name = 'PG'; UPDATE EducationLevel SET orderNo = 5 WHERE name = 'MPhil'; UPDATE EducationLevel SET orderNo = 6 WHERE name = 'PHD';

UPDATE GlobalSetting
SET 
propertyValue = 'Lodestar Education Services Pvt. Ltd. 3rd Floor, "Oceana Chambers", No.757/11, K.R. Road, 13th Cross, Jayanagar, 7th Block-West, Bangalore : 560082, Landmark: Above Kotak Mahindra Bank on KR Road-13th Cross Junction.'
WHERE propertyName  = 'lodestar.address'