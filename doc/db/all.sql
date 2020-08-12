
drop table if exists chapter;
CREATE TABLE `chapter` (
  `id` varchar(8) NOT NULL comment 'ID',
  `course_id` char(8) comment '课程ID',
  `name` varchar(50) comment '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='大章';






drop table if exists test;

CREATE TABLE `test` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


insert into test (id,name) values (1,"测试");
