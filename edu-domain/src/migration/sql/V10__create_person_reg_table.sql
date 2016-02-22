drop table if exists civil.`person`;
CREATE TABLE civil.`person` (
  `person_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `user_id` VARCHAR(100) COMMENT 'user id',
  `person_name` VARCHAR(200) COMMENT '真实姓名',
  `enterprise` VARCHAR(200) COMMENT '所在公司',
  `location` VARCHAR(100) NOT NULL COMMENT '地区',
  `industry_involved` VARCHAR(200) NOT NULL COMMENT '所属行业',
  `work_experience` VARCHAR(2000) COMMENT '工作经历',
  `education_background` VARCHAR(2000) COMMENT '教育背景',
  `certificate_skill` VARCHAR(2000) COMMENT '个人证书和技能',
  `recommend_letter` VARCHAR(2000) COMMENT '推荐信',
  `verified` INT(1) default '0' COMMENT '0: not verified, 1: verified',
  `active_ind` INT(1) default '1' COMMENT '0: inactived, 1: activated',
  `deleted` INT(1) NOT NULL DEFAULT '0' COMMENT 'Soft delete',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'create time',
  `created_by` VARCHAR(100) default 'Auto' COMMENT 'create by',
  `updated_at`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  `updated_by` VARCHAR(100) default 'Auto' COMMENT 'updated by',
  primary KEY (`person_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;