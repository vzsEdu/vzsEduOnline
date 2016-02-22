drop table if exists civil.`projects`;
CREATE TABLE civil.`projects` (
  `project_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
  `user_id` INT(11) NOT NULL,
  `publish_date` DATETIME NOT NULL COMMENT 'project publish date, 发布项目的时间',
  `release_date` DATETIME NOT NULL COMMENT 'project release date, 项目开始的时间',
  `project_name` VARCHAR(100) NOT NULL COMMENT 'project name',
  `province` VARCHAR(100) NOT NULL COMMENT '省',
  `city` VARCHAR(100) NOT NULL COMMENT '市',
  `industry_involved` VARCHAR(200) NOT NULL COMMENT '所属行业',
  `project_detail_description` VARCHAR(350) NOT NULL COMMENT '具体描述',
  `collaborate_way` VARCHAR(200) NOT NULL COMMENT '合作方式',
  `task_detail_description` VARCHAR(550) NOT NULL COMMENT '具体描述',
  `dead_line` VARCHAR(250) NOT NULL COMMENT '项目期限',
  `profit` VARCHAR(250) NOT NULL COMMENT '项目回报',
  `comment` VARCHAR(350) NOT NULL COMMENT '备注',
  `imagePath` VARCHAR(200) COMMENT '项目图片路径',
  `published` INT(1) NOT NULL DEFAULT '0' COMMENT '是否发布',
  `deleted` INT(1) NOT NULL DEFAULT '0' COMMENT 'Soft delete',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedAt`DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  `regId` VARBINARY(40) COMMENT 'Updated user',
  primary KEY (`project_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;