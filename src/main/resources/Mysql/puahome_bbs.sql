/*
Navicat MySQL Data Transfer

Source Server         : spiderforpua
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-11-06 20:14:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `puahome_bbs`
-- ----------------------------
DROP TABLE IF EXISTS `puahome_bbs`;
CREATE TABLE `puahome_bbs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `writer` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `content` text CHARACTER SET utf8,
  `editdate` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `listurl` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `commentnum` int(11) DEFAULT NULL,
  `readnum` int(11) DEFAULT NULL,
  `supportnum` int(11) DEFAULT NULL,
  `collectnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12172 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of puahome_bbs
-- ----------------------------
