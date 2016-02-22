drop table if exists civil.`application_center`;
CREATE TABLE civil.`application_center` (
  `application_center_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `user_id` INT(11) NOT NULL,
  `software_name` VARCHAR(150) COMMENT 'software name',
  `software_description` LONGTEXT COMMENT 'software desc',
  `image_path` VARCHAR(200) COMMENT '项目图片路径',
  `contact_way` VARCHAR(150) COMMENT '联系方式,可以填写qq,邮箱,电话等任意方式',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'create time',
  `created_by` VARCHAR(100) default 'Auto' COMMENT 'create by',
  `updated_at`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  `updated_by` VARCHAR(100) default 'Auto' COMMENT 'updated by',
  primary KEY (`application_center_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;