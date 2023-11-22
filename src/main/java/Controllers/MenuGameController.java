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
import javafx.scene.layout.AnchorPane;
import javafx.scene.*;


public class MenuGameController extends DictionaryController implements Initializable {

  @FXML
  private Button easyBtn;
  @FXML
  private Button mediumBtn;
  @FXML
  private Button hardBtn;
  @FXML
  private AnchorPane container;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    easyBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        showComponent("/GUI/Wordle/EasyMode.fxml");
      }
    });

    mediumBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        showComponent("/GUI/Wordle/MediumMode.fxml");
      }
    });

    hardBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        showComponent("/GUI/Wordle/HardMode.fxml");
      }
    });
    }

}
