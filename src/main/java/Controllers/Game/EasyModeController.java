package Controllers.Game;

import Alerts.Alerts;
import CommandlineVer.CallAPI;
import CommandlineVer.Word;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javax.annotation.processing.Generated;
import javax.imageio.IIOException;

public class EasyModeController extends Wordle{
  @FXML
  private Label Box1 = new Label();
  @FXML
  private Label Box2 = new Label();
  @FXML
  private Label Box3 = new Label();
  @FXML
  private TextField guess;

  String word = "fun";


  @Override
  protected void generate(String path) {
    try {
      FileReader fileReader = new FileReader(path);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      List<String> list = new ArrayList<>();
      String line;
      while((line = bufferedReader.readLine()) != null) {
        list.add(line.trim());
      }
      word = list.get((int)(Math.random()*list.size()));
      System.out.println(word);
      turns = 3;
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void checkGuess() {
//    System.out.println(guessWord);
//    System.out.println(word);
    guessWord = guess.getText();

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
    turns--;
   }


   @FXML @Override
   public void check() {
    checkGuess();
    if (turns == 0) {
      Alerts alerts = new Alerts();
      alerts.showAlertInfo("Loser", "You are Loserrr");
      turns = 3;
    }
    if (word.equals(guess.getText())) {
      Alerts alerts = new Alerts();
      alerts.showAlertInfo("Winn", "You are winner");
      turns = 3;
    }
   }
   @FXML @Override
  public void replay() {
    path = "T:\\project\\Ducktionary\\src\\main\\resources\\Utils\\3Char.txt";
    generate(path);
    turns = 3;
     Box1.setText("");
     Box2.setText("");
     Box3.setText("");
     Box1.setStyle("-fx-background-color: #ffffff;");
     Box2.setStyle("-fx-background-color: #ffffff;");
     Box3.setStyle("-fx-background-color: #ffffff;");
     guess.setText("");
   }
   @FXML @Override
  public void showHint() {
     hintText.setText(word0.getWordType() + "\n" + word0.getWordExplain());
   }
  }

