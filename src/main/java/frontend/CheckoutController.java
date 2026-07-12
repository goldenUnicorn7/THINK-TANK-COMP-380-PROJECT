package frontend;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckoutController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void goToOrderConfirmationTicket(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderConfirmationScreen.fxml"));
        root = loader.load();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }   
}
