-- =====================================================
-- CAR RENTAL DATABASE SCHEMA
-- WARNING: This script deletes and recreates all tables.
-- =====================================================

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET @OLD_SQL_MODE = @@SQL_MODE;

SET UNIQUE_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 0;

SET SQL_MODE =
'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,
ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- =====================================================
-- CREATE AND SELECT DATABASE
-- =====================================================

CREATE SCHEMA IF NOT EXISTS `new_database`
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE `new_database`;


-- =====================================================
-- REMOVE OLD TABLES
-- Tables are dropped in reverse dependency order.
-- =====================================================

DROP TABLE IF EXISTS `Car_Reviews`;
DROP TABLE IF EXISTS `Pickup_Return`;
DROP TABLE IF EXISTS `Cart`;
DROP TABLE IF EXISTS `Bookings`;
DROP TABLE IF EXISTS `Car`;
DROP TABLE IF EXISTS `Users`;


-- =====================================================
-- USERS TABLE
-- =====================================================

CREATE TABLE `Users` (
    `UserID` INT NOT NULL AUTO_INCREMENT,
    `UserPhoneNum` VARCHAR(20) NOT NULL,
    `UserPassword` VARCHAR(255) NOT NULL,
    `UserEmail` VARCHAR(100) NOT NULL,
    `UserName` VARCHAR(100) NULL,

    PRIMARY KEY (`UserID`),
    UNIQUE INDEX `UserEmail_UNIQUE` (`UserEmail`)
) ENGINE = InnoDB;


-- =====================================================
-- CAR TABLE
-- =====================================================

CREATE TABLE `Car` (
    `CarID` INT NOT NULL AUTO_INCREMENT,
    `CarBrand` VARCHAR(45) NOT NULL,
    `CarModel` VARCHAR(45) NOT NULL,
    `CarColor` VARCHAR(45) NOT NULL,
    `CarYear` INT NOT NULL,
    `Price` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    `Availability` VARCHAR(45) NOT NULL DEFAULT 'Available',

    PRIMARY KEY (`CarID`)
) ENGINE = InnoDB;


-- =====================================================
-- BOOKINGS TABLE
-- =====================================================

