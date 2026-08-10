package frontend;

import java.io.IOException;
import java.util.List;

import backend.model.Car;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ConfirmationScreenController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    private int receiptDays = 1;
    
    @FXML
    private TableView<Car> receiptTable;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TableColumn<Car, Integer> daysColumn;

    @FXML
    private TableColumn<Car, Double> totalPriceColumn;

    @FXML 
    ImageView logoImageView;
    Image logoImage = new Image(getClass().getResourceAsStream("/frontend/img/logo.png"));
    
    @FXML
    public void displayImage() {
        logoImageView.setImage(logoImage);
        
    }

    //table for the 'receipt' of what the user purchased
    @FXML
    public void initialize() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        daysColumn.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(receiptDays).asObject());

        totalPriceColumn.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice() * receiptDays).asObject()
        );
    }

    public void setReceiptData(List<Car> purchasedCars, int days, double totalPrice) {
        this.receiptDays = days;

        daysColumn.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(receiptDays).asObject());

        totalPriceColumn.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice() * receiptDays).asObject()
        );

        receiptTable.setItems(FXCollections.observableArrayList(purchasedCars));
        receiptTable.refresh();
    }

    @FXML
    public void goToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/SearchView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToReview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/ReviewScreen.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
