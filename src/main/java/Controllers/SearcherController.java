package Controllers;

//import CommandlineVer.CallAPI;
import CommandlineVer.VietnameseWord;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import javafx.scene.input.KeyEvent;


public class SearcherController implements Initializable {
  @FXML
  private TextField SearchBox;
  @FXML
  private Button SearchButton;
  @FXML
  private TextField Detect;
  @FXML
  private TextArea SearchResult;
  @FXML
  private TextField Word;
  @FXML
  private TextField Transliterate;
  @FXML
  private TextArea Define;
  @FXML
  private TextArea Examples;
  @FXML
  private Label notExist;

  ObservableList<String> list = FXCollections.observableArrayList();


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    SearchBox.setOnKeyTyped(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (SearchBox.getText().isEmpty())
          SearchButton.setVisible(false);
        else {
          SearchButton.setVisible(true);
        }
      }
    });
    SearchButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) { handleOnKey();}
    });
  }
  @FXML
  private void handleOnKey() {
    list.clear();
    String searchKey = SearchBox.getText();
    List<VietnameseWord> res = CallAPI.lookup(searchKey, "en", "vi");
    if (res == null) {
      notExist.setVisible(true);
      return;
    }
    notExist.setVisible(false);
    Word.setText(searchKey);
    String defineText = "";
    for (int i = 0; i < res.size(); i++) {
      defineText += res.get(i);
    }
    Define.setText(defineText);

    String examplesText = "";

    for (int i = 0; i < res.size(); i++) {
      List<String> examplesString = CallAPI.example(searchKey, res.get(i).getDisplayTarget());
      for (int j = 0; j < examplesString.size(); j++) {
        examplesText += examplesString.get(i) + "\n";
      }
    }
    Examples.setText(examplesText);

  }
}
