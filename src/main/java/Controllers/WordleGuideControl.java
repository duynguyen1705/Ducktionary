package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WordleGuideControl extends Controller{

  @FXML
  private Button nextBtn;
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    nextBtn.setOnAction(e -> changeScene("/GUI/MenuGame.fxml"));
  }
}
