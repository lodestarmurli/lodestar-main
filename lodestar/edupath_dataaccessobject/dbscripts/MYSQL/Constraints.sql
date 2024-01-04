ALTER TABLE StrengthsAreaTagging ADD INDEX Index_StrengthTag_Area USING BTREE(areaId);
ALTER TABLE CollegeCourseFeeSeats ADD INDEX IDX_COLLEGE_ID(collegeId), ADD INDEX IDX_COMBINATION_ID(combinationId), ADD INDEX IDX_STREAM_ID(streamId), ADD INDEX IDX_RESERVATION_ID(reservationId);
ALTER TABLE CollegeInfra ADD INDEX IDX_COLLEGE_ID(collegeId);
ALTER TABLE CollegeAchievements ADD INDEX IDX_COLLEGE_ID(collegeId);
ALTER TABLE CollegeActivities ADD INDEX IDX_COLLEGE_ID(collegeId);
ALTER TABLE CollegeStreamDetails ADD INDEX IDX_COLLEGE_ID(collegeId), ADD INDEX IDX_STREAM_ID(streamId);
ALTER TABLE CollegeStreamCombination ADD INDEX IDX_COLLEGE_ID(collegeId), ADD INDEX IDX_STREAM_ID(streamId), ADD INDEX IDX_COMBINATION_ID(combinationId);

--Start sasedeve Edited by Mrutyunjaya on 04-04-2017
ALTER TABLE  `TrialInterestCodeMapping` ADD  `isClass10` TINYINT( 1 ) NOT NULL DEFAULT  '1' AFTER  `raisecCode` ;

--END sasedeve Edited by Mrutyunjaya on 04-04-2017

--Start sasedeve Edited by Mrutyunjaya on 18-04-2017
ALTER TABLE `Board` ADD `cityId` INT(11) NOT NULL AFTER `name`;

ALTER TABLE `City` ADD `isActive` TINYINT(1) NOT NULL DEFAULT '0' AFTER `name`;

ALTER TABLE `TrialstudentextraDetail` CHANGE `fatherEmailId` `extrafatherEmailId` VARCHAR(265) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;

ALTER TABLE `TrialstudentextraDetail` CHANGE `fatherName` `extrafatherName` VARCHAR(265) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;

--END sasedeve Edited by Mrutyunjaya on 18-04-2017