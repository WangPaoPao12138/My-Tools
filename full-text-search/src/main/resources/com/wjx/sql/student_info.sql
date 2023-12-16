/*
 Navicat Premium Data Transfer

 Source Server         : LOCL
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : test_idx

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 16/12/2023 00:36:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_id` int(0) NOT NULL,
  `class_id` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

--StudentInfo 表数据初始化
CREATE DEFINER=`root`@`%` PROCEDURE `insert_stu`(max_num INT)
    BEGIN
    DECLARE i INT DEFAULT 0;
    SET autocommit = 0;
    REPEAT
        SET i = i + 1;
        INSERT INTO student_info(course_id,class_id,student_id,name)
        VALUES (rand_num(10000,10100),rand_num(10000,10200),rand_num(1,200000),rand_string(6));
    UNTIL i = max_num
    END REPEAT;
    COMMIT;
END;
--新增 1,000,000 数据
CALL insert_stu(1000000);