package frontend;

import java.io.IOException;
import java.util.List;

import backend.dao.carDAO;
import backend.model.Car;
import backend.service.CartService;
import backend.session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SearchViewController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Car> carTableView;

    @FXML
    private TableColumn<Car, String> carColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TableColumn<Car, String> availabilityColumn;

    private final carDAO carDao = new carDAO();
    private final CartService cartService = new CartService();

    //some of the logic for adding the image. you can change url incase it dont work
    @FXML
    ImageView myImageView;
    Image myImage = new Image("file:src/main/resources/frontend/img/ford_must.png");

    @FXML
    public void displayImage() {
        myImageView.setImage(myImage);
        
    }

   



    @FXML
    public void initialize() {
        System.out.println("SearchViewController initialize() is running");

        carColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

       

        loadAllCars();
    }

    private void loadAllCars() {
        List<Car> cars = carDao.getAllCars();

        System.out.println("Cars received from DAO: " + cars.size());

        carTableView.setItems(FXCollections.observableArrayList(cars));

        System.out.println("Cars loaded into table: " + carTableView.getItems().size());
    }

    @FXML
    public void searchCars(ActionEvent event) {
        String keyword = searchField.getText();

        System.out.println("Searching for: " + keyword);

        List<Car> cars;

        if (keyword == null || keyword.trim().isEmpty()) {
            cars = carDao.getAllCars();
        } else {
            cars = carDao.searchCars(keyword.trim());
        }

        System.out.println("Search results: " + cars.size());

        carTableView.setItems(FXCollections.observableArrayList(cars));
    }

    @FXML
    public void addToCart(ActionEvent event) {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();

        if (selectedCar == null) {
            System.out.println("No car selected.");
            return;
        }

        if (session.getCurrentUser() == null) {
            System.out.println("No logged-in user found.");
            return;
        }

        int userId = session.getCurrentUser().getUserId();
        int carId = selectedCar.getCarID();

        System.out.println("Selected car: " + selectedCar.getCarBrand() + " " + selectedCar.getCarModel());
        System.out.println("User ID: " + userId);
        System.out.println("Car ID: " + carId);

        boolean added = cartService.addToCart(userId, carId);

        if (added) {
            System.out.println("Car added to cart from search.");
        } else {
            System.out.println("Failed to add car to cart from search.");
        }
    }

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}