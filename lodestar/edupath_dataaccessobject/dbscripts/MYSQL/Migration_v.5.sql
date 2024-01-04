CREATE TABLE IF NOT EXISTS `Payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `loginId` varchar(100) NOT NULL,
  `agreedAmount` decimal(10,2) NOT NULL,
  `dueAmount` decimal(10,2) NOT NULL,
  `paidAmount` decimal(10,2) NOT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);