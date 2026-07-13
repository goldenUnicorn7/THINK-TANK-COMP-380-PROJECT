package frontend;

import backend.model.Car;
import backend.service.CarService;
import backend.service.CartService;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

public class AvailableCarsController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    private final CarService carService = new CarService();
    private final CartService cartService = new CartService();

    private int loggedInUserId = 1;

    @FXML
    private TableView<Car> availableCarsTable;

    @FXML
    private TableColumn<Car, Integer> carIdColumn;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, String> colorColumn;

    @FXML
    private TableColumn<Car, Integer> yearColumn;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TableColumn<Car, String> statusColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button goToCartButton;

    @FXML
    private Button backButton;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        setupTableColumns();
        loadCars();
    }

    private void setupTableColumns() {
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("carColor"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("carYear"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    private void loadCars() {
        try {
            List<Car> cars = carService.getAllCars();

            ObservableList<Car> carList = FXCollections.observableArrayList(cars);
            availableCarsTable.setItems(carList);

            if (messageLabel != null) {
                messageLabel.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (messageLabel != null) {
                messageLabel.setText("Could not load cars.");
            }
        }
    }

    @FXML
    public void searchCars(ActionEvent event) {
        try {
            String keyword = "";

            if (searchField != null) {
                keyword = searchField.getText();
            }

            List<Car> cars = carService.searchCars(keyword);

            ObservableList<Car> carList = FXCollections.observableArrayList(cars);
            availableCarsTable.setItems(carList);

            if (messageLabel != null) {
                messageLabel.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (messageLabel != null) {
                messageLabel.setText("Search failed.");
            }
        }
    }

    @FXML
    public void refreshCars(ActionEvent event) {
        if (searchField != null) {
            searchField.clear();
        }

        loadCars();
    }

    @FXML
    public void addSelectedCarToCart(ActionEvent event) throws IOException {
        Car selectedCar = availableCarsTable.getSelectionModel().getSelectedItem();

        if (selectedCar == null) {
            if (messageLabel != null) {
                messageLabel.setText("Please select a car first.");
            }
            return;
        }

        int selectedCarId = selectedCar.getCarID();

        boolean added = cartService.addToCart(loggedInUserId, selectedCarId);

        if (added) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/AddToCartScreen.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            if (messageLabel != null) {
                messageLabel.setText("Failed to add car to cart.");
            }
        }
    }

    @FXML
    public void goToCartScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/CartView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goBackToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}