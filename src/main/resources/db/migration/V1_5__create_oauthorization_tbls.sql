DROP TABLE `estecnicslabscom`.`office365Tokens`;
DROP TABLE `estecnicslabscom`.`Office365Authorization`;

CREATE TABLE `estecnicslabscom`.`OauthAuthorization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clientId` text NOT NULL,
  `clientSecret` text NOT NULL,
  `provider` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE `estecnicslabscom`.`oauthTokens` (
  `seqId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `accessToken` text NOT NULL,
  `refreshToken` text,
  `id` int(11) DEFAULT NULL,
  `scope` text,
  `expiresIn` date DEFAULT NULL,
  `createdDateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seqId`),
  KEY `fk_oauthTokens_1_idx` (`id`),
  CONSTRAINT `fk_oauthTokens_1` FOREIGN KEY (`id`) REFERENCES `OauthAuthorization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;