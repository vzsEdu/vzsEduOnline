drop table if exists eduOnline.`students`;
CREATE TABLE eduOnline.`students` (
  `student_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `name` VARCHAR(200) NOT NULL COMMENT 'student name',
  `register_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `register_grade` VARCHAR(100) NOT NULL COMMENT '注册时候的年纪',
  `school` VARCHAR (100) NOT NULL COMMENT 'school name',
  `birthday` DATETIME NOT NULL,
  `gender` INT(1) NOT NULL COMMENT '0 is woman, 1 is man',
  `phone` VARCHAR(50) COMMENT 'phone number',
  `address` VARCHAR(100) COMMENT 'address',
  `e_mail` VARCHAR (50) COMMENT 'eMail',
  `parent_name` VARCHAR (20) COMMENT 'parent Name',
  `parent_related` VARCHAR (20) COMMENT '家长关系, 比如爸爸,妈妈',
  `parent_phone` VARCHAR (20) COMMENT 'parent phone number',
  `parent_weChat_id` VARCHAR (50) COMMENT 'parent we chat ID',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedAt`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  `regId` VARBINARY(40) COMMENT 'Updated user',
  primary KEY (`student_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;