drop table if exists civil.`person_blackboard`;
CREATE TABLE civil.`person_blackboard` (
  `person_blackboard_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `user_id` VARCHAR(100) COMMENT 'user id',
  `blackboard` LONGTEXT COMMENT '',
  `deleted` INT(1) NOT NULL DEFAULT '0' COMMENT 'Soft delete',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'create time',
  `created_by` VARCHAR(100) default 'Auto' COMMENT 'create by',
  `updated_at`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  `updated_by` VARCHAR(100) default 'Auto' COMMENT 'updated by',
  primary KEY (`person_blackboard_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;