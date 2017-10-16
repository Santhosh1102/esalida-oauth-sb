ALTER TABLE `estecnicslabscom`.`office365Tokens`
CHANGE COLUMN `expiresIn` `expiresIn` DATETIME NOT NULL ;

ALTER TABLE `esalidaoauth`.`tenant` ADD COLUMN `tenantUniqueIdentifier` VARCHAR(45) NOT NULL AFTER `tenantDbName`;

UPDATE `esalidaoauth`.`tenant` SET `tenantUniqueIdentifier`='a2f18ca9' WHERE `id`='1001';
