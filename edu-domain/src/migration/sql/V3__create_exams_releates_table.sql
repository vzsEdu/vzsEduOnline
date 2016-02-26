drop table if exists eduOnline.`check_points`;
CREATE TABLE eduOnline.`check_points` (
  `check_point_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `check_point_name_level_one` VARCHAR(200) NOT NULL COMMENT 'check point name',
  `check_point_name_level_two` VARCHAR(200) NOT NULL COMMENT 'check point second name like 语法中的时态',
  `check_point_name_level_three` VARCHAR(200) COMMENT 'check point third name like 语法中的时态的过去时',
  `check_point_name_level_four` VARCHAR(200)  COMMENT 'check point fourth name like 扩展',
  `check_point_desc` VARCHAR(200) COMMENT 'check point description',
  primary KEY (`check_point_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO eduOnline.`check_points` (check_point_name_level_one, check_point_name_level_two, check_point_name_level_three,
check_point_name_level_four, check_point_desc)
 VALUES('grammar', 'tense', '', '', '语法/时态');


drop table if exists eduOnline.`exam_types`;
CREATE TABLE eduOnline.`exam_types` (
  `exam_type_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `name` VARCHAR(200) NOT NULL COMMENT '题型',
  `description` VARCHAR(200) COMMENT 'description',
  primary KEY (`exam_type_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO eduOnline.`exam_types` (name, description)
 VALUES('SINGLE_OPTION', '单选题');


drop table if exists eduOnline.`exams`;
CREATE TABLE eduOnline.`exams` (
  `exam_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `title` VARCHAR(200) NOT NULL COMMENT 'exam title',
  `raise_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出考卷的日期, 比如2015年统考, 就是2015年的, 不是录入日期',
  `source` VARCHAR (100) COMMENT 'the exam comes from',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedAt`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  `regId` VARBINARY(40) COMMENT 'Updated user',
  primary KEY (`exam_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
