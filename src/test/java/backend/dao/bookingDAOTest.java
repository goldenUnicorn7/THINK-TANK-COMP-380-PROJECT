package backend.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import backend.dao.bookingDAO;
import backend.model.Booking;



public class bookingDAOTest {

    @Test
    public void testGetBookingById(){
        bookingDAO bookingDAO = new bookingDAO();
        Booking booking = bookingDAO.getBookingById(1);

        assertNotNull(booking);
        assertEquals(1, booking.getBookingID());
        assertEquals(1, booking.getUserID());
        assertEquals(1, booking.getCarID());
    }

    @Test
    public void testGetBookingByUserId(){
        bookingDAO dao = new bookingDAO();
        List<Booking> bookings = dao.getBookingByUserId(1);

        assertNotNull(bookings);
        assertEquals(3, bookings.size());
        
        for (Booking booking : bookings) {
            assertEquals(1, booking.getUserID());
        }
    }

    @Test
    public void testGetBookingByUserIdNoBookings() {
        bookingDAO dao = new bookingDAO();
        List<Booking> bookings = dao.getBookingByUserId(999); // Assuming user ID 999 has no bookings

        assertNotNull(bookings);
        assertTrue(bookings.isEmpty());
    }

}
