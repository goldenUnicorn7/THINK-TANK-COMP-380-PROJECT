package frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import backend.service.CarService;
import backend.service.CartService;
import backend.service.UserService;

public class MainPageController {

    @FXML
    private Button searchButton;

    @FXML
    private Button cartButton;

    @FXML
    private Button viewCars;

    private final CarService carService = new CarService();
    private final CartService cartService = new CartService();
    private final UserService userService = new UserService();

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {
        // Services are connected here for later use.
        // carService, cartService, and userService can be used when needed.
    }

    @FXML
    public void goToCart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToAvailableCars(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvailableCarsView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}