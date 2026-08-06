package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import backend.db.DBConnection;
import backend.model.PickupReturn;

/**
 * Class Name: PickupReturnDAO
 * Date: July 4, 2026
 * Programmer: Carla Garcia
 *
 * Description:
 * Provides database access operations for pickup and return records in the car rental desktop application.
 * This class handles inserting, retrieving, updating, and deleting pickup and return records.
 *
 * Important Functions:
 * insertPickupReturn() inserts a new pickup and return record into the database.
 * getPickupReturnByBookingId() retrieves a pickup and return record by its associated booking ID.
 * updatePickupReturn() updates an existing pickup and return record in the database.
 * deletePickupReturnByBookingId() deletes a pickup and return record based on the associated booking ID.
 *
 * Important Data Structures:
 * The PickupReturn model class stores pickup and return information retrieved from or sent to the database.
 * PreparedStatement is used to safely execute parameterized SQL queries.
 *
 * Algorithm:
 * Each method opens a database connection, prepares an SQL statement, assigns the required parameters,
 * executes the query or update, and returns the resulting PickupReturn object or success value. 
 * PreparedStatement was used to prevent SQL injection attacks and ensure safe database operations.
 *
 * @author Carla Garcia
 * @version 1.0
 */

/**
 * Data Access Object (DAO) class for managing pickup and return records in the database.
 */
public class PickupReturnDAO {

    /**
     * Constructor for the PickupReturnDAO class. Initializes a new instance of the PickupReturnDAO.
     */
    public PickupReturnDAO() {
    }

    /**
     * Inserts a new pickup and return record into the database.
     * @param pickupReturn The PickupReturn object containing the record details.
     * @return true if the record was successfully inserted, false otherwise.
     */
    public boolean insertPickupReturn(PickupReturn pickupReturn) {

        String sql = "INSERT INTO Pickup_Return " +
                "(BookingID, pickup_confirmed, pickup_Date_Time, return_Date_Time, return_Confirmed, Extra_Charges) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pickupReturn.getBookingId());
            stmt.setString(2, pickupReturn.getPickupConfirmed());
            stmt.setTimestamp(3, Timestamp.valueOf(pickupReturn.getPickupDateTime()));
            stmt.setTimestamp(4, Timestamp.valueOf(pickupReturn.getReturnDateTime()));
            stmt.setString(5, pickupReturn.getReturnConfirmed());
            stmt.setBigDecimal(6, pickupReturn.getExtraCharges());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Insert PickupReturn database error.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a pickup and return record by its associated booking ID from the database.
     * @param bookingId The ID of the booking for which to retrieve pickup and return records.
     * @return The PickupReturn object if found, null otherwise.
     */
    public PickupReturn getPickupReturnByBookingId(int bookingId) {

        String sql = "SELECT * FROM Pickup_Return WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new PickupReturn(
                            rs.getInt("BookingID"),
                            rs.getString("pickup_confirmed"),
                            rs.getTimestamp("pickup_Date_Time").toLocalDateTime(),
                            rs.getTimestamp("return_Date_Time").toLocalDateTime(),
                            rs.getString("return_Confirmed"),
                            rs.getBigDecimal("Extra_Charges")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Get PickupReturn by BookingID database error.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updates an existing pickup and return record in the database.
     * @param pickupReturn The PickupReturn object containing the updated record details.
     * @return true if the record was successfully updated, false otherwise.
     */
    public boolean updatePickupReturn(PickupReturn pickupReturn) {

        String sql = "UPDATE Pickup_Return SET " +
                "pickup_confirmed = ?, " +
                "pickup_Date_Time = ?, " +
                "return_Date_Time = ?, " +
                "return_Confirmed = ?, " +
                "Extra_Charges = ? " +
                "WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pickupReturn.getPickupConfirmed());
            stmt.setTimestamp(2, Timestamp.valueOf(pickupReturn.getPickupDateTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(pickupReturn.getReturnDateTime()));
            stmt.setString(4, pickupReturn.getReturnConfirmed());
            stmt.setBigDecimal(5, pickupReturn.getExtraCharges());
            stmt.setInt(6, pickupReturn.getBookingId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update PickupReturn database error.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a pickup and return record from the database based on the associated booking ID.
     * @param bookingId The ID of the booking for which to delete pickup and return records.
     * @return true if the record was successfully deleted, false otherwise.
     */
    public boolean deletePickupReturnByBookingId(int bookingId) {

        String sql = "DELETE FROM Pickup_Return WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete PickupReturn database error.");
            e.printStackTrace();
            return false;
        }
    }
}