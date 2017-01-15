create database db_test;

CREATE TABLE `t_user` (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
	`user_name` varchar(250) NOT NULL DEFAULT '' COMMENT '用户名',
	`password` varchar(250) NOT NULL DEFAULT '' COMMENT '密码',
	`is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '软删除标志位，0：未删除，1：删除',
	
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';