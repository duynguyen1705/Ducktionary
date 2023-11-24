package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;


public class MenuGameController extends MainController {

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    easyBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        changeScene("/GUI/Wordle/EasyMode.fxml");
      }
    });

    mediumBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        changeScene("/GUI/Wordle/MediumMode.fxml");
      }
    });

    hardBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        changeScene("/GUI/Wordle/HardMode.fxml");
      }
    });
    handleActionChangeScene();
    }
    @FXML

  private Button easyBtn, mediumBtn, hardBtn;
}
