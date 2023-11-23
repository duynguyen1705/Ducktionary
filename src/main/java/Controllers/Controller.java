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

public abstract class Controller {

  @FXML
  protected AnchorPane container;

  @FXML
  public void handleActionChangeScene() {

    searchWordBtn.setOnAction(e -> changeScene("/GUI/Searcher.fxml"));

    addWordBtn.setOnAction(e -> changeScene("/GUI/Adder.fxml"));

    translateBtn.setOnAction(e -> changeScene("/GUI/TranslateText.fxml"));

    menuBtn.setOnAction(e -> changeScene("/GUI/Menu.fxml"));

    gameBtn.setOnAction(e -> changeScene("/GUI/MenuGame.fxml"));
    closeBtn.setOnMouseClicked(e -> {
      System.exit(0);
    });
    tooltip1.setShowDelay(Duration.seconds(0.5));
    tooltip2.setShowDelay(Duration.seconds(0.5));
    tooltip3.setShowDelay(Duration.seconds(0.5));
    tooltip4.setShowDelay(Duration.seconds(0.5));
    tooltip5.setShowDelay(Duration.seconds(0.5));
    tooltip6.setShowDelay(Duration.seconds(0.5));

  }

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
  @FXML
  public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;
  @FXML
  public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;
}
