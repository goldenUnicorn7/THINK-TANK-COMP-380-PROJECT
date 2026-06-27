-- MySQL Workbench Forward Engineering - Created Database for Car rental Desktop application

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE,
SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP SCHEMA IF EXISTS `new_database`;
CREATE SCHEMA IF NOT EXISTS `new_database` DEFAULT CHARACTER SET utf8;
USE `new_database`;

-- -----------------------------------------------------
-- Table `Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Users` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `UserPhoneNum` VARCHAR(20) NOT NULL,
  `UserPassword` VARCHAR(45) NOT NULL,
  `UserEmail` VARCHAR(45) NOT NULL,
  `UserName` VARCHAR(45) NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE INDEX `UserEmail_UNIQUE` (`UserEmail`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Car` (
  `CarID` INT NOT NULL AUTO_INCREMENT,
  `CarBrand` VARCHAR(45) NOT NULL,
  `CarModel` VARCHAR(45) NOT NULL,
  `CarColor` VARCHAR(45) NOT NULL,
  `CarYear` INT NOT NULL,
  PRIMARY KEY (`CarID`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Bookings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bookings` (
  `BookingID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `CarID` INT NOT NULL,
  `pickup_Date` DATE NOT NULL,
  `return_Date` DATE NOT NULL,
  `Total_price` DECIMAL(10,2) NOT NULL,
  `Booking_Status` VARCHAR(45) NOT NULL,
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
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Pickup_Return`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pickup_Return` (
  `record_id` INT NOT NULL AUTO_INCREMENT,
  `BookingID` INT NOT NULL,
  `pickup_confirmed` VARCHAR(45) NOT NULL,
  `pickup_Date_Time` DATETIME NOT NULL,
  `return_Confirmed` VARCHAR(45) NOT NULL,
  `Extra_Charges` DECIMAL(10,2) NOT NULL,
  `return_Date_Time` DATETIME NOT NULL,
  PRIMARY KEY (`record_id`),

  INDEX `fk_Pickup_Return_Bookings_idx` (`BookingID`),

  CONSTRAINT `fk_Pickup_Return_Bookings`
    FOREIGN KEY (`BookingID`)
    REFERENCES `Bookings` (`BookingID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cart` (
  `CartID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `CarID` INT NOT NULL,
  `Return_Date` DATE NOT NULL,
  `Pickup_Date` DATE NOT NULL,
  `estimated_price` DECIMAL(10,2) NOT NULL,
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
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Car_Reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Car_Reviews` (
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
    ON UPDATE CASCADE
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;