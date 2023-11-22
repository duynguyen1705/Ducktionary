package Controllers;

import Alerts.Alerts;
import CommandlineVer.CallAPI;
import CommandlineVer.Dictionary;
import CommandlineVer.DictionaryManagement;
import CommandlineVer.Word;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MenuController extends DictionaryController implements Initializable {
  @FXML
  public Button easyBtn;
  @FXML
  public Button mediumBtn;
  @FXML
  public Button hardBtn;
  @FXML
  public AnchorPane container;
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    easyBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        showComponent("");
      }
    });
  }

}
