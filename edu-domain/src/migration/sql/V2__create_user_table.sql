drop table if exists civil.`personal_users`;
CREATE TABLE civil.`personal_users` (
  `personalUserId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `name` VARCHAR(200) NOT NULL COMMENT 'user name',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedAt`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  `regId` VARBINARY(40) COMMENT 'Updated user',
  primary KEY (`personalUserId`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;