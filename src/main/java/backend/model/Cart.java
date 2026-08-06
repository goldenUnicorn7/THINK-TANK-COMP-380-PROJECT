package backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class Name: Cart
 * Date: July 4, 2026
 * Programmer: Carla Garcia
 * 
 * Description: 
 * Represents a shopping cart for a user, containing information about the selected car rental. 
 * This class encapsulates the details of a cart, including user ID, car ID, pickup and return dates, and the 
 * estimated price for the rental.
 * 
 * Important Functions: 
 * The class provides constructors for creating Cart objects, as well as getter and setter 
 * methods for accessing and modifying the fields of the cart.
 * 
 * Important Data Structures: 
 * The Cart class uses fields to store the cart ID, user ID, car ID, pickup date, return 
 * date, and estimated price. The LocalDateTime class is used for date and time representation, and BigDecimal is 
 * used for monetary values.
 * 
 * Algorithm: 
 * No complex algorithms are implemented in this class. It serves as a data model for shopping carts, 
 * providing methods to create Cart objects with or without a cart ID, and allowing for the retrieval and 
 * modification of the cart details through getter and setter methods.
 * 
 * @author Carla Garcia
 * @version 1.0
 */

/**
 * Represents a shopping cart for a user, containing information about the selected car rental.
 */
public class Cart {
    private int cartId;
    private int userId;
    private int carId;
    private LocalDateTime returnDate;
    private LocalDateTime pickupDate;
    private BigDecimal estimatedPrice;

    /**
     * Default constructor.
     */
    public Cart() {
    }

    /**
     * Parameterized constructor to initialize a Cart object with all fields.
     * @param cartId 
     * @param userId
     * @param carId
     * @param returnDate
     * @param pickupDate
     * @param estimatedPrice 
     */           
    public Cart(int cartId, int userId, int carId, LocalDateTime returnDate, LocalDateTime pickupDate,
            BigDecimal estimatedPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.carId = carId;
        this.returnDate = returnDate;
        this.pickupDate = pickupDate;
        this.estimatedPrice = estimatedPrice;
    }

    /**
     * Gets the cart ID.
     * @return cart ID 
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Gets the user ID associated with the cart.
     * @return user ID 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the car ID associated with the cart.
     * @return car ID 
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Gets the return date for the car rental.
     * @return return date 
     */
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    /**
     * Gets the pickup date for the car rental.
     * @return pickup date 
     */
    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    /**
     * Gets the estimated price for the car rental.
     * @return estimated price
     */
    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    /**
     * Sets the cart ID.
     * @param cartId 
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    /**
     * Sets the user ID associated with the cart.
     * @param userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the car ID associated with the cart.
     * @param carId
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Sets the return date for the car rental.
     * @param returnDate 
     */
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Sets the pickup date for the car rental.
     * @param pickupDate 
     */
    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     * Sets the estimated price for the car rental.
     * @param estimatedPrice 
     */
    public void setEstimatedPrice(BigDecimal estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

}
