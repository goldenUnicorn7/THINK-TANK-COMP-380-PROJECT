package backend.model;

public class Car {

    private int carID;
    private String carBrand;
    private String carModel;
    private String carColor;
    private int carYear;
    private double price;
    private String availability;

    public Car() {
    }

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

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return carBrand + " " + carModel + " (" + carYear + ")";
    }
}