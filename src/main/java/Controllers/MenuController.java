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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MenuController implements Initializable {

  @FXML
  private TextField SearchBox;
  @FXML
  private Label notAvailableAlert;

  @FXML
  private Button SearchBtn;
  @FXML
  private TextArea result;

  private DictionaryManagement dictionaryManagement = new DictionaryManagement();
  private Dictionary dictionary = new Dictionary();
  @FXML
  private void handleClickSearchBtn() {
    String word = CallAPI.lookup(SearchBox.getText());
    result.setText(word);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    SearchBox.setOnKeyTyped(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (SearchBox.getText().isEmpty())
          SearchBtn.setDisable(true);
        else
          SearchBtn.setDisable(false);
      }
    });
  }
}
