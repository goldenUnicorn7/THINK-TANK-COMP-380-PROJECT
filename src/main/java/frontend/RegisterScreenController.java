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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterScreenController {
    
    @FXML 
    private TextField registerEmail;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private void goToSignInScreen(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/LoginScreenView.fxml"));

        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void goToMainMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/MainPage.fxml"));

        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

}
