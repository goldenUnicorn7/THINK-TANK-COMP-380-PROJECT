package frontend;

import java.io.IOException;
import java.util.List;

import backend.model.Car;
import backend.service.CartService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    private VBox cartItemsContainer;

    @FXML
    private Label totalLabel;

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


    /**
     * Sets up the table columns and loading the cart items by initializing the controller.
     */
    @FXML
    public void initialize() {
        loadCartItems();
    }

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
     * Sets up the table columns for the cart table.
     */


    /**
     * Loads the cart items for the logged-in user and displays them in the table.
     */
    private void loadCartItems() {
        try {
            List<Car> cars = cartService.getCartCars(loggedInUserId);

            cartItemsContainer.getChildren().clear();
            double totalPrice = 0.0;

            for(Car car : cars) {
                cartItemsContainer.getChildren().add(createCartItemCard(car));
                totalPrice += car.getPrice();
            }

            if(totalLabel != null) {
                totalLabel.setText(String.format("Total: $%.2f", totalPrice));
            }

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

    private HBox createCartItemCard(Car car) {
        HBox itemCard = new HBox(20);
        itemCard.setAlignment(Pos.CENTER_LEFT);
        itemCard.setPrefSize(600, 110);
        itemCard.setStyle(
            "-fx-border-color: black;" +
            "-fx-border-width: 1;" +
            "-fx-background-color: #f0f0f0;"
        );

        ImageView carImage = new ImageView(getCarImage(car));
        carImage.setFitHeight(80);
        carImage.setFitWidth(120);
        carImage.setPreserveRatio(true);
        
        Label carNameLabel = new Label(car.getCarBrand() + " " + car.getCarModel());
        carNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label priceLabel = new Label("$" + String.format("%.2f", car.getPrice()));
        priceLabel.setStyle("-fx-font-size: 16px;");

        Label daysLabel = new Label("Days 1");

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
        boolean removed = cartService.removeFromCart(loggedInUserId, car.getCarID());

        if (removed) {
            if (messageLabel != null) {
                messageLabel.setText("Car removed from cart.");
            }

            loadCartItems();
        } else {
            if (messageLabel != null) {
                messageLabel.setText("Could not remove car.");
            }
        }
     });

        VBox infoBox = new VBox(8);
        infoBox.getChildren().addAll(carNameLabel, daysLabel, removeButton);

        Region spacer = new Region();
        spacer.setPrefWidth(170);

        VBox priceBox = new VBox(8);
        priceBox.setAlignment(Pos.CENTER_RIGHT);
        priceBox.getChildren().add(priceLabel);

        itemCard.getChildren().addAll(carImage, infoBox, spacer, priceBox);

        return itemCard;
    }

    private Image getCarImage(Car car) {
        String brand = car.getCarBrand().toLowerCase();
        String model = car.getCarModel().toLowerCase();

        String imagePath;

        if (brand.contains("ford") && model.contains("must")) {
            imagePath = "/frontend/img/ford_must.png";
        } else if (brand.contains("honda")) {
            imagePath = "/frontend/img/hondacivic.png";
        } else if (brand.contains("tesla")) {
            imagePath = "/frontend/img/teslamodel3.png";
        } else if (brand.contains("toyota")) {
            imagePath = "/frontend/img/toyotaCamry.png";
        } else {
            imagePath = "/frontend/img/default_car.png";
        }

        return new Image(getClass().getResourceAsStream(imagePath));
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
    
}