package backend.model;

import java.time.LocalDateTime;

public class CarReview {
    private int reviewId;
    private int userId;
    private int carId;
    private int bookingId;
    private int rating;
    private String comments;
    private LocalDateTime reviewDate;

    public CarReview() {
    }

    public CarReview(int reviewId, int userId, int carId, int bookingId, int rating, String comments,
            LocalDateTime reviewDate) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.carId = carId;
        this.bookingId = bookingId;
        this.rating = rating;
        this.comments = comments;
        this.reviewDate = reviewDate;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarId() {
        return carId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
