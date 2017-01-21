create database db_test;

CREATE TABLE `t_user` (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
	`user_name` varchar(250) NOT NULL DEFAULT '' COMMENT '用户名',
	`password` varchar(250) NOT NULL DEFAULT '' COMMENT '密码',
	
	`is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '软删除标志位，0：未删除，1：删除',
	`created_by` bigint(20) NOT NULL DEFAULT '-1' COMMENT '创建人员',
	`created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`updated_by` bigint(4) NOT NULL DEFAULT '-1' COMMENT '更新人员',
	`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',

	PRIMARY KEY (`id`),
	KEY `idx_created_at` (`created_at`),
	KEY `idx_updated_at` (`updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用戶表';
