DROP TABLE `estecnicslabscom`.`collectioninfo`;
DROP TABLE `estecnicslabscom`.`collections`;

CREATE TABLE `estecnicslabscom`.`collections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_collections` (`name`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `estecnicslabscom`.`collectioninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `docId` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `collectionId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_collectioninfo` (`docId`,`collectionId`),
  KEY `fk_collectioninfo_1_idx` (`collectionId`),
  CONSTRAINT `fk_collectioninfo_1` FOREIGN KEY (`collectionId`) REFERENCES `collections` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `estecnicslabscom`.`sharedCollections` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `collectionId` INT(11) NOT NULL,
 `ownerId` BIGINT(20) NULL,
 `sharedUserId` BIGINT(20) NOT NULL,
 PRIMARY KEY (`collectionId`, `sharedUserId`),
 UNIQUE INDEX `id_UNIQUE` (`id` ASC),
 CONSTRAINT
 FOREIGN KEY (`collectionId`)
 REFERENCES `estecnicslabscom`.`collections` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION);
