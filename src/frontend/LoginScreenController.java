package frontend;

import backend.model.User;
import backend.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label messageLabel;

    private Parent root;
    private Stage stage;
    private Scene scene;

    private final UserService userService = new UserService();

    @FXML
    public void loginUser(ActionEvent event) throws IOException {
        String email = userName.getText();
        String password = userPassword.getText();

        User user = userService.login(email, password);

        if (user != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            System.out.println("Login successful. Welcome " + user.getName());
        } else {
            if (messageLabel != null) {
                messageLabel.setText("Invalid email or password.");
            }

            System.out.println("Invalid email or password.");
        }
    }
}