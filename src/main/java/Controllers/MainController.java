package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public abstract class MainController extends Controller implements Initializable {
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
  public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;
  @FXML
  public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;
}
