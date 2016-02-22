drop table if exists civil.`project_application`;
CREATE TABLE civil.`project_application` (
  `project_application_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `user_id` VARCHAR(100) COMMENT 'user id',
  `project_id` INT(11) NOT NULL,
  `status` VARCHAR(50) NOT NULL COMMENT '申请状态',
  `comment` VARCHAR(350) NOT NULL COMMENT '备注',
  `apply_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '申请时间',
  `last_updated_time` DATETIME NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'create time',
  `created_by` VARCHAR(100) default 'Auto' COMMENT 'create by',
  `updated_at`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  `updated_by` VARCHAR(100) default 'Auto' COMMENT 'updated by',
  primary KEY (`project_application_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;