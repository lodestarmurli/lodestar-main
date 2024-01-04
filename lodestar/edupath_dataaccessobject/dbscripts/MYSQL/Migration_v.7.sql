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

ALTER TABLE `Board` ADD `cityId` INT(11) NOT NULL AFTER `name`;

ALTER TABLE `City` ADD `isActive` TINYINT(1) NOT NULL DEFAULT '0' AFTER `name`;

ALTER TABLE `TrialstudentextraDetail` CHANGE `fatherEmailId` `extrafatherEmailId` VARCHAR(265) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;

ALTER TABLE `TrialstudentextraDetail` CHANGE `fatherName` `extrafatherName` VARCHAR(265) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;

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

INSERT INTO `CollegeParametersValues` (`displayValue`, `actualValue`, `parameterId`, `displayOrder`) VALUES ('Telangana Intermediate Board', '6', '1', '6');





