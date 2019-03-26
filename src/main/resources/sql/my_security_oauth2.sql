/*
 Navicat Premium Data Transfer

 Source Server         : 本地5.7.23
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : my_security_oauth2

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 26/03/2019 14:18:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5加密的access_token的值',
  `token` blob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
  `authentication_id` varchar(256) NOT NULL COMMENT 'MD5加密过的username,client_id,scope  ',
  `user_name` varchar(256) DEFAULT NULL COMMENT '登录的用户名',
  `client_id` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  `authentication` blob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据',
  `refresh_token` varchar(256) DEFAULT NULL COMMENT 'MD5加密果的refresh_token的值',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth访问令牌';

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL COMMENT '登录的用户名',
  `clientId` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  `scope` varchar(256) DEFAULT NULL COMMENT '申请的权限 ',
  `status` varchar(10) DEFAULT NULL COMMENT '状态（Approve或Deny）',
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '过期时间',
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最终修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth审批信息';

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端密匙',
  `scope` varchar(256) DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". ',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". \n',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '重定向URI',
  `authorities` varchar(256) DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效时间值(单位:秒) ',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '更新令牌有效时间值(单位:秒) ',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '预留字段',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '用户是否自动Approval操作',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth客户端信息';

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5加密的access_token值',
  `token` blob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
  `authentication_id` varchar(256) NOT NULL COMMENT 'MD5加密过的username,client_id,scope',
  `user_name` varchar(256) DEFAULT NULL COMMENT '登录的用户名',
  `client_id` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth客户端令牌';

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL COMMENT '授权码(未加密)',
  `authentication` blob COMMENT 'AuthorizationRequestHolder.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth编码';

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5加密过的refresh_token的值',
  `token` blob COMMENT 'OAuth2RefreshToken.java对象序列化后的二进制数据',
  `authentication` blob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='oauth刷新令牌';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '前端URL',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标',
  `component` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `type` int(2) DEFAULT '1' COMMENT '菜单类型（1=菜单,2=按钮）',
  `state` int(2) DEFAULT '1' COMMENT '状态(1=正常，0=删除)',
  `author` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单权限信息';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色编码',
  `described` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色描述',
  `state` int(2) DEFAULT '1' COMMENT '状态（1=正常,0=删除）',
  `author` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_idx1_role_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色信息';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `menuId` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`roleId`,`menuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色菜单信息';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `deptId` int(11) DEFAULT NULL COMMENT '部门ID',
  `state` int(2) DEFAULT '1' COMMENT '状态（1=正常，0=删除）',
  `author` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户信息';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`userId`,`roleId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色信息';

SET FOREIGN_KEY_CHECKS = 1;
