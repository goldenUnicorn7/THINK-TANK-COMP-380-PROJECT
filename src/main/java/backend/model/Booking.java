package backend.model;
import java.math.BigDecimal;

/**
 * Class Name: Booking
 * Date: June 28, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Represents a car rental booking in the desktop application. The class stores
 * the booking identifier, associated user and car identifiers, rental dates,
 * total price, and current booking status.
 *
 * Important Functions:
 * The constructors create Booking objects with or without an existing booking
 * ID. Getter and setter methods provide access to and modification of each
 * booking attribute.
 *
 * Important Data Structures:
 * This class uses primitive integer values, String objects, and BigDecimal.
 * BigDecimal is used for the total price to provide more accurate monetary
 * calculations than floating-point data types.
 *
 * Algorithm:
 * No complex algorithm is used because this model class mainly stores and
 * transfers booking information between the database, service layer, and
 * frontend.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class Booking {

    /** The unique identifier for the booking. */
    private int bookingID;

    /** The unique identifier for the user who made the booking. */
    private int userID;

    /** The unique identifier for the car being booked. */
    private int carID;

    /** The date when the car is to be picked up. */
    private String pickupDate;

    /** The date when the car is to be returned. */
    private String returnDate;

    /** The total price for the booking. */
    private BigDecimal totalPrice;

    /** The current status of the booking (e.g., "Confirmed", "Cancelled"). */
    private String bookingStatus;

    /** Creates an empty Booking object. */
    public Booking() {
    }

    /**
     * Creates a Booking object with the specified attributes, including an existing booking ID.
     *
     * @param bookingID     The unique identifier for the booking
     * @param userID        The unique identifier for the user
     * @param carID         The unique identifier for the car
     * @param pickupDate    The pickup date for the booking
     * @param returnDate    The return date for the booking
     * @param totalPrice    The total price for the booking
     * @param bookingStatus The current status of the booking
     */
    public Booking(int bookingID, int userID, int carID, String pickupDate, String returnDate, BigDecimal totalPrice,
            String bookingStatus) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.carID = carID;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
    }

    /**
     * Creates a Booking object with the specified attributes, excluding the booking ID.
     *
     * @param userID        The unique identifier for the user
     * @param carID         The unique identifier for the car
     * @param pickupDate    The pickup date for the booking
     * @param returnDate    The return date for the booking
     * @param totalPrice    The total price for the booking
     * @param bookingStatus The current status of the booking
     */
    public Booking(int userID, int carID, String pickupDate, String returnDate, BigDecimal totalPrice, String bookingStatus) {
        this.userID = userID;
        this.carID = carID;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
    }

    /**
     * Returns the unique identifier for the booking.
     *
     * @return The booking ID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * Returns the unique identifier for the user who made the booking.
     *
     * @return The user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Returns the unique identifier for the car being booked.
     *
     * @return The car ID
     */
    public int getCarID() {
        return carID;
    }

    /**
     * Returns the date when the car is to be picked up.
     *
     * @return The pickup date
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Returns the date when the car is to be returned.
     *
     * @return The return date
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Returns the total price for the booking.
     *
     * @return The total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns the current status of the booking.
     *
     * @return The booking status
     */
    public String getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets the unique identifier for the booking.
     *
     * @param bookingID The booking ID to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * Sets the unique identifier for the user who made the booking.
     *
     * @param userID The user ID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Sets the unique identifier for the car being booked.
     *
     * @param carID The car ID to set
     */
    public void setCarID(int carID) {
        this.carID = carID;
    }

    /**
     * Sets the date when the car is to be picked up.
     *
     * @param pickupDate The pickup date to set
     */ 
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     * Sets the date when the car is to be returned.
     *
     * @param returnDate The return date to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Sets the total price for the booking.
     *
     * @param totalPrice The total price to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Sets the current status of the booking.
     *
     * @param bookingStatus The booking status to set
     */
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
