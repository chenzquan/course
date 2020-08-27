
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



drop table if exists `section`;
create table `section`(
    `id` char (8) not null default '' comment 'ID',
    `title` varchar (50) not null comment '标题',
    `course_id` char (8) comment '课程|course.id',
    `chapter_id` char (8) comment '大章|chapter.id',
    `video` varchar(200) comment '视频',
    `time` INT comment '时长|单位秒',
    `charge` char (1) comment '收费|c 收费；F 免费',
    `sort` int (1) comment '顺序',
    `created_at` DATETIME(3) comment '创建时间',
    `updated_at` DATETIME(3) comment '修改时间',
      PRIMARY KEY (`id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='小节';

insert into `section` (id,title,course_id,chapter_id,video,time,charge,sort,created_at,updated_at)
values('00000001','测试小节01','00000001','00000000','',500,'F',1,now(),now());


drop table if exists `course`;
create table `course`(
    `id` char (8) not null default '' comment 'ID',
    `name` varchar(50) not null comment '名称',
    `summary` varchar(2000) comment '概述',
    `time` INT comment '时长|单位秒',
    `price` decimal(8,2) default 0.00 comment '价格(元)',
    `image` varchar (100) comment '封面',
    `level` char (1)  comment '级别|枚举[CourseLevelEnum]: ONE("1","初级"),TWO("2","中级"),THREE("3","高级")',
    `charge` char (1) comment '收费|枚举[CourseChargeEnum]: CHARGE("C","收费"),FREE("F","免费")',
    `status` char (1) comment '状态|枚举[CourseStatusEnum]: PUBLISH("P","发布"),DRAFT("D","草稿")',
    `enroll` integer default 0 comment '报名数',
    `sort` int comment '顺序',
    `created_at` DATETIME(3) comment '创建时间',
    `updated_at` DATETIME(3) comment '修改时间',
      PRIMARY KEY (`id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment='课程表';


insert into course(id,name,summary,time,price,image,level,charge,status,enroll,sort,created_at,updated_at)
values ('00000001','测试课程01','这是一门测试课程',7200,19.9,'',0,'C','D',100,0,now(),now());






drop table if exists test;

CREATE TABLE `test` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


insert into test (id,name) values (1,'测试');








