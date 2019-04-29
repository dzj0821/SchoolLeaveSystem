/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : school_leave_system

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-04-29 23:49:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` int(1) NOT NULL COMMENT '专业内分班编号，如软件1班',
  `grade_id` int(11) NOT NULL COMMENT '所属年级',
  `major_id` int(11) NOT NULL COMMENT '所属专业',
  PRIMARY KEY (`id`),
  KEY `class_ibfk_1` (`grade_id`),
  KEY `class_ibfk_2` (`major_id`),
  CONSTRAINT `clazz_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`year`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clazz_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='班级表\r\n/*\r\nBEGIN\r\n    SET @count = (SELECT COUNT(*) FROM class WHERE grade_year = NEW.grade_year AND major_id = NEW.major_id AND no = NEW.no);\r\n    IF @count != 0 THEN\r\n        SIGNAL SQLSTATE ''45000''\r\n SET MESSAGE_TEXT = ''试图往班级表中插入重复的班级'';\r\n    END IF;\r\nEND\r\n*/';

-- ----------------------------
-- Table structure for collage
-- ----------------------------
DROP TABLE IF EXISTS `collage`;
CREATE TABLE `collage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='学院表';

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(5) NOT NULL COMMENT '年级的年份',
  PRIMARY KEY (`id`),
  KEY `year` (`year`)
) ENGINE=InnoDB AUTO_INCREMENT=2021 DEFAULT CHARSET=utf8 COMMENT='年级表';

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '请假用户的id',
  `clazz_name` varchar(255) NOT NULL COMMENT '请假学生请假时的完整班级名，如2019级计算机与信息工程学院软件工程1班',
  `telephone` int(11) NOT NULL COMMENT '请假学生请假时的电话',
  `start_date` date NOT NULL COMMENT '请假的开始日期',
  `start_lesson` int(2) NOT NULL COMMENT '从第X节课开始请假',
  `end_date` date NOT NULL COMMENT '请假的结束日期',
  `end_lesson` int(2) NOT NULL COMMENT '请假到第X节课为止',
  `reason` text NOT NULL COMMENT '请假理由',
  `create_time` datetime NOT NULL COMMENT '请假申请创建的时间',
  `type` int(1) NOT NULL COMMENT '假条状态\r\n0-等待审核\r\n1-未通过\r\n2-通过',
  `reviewer_id` int(11) DEFAULT NULL COMMENT '审核人的id',
  `review_time` datetime DEFAULT NULL COMMENT '审核的时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `reviewer_id` (`reviewer_id`),
  CONSTRAINT `leave_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leave_ibfk_2` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ip` varchar(100) NOT NULL,
  `action` varchar(100) NOT NULL,
  `message` text,
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '专业名称',
  `collage_id` int(11) NOT NULL COMMENT '所属学院',
  PRIMARY KEY (`id`),
  KEY `belong_collage_id` (`collage_id`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`collage_id`) REFERENCES `collage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='专业表';

-- ----------------------------
-- Table structure for permission_clazz
-- ----------------------------
DROP TABLE IF EXISTS `permission_clazz`;
CREATE TABLE `permission_clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '给予权限的用户id',
  `class_id` int(11) NOT NULL COMMENT '给予X班级的权限',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `permission_clazz_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_clazz_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `clazz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission_collage
-- ----------------------------
DROP TABLE IF EXISTS `permission_collage`;
CREATE TABLE `permission_collage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '给予权限的用户id',
  `collage_id` int(11) NOT NULL COMMENT '给予X学院的权限',
  PRIMARY KEY (`id`),
  KEY `collage_id` (`collage_id`) USING BTREE,
  KEY `user_id` (`user_id`),
  CONSTRAINT `permission_collage_ibfk_1` FOREIGN KEY (`collage_id`) REFERENCES `collage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_collage_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `hex256_password` varchar(128) NOT NULL COMMENT 'hex256加密后的密码',
  `type` int(1) NOT NULL COMMENT '用户类型\r\n0-超级管理员\r\n1-院级管理员\r\n2-班级管理员\r\n3-普通用户（学生）',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `telephone` int(11) NOT NULL COMMENT '电话',
  `client_token` varchar(64) DEFAULT NULL COMMENT '客户端保持登录验证用的token',
  `client_id` varchar(20) DEFAULT NULL COMMENT '客户端的唯一id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
