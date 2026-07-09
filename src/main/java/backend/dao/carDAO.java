package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Car;

public class carDAO {

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