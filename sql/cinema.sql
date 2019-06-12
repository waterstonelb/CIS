-- MySQL dump 10.17  Distrib 10.3.15-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	10.3.15-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'电影季','电影季','2019-06-23 17:55:59',1,'2019-04-20 17:55:59'),(2,'电影季2','电影季2','2019-06-23 17:55:59',2,'2019-04-20 17:55:59'),(3,'会员活动','会员活动','2019-06-27 16:00:00',3,'2019-06-08 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  `end_time` timestamp NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'测试优惠券','春季电影节',40,5,'2019-04-20 17:55:59','2019-06-23 17:55:59',0),(2,'测试优惠券','电影节2',30,4,'2019-04-20 17:55:59','2019-06-23 17:55:59',0),(3,'会员优惠券','会员优惠券',50,10,'2019-06-08 16:00:00','2019-06-27 16:00:00',1);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (10,1);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_name` varchar(255) DEFAULT NULL,
  `hall_column` int(11) DEFAULT NULL,
  `hall_row` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'IMAX厅',10,5),(2,'5D厅',12,8),(3,'4D厅',13,13);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `start_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'http://img31.mtime.cn/pi/2016/04/12/142822.53400937_1000X1000.jpg','大森贵弘 /伊藤秀樹','','神谷浩史 /井上和彦 /高良健吾 /小林沙苗 /泽城美雪','动画',NULL,NULL,120,'2019-04-14 14:54:31','夏目友人帐','在人与妖怪之间过着忙碌日子的夏目，偶然与以前的同学结城重逢，由此回忆起了被妖怪缠身的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。和玲子相识的她，现在和独子椋雄一同过着平稳的生活。夏目通过与他们的交流，心境也变得平和。但这对母子居住的城镇，却似乎潜伏着神秘的妖怪。在调查此事归来后，寄生于猫咪老师身体的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。而吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个',0),(2,'http://img5.mtime.cn/pi/2018/12/03/091948.37827258_1000X1000.jpg','安娜·波顿','','布利·拉尔森','动作/冒险/科幻','','',120,'2019-06-09 16:00:00','惊奇队长','漫画中的初代惊奇女士曾经是一名美国空军均情报局探员，暗恋惊奇先生。。。',0),(3,'http://img5.mtime.cn/pi/2017/06/11/161328.86015429_1000X1000.jpg','2','','2','2','','',120,'2019-06-09 16:00:00','黑豹','2',0),(16,'http://img5.mtime.cn/pi/2018/11/07/172634.26071380_1000X1000.jpg','林孝谦','','陈意涵','爱情','大陆','',123,'2019-06-09 16:00:00','比悲伤更悲伤的故事','唱片制作人张哲凯(刘以豪)和王牌作词人宋媛媛(陈意涵)相依为命，两人自幼身世坎坷只有彼此为伴，他们是亲人、是朋友，也彷佛是命中注定的另一半。父亲罹患遗传重症而被母亲抛弃的哲凯，深怕自己随时会发病不久人世，始终没有跨出友谊的界线对媛媛展露爱意。眼见哲凯的病情加重，他暗自决定用剩余的生命完成他们之间的终曲，再为媛媛找个可以托付一生的好男人。这时，事业有 成温柔体贴的医生(张书豪)适时的出现让他成为照顾媛媛的最佳人选，二人按部就班发展着关系。一切看似都在哲凯的计划下进行。然而，故事远比这里所写更要悲伤......',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,2,'2019-06-12 17:00:00','2019-06-12 19:00:00',55);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT current_timestamp(),
  `real_pay` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (10,1,5,3,1,1,'2019-06-12 10:50:52',55);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `charge` double NOT NULL,
  `charge_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(50) NOT NULL,
  `card_price` double NOT NULL,
  `target_amount` double NOT NULL,
  `discount_amount` double NOT NULL,
  `discount` float NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_activity`
--

LOCK TABLES `vip_activity` WRITE;
/*!40000 ALTER TABLE `vip_activity` DISABLE KEYS */;
INSERT INTO `vip_activity` VALUES (1,'金卡',500,200,50,0.8,1),(2,'银卡',300,100,10,0.9,1);
/*!40000 ALTER TABLE `vip_activity` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
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
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `vipactivity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (1,10,255,'2019-06-09 12:02:42',1);
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

-- Dump completed on 2019-06-10 19:40:08
