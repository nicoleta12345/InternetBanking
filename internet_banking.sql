CREATE DATABASE  IF NOT EXISTS `internet_banking` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `internet_banking`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: internet_banking
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `accountNumber` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'123',115.5),(2,'124',193);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `postalCode` bigint(20) DEFAULT NULL,
  `streetName` varchar(255) DEFAULT NULL,
  `streetNumber` int(11) NOT NULL,
  `town` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,123,'Strada Merilor',21,'Craiova');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_deposit`
--

DROP TABLE IF EXISTS `transaction_deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_deposit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` double NOT NULL,
  `receiver_idAccount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7F3DD23DC21E8032` (`receiver_idAccount`),
  CONSTRAINT `FK7F3DD23DC21E8032` FOREIGN KEY (`receiver_idAccount`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_deposit`
--

LOCK TABLES `transaction_deposit` WRITE;
/*!40000 ALTER TABLE `transaction_deposit` DISABLE KEYS */;
INSERT INTO `transaction_deposit` VALUES (1,20,1);
/*!40000 ALTER TABLE `transaction_deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_transfer`
--

DROP TABLE IF EXISTS `transaction_transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` double NOT NULL,
  `receiver_idAccount` int(11) DEFAULT NULL,
  `sender_idAccount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7C99702CC21E8032` (`receiver_idAccount`),
  KEY `FK7C99702C235F90F8` (`sender_idAccount`),
  CONSTRAINT `FK7C99702C235F90F8` FOREIGN KEY (`sender_idAccount`) REFERENCES `account` (`accountId`),
  CONSTRAINT `FK7C99702CC21E8032` FOREIGN KEY (`receiver_idAccount`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_transfer`
--

LOCK TABLES `transaction_transfer` WRITE;
/*!40000 ALTER TABLE `transaction_transfer` DISABLE KEYS */;
INSERT INTO `transaction_transfer` VALUES (1,3,2,1),(2,10,2,1),(3,20,1,2);
/*!40000 ALTER TABLE `transaction_transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_withdrawal`
--

DROP TABLE IF EXISTS `transaction_withdrawal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_withdrawal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` double NOT NULL,
  `sender_idAccount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC1D228B6235F90F8` (`sender_idAccount`),
  CONSTRAINT `FKC1D228B6235F90F8` FOREIGN KEY (`sender_idAccount`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_withdrawal`
--

LOCK TABLES `transaction_withdrawal` WRITE;
/*!40000 ALTER TABLE `transaction_withdrawal` DISABLE KEYS */;
INSERT INTO `transaction_withdrawal` VALUES (1,12,1);
/*!40000 ALTER TABLE `transaction_withdrawal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `userCode` varchar(255) NOT NULL,
  `userDetails_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36EBCBDF704C5D` (`userDetails_Id`),
  CONSTRAINT `FK36EBCBDF704C5D` FOREIGN KEY (`userDetails_Id`) REFERENCES `userdetails` (`userDetailsId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'pass','456',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `user_Id` int(11) NOT NULL,
  `account_Id` int(11) NOT NULL,
  UNIQUE KEY `account_Id` (`account_Id`),
  KEY `FK14C321B9D6E17E3D` (`account_Id`),
  KEY `FK14C321B9375ADD17` (`user_Id`),
  CONSTRAINT `FK14C321B9375ADD17` FOREIGN KEY (`user_Id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK14C321B9D6E17E3D` FOREIGN KEY (`account_Id`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetails` (
  `userDetailsId` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `cnp` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `adress_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`userDetailsId`),
  KEY `FK4ADAD3F7BD52B1B` (`adress_Id`),
  CONSTRAINT `FK4ADAD3F7BD52B1B` FOREIGN KEY (`adress_Id`) REFERENCES `address` (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetails`
--

LOCK TABLES `userdetails` WRITE;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` VALUES (1,22,'12345678901234','lala@yahoo.com','Ana','Popescu',1);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-17 10:02:16
