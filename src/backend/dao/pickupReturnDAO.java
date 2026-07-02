package backend.dao;

import backend.db.DBConnection;
import backend.model.PickupReturn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PickupReturnDAO {

    public PickupReturnDAO(){
    } 
    

        String sql = "INSERT INTO Pickup_Return (BookingID, pickup_confirmed, pickup_Date_Time, return_Date_Time, return_Confirmed, Extra_Charges) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                
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
             PreparedStatement stmt = conn.prepareStatement(
                
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PickupReturn(
                        rs.getInt("BookingID"),
                        rs.getString("pickup_confirmed"),
                        rs.getTimestamp("pickup_Date_Time").toLocalDateTime(),
                        rs.getTimestamp("return_Date_Time").toLocalDateTime(),
                        rs.getString("return_Confirmed"),
                        rs.getBigDecimal("Extra_Charges")
                );

        } catch (SQLException e) {
            System.out.println("Get PickupReturn by BookingID database error.");
            e.printStackTrace();
        }

        return null;
    }

    public boolean updatePickupReturn(PickupReturn pickupReturn) {
        String sql = "UPDATE Pickup_Return SET pickup_confirmed = ?, pickup_Date_Time = ?, return_Date_Time = ?, return_Confirmed = ?, Extra_Charges = ? WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                
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
}
