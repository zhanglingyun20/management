/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : vr

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-09-07 01:41:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for site_bill
-- ----------------------------
DROP TABLE IF EXISTS `site_bill`;
CREATE TABLE `site_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `amount` double(11,0) DEFAULT '0' COMMENT '记账金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `bill_date` datetime DEFAULT NULL COMMENT '记账时间',
  `create_time` datetime DEFAULT NULL COMMENT '记账时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='场地记账表';

-- ----------------------------
-- Records of site_bill
-- ----------------------------
INSERT INTO `site_bill` VALUES ('6', '193', '333', '333威威威威呃呃呃', '2016-09-07 01:13:00', '2016-09-07 01:13:13');
INSERT INTO `site_bill` VALUES ('7', '193', '22', '分哇哇哇', '2016-09-07 01:37:00', '2016-09-07 01:37:08');
INSERT INTO `site_bill` VALUES ('8', '193', '222', '2222', '2016-09-08 01:39:00', '2016-09-07 01:39:06');
INSERT INTO `site_bill` VALUES ('9', '193', '444', '她她她', '2016-09-07 01:39:00', '2016-09-07 01:39:24');
INSERT INTO `site_bill` VALUES ('10', '193', '222', '222', '2016-09-07 01:39:00', '2016-09-07 01:39:49');
INSERT INTO `site_bill` VALUES ('11', '193', '222', '222任溶溶', '2016-09-07 01:39:00', '2016-09-07 01:40:00');
