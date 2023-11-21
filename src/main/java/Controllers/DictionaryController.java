package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        searchWordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/GUI/SearcherGui.fxml");
            }
        });

        addWordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/GUI/AdditionGui.fxml");
            }
        });

        translateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/GUI/TranslationGui.fxml");
            }
        });

        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/GUI/MenuGUI.fxml");
            }
        });

        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        showComponent("/GUI/MenuGui.fxml");

        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    private void showComponent(String path) {
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
    private Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn;

    @FXML
    private AnchorPane container;
}
