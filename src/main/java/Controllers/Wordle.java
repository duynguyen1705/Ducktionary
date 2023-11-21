package Controllers;

import CommandlineVer.Dictionary;
import CommandlineVer.DictionaryManagement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Wordle {

  @FXML
  Button ExitBtn;
  @FXML
  Button continueBtn;
  @FXML
  Pane[] panes;
  @FXML
  TextField guess;
  String word;
  String guessWord;
  int attempts;

  public void generateWord() {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    dictionaryManagement.insertFromFile(dictionary, "src/main/resources/Utils/dictionaries.txt");
    word = dictionary.get((int)(Math.random()*dictionary.size())).getWordTarget();
    panes = new Pane[word.length()];
    guessWord = guess.getText();
  }
  public void check() {

    }
  public void displayGuess() {
    for(int i = 0; i < panes.length; i++) {

    }
  }

}

