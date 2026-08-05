package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Booking;

/**
 Data Access Object (DAO) class for managing bookings in the database.
  */
public class bookingDAO {

    /**
     Constructor for the bookingDAO class. Initializes a new instance of the bookingDAO.
     */
    public bookingDAO() {
    }

    /**
     * Inserts a new booking into the database.
       @param booking The Booking object containing the booking details to be inserted.
       @return true if booking was successfully inserted, false otherwise.
     */
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

    /**
     * Retrieves a list of bookings associated with a specific user ID from the database.
       @param userId The ID of the user for whom to retrieve bookings.
       @return a list of Booking objects associated with the given user ID, or null if an error occurs.
     */
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

    /**
     * Retrieves a booking by its ID from the database.
     * @param bookingId The ID of the booking to retrieve.
     * @return matching Booking object if found, null otherwise.
     */
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

    /**
     * Updates an existing booking in the database.
     * @param booking The Booking object containing the updated booking details.
     * @return true if the booking was successfully updated, false otherwise.
     */
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

    /**
     * Deletes a booking from the database.
     * @param bookingId The ID of the booking to delete.
     * @return true if booking was successfully deleted, false otherwise.
     */
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