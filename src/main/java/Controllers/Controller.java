package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public abstract class Controller implements Initializable{

  @FXML
  protected AnchorPane container;

  @FXML
  protected void changeScene(String path) {
    try {
      AnchorPane pane = FXMLLoader.load(getClass().getResource(path));
      container.getChildren().clear();
      container.getChildren().add(pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
