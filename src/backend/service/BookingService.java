package backend.service;
import java.util.List;

import backend.dao.bookingDAO;
import backend.dao.PickupReturnDAO;
import backend.model.Booking;
import backend.model.PickupReturn;


public class BookingService {
    private bookingDAO bookingDAO;
    private PickupReturnDAO pickupReturnDAO;
    
    public BookingService() {
        this.bookingDAO = new bookingDAO();
        this.pickupReturnDAO = new PickupReturnDAO();
    }

    public boolean insertBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }
    public List<Booking> getBookingByUserId(int userId) {
        return bookingDAO.getBookingByUserId(userId);
    }
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }
    public boolean updateBooking(Booking booking) {
        return bookingDAO.updateBooking(booking);
    }

    public boolean insertPickupReturn(PickupReturn pickupReturn) {
        return pickupReturnDAO.insertPickupReturn(pickupReturn);
    }

    public PickupReturn getPickupReturnByBookingId(int bookingId) {
        return pickupReturnDAO.getPickupReturnByBookingId(bookingId);
    }

    public boolean updatePickupReturn(PickupReturn pickupReturn) {
        return pickupReturnDAO.updatePickupReturn(pickupReturn);
    }

    
}