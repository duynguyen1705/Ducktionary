package Controllers.Game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MediumModeController extends Wordle {

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    turns = 4;

    path = "src\\main\\resources\\Utils\\4Char.txt";
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Chế độ trung bình");
    alert.setHeaderText("Trung bình");
    alert.setContentText("Ở chế độ này, bạn cần đoán 1 từ có 4 chữ cái. \n Bạn có 4 lượt đoán.");
    alert.show();
    generate(path);
    word = word.toUpperCase();
  }

  @Override
  protected void checkGuess() {

    guessWord = guess.getText();
    guessWord = guessWord.toUpperCase();
    resetBox();
    if (guessWord.length() != 4) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Cảnh báo");
      alert.setContentText("Bạn đang nhập sai từ");
      alert.show();
      turns--;
    } else {
      String letter1 = guessWord.substring(0, 1);
      System.out.println(letter1);
      Box1.setText(letter1);

      if (letter1.equals(word.substring(0, 1))) {
        Box1.setStyle("-fx-background-color: #8eeda1;");
      } else if (word.indexOf(letter1) > 0) {
        Box1.setStyle("-fx-background-color: #5f6ef5;");
      }

      String letter2 = guessWord.substring(1, 2);
      Box2.setText(letter2);
      if (letter2.equals(word.substring(1, 2))) {

        Box2.setStyle("-fx-background-color: #8eeda1;");
      } else if (word.indexOf(letter2) != -1 && word.indexOf(letter2) != 1) {
        Box2.setStyle("-fx-background-color: #5f6ef5;");
      }
      String letter3 = guessWord.substring(2, 3);
      Box3.setText(letter3);
      if (letter3.equals(word.substring(2, 3))) {
        Box3.setStyle("-fx-background-color: #8eeda1;");
      } else if (word.indexOf(letter3) != -1 && word.indexOf(letter3) != 2) {
        Box3.setStyle("-fx-background-color: #5f6ef5;");
      }
      String letter4 = guessWord.substring(3, 4);
      Box4.setText(letter4);
      if (letter4.equals(word.substring(3, 4))) {
        Box4.setStyle("-fx-background-color: #8eeda1;");
      } else if (word.indexOf(letter4) != -1 && word.indexOf(letter4) != 3) {
        Box4.setStyle("-fx-background-color: #5f6ef5;");
      }

      turns--;
    }
  }

  @FXML @Override
  public void check() {
    checkGuess();
    if (turns == 0) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setContentText("You are loser");
      alert.setTitle("Lose");
      alert.show();
      turns = 4;
    }
    if (word.toUpperCase().equals(guessWord.toUpperCase())) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setContentText("You are winner");
      alert.setTitle("Win");
      alert.show();
      turns = 4;
    }
  }
  @FXML @Override
  public void replay() {
    path = "T:\\project\\Ducktionary\\src\\main\\resources\\Utils\\4Char.txt";
    generate(path);
    turns = 4;
    resetBox();
  }

  @FXML
  private void resetBox() {
    Box1.setText("");
    Box2.setText("");
    Box3.setText("");
    Box4.setText("");
    Box1.setStyle("-fx-border-color: #000000");
    Box2.setStyle("-fx-border-color: #000000");
    Box3.setStyle("-fx-border-color: #000000");
    Box4.setStyle("-fx-border-color: #000000");
    Box1.setStyle("-fx-background-color: #ffffff;");
    Box2.setStyle("-fx-background-color: #ffffff;");
    Box3.setStyle("-fx-background-color: #ffffff;");
    Box4.setStyle("-fx-background-color: #ffffff;");
    guess.setText("");
  }


  @FXML
  private Label Box1 = new Label();
  @FXML
  private Label Box2 = new Label();
  @FXML
  private Label Box3 = new Label();
  @FXML
  private Label Box4 = new Label();
  @FXML
  private TextField guess;

}

