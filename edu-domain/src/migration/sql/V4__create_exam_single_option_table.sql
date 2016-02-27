drop table if exists eduOnline.`exam_question_options`;
CREATE TABLE eduOnline.`exam_question_options` (
  `exam_question_option_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `exam_id` INT(11) COMMENT 'the question belong to which exam, empty means not belong to any exam',
  `squenceId` INT(10) COMMENT 'the sequence of question',
  `preReading` MEDIUMTEXT COMMENT '题目主干,类似阅读的全文',
  `question` MEDIUMTEXT NOT NULL COMMENT '题目包含了空格',
  `option_A` VARCHAR(100) NOT NULL COMMENT 'option a',
  `option_B` VARCHAR(100)  COMMENT 'option b',
  `option_C` VARCHAR(100)  COMMENT 'option c',
  `option_D` VARCHAR(100) COMMENT 'option d',
  `option_E` VARCHAR(100) COMMENT 'option e',
  `option_F` VARCHAR(100)  COMMENT 'option f',
  `answer` VARCHAR (100) COMMENT 'split by comma',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  primary KEY (`exam_question_option_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
