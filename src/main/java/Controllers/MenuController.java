package Controllers;

import CommandlineVer.CallAPI;
import CommandlineVer.Dictionary;
import CommandlineVer.DictionaryManagement;
import CommandlineVer.Word;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    homeAddBtn.setDisable(true);
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
      try {
        word = CallAPI.lookup(SearchBox.getText());
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }
      if (word == null)
        wordName.setText("từ không tồn tại");
      else {
        System.out.println("word type: " + word.getWordType());
        wordName.setText(word.getWordTarget());
        wordType.setText(word.getWordType());
        wordExplain.setText(word.getWordExplain());
        wordExample.setText(word.getWordExample());
        dictionaryManagement.insertFromFile(dictionary, "src/main/resources/Utils/dictionaries.txt");
        homeAddBtn.setDisable(false);
        homeAddBtn.setOnAction(event -> {

          String word_name = wordName.getText();
          String word_type = wordType.getText();
          String word_example = wordExample.getText();
          String word_explain = "*" + word_type + "\n" + wordExplain.getText() + "\n" + word_example;

          Word newWord = new Word(word_name, word_explain);
          if (dictionary.contains(newWord)) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmation Dialog");
            confirmAlert.setHeaderText("Xác nhận");
            confirmAlert.setContentText("Từ đã xuất hiện trong từ điển. Thêm từ?");
            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
              dictionary.remove(newWord);
              dictionary.add(newWord);
              dictionaryManagement.dictionaryExportToFile(dictionary,
                      "src/main/resources/Utils/dictionaries.txt");
            }

          }
          else {
            dictionary.add(newWord);
            dictionaryManagement.dictionaryExportToFile(dictionary,
                    "src/main/resources/Utils/dictionaries.txt" );
          }

        });
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
  private Label wordName;
  @FXML
  private Label wordType;
  @FXML
  private Label wordExample;
  @FXML
  private Label wordExplain;
  @FXML
  private Button homeAddBtn;
  Word word = null;
}