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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class MenuGameController extends DictionaryController implements Initializable {

  @FXML
  private Button easyBtn;
  @FXML
  private Button mediumBtn;
  @FXML
  private Button hardBtn;

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
        showComponent("/GUI/AdditionGui.fxml");
      }
    });

    hardBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        showComponent("/GUI/TranslationGui.fxml");
      }
    });

  }
}
