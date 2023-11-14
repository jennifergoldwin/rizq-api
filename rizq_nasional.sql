-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for rizq_nasional
CREATE DATABASE IF NOT EXISTS `rizq_nasional` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `rizq_nasional`;

-- Dumping structure for table rizq_nasional.investment_table
CREATE TABLE IF NOT EXISTS `investment_table` (
  `id` varchar(10) NOT NULL,
  `userIdentityNumber` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `dateWithdrawl` date NOT NULL,
  `planId` varchar(255) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `statusPlan` varchar(20) NOT NULL,
  `statusWithdrawal` varchar(20) NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table rizq_nasional.investment_table: ~12 rows (approximately)
-- INSERT INTO `investment_table` (`code`, `userIdentityNumber`, `date`, `tenure`, `plan`, `interest`, `amount`, `statusPlan`, `statusWithdrawal`) VALUES
-- 	('INV001', '123456789', '2023-10-21', 100, 'Premium', 7.00, 500.00, 'On Going', 'true'),
-- 	('INV003', '123456789', '2023-10-22', 100, 'Premium', 8.50, 500.00, 'On Going', 'false'),
-- 	('INV004', '123456789', '2023-10-22', 100, 'Premium', 6.00, 500.00, 'On Going', 'false'),
-- 	('INV005', '123456789', '2023-10-22', 100, 'Premium', 6.00, 500.00, 'On Going', 'true'),
-- 	('INV006', '123456789', '2023-10-22', 100, 'Premium', 6.00, 500.00, 'On Going', 'false'),
-- 	('INV007', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'true'),
-- 	('INV008', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'false'),
-- 	('INV009', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'true'),
-- 	('INV010', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'false'),
-- 	('INV011', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'false'),
-- 	('INV012', '123456789', '2023-10-22', 100, 'Premium', 5.50, 500.00, 'On Going', 'false'),
-- 	('INV002', '123456789', '2023-10-22', 100, 'Premium', 8.50, 500.00, 'On Going', 'false');

-- Dumping structure for table rizq_nasional.stockholding
CREATE TABLE IF NOT EXISTS `stockholding` (
  `id` varchar(100) NOT NULL,
  `userIdentityNumber` varchar(100) NOT NULL,
  `stockId` varchar(100) NOT NULL,
  `investmentId` varchar(100) NOT NULL,
  `purchasedPrice` int(11) NOT NULL,
  `purchasedDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table rizq_nasional.stockholding: ~12 rows (approximately)
-- INSERT INTO `stockholding` (`id`, `userIdentityNumber`, `stockId`, `investmentId`, `purchasedPrice`, `purchasedDate`) VALUES
-- 	('SH2361101', '123456789', 'GLD', 'INV001', 12000, '2023-11-06'),
-- 	('SH236118122', '123456789', 'GLD', 'INV002', 12000, '2023-11-06'),
-- 	('SH236118166', '123456789', 'GLD', 'INV003', 12000, '2023-11-06'),
-- 	('SH2380908', '123456789', 'IE', 'INV004', 5000, '2023-09-08'),
-- 	('SH238090822', '123456789', 'IE', 'INV005', 5000, '2023-09-08'),
-- 	('SH238090866', '123456789', 'IE', 'INV006', 5000, '2023-09-08'),
-- 	('SH2381001', '123456789', 'CRYPT', 'INV007', 10000, '2023-10-08'),
-- 	('SH238108166', '123456789', 'CRYPT', 'INV008', 9000, '2023-10-08'),
-- 	('SH2381091', '123456789', 'CRYPT', 'INV009', 10000, '2023-10-08'),
-- 	('SH238109122', '123456789', 'CRYPT', 'INV010', 10000, '2023-10-08'),
-- 	('SH238109166', '123456789', 'CRYPT', 'INV011', 10000, '2022-10-08'),
-- 	('SH2381101', '123456789', 'FX', 'INV012', 4000, '2023-11-08');

-- Dumping structure for table rizq_nasional.stocks
CREATE TABLE IF NOT EXISTS `stocks` (
  `id` varchar(100) NOT NULL,
  `stockName` varchar(100) NOT NULL,
  `currPrice` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table rizq_nasional.stocks: ~4 rows (approximately)
-- INSERT INTO `stocks` (`id`, `stockName`, `currPrice`) VALUES
-- 	('CRYPT', 'CRYPTO', 15000),
-- 	('FX', 'FOREX', 5000),
-- 	('GLD', 'GOLD', 13000),
-- 	('IE', 'INTERNATIONAL EQUITIES', 8000);

CREATE TABLE IF NOT EXISTS `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  `createdby` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
)

-- Dumping structure for table rizq_nasional.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(100) NOT NULL,
  `identityNumber` varchar(100) NOT NULL,
  `phoneNumber` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `postCode` varchar(100) NOT NULL,
  `occupation` varchar(100) NOT NULL,
  `bankName` varchar(100) NOT NULL,
  `bankAccountNumber` varchar(100) NOT NULL,
  `bankHolderName` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  `createdby` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table rizq_nasional.users: ~2 rows (approximately)
INSERT INTO `users` (`id`, `fullName`, `identityNumber`, `phoneNumber`, `email`, `state`, `city`, `address`, `postCode`, `occupation`, `bankName`, `bankAccountNumber`, `bankHolderName`, `password`, `role`) VALUES
	(1, 'Jessica', '123456789', '+6281377036883', 'jessica@gmail.com', 'Selangor', 'Kuala Lumpur', '10AB Gudng', '123', 'Manager', 'UOB', '12228922', 'Jessica', '$2a$10$VgCfGHKo6i572GeEA069Rerh1o3g3wlbDDa6bt4uoNUIIDFEyQ/Je', 'ROLE_USER'),
	(2, 'Sandra', '111111111', '+6281377036883', 'sandra@gmail.com', 'Selangor', 'Kuala Lumpur', '10AB Gudng', '123', 'Manager', 'UOB', '12228922', 'Jessica', '$2a$10$9AegNOqMV2DOghGoDCFlp.iY28ibZC.fYV8Yi6gWwzfQksmpVMz9y', 'ROLE_USER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
