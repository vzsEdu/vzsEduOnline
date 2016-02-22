drop table if exists civil.`project_history`;
CREATE TABLE civil.`project_history` (
  `project_history_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `user_id` VARCHAR(200) COMMENT 'user id',
  `project_name` VARCHAR(100) NOT NULL COMMENT 'project name',
  `image_path` VARCHAR(200) COMMENT '项目图片路径',
  `description` VARCHAR(1000) NOT NULL COMMENT '项目描述',
  `deleted` INT(1) NOT NULL DEFAULT '0' COMMENT 'Soft delete',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'create time',
  `created_by` VARCHAR(100) default 'Auto' COMMENT 'create by',
  `updated_at`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  `updated_by` VARCHAR(100) default 'Auto' COMMENT 'updated by',
  primary KEY (`project_history_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;