package backend.dao;

import backend.db.DBConnection;
import backend.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: cartDAO
 * Date: July 4, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Provides database access operations for the shopping cart in the car rental
 * desktop application. This class allows users to add cars to their cart,
 * retrieve cart contents, remove individual cars, and clear the entire cart.
 *
 * Important Functions:
 * addToCart() inserts a selected car into the user's cart, getCartCars()
 * retrieves all cars currently stored in the cart, removeFromCart() deletes
 * one selected car, and clearCart() removes all cart items for a user.
 *
 * Important Data Structures:
 * ArrayList and List are used to store Car objects retrieved from the database.
 * PreparedStatement is used to execute parameterized SQL queries safely.
 *
 * Algorithm:
 * Each method connects to the database, prepares an SQL statement, supplies
 * the required user and car identifiers, executes the operation, and returns
 * either a success value or a list of matching Car objects.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class cartDAO {

    /**
     * Adds a car to the user's shopping cart in the database.
     *
     * @param userId The unique identifier for the user
     * @param carId  The unique identifier for the car to add
     * @return true if the car was successfully added, false otherwise
     */
    public boolean addToCart(int userId, int carId) {
        String sql = """
        INSERT INTO Cart (UserID, CarID, Pickup_Date, Return_Date, estimated_price)
        SELECT ?, CarID, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 DAY), Price
        FROM Car
        WHERE CarID = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, carId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding car to cart.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all cars currently in the user's shopping cart from the database.
     *
     * @param userId The unique identifier for the user
     * @return A list of Car objects representing the cars in the user's cart
     */
    public List<Car> getCartCars(int userId) {
        List<Car> cars = new ArrayList<>();

        String sql = """
                SELECT c.CarID,
                       c.CarBrand,
                       c.CarModel,
                       c.CarColor,
                       c.CarYear,
                       c.Price,
                       c.Availability
                FROM cart ct
                JOIN car c ON ct.CarID = c.CarID
                WHERE ct.UserID = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getInt("CarID"),
                            rs.getString("CarBrand"),
                            rs.getString("CarModel"),
                            rs.getString("CarColor"),
                            rs.getInt("CarYear"),
                            rs.getDouble("Price"),
                            rs.getString("Availability")
                    );

                    cars.add(car);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error loading cart cars.");
            e.printStackTrace();
        }

        return cars;
    }

    /**
     * Removes a car from the user's shopping cart in the database.
     *
     * @param userId The unique identifier for the user
     * @param carId  The unique identifier for the car to remove
     * @return true if the car was successfully removed, false otherwise
     */
    public boolean removeFromCart(int userId, int carId) {
        String sql = "DELETE FROM cart WHERE UserID = ? AND CarID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, carId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Error removing car from cart.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Clears all cars from the user's shopping cart in the database.
     *
     * @param userId The unique identifier for the user
     * @return true if the cart was successfully cleared, false otherwise
     */
    public boolean clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE UserID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error clearing cart.");
            e.printStackTrace();
            return false;
        }
    }
}