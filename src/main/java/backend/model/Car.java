package backend.model;

public class Car {
    private int carID;
    private String carBrand;
    private String carModel;
    private String carColor;
    private int carYear;

    public Car() {
    }

    public Car(int carID, String carBrand, String carModel, String carColor, int carYear) {
        this.carID = carID;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carYear = carYear;
    }

    public Car(String carBrand, String carModel, String carColor, int carYear) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carYear = carYear;
    }

    public int getCarID() {
        return carID;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

}
