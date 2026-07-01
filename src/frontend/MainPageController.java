package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class MainPageController {
    
    @FXML
    private Button searchButton;
    
    @FXML 
    private Button cartButton;

    @FXML
    private Button viewCars;


    private Parent root;
    private Stage stage;
    private Scene scene;


    @FXML
    public void goToCart(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartView.fxml"));

        root = loader.load();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
