package Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public abstract class Controller implements Initializable {

  protected AnchorPane container;

  @FXML
  protected void changeScene(String path) {
    try {
      AnchorPane component = FXMLLoader.load(getClass().getResource(path));
      container.getChildren().clear();
      container.getChildren().add(component);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
