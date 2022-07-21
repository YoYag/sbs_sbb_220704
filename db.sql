# DB 생성
DROP DATABASE IF EXISTS sbb;
CREATE DATABASE sbb;
USE sbb;

# 회원 테이블 생성
CREATE TABLE `site_user` (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` CHAR(50) NOT NULL UNIQUE KEY,
    `password` VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE KEY
);

DESC site_user;

# 회원데이터 생성
INSERT INTO `site_user`
SET `username` = '유저1',
`password` = '1234',
email = 'use1@test.com';

INSERT INTO `site_user`
SET `username` = '유저2',
`password` = '1234',
email = 'use2@test.com';

INSERT INTO `site_user`
SET `username` = '유저3',
`password` = '1234',
email = 'use3@test.com';

# 게시물 테이블 생성
CREATE TABLE `article` (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reg_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL,
    `site_user_id` BIGINT UNSIGNED NOT NULL
);

# 게시물데이터 생성
INSERT INTO `article`
SET reg_date = NOW(),
update_date = NOW(),
title = '제목 1',
`body` = '내용 1',
`site_user_id` = 1;

INSERT INTO `article`
SET reg_date = NOW(),
update_date = NOW(),
title = '제목 2',
`body` = '내용 2',
`site_user_id` = 2;

INSERT INTO `article`
SET reg_date = NOW(),
update_date = NOW(),
title = '제목 3',
`body` = '내용 3',
`site_user_id` = 3;

# 질문 테이블 생성
CREATE TABLE `question` (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    create_date DATETIME NOT NULL,
    `subject` VARCHAR(200) NOT NULL,
    `content` TEXT NOT NULL
);

# 질문데이터 생성
INSERT INTO `question`
SET create_date = NOW(),
`subject` = '질문 1',
`content` = '질문내용 1';

INSERT INTO `question`
SET create_date = NOW(),
`subject` = '질문 2',
`content` = '질문내용 2';

INSERT INTO `question`
SET create_date = NOW(),
`subject` = '질문 3',
`content` = '질문내용 3';

# 답변 테이블 생성
CREATE TABLE answer (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    question_id INT(11) UNSIGNED NOT NULL
);

# 답변데이터 생성
INSERT INTO answer SET
create_date = NOW(),
content = '답변내용 1',
question_id = 1;

INSERT INTO answer SET
create_date = NOW(),
content = '답변내용 2',
question_id = 2;

INSERT INTO answer SET
create_date = NOW(),
content = '답변내용 3',
question_id = 3;

SELECT * FROM `site_user`;
SELECT * FROM `article`;
SELECT * FROM `question`;
SELECT * FROM `answer`;