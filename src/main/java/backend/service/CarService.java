package backend.service;

import java.util.List;

import backend.dao.carDAO;
import backend.model.Car;

/**
 * Class Name: CarService
 * Date: July 4, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Provides business logic for retrieving and searching cars in the car rental
 * desktop application. This service connects frontend controllers to the
 * carDAO database-access class.
 *
 * Important Functions:
 * getAllCars() retrieves every car, searchCars() validates and processes a
 * search keyword, and getCarById() validates a car ID before retrieving the
 * corresponding vehicle.
 *
 * Important Data Structures:
 * A List of Car objects is used to transfer multiple vehicle records between
 * the DAO layer and the frontend. Individual cars are represented using the
 * Car model class.
 *
 * Algorithm:
 * Each method validates the provided input before delegating the database
 * operation to carDAO. Blank search terms return all cars, while invalid or
 * missing car identifiers produce an IllegalArgumentException.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class CarService {

    /** The carDAO instance used for database access. */
    private final carDAO carDAO;

    /**
     * Constructs a CarService instance and initializes the carDAO.
     */
    public CarService() {
        this.carDAO = new carDAO();
    }

    /**
     * Retrieves all cars from the database.
     *
     * @return A list of all Car objects in the database
     */
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    /**
     * Searches for cars in the database that match the given keyword.
     * Blank or null keywords return all cars.
     *
     * @param keyword The search keyword to filter cars
     * @return A list of Car objects that match the search criteria
     */
    public List<Car> searchCars(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return carDAO.getAllCars();
        }

        return carDAO.searchCars(keyword.trim());
    }

    /**
     * Retrieves a car from the database by its unique identifier.
     * Throws an IllegalArgumentException if the car ID is invalid or not found.
     *
     * @param carId The unique identifier for the car
     * @return The Car object if found
     * @throws IllegalArgumentException if the car ID is invalid or not found
     */
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