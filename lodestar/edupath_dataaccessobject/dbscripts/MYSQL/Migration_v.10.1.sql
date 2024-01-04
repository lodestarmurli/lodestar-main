CREATE TABLE IF NOT EXISTS `GSInputanswer` (
`id` int(11) NOT NULL,
  `studentid` int(11) NOT NULL,
  `questionno` int(11) NOT NULL,
  `answer` varchar(256) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `TipsAndSuggestion` (
`id` int(11) NOT NULL,
  `studenid` int(11) NOT NULL,
  `facilitatorid` int(11) DEFAULT NULL,
  `issession1completefaci` tinyint(1) NOT NULL DEFAULT '0',
  `issession2completefaci` tinyint(1) NOT NULL DEFAULT '0',
  `Session1date` datetime DEFAULT NULL,
  `Session2date` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE `GSInputanswer`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `TipsAndSuggestion`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `GSInputanswer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

ALTER TABLE `TipsAndSuggestion`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

INSERT INTO `ApplicationMenu` (`id`, `refName`, `displayName`, `actionPath`, `menuLevel`, `parentId`, `menuOrder`, `roleWeight`, `enablePermission`, `allowCreate`, `allowRead`, `allowUpdate`, `allowDelete`, `iconPath`) VALUES
(52, 'TipandSuggestion', 'com.lodestar.edupath.navigation.tab.tipsandsuggestion', 'TipsuggetionsAction', 1, 22, 5, 3, 1, 1, 1, 1, 1, ''),
(53, 'GSinput', 'com.lodestar.edupath.navigation.tab.gsinput', 'GSinput', 1, 27, 3, 3, 1, 1, 1, 1, 1, ''),
(54, 'GSInputs', 'com.lodestar.edupath.navigation.tab.gsinput', 'GSinputsView', 1, NULL, 8, 1, 1, 1, 1, 1, 1, '');