package Controllers;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/DictionaryWindow.fxml"));
        Scene adderScene = new Scene(root, 600, 400);
        stage.setScene(adderScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
