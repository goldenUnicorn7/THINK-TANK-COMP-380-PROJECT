package frontend;

import java.io.IOException;

import backend.model.User;
import backend.service.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();

    private Parent root;
    private Stage stage;
    private Scene scene;

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

        User user = userService.login(email, password);

        if (user != null) {
            System.out.println("Login successful. Welcome " + user.getName());
            goToMainPage(event);
        } else {
            showMessage("Invalid email or password.");
            System.out.println("Invalid email or password.");
        }
    }

    private void goToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void showMessage(String message) {
        if (messageLabel != null) {
            messageLabel.setText(message);
        }
    }
}