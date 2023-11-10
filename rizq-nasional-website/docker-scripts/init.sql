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

