package backend.service;

import backend.dao.carDAO;
import backend.model.Car;

import java.util.List;

public class CarService {

    private final carDAO carDAO;

    public CarService() {
        this.carDAO = new carDAO();
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public List<Car> searchCars(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return carDAO.getAllCars();
        }

        return carDAO.searchCars(keyword.trim());
    }

    public Car getCarById(int carId) {
        if (carId <= 0) {
            throw new IllegalArgumentException("Invalid car.");
        }

        Car car = carDAO.getCarById(carId);

        if (car == null) {
            throw new IllegalArgumentException("Car not found.");
        }

        return car;
    }
}
