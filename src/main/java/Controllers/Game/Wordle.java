package Controllers.Game;

import CommandlineVer.Word;
import Controllers.Controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class Wordle extends Controller {
  static int turns;
  String word;
  String guessWord;
  Word word0;
  String path;
  public TextField guess;
  public TextArea hintText;

  protected void generate(String path) {
    guessWord = guess.getText();
    try {
      FileReader fileReader = new FileReader(path);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      List<String> list = new ArrayList<>();
      String line;
      while((line = bufferedReader.readLine()) != null) {
        list.add(line.trim());
      }
      word = list.get((int)(Math.random()*list.size()));
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  protected abstract void checkGuess();
  protected abstract void check();
  protected abstract void replay();
  protected abstract void showHint();
}
