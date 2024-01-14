-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2023 at 07:40 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rizq_nasional`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `fullName` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  `createdby` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `admins` (`id`, `fullName`, `username`, `password`, `role`, `createdby`) VALUES
(1, 'Eric Chandra', 'erichan', '$2a$10$QJezNId44Z5rjIQqvL022exMrNqG7HJefqM9NtEb3Y7.d9fJNKFgy', 'ROLE_MASTER_ADMIN', 'default'),
(2, 'Richard', 'richard', '$2a$10$GyjIj6vCDuZHJV5p7O/I2uTaeE4brhkmATSa.UMDJhQ37iXSlwgKi', 'ROLE_MASTER_ADMIN', 'default');


CREATE TABLE `investment` (
  `id` varchar(10) NOT NULL,
  `userIdentityNumber` varchar(20) NOT NULL,
  `dateDeposit` date NOT NULL,
  `dateWithdrawal` date DEFAULT NULL,
  `totalDeposit` double NOT NULL,
  `totalProfit` double NOT NULL,
  `statusDeposit` varchar(20) NOT NULL,
  `statusWithdrawal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `plan` (
  `id` varchar(100) NOT NULL,
  `planType` varchar(100) NOT NULL,
  `interest` double NOT NULL,
  `tenure` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `plan` (`id`, `planType`, `interest`, `tenure`, `price`) VALUES
('BS001', 'Basic', 0.3, 100, 50),
('PR001', 'Premium', 0.5, 300, 100),
('ST001', 'Standard', 0.4, 200, 200);

CREATE TABLE `statement`(
  `id` varchar(10) NOT NULL,
  `userIdentityNumber` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `product` varchar(20) NOT NULL,
  `leverage` varchar(20) NOT NULL,
  `profitLoss` varchar(20) NOT NULL
)

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
  `createdby` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `investment`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `plan`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;


