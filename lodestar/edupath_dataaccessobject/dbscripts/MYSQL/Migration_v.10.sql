CREATE TABLE IF NOT EXISTS `PartnerDetails` (
`id` int(11) NOT NULL,
  `UIN` text NOT NULL,
  `PartneName` varchar(256) NOT NULL,
  `EmailID` varchar(256) NOT NULL,
  `Password` text NOT NULL,
  `AuthCode` text NOT NULL,
  `EncryptionCode` text NOT NULL,
  `IsSendMail` tinyint(1) NOT NULL DEFAULT '1',
  `EmailTemplate` varchar(265) DEFAULT NULL,
  `logpath` text,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `CreationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE `PartnerDetails`
 ADD PRIMARY KEY (`id`);
 
 ALTER TABLE `PartnerDetails`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `PartnerStudentDetails` (
`id` int(11) NOT NULL,
  `partnerUIN` varchar(265) NOT NULL,
  `studenUIN` varchar(265) DEFAULT NULL,
  `studentid` int(11) NOT NULL,
  `LDID` varchar(265) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


ALTER TABLE `PartnerStudentDetails`
 ADD PRIMARY KEY (`id`);
 
 ALTER TABLE `PartnerStudentDetails`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `MessageFailed` (
`id` int(10) unsigned NOT NULL,
  `queuedTime` datetime DEFAULT NULL,
  `messageSubject` text,
  `messageData` text,
  `toAddress` varchar(500) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `notifierType` varchar(500) DEFAULT NULL,
  `notifiedTime` datetime DEFAULT NULL,
  `filePath` varchar(500) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE `MessageFailed`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `MessageFailed`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

ALTER TABLE `PartnerStudentDetails` ADD `CityString` VARCHAR(265) NULL AFTER `LDID`, ADD `IsRegister` TINYINT(1) NOT NULL DEFAULT '0' AFTER `CityString`;
INSERT INTO `ApplicationMenu` (`refName`, `displayName`, `actionPath`, `menuLevel`, `parentId`, `menuOrder`, `roleWeight`, `enablePermission`, `allowCreate`, `allowRead`, `allowUpdate`, `allowDelete`, `iconPath`) VALUES
('WorldOfJobs', 'com.lodestar.edupath.navigation.tab.worldofjobs', 'worldofjobs', 1, 4, 4, 2, 1, 1, 1, 1, 1, 'globe');
INSERT INTO `ApplicationMenu` (`refName`, `displayName`, `actionPath`, `menuLevel`, `parentId`, `menuOrder`, `roleWeight`, `enablePermission`, `allowCreate`, `allowRead`, `allowUpdate`, `allowDelete`, `iconPath`) VALUES
('FailedEmails', 'com.lodestar.edupath.navigation.tab.FailedEmails', 'ManageFailedEmails', 1, NULL, 8, 1, 1, 1, 1, 1, 1, '');
