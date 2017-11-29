CREATE TABLE `adapters_monitoring` (
  `messageId` varchar(45) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `queueName` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `payloadBody` longtext NOT NULL,
  `errorString` longtext,
  `inQueueTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;