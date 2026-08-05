package backend.model;
/**
 * Class Name: Car
 * Date: June 28, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Represents a car available in the car rental desktop application.
 * The class stores identifying information, vehicle details, rental price,
 * and the current availability status of the car.
 *
 * Important Functions:
 * The constructors create Car objects, while the getter and setter methods
 * provide access to and modification of each car attribute. The toString()
 * method returns a readable summary of the car for display in the user interface.
 *
 * Important Data Structures:
 * This class uses primitive data types and String objects to store car
 * information. No complex data structure or algorithm is required because
 * this model class primarily acts as a data container.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class Car {

    /** The unique identifier for the car. */
    private int carID;

    /** The brand of the car. */
    private String carBrand;

    /** The model of the car. */
    private String carModel;

    /** The color of the car. */
    private String carColor;

    /** The year the car was manufactured. */
    private int carYear;

    /** The rental price of the car. */ 
    private double price;

    /** The availability status of the car. */
    private String availability;

    /**
     * Creates an empty Car object.
     */
    public Car() {
    }

    /**
     * Creates a Car object with the specified attributes.
     *
     * @param carID       The unique identifier for the car.
     * @param carBrand    The brand of the car.
     * @param carModel    The model of the car.
     * @param carColor    The color of the car.
     * @param carYear     The year the car was manufactured.
     * @param price       The rental price of the car.
     * @param availability The availability status of the car.
     */

    public Car(int carID, String carBrand, String carModel, String carColor,
               int carYear, double price, String availability) {
        this.carID = carID;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carYear = carYear;
        this.price = price;
        this.availability = availability;
    }

    /**
     * Returns the unique identifier for the car.
     *
     * @return The car ID
     */
    public int getCarID() {
        return carID;
    }

    /**
     * Sets the unique identifier for the car.
     *
     * @param carID The car ID to set
     */
    public void setCarID(int carID) {
        this.carID = carID;
    }

    /**
     * Returns the brand of the car.
     *
     * @return The car brand
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * Sets the brand of the car.
     *
     * @param carBrand The car brand to set
     */
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * Returns the model of the car.
     *
     * @return The car model
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Sets the model of the car.
     *
     * @param carModel The car model to set
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * Returns the color of the car.
     *
     * @return The car color
     */
    public String getCarColor() {
        return carColor;
    }

    /**
     * Sets the color of the car.
     *
     * @param carColor The car color to set
     */
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    /**
     * Returns the year the car was manufactured.
     *
     * @return The car year
     */
    public int getCarYear() {
        return carYear;
    }

    /**
     * Sets the year the car was manufactured.
     *
     * @param carYear The car year to set
     */

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    /**
     * Returns the rental price of the car.
     *
     * @return The car price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the rental price of the car.
     *
     * @param price The car price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the availability status of the car.
     *
     * @return The car availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * Sets the availability status of the car.
     *
     * @param availability The car availability to set
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * Returns a string representation of the car, including its brand, model, and year.
     *
     * @return A string summarizing the car's key attributes
     */
    @Override
    public String toString() {
        return carBrand + " " + carModel + " (" + carYear + ")";
    }
}