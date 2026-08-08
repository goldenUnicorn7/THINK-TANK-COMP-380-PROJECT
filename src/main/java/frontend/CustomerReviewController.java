package frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CustomerReviewController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    Image logoImage = new Image(getClass().getResourceAsStream("/frontend/img/logo.png"));

    @FXML
    public void displayImage(ImageView logoImageView) {
        logoImageView.setImage(logoImage);
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
