package Controllers.Game;

import CommandlineVer.CallAPI;
import CommandlineVer.Word;
import Controllers.Controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class Wordle extends Controller {
  int turns;
  String word;
  String guessWord;
  Word word0;
  String path;
  @FXML
  public TextField guess;
  @FXML
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
  @FXML
  public void handleOnClickExit() {
    changeScene("/GUI/MenuGame.fxml");
  }
  @FXML
  protected void showHint() throws IOException, InterruptedException {
    word0 = CallAPI.lookup(word);
    if (word0.getWordExplain() == null)
      hintText.setText("Từ này rất dễ, không có gợi ý đâu");
    else
      hintText.setText(word0.getWordExplain() + "\n" + word0.getWordType());
  }
  protected abstract void checkGuess();
  protected abstract void check();
  protected abstract void replay();
}