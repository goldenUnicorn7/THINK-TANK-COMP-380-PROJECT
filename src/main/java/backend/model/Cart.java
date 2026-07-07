package backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cart {
    private int cartId;
    private int userId;
    private int carId;
    private LocalDateTime returnDate;
    private LocalDateTime pickupDate;
    private BigDecimal estimatedPrice;

    public Cart() {
    }

    public Cart(int cartId, int userId, int carId, LocalDateTime returnDate, LocalDateTime pickupDate,
            BigDecimal estimatedPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.carId = carId;
        this.returnDate = returnDate;
        this.pickupDate = pickupDate;
        this.estimatedPrice = estimatedPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarId() {
        return carId;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setEstimatedPrice(BigDecimal estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

}
