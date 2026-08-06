package backend.model;

import java.time.LocalDateTime;

/**
 * Class Name: CarReview
 * Date: July 4, 2026
 * Programmer: Carla Garcia
 * 
 * Description: 
 * Represents a review for a car rental. This class encapsulates the details of a review, including 
 * user ID, car ID, booking ID, rating, comments, and the date and time of the review.
 * 
 * Important Functions: 
 * The constructors allow for the creation of CarReview objects with or without a review ID. 
 * Getter and setter methods are provided for accessing and modifying the fields of the review.
 * 
 * Important Data Structures: 
 * The CarReview class uses fields to store the review ID, user ID, car ID, booking ID, 
 * rating, comments, and review date. The LocalDateTime class is used for date and time representation.
 * 
 * Algorithm: 
 * No complex algorithms are implemented in this class. It serves as a data model for car reviews, 
 * providing methods to create CarReview objects and allowing for the retrieval and modification of the review 
 * details through getter and setter methods.
 * 
 * @author Carla Garcia
 * @version 1.0
 */

/**
 * Represents a review for a car rental.
 */
public class CarReview {
    private int reviewId;
    private int userId;
    private int carId;
    private int bookingId;
    private int rating;
    private String comments;
    private LocalDateTime reviewDate;

    /**
     * Default constructor.
     */
    public CarReview() {
    }

    /**
     * Parameterized constructor to initialize a CarReview object with all fields.
     * @param reviewId 
     * @param userId
     * @param carId
     * @param bookingId
     * @param rating
     * @param comments
     * @param reviewDate
     */
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

    /**
     * Gets the review ID.
     * @return review ID 
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * Gets the user ID associated with the review.
     * @return user ID 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the car ID associated with the review.
     * @return car ID
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Gets the booking ID associated with the review.
     * @return booking ID 
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Gets the rating for the review.
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the comments for the review.
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Gets the date and time when the review was submitted.
     * @return review date and time
     */
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    /**
     * Sets the review ID.
     * @param reviewId 
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Sets the user ID associated with the review.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the car ID associated with the review.
     * @param carId
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Sets the booking ID associated with the review.
     * @param bookingId
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Sets the rating for the review.
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Sets the comments for the review.
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Sets the date and time when the review was submitted.
     * @param reviewDate 
     */
    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
