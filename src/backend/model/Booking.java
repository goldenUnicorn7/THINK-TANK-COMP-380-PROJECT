package backend.model;

public class Booking {
    private int bookingID;
    private int userID;
    private int carID;
    private String pickupDate;
    private String returnDate;
    private int totalPrice;
    private String bookingStatus;

    public Booking() {}

    public Booking(int bookingID, int userID, int carID, String pickupDate, String returnDate, int totalPrice, String bookingStatus){
        this.bookingID = bookingID;
        this.userID = userID;
        this.carID = carID;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
    }

    public Booking(int userID, int carID, String pickupDate, String returnDate, int totalPrice, String bookingStatus){
        this.userID = userID;
        this.carID = carID;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public int getCarID() {
        return carID;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setCarID(int carID) {
        this.carID = carID;
    }
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
}
