package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Car;

public class cartDAO {

    public boolean addToCart(int userId, int carId) {
        System.out.println("NEW addToCart METHOD IS RUNNING");

        String sql =  """
            INSERT INTO Cart (UserID, CarID, Return_Date, Pickup_Date, estimated_price)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, carId);

             // Temporary default dates so the cart insert works
        stmt.setDate(3, java.sql.Date.valueOf("2026-07-20")); // Return_Date
        stmt.setDate(4, java.sql.Date.valueOf("2026-07-15")); // Pickup_Date

        // Temporary estimated price
        stmt.setDouble(5, 0.00);

        System.out.println("SQL being used: " + sql);

         System.out.println("Running new cart insert with dates and estimated price..."); 


            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding car to cart.");
            e.printStackTrace();
            return false;
        }
    }

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
                FROM Cart ct
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