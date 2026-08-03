package frontend;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import backend.model.Car;
import backend.service.BookingService;
import backend.service.CartService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CheckoutController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    private final CartService cartService = new CartService();
    private final BookingService bookingService = new BookingService();

    // Temporary testing UserID.
    // Must match AvailableCarsController and CartViewController.
    private int loggedInUserId = 1;

    @FXML
    private TableView<Car> checkoutTable;

    @FXML
    private TableColumn<Car, Integer> carIdColumn;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TableColumn<Car, Integer> daysColumn;

    @FXML
    private TableColumn<Car, Double> totalColumn;

    @FXML
    private DatePicker pickupDatePicker;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private ComboBox<String> paymentMethodComboBox;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Button confirmBookingButton;

    @FXML
    private Button backButton;

    @FXML 
    ImageView logoImageView;
    Image logoImage = new Image(getClass().getResourceAsStream("/frontend/img/logo.png"));
    
    @FXML
    public void displayImage() {
        logoImageView.setImage(logoImage);
        
    }

    private ObservableList<Car> cartCars = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("CheckoutController initialize() running...");

        setupTableColumns();
        setupPaymentMethods();
        setDefaultDates();
        loadCartCars();

        pickupDatePicker.setOnAction(event -> updateTotalAmount());
        returnDatePicker.setOnAction(event -> updateTotalAmount());
    }

    private void setupTableColumns() {
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        daysColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(calculateDays()).asObject()
        );

        totalColumn.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice() * calculateDays()).asObject()
        );
    }

    private void setupPaymentMethods() {
        paymentMethodComboBox.getItems().clear();
        paymentMethodComboBox.getItems().addAll(
                "Credit Card",
                "Debit Card",
                "Cash",
                "Online Payment"
        );
    }

    private void setDefaultDates() {
        LocalDate today = LocalDate.now();

        pickupDatePicker.setValue(today);
        returnDatePicker.setValue(today.plusDays(1));
    }

    private void loadCartCars() {
        try {
            List<Car> cars = cartService.getCartCars(loggedInUserId);

            cartCars = FXCollections.observableArrayList(cars);
            checkoutTable.setItems(cartCars);

            System.out.println("Checkout cart items loaded: " + cars.size());

            updateTotalAmount();

            if (messageLabel != null) {
                messageLabel.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (messageLabel != null) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Could not load checkout items.");
            }
        }
    }

    private int calculateDays() {
        LocalDate pickupDate = pickupDatePicker.getValue();
        LocalDate returnDate = returnDatePicker.getValue();

        if (pickupDate == null || returnDate == null) {
            return 1;
        }

        long days = ChronoUnit.DAYS.between(pickupDate, returnDate);

        if (days <= 0) {
            return 1;
        }

        return (int) days;
    }

    private double calculateTotalAmount() {
        int days = calculateDays();
        double total = 0.0;

        for (Car car : cartCars) {
            total += car.getPrice() * days;
        }

        return total;
    }

    private void updateTotalAmount() {
        double total = calculateTotalAmount();

        if (totalAmountLabel != null) {
            totalAmountLabel.setText("$" + String.format("%.2f", total));
        }

        if (checkoutTable != null) {
            checkoutTable.refresh();
        }
    }

    @FXML
    public void confirmBooking(ActionEvent event) {
        try {
            LocalDate pickupDate = pickupDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            String paymentMethod = paymentMethodComboBox.getValue();

            if (cartCars == null || cartCars.isEmpty()) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Your cart is empty.");
                return;
            }

            if (pickupDate == null || returnDate == null) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Please select pickup and return dates.");
                return;
            }

            if (!returnDate.isAfter(pickupDate)) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Return date must be after pickup date.");
                return;
            }

            if (paymentMethod == null || paymentMethod.isBlank()) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Please select a payment method.");
                return;
            }

            boolean allBooked = true;

            for (Car car : cartCars) {
                double carTotal = car.getPrice() * calculateDays();

                boolean booked = bookingService.createBooking(
                        loggedInUserId,
                        car.getCarID(),
                        pickupDate,
                        returnDate,
                        carTotal
                );

                if (!booked) {
                    allBooked = false;
                }
            }

            if (allBooked) {
                double finalTotal = calculateTotalAmount();

                // used in confirm screen
                int days = calculateDays();
                List<Car> purchasedCars = List.copyOf(cartCars);
                
                cartService.clearCart(loggedInUserId);
                cartCars.clear();
                checkoutTable.refresh();
                updateTotalAmount();

                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Booking confirmed successfully. Total: $" + String.format("%.2f", finalTotal));

                //goto confirmation screen
                goToConfirmationScreen(event, purchasedCars, days, finalTotal);

            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Some bookings failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Booking failed. Check terminal.");
        }
    }


    // navigate to confirmation screen
    @FXML 
    public void goToConfirmationScreen(ActionEvent event, List<Car> purchasedCars, int days, double totalAmount) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/ConfirmationScreen.fxml"));
        root = loader.load();

        ConfirmationScreenController confirmationController = loader.getController();
        confirmationController.setReceiptData(purchasedCars, days, totalAmount);


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goBackToCart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/CartView.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}