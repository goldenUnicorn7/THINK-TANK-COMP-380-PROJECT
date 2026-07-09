package frontend;

import java.io.IOException;
import java.util.List;

import backend.dao.carDAO;
import backend.model.Car;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CarViewController {

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

    @FXML
    public void initialize() {
        System.out.println("CarViewController initialize() is running");

        carColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        List<Car> cars = carDao.getAllCars();

        System.out.println("Cars received from DAO: " + cars.size());

        carTableView.setItems(FXCollections.observableArrayList(cars));

        System.out.println("Cars loaded into table: " + carTableView.getItems().size());
    }

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addToCart(ActionEvent event) {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();

        if (selectedCar == null) {
            System.out.println("No car selected.");
            return;
        }

        System.out.println("Selected car: " + selectedCar.getCarBrand() + " " + selectedCar.getCarModel());

        // Later you can connect this to CartService / CartDAO.
    }
}