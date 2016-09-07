/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : vr

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-09-06 01:34:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_game_id` int(11) DEFAULT NULL COMMENT '设备游戏id',
  `amount` double(11,0) DEFAULT '0' COMMENT '记账金额',
  `user_id` int(11) DEFAULT '0' COMMENT '操作人id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '操作用户名',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `bill_date` datetime DEFAULT NULL COMMENT '记账时间',
  `create_time` datetime DEFAULT NULL COMMENT '记账时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账单表';

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '2', '3333', '187', 'admin', '3333333333', '2016-09-07 01:30:00', '2016-09-06 01:30:53');
