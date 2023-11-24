package Controllers;

import CommandlineVer.CallAPI;
import CommandlineVer.Dictionary;
import CommandlineVer.DictionaryManagement;
import CommandlineVer.Word;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class MenuController extends MainController {

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
    SearchBtn.setOnAction(e -> {
      Word word = CallAPI.lookup(SearchBox.getText());
      if (word == null)
        wordName.setText("từ không tồn tại");
      else {
        System.out.println("word type: " + word.getWordType());
        wordName.setText(word.getWordTarget());
        wordType.setText(word.getWordType());
        wordExample.setText(word.getWordExample());
        dictionaryManagement.insertFromFile(dictionary, "src/main/resources/Utils/dictionaries.txt");
//        dictionary.add(word);
//        dictionaryManagement.dictionaryExportToFile(dictionary, "src/main/resources/Utils/dictionaries.txt");
      }
    });
    handleActionChangeScene();
  }

  private Dictionary dictionary = new Dictionary();
  private DictionaryManagement dictionaryManagement = new DictionaryManagement();

  @FXML
  private TextField SearchBox;
  @FXML
  private Button SearchBtn;
  @FXML
  private TextArea result;
  @FXML
  private Label wordName;
  @FXML
  private Label wordType;
  @FXML
  private Label wordExample;
}