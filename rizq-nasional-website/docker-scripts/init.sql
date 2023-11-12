CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(100) NOT NULL,
    identityNumber VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    `state` VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    `address`  VARCHAR(100) NOT NULL,
    postCode VARCHAR(100) NOT NULL,
    occupation VARCHAR(100) NOT NULL,
    bankName VARCHAR(100) NOT NULL,
    bankAccountNumber VARCHAR(100) NOT NULL,
    bankHolderName VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `role` VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS stocks (
    id VARCHAR(100) PRIMARY KEY,
    stockName VARCHAR(100) NOT NULL,
    currPrice INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS stockHolding (
    id VARCHAR(100) PRIMARY KEY,
    userIdentityNumber VARCHAR(100) NOT NULL,
    stockId VARCHAR(100) NOT NULL,
    purchasedPrice INTEGER NOT NULL,
    purchasedDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS `investment_table` (
  `code` varchar(10) NOT NULL,
  `userIdentityNumber` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `tenure` int(11) NOT NULL,
  `plan` varchar(255) NOT NULL,
  `interest` decimal(10,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `statusPlan` varchar(20) NOT NULL,
  `statusWithdrawal` varchar(20) NOT NULL
);

-- dummydata
INSERT INTO `users` (`id`, `fullName`, `identityNumber`, `phoneNumber`, `email`, `state`, `city`, `address`, `postCode`, `occupation`, `bankName`, `bankAccountNumber`, `bankHolderName`, `password`, `role`) VALUES
	(1, 'Jessica', '123456789', '+6281377036883', 'jessica@gmail.com', 'Selangor', 'Kuala Lumpur', '10AB Gudng', '123', 'Manager', 'UOB', '12228922', 'Jessica', '$2a$10$VgCfGHKo6i572GeEA069Rerh1o3g3wlbDDa6bt4uoNUIIDFEyQ/Je', 'ROLE_USER'),
	(2, 'Sandra', '111111111', '+6281377036883', 'sandra@gmail.com', 'Selangor', 'Kuala Lumpur', '10AB Gudng', '123', 'Manager', 'UOB', '12228922', 'Jessica', '$2a$10$9AegNOqMV2DOghGoDCFlp.iY28ibZC.fYV8Yi6gWwzfQksmpVMz9y', 'ROLE_USER');

INSERT INTO `stocks` (`id`, `stockName`, `currPrice`) VALUES
	('CRYPT', 'CRYPTO', 15000),
	('FX', 'FOREX', 5000),
	('GLD', 'GOLD', 13000),
	('IE', 'INTERNATIONAL EQUITIES', 8000);

INSERT INTO `stockholding` (`id`, `userIdentityNumber`, `stockId`, `investmentId`, `purchasedPrice`, `purchasedDate`) VALUES
	('SH2361101', '123456789', 'GLD', 'INV001', 12000, '2023-11-06'),
	('SH236118122', '123456789', 'GLD', 'INV002', 12000, '2023-11-06'),
	('SH236118166', '123456789', 'GLD', 'INV003', 12000, '2023-11-06'),
	('SH2380908', '123456789', 'IE', 'INV004', 5000, '2023-09-08'),
	('SH238090822', '123456789', 'IE', 'INV005', 5000, '2023-09-08'),
	('SH238090866', '123456789', 'IE', 'INV006', 5000, '2023-09-08'),
	('SH2381001', '123456789', 'CRYPT', 'INV007', 10000, '2023-10-08'),
	('SH238108166', '123456789', 'CRYPT', 'INV008', 9000, '2023-10-08'),
	('SH2381091', '123456789', 'CRYPT', 'INV009', 10000, '2023-10-08'),
	('SH238109122', '123456789', 'CRYPT', 'INV010', 10000, '2023-10-08'),
	('SH238109166', '123456789', 'CRYPT', 'INV011', 10000, '2022-10-08'),
	('SH2381101', '123456789', 'FX', 'INV012', 4000, '2023-11-08');


INSERT INTO `investment_table` (`code`, `userIdentityNumber`, `date`, `tenure`, `plan`, `interest`, `amount`, `statusPlan`, `statusWithdrawal`) VALUES
	('INV001', '123456789', '2023-10-21', 100, 'Premium', 7.00, 500.00, 'On Going', 'true'),
	('INV003', '123456789', '2023-10-22', 100, 'Premium', 8.50, 500.00, 'On Going', 'false'),
	('INV004', '123456789', '2023-10-22', 100, 'Premium', 6.00, 500.00, 'On Going', 'false'),
	('INV005', '123456789', '2023-10-22', 100, 'Premium', 6.00, 500.00, 'On Going', 'true'),
	('INV006', '123456789', '2023-10-22', 100, 'Premium', 6.00, 500.00, 'On Going', 'false'),
	('INV007', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'true'),
	('INV008', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'false'),
	('INV009', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'true'),
	('INV010', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'false'),
	('INV011', '123456789', '2023-10-22', 100, 'Premium', 7.00, 500.00, 'On Going', 'false'),
	('INV012', '123456789', '2023-10-22', 100, 'Premium', 5.50, 500.00, 'On Going', 'false'),
	('INV002', '123456789', '2023-10-22', 100, 'Premium', 8.50, 500.00, 'On Going', 'false');

