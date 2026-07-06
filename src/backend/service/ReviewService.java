package backend.service;


import java.util.List;

import backend.dao.carReviewDAO;
import backend.model.CarReview;

public class ReviewService {
    private carReviewDAO carReviewDAO;

    public ReviewService() {
        this.carReviewDAO = new carReviewDAO();
    }

    public boolean insertReview(CarReview review) {
        return carReviewDAO.insertReview(review);
    }

    public CarReview getReviewById(int reviewId) {
        return carReviewDAO.getReviewById(reviewId);
    }

    public boolean updateReview(CarReview review) {
        return carReviewDAO.updateReview(review);
    }

    public boolean deleteReview(int reviewId) {
        return carReviewDAO.deleteReview(reviewId);
    }

    public List<CarReview> getAllReviews() {
        return carReviewDAO.getAllReviews();
    }
}