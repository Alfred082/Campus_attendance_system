-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2023-06-02 08:11:01
-- 服务器版本： 5.7.34-log
-- PHP 版本： 7.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `kaoqing`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_account` varchar(10) DEFAULT NULL,
  `admin_password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `attend`
--

CREATE TABLE `attend` (
  `attend_id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `attend_start` timestamp NULL DEFAULT NULL,
  `attend_end` timestamp NULL DEFAULT NULL,
  `attend_longitude` double DEFAULT NULL,
  `attend_latitude` double DEFAULT NULL,
  `attend_location` varchar(50) DEFAULT NULL,
  `attend_type` int(1) DEFAULT NULL,
  `attend_gesture` varchar(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `course_name` varchar(50) DEFAULT NULL,
  `course_avatar` varchar(256) DEFAULT NULL,
  `course_introduce` varchar(256) DEFAULT NULL,
  `course_code` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`course_id`, `teacher_id`, `course_name`, `course_avatar`, `course_introduce`, `course_code`) VALUES
(13, 1, '云计算', 'avatars/course-default.png', '云计算云计算云计算云计算云计算云计算', '995598'),
(14, 19, '数学', '/image/avatars/c14.png', '数学课程', '395481'),
(15, 19, '语文', '/image/avatars/c15.png', '语法', '908743');

-- --------------------------------------------------------

--
-- 表的结构 `course_student`
--

CREATE TABLE `course_student` (
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `course_student`
--

INSERT INTO `course_student` (`student_id`, `course_id`, `join_time`) VALUES
(12, 14, '2023-06-01 22:43:27');

-- --------------------------------------------------------

--
-- 表的结构 `leave_attend`
--

CREATE TABLE `leave_attend` (
  `leave_id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `leave_time` timestamp NULL DEFAULT NULL,
  `back_time` timestamp NULL DEFAULT NULL,
  `leave_reason` varchar(256) DEFAULT NULL,
  `approval_time` timestamp NULL DEFAULT NULL,
  `approval_result` int(11) DEFAULT NULL,
  `approval_remark` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `leave_attend`
--

INSERT INTO `leave_attend` (`leave_id`, `student_id`, `course_id`, `leave_time`, `back_time`, `leave_reason`, `approval_time`, `approval_result`, `approval_remark`) VALUES
(13, 12, 14, '2023-06-01 16:00:00', '2023-06-04 16:00:00', '回家', NULL, 0, NULL),
(14, 12, 14, '2023-06-01 16:00:00', '2023-06-11 16:00:00', '回家', NULL, 0, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `record`
--

CREATE TABLE `record` (
  `attend_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `record_time` timestamp NULL DEFAULT NULL,
  `record_location` varchar(50) DEFAULT NULL,
  `record_photo` varchar(256) DEFAULT NULL,
  `record_result` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `student_account` varchar(10) DEFAULT NULL,
  `student_password` varchar(10) DEFAULT NULL,
  `student_name` varchar(20) DEFAULT NULL,
  `student_sex` tinyint(1) DEFAULT NULL,
  `student_avatar` varchar(256) DEFAULT NULL,
  `student_class` varchar(50) DEFAULT NULL,
  `student_face` varchar(256) DEFAULT NULL,
  `student_phone` varchar(11) DEFAULT NULL,
  `student_email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`student_id`, `student_account`, `student_password`, `student_name`, `student_sex`, `student_avatar`, `student_class`, `student_face`, `student_phone`, `student_email`) VALUES
(12, '000001', '000000', '江道宽', 1, '/image/avatars/s12.png', '0814171', '/image/face/12.png', '13137749525', '2116161338@qq.com');

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `teacher_account` varchar(10) DEFAULT NULL,
  `teacher_password` varchar(10) DEFAULT NULL,
  `teacher_name` varchar(20) DEFAULT NULL,
  `teacher_sex` tinyint(1) DEFAULT NULL,
  `teacher_phone` varchar(11) DEFAULT NULL,
  `teacher_email` varchar(20) DEFAULT NULL,
  `teacher_avatar` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `admin_id`, `teacher_account`, `teacher_password`, `teacher_name`, `teacher_sex`, `teacher_phone`, `teacher_email`, `teacher_avatar`) VALUES
(19, 1, '000001', '000000', '张老师', 0, '13137749525', '2116161338@qq.com', 'avatars/user-default.png');

--
-- 转储表的索引
--

--
-- 表的索引 `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- 表的索引 `attend`
--
ALTER TABLE `attend`
  ADD PRIMARY KEY (`attend_id`);

--
-- 表的索引 `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`);

--
-- 表的索引 `course_student`
--
ALTER TABLE `course_student`
  ADD PRIMARY KEY (`student_id`,`course_id`);

--
-- 表的索引 `leave_attend`
--
ALTER TABLE `leave_attend`
  ADD PRIMARY KEY (`leave_id`);

--
-- 表的索引 `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`attend_id`,`student_id`);

--
-- 表的索引 `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`);

--
-- 表的索引 `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `attend`
--
ALTER TABLE `attend`
  MODIFY `attend_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- 使用表AUTO_INCREMENT `course`
--
ALTER TABLE `course`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 使用表AUTO_INCREMENT `leave_attend`
--
ALTER TABLE `leave_attend`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- 使用表AUTO_INCREMENT `student`
--
ALTER TABLE `student`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- 使用表AUTO_INCREMENT `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
