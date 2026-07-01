package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import org.w3c.dom.Node;

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

    @FXML
    public void goToSearch(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchView.fxml"));

        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToAvailableCars(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AvailableCarsView.fxml"));

        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




}