// The Bookings table stores information about car bookings made by users. 
//It includes foreign keys to the Users and Car tables, as well as constraints to ensure data integrity.
CREATE TABLE `Bookings` (
    `BookingID` INT NOT NULL AUTO_INCREMENT,
    `UserID` INT NOT NULL,
    `CarID` INT NOT NULL,
    `pickup_Date` DATE NOT NULL,
    `return_Date` DATE NOT NULL,
    `Total_price` DECIMAL(10,2) NOT NULL,
    `Booking_Status` VARCHAR(45) NOT NULL DEFAULT 'Pending',

// The Booking_Status column indicates the current status of the booking, such as 'Pending', 'Confirmed', or 'Cancelled'.
    PRIMARY KEY (`BookingID`),

    INDEX `fk_Bookings_Users_idx` (`UserID`),
    INDEX `fk_Bookings_Car_idx` (`CarID`),

    CONSTRAINT `fk_Bookings_Users`
        FOREIGN KEY (`UserID`)
        REFERENCES `Users` (`UserID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `fk_Bookings_Car`
        FOREIGN KEY (`CarID`)
        REFERENCES `Car` (`CarID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `chk_Bookings_Dates`
        CHECK (`return_Date` >= `pickup_Date`),

    CONSTRAINT `chk_Bookings_TotalPrice`
        CHECK (`Total_price` >= 0)
) ENGINE = InnoDB;


-- =====================================================
-- PICKUP AND RETURN TABLE
-- =====================================================

// The Pickup_Return table stores information about the pickup and return of cars for each booking.
CREATE TABLE `Pickup_Return` (
    `record_id` INT NOT NULL AUTO_INCREMENT,
    `BookingID` INT NOT NULL,
    `pickup_confirmed` VARCHAR(45) NOT NULL DEFAULT 'No',
    `pickup_Date_Time` DATETIME NULL,
    `return_Confirmed` VARCHAR(45) NOT NULL DEFAULT 'No',
    `Extra_Charges` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    `return_Date_Time` DATETIME NULL,

// The pickup_confirmed and return_Confirmed columns indicate whether the pickup and return have been confirmed, respectively.
    PRIMARY KEY (`record_id`),

    INDEX `fk_Pickup_Return_Bookings_idx` (`BookingID`),

    CONSTRAINT `fk_Pickup_Return_Bookings`
        FOREIGN KEY (`BookingID`)
        REFERENCES `Bookings` (`BookingID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `chk_Pickup_Return_ExtraCharges`
        CHECK (`Extra_Charges` >= 0)
) ENGINE = InnoDB;


-- =====================================================
-- CART TABLE
-- These column names match your Java INSERT statement:
--
-- INSERT INTO Cart
-- (UserID, CarID, Return_Date, Pickup_Date, estimated_price)
-- VALUES (?, ?, ?, ?, ?)
-- =====================================================

// The Cart table stores information about the items in a user's shopping cart, including the user ID, car ID, pickup and return dates, and estimated price.
CREATE TABLE `Cart` (
    `CartID` INT NOT NULL AUTO_INCREMENT,
    `UserID` INT NOT NULL,
    `CarID` INT NOT NULL,
    `Return_Date` DATE NOT NULL,
    `Pickup_Date` DATE NOT NULL,
    `estimated_price` DECIMAL(10,2) NOT NULL,

// The estimated_price column represents the estimated cost of the rental based on the selected car and rental duration.
    PRIMARY KEY (`CartID`),

    INDEX `fk_Cart_Users_idx` (`UserID`),
    INDEX `fk_Cart_Car_idx` (`CarID`),

    CONSTRAINT `fk_Cart_Users`
        FOREIGN KEY (`UserID`)
        REFERENCES `Users` (`UserID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `fk_Cart_Car`
        FOREIGN KEY (`CarID`)
        REFERENCES `Car` (`CarID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `chk_Cart_Dates`
        CHECK (`Return_Date` >= `Pickup_Date`),

    CONSTRAINT `chk_Cart_EstimatedPrice`
        CHECK (`estimated_price` >= 0)
) ENGINE = InnoDB;


-- =====================================================
-- CAR REVIEWS TABLE
-- =====================================================

// The Car_Reviews table stores reviews submitted by users for the cars they have rented.
CREATE TABLE `Car_Reviews` (
    `ReviewID` INT NOT NULL AUTO_INCREMENT,
    `UserID` INT NOT NULL,
    `CarID` INT NOT NULL,
    `BookingID` INT NOT NULL,
    `Rating` INT NOT NULL,
    `Comments` VARCHAR(255) NULL,
    `Review_Date` DATE NOT NULL,

    PRIMARY KEY (`ReviewID`),

    INDEX `fk_Car_Reviews_Users_idx` (`UserID`),
    INDEX `fk_Car_Reviews_Car_idx` (`CarID`),
    INDEX `fk_Car_Reviews_Bookings_idx` (`BookingID`),

    CONSTRAINT `fk_Car_Reviews_Users`
        FOREIGN KEY (`UserID`)
        REFERENCES `Users` (`UserID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `fk_Car_Reviews_Car`
        FOREIGN KEY (`CarID`)
        REFERENCES `Car` (`CarID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `fk_Car_Reviews_Bookings`
        FOREIGN KEY (`BookingID`)
        REFERENCES `Bookings` (`BookingID`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT `chk_Car_Reviews_Rating`
        CHECK (`Rating` BETWEEN 1 AND 5)
) ENGINE = InnoDB;


-- =====================================================
-- RESTORE MYSQL SETTINGS
-- =====================================================

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;


-- =====================================================
-- VERIFY TABLES
-- =====================================================

SHOW TABLES;

DESCRIBE `Users`;
DESCRIBE `Car`;
DESCRIBE `Bookings`;
DESCRIBE `Pickup_Return`;
DESCRIBE `Cart`;
DESCRIBE `Car_Reviews`;