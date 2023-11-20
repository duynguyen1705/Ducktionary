package Controllers;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

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
import javafx.scene.layout.AnchorPane;

public class Main implements Initializable {
  @FXML
  private Button SearchButton;
  @FXML
  private Button AddButton;
  @FXML
  private Button TranslateButton;
  @FXML
  private Button ExitButton;
  @FXML
  private AnchorPane container;
  private void setNode(Node node) {
    container.getChildren().clear();
    container.getChildren().add(node);
  }
  private void showComponent(String path) {
    try {
      AnchorPane component = FXMLLoader.load(getClass().getResource(path));

    }
    catch (IOException e) {
      e.printStackTrace();
    }

  }
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
      SearchButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          showComponent(Path.getSearchGUI());
        }
      });
      AddButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          showComponent(Path.getAddGUI());
        }
      });
      TranslateButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
          showComponent(Path.getTranslateGUI());
        }
      });
      showComponent(Path.getSearchGUI());
      ExitButton.setOnMouseClicked(e -> {
        System.exit(0);
      });
  }
}
