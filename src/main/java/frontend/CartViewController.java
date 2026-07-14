package frontend;

import backend.model.Car;
import backend.service.CartService;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

public class CartViewController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    private final CartService cartService = new CartService();

    private int loggedInUserId = 1;

    @FXML
    private TableView<Car> cartTable;

    @FXML
    private TableColumn<Car, String> carColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Integer> daysColumn;

    @FXML
    private TableColumn<Car, Double> totalColumn;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button checkoutButton;

    @FXML
    private Button availableCarsButton;

    @FXML
    private Button removeButton;

    @FXML
    private Label messageLabel;

    private ObservableList<Car> cartCars = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadCartItems();
    }

    private void setupTableColumns() {
        carColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));

        daysColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(1).asObject()
        );

        totalColumn.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject()
        );
    }

    private void loadCartItems() {
        try {
            List<Car> cars = cartService.getCartCars(loggedInUserId);

            cartCars = FXCollections.observableArrayList(cars);
            cartTable.setItems(cartCars);

            System.out.println("Cart items loaded: " + cars.size());

            if (messageLabel != null) {
                messageLabel.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (messageLabel != null) {
                messageLabel.setText("Could not load cart items.");
            }
        }
    }

    @FXML
public void checkout(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/Checkout.fxml"));
    root = loader.load();

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToAvailableCars(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/AvailableCars.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void removeSelectedCar(ActionEvent event) {
        Car selectedCar = cartTable.getSelectionModel().getSelectedItem();

        if (selectedCar == null) {
            if (messageLabel != null) {
                messageLabel.setText("Please select a car to remove.");
            }
            return;
        }

        boolean removed = cartService.removeFromCart(loggedInUserId, selectedCar.getCarID());

        if (removed) {
            cartCars.remove(selectedCar);
            cartTable.refresh();

            if (messageLabel != null) {
                messageLabel.setText("Car removed from cart.");
            }
        } else {
            if (messageLabel != null) {
                messageLabel.setText("Could not remove car.");
            }
        }
    }
}