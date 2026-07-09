USE new_database;

INSERT INTO Users (UserPhoneNum, UserPassword, UserEmail, UserName)
VALUES
('1234567890', '1234', 'test@example.com', 'Test User')
ON DUPLICATE KEY UPDATE
UserPhoneNum = VALUES(UserPhoneNum),
UserPassword = VALUES(UserPassword),
UserName = VALUES(UserName);

INSERT INTO Car (CarID, CarBrand, CarModel, CarColor, CarYear, Price, Availability)
VALUES
(1, 'Toyota', 'Camry', 'White', 2022, 50.00, 'Available'),
(2, 'Honda', 'Civic', 'Black', 2021, 45.00, 'Available'),
(3, 'Tesla', 'Model 3', 'Red', 2023, 90.00, 'Available'),
(4, 'Ford', 'Mustang', 'Blue', 2020, 85.00, 'Available')
ON DUPLICATE KEY UPDATE
CarBrand = VALUES(CarBrand),
CarModel = VALUES(CarModel),
CarColor = VALUES(CarColor),
CarYear = VALUES(CarYear),
Price = VALUES(Price),
Availability = VALUES(Availability);

SELECT CarID, CarBrand, CarModel, Price, Availability
FROM Car;