ALTER TABLE  `TrialInterestCodeMapping` ADD  `isClass10` TINYINT( 1 ) NOT NULL DEFAULT  '1' AFTER  `raisecCode` ;
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