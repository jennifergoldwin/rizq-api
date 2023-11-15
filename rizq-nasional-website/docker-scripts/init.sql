CREATE TABLE IF NOT EXISTS `investment` (
  `id` varchar(10) NOT NULL,
  `userIdentityNumber` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `dateWithdrawl` date NOT NULL,
  `planId` varchar(255) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `statusPlan` varchar(20) NOT NULL,
  `statusWithdrawal` varchar(20) NOT NULL,
  PRIMARY KEY(`id`)
)


CREATE TABLE IF NOT EXISTS `stockholding` (
  `id` varchar(100) NOT NULL,
  `userIdentityNumber` varchar(100) NOT NULL,
  `investmentId` varchar(100) NOT NULL,
  `stockId` varchar(100) NOT NULL,
  `purchasedPrice` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  `purchasedDate` date NOT NULL,
  PRIMARY KEY (`id`)
)


CREATE TABLE IF NOT EXISTS `stocks` (
  `id` varchar(100) NOT NULL,
  `stockName` varchar(100) NOT NULL,
  `currPrice` int(11) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE IF NOT EXISTS `plan`(
  `id` varchar(100) NOT NULL,
  `planType` varchar(100) NOT NULL,
  `interest` double NOT NULL,
  `tenure` int NOT NULL,
  `price` int NOT NULL
  PRIMARY KEY(`id`)
)

INSERT INTO `plan` (`id`, `planType`, `interest`, `tenure`) VALUES ('BS001', 'Basic', '0.3', '100','50');
INSERT INTO `plan` (`id`, `planType`, `interest`, `tenure`) VALUES ('ST001', 'Standard', '0.4', '200','100');
INSERT INTO `plan` (`id`, `planType`, `interest`, `tenure`) VALUES ('PR001', 'Premium', '0.5', '300','200');


CREATE TABLE IF NOT EXISTS `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  `createdby` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
)


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
)