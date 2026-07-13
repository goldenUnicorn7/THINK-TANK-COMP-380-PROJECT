package backend.service;

import java.util.List;

import backend.dao.cartDAO;
import backend.model.Car;

public class CartService {

    private final cartDAO cartDAO;

    public CartService() {
        this.cartDAO = new cartDAO();
    }

    public boolean addToCart(int userId, int carId) {
        if (userId <= 0 || carId <= 0) {
            return false;
        }

        return cartDAO.addToCart(userId, carId);
    }

    public List<Car> getCartCars(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user.");
        }

        return cartDAO.getCartCars(userId);
    }

    public boolean removeFromCart(int userId, int carId) {
        if (userId <= 0 || carId <= 0) {
            return false;
        }

        return cartDAO.removeFromCart(userId, carId);
    }

    public boolean clearCart(int userId) {
        if (userId <= 0) {
            return false;
        }

        return cartDAO.clearCart(userId);
    }
}