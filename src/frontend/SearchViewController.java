package frontend;

import java.io.IOException;

import backend.model.Car;
import backend.service.CarService;
import backend.service.CartService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchViewController {

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Car> searchResults;

    private final CarService carService = new CarService();
    private final CartService cartService = new CartService();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {
        // Later, load cars into the table here using carService.
    }

    @FXML
    public void searchCars(ActionEvent event) {
        String keyword = searchBar.getText();

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Please enter something to search.");
            return;
        }

        System.out.println("Searching for: " + keyword);
    }

    @FXML
    public void addToCart(ActionEvent event) {
        Car selectedCar = searchResults.getSelectionModel().getSelectedItem();

        if (selectedCar == null) {
            System.out.println("Please select a car first.");
            return;
        }

        System.out.println("Selected car added to cart: " + selectedCar);
    }

    @FXML
    public void goToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}