package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/frontend/RegisterScreen.fxml");

        if (fxmlLocation == null) {
            throw new RuntimeException("Cannot find RegisterScreen.fxml in src/main/resources/frontend");
        }

        Parent root = FXMLLoader.load(fxmlLocation);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Car Rental System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}