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

public class carReviewDAO {

    public carReviewDAO() { 
    }

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
