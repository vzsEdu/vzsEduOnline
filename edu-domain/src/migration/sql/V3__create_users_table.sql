CREATE TABLE civil.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `login_id` VARCHAR(100) COMMENT 'login id',
  `email` VARCHAR(100)  COMMENT 'email',
  `mobile` VARCHAR(30) COMMENT 'mobile',
  `account_type` INT(1) COMMENT '0: admin account, 1: company account, 2: personal account',
  `password` VARCHAR(256) NOT NULL COMMENT 'password MD5',
  `portrait` VARCHAR(200)  COMMENT 'portrait',
  `role_id` VARCHAR(20) COMMENT 'role id, foreign key to roles table',
  `nick_name` VARCHAR(100)  COMMENT 'nick name or company name',
  `verified` INT(1) default '0' COMMENT '0: not verified, 1: verified',
  `active_ind` INT(1) default '1' COMMENT '0: inactived, 1: activated',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'create time',
  `created_by` VARCHAR(100) default 'Auto' COMMENT 'create by',
  `updated_at`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  `updated_by` VARCHAR(100) default 'Auto' COMMENT 'updated by',
  primary KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;