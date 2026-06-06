SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `name` VARCHAR(50) NULL,
    `password` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NULL,
    `role` VARCHAR(50) NULL,
    `register_time` DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `username` (`username`) USING BTREE

) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES
(1,'admin','张三','123456','admin@qq.com','USER','2025-06-01 11:50:00'),
(2,'test','李四','123456','test@qq.com','USER','2025-06-01 11:50:00'),
(3,'music01','王五','123456','music01@qq.com','USER','2025-06-01 11:50:00'),
(4,'music02','赵六','123456','music02@qq.com','USER','2025-06-01 11:50:00'),
(5,'music03','孙七','123456','music03@qq.com','USER','2025-06-01 11:50:00');

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`
(
    `singer_id` INT NOT NULL AUTO_INCREMENT,
    `singer_name` VARCHAR(50) NOT NULL,
    `country` VARCHAR(50) NULL,
    `song_count` INT DEFAULT 0,

    PRIMARY KEY (`singer_id`) USING BTREE,
    INDEX `singer_name`(`singer_name`) USING BTREE

) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer`
VALUES
(1,'周杰伦','中国',0),
(2,'邓紫棋','中国',0),
(3,'林俊杰','新加坡',0),
(4,'陈奕迅','中国香港',0),
(5,'薛之谦','中国',0);

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;

