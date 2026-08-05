package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Car;

/**
 * Class Name: carDAO
 * Date: July 4, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Provides database access operations for car records in the car rental
 * desktop application. This class retrieves all cars, searches for cars
 * using a keyword, and finds a specific car by its database identifier.
 *
 * Important Functions:
 * getAllCars() retrieves every car from the database, searchCars() filters
 * cars using several vehicle attributes, and getCarById() retrieves one car.
 * mapResultSetToCar() converts a database row into a Car model object.
 *
 * Important Data Structures:
 * ArrayList and List are used to store collections of Car objects returned
 * from the database. PreparedStatement is used to safely execute parameterized
 * SQL queries.
 *
 * Algorithm:
 * Each method opens a database connection, prepares and executes an SQL query,
 * reads the ResultSet, converts matching rows into Car objects, and returns
 * the resulting object or list.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */


public class carDAO {

    /**
     * Retrieves all cars from the database.
     *
     * @return A list of all Car objects in the database
     */
    public List<Car> getAllCars() {
    List<Car> cars = new ArrayList<>();

    String sql = """
            SELECT CarID, CarBrand, CarModel, CarColor, CarYear, Price, Availability
            FROM Car
            ORDER BY CarBrand, CarModel
            """;

    System.out.println("Running getAllCars query...");

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
    ) {
        while (rs.next()) {
            cars.add(mapResultSetToCar(rs));
        }

        System.out.println("Cars loaded from database inside DAO: " + cars.size());

    } catch (SQLException e) {
        System.out.println("Database error while loading cars: " + e.getMessage());
        e.printStackTrace();
    }

    return cars;
}

/**
     * Searches for cars in the database that match the given keyword.
     * The search is performed on multiple car attributes, including brand,
     * model, color, year, price, and availability.
     *
     * @param keyword The search keyword to filter cars
     * @return A list of Car objects that match the search criteria
     */
    public List<Car> searchCars(String keyword) {
        List<Car> cars = new ArrayList<>();

        String sql = """
                SELECT CarID, CarBrand, CarModel, CarColor, CarYear, Price, Availability
                FROM Car
                WHERE CarBrand LIKE ?
                   OR CarModel LIKE ?
                   OR CarColor LIKE ?
                   OR CAST(CarYear AS CHAR) LIKE ?
                   OR CAST(Price AS CHAR) LIKE ?
                   OR Availability LIKE ?
                ORDER BY CarBrand, CarModel
                """;

        String searchKeyword = "%" + keyword + "%";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            stmt.setString(3, searchKeyword);
            stmt.setString(4, searchKeyword);
            stmt.setString(5, searchKeyword);
            stmt.setString(6, searchKeyword);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cars.add(mapResultSetToCar(rs));
                }
            }

            System.out.println("Cars found from search: " + cars.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    /**
     * Retrieves a car from the database by its unique identifier.
     *
     * @param carId The unique identifier for the car
     * @return The Car object if found, or null if not found
     */
    public Car getCarById(int carId) {
        String sql = """
                SELECT CarID, CarBrand, CarModel, CarColor, CarYear, Price, Availability
                FROM Car
                WHERE CarID = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, carId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCar(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Maps a ResultSet row to a Car object.
     *
     * @param rs The ResultSet containing car data
     * @return A Car object populated with data from the ResultSet
     * @throws SQLException If an SQL error occurs while accessing the ResultSet
     */
    private Car mapResultSetToCar(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setCarID(rs.getInt("CarID"));
        car.setCarBrand(rs.getString("CarBrand"));
        car.setCarModel(rs.getString("CarModel"));
        car.setCarColor(rs.getString("CarColor"));
        car.setCarYear(rs.getInt("CarYear"));
        car.setPrice(rs.getDouble("Price"));
        car.setAvailability(rs.getString("Availability"));

        return car;
    }
}