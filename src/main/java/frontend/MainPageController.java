package frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * MainPageController
 * Date: June 30, 2026
 * Programmers: Emily Honarchian
 * Description: Manages the UI for the main page. It allows user to navigate to search, cart, and available cars screens.
 * Functions: The methods change the screens based on user inputs, displays an image.
 * Data Structures: No important data structures
 * Algorithm: No important algorithms
 */

public class MainPageController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    ImageView logoImageView;

    Image logoImage = new Image(getClass().getResourceAsStream("/frontend/img/logo.png"));
    /**
     * Displays image (logo) on the screen
     */
    @FXML
    public void displayImage() {
        logoImageView.setImage(logoImage);
        
    }

    /**
     * Navigates to search screen.
     * @param event event that triggers navigation to screen through button click.
     */
    @FXML
    public void goToSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/SearchView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to cart view screen.
     * @param event event that triggers navigation to screen through button click.
     */
    @FXML
    public void goToCart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/CartView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to available cars screen.
     * @param event event that triggers navigation to screen through button click.
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
}