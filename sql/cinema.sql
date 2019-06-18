-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'鐢靛奖瀛?,'鐢靛奖瀛?,'2019-06-23 17:55:59',1,'2019-04-20 17:55:59'),(2,'鐢靛奖瀛?','鐢靛奖瀛?','2019-06-23 17:55:59',2,'2019-04-20 17:55:59'),(3,'浼氬憳娲诲姩','浼氬憳娲诲姩','2019-06-27 16:00:00',3,'2019-06-08 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
INSERT INTO `activity_movie` VALUES (2,1),(2,2);
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'娴嬭瘯浼樻儬鍒?,'鏄ュ鐢靛奖鑺?,40,5,'2019-04-20 17:55:59','2019-06-23 17:55:59',0),(2,'娴嬭瘯浼樻儬鍒?,'鐢靛奖鑺?',30,4,'2019-04-20 17:55:59','2019-06-23 17:55:59',0),(3,'浼氬憳浼樻儬鍒?,'浼氬憳浼樻儬鍒?,50,10,'2019-06-08 16:00:00','2019-06-27 16:00:00',1);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (10,1),(2,11),(2,11),(2,11),(2,11),(1,11),(2,11),(2,10),(1,10);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_name` varchar(255) DEFAULT NULL,
  `hall_column` int(11) DEFAULT NULL,
  `hall_row` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'IMAX鍘?,10,5),(2,'5D鍘?,12,8),(3,'4D鍘?,13,13);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'http://img31.mtime.cn/pi/2016/04/12/142822.53400937_1000X1000.jpg','澶ф．璐靛紭 /浼婅棨绉€妯?,'','绁炶胺娴╁彶 /浜曚笂鍜屽溅 /楂樿壇鍋ュ惥 /灏忔灄娌欒嫍 /娉藉煄缇庨洩','鍔ㄧ敾','','',121,'2019-04-13 16:00:00','澶忕洰鍙嬩汉甯?,'鍦ㄤ汉涓庡鎬箣闂磋繃鐫€蹇欑鏃ュ瓙鐨勫鐩紝鍋剁劧涓庝互鍓嶇殑鍚屽缁撳煄閲嶉€紝鐢辨鍥炲繂璧蜂簡琚鎬紶韬殑鑻︽订璁板繂銆傛鏃讹紝澶忕洰璁よ瘑浜嗗湪褰掕繕鍚嶅瓧鐨勫鎬蹇嗕腑鍑虹幇鐨勫コ鎬锋触鏉戝鑾夋灊銆傚拰鐜插瓙鐩歌瘑鐨勫ス锛岀幇鍦ㄥ拰鐙瓙妞嬮泟涓€鍚岃繃鐫€骞崇ǔ鐨勭敓娲汇€傚鐩€氳繃涓庝粬浠殑浜ゆ祦锛屽績澧冧篃鍙樺緱骞冲拰銆備絾杩欏姣嶅瓙灞呬綇鐨勫煄闀囷紝鍗翠技涔庢綔浼忕潃绁炵鐨勫鎬€傚湪璋冩煡姝や簨褰掓潵鍚庯紝瀵勭敓浜庣尗鍜€佸笀韬綋鐨勨€滃涔嬬鈥濓紝鍦ㄨ棨鍘熷鐨勫涵闄腑锛屼竴澶滀箣闂村氨闀挎垚鏍戠粨鍑烘灉瀹炪€傝€屽悆鎺変簡涓庤嚜宸卞舰鐘剁浉浼兼灉瀹炵殑鐚挭鑰佸笀锛岀珶鐒跺垎瑁傛垚浜?涓?,0),(2,'http://img5.mtime.cn/pi/2018/12/03/091948.37827258_1000X1000.jpg','瀹夊路娉㈤】','','甯冨埄路鎷夊皵妫?,'鍔ㄤ綔/鍐掗櫓/绉戝够','','',120,'2019-06-09 16:00:00','鎯婂闃熼暱','婕敾涓殑鍒濅唬鎯婂濂冲＋鏇剧粡鏄竴鍚嶇編鍥界┖鍐涘潎鎯呮姤灞€鎺㈠憳锛屾殫鎭嬫儕濂囧厛鐢熴€傘€傘€?,0),(3,'http://img5.mtime.cn/pi/2017/06/11/161328.86015429_1000X1000.jpg','2','','2','2','','',120,'2019-06-09 16:00:00','榛戣惫','2',0),(16,'http://img5.mtime.cn/pi/2018/11/07/172634.26071380_1000X1000.jpg','鏋楀瓭璋?,'','闄堟剰娑?,'鐖辨儏','澶ч檰','',123,'2019-06-09 16:00:00','姣旀偛浼ゆ洿鎮蹭激鐨勬晠浜?,'鍞辩墖鍒朵綔浜哄紶鍝插嚡(鍒樹互璞?鍜岀帇鐗屼綔璇嶄汉瀹嬪獩濯?闄堟剰娑?鐩镐緷涓哄懡锛屼袱浜鸿嚜骞艰韩涓栧潕鍧峰彧鏈夊郊姝や负浼达紝浠栦滑鏄翰浜恒€佹槸鏈嬪弸锛屼篃褰蜂經鏄懡涓敞瀹氱殑鍙︿竴鍗娿€傜埗浜茬焦鎮ｉ仐浼犻噸鐥囪€岃姣嶄翰鎶涘純鐨勫摬鍑紝娣辨€曡嚜宸遍殢鏃朵細鍙戠梾涓嶄箙浜轰笘锛屽缁堟病鏈夎法鍑哄弸璋婄殑鐣岀嚎瀵瑰獩濯涘睍闇茬埍鎰忋€傜溂瑙佸摬鍑殑鐥呮儏鍔犻噸锛屼粬鏆楄嚜鍐冲畾鐢ㄥ墿浣欑殑鐢熷懡瀹屾垚浠栦滑涔嬮棿鐨勭粓鏇诧紝鍐嶄负濯涘獩鎵句釜鍙互鎵樹粯涓€鐢熺殑濂界敺浜恒€傝繖鏃讹紝浜嬩笟鏈?鎴愭俯鏌斾綋璐寸殑鍖荤敓(寮犱功璞?閫傛椂鐨勫嚭鐜拌浠栨垚涓虹収椤惧獩濯涚殑鏈€浣充汉閫夛紝浜屼汉鎸夐儴灏辩彮鍙戝睍鐫€鍏崇郴銆備竴鍒囩湅浼奸兘鍦ㄥ摬鍑殑璁″垝涓嬭繘琛屻€傜劧鑰岋紝鏁呬簨杩滄瘮杩欓噷鎵€鍐欐洿瑕佹偛浼?.....',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
INSERT INTO `movie_like` VALUES (1,10,'2019-05-25 02:40:19'),(1,11,'2019-05-22 09:38:12'),(2,10,'2019-05-23 09:38:12');
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund_policy`
--

DROP TABLE IF EXISTS `refund_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `refund_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refund_day` double NOT NULL,
  `refund_hour` double NOT NULL,
  `issue_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_policy`
--

LOCK TABLES `refund_policy` WRITE;
/*!40000 ALTER TABLE `refund_policy` DISABLE KEYS */;
INSERT INTO `refund_policy` VALUES (1,0.9,0.55,'2019-06-08 10:03:38'),(2,0.8,0.5,'2019-06-10 18:18:57');
/*!40000 ALTER TABLE `refund_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,2,'2019-06-12 17:00:00','2019-06-12 19:00:00',55),(74,1,2,'2019-06-13 06:00:00','2019-06-13 08:00:00',55),(75,2,2,'2019-06-13 06:00:00','2019-06-13 08:00:00',55),(76,3,3,'2019-06-13 06:00:00','2019-06-13 08:00:00',55),(77,1,3,'2019-06-13 11:00:00','2019-06-13 13:00:00',45),(78,2,3,'2019-06-13 11:00:00','2019-06-13 13:00:00',45),(79,3,3,'2019-06-13 11:00:00','2019-06-13 13:00:00',45),(80,3,16,'2019-06-14 11:00:00','2019-06-14 14:00:00',45),(81,1,16,'2019-06-14 11:00:00','2019-06-14 14:00:00',45),(82,2,16,'2019-06-14 11:00:00','2019-06-14 14:00:00',45);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `real_pay` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (10,1,5,3,3,1,'2019-06-12 10:50:52',55),(11,1,4,4,2,70,'2019-06-12 14:50:07',55),(11,1,5,4,3,71,'2019-06-12 14:50:07',55),(11,1,8,2,3,72,'2019-06-12 15:12:52',52.5),(11,1,9,3,3,73,'2019-06-12 15:18:37',52.5),(11,1,9,2,3,74,'2019-06-12 15:36:47',50),(11,1,8,3,3,75,'2019-06-12 15:41:28',50),(11,1,8,0,3,76,'2019-06-12 15:43:34',53.75),(11,1,9,0,3,77,'2019-06-12 15:43:34',53.75),(11,1,8,1,3,78,'2019-06-12 15:43:34',53.75),(11,1,9,1,3,79,'2019-06-12 15:43:34',53.75),(10,74,9,0,3,80,'2019-06-13 03:47:45',44.00000065565109),(10,74,9,1,1,81,'2019-06-13 03:47:45',44.00000065565109),(10,76,11,6,3,82,'2019-06-13 03:50:32',42.00000062584877),(10,76,12,6,1,83,'2019-06-13 03:50:32',42.00000062584877);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123456',0),(2,'manager','123456',1),(3,'staff','123456',2),(10,'test','123456',3),(11,'general','123456',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_charge`
--

DROP TABLE IF EXISTS `user_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `charge` double NOT NULL,
  `charge_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_charge`
--

LOCK TABLES `user_charge` WRITE;
/*!40000 ALTER TABLE `user_charge` DISABLE KEYS */;
INSERT INTO `user_charge` VALUES (1,10,100,'2019-06-12 10:54:38');
/*!40000 ALTER TABLE `user_charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (1,7);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_activity`
--

DROP TABLE IF EXISTS `vip_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vip_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(50) NOT NULL,
  `card_price` double NOT NULL,
  `target_amount` double NOT NULL,
  `discount_amount` double NOT NULL,
  `discount` float NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_activity`
--

LOCK TABLES `vip_activity` WRITE;
/*!40000 ALTER TABLE `vip_activity` DISABLE KEYS */;
INSERT INTO `vip_activity` VALUES (1,'閲戝崱',500,200,50,0.8,1),(2,'閾跺崱',300,100,10,0.9,1);
/*!40000 ALTER TABLE `vip_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vipactivity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (1,10,83,'2019-06-13 03:50:37',1);
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-13 13:08:16
