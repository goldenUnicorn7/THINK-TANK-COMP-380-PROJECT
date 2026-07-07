package backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import backend.dao.cartDAO;
import backend.model.Cart;

public class CartService {

    private final cartDAO cartDAO;

    private static final BigDecimal DAILY_RATE = new BigDecimal("50.00");

    public CartService() {
        this.cartDAO = new cartDAO();
    }

    public boolean addToCart(int userId, int carId, LocalDateTime pickupDate, LocalDateTime returnDate) {
        validateCartInput(userId, carId, pickupDate, returnDate);

        if (cartDAO.cartItemExists(userId, carId)) {
            throw new IllegalArgumentException("This car is already in your cart.");
        }

        BigDecimal estimatedPrice = calculateEstimatedPrice(pickupDate, returnDate);

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCarId(carId);
        cart.setPickupDate(pickupDate);
        cart.setReturnDate(returnDate);
        cart.setEstimatedPrice(estimatedPrice);

        return cartDAO.addToCart(cart);
    }

    public List<Cart> getCartByUserID(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user.");
        }

        return cartDAO.getCartByUserID(userId);
    }

    public Cart getCartByID(int cartId) {
        if (cartId <= 0) {
            throw new IllegalArgumentException("Invalid cart item.");
        }

        return cartDAO.getCartByID(cartId);
    }

    public boolean removeFromCart(int cartId) {
        if (cartId <= 0) {
            throw new IllegalArgumentException("Invalid cart item.");
        }

        return cartDAO.removeFromCart(cartId);
    }

    public boolean clearCartByUserID(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user.");
        }

        return cartDAO.clearCartByUserID(userId);
    }

    public boolean cartItemExists(int userId, int carId) {
        if (userId <= 0 || carId <= 0) {
            return false;
        }

        return cartDAO.cartItemExists(userId, carId);
    }

    private void validateCartInput(int userId, int carId, LocalDateTime pickupDate, LocalDateTime returnDate) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user.");
        }

        if (carId <= 0) {
            throw new IllegalArgumentException("Invalid car.");
        }

        if (pickupDate == null) {
            throw new IllegalArgumentException("Pickup date is required.");
        }

        if (returnDate == null) {
            throw new IllegalArgumentException("Return date is required.");
        }

        if (pickupDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Pickup date cannot be in the past.");
        }

        if (!returnDate.isAfter(pickupDate)) {
            throw new IllegalArgumentException("Return date must be after pickup date.");
        }
    }

    private BigDecimal calculateEstimatedPrice(LocalDateTime pickupDate, LocalDateTime returnDate) {
        long days = ChronoUnit.DAYS.between(
                pickupDate.toLocalDate(),
                returnDate.toLocalDate()
        );

        if (days <= 0) {
            days = 1;
        }

        return DAILY_RATE.multiply(BigDecimal.valueOf(days));
    }
}