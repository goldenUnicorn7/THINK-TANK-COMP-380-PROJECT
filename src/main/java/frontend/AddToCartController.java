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

    @FXML
    public void goToCartScreen(ActionEvent event) {
        changeScene(event, "/frontend/CartView.fxml");
    }

    @FXML
    public void goToAvailableCars(ActionEvent event) {
        changeScene(event, "/frontend/AvailableCars.fxml");
    }

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