package frontend;

import java.io.IOException;

import backend.model.Car;
import backend.model.User;
import backend.service.CartService;
import backend.session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.stage.Stage;
/**
 * AddToCartController
 * Date: July 12, 2026
 * Programmer: Emily Honarchian, Snigdha Bolisetty
 * Description: Manages the UI for adding an available car to user's cart. Reads and saves selected car to the cart, and notifies user if the car was added sucessfully. Also manages navigating back to available cars screen or to cart screen.
 * Functions: Methods in this class change screens, display alerts, get the selected car from user, saves it to the cart, and updates the screen by giving a message on the status of whether the car could be saved to the cart or not. 
 * Data Structures: TableView<Car> is used, which holds list of available cars and allows user to select a car to add to cart.
 * Algorithm: No complex algorithm is used.
*/
public class AddToCartController {

    @FXML
    private TableView<Car> carTable;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button goBackToStore;

    @FXML
    private Button goToCart;

    @FXML
    private Label messageLabel;

    private final CartService cartService = new CartService();

    /** 
     * Adds the selected car to the user's cart. Input: Button clicked, output: saves selected car to cart and displays message on screen
     */
    @FXML
    private void addSelectedCarToCart(ActionEvent event) {

        Car selectedCar = carTable
                .getSelectionModel()
                .getSelectedItem();

        if (selectedCar == null) {
            messageLabel.setText("Please select a car first.");
            return;
        }

        User currentUser = session.getCurrentUser();

        if (currentUser == null) {
            messageLabel.setText("Please log in before adding a car.");
            System.out.println("No current user found in session.");
            return;
        }

        int userId = currentUser.getUserId();
        int carId = selectedCar.getCarID();

        System.out.println("Attempting to add car to cart.");
        System.out.println("User ID: " + userId);
        System.out.println("Car ID: " + carId);

        try {
            boolean added = cartService.addToCart(userId, carId);

            if (added) {
                messageLabel.setText("Car added to cart successfully.");
            } else {
                messageLabel.setText("Failed to add car to cart.");
            }

        } catch (Exception e) {
            e.printStackTrace();

            messageLabel.setText("Database error while adding car.");

            showAlert(
                    Alert.AlertType.ERROR,
                    "Add To Cart Error",
                    e.getMessage() == null
                            ? "An unknown database error occurred."
                            : e.getMessage()
            );
        }
    }

    /**
     * Navigates to the view cart screen. input: button clicked, output: changes scene
     */
    @FXML
    public void goToCartScreen(ActionEvent event) {
        changeScene(event, "/frontend/CartView.fxml");
    }

    /**
     * Navigates to the available cars screen
     * Input: Button clicked, output: changes scene
     */
    @FXML
    public void goToAvailableCars(ActionEvent event) {
        changeScene(event, "/frontend/AvailableCars.fxml");
    }
   
    /**
     * Loads FXML file and changes the scene to the fxml path.
     * @param event event that triggers scene change from a button click
     * @param fxmlPath the path to the FXML file that will load
     */
    private void changeScene(
            ActionEvent event,
            String fxmlPath
    ) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlPath)
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Screen Loading Error",
                    "Could not open: " + fxmlPath
            );
        }
    }

    /**
     * Displays an alert popup message 
     * @param type type of alert
     * @param title title of the alert shown
     * @param message the message that is displayed in the alert
     */
    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}