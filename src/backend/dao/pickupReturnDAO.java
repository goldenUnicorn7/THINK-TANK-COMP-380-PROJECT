package backend.dao;

import backend.db.DBConnection;
import backend.model.PickupReturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PickupReturnDAO {

    public PickupReturnDAO() {
    }

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