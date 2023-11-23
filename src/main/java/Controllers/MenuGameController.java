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
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.*;
import javafx.util.Duration;


public class MenuGameController extends Controller implements Initializable {


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
  private Button easyBtn;
  @FXML
  private Button mediumBtn;
  @FXML
  private Button hardBtn;
  @FXML
  private AnchorPane container;
  @FXML
  public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;

  @FXML
  public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;
}
