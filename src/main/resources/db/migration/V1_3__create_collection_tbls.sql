CREATE TABLE estecnicslabscom.`collectioninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `docId` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `collectionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE estecnicslabscom.`collections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE=InnoDB DEFAULT CHARSET=latin1;