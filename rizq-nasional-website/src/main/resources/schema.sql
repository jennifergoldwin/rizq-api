CREATE TABLE IF NOT EXISTS `users`(
    `id`  INTEGER PRIMARY KEY AUTO_INCREMENT,
    `fullName` VARCHAR(100) NOT NULL,
    `identityNumber` VARCHAR(100) NOT NULL,
    `phoneNumber` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `state` VARCHAR(100) NOT NULL,
    `city` VARCHAR(100) NOT NULL,
    `address`  VARCHAR(100) NOT NULL,
    `postCode` VARCHAR(100) NOT NULL,
    `occupation` VARCHAR(100) NOT NULL,
    `bankName` VARCHAR(100) NOT NULL,
    `bankAccountNumber` VARCHAR(100) NOT NULL,
    `bankHolderName` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `role` VARCHAR(100) NOT NULL
);

-- CREATE TABLE IF NOT EXISTS `stocks` (
--     `id` VARCHAR(100) PRIMARY KEY,
--     `stockName` VARCHAR(100) NOT NULL,
--     `currPrice` INTEGER NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS `stockHolding` (
--     `id` VARCHAR(100) PRIMARY KEY,
--     `userIdentityNumber` VARCHAR(100) NOT NULL,
--     `stockId` VARCHAR(100) NOT NULL,
--     `purchasedPrice` INTEGER NOT NULL,
--     `purchasedDate` DATE NOT NULL,
--     FOREIGN KEY (`userIdentityNumber`) REFERENCES `users`(`identityNumber`),
--     FOREIGN KEY (`stockId`) REFERENCES `stocks`(`id`)
-- );

-- CREATE TABLE IF NOT EXISTS `stockHolding` (
--     `id` VARCHAR(100) PRIMARY KEY,
--     `userIdentityNumber` VARCHAR(100) NOT NULL,
--     `stockId` VARCHAR(100) NOT NULL,
--     `purchasedPrice` INTEGER NOT NULL,
--     `purchasedDate` DATE NOT NULL,
-- );

-- SELECT u.fullName AS UserName, sh.userIdentityNumber AS UserID, DATE_FORMAT(sh.purchasedDate, '%Y-%m') AS Month, SUM(s.currPrice - sh.purchasedPrice) AS InvestmentGrowth FROM users u INNER JOIN stockHolding sh ON u.identityNumber = sh.userIdentityNumber INNER JOIN stocks s ON sh.stockId = s.id GROUP BY UserName, UserID, Month ORDER BY UserName, Month; 