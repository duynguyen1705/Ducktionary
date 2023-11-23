package Controllers;

import CommandlineVer.CallAPI;
import CommandlineVer.Dictionary;
import CommandlineVer.DictionaryManagement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MenuController extends Controller implements Initializable {


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
    searchWordBtn.setOnAction(e -> showComponent("/GUI/SearcherGui.fxml"));

    addWordBtn.setOnAction(e -> showComponent("/GUI/AdditionGui.fxml"));

    translateBtn.setOnAction(e -> showComponent("/GUI/TranslationGui.fxml"));

    menuBtn.setOnAction(e -> showComponent("/GUI/MenuGui.fxml"));

    gameBtn.setOnAction(e -> showComponent("/GUI/MenuGameGui.fxml"));

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
  @FXML
  private TextField SearchBox;
  @FXML
  private Label notAvailableAlert;

  @FXML
  private Button SearchBtn;
  @FXML
  private TextArea result;
  @FXML
  public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;

  @FXML
  public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;

  @FXML
  public AnchorPane container;

  private DictionaryManagement dictionaryManagement = new DictionaryManagement();
  private Dictionary dictionary = new Dictionary();
}