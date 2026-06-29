package backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PickupReturn {
    private int recordId;
    private int bookingId;
    private String pickupConfirmed;
    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;
    private String returnConfirmed;
    private BigDecimal extraCharges;

    public PickupReturn() {
    }

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

    public PickupReturn(int bookingId, String pickupConfirmed, LocalDateTime pickupDateTime,
            LocalDateTime returnDateTime, String returnConfirmed, BigDecimal extraCharges) {
        this.bookingId = bookingId;
        this.pickupConfirmed = pickupConfirmed;
        this.pickupDateTime = pickupDateTime;
        this.returnDateTime = returnDateTime;
        this.returnConfirmed = returnConfirmed;
        this.extraCharges = extraCharges;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getPickupConfirmed() {
        return pickupConfirmed;
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public String getReturnConfirmed() {
        return returnConfirmed;
    }

    public BigDecimal getExtraCharges() {
        return extraCharges;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setPickupConfirmed(String pickupConfirmed) {
        this.pickupConfirmed = pickupConfirmed;
    }

    public void setPickupDateTime(LocalDateTime pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public void setReturnConfirmed(String returnConfirmed) {
        this.returnConfirmed = returnConfirmed;
    }

    public void setExtraCharges(BigDecimal extraCharges) {
        this.extraCharges = extraCharges;
    }
}