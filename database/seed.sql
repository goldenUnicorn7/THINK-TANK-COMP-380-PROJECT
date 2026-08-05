USE new_database;


-- =====================================================
-- TEST USER
-- =====================================================

// The Users table stores information about registered users, including their phone number, password, email, and name.
INSERT INTO Users (
    UserPhoneNum,
    UserPassword,
    UserEmail,
    UserName
)

// The ON DUPLICATE KEY UPDATE clause ensures that if a user with the same email already exists, their information will be updated instead of creating a duplicate entry.
VALUES (
    '1234567890',
    '1234',
    'test@example.com',
    'Test User'
)

// The ON DUPLICATE KEY UPDATE clause allows for updating specific columns of an existing record if a duplicate key is found, ensuring that the user's information remains current.
ON DUPLICATE KEY UPDATE
    UserPhoneNum = '1234567890',
    UserPassword = '1234',
    UserName = 'Test User';


-- =====================================================
-- TEST CARS
-- =====================================================

INSERT INTO Car (
    CarID,
    CarBrand,
    CarModel,
    CarColor,
    CarYear,
    Price,
    Availability
)
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


-- =====================================================
-- VERIFY INSERTED DATA
-- =====================================================

SELECT
    UserID,
    UserPhoneNum,
    UserEmail,
    UserName
FROM Users;

SELECT
    CarID,
    CarBrand,
    CarModel,
    CarColor,
    CarYear,
    Price,
    Availability
FROM Car;