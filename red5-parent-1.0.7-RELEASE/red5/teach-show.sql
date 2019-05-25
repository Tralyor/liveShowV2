/*
Navicat MySQL Data Transfer

Source Server         : connector1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : teach-show

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-05-25 18:28:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_comment
-- ----------------------------
DROP TABLE IF EXISTS `class_comment`;
CREATE TABLE `class_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `class_id` int(11) unsigned DEFAULT NULL,
  `comment_id` varchar(30) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `level` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `comment_id` (`comment_id`),
  CONSTRAINT `class_comment_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `tclass` (`id`),
  CONSTRAINT `class_comment_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `tuser` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_comment
-- ----------------------------
INSERT INTO `class_comment` VALUES ('4', '1', '123', '课程上全是干货，就是和学生互动较少。', null);
INSERT INTO `class_comment` VALUES ('5', '1', '1150299234', '缓存的知识点讲的过于透彻了，希望有机会能够让学生多思考', null);
INSERT INTO `class_comment` VALUES ('6', '6', '1150299234', '软件工程的老师也上的很棒就是语速有点快', null);

-- ----------------------------
-- Table structure for danmaku
-- ----------------------------
DROP TABLE IF EXISTS `danmaku`;
CREATE TABLE `danmaku` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `class_id` int(11) unsigned DEFAULT NULL,
  `gmt_creat` varchar(255) DEFAULT NULL,
  `user_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `danmaku_ibfk_2` (`class_id`),
  CONSTRAINT `danmaku_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tuser` (`user_id`),
  CONSTRAINT `danmaku_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `teah_recor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of danmaku
-- ----------------------------
INSERT INTO `danmaku` VALUES ('27', '缓存的数据结构有哪几种？', '33', '1558435680887', '123');
INSERT INTO `danmaku` VALUES ('28', '123', '34', '1558522182451', '123');
INSERT INTO `danmaku` VALUES ('29', '不错的课程', '38', '1558525000576', '123');
INSERT INTO `danmaku` VALUES ('30', 'redis的问题', '39', '1558530221225', '123');
INSERT INTO `danmaku` VALUES ('31', '毕业设计答辩', '40', '1558533960352', '123');
INSERT INTO `danmaku` VALUES ('32', 'redis的优点好像还不是很清楚还能复述一下吗？', '33', '1558435680990', '1150299234');
INSERT INTO `danmaku` VALUES ('33', 'redis的数据结构有几种', '41', '1558535254877', '123');
INSERT INTO `danmaku` VALUES ('34', '缓存的数据结构有哪些', '42', '1558566100217', '123');
INSERT INTO `danmaku` VALUES ('35', '缓存的数据结构有哪些', '47', '1558574004775', '123');
INSERT INTO `danmaku` VALUES ('36', '1', '50', '1558779078823', '1234');

-- ----------------------------
-- Table structure for learn_record
-- ----------------------------
DROP TABLE IF EXISTS `learn_record`;
CREATE TABLE `learn_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `record_id` int(11) unsigned NOT NULL,
  `gmt_in` varchar(30) DEFAULT NULL,
  `gmt_out` varchar(30) DEFAULT NULL,
  `face_rego_count` int(11) DEFAULT '0',
  `face_rego_success` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `record_id` (`record_id`),
  CONSTRAINT `learn_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tuser` (`user_id`),
  CONSTRAINT `learn_record_ibfk_2` FOREIGN KEY (`record_id`) REFERENCES `teah_recor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of learn_record
-- ----------------------------
INSERT INTO `learn_record` VALUES ('130', '123', '27', '1558406114607', '1558406230833', '44', '32');
INSERT INTO `learn_record` VALUES ('131', '123', '28', '1558406380276', '1558406416600', '24', '23');
INSERT INTO `learn_record` VALUES ('132', '123', '29', '1558406795650', '1558406908699', '55', '30');
INSERT INTO `learn_record` VALUES ('133', '123', '30', '1558407242910', '1558407287412', '28', '19');
INSERT INTO `learn_record` VALUES ('135', '123', '33', '1558435412390', '1558435419719', '2', '0');
INSERT INTO `learn_record` VALUES ('136', '123', '33', '1558435419732', '1558435692937', '184', '40');
INSERT INTO `learn_record` VALUES ('137', '123', '34', '1558522165604', '1558522173842', '0', '0');
INSERT INTO `learn_record` VALUES ('138', '123', '34', '1558522173868', '1558522238778', '0', '0');
INSERT INTO `learn_record` VALUES ('139', '123', '35', '1558522705401', '1558523574161', '10', '10');
INSERT INTO `learn_record` VALUES ('140', '123', '35', '1558522867216', '1558523574161', '0', '0');
INSERT INTO `learn_record` VALUES ('141', '123', '35', '1558523554510', '1558523574161', '0', '0');
INSERT INTO `learn_record` VALUES ('142', '123', '37', '1558523742686', '1558523904222', '14', '14');
INSERT INTO `learn_record` VALUES ('144', '123', '38', '1558524809533', '1558525119746', '94', '72');
INSERT INTO `learn_record` VALUES ('145', '123', '39', '1558530132622', '1558530259952', '81', '66');
INSERT INTO `learn_record` VALUES ('146', '123', '40', '1558533672705', '1558533679438', '0', '0');
INSERT INTO `learn_record` VALUES ('147', '123', '40', '1558533679451', '1558533834628', '0', '0');
INSERT INTO `learn_record` VALUES ('148', '123', '40', '1558533834641', '1558534007974', '117', '104');
INSERT INTO `learn_record` VALUES ('149', '123', '41', '1558535181489', '1558535315023', '89', '85');
INSERT INTO `learn_record` VALUES ('150', '123', '42', '1558566010865', '1558566016317', '1', '0');
INSERT INTO `learn_record` VALUES ('151', '123', '42', '1558566016334', '1558566143177', '88', '88');
INSERT INTO `learn_record` VALUES ('152', '123', '43', '1558570221297', '1558570227895', '2', '2');
INSERT INTO `learn_record` VALUES ('153', '123', '43', '1558570227913', '1558570297232', '46', '45');
INSERT INTO `learn_record` VALUES ('154', '123', '44', '1558571343194', '1558571368724', '0', '0');
INSERT INTO `learn_record` VALUES ('155', '123', '45', '1558572032488', '1558572092369', '29', '17');
INSERT INTO `learn_record` VALUES ('156', '123', '46', '1558572538366', '1558572658181', '78', '40');
INSERT INTO `learn_record` VALUES ('157', '123', '47', '1558573944824', '1558574035282', '58', '56');
INSERT INTO `learn_record` VALUES ('158', '123', '48', '1558578556188', '1558578610370', '27', '27');
INSERT INTO `learn_record` VALUES ('159', '123', '50', '1558778776993', '1558778865008', '48', '31');
INSERT INTO `learn_record` VALUES ('160', '123', '50', '1558778865030', '1558778875943', '6', '3');

-- ----------------------------
-- Table structure for tclass
-- ----------------------------
DROP TABLE IF EXISTS `tclass`;
CREATE TABLE `tclass` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `class_name` varchar(100) NOT NULL DEFAULT '',
  `class_intro` varchar(100) DEFAULT NULL,
  `creater_id` varchar(30) DEFAULT NULL,
  `teaching` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creater_id` (`creater_id`),
  CONSTRAINT `tclass_ibfk_1` FOREIGN KEY (`creater_id`) REFERENCES `tuser` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tclass
-- ----------------------------
INSERT INTO `tclass` VALUES ('1', 'redis实战课程', '很棒的缓存教程!', '1234', '1');
INSERT INTO `tclass` VALUES ('2', '16级数据结构', '老师上的非常的棒', '1234', '0');
INSERT INTO `tclass` VALUES ('3', '15级软件工程', '老师讲的也非常棒', '1234', '0');
INSERT INTO `tclass` VALUES ('4', '16级数据结构', '老师上的非常的棒！', '1234', '0');
INSERT INTO `tclass` VALUES ('5', '17级软件工程', '老师上的非常的棒', '1234', '0');
INSERT INTO `tclass` VALUES ('6', '18级软件工程', '老师讲的真不错', '1234', '0');
INSERT INTO `tclass` VALUES ('7', '19级软件工程', '介绍软件开发的方法', '1234', '0');
INSERT INTO `tclass` VALUES ('8', '20级软件工程', '介绍软件开发的方法', '1234', '0');
INSERT INTO `tclass` VALUES ('9', '21级软件工程', '软件开发的方法介绍', '1234', '0');
INSERT INTO `tclass` VALUES ('10', '22级软件工程', '介绍软件工程的开发方法', '1234', '0');

-- ----------------------------
-- Table structure for teah_recor
-- ----------------------------
DROP TABLE IF EXISTS `teah_recor`;
CREATE TABLE `teah_recor` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `class_id` int(11) unsigned DEFAULT NULL,
  `gmt_start` varchar(30) DEFAULT NULL,
  `gmt_end` varchar(30) DEFAULT NULL,
  `class_num` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `teah_recor_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `tclass` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teah_recor
-- ----------------------------
INSERT INTO `teah_recor` VALUES ('27', '1', '1558406114607', '1558406230836', '1');
INSERT INTO `teah_recor` VALUES ('28', '1', '1558406374466', '1558406416612', '2');
INSERT INTO `teah_recor` VALUES ('29', '1', '1558406795632', '1558406908702', '3');
INSERT INTO `teah_recor` VALUES ('30', '1', '1558407228727', '1558407320527', '4');
INSERT INTO `teah_recor` VALUES ('31', '1', '1558407793783', '1558407839530', '5');
INSERT INTO `teah_recor` VALUES ('33', '1', '1558435377663', '1558435692941', '6');
INSERT INTO `teah_recor` VALUES ('34', '1', '1558522110923', '1558522238789', '7');
INSERT INTO `teah_recor` VALUES ('35', '1', '1558522700182', '1558523574166', '8');
INSERT INTO `teah_recor` VALUES ('36', '1', '1558523576275', '1558523608642', '9');
INSERT INTO `teah_recor` VALUES ('37', '1', '1558523690375', '1558523924041', '10');
INSERT INTO `teah_recor` VALUES ('38', '1', '1558524776253', '1558525126090', '11');
INSERT INTO `teah_recor` VALUES ('39', '1', '1558530057457', '1558530259960', '12');
INSERT INTO `teah_recor` VALUES ('40', '1', '1558533646279', '1558534007977', '13');
INSERT INTO `teah_recor` VALUES ('41', '1', '1558535168202', '1558535315025', '14');
INSERT INTO `teah_recor` VALUES ('42', '1', '1558565999135', '1558566143180', '15');
INSERT INTO `teah_recor` VALUES ('43', '1', '1558570203658', '1558570297236', '16');
INSERT INTO `teah_recor` VALUES ('44', '1', '1558571338809', '1558571368739', '17');
INSERT INTO `teah_recor` VALUES ('45', '1', '1558572016975', '1558572092379', '18');
INSERT INTO `teah_recor` VALUES ('46', '1', '1558572529328', '1558572658191', '19');
INSERT INTO `teah_recor` VALUES ('47', '1', '1558573928801', '1558574035292', '20');
INSERT INTO `teah_recor` VALUES ('48', '7', '1558578556188', '1558578610370', '1');
INSERT INTO `teah_recor` VALUES ('49', '7', '1558578688267', '1558578765500', '2');
INSERT INTO `teah_recor` VALUES ('50', '1', '1558778738233', null, '21');

-- ----------------------------
-- Table structure for tuser
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `user_id` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `type` int(1) DEFAULT NULL,
  `password` varchar(30) NOT NULL DEFAULT '88888888',
  `img_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser
-- ----------------------------
INSERT INTO `tuser` VALUES ('1150299234', '胡昊', '0', '123', '/static/img/closure/2019/05/22/16\\4d2bb490-4739-40b1-a2a2-8ea8b04cb93e.jpg');
INSERT INTO `tuser` VALUES ('123', '孙奇', '0', '123', '/static/img/closure/2019/05/23/09\\19e5a898-48b1-45b8-b1ea-72eb0ff59a40.jpg');
INSERT INTO `tuser` VALUES ('1234', 'cjn', '1', '123', '/static/img/closure/2019/05/22/16\\1b55040a-ccc9-42f0-9525-cd5c39fab9bb.png');

-- ----------------------------
-- Table structure for user_class_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_class_mapping`;
CREATE TABLE `user_class_mapping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `class_id` int(11) unsigned DEFAULT NULL,
  `user_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_class_mapping_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `tclass` (`id`),
  CONSTRAINT `user_class_mapping_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tuser` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_class_mapping
-- ----------------------------
INSERT INTO `user_class_mapping` VALUES ('1', '1', '123');
INSERT INTO `user_class_mapping` VALUES ('2', '1', '1150299234');
INSERT INTO `user_class_mapping` VALUES ('8', '6', '1150299234');
INSERT INTO `user_class_mapping` VALUES ('9', '6', '123');
INSERT INTO `user_class_mapping` VALUES ('10', '7', '1150299234');
INSERT INTO `user_class_mapping` VALUES ('11', '7', '123');
INSERT INTO `user_class_mapping` VALUES ('12', '8', '1150299234');
INSERT INTO `user_class_mapping` VALUES ('13', '8', '123');
INSERT INTO `user_class_mapping` VALUES ('14', '9', '1150299234');
INSERT INTO `user_class_mapping` VALUES ('15', '9', '123');
INSERT INTO `user_class_mapping` VALUES ('16', '10', '1150299234');
INSERT INTO `user_class_mapping` VALUES ('17', '10', '123');
