
-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(100) DEFAULT NULL,
  `create_date` varchar(100) DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `task_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '漫画任务表';

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `admin_name` varchar(10) DEFAULT NULL,
                       `admin_password` varchar(10) DEFAULT NULL,
                       `role` int(11) DEFAULT NULL COMMENT '超级管理员',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '123', '123', '0');



-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `user_tel` varchar(12) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  `session_id` varchar(1000) DEFAULT NULL,
  `last_login_ip` varchar(20) DEFAULT NULL,
  `login_ip` varchar(10) DEFAULT NULL,
  `create_user` varchar(10) DEFAULT NULL,
  `update_user` varchar(10) DEFAULT NULL,
  `role_type` integer(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `self_desc` varchar(1000) DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';
