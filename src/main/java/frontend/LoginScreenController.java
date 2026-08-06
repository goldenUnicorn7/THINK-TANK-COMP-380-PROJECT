package frontend;

import java.io.IOException;

import backend.model.User;
import backend.service.UserService;
import backend.session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * LoginScreenController
 * Date: June 30, 2026
 * Programmer: Emily Honarchian, Snigdha Bolisetty
 * Description: Manages the UI for the login screen. It verifies a user's email & password, and will also navigate user to main page screen if log in is successful.
 * Functions: The methods validate the login input from the user, logs in the account, changes the screens based on user inputs, displays an image and gives error message alerts to the user. 
 * Data Structures: No important data structures
 * Algorithm: Login checks email and password against UserService for verification, and is used because it is only two fields that need to be checked.
 */
public class LoginScreenController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label messageLabel;

    @FXML
    private Button loginButton;

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


    private final UserService userService = new UserService();

    private Parent root;
    private Stage stage;
    private Scene scene;

    /**
     * Login button styling setup
     */
    @FXML
    public void initialize() {
        // This is for styling buttons. was testing
        // if we dont like it we can remove it/change it.
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        loginButton.setOnMouseEntered(event -> {
            loginButton.setStyle("-fx-background-color: #a04588; -fx-text-fill: white; -fx-font-weight: bold;");
        });

        loginButton.setOnMouseExited(event -> {
            loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        });
    }

    /**
     * verifies email and password, logs in user, navigates to main page
     * @param event event triggered by clicking the login button.
     */
    @FXML
    public void loginUser(ActionEvent event) throws IOException {
        String email = userName.getText();
        String password = userPassword.getText();


        if (email == null || email.trim().isEmpty()) {
            showMessage("Please enter your email.");
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            showMessage("Please enter your password.");
            return;
        }

        User user = userService.login(email.trim(), password.trim());

        if (user != null) {

            // IMPORTANT: save logged-in user here
            session.setCurrentUser(user);

            System.out.println("Login successful. Welcome " + user.getName());
            System.out.println("Session user saved with ID: " + session.getCurrentUser().getUserId());

            goToMainPage(event);

        } else {
            showMessage("Invalid email or password.");
            System.out.println("Invalid email or password.");
        }
    }

    /**
     * Navigates to the main page screen.
     * @param event event triggered by clicking the button.
     */
    private void goToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/frontend/MainPage.fxml")
        );

        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * displays message on login screen
     * @param message the message displayed on the screen.
     */
    private void showMessage(String message) {
        if (messageLabel != null) {
            messageLabel.setText(message);
        }
    }
}