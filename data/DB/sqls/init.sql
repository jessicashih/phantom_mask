-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: kdan
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `balance` decimal(19,2) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,952.69,'Eric Underwood'),(2,508.19,'Peggy Maxwell'),(3,759.15,'Sherri Lynch'),(4,857.48,'Mindy Perkins'),(5,791.87,'Ruby Andrews'),(6,690.99,'Jo Barton'),(7,504.10,'Jose May'),(8,266.06,'Ricky Anderson'),(9,584.52,'Frances Collier'),(10,110.18,'Eula Wheeler'),(11,291.88,'Mae Hill'),(12,488.47,'Tamara Dean'),(13,149.91,'Lawrence Fletcher'),(14,933.18,'Cassandra Fields'),(15,509.55,'Audrey Brewer'),(16,530.50,'Juan Estrada'),(17,153.56,'Viola Quinn'),(18,579.98,'Ada Larson'),(19,545.50,'Connie Vasquez'),(20,161.91,'Bobbie Russell');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mask`
--

DROP TABLE IF EXISTS `mask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mask` (
  `item_no` bigint NOT NULL AUTO_INCREMENT,
  `color` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `num_of_pack` int DEFAULT NULL,
  PRIMARY KEY (`item_no`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mask`
--

LOCK TABLES `mask` WRITE;
/*!40000 ALTER TABLE `mask` DISABLE KEYS */;
INSERT INTO `mask` VALUES (1,'blue','AniMask',10),(2,'black','MaskT',10),(3,'black','Free to Roam',3),(4,'green','AniMask',10),(5,'blue','Masquerade',6),(6,'blue','Second Smile',10),(7,'green','Free to Roam',3),(8,'black','AniMask',3),(9,'black','Masquerade',3),(10,'blue','MaskT',3),(11,'black','Masquerade',10),(12,'blue','Free to Roam',10),(13,'black','Free to Roam',10),(14,'blue','AniMask',6),(15,'black','MaskT',3),(16,'green','Second Smile',3),(17,'blue','Free to Roam',3),(18,'green','MaskT',10),(19,'green','Masquerade',10),(20,'black','AniMask',6),(21,'green','MaskT',6),(22,'green','Second Smile',6),(23,'blue','Masquerade',10),(24,'blue','Second Smile',3),(25,'black','MaskT',6),(26,'blue','MaskT',6),(27,'black','Free to Roam',6),(28,'black','Second Smile',3),(29,'black','Second Smile',6),(30,'blue','Masquerade',3),(31,'green','MaskT',3),(32,'black','AniMask',10),(33,'green','AniMask',3),(34,'green','Masquerade',3),(35,'green','Masquerade',6),(36,'blue','AniMask',3);
/*!40000 ALTER TABLE `mask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mask_price_record`
--

DROP TABLE IF EXISTS `mask_price_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mask_price_record` (
  `seq_no` bigint NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `create_time` time DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `item_no` bigint DEFAULT NULL,
  `pharmacy_seqno` bigint DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `update_time` time DEFAULT NULL,
  PRIMARY KEY (`seq_no`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mask_price_record`
--

LOCK TABLES `mask_price_record` WRITE;
/*!40000 ALTER TABLE `mask_price_record` DISABLE KEYS */;
INSERT INTO `mask_price_record` VALUES (1,'2021-05-02','16:41:14',NULL,1,1,33.65,NULL,NULL),(2,'2021-05-02','16:41:14',NULL,2,2,14.90,NULL,NULL),(3,'2021-05-02','16:41:14',NULL,3,2,13.83,NULL,NULL),(4,'2021-05-02','16:41:14',NULL,4,2,49.21,NULL,NULL),(5,'2021-05-02','16:41:14',NULL,5,2,16.75,NULL,NULL),(6,'2021-05-02','16:41:14',NULL,6,3,39.98,NULL,NULL),(7,'2021-05-02','16:41:14',NULL,7,3,8.83,NULL,NULL),(8,'2021-05-02','16:41:14',NULL,8,3,12.81,NULL,NULL),(9,'2021-05-02','16:41:14',NULL,9,3,8.17,NULL,NULL),(10,'2021-05-02','16:41:14',NULL,10,3,7.04,NULL,NULL),(11,'2021-05-02','16:41:14',NULL,11,4,19.54,NULL,NULL),(12,'2021-05-02','16:41:14',NULL,12,4,30.74,NULL,NULL),(13,'2021-05-02','16:41:14',NULL,13,4,26.54,NULL,NULL),(14,'2021-05-02','16:41:14',NULL,14,4,28.72,NULL,NULL),(15,'2021-05-02','16:41:14',NULL,15,4,4.14,NULL,NULL),(16,'2021-05-02','16:41:14',NULL,16,4,6.55,NULL,NULL),(17,'2021-05-02','16:41:14',NULL,9,5,3.76,NULL,NULL),(18,'2021-05-02','16:41:14',NULL,17,5,7.89,NULL,NULL),(19,'2021-05-02','16:41:14',NULL,18,5,32.57,NULL,NULL),(20,'2021-05-02','16:41:14',NULL,4,5,22.01,NULL,NULL),(21,'2021-05-02','16:41:14',NULL,19,5,42.27,NULL,NULL),(22,'2021-05-02','16:41:14',NULL,20,5,14.16,NULL,NULL),(23,'2021-05-02','16:41:14',NULL,21,6,29.91,NULL,NULL),(24,'2021-05-02','16:41:14',NULL,22,6,11.89,NULL,NULL),(25,'2021-05-02','16:41:14',NULL,18,6,35.06,NULL,NULL),(26,'2021-05-02','16:41:14',NULL,3,6,5.31,NULL,NULL),(27,'2021-05-02','16:41:14',NULL,4,7,10.83,NULL,NULL),(28,'2021-05-02','16:41:14',NULL,8,7,8.94,NULL,NULL),(29,'2021-05-02','16:41:14',NULL,5,7,20.00,NULL,NULL),(30,'2021-05-02','16:41:14',NULL,23,7,21.67,NULL,NULL),(31,'2021-05-02','16:41:14',NULL,24,7,7.32,NULL,NULL),(32,'2021-05-02','16:41:14',NULL,25,8,13.41,NULL,NULL),(33,'2021-05-02','16:41:14',NULL,9,9,10.28,NULL,NULL),(34,'2021-05-02','16:41:14',NULL,7,9,14.18,NULL,NULL),(35,'2021-05-02','16:41:14',NULL,18,9,47.83,NULL,NULL),(36,'2021-05-02','16:41:14',NULL,12,10,38.41,NULL,NULL),(37,'2021-05-02','16:41:14',NULL,26,10,27.91,NULL,NULL),(38,'2021-05-02','16:41:14',NULL,27,10,28.54,NULL,NULL),(39,'2021-05-02','16:41:14',NULL,5,10,28.02,NULL,NULL),(40,'2021-05-02','16:41:14',NULL,28,11,5.06,NULL,NULL),(41,'2021-05-02','16:41:14',NULL,24,11,12.51,NULL,NULL),(42,'2021-05-02','16:41:14',NULL,3,11,10.81,NULL,NULL),(43,'2021-05-02','16:41:14',NULL,29,11,23.73,NULL,NULL),(44,'2021-05-02','16:41:14',NULL,2,11,43.94,NULL,NULL),(45,'2021-05-02','16:41:14',NULL,15,11,10.13,NULL,NULL),(46,'2021-05-02','16:41:14',NULL,17,12,5.61,NULL,NULL),(47,'2021-05-02','16:41:14',NULL,2,12,46.51,NULL,NULL),(48,'2021-05-02','16:41:14',NULL,28,12,9.59,NULL,NULL),(49,'2021-05-02','16:41:14',NULL,30,12,6.26,NULL,NULL),(50,'2021-05-02','16:41:14',NULL,1,12,11.47,NULL,NULL),(51,'2021-05-02','16:41:14',NULL,22,13,6.82,NULL,NULL),(52,'2021-05-02','16:41:14',NULL,21,13,25.73,NULL,NULL),(53,'2021-05-02','16:41:14',NULL,25,13,9.70,NULL,NULL),(54,'2021-05-02','16:41:14',NULL,4,14,25.42,NULL,NULL),(55,'2021-05-02','16:41:14',NULL,1,14,34.39,NULL,NULL),(56,'2021-05-02','16:41:14',NULL,28,14,12.38,NULL,NULL),(57,'2021-05-02','16:41:14',NULL,31,14,11.56,NULL,NULL),(58,'2021-05-02','16:41:14',NULL,14,14,19.16,NULL,NULL),(59,'2021-05-02','16:41:14',NULL,32,14,36.31,NULL,NULL),(60,'2021-05-02','16:41:14',NULL,27,14,20.16,NULL,NULL),(61,'2021-05-02','16:41:14',NULL,33,14,3.23,NULL,NULL),(62,'2021-05-02','16:41:14',NULL,34,14,5.73,NULL,NULL),(63,'2021-05-02','16:41:14',NULL,23,15,38.33,NULL,NULL),(64,'2021-05-02','16:41:14',NULL,25,16,12.90,NULL,NULL),(65,'2021-05-02','16:41:14',NULL,8,16,10.65,NULL,NULL),(66,'2021-05-02','16:41:14',NULL,15,16,4.83,NULL,NULL),(67,'2021-05-02','16:41:14',NULL,7,16,13.93,NULL,NULL),(68,'2021-05-02','16:41:14',NULL,9,17,7.33,NULL,NULL),(69,'2021-05-02','16:41:14',NULL,35,17,26.60,NULL,NULL),(70,'2021-05-02','16:41:14',NULL,20,17,6.53,NULL,NULL),(71,'2021-05-02','16:41:14',NULL,5,18,13.55,NULL,NULL),(72,'2021-05-02','16:41:14',NULL,2,18,46.69,NULL,NULL),(73,'2021-05-02','16:41:14',NULL,12,18,15.79,NULL,NULL),(74,'2021-05-02','16:41:14',NULL,22,18,17.61,NULL,NULL),(75,'2021-05-02','16:41:14',NULL,13,18,35.66,NULL,NULL),(76,'2021-05-02','16:41:14',NULL,26,18,28.27,NULL,NULL),(77,'2021-05-02','16:41:14',NULL,18,18,39.40,NULL,NULL),(78,'2021-05-02','16:41:14',NULL,17,18,14.61,NULL,NULL),(79,'2021-05-02','16:41:14',NULL,28,18,13.52,NULL,NULL),(80,'2021-05-02','16:41:14',NULL,35,19,12.70,NULL,NULL),(81,'2021-05-02','16:41:14',NULL,22,20,14.90,NULL,NULL),(82,'2021-05-02','16:41:14',NULL,36,20,9.24,NULL,NULL),(83,'2021-01-02','20:41:02',_binary '',9,9,9.26,'2021-05-02','16:41:14'),(84,'2021-01-04','11:29:05',_binary '',35,19,12.01,'2021-05-02','16:41:14'),(85,'2021-01-07','03:49:25',_binary '',25,13,8.90,'2021-05-02','16:41:14'),(86,'2021-01-22','09:27:11',_binary '',22,13,7.30,'2021-05-02','16:41:14'),(87,'2021-01-26','03:41:14',_binary '',10,3,7.01,'2021-05-02','16:41:14'),(88,'2021-01-08','13:36:55',_binary '',23,15,38.03,'2021-05-02','16:41:14'),(89,'2021-01-09','02:23:32',_binary '',10,3,6.88,'2021-05-02','16:41:14'),(90,'2021-01-11','00:00:08',_binary '',8,3,13.86,'2021-05-02','16:41:14'),(91,'2021-01-20','18:31:28',_binary '',32,14,39.63,'2021-05-02','16:41:14'),(92,'2021-01-28','11:56:40',_binary '',21,13,27.41,'2021-05-02','16:41:14'),(93,'2021-01-30','07:01:12',_binary '',28,11,4.60,'2021-05-02','16:41:14'),(94,'2021-01-07','22:59:35',_binary '',12,10,38.72,'2021-05-02','16:41:14'),(95,'2021-01-09','06:34:26',_binary '',35,17,26.49,'2021-05-02','16:41:14'),(96,'2021-01-17','13:13:56',_binary '',36,20,8.71,'2021-05-02','16:41:14'),(97,'2021-01-18','03:33:09',_binary '',2,2,14.34,'2021-05-02','16:41:14'),(98,'2021-01-02','05:00:08',_binary '',35,19,12.52,'2021-05-02','16:41:14'),(99,'2021-01-03','11:50:47',_binary '',35,19,11.92,'2021-05-02','16:41:14'),(100,'2021-01-11','17:54:43',_binary '',23,15,35.27,'2021-05-02','16:41:14'),(101,'2021-01-12','21:00:59',_binary '',28,11,5.20,'2021-05-02','16:41:14'),(102,'2021-01-14','16:22:48',_binary '',23,15,41.92,'2021-05-02','16:41:14'),(103,'2021-01-16','20:39:46',_binary '',35,19,12.02,'2021-05-02','16:41:14'),(104,'2021-01-20','19:19:28',_binary '',21,13,27.45,'2021-05-02','16:41:14'),(105,'2021-01-05','21:10:10',_binary '',12,10,41.35,'2021-05-02','16:41:14'),(106,'2021-01-18','09:44:02',_binary '',7,3,8.39,'2021-05-02','16:41:14'),(107,'2021-01-19','12:34:04',_binary '',8,7,8.97,'2021-05-02','16:41:14'),(108,'2021-01-21','22:42:24',_binary '',1,1,31.47,'2021-05-02','16:41:14'),(109,'2021-01-25','02:46:28',_binary '',23,15,34.65,'2021-05-02','16:41:14'),(110,'2021-01-25','09:01:09',_binary '',35,19,13.67,'2021-05-02','16:41:14'),(111,'2021-01-28','07:47:49',_binary '',26,10,29.10,'2021-05-02','16:41:14'),(112,'2021-01-29','07:50:51',_binary '',4,7,11.50,'2021-05-02','16:41:14'),(113,'2021-01-02','19:09:19',_binary '',35,19,13.04,'2021-05-02','16:41:14'),(114,'2021-01-04','04:43:21',_binary '',2,11,47.10,'2021-05-02','16:41:14'),(115,'2021-01-09','16:28:11',_binary '',25,8,13.68,'2021-05-02','16:41:14'),(116,'2021-01-12','14:08:04',_binary '',22,20,16.08,'2021-05-02','16:41:14'),(117,'2021-01-13','02:19:26',_binary '',2,11,46.50,'2021-05-02','16:41:14'),(118,'2021-01-24','06:42:24',_binary '',21,13,27.22,'2021-05-02','16:41:14'),(119,'2021-01-13','08:26:03',_binary '',22,18,17.30,'2021-05-02','16:41:14'),(120,'2021-01-10','18:21:02',_binary '',23,15,39.29,'2021-05-02','16:41:14'),(121,'2021-01-14','20:36:52',_binary '',1,1,34.11,'2021-05-02','16:41:14'),(122,'2021-01-22','05:53:35',_binary '',28,11,4.77,'2021-05-02','16:41:14'),(123,'2021-01-24','19:38:39',_binary '',12,10,41.83,'2021-05-02','16:41:14'),(124,'2021-01-29','06:09:31',_binary '',22,6,12.42,'2021-05-02','16:41:14'),(125,'2021-01-19','20:53:57',_binary '',36,20,10.02,'2021-05-02','16:41:14'),(126,'2021-01-30','17:50:39',_binary '',28,12,9.06,'2021-05-02','16:41:14'),(127,'2021-01-11','04:29:31',_binary '',25,16,14.01,'2021-05-02','16:41:14'),(128,'2021-01-09','03:09:01',_binary '',23,15,35.73,'2021-05-02','16:41:14'),(129,'2021-01-12','17:12:59',_binary '',22,6,10.82,'2021-05-02','16:41:14'),(130,'2021-01-13','16:06:08',_binary '',22,20,16.26,'2021-05-02','16:41:14'),(131,'2021-01-14','13:13:17',_binary '',10,3,7.28,'2021-05-02','16:41:14'),(132,'2021-01-18','00:31:18',_binary '',4,2,48.52,'2021-05-02','16:41:14'),(133,'2021-01-19','00:20:59',_binary '',5,2,17.00,'2021-05-02','16:41:14'),(134,'2021-01-24','00:07:03',_binary '',12,4,27.84,'2021-05-02','16:41:14'),(135,'2021-01-25','07:12:20',_binary '',6,3,39.53,'2021-05-02','16:41:14'),(136,'2021-01-26','22:47:36',_binary '',15,11,10.83,'2021-05-02','16:41:14'),(137,'2021-01-02','12:07:07',_binary '',14,4,28.52,'2021-05-02','16:41:14'),(138,'2021-01-05','00:08:49',_binary '',1,1,32.36,'2021-05-02','16:41:14'),(139,'2021-01-07','10:00:00',_binary '',35,19,13.16,'2021-05-02','16:41:14'),(140,'2021-01-11','18:31:44',_binary '',5,10,26.55,'2021-05-02','16:41:14'),(141,'2021-01-12','18:50:36',_binary '',25,13,9.53,'2021-05-02','16:41:14'),(142,'2021-01-16','15:46:54',_binary '',4,5,22.05,'2021-05-02','16:41:14'),(143,'2021-01-22','02:13:55',_binary '',25,16,11.63,'2021-05-02','16:41:14'),(144,'2021-01-23','23:38:40',_binary '',14,4,27.67,'2021-05-02','16:41:14'),(145,'2021-01-25','16:06:27',_binary '',20,17,6.30,'2021-05-02','16:41:14'),(146,'2021-01-01','15:57:40',_binary '',14,14,18.96,'2021-05-02','16:41:14'),(147,'2021-01-07','12:20:58',_binary '',2,2,15.79,'2021-05-02','16:41:14'),(148,'2021-01-08','07:34:26',_binary '',4,2,46.64,'2021-05-02','16:41:14'),(149,'2021-01-14','11:41:41',_binary '',14,4,27.57,'2021-05-02','16:41:14'),(150,'2021-01-22','19:01:35',_binary '',4,2,44.45,'2021-05-02','16:41:14'),(151,'2021-01-24','04:40:43',_binary '',35,19,11.83,'2021-05-02','16:41:14'),(152,'2021-01-27','19:47:28',_binary '',9,9,9.58,'2021-05-02','16:41:14'),(153,'2021-01-03','17:36:46',_binary '',8,3,12.49,'2021-05-02','16:41:14'),(154,'2021-01-21','04:10:05',_binary '',35,19,12.11,'2021-05-02','16:41:14'),(155,'2021-01-26','02:28:08',_binary '',5,18,13.05,'2021-05-02','16:41:14'),(156,'2021-01-27','02:29:55',_binary '',3,6,5.30,'2021-05-02','16:41:14'),(157,'2021-01-18','16:37:03',_binary '',26,18,27.93,'2021-05-02','16:41:14'),(158,'2021-01-08','06:08:22',_binary '',8,3,13.93,'2021-05-02','16:41:14'),(159,'2021-01-05','18:53:24',_binary '',8,16,10.49,'2021-05-02','16:41:14'),(160,'2021-01-17','23:14:20',_binary '',17,12,5.13,'2021-05-02','16:41:14'),(161,'2021-01-01','20:13:53',_binary '',17,12,5.42,'2021-05-02','16:41:14'),(162,'2021-01-10','03:21:55',_binary '',1,1,32.22,'2021-05-02','16:41:14'),(163,'2021-01-13','18:56:13',_binary '',27,10,26.39,'2021-05-02','16:41:14'),(164,'2021-01-15','15:10:28',_binary '',2,2,14.11,'2021-05-02','16:41:14'),(165,'2021-01-17','14:48:26',_binary '',35,17,26.08,'2021-05-02','16:41:14'),(166,'2021-01-19','16:44:30',_binary '',21,6,32.64,'2021-05-02','16:41:14'),(167,'2021-01-25','08:00:44',_binary '',4,14,24.74,'2021-05-02','16:41:14'),(168,'2021-01-06','08:01:31',_binary '',4,5,19.96,'2021-05-02','16:41:14'),(169,'2021-01-08','21:56:50',_binary '',35,19,13.58,'2021-05-02','16:41:14'),(170,'2021-01-09','18:04:01',_binary '',19,5,38.09,'2021-05-02','16:41:14'),(171,'2021-01-10','07:42:34',_binary '',25,13,10.11,'2021-05-02','16:41:14'),(172,'2021-01-22','15:51:01',_binary '',36,20,9.45,'2021-05-02','16:41:14'),(173,'2021-01-25','01:06:21',_binary '',26,10,28.68,'2021-05-02','16:41:14'),(174,'2021-01-27','16:46:47',_binary '',35,17,27.68,'2021-05-02','16:41:14'),(175,'2021-01-28','03:01:56',_binary '',2,2,14.36,'2021-05-02','16:41:14'),(176,'2021-01-01','06:05:32',_binary '',10,3,7.26,'2021-05-02','16:41:14'),(177,'2021-01-01','22:54:04',_binary '',12,10,35.03,'2021-05-02','16:41:14'),(178,'2021-01-18','08:25:29',_binary '',9,9,10.08,'2021-05-02','16:41:14'),(179,'2021-01-25','05:32:22',_binary '',24,11,11.80,'2021-05-02','16:41:14'),(180,'2021-01-27','14:02:37',_binary '',36,20,9.60,'2021-05-02','16:41:14');
/*!40000 ALTER TABLE `mask_price_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy` (
  `seq_no` bigint NOT NULL AUTO_INCREMENT,
  `balance` decimal(19,2) DEFAULT NULL,
  `fri_close` time DEFAULT NULL,
  `fri_open` time DEFAULT NULL,
  `mon_close` time DEFAULT NULL,
  `mon_open` time DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sat_close` time DEFAULT NULL,
  `sat_open` time DEFAULT NULL,
  `sun_close` time DEFAULT NULL,
  `sun_open` time DEFAULT NULL,
  `thu_close` time DEFAULT NULL,
  `thu_open` time DEFAULT NULL,
  `tue_close` time DEFAULT NULL,
  `tue_open` time DEFAULT NULL,
  `wed_close` time DEFAULT NULL,
  `wed_open` time DEFAULT NULL,
  PRIMARY KEY (`seq_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy`
--

LOCK TABLES `pharmacy` WRITE;
/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
INSERT INTO `pharmacy` VALUES (1,777.61,'23:59:59','17:09:00','21:58:00','12:56:00','Better You','20:20:00','00:00:00','09:33:00','07:10:00',NULL,NULL,'22:42:00','13:06:00','21:58:00','12:56:00'),(2,596.94,'07:20:00','00:05:00','14:48:00','11:00:00','Cash Saver Pharmacy','23:59:59','09:01:00','12:43:00','00:00:00',NULL,NULL,'07:20:00','00:05:00',NULL,NULL),(3,181.84,'16:49:00','02:55:00',NULL,NULL,'PrecisionMed',NULL,NULL,'05:33:00','10:59:00','19:40:00','16:30:00','16:25:00','14:10:00','21:46:00','16:57:00'),(4,903.57,'23:59:59','15:01:00',NULL,NULL,'MedSavvy','21:24:00','00:00:00','07:58:00','00:03:00','22:48:00','12:14:00','23:13:00','10:08:00','21:48:00','12:38:00'),(5,905.44,NULL,NULL,'17:06:00','07:14:00','Pill Pack','06:35:00','04:35:00','16:59:00','01:39:00',NULL,NULL,'19:25:00','16:47:00','19:00:00','15:30:00'),(6,858.91,NULL,NULL,'17:49:00','13:18:00','Heartland Pharmacy','08:03:00','04:10:00','18:50:00','15:07:00','11:25:00','00:00:00','17:45:00','05:06:00','23:59:59','03:25:00'),(7,323.30,NULL,NULL,'16:49:00','10:53:00','Longhorn Pharmacy',NULL,NULL,'02:05:00','15:53:00','00:30:00','08:25:00','21:42:00','17:41:00','16:49:00','10:53:00'),(8,238.89,'19:48:00','08:05:00','09:16:00','03:27:00','PharmaMed','09:16:00','03:27:00',NULL,NULL,'17:25:00','09:49:00','19:40:00','14:41:00','16:06:00','04:05:00'),(9,151.65,NULL,NULL,'23:59:59','10:09:00','Neighbors','20:24:00','13:14:00','16:40:00','00:02:00','17:46:00','15:31:00','02:26:00','00:00:00','17:33:00','15:26:00'),(10,753.18,'23:59:59','00:27:00',NULL,NULL,'Discount Drugs','04:08:00','00:00:00','06:25:00','03:04:00','23:19:00','14:04:00',NULL,NULL,'09:37:00','05:16:00'),(11,467.39,'23:59:59','13:36:00',NULL,NULL,'Medlife','20:51:00','00:00:00','19:44:00','02:42:00','09:13:00','15:57:00',NULL,NULL,'20:32:00','16:49:00'),(12,896.75,'07:57:00','00:00:00','14:53:00','07:50:00','Pride Pharmacy','17:45:00','12:20:00','10:49:00','15:50:00','23:59:59','00:53:00',NULL,NULL,NULL,NULL),(13,785.02,'20:39:00','16:20:00','23:07:00','16:30:00','Atlas Drugs','12:55:00','00:17:00','23:59:00','05:33:00',NULL,NULL,NULL,NULL,'16:48:00','10:16:00'),(14,220.73,'15:08:00','04:02:00','15:08:00','04:02:00','Thrifty Way Pharmacy','21:32:00','12:21:00',NULL,NULL,NULL,NULL,'18:23:00','09:57:00','00:10:00','12:10:00'),(15,274.49,NULL,NULL,'14:26:00','10:06:00','Apotheco','14:26:00','10:06:00','05:32:00','15:53:00','19:49:00','12:24:00','22:13:00','08:27:00','16:22:00','08:06:00'),(16,767.14,NULL,NULL,'20:52:00','04:08:00','Drug Blend','20:37:00','11:18:00','14:48:00','04:26:00','23:43:00','16:44:00','06:01:00','01:01:00','20:37:00','11:18:00'),(17,898.06,'04:59:00','08:57:00','14:55:00','07:59:00','Wellcare',NULL,NULL,NULL,NULL,'06:48:00','01:57:00','04:59:00','08:57:00','22:13:00','17:12:00'),(18,181.76,'16:59:00','05:24:00','06:43:00','02:30:00','Assured Rx','06:43:00','02:30:00',NULL,NULL,'05:27:00','02:06:00','23:59:59','08:44:00','11:28:00','00:00:00'),(19,510.91,NULL,NULL,'11:46:00','07:12:00','RxToMe','23:59:59','10:47:00','12:50:00','00:00:00','14:07:00','08:59:00',NULL,NULL,'20:15:00','16:24:00'),(20,466.36,'21:01:00','16:08:00','23:59:59','00:20:00','DFW Wellness',NULL,NULL,'13:23:00','10:02:00','09:41:00','10:02:00','16:06:00','00:00:00','13:23:00','10:02:00');
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_record`
--

DROP TABLE IF EXISTS `purchase_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_record` (
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` date DEFAULT NULL,
  `create_time` time DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `price_record` bigint DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_record`
--

LOCK TABLES `purchase_record` WRITE;
/*!40000 ALTER TABLE `purchase_record` DISABLE KEYS */;
INSERT INTO `purchase_record` VALUES ('067a0f0f-aebd-40b6-90f7-e393b8d54f3f','2021-01-04','11:29:05',1,84),('06bfa9aa-6e4b-4170-aa46-d16e2495fff3','2021-01-28','03:01:56',19,175),('088e7131-353f-4d94-bdce-96cc8a7d780f','2021-01-21','04:10:05',14,154),('0ce1ec37-a589-44ea-b020-747e50296f53','2021-01-26','03:41:14',1,87),('0d373571-1d94-4024-91d4-717ad1f98bc7','2021-01-25','08:00:44',18,167),('13df0990-8cd3-49bc-a221-bcb3e15fafcd','2021-01-01','22:54:04',20,177),('1564f43d-396a-4d23-949c-73365d241ad4','2021-01-24','00:07:03',11,134),('17075772-4f8c-4cc3-bb0e-c67112b0ea84','2021-01-08','06:08:22',16,158),('18d996cb-63b5-4f4a-8906-3f5ef4edaa5b','2021-01-16','15:46:54',12,142),('19851551-7854-4f2a-8d28-ab7f4c575f84','2021-01-22','09:27:11',1,86),('1994a97b-4b7c-4b07-ae1e-f18e686c9d90','2021-01-02','19:09:19',6,113),('201deecb-a36b-44c0-ac2c-a9f8a361e25a','2021-01-08','21:56:50',19,169),('203bc938-ef6b-4f40-854a-764e79c44174','2021-01-26','02:28:08',14,155),('2065da0a-0435-4a1b-9631-80911e3142ca','2021-01-02','20:41:02',1,83),('234d4c72-9cdf-4fe1-bfd3-bc2e87e67c2b','2021-01-12','21:00:59',4,101),('242511df-458f-49e2-8876-dfd740c32404','2021-01-12','18:50:36',12,141),('25275961-86cc-44e1-a6c0-dab3277acf21','2021-01-25','05:32:22',20,179),('2734d77c-332a-4070-95c3-0e0ab2c42b92','2021-01-11','04:29:31',10,127),('2971f094-7139-4df3-8cd3-6cb4c0e314b7','2021-01-09','18:04:01',19,170),('2a95de0e-ee23-45ef-8e3c-f1eab676152c','2021-01-01','15:57:40',13,146),('2afe9700-100a-4eaa-b71f-70f992f2aeea','2021-01-29','06:09:31',8,124),('2da79dc5-03aa-4a65-acef-edf68955be73','2021-01-27','14:02:37',20,180),('363c1dd3-0ace-462c-861d-247dc3e8192c','2021-01-30','17:50:39',9,126),('39e0c381-9685-4cf9-97c8-e2494fd4631e','2021-01-30','07:01:12',2,93),('3ae591da-cb93-49b5-b42a-94b07e335226','2021-01-03','17:36:46',14,153),('3b2796eb-943f-4d91-ab56-28e2ee47f770','2021-01-26','22:47:36',11,136),('3c67c796-85f2-46fa-abf5-101c55622a6a','2021-01-21','22:42:24',5,108),('409dc0bf-f341-456b-86c6-b8bf8a7498e7','2021-01-14','16:22:48',4,102),('43455aab-4c0b-4f64-809e-1f0973944814','2021-01-09','03:09:01',11,128),('4aab0f1e-cbd0-40a1-9881-51563db36450','2021-01-14','11:41:41',13,149),('4af0938b-155c-42da-9e18-42e333209b0f','2021-01-22','15:51:01',19,172),('4d084770-6367-4ee2-a446-944bdb3555b4','2021-01-01','06:05:32',20,176),('4eb04106-36d0-42f3-88a2-da47a126c3a9','2021-01-18','08:25:29',20,178),('4f04b715-2b62-4024-9a0c-2d53a7bde045','2021-01-12','14:08:04',6,116),('51737816-51f2-4236-8306-ed953cba08ec','2021-01-24','04:40:43',13,151),('538de9bf-70b7-4a51-9068-99b3e1e32a23','2021-01-22','02:13:55',12,143),('5d168d22-1de4-43a2-9508-546ffd70cb0a','2021-01-19','00:20:59',11,133),('5e2fb912-e749-4c77-8e43-14bef515fef1','2021-01-05','18:53:24',17,159),('5e776161-735a-40d2-8cd2-2be8401b739d','2021-01-18','09:44:02',5,106),('5e7daf9d-82b3-4633-81e9-132b07a2f524','2021-01-22','19:01:35',13,150),('5ea697d8-72d7-4be7-86a4-54cde1464cf3','2021-01-07','10:00:00',12,139),('5ec675a0-215c-4cff-b929-7ba8eb5fcdd1','2021-01-05','21:10:10',5,105),('60d89511-f21f-43b7-a4af-071f3d253e51','2021-01-09','06:34:26',3,95),('620c96b8-696b-4242-8c8f-ceef96aa24b0','2021-01-09','02:23:32',2,89),('645842f2-4782-4cf6-aa1d-e1f85db6c3e4','2021-01-20','18:31:28',2,91),('64d3cab6-94ff-45f1-830f-5fcd18a1d89c','2021-01-17','23:14:20',17,160),('659aae3a-b424-4fbd-ab29-6feb903360ff','2021-01-28','07:47:49',5,111),('6e18ac1d-b285-4963-a59e-801e736d7d18','2021-01-13','02:19:26',6,117),('7096204e-af0f-42b9-8cb8-4a50e1db1e6c','2021-01-07','22:59:35',3,94),('7257569e-9bcf-41fb-bcd2-0bb5702c276d','2021-01-17','13:13:56',3,96),('735fceb7-d1f2-4494-91e8-adb44a84467d','2021-01-16','20:39:46',4,103),('770261eb-fc1c-44d6-9446-f949ecea50d0','2021-01-18','00:31:18',11,132),('77d89d19-03b2-45af-b0f1-7bb4f76df2a9','2021-01-11','17:54:43',4,100),('7bd1b94b-d77d-4d85-bc55-153b5b8b700e','2021-01-27','02:29:55',14,156),('7ccec620-99f6-4d20-9356-a3d7af6d3427','2021-01-18','03:33:09',3,97),('7ec307f3-8379-47cb-8faa-1af496fdb790','2021-01-14','13:13:17',11,131),('7ee668a6-534a-4dae-8ded-e62c513de4ca','2021-01-01','20:13:53',18,161),('81061d27-8fbc-43ac-9354-8562aaf12981','2021-01-20','19:19:28',4,104),('88b2e0af-e292-4a9c-8aea-a87483f3d1da','2021-01-23','23:38:40',12,144),('88c77912-3a35-4fc6-afe4-43ee5103e751','2021-01-06','08:01:31',19,168),('8b237879-baea-41ec-812e-e41b0e635072','2021-01-07','03:49:25',1,85),('91e20ad5-713c-4176-b436-efefcd40decb','2021-01-12','17:12:59',11,129),('931ee1fa-20fc-41c8-afbb-3ddf3ec403b1','2021-01-08','13:36:55',2,88),('955267f2-c6a0-438b-8986-9bd92210086f','2021-01-19','12:34:04',5,107),('98149f1c-956a-40c6-99f3-2777a10a02de','2021-01-02','05:00:08',4,98),('99e41fe2-cb38-43d5-b91b-d2bb40ed6cbb','2021-01-03','11:50:47',4,99),('9bba89db-0db9-4e2e-a9fa-ca7d269413c9','2021-01-15','15:10:28',18,164),('9c9c9a9e-20f6-4b27-b304-0b438d90589a','2021-01-07','12:20:58',13,147),('9e09a59f-84ee-4280-a95c-e0b1f024081b','2021-01-13','08:26:03',7,119),('9fc1c663-1833-465d-83b2-50ff163610e0','2021-01-27','19:47:28',13,152),('a3d03059-f253-4846-bffc-8e31505500b9','2021-01-25','02:46:28',5,109),('abc47815-428d-4f26-ae9b-37203d370dce','2021-01-18','16:37:03',15,157),('ae7f4230-1859-407c-929c-05c69c2ccb43','2021-01-19','16:44:30',18,166),('af174ffc-860f-4161-9110-616c5cf48c6d','2021-01-05','00:08:49',12,138),('bbf4bd33-5e25-4ef0-bc49-4ea7462ffca6','2021-01-25','09:01:09',5,110),('bd969ae3-11ea-4e84-8955-a1c63b7f280d','2021-01-13','16:06:08',11,130),('bdf19b3d-8f2b-465a-a496-9f619d054601','2021-01-24','19:38:39',8,123),('be3d61ca-d893-4d58-8946-7a3d064312ae','2021-01-24','06:42:24',6,118),('be4bd098-8742-4efc-83a3-df73941b8fb5','2021-01-02','12:07:07',12,137),('c1d717c3-65ee-4514-abaf-650e4a68ba60','2021-01-10','07:42:34',19,171),('c576c7c8-a050-4e88-b14c-1aede437004e','2021-01-04','04:43:21',6,114),('c583b12d-d565-4a8f-b779-9b0bcfad12cc','2021-01-17','14:48:26',18,165),('c615a562-da88-43d4-b784-775e74d44ead','2021-01-29','07:50:51',5,112),('c8d7d45f-9cbd-4ab0-8eaf-934e9c515065','2021-01-28','11:56:40',2,92),('cd6d4b53-9e6d-4e12-9cc6-3b8de2aed76a','2021-01-22','05:53:35',8,122),('cda5b708-1b87-4b65-aca2-9e30756cc31b','2021-01-19','20:53:57',9,125),('ce2c41f9-032c-496b-8498-9ae4bf22bdc3','2021-01-25','01:06:21',19,173),('cf2d0300-48b9-4312-ae18-70c3d7f87693','2021-01-11','18:31:44',12,140),('cf49db21-6bb1-4a5e-9b72-60b132656c6f','2021-01-09','16:28:11',6,115),('da819346-ab0b-41e5-8f1d-3699e9bca677','2021-01-27','16:46:47',19,174),('dcce5257-53d0-4650-8bb7-84464b2d6195','2021-01-25','07:12:20',11,135),('e113261f-f04a-45ba-8ff0-79a9116052c1','2021-01-13','18:56:13',18,163),('e4e5f831-b10f-4a7d-9de5-0c28d4185910','2021-01-08','07:34:26',13,148),('e8b2071b-a76c-4afd-bc17-2543a72960e2','2021-01-10','03:21:55',18,162),('eae8660c-4128-4e34-8508-0d680639718c','2021-01-10','18:21:02',8,120),('eaf55d99-7019-46a4-b0e7-f4738f243531','2021-01-25','16:06:27',12,145),('f110a487-0840-471e-9623-6172b775030c','2021-01-14','20:36:52',8,121),('f2c196be-54eb-44be-bfd3-baa195fd0c11','2021-01-11','00:00:08',2,90);
/*!40000 ALTER TABLE `purchase_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-02  8:45:47
