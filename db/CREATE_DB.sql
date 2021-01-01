CREATE DATABASE IF NOT EXISTS `Finance` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

use `Finance`;

CREATE TABLE IF NOT EXISTS `family_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '家庭组名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `family_account_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='家庭表';

CREATE TABLE IF NOT EXISTS `account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `family_id` int DEFAULT NULL COMMENT '家庭组Id',
  `login_name` varchar(10) NOT NULL COMMENT '登录邮箱',
  `name` varchar(10) NOT NULL COMMENT '昵称',
  `pass_word` varchar(10) NOT NULL COMMENT '登录密码',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否后台管理员',
  `is_super_account` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为家庭管理员 默认为FALSE 首次创建家庭的人为TRUE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`),
  KEY `account_family_account_id_fk` (`family_id`),
  CONSTRAINT `account_family_account_id_fk` FOREIGN KEY (`family_id`) REFERENCES `family_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `bond_account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '证券账户Id',
  `name` varchar(10) NOT NULL COMMENT '证券账户名',
  `account_id` int NOT NULL COMMENT '所属用户Id',
  PRIMARY KEY (`id`),
  KEY `bond_account_account_id_fk` (`account_id`),
  CONSTRAINT `bond_account_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='证券账户表';

CREATE TABLE IF NOT EXISTS `bond_list` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '持股明细Id',
  `bond_account_id` int NOT NULL COMMENT '来源账户',
  `name` varchar(10) NOT NULL COMMENT '股票名称',
  PRIMARY KEY (`id`),
  KEY `bond_list_bond_account_id_fk` (`bond_account_id`),
  CONSTRAINT `bond_list_bond_account_id_fk` FOREIGN KEY (`bond_account_id`) REFERENCES `bond_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='持股明细';

CREATE TABLE IF NOT EXISTS `bond_io_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bond_list_id` int NOT NULL,
  `is_buy_in` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `bond_io_list_bond_list_id_fk` (`bond_list_id`),
  CONSTRAINT `bond_io_list_bond_list_id_fk` FOREIGN KEY (`bond_list_id`) REFERENCES `bond_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='证券流水表';

CREATE TABLE IF NOT EXISTS `io_list` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '收入支出ID',
  `family_id` int DEFAULT NULL COMMENT '家庭Id',
  `is_output` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否为支出',
  `money` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `type` int NOT NULL DEFAULT '1' COMMENT '支出（收入）0:税收（工资）1:衣食住行（股票）2:医疗（分红）3:其他（奖金）',
  `date` date NOT NULL COMMENT '日期   直接调用数据库DATE',
  `account_id` int NOT NULL,
  `source` varchar(10) DEFAULT NULL COMMENT '来源',
  `comment` varchar(255) DEFAULT NULL COMMENT '相关备注',
  PRIMARY KEY (`id`),
  KEY `io_list_account_id_fk` (`account_id`),
  KEY `io_list_family_account_id_fk` (`family_id`),
  CONSTRAINT `io_list_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `io_list_family_account_id_fk` FOREIGN KEY (`family_id`) REFERENCES `family_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收入支出表';


DROP PROCEDURE IF EXISTS `insert_family`;
DELIMITER $$
USE `Finance`$$
CREATE PROCEDURE `insert_family` (OUT `f_id` INTEGER,IN `f_name` VARCHAR(10))
BEGIN
INSERT INTO `family_account` (`name`) VALUES (`f_name`);
SET f_id=LAST_INSERT_ID();
END$$
DELIMITER ;