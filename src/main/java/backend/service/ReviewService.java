package backend.service;


import java.util.List;

import backend.dao.carReviewDAO;
import backend.model.CarReview;

/**
 * Class Name: ReviewService
 * Date: July 4, 2026
 * Programmer: Carla Garcia
 * 
 * Description: 
 * Provides business logic for managing car reviews in the car rental application. This service 
 * connects frontend controllers to the carReviewDAO database-access class.
 * 
 * Important Functions: 
 * insertReview() validates and inserts a new car review.
 * getReviewById() retrieves a review by its ID.
 * updateReview() updates an existing review.
 * deleteReview() deletes a review.
 * getAllReviews() retrieves all reviews.
 * 
 * Important Data Structures: 
 * The CarReview model class stores review information retrieved from or sent to the 
 * database. Lists of CarReview objects are used to transfer multiple review records between the DAO layer and the 
 * frontend.
 * 
 * Algorithm: 
 * Each method delegates the requested database operation to carReviewDAO and returns the resulting 
 * object(s) or success value. The service ensures that reviews are created, retrieved, updated, and deleted 
 * correctly in the database.
 * 
 * @author Carla Garcia
 * @version 1.0
 */

/**
 * Service class for managing car reviews. This class provides methods to interact with the carReviewDAO for performing CRUD operations on car reviews.
 */
public class ReviewService {
    private carReviewDAO carReviewDAO;

    /**
     * Constructs a new ReviewService instance.
     */
    public ReviewService() {
        this.carReviewDAO = new carReviewDAO();
    }

    /**
     * Inserts a new car review into the database.
     * @param review The CarReview object containing the review details.
     * @return true if the review was successfully inserted, false otherwise.
     */
    public boolean insertReview(CarReview review) {
        return carReviewDAO.insertReview(review);
    }

    /**
     * Retrieves a car review by its ID.
     * @param reviewId The ID of the review to retrieve.
     * @return The CarReview object if found, null otherwise.
     */
    public CarReview getReviewById(int reviewId) {
        return carReviewDAO.getReviewById(reviewId);
    }

    /**
     * Updates an existing car review in the database.
     * @param review The CarReview object containing the updated review details.
     * @return true if the review was successfully updated, false otherwise.
     */
    public boolean updateReview(CarReview review) {
        return carReviewDAO.updateReview(review);
    }

    /**
     * Deletes a car review from the database.
     * @param reviewId The ID of the review to delete.
     * @return true if the review was successfully deleted, false otherwise.
     */
    public boolean deleteReview(int reviewId) {
        return carReviewDAO.deleteReview(reviewId);
    }

    /**
     * Retrieves all car reviews from the database.
     * @return A list of all CarReview objects.
     */
    public List<CarReview> getAllReviews() {
        return carReviewDAO.getAllReviews();
    }
}