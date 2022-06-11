-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafit_db_v3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafit_db_v3
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ssafit_db_v3` ;
CREATE SCHEMA `ssafit_db_v3` DEFAULT CHARACTER SET utf8 ;
USE `ssafit_db_v3` ;

-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`channel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`channel`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`channel` (
  `channel_id` VARCHAR(45) NOT NULL,
  `channel_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`channel_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`video`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`video`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`video` (
  `video_id` VARCHAR(45) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `channel_id` VARCHAR(45) NOT NULL,
  `view_cnt` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`video_id`),
  INDEX `fk_video_channel1_idx` (`channel_id` ASC) VISIBLE,
  CONSTRAINT `fk_video_channel1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `ssafit_db_v3`.`channel` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`tag`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`tag` (
  `tag_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`tag_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`tag_has_video`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`tag_has_video`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`tag_has_video` (
  `tag_name` VARCHAR(100) NOT NULL,
  `video_id` VARCHAR(45) NOT NULL,
  INDEX `fk_tag_has_video_tag_idx` (`tag_name` ASC) VISIBLE,
  INDEX `fk_tag_has_video_video1_idx` (`video_id` ASC) VISIBLE,
  PRIMARY KEY (`video_id`, `tag_name`),
  CONSTRAINT `fk_tag_has_video_tag`
    FOREIGN KEY (`tag_name`)
    REFERENCES `ssafit_db_v3`.`tag` (`tag_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tag_has_video_video1`
    FOREIGN KEY (`video_id`)
    REFERENCES `ssafit_db_v3`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`area`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`area` (
  `area_name` VARCHAR(45) NOT NULL,
  `class_num` INT NOT NULL,
  PRIMARY KEY (`area_name`, `class_num`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`user`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`user` (
  `user_id` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `grade` INT NOT NULL DEFAULT 1,
  `area_name` VARCHAR(45) NOT NULL,
  `class_num` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_user_area1_idx` (`area_name` ASC, `class_num` ASC) VISIBLE,
  CONSTRAINT `fk_user_area1`
    FOREIGN KEY (`area_name` , `class_num`)
    REFERENCES `ssafit_db_v3`.`area` (`area_name` , `class_num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`mark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`mark`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`mark` (
  `user_id` VARCHAR(45) NOT NULL,
  `video_id` VARCHAR(45) NOT NULL,
  INDEX `fk_mark_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_mark_video1_idx` (`video_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`, `video_id`),
  CONSTRAINT `fk_mark_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit_db_v3`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mark_video1`
    FOREIGN KEY (`video_id`)
    REFERENCES `ssafit_db_v3`.`video` (`video_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`follow`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`follow` (
  `channel_id` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  INDEX `fk_follow_channel1_idx` (`channel_id` ASC) VISIBLE,
  INDEX `fk_follow_user1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`channel_id`, `user_id`),
  CONSTRAINT `fk_follow_channel1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `ssafit_db_v3`.`channel` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit_db_v3`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`review`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`review` (
  `review_no` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(1000) NOT NULL,
  `date` DATETIME NOT NULL DEFAULT now(),
  `video_id` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`review_no`),
  INDEX `fk_review_video1_idx` (`video_id` ASC) VISIBLE,
  INDEX `fk_review_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_video1`
    FOREIGN KEY (`video_id`)
    REFERENCES `ssafit_db_v3`.`video` (`video_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit_db_v3`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafit_db_v3`.`score`;
CREATE TABLE IF NOT EXISTS `ssafit_db_v3`.`score` (
  `user_id` VARCHAR(45) NOT NULL,
  `score_upper` INT NOT NULL DEFAULT 0,
  `score_lower` INT NOT NULL DEFAULT 0,
  `score_belly` INT NOT NULL DEFAULT 0, 
  `score_body` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_score_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit_db_v3`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ==========================================================================================================
-- -----------------------------------------------------
-- Table `youtube_db`.`tag`
-- -----------------------------------------------------
INSERT 
	INTO `ssafit_db_v3`.`tag`
	VALUES
		('상체'),
        ('하체'),
        ('복부'),
        ('전신');
        
SELECT * FROM `ssafit_db_v3`.`tag`;


-- -----------------------------------------------------
-- Table `youtube_db`.`channel`
-- -----------------------------------------------------
INSERT 
	INTO `ssafit_db_v3`.`channel`
		(`channel_id`, `channel_name`)
	VALUES
		('UCpg89Ys3E4BaLGgEEWVmI9g', 'Thankyou BUBU'),
        ('UCjGoJbTmFYjd5OnPRUur02A', '김강민_Kim Kang min'),
        ('UCoe-0EVDJnjlSoPK8ygcGwQ', '김종국 GYM JONG KOOK'),
        ('UC2WZ-lHHSOI81g2Jp4BpsWA', '소미핏 SomiFit'),
        ('UCdtRAcd3L_UpV4tMXCw63NQ', '피지컬갤러리'),
        ('UC4yq3FWEWqMvFNFBsV3gbKQ', '힙으뜸'),
        ('UC9trbyGOOjJmMea3w6c-e2A', '비타민신지니 VitaminJINY'),
        ('UCxWRe2A7LMTEwMBErR4DkgQ', '삐약스핏[살빼주는 병맛 다이어트 채널]'),
        ('UCyOIOpN15dSBu1wMM5I2ayw', '근력발전소'),
        ('UCKEspD9kts44sG5E80kQcjQ', '이지은 다이어트 Jiny diet'),
        ('UCF97u_Y5C3FxAcD0nn1DCKQ', '보통사람을 위한 운동채널'),
        ('UCoHirUZkTs1nOtyJTNbNJ5g', '지피티'),
        ('UCkEQJUPvOVX0xNGLFYcTxfg', 'Hbro 길환TV'),
        ('UCxM_KJ601hwrOpjVC07iMVQ', 'DanoTV'),
        ('UCMY_lKUu8yGZaVsu_9lPIGg', '권혁 Hulk\'s TV');

SELECT * FROM `ssafit_db_v3`.`channel`;


-- -----------------------------------------------------
-- Table `youtube_db`.`video`
-- -----------------------------------------------------
INSERT 
	INTO `ssafit_db_v3`.`video`
		(`video_id`, `title`, `channel_id`, `view_cnt`)
	VALUES
		('gMaB-fG4u4g', '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', 'UCpg89Ys3E4BaLGgEEWVmI9g', 0),
		('swRNeYw1JkY', '하루 15분! 전신 칼로리 불태우는 다이어트 운동', 'UCpg89Ys3E4BaLGgEEWVmI9g', 0),
		('54tTYO-vU2E', '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]', 'UCpg89Ys3E4BaLGgEEWVmI9g', 0),
		('QqqZH3j_vH0', '상체비만 다이어트 최고의 운동 [상체 핵매운맛]', 'UCpg89Ys3E4BaLGgEEWVmI9g', 0),
		('PjGcOP-TQPE', '11자복근 복부 최고의 운동 [복근 핵매운맛]', 'UCpg89Ys3E4BaLGgEEWVmI9g', 0),
		('tzN6ypk6Sps', '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]', 'UCjGoJbTmFYjd5OnPRUur02A', 0),
		("u5OgcZdNbMo", '저는 하체 식주의자 입니다', 'UCoe-0EVDJnjlSoPK8ygcGwQ', 0),
		("7TLk7pscICk", '(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)', 'UC2WZ-lHHSOI81g2Jp4BpsWA', 0),
		("sVQqBDBZhmI", '(Sub)복부운동 짧고 굵게! 운동효율 갑! [6 MINS ABS WORKOUT]', 'UC2WZ-lHHSOI81g2Jp4BpsWA', 0),
		("swxfuFCVRYE", '(Sub)빡샘과 함께 운동하자 [필수 코어 운동]', 'UCdtRAcd3L_UpV4tMXCw63NQ', 0),
		("kETh8T3it4k", '(Sub)(층간소음X, 설명O) 복근운동과 유산소를 한번에:exclamation:️서서하는 복근운동 1탄:fire:', 'UC4yq3FWEWqMvFNFBsV3gbKQ', 0),
		("gqR73V3fq2k", '힙으뜸 체지방 태우는 전신 운동 15분 루틴 ㅣ15MIN FULL BODY WORKOUT', 'UC4yq3FWEWqMvFNFBsV3gbKQ', 0),
		("Vx2yxXQ0Pkk", '(Sub):sports_medal:요일별운동:sports_medal: 목요일 상체집중 근력운동 15분 루틴!', 'UC4yq3FWEWqMvFNFBsV3gbKQ', 0),
		("hR1ZgDQqyVI", '(Sub):fire:볼록 튀어나온 아랫뱃살:fire:무조건 빠지는 역대급 뱃살폭파운동 (똥배/복부지방)', 'UC9trbyGOOjJmMea3w6c-e2A', 0),
		("2swcod5RYvU", '(Sub)집에서하는 상체운동!Upper body workout at home', 'UCxWRe2A7LMTEwMBErR4DkgQ', 0),
		("4EcIZexNa7A", '(Sub)일반인이 \'이것\'만 알면 상체 프레임과 등근육은 무조건 넓어집니다', 'UCyOIOpN15dSBu1wMM5I2ayw', 0),
		("jiHoMmNhVzM", '(Sub):diamonds:말랑 출렁이는 뱃살:diamonds:에서 탄력있는 일자복근 만들기', 'UCKEspD9kts44sG5E80kQcjQ', 0),
		("dpBYYEhdofI", '(Sub)앞벅지 볼록, 뒷벅지 셀룰라이트, 허벅지 안쪽살 모조리 불태우고:fire: [여리탄탄 일자 허벅지] 되는 7일 루틴', 'UCKEspD9kts44sG5E80kQcjQ', 0),
		("3TyTGxBNwic", '(Sub)\'스쿼트\'를 하지 않고 하체 근육을 키우는 가장 빠른방법!? (feat.허벅지/엉덩이)', 'UCF97u_Y5C3FxAcD0nn1DCKQ', 0),
		("GhIpFWzuliA", '(Sub)당신의 16분이 전혀 아깝지 않을 영상.. 하체가 빈약하다면 필수시청', 'UCoHirUZkTs1nOtyJTNbNJ5g', 0),
		("nz5qsvRgK24", '[전신 운동] 근육은 늘리고 살은 빼는 홈트 (초급자 추천)', 'UCkEQJUPvOVX0xNGLFYcTxfg', 0),
		("cMkZ6A7wngk", '[ENG/ 전신 올인원 운동] 유튜브에서 다이어트 전신운동 찾았어요? 이제 딱 이거 하나만 해요! l diet workoutㅣ다노티비', 'UCxM_KJ601hwrOpjVC07iMVQ', 0),
		("CYcLODSeC-c", 'Lv.4 층간소음없이 딱! 15분 체지방 100% 녹여버리는 루틴 [Noise Free 15mins Fat Burning Workout]', 'UCMY_lKUu8yGZaVsu_9lPIGg', 0),
		("KXYi6bI-UPE", '(Sub)Lv.5 13분만에 하체 마비시키는 루틴! 근육통100% 옵니다. (누구나 집에서 가능) 13mins intense Legs Workout', 'UCMY_lKUu8yGZaVsu_9lPIGg', 0);

SELECT `video`.`video_id`, `video`.`title`, `channel`.`channel_name` , `video`.`view_cnt`
	FROM `ssafit_db_v3`.`video`
        LEFT OUTER JOIN `channel` ON `video`.`channel_id` = `channel`.`channel_id`;
        
-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`area`
-- -----------------------------------------------------
INSERT
	INTO `ssafit_db_v3`.`area` 
		(`area_name`, `class_num`)
	VALUES 
		('서울', 1),
		('서울', 2),
        ('서울', 3),
        ('서울', 4),
        ('대전', 1),
        ('대전', 2),
        ('대전', 3),
        ('대전', 4),
        ('구미', 1),
        ('구미', 2),
        ('구미', 3),
        ('구미', 4),
        ('광주', 1),
        ('광주', 2),
        ('광주', 3),
        ('광주', 4),
        ('부울경', 1),
        ('부울경', 2),
        ('부울경', 3),
        ('부울경', 4);
        

SELECT `area_name`, `class_num`
	FROM `ssafit_db_v3`.`area`;

-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`user`
-- -----------------------------------------------------
INSERT
	INTO `ssafit_db_v3`.`user` 
		(`user_id`, `nickname`, `password`, `name`, `age`, `grade`, `area_name`, `class_num`)
	VALUES 
		('admin', '관리자', 'admin', '관리자', 0, 0, '서울', 1),
		('user1', 'nickname1', '1111', '성1 이름1', 21, 1, '서울', 1),
        ('user2', 'nickname2', '2222', '성2 이름2', 22, 1, '대전', 4),
        ('user3', 'nickname3', '3333', '성3 이름3', 23, 1, '구미', 3),
        ('user4', 'nickname4', '4444', '성4 이름4', 24, 1, '광주', 2),
        ('user5', 'nickname5', '5555', '성5 이름5', 25, 1, '부울경', 1);

SELECT `user_id`, `nickname`, `password`, `name`, `age`, `grade`, `area_name`, `class_num`
	FROM `ssafit_db_v3`.`user`;

-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`score`
-- -----------------------------------------------------
-- INSERT
-- 	INTO `ssafit_db_v3`.`score` 
-- 		(`user_id`, `score_upper`, `score_lower`, `score_belly`, `score_body`)
-- 	VALUES 
-- 		('admin', 0, 0, 0, 0),
--         ('user1', 0, 0, 0, 0),
--         ('user2', 0, 0, 0, 0),
--         ('user3', 0, 0, 0, 0),
--         ('user4', 0, 0, 0, 0),
--         ('user5', 0, 0, 0, 0);
        
INSERT
	INTO `ssafit_db_v3`.`score` 
		(`user_id`)
	VALUES 
		('admin'),
        ('user1'),
        ('user2'),
        ('user3'),
        ('user4'),
        ('user5');

SELECT `user_id`, `score_upper`, `score_lower`, `score_belly`, `score_body`
	FROM `ssafit_db_v3`.`score`;

-- -----------------------------------------------------
-- Table `ssafit_db_v3`.`review`
-- -----------------------------------------------------
INSERT 
	INTO `ssafit_db_v3`.`review`
		(`content`, `video_id`, `user_id`)
	VALUES
        ('리뷰 내용1', '2swcod5RYvU', 'user1'),
        ('리뷰 내용2', '3TyTGxBNwic', 'user1'),
        ('리뷰 내용3', '4EcIZexNa7A', 'user1'),
        ('리뷰 내용4', '54tTYO-vU2E', 'user1'),
        ('리뷰 내용5', '7TLk7pscICk', 'user1'),
        ('리뷰 내용6', 'cMkZ6A7wngk', 'user1'),
        ('리뷰 내용7', 'CYcLODSeC-c', 'user1'),
        ('리뷰 내용8', 'dpBYYEhdofI', 'user1'),
        ('리뷰 내용9', 'GhIpFWzuliA', 'user1'),
        ('리뷰 내용10', 'gMaB-fG4u4g', 'user1'),
        ('리뷰 내용11', 'gqR73V3fq2k', 'user2'),
        ('리뷰 내용12', 'hR1ZgDQqyVI', 'user2'),
        ('리뷰 내용13', 'jiHoMmNhVzM', 'user2'),
        ('리뷰 내용14', 'kETh8T3it4k', 'user2'),
        ('리뷰 내용15', 'KXYi6bI-UPE', 'user2'),
        ('리뷰 내용16', 'nz5qsvRgK24', 'user2'),
        ('리뷰 내용17', 'PjGcOP-TQPE', 'user2'),
        ('리뷰 내용18', 'QqqZH3j_vH0', 'user2'),
        ('리뷰 내용19', 'sVQqBDBZhmI', 'user2'),
        ('리뷰 내용20', 'swRNeYw1JkY', 'user2'),
        ('리뷰 내용21', 'swxfuFCVRYE', 'user3'),
        ('리뷰 내용22', 'tzN6ypk6Sps', 'user3'),
        ('리뷰 내용23', 'u5OgcZdNbMo', 'user3'),
        ('리뷰 내용24', 'Vx2yxXQ0Pkk', 'user3');

SELECT `review`.`review_no`, `review`.`content`, `review`.`date`, `video`.`title`, `user`.`name`
	FROM `ssafit_db_v3`.`review`
		LEFT OUTER JOIN `ssafit_db_v3`.`video` ON `review`.`video_id` = `video`.`video_id`
		LEFT OUTER JOIN `ssafit_db_v3`.`user` ON `review`.`user_id` = `user`.`user_id`;


-- -----------------------------------------------------
-- Table `youtube_db`.`follow`
-- -----------------------------------------------------
INSERT
	INTO `ssafit_db_v3`.`follow`
		(`user_id`, `channel_id`)
	VALUES
		('user1', 'UCpg89Ys3E4BaLGgEEWVmI9g'),
        ('user2', 'UCjGoJbTmFYjd5OnPRUur02A'),
        ('user3', 'UCoe-0EVDJnjlSoPK8ygcGwQ'),
        ('user4', 'UC2WZ-lHHSOI81g2Jp4BpsWA'),
        ('user5', 'UCdtRAcd3L_UpV4tMXCw63NQ');

SELECT `follow`.`user_id`, `follow`.`channel_id`
 	FROM `ssafit_db_v3`.`follow` 
 		LEFT OUTER JOIN `ssafit_db_v3`.`user` ON `follow`.`user_id` = `user`.`user_id`
 		LEFT OUTER JOIN `ssafit_db_v3`.`channel` ON `follow`.`channel_id` = `channel`.`channel_id`;


-- -----------------------------------------------------
-- Table `youtube_db_db`.`mark`
-- -----------------------------------------------------
INSERT INTO `ssafit_db_v3`.`mark`
	VALUES
		('user1', 'GhIpFWzuliA'),
        ('user2', 'nz5qsvRgK24'),
        ('user3', 'cMkZ6A7wngk'),
        ('user4', 'CYcLODSeC-c'),
        ('user5', 'KXYi6bI-UPE');

SELECT `user`.`name`, `video`.`title`
	FROM `ssafit_db_v3`.`mark` 
		LEFT OUTER JOIN `ssafit_db_v3`.`user` ON `mark`.`user_id` = `user`.`user_id`
		LEFT OUTER JOIN `ssafit_db_v3`.`video` ON `mark`.`video_id` = `video`.`video_id`;
        
-- -----------------------------------------------------
-- Table `youtube_db`.`tag_has_video`
-- -----------------------------------------------------
INSERT INTO `ssafit_db_v3`.`tag_has_video`
	VALUES
		('상체', '54tTYO-vU2E'), ('상체', 'QqqZH3j_vH0'), ('상체', 'Vx2yxXQ0Pkk'), ('상체', '2swcod5RYvU'), ('상체', '4EcIZexNa7A'),
        ('하체', 'tzN6ypk6Sps'), ('하체', 'u5OgcZdNbMo'), ('하체', 'dpBYYEhdofI'), ('하체', '3TyTGxBNwic'), ('하체', 'GhIpFWzuliA'), ('하체', 'KXYi6bI-UPE'), 
        ('복부', 'PjGcOP-TQPE'), ('복부', '7TLk7pscICk'), ('복부', 'sVQqBDBZhmI'), ('복부', 'swxfuFCVRYE'), ('복부', 'kETh8T3it4k'), ('복부', 'hR1ZgDQqyVI'), ('복부', 'jiHoMmNhVzM'), 
        ('전신', 'gMaB-fG4u4g'), ('전신', 'swRNeYw1JkY'), ('전신', 'gqR73V3fq2k'), ('전신', 'nz5qsvRgK24'), ('전신', 'cMkZ6A7wngk'), ('전신', 'CYcLODSeC-c');
        
SELECT `tag_has_video`.`tag_name`, `tag_has_video`.`video_id`
FROM `ssafit_db_v3`.`tag_has_video`;

SELECT user.user_id, nickname, password, name, age, grade, area_name, class_num, score_upper, score_lower, score_belly, score_body
		FROM user
		LEFT JOIN score ON user.user_id = score.user_id;