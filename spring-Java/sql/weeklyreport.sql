-- 删库建库：假设库名是Weekly_Report
DROP DATABASE IF EXISTS Weekly_Report;
CREATE DATABASE Weekly_Report;
USE Weekly_Report;

-- 删除表（如果存在）
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS UserInformation;
DROP TABLE IF EXISTS IdleTime;
DROP TABLE IF EXISTS DailyPaper;
DROP TABLE IF EXISTS ClockInRecord;
DROP TABLE IF EXISTS UserCourse;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS WeeklyPaper;

-- 创建表
-- -1. 用户表
CREATE TABLE User (
    Id int NOT NULL,
    username varchar(255) UNIQUE,
    password varchar(255),
    PRIMARY KEY (`Id`) USING BTREE
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -2. 用户信息表
CREATE TABLE UserInformation (
    userId int NOT NULL,
    nickName varchar(255) NOT NULL,
    headSculpture longtext,
    college text,
    major text,
    grade year DEFAULT NULL,
    PRIMARY KEY (`userId`) USING BTREE,
    FOREIGN KEY (userId) REFERENCES User (Id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -3. 空闲时刻表
CREATE TABLE IdleTime (
    Id int NOT NULL,
    userId int NOT NULL,
    date date NOT NULL,
    time time NOT NULL,
    idel tinyint(1) NOT NULL,
    PRIMARY KEY (`Id`) USING BTREE,
    FOREIGN KEY (userId) REFERENCES User (Id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -4. 打卡记录表
CREATE TABLE ClockInRecord (
    Id int NOT NULL,
    userId int NOT NULL,
    date date NOT NULL,
    time time NOT NULL,
    workHour int NOT NULL DEFAULT 0,
    PRIMARY KEY (`Id`) USING BTREE,
    FOREIGN KEY (userId) REFERENCES User (Id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -5. 课程表
CREATE TABLE Course (
    Id int NOT NULL,
    title varchar(255),
    description text,
    dateTime datetime,
    technicalDirection enum('前端', '后端', '测试', '软技能'),
    level enum('初级', '高级'),
    mode enum('线上', '线下'),
    PRIMARY KEY (`Id`) USING BTREE
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -6. 参与课程表
CREATE TABLE UserCourse (
    Id int NOT NULL,
    userId int NOT NULL,
    courseId int NOT NULL,
    PRIMARY KEY (`Id`) USING BTREE,
    FOREIGN KEY (userId) REFERENCES User (Id),
    FOREIGN KEY (courseId) REFERENCES Course (Id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -7. 日报表
CREATE TABLE DailyPaper (
    Id int NOT NULL,
    userId int NOT NULL,
    date date NOT NULL,
    completeWork longtext,
    risk longtext,
    delay tinyint(1) NOT NULL,
    PRIMARY KEY (`Id`) USING BTREE,
    FOREIGN KEY (userId) REFERENCES User (Id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- -8. 周报表
CREATE TABLE WeeklyPaper (
    Id int NOT NULL,
    userId int NOT NULL,
    date date NOT NULL,
    completeWork longtext,
    nextPlan longtext,
    delay tinyint(1) NOT NULL,
    PRIMARY KEY (`Id`) USING BTREE,
    FOREIGN KEY (userId) REFERENCES User (Id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 插入数据
insert into user (Id, username, password) values (1,'admin','123');