package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class DictionaryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchWordBtn.setOnAction(e -> showComponent("/GUI/SearcherGui.fxml"));

        addWordBtn.setOnAction(e -> showComponent("/GUI/AdditionGui.fxml"));

        translateBtn.setOnAction(e -> showComponent("/GUI/TranslationGui.fxml"));

        menuBtn.setOnAction(e -> showComponent("/GUI/MenuGUI.fxml"));

        gameBtn.setOnAction(e -> showComponent("/GUI/MenuGameGUI.fxml"));

        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        showComponent("/GUI/MenuGUI.fxml");

        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    protected void setNode(Node node) {
         container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    protected void showComponent(String path) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            setNode(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Tooltip tooltip1, tooltip2, tooltip3;

    @FXML
    private Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;

    @FXML
    private AnchorPane container;
}
