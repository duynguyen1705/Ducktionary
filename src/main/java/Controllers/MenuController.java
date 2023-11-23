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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class MenuController extends Controller implements Initializable {

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
        result.setText("Từ không tồn tại");
      else {
        result.setText(word.toString());
        dictionaryManagement.insertFromFile(dictionary, "src/main/resources/Utils/dictionaries.txt");
        dictionary.add(word);
        dictionaryManagement.dictionaryExportToFile(dictionary, "src/main/resources/Utils/dictionaries.txt");
      }
    });
    searchWordBtn.setOnAction(e -> changeScene("/GUI/Searcher.fxml"));

    addWordBtn.setOnAction(e -> changeScene("/GUI/Adder.fxml"));

    translateBtn.setOnAction(e -> changeScene("/GUI/TranslateText.fxml"));

    menuBtn.setOnAction(e -> changeScene("/GUI/Menu.fxml"));

    gameBtn.setOnAction(e -> changeScene("/GUI/MenuGame.fxml"));

    tooltip1.setShowDelay(Duration.seconds(0.5));
    tooltip2.setShowDelay(Duration.seconds(0.5));
    tooltip3.setShowDelay(Duration.seconds(0.5));
    tooltip4.setShowDelay(Duration.seconds(0.5));
    tooltip5.setShowDelay(Duration.seconds(0.5));
    tooltip6.setShowDelay(Duration.seconds(0.5));

    closeBtn.setOnMouseClicked(e -> {
      System.exit(0);
    });
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
  private Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;
  @FXML
  private Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;

}