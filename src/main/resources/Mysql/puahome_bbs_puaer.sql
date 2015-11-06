/*
Navicat MySQL Data Transfer

Source Server         : spiderforpua
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-11-06 20:15:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `puahome_bbs_puaer`
-- ----------------------------
DROP TABLE IF EXISTS `puahome_bbs_puaer`;
CREATE TABLE `puahome_bbs_puaer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personalurl` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `themenum` int(11) DEFAULT NULL,
  `fansnum` int(11) DEFAULT NULL,
  `attentionnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11953 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of puahome_bbs_puaer
-- ----------------------------
