package Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public abstract class Controller implements Initializable {
  @FXML
  public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;

  @FXML
  public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;

  @FXML
  public AnchorPane container;


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
}
