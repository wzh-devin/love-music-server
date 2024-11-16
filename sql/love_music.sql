CREATE TABLE `admin`
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '用户id',
    username VARCHAR(255) NOT NULL COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码'
) ENGINE = innodb DEFAULT CHARSET = utf8 COMMENT = '管理员用户表'
CREATE TABLE `singer`
(
    `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '歌手id',
    `name`           BIGINT       NOT NULL COMMENT '歌手名',
    `sex`            TINYINT      NOT NULL COMMENT '歌手性别',
    `singer_pic_url` VARCHAR(255) NOT NULL  COMMENT '歌手照片路径',
    `birthday`       DATETIME NOT NULL COMMENT '歌手的出生日期',
    `description`    VARCHAR(255) NULL COMMENT '描述',
    `birthplace`     VARCHAR(255) NULL COMMENT '出生地',
    `nationality`    VARCHAR(255) NULL COMMENT '国籍',
    `create_time`    DATETIME     NOT NULL COMMENT '创建时间',
    `modify_time`    DATETIME     NOT NULL COMMENT '修改时间'
);
CREATE TABLE `album`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '专辑id',
    `singer_id`    BIGINT       NOT NULL COMMENT '歌手id',
    `name`         VARCHAR(255) NOT NULL COMMENT '专辑名',
    `album_pic_url` VARCHAR(255) NULL COMMENT '专辑图片路径',
    `release_time` DATETIME     NOT NULL COMMENT '发布时间',
    `description`  VARCHAR(255) NULL COMMENT '描述',
    `create_time`  DATETIME     NOT NULL COMMENT '创建时间',
    `modify_time`  DATETIME     NOT NULL COMMENT '修改时间'
);
CREATE TABLE `music`
(
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '歌曲id',
    `singer_id`     BIGINT       NOT NULL COMMENT '歌手id',
    `album_id`      BIGINT NULL COMMENT '专辑id',
    `name`          VARCHAR(255) NOT NULL COMMENT '歌曲名',
    `music_url`     VARCHAR(255) NULL COMMENT '音乐地址',
    `music_pic_url` VARCHAR(255) NULL COMMENT '歌曲图片路径',
    `description`   VARCHAR(255) NULL COMMENT '描述',
    `create_time`   DATETIME     NOT NULL COMMENT '创建时间',
    `modify_time`   DATETIME     NOT NULL COMMENT '修改时间'
);