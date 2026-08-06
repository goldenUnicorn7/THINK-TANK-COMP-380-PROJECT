package backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class Name: PickupReturn
 * Date: July 4, 2026
 * Programmer: Carla Garcia
 * 
 * Description: 
 * Represents a pickup and return record for a car rental. This class encapsulates the details of a 
 * pickup and return transaction, including booking ID, confirmation statuses, date and time of pickup and return, 
 * and any extra charges incurred.
 * 
 * Important Functions: 
 * The class provides constructors for creating PickupReturn objects, as well as getter and 
 * setter methods for accessing and modifying the fields of the PickupReturn record.
 * 
 * Important Data Structures: 
 * The PickupReturn class uses fields to store the record ID, booking ID, pickup 
 * confirmation status, pickup date and time, return date and time, return confirmation status, and extra charges. 
 * The LocalDateTime class is used for date and time representation, and BigDecimal is used for monetary values.
 * 
 * Algorithm: 
 * No complex algorithms are implemented in this class. It serves as a data model for pickup and return 
 * records, providing methods to create PickupReturn objects with or without a record ID, and allowing for the 
 * retrieval and modification of the pickup and return details through getter and setter methods.
 * 
 * @author Carla Garcia
 * @version 1.0
 */

/**
 * Represents a pickup and return record for a car rental.
 */
public class PickupReturn {
    private int recordId;
    private int bookingId;
    private String pickupConfirmed;
    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;
    private String returnConfirmed;
    private BigDecimal extraCharges;

    /**
     * Default constructor.
     */
    public PickupReturn() {
    }

    /**
     * Parameterized constructor to initialize a PickupReturn object with all fields.
     * @param recordId the unique identifier for the pickup and return record
     * @param bookingId the unique identifier for the associated booking
     * @param pickupConfirmed the confirmation status of the pickup
     * @param pickupDateTime the date and time of the pickup
     * @param returnDateTime the date and time of the return
     * @param returnConfirmed the confirmation status of the return
     * @param extraCharges the extra charges incurred during the rental
     */
    public PickupReturn(int recordId, int bookingId, String pickupConfirmed, LocalDateTime pickupDateTime,
            LocalDateTime returnDateTime, String returnConfirmed, BigDecimal extraCharges) {
        this.recordId = recordId;
        this.bookingId = bookingId;
        this.pickupConfirmed = pickupConfirmed;
        this.pickupDateTime = pickupDateTime;
        this.returnDateTime = returnDateTime;
        this.returnConfirmed = returnConfirmed;
        this.extraCharges = extraCharges;
    }

    /**
     * Parameterized constructor to initialize a PickupReturn object without recordId.
     * @param bookingId
     * @param pickupConfirmed
     * @param pickupDateTime
     * @param returnDateTime
     * @param returnConfirmed
     * @param extraCharges
     */
    public PickupReturn(int bookingId, String pickupConfirmed, LocalDateTime pickupDateTime,
            LocalDateTime returnDateTime, String returnConfirmed, BigDecimal extraCharges) {
        this.bookingId = bookingId;
        this.pickupConfirmed = pickupConfirmed;
        this.pickupDateTime = pickupDateTime;
        this.returnDateTime = returnDateTime;
        this.returnConfirmed = returnConfirmed;
        this.extraCharges = extraCharges;
    }

    /**
     * Gets the record ID of the pickup and return record.
     * @return record ID
     */
    public int getRecordId() {
        return recordId;
    }

    /**
     * Gets the booking ID associated with the pickup and return record.
     * @return booking ID
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Gets the pickup confirmation status.
     * @return pickup confirmation status
     */
    public String getPickupConfirmed() {
        return pickupConfirmed;
    }

    /**
     * Gets the pickup date and time.
     * @return pickup date and time
     */
    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    /**
     * Gets the return date and time.
     * @return return date and time
     */
    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    /**
     * Gets the return confirmation status.
     * @return return confirmation status
     */
    public String getReturnConfirmed() {
        return returnConfirmed;
    }

    /**
     * Gets the extra charges.
     * @return extra charges
     */
    public BigDecimal getExtraCharges() {
        return extraCharges;
    }

    /**
     * Sets the record ID of the pickup and return record.
     * @param recordId 
     */
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    /**
     * Sets the booking ID associated with the pickup and return record.
     * @param bookingId 
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Sets the pickup confirmation status.
     * @param pickupConfirmed 
     */
    public void setPickupConfirmed(String pickupConfirmed) {
        this.pickupConfirmed = pickupConfirmed;
    }

    /**
     * Sets the pickup date and time.
     * @param pickupDateTime 
     */
    public void setPickupDateTime(LocalDateTime pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    /**
     * Sets the return date and time.
     * @param returnDateTime 
     */
    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    /**
     * Sets the return confirmation status.
     * @param returnConfirmed 
     */
    public void setReturnConfirmed(String returnConfirmed) {
        this.returnConfirmed = returnConfirmed;
    }

    /**
     * Sets the extra charges.
     * @param extraCharges 
     */
    public void setExtraCharges(BigDecimal extraCharges) {
        this.extraCharges = extraCharges;
    }
}