
drop table if exists chapter;
CREATE TABLE `chapter` (
  `id` varchar(8) NOT NULL comment 'ID',
  `course_id` char(8) comment '课程ID',
  `name` varchar(50) comment '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='大章';



insert into chapter (id,name,course_id) values ('1','54545','321321');
insert into chapter (id,name,course_id) values ('2','54545','321321');
insert into chapter (id,name,course_id) values ('3','54545','321321');
insert into chapter (id,name,course_id) values ('4','54545','321321');
insert into chapter (id,name,course_id) values ('5','54545','321321');
insert into chapter (id,name,course_id) values ('6','54545','321321');
insert into chapter (id,name,course_id) values ('7','54545','321321');
insert into chapter (id,name,course_id) values ('8','54545','321321');
insert into chapter (id,name,course_id) values ('9','54545','321321');
insert into chapter (id,name,course_id) values ('10','54545','321321');
insert into chapter (id,name,course_id) values ('11','54545','321321');
insert into chapter (id,name,course_id) values ('12','54545','321321');
insert into chapter (id,name,course_id) values ('13','54545','321321');
insert into chapter (id,name,course_id) values ('14','54545','321321');
insert into chapter (id,name,course_id) values ('15','54545','321321');
insert into chapter (id,name,course_id) values ('16','54545','321321');
insert into chapter (id,name,course_id) values ('17','54545','321321');
insert into chapter (id,name,course_id) values ('18','54545','321321');
insert into chapter (id,name,course_id) values ('19','54545','321321');
insert into chapter (id,name,course_id) values ('20','54545','321321');
insert into chapter (id,name,course_id) values ('21','54545','321321');
insert into chapter (id,name,course_id) values ('22','54545','321321');
insert into chapter (id,name,course_id) values ('23','54545','321321');
insert into chapter (id,name,course_id) values ('24','54545','321321');
insert into chapter (id,name,course_id) values ('25','54545','321321');
insert into chapter (id,name,course_id) values ('26','54545','321321');
insert into chapter (id,name,course_id) values ('27','54545','321321');
insert into chapter (id,name,course_id) values ('28','54545','321321');
insert into chapter (id,name,course_id) values ('29','54545','321321');
insert into chapter (id,name,course_id) values ('30','54545','321321');
insert into chapter (id,name,course_id) values ('31','54545','321321');
insert into chapter (id,name,course_id) values ('32','54545','321321');



drop table if exists test;

CREATE TABLE `test` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


insert into test (id,name) values (1,"测试");
