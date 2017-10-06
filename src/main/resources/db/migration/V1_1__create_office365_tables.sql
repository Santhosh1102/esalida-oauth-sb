
CREATE TABLE estecnicslabscom.`Office365Authorization` (
  `id` int(11) NOT NULL,
  `client_id` text,
  `client_secret` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE estecnicslabscom.`office365Tokens` (
  `userId` int(11) NOT NULL,
  `accessToken` text NOT NULL,
  `refreshToken` text NOT NULL,
  `id` int(11) DEFAULT NULL,
  `scope` text NOT NULL,
  `expiresIn` mediumtext NOT NULL,
  `createdDateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`),
  KEY `id_idx` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `Office365Authorization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO estecnicslabscom.`Office365Authorization` VALUES (1,'8d8ffe9c-a0b4-4c75-8692-3301394b7391','JwK2ghy77yq2qfewQbOwWEW');
