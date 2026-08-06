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

/**
 * Class Name: SearchViewController
 * Date: August 4, 2026
 * Co-Programmer: Jose Beltran
 *
 * Description:
 * SearchViewController manages the search function of the car rental app.
 * Users can search for cars by name. Currently it is set for table view,
 * but we have a tilepane view already in the works.
 *
 * Important Functions:
 * initialize() fetches all cars from the database and populates the TableView.
 * loadAllCars() helper for initialize() to load all cars.
 * searchCars() filters cars based on user input in the search field.
 * addToCart() adds the selected car to the user's cart if logged in.
 * goToMainMenu() navigates back to the main menu of the application.
 *
 * Important Data Structures:
 * List<Car> holds the list of cars fetched from the database.
 * ObservableList<Car>  used to translate List<Car> for JavaFX TableView.
 * TableView<Car>  displays the list of cars in a table format.
 *
 * Algorithm:
 * Load all cars from database from carDAO. Then we list those cars. That list is then 
 * translated so that it can be displayed properly. When the user searches for a car, refresh
 * with the current filters. When the user adds a car from 'add to cart', store the selected car
 * for cart service to reference.  
 *
 * @co-authored Jose Beltran
 * @version 1.0
 */

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
    ImageView logoImageView;
    Image logoImage = new Image(getClass().getResourceAsStream("/frontend/img/logo.png"));
    
    @FXML
    public void displayImage() {
        myImageView.setImage(myImage);
        logoImageView.setImage(logoImage);        
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

/*
While not current apart of the program, these will be used to move to a more sleek design.
Idea: Instead of a table view, we can use a tile pane to display the cars. 
Each car will be represented as a card with its image, brand, model, price, and availability. 
Users can click on a card to add the car to their cart.

 public VBox createCarCard(Car car) {
        VBox card = new VBox(8);
        card.setPrefSize(350, 220);
        card.setMinSize(350,220);
        card.setMaxSize(350,220);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #d0d0d0;" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 15;"
        );

        Label brandLabel = new Label(car.getCarBrand());
        brandLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label modelLabel = new Label(car.getCarModel());
        Label peopleLabel = new Label("5 people");

        Label priceLabel = new Label("$" + car.getPrice() + " per day");
        priceLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label availabilityLabel = new Label(car.getAvailability());
        availabilityLabel.setStyle("-fx-text-fill: green;");

        VBox textBox = new VBox(6);
        textBox.getChildren().addAll(brandLabel, modelLabel, peopleLabel);
        
        ImageView carImageView = new ImageView();
        carImageView.setImage(getCarImage(car));
        carImageView.setFitWidth(120);
        carImageView.setFitHeight(80);
        carImageView.setPreserveRatio(true);
    
        return card;
}

*/