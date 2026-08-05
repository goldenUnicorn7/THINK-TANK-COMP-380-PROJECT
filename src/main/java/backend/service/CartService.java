package backend.service;

import java.util.List;

import backend.dao.cartDAO;
import backend.model.Car;

/**
 * Class Name: CartService
 * Date: July 4, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Provides business logic for managing a user's cart in the car rental
 * desktop application. This service validates user and car identifiers
 * before calling the cartDAO database-access methods.
 *
 * Important Functions:
 * addToCart() adds a selected car, getCartCars() retrieves the user's cart,
 * removeFromCart() removes one car, and clearCart() removes all cars from
 * the user's cart.
 *
 * Important Data Structures:
 * A List of Car objects is used to transfer the vehicles stored in a user's
 * cart from the DAO layer to the frontend.
 *
 * Algorithm:
 * Each method first validates the provided identifiers. Valid requests are
 * passed to cartDAO, while invalid identifiers return false or cause an
 * IllegalArgumentException.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class CartService {

    /** The cartDAO instance used for database access. */
    private final cartDAO cartDAO;

    /**
     * Constructs a CartService instance and initializes the cartDAO.
     */
    public CartService() {
        this.cartDAO = new cartDAO();
    }

    /**
     * Adds a car to the user's shopping cart.
     *
     * @param userId The unique identifier for the user
     * @param carId  The unique identifier for the car to add
     * @return true if the car was successfully added, false otherwise
     */
    public boolean addToCart(int userId, int carId) {
        if (userId <= 0 || carId <= 0) {
            return false;
        }

        return cartDAO.addToCart(userId, carId);
    }

    /**
     * Retrieves all cars currently in the user's shopping cart.
     *
     * @param userId The unique identifier for the user
     * @return A list of Car objects representing the cars in the user's cart
     * @throws IllegalArgumentException if the user ID is invalid
     */
    public List<Car> getCartCars(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user.");
        }

        return cartDAO.getCartCars(userId);
    }

    /**
     * Removes a car from the user's shopping cart.
     *
     * @param userId The unique identifier for the user
     * @param carId  The unique identifier for the car to remove
     * @return true if the car was successfully removed, false otherwise
     */
    public boolean removeFromCart(int userId, int carId) {
        if (userId <= 0 || carId <= 0) {
            return false;
        }

        return cartDAO.removeFromCart(userId, carId);
    }

    /**
     * Clears all cars from the user's shopping cart.
     *
     * @param userId The unique identifier for the user
     * @return true if the cart was successfully cleared, false otherwise
     */
    public boolean clearCart(int userId) {
        if (userId <= 0) {
            return false;
        }

        return cartDAO.clearCart(userId);
    }
}