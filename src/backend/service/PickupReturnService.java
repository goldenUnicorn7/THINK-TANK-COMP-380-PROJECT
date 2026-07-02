package backend.service;

import backend.dao.PickupReturnDAO;
import backend.model.PickupReturn;
import java.math.BigDecimal;

public class PickupReturnService {
    private PickupReturnDAO pickupReturnDAO;

    public PickupReturnService() {
        this.pickupReturnDAO = new PickupReturnDAO();
    }

    public boolean insertPickupReturn(PickupReturn pickupReturn) {
        if(pickupReturn == null) {
            System.out.println("Pickup Required.");
            return false;
        }

        if(pickupReturn.getBookingId() <= 0 || pickupReturn.getPickupDateTime() == null || pickupReturn.getReturnDateTime() == null) {
            System.out.println("Invalid PickupReturn data.");
            return false;
        }

        if(pickupReturn.getExtraCharges() == null || pickupReturn.getExtraCharges().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Invalid extra charges.");
            return false;
        }

        return pickupReturnDAO.insertPickupReturn(pickupReturn);
    }
    public PickupReturn getPickupReturnByBookingId(int bookingId) {
        if(bookingId <= 0) {
            System.out.println("Invalid booking ID.");
            return null;
        }

        return pickupReturnDAO.getPickupReturnByBookingId(bookingId);
    }
    public boolean updatePickupReturn(PickupReturn pickupReturn) {
        if(pickupReturn.getBookingId() <= 0 || pickupReturn.getPickupDateTime() == null || pickupReturn.getReturnDateTime() == null) {
            System.out.println("Invalid PickupReturn data.");
            return false;
        }

        if(pickupReturn.getExtraCharges() == null || pickupReturn.getExtraCharges().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Invalid extra charges.");
            return false;
        }

        return pickupReturnDAO.updatePickupReturn(pickupReturn);
    }
}
