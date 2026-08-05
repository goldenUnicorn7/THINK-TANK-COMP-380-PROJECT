package backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import backend.dao.PickupReturnDAO;
import backend.dao.bookingDAO;
import backend.model.Booking;
import backend.model.PickupReturn;

/**
 * Service class for managing bookings and pickup/return records. This class provides methods to interact with the bookingDAO and PickupReturnDAO for performing CRUD operations on bookings and pickup/return records.
 */
public class BookingService {

    private bookingDAO bookingDAO;
    private PickupReturnDAO pickupReturnDAO;

    /**
     * Constructs a new BookingService instance.
     */
    public BookingService() {
        this.bookingDAO = new bookingDAO();
        this.pickupReturnDAO = new PickupReturnDAO();
    }

    /**
     * Inserts a new booking into the database.
     * @param booking The Booking object containing the booking details to be inserted.
     * @return true if booking was successfully inserted, false otherwise.
     */
    public boolean insertBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }

    /**
     * Retrieves a list of bookings associated with a specific user ID from the database.
     * @param userId The ID of the user for whom to retrieve bookings.
     * @return A list of Booking objects associated with the specified user ID.
     */
    public List<Booking> getBookingByUserId(int userId) {
        return bookingDAO.getBookingByUserId(userId);
    }

    /**
     * Retrieves a booking by its ID from the database.
     * @param bookingId The ID of the booking to retrieve.
     * @return The Booking object if found, null otherwise.
     */
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    /**
     * Updates an existing booking in the database.
     * @param booking The Booking object containing the updated booking details.
     * @return true if the booking was successfully updated, false otherwise.
     */
    public boolean updateBooking(Booking booking) {
        return bookingDAO.updateBooking(booking);
    }

   /** 
    * Inserts a new pickup and return record into the database.
    * @param pickupReturn The PickupReturn object containing the record details.
    * @return true if the record was successfully inserted, false otherwise.
    */
    public boolean insertPickupReturn(PickupReturn pickupReturn) {
        return pickupReturnDAO.insertPickupReturn(pickupReturn);
    }

    /**
     * Retrieves a pickup and return record by its associated booking ID from the database.
     * @param bookingId The ID of the booking for which to retrieve pickup and return records.
     * @return The PickupReturn object if found, null otherwise.
     */
    public PickupReturn getPickupReturnByBookingId(int bookingId) {
        return pickupReturnDAO.getPickupReturnByBookingId(bookingId);
    }

    /**
     * Updates an existing pickup and return record in the database.
     * @param pickupReturn The PickupReturn object containing the updated record details.
     * @return true if the record was successfully updated, false otherwise.
     */
    public boolean updatePickupReturn(PickupReturn pickupReturn) {
        return pickupReturnDAO.updatePickupReturn(pickupReturn);
    }

    /**
     * Creates a new booking with the specified details and inserts it into the database.
     * @param userId The ID of the user creating the booking.
     * @param carId The ID of the car to be booked.
     * @param pickupDate The date when the car will be picked up.
     * @param returnDate The date when the car will be returned.
     * @param totalAmount The total amount for the booking.
     * @return true if the booking was successfully created, false otherwise.
     */
  
    public boolean createBooking(int userId, int carId, LocalDate pickupDate, LocalDate returnDate, double totalAmount) {
        if (userId <= 0 || carId <= 0 || pickupDate == null || returnDate == null) {
            return false;
        }

        if (!returnDate.isAfter(pickupDate)) {
            return false;
        }

        BigDecimal totalPrice = BigDecimal.valueOf(totalAmount);

        Booking booking = new Booking(
                userId,
                carId,
                pickupDate.toString(),
                returnDate.toString(),
                totalPrice,
                "Confirmed"
        );

        return bookingDAO.insertBooking(booking);
    }
}