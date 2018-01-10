CREATE TABLE `estecnicslabscom`.`dashboards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(300) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `type` varchar(45) DEFAULT NULL,
  `query` json DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

CREATE TABLE `estecnicslabscom`.`sharedDashboards` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dashboardId` int(11) NOT NULL,
  `sharedUserId` bigint(20) NOT NULL,
  PRIMARY KEY (`dashboardId`,`sharedUserId`),
  UNIQUE KEY `index2` (`id`),
  CONSTRAINT `sharedDashboards_ibfk_1` FOREIGN KEY (`dashboardId`) REFERENCES `dashboards` (`id`)
);