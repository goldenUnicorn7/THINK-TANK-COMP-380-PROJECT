package frontend;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CustomerReviewController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private VBox reviewContainer;


    @FXML
    private TextArea reviewTextArea;


    @FXML
    private Label messageLabel;


    Image logoImage = new Image(getClass().getResourceAsStream("/frontend/img/logo.png"));


    @FXML
    public void displayImage(ImageView logoImageView) {
        logoImageView.setImage(logoImage);
    }


    @FXML
    public void initialize() {
        // Load existing reviews from the database and display them in the reviewContainer
        loadExistingReviews();
    }


    private void loadExistingReviews() {
        // Clear the reviewContainer before loading new reviews
        reviewContainer.getChildren().clear();


        addReviewCard("Jose ","Great service! The car was clean and well-maintained.");
        addReviewCard("Emily ","The booking process was smooth and hassle-free.");
        addReviewCard("Snigdha ","I had a wonderful experience renting a car from this service.");
       
    }


    private void addReviewCard(String customerName, String reviewText) {
        Label reviewLabel = new Label(customerName + ": " + reviewText);
        reviewLabel.setWrapText(true);
        reviewLabel.setMaxWidth(700);
        reviewLabel.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5; -fx-font-size: 14px;");
        reviewContainer.getChildren().add(reviewLabel);
    }


    @FXML
    public void submitReview(ActionEvent event) {
        String reviewText = reviewTextArea.getText();
        if (reviewText == null || reviewText.trim().isEmpty()) {
            messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            messageLabel.setText("Please enter a review before submitting.");
            return;
        }
        addReviewCard("You", reviewText);
       
        reviewTextArea.clear();
       
        messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
        messageLabel.setText("Thank you for your review!");
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


}
