package frontend;

import javafx.fxml.FXML;
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

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public void loginUser(ActionEvent event) throws IOException{
        String username = userName.getText();
        String password = userPassword.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        
    }


}
