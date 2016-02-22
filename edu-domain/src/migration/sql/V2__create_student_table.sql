drop table if exists eduOnline.`students`;
CREATE TABLE eduOnline.`students` (
  `studentId` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `name` VARCHAR(200) NOT NULL COMMENT 'student name',
  `registerDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registerGrade` VARCHAR(100) NOT NULL COMMENT '注册时候的年纪',
  `birthday` DATETIME NOT NULL,
  `gender` INT(1) NOT NULL COMMENT '0 is woman, 1 is man',
  `phone` VARCHAR(50) COMMENT 'phone number',
  `address` VARCHAR(100) COMMENT 'address',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedAt`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  `regId` VARBINARY(40) COMMENT 'Updated user',
  primary KEY (`personalUserId`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;