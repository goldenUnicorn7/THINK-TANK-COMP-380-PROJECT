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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * RegisterScreenController
 * Date: July 27, 2026
 * Programmer: Emily Honarchian, Snigdha Bolisetty
 * Description: Manages the UI for the registration screen. It verifies a new user's email & passowrd, and will also navigate user to login screen if customer is already a verified user. 
 * Functions: The methods validate the registration input from the user, create display/placeholder account name, logs in the new account or allows signing in to existing account, changes the screens based on user inputs, displays an image and gives error message alerts to the user. 
 * Data Structures: No important data structures
 * Algorithm: Checks to see if email matches a pattern, and is used because it is only one field that needs to be checked.
 */
public class RegisterScreenController {

    @FXML
    private TextField registerEmail;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    
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

    /**
     * Opens the login screen when the Sign In button is clicked.
     */
    @FXML
    private void goToSignInScreen(ActionEvent event) {
        changeScreen(event, "/frontend/LoginScreenView.fxml");
    }

    /**
     * Creates a new user when the Sign Up button is clicked.
     */
    @FXML
    private void registerUser(ActionEvent event) {

        String email = registerEmail.getText().trim();
        String password = registerPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please enter an email address and password."
            );
            return;
        }

        if (!isValidEmail(email)) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Email",
                    "Please enter a valid email address."
            );
            return;
        }

        if (password.length() < 4) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Password",
                    "The password must contain at least 4 characters."
            );
            return;
        }

        try {
            /*
             * Your current registration page only collects email and password,
             * but UserService.register() also needs a phone number and name.
             *
             * The name is generated from the part of the email before @.
             * A temporary phone number is generated using the current time.
             */
            String name = createNameFromEmail(email);
            String temporaryPhone = createTemporaryPhone();

            boolean registered = userService.register(
                    temporaryPhone,
                    password,
                    email,
                    name
            );

            if (!registered) {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Registration Failed",
                        "The account could not be created.\n"
                                + "This email may already be registered."
                );
                return;
            }

            /*
             * Log the new user in after registration.
             */
            User registeredUser = userService.login(email, password);

            if (registeredUser == null) {
                showAlert(
                        Alert.AlertType.WARNING,
                        "Account Created",
                        "Your account was created, but automatic login failed.\n"
                                + "Please sign in manually."
                );

                changeScreen(event, "/frontend/LoginScreenView.fxml");
                return;
            }

            /*
             * Save the logged-in user so other controllers can access
             * the user's ID and account information.
             */
            session.setCurrentUser(registeredUser);

            System.out.println(
                    "Registration successful. User ID: "
                            + registeredUser.getUserId()
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Registration Successful",
                    "Your account has been created successfully."
            );

            changeScreen(event, "/frontend/MainPage.fxml");

        } catch (Exception e) {
            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Registration Error",
                    "An error occurred while creating the account.\n\n"
                            + getErrorMessage(e)
            );
        }
    }

    /**
     * Changes the current JavaFX scene.
     */
    private void changeScreen(ActionEvent event, String fxmlPath) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlPath)
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Screen Loading Error",
                    "Could not open the requested screen:\n" + fxmlPath
            );
        }
    }

    /**
     * Performs a basic email format check.
     */
    private boolean isValidEmail(String email) {
        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    /**
     * Creates a display name from the email address.
     *
     * Example:
     * testuser@gmail.com becomes testuser
     */
    private String createNameFromEmail(String email) {

        int atPosition = email.indexOf("@");

        if (atPosition > 0) {
            return email.substring(0, atPosition);
        }

        return "New User";
    }

    /**
     * Creates a temporary phone number because the current registration
     * screen does not contain a phone-number field.
     */
    private String createTemporaryPhone() {

        String currentTime = String.valueOf(System.currentTimeMillis());

        return currentTime.substring(currentTime.length() - 10);
    }

    /**
     * Displays a JavaFX alert.
     */
    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message
    ) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Prevents a null exception message from appearing in the alert.
     */
    private String getErrorMessage(Exception exception) {

        if (exception.getMessage() == null
                || exception.getMessage().isBlank()) {

            return exception.getClass().getSimpleName();
        }

        return exception.getMessage();
    }
}