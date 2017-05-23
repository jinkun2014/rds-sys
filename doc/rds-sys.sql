/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.26 : Database - rds_sys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rds_sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rds_sys`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `menu_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '是否是叶子',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源id',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`open_mode`,`description`,`icon`,`pid`,`seq`,`status`,`menu_type`,`is_leaf`,`resource_id`,`del_flag`,`update_time`,`create_time`) values (236,'系统管理','','0','','',NULL,0,0,0,1,NULL,0,'2017-01-08 03:33:16','2017-01-08 03:33:16'),(237,'机构管理','','0','','',236,0,0,0,1,NULL,0,'2017-01-08 03:33:32','2017-01-08 03:33:32'),(238,'权限管理','','0','','',236,0,0,0,1,NULL,0,'2017-01-08 03:38:43','2017-01-08 03:38:43'),(239,'机构列表','/sys/orgs/ui/list','0','','',237,0,0,0,1,NULL,0,'2017-01-10 21:13:09','2017-01-08 03:38:56'),(240,'用户列表','/sys/users/ui/list','0','','',237,0,0,0,0,NULL,0,'2017-01-10 21:14:55','2017-01-08 03:39:08'),(242,'列表','','0','','',239,0,0,1,0,NULL,0,'2017-01-10 16:23:51','2017-01-08 10:22:24'),(244,'增加','','0','','',239,1,0,1,0,NULL,0,'2017-01-08 10:23:19','2017-01-08 10:23:19'),(245,'资源列表','/sys/resources/ui/list','0','','',238,0,0,0,0,NULL,0,'2017-01-10 22:59:30','2017-01-10 21:15:22'),(246,'角色列表','/sys/roles/ui/list','0','','',238,0,0,0,0,NULL,0,'2017-01-10 21:16:15','2017-01-10 21:16:15'),(247,'物业管理','','0','','',NULL,0,0,0,1,NULL,0,'2017-01-10 21:20:31','2017-01-10 21:20:31'),(248,'小区管理','','0','','',247,0,0,0,1,NULL,0,'2017-01-10 21:20:48','2017-01-10 21:20:48'),(249,'垃圾管理','','0','','',248,0,0,0,0,NULL,0,'2017-01-10 21:21:00','2017-01-10 21:21:00');

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '叶子节点',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='组织机构';

/*Data for the table `sys_org` */

insert  into `sys_org`(`id`,`name`,`address`,`code`,`icon`,`pid`,`is_leaf`,`seq`,`del_flag`,`update_time`,`create_time`) values (26,'百度','','','',NULL,1,1,0,'2017-04-21 15:32:14','2017-01-06 12:37:12'),(27,'事业部','','','',26,1,0,0,'2017-01-06 12:38:56','2017-01-06 12:37:26'),(28,'开发部','','','',27,0,0,0,'2017-01-06 12:37:37','2017-01-06 12:37:37'),(29,'产品部','','','',27,0,1,1,'2017-01-06 12:37:50','2017-01-06 12:37:50'),(30,'售后部','','','',26,0,1,0,'2017-01-06 12:38:12','2017-01-06 12:38:12'),(31,'产品部','','','',27,0,1,1,'2017-01-06 12:41:17','2017-01-06 12:41:17'),(32,'产品部','','','',27,0,1,1,'2017-01-06 12:42:38','2017-01-06 12:42:38'),(33,'产品部','','','',27,0,1,1,'2017-01-06 12:45:32','2017-01-06 12:45:32'),(34,'系统开发','','','',NULL,0,0,0,'2017-04-21 15:32:00','2017-01-06 12:55:39'),(35,'测试','','','',NULL,0,0,1,'2017-04-21 15:33:06','2017-04-21 15:33:06');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(100) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '是否是叶子',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`url`,`open_mode`,`description`,`icon`,`pid`,`seq`,`status`,`resource_type`,`is_leaf`,`del_flag`,`update_time`,`create_time`) values (236,'系统管理','','0','','/resources/images/32x32/settings.png',NULL,1,0,0,1,0,'2017-05-22 22:51:03','2017-01-08 03:33:16'),(237,'机构管理','','0','','/resources/images/32x32/1-1.png',236,0,0,0,1,0,'2017-05-22 22:39:11','2017-01-08 03:33:32'),(238,'权限管理','','0','','/resources/images/32x32/1-1.png',236,1,0,0,1,0,'2017-05-22 22:39:18','2017-01-08 03:38:43'),(239,'机构列表','/sys/orgs/ui/list','0','','/resources/images/32x32/user.png',237,0,0,0,1,0,'2017-05-23 08:12:16','2017-01-08 03:38:56'),(240,'用户列表','/sys/users/ui/list','0','','/resources/images/32x32/1-1.png',237,0,0,0,0,0,'2017-05-22 22:39:40','2017-01-08 03:39:08'),(245,'资源列表','/sys/resources/ui/list','0','','/resources/images/32x32/graphic-design.png',238,0,0,0,0,0,'2017-05-23 08:19:42','2017-01-10 21:15:22'),(246,'角色列表','/sys/roles/ui/list','0','','/resources/images/32x32/1-1.png',238,0,0,0,0,0,'2017-05-22 22:39:51','2017-01-10 21:16:15'),(247,'直播管理','','0','','/resources/images/32x32/photography.png',NULL,0,0,0,1,0,'2017-05-22 22:55:36','2017-01-10 21:20:31'),(248,'直播会员','','0','','/resources/images/32x32/sign-out.png',247,0,0,0,1,0,'2017-05-22 22:50:08','2017-01-10 21:20:48'),(249,'用户管理','/live/users/ui/list','0','','/resources/images/32x32/1-1.png',248,0,0,0,0,0,'2017-05-22 22:40:00','2017-01-10 21:21:00');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`seq`,`description`,`status`,`del_flag`,`update_time`,`create_time`) values (11,'系统管理员',0,'',0,0,'2017-01-08 17:30:37','2017-01-08 17:30:37'),(14,'普通员工',1,'',0,NULL,NULL,NULL);

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=606 DEFAULT CHARSET=utf8 COMMENT='角色-资源';

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (564,11,236),(565,11,237),(566,11,239),(569,11,240),(570,11,238),(571,11,245),(572,11,246),(573,11,247),(574,11,248),(575,11,249),(603,14,247),(604,14,248),(605,14,249);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) NOT NULL COMMENT '登录名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `sex` tinyint(2) DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_type` tinyint(2) DEFAULT '0' COMMENT '用户类别',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`name`,`password`,`sex`,`age`,`phone`,`user_type`,`status`,`del_flag`,`update_time`,`create_time`) values (38,'admin','系统管理员','6ff3b18929bae1973fceaf16e8746f08',0,NULL,'',0,0,0,'2017-01-09 10:52:59','2017-01-09 10:52:59'),(42,'test','测试','986b9372eaaa6adab68b024c0be2c7e3',0,NULL,'',0,0,0,'2017-01-16 21:46:39','2017-01-16 21:34:54');

/*Table structure for table `sys_user_org` */

DROP TABLE IF EXISTS `sys_user_org`;

CREATE TABLE `sys_user_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户-机构';

/*Data for the table `sys_user_org` */

insert  into `sys_user_org`(`id`,`user_id`,`org_id`) values (22,38,34),(25,42,28);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (4,38,11),(7,42,14);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
