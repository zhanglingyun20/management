/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : vr

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-09-06 01:34:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device_game
-- ----------------------------
DROP TABLE IF EXISTS `device_game`;
CREATE TABLE `device_game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) DEFAULT '0',
  `device_code` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `game_id` int(11) DEFAULT '0' COMMENT '游戏id',
  `game_code` varchar(50) DEFAULT NULL COMMENT '游戏编码',
  `state` varchar(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_game
-- ----------------------------
INSERT INTO `device_game` VALUES ('1', '2', 'D_1471192124702', '1', 'qq', 'normal', '2016-09-05 21:54:29');
INSERT INTO `device_game` VALUES ('2', '2', 'D_1471192124702', '7', 'G_1471782528051', 'normal', '2016-09-05 21:56:13');
