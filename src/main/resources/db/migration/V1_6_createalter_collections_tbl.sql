CREATE TABLE `estecnicslabscom`.`sharedCollections` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `collectionId` INT(11) NOT NULL,
 `ownerId` BIGINT(20) NULL,
 `sharedUserId` BIGINT(20) NOT NULL,
 PRIMARY KEY (`collectionId`, `sharedUserId`),
 UNIQUE INDEX `id_UNIQUE` (`id` ASC),
 CONSTRAINT `id`
 FOREIGN KEY (`collectionId`)
 REFERENCES `estecnicslabscom`.`collections` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION);

ALTER TABLE `estecnicslabscom`.`collectioninfo`
ADD CONSTRAINT `fk_collectioninfo_1`
  FOREIGN KEY (`collectionId`)
  REFERENCES `estecnicslabscom`.`collections` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE estecnicslabscom.collectioninfo ADD CONSTRAINT uq_collectioninfo UNIQUE(docId, collectionId);

ALTER TABLE estecnicslabscom.collections ADD CONSTRAINT uq_collections UNIQUE(name, userId);

