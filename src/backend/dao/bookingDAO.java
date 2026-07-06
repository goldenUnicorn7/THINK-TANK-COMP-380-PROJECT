package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Booking;

public class bookingDAO {
    public bookingDAO() {
    }

    public boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO Bookings (UserID, CarID, pickup_Date, return_Date, Total_price, Booking_Status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getUserID());
            stmt.setInt(2, booking.getCarID());
            stmt.setString(3, booking.getPickupDate());
            stmt.setString(4, booking.getReturnDate());
            stmt.setBigDecimal(5, booking.getTotalPrice());
            stmt.setString(6, booking.getBookingStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Insert Booking database error.");
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getBookingByUserId(int userId) {
        String sql = "SELECT * FROM Bookings WHERE UserID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("BookingID"),
                        rs.getInt("UserID"),
                        rs.getInt("CarID"),
                        rs.getString("pickup_Date"),
                        rs.getString("return_Date"),
                        rs.getBigDecimal("Total_price"),
                        rs.getString("Booking_Status")));
            }

        } catch (SQLException e) {
            System.out.println("Get Booking by ID database error.");
            e.printStackTrace();
        }

        return null;
    }

    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM Bookings WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Booking(
                        rs.getInt("BookingID"),
                        rs.getInt("UserID"),
                        rs.getInt("CarID"),
                        rs.getString("pickup_Date"),
                        rs.getString("return_Date"),
                        rs.getBigDecimal("Total_price"),
                        rs.getString("Booking_Status"));
            }

        } catch (SQLException e) {
            System.out.println("Get Booking by ID database error.");
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBooking(Booking booking) {
        String sql = "UPDATE Bookings SET UserID = ?, CarID = ?, pickup_Date = ?, return_Date = ?, Total_price = ?, Booking_Status = ? WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getUserID());
            stmt.setInt(2, booking.getCarID());
            stmt.setString(3, booking.getPickupDate());
            stmt.setString(4, booking.getReturnDate());
            stmt.setBigDecimal(5, booking.getTotalPrice());
            stmt.setString(6, booking.getBookingStatus());
            stmt.setInt(7, booking.getBookingID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Booking database error.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM Bookings WHERE BookingID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Booking database error.");
            e.printStackTrace();
            return false;
        }
    }
}