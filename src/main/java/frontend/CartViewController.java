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

/**
 * CartViewController
 * Date: June 30, 2026
 * Programmers: Emily Honarchian, Snigdha Bolisetty
 * Description: Manages the UI for viewing the user's cart, displays all cars in the cart in a table, allows user to remove cars from their cart, and can navigate to checkout or main page.
 * Functions: Methods in this class load and display list of cars in the cart, remove selected car from the cart, and change screens to checkout or main page.
 * Data Structures: TableView<Car> - holds and displays list of cars in the cart, ObservableList<Car> - updates the TableView with any changes in the cart
 * Algorithm: A car is removed from the table if it is successfully removed from the cart in the backend database.
 */
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

    /**
     * Sets up the table columns and loading the cart items by initializing the controller.
     */
    @FXML
    public void initialize() {
        setupTableColumns();
        loadCartItems();
    }

    /**
     * Sets up the table columns for the cart table.
     */
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

    /**
     * Loads the cart items for the logged-in user and displays them in the table.
     */
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

    /**
     * Navigates to the checkout screen.
     * @param event is the action event triggered by clicking the checkout button.
     */
    @FXML
    public void checkout(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/Checkout.fxml"));
    root = loader.load();

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

    /**
     * Navigates back to the main page.
     * @param event is the action event triggered by clicking the main menu button.
     */
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the available cars screen.
     * @param event is the action event triggered by clicking the available cars button.
     */
    @FXML
    public void goToAvailableCars(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/AvailableCars.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Removes the selected car from the user's cart.
     * @param event is the action event triggered by clicking the remove button.
     */
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