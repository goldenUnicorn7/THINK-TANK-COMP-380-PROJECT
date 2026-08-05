package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.CarReview;

/**
 * Data Access Object (DAO) class for managing car reviews in the database. This class provides methods to perform CRUD operations on the Car_Reviews table.
 */
public class carReviewDAO {

    /**
     * Constructs a new carReviewDAO instance. This constructor initializes the DAO for managing car reviews in the database.
     */
    public carReviewDAO() { 
    }

    /**
     * Inserts a new car review into the database.
     * @param review The CarReview object containing the review details.
     * @return true if the review was successfully inserted, false otherwise.
     */
    public boolean insertReview(CarReview review) {
        String sql = "INSERT INTO Car_Reviews (CarID, UserID, BookingID, Rating, Comments, Review_Date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getCarId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getBookingId());
            stmt.setInt(4, review.getRating());
            stmt.setString(5, review.getComments());
            stmt.setTimestamp(6, Timestamp.valueOf(review.getReviewDate()));
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a car review by its ID from the database.
     * @param reviewId The ID of the review to retrieve.
     * @return The CarReview object if found, null otherwise.
     */
    public CarReview getReviewById(int reviewId) {
        String sql = "SELECT * FROM Car_Reviews WHERE ReviewID = ?";
       try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CarReview(
                    rs.getInt("ReviewID"),
                    rs.getInt("UserID"),
                    rs.getInt("CarID"),
                    rs.getInt("BookingID"),
                    rs.getInt("Rating"),
                    rs.getString("Comments"),
                    rs.getTimestamp("Review_Date").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Updates an existing car review in the database.
     * @param review The CarReview object containing the updated review details.
     * @return true if the review was successfully updated, false otherwise.
     */
    public boolean updateReview(CarReview review) {
        String sql = "UPDATE Car_Reviews SET CarID = ?, UserID = ?, Rating = ?, Comments = ? WHERE ReviewID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getCarId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComments());
            stmt.setInt(5, review.getReviewId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a car review from the database.
     * @param reviewId The ID of the review to delete.
     * @return true if the review was successfully deleted, false otherwise.
     */
    public boolean deleteReview(int reviewId) {
        String sql = "DELETE FROM Car_Reviews WHERE ReviewID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all car reviews from the database.
     * @return A list of all CarReview objects.
     */
    public List<CarReview> getAllReviews() {
        String sql = "SELECT * FROM Car_Reviews";
        List<CarReview> reviews = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CarReview review = new CarReview(
                    rs.getInt("ReviewID"),
                    rs.getInt("UserID"),
                    rs.getInt("CarID"),
                    rs.getInt("BookingID"),
                    rs.getInt("Rating"),
                    rs.getString("Comments"),
                    rs.getTimestamp("Review_Date").toLocalDateTime()
                );
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