CREATE TABLE `song`
(
    `song_id` INT NOT NULL AUTO_INCREMENT,
    `song_name` VARCHAR(100) NOT NULL,
    `singer_id` INT NOT NULL,
    `duration` FLOAT NOT NULL,
    `play_count` INT DEFAULT 0,

    PRIMARY KEY (`song_id`) USING BTREE,
    INDEX `singer_id`(`singer_id`) USING BTREE,
    INDEX `song_name`(`song_name`) USING BTREE,

    CONSTRAINT `fk_song_singer`
    FOREIGN KEY (`singer_id`)
    REFERENCES `singer` (`singer_id`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song`
VALUES
(1,'晴天',1,4.5,5000),
(2,'东风破',1,4.2,3000),
(3,'稻香',1,4.1,6000),
(4,'夜曲',1,4.3,5500),
(5,'Gloria',2,4.1,2000),
(6,'倒数',2,3.8,1800),
(7,'光年之外',2,4.0,8000),
(8,'泡沫',2,4.2,7000),
(9,'伟大的渺小',3,4.3,2500),
(10,'江南',3,4.5,9000),
(11,'修炼爱情',3,4.1,8500),
(12,'十年',4,4.0,9500),
(13,'浮夸',4,4.2,9000),
(14,'K歌之王',4,4.3,7500),
(15,'演员',5,4.1,8200),
(16,'绅士',5,4.0,7800),
(17,'丑八怪',5,4.2,8800),
(18,'来自天堂的魔鬼', 2, 4.1, 3500),
(19,'句号', 2, 4.3, 2800),
(20,'多远都要在一起', 2, 3.9, 4200),
(21,'我的秘密', 2, 4.0, 3100),
(22,'喜欢你', 2, 3.8, 5600),
(23,'A.I.N.Y.', 2, 4.2, 2900);
-- ----------------------------
-- Table structure for playlist
-- ----------------------------
DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist`
(
    `playlist_id` INT NOT NULL AUTO_INCREMENT,
    `playlist_name` VARCHAR(100) NOT NULL,
    `user_id` INT NOT NULL,
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`playlist_id`) USING BTREE,
    INDEX `user_id`(`user_id`) USING BTREE,
    CONSTRAINT `fk_playlist_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of playlist
-- ----------------------------
INSERT INTO `playlist`
VALUES
(1,'我的收藏',1,'2025-06-01 12:00:00'),
(2,'经典华语',2,'2025-06-01 12:10:00'),
(3,'周杰伦专区',3,'2025-06-01 12:20:00'),
(4,'运动歌单',4,'2025-06-01 12:30:00'),
(5,'睡前歌单',5,'2025-06-01 12:40:00');

-- ----------------------------
-- Table structure for playlist_song
-- ----------------------------
DROP TABLE IF EXISTS `playlist_song`;
CREATE TABLE `playlist_song`
(
    `playlist_id` INT NOT NULL,
    `song_id` INT NOT NULL,

    PRIMARY KEY (`playlist_id`,`song_id`) USING BTREE,
    INDEX `song_id`(`song_id`) USING BTREE,

    CONSTRAINT `fk_ps_playlist`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `playlist` (`playlist_id`),

    CONSTRAINT `fk_ps_song`
    FOREIGN KEY (`song_id`)
    REFERENCES `song` (`song_id`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of playlist_song
-- ----------------------------
INSERT INTO `playlist_song`
VALUES
(1,1),
(1,3),
(1,7),
(1,12),

(2,2),
(2,10),
(2,13),

(3,1),
(3,2),
(3,3),
(3,4),

(4,7),
(4,10),
(4,15),

(5,6),
(5,11),
(5,16);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `comment_id` INT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(500) NOT NULL,
    `user_id` INT NOT NULL,
    `song_id` INT NOT NULL,
    `comment_time` DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`comment_id`) USING BTREE,
    INDEX `user_id`(`user_id`) USING BTREE,
    INDEX `song_id`(`song_id`) USING BTREE,

    CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`),

    CONSTRAINT `fk_comment_song`
    FOREIGN KEY (`song_id`)
    REFERENCES `song` (`song_id`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment`
VALUES
(1,'非常好听',1,1,'2025-06-01 13:00:00'),
(2,'单曲循环',2,1,'2025-06-01 13:05:00'),
(3,'经典中的经典',3,12,'2025-06-01 13:10:00'),
(4,'歌词很感人',4,10,'2025-06-01 13:15:00'),
(5,'最喜欢的一首歌',5,7,'2025-06-01 13:20:00'),
(6,'旋律很好',1,15,'2025-06-01 13:25:00'),
(7,'百听不厌',2,13,'2025-06-01 13:30:00'),
(8,'太有感觉了',3,3,'2025-06-01 13:35:00'),
(9,'推荐给大家',4,16,'2025-06-01 13:40:00'),
(10,'音乐质量很高',5,8,'2025-06-01 13:45:00');

-- ----------------------------
-- View structure for v_hot_song
-- ----------------------------
DROP VIEW IF EXISTS v_hot_song;
CREATE VIEW v_hot_song AS
SELECT
    s.song_id,
    s.song_name,
    sg.singer_name,
    s.duration,
    s.play_count

FROM song s
JOIN singer sg
ON s.singer_id = sg.singer_id
WHERE s.play_count >= 5000;



SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- Trigger 1
-- song_check_insert
-- 新增歌曲前合法性检查
-- =====================================================

DROP TRIGGER IF EXISTS `song_check_insert`;
DELIMITER ;;
CREATE TRIGGER `song_check_insert`
BEFORE INSERT
ON `song`
FOR EACH ROW
BEGIN
    -- 歌曲名不能为空
    IF NEW.song_name IS NULL OR TRIM(NEW.song_name) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='歌曲名称不能为空';
    END IF;
    -- 时长必须大于0
    IF NEW.duration <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='歌曲时长必须大于0';
    END IF;
    -- 同一歌手不能存在同名歌曲
    IF EXISTS
    (
        SELECT 1
        FROM song
        WHERE singer_id = NEW.singer_id
        AND song_name = NEW.song_name
    )
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='该歌手已存在同名歌曲';
    END IF;
END;;
DELIMITER ;

-- =====================================================
-- Trigger 2
-- song_insert_count
-- 新增歌曲后自动维护歌手歌曲数
-- =====================================================
DROP TRIGGER IF EXISTS `song_insert_count`;
DELIMITER ;;
CREATE TRIGGER `song_insert_count`
AFTER INSERT
ON `song`
FOR EACH ROW
BEGIN
    UPDATE singer
    SET song_count = song_count + 1
    WHERE singer_id = NEW.singer_id;
END;;
DELIMITER ;

-- =====================================================
-- Trigger 3
-- song_delete_count
-- 删除歌曲后自动维护歌手歌曲数
-- =====================================================

DROP TRIGGER IF EXISTS `song_delete_count`;

DELIMITER ;;

CREATE TRIGGER `song_delete_count`
AFTER DELETE
ON `song`
FOR EACH ROW
BEGIN

    UPDATE singer
    SET song_count = song_count - 1
    WHERE singer_id = OLD.singer_id;

END;;

DELIMITER ;


-- =====================================================
-- Trigger 4
-- song_update_count
-- 修改歌曲所属歌手时维护歌曲数
-- =====================================================

DROP TRIGGER IF EXISTS song_update_count;
DELIMITER ;;
CREATE TRIGGER song_update_count
AFTER UPDATE
ON song
FOR EACH ROW
BEGIN

    IF OLD.singer_id <> NEW.singer_id THEN

        UPDATE singer
        SET song_count = song_count - 1
        WHERE singer_id = OLD.singer_id;

        UPDATE singer
        SET song_count = song_count + 1
        WHERE singer_id = NEW.singer_id;

    END IF;
END;;
DELIMITER ;


UPDATE singer s
SET song_count =
(
    SELECT COUNT(*)
    FROM song
    WHERE singer_id=s.singer_id
);

-- =====================================================
-- Procedure Structure
-- sp_change_song_singer
-- 存储过程控制下的更新操作
-- =====================================================

DROP PROCEDURE IF EXISTS `sp_change_song_singer`;
DELIMITER ;;
CREATE PROCEDURE `sp_change_song_singer`
(
    IN p_song_id INT,
    IN p_new_singer_id INT
)
BEGIN
    DECLARE v_old_singer_id INT;
    DECLARE v_song_name VARCHAR(100);
    -- 异常处理：任何错误都回滚事务并抛出异常
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    -- 歌手是否存在
    IF NOT EXISTS
    (
        SELECT 1
        FROM singer
        WHERE singer_id = p_new_singer_id
    )
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='目标歌手不存在';
    END IF;
    -- 获取旧歌手ID和歌曲名称
    SELECT singer_id,song_name
    INTO v_old_singer_id,v_song_name
    FROM song
    WHERE song_id = p_song_id;
    -- 不能修改为自己
    IF v_old_singer_id = p_new_singer_id
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='歌曲已属于该歌手';
    END IF;
    -- 检查目标歌手是否已有同名歌曲
    IF EXISTS
    (
        SELECT 1
        FROM song
        WHERE singer_id = p_new_singer_id
        AND song_name = v_song_name
    )
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='目标歌手已存在同名歌曲';
    END IF;

    -- 开启事务
    START TRANSACTION;
    -- 更新歌曲所属歌手
    UPDATE song
    SET singer_id = p_new_singer_id
    WHERE song_id = p_song_id;
    COMMIT;
END;;
DELIMITER ;


-- =====================================================
-- Procedure Structure
-- sp_delete_singer
-- 事务控制下的删除操作
-- =====================================================

DROP PROCEDURE IF EXISTS `sp_delete_singer`;
DELIMITER ;;
CREATE PROCEDURE `sp_delete_singer`
(
    IN p_singer_id INT
)

BEGIN
    DECLARE v_song_count INT DEFAULT 0;
    -- 异常处理
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    -- 检查歌手是否存在
    IF NOT EXISTS
    (
        SELECT 1
        FROM singer
        WHERE singer_id = p_singer_id
    )
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='歌手不存在';
    END IF;
    -- 获取歌曲数量
    SELECT song_count
    INTO v_song_count
    FROM singer
    WHERE singer_id = p_singer_id;

    -- 开启事务
    START TRANSACTION;
    -- 删除评论
    DELETE FROM comment
    WHERE song_id IN
    (
        SELECT song_id
        FROM song
        WHERE singer_id = p_singer_id
    );
    -- 删除歌单关联
    DELETE FROM playlist_song
    WHERE song_id IN
    (
        SELECT song_id
        FROM song
        WHERE singer_id = p_singer_id
    );
    -- 删除歌曲
    DELETE FROM song
    WHERE singer_id = p_singer_id;
    -- 热门歌手保护
    IF v_song_count >= 10
    THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='热门歌手禁止直接删除';
    END IF;
    -- 删除歌手
    DELETE FROM singer
    WHERE singer_id = p_singer_id;
    COMMIT;
END;;
DELIMITER ;

-- =====================================================
-- Procedure Structure
-- sp_delete_song
-- 事务控制下的删除歌曲（级联删除 playlist_song 和 comment）
-- =====================================================

DROP PROCEDURE IF EXISTS `sp_delete_song`;
DELIMITER ;;
CREATE PROCEDURE `sp_delete_song`(
    IN p_song_id INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;
    -- 删除歌单中的关联
    DELETE FROM playlist_song WHERE song_id = p_song_id;
    -- 删除评论
    DELETE FROM comment WHERE song_id = p_song_id;
    -- 删除歌曲（触发器会自动更新 singer.song_count）
    DELETE FROM song WHERE song_id = p_song_id;
    COMMIT;
END;;

DELIMITER ;