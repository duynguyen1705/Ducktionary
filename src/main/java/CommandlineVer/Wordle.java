package CommandlineVer;

import java.util.ArrayList;

public class Wordle {
    private String targetWord;
    private ArrayList<Character> guess;
    int count = 0;
    private Dictionary dictionary = new Dictionary();
    public void chooseWord() {
      int index = (int)(Math.random()*dictionary.size());
      DictionaryManagement dictionaryManagement = new DictionaryManagement();
      dictionaryManagement.insertFromFile(dictionary, "dictionaries.txt");
      Word guessWord = dictionary.get(index);
      targetWord = guessWord.getWordTarget();
      guess = new ArrayList<>();
      for (int i = 0; i < targetWord.length(); i++) {
        guess.add('_');
        count++;
      }
    }
    public void print() {
      for (int i = 0; i < guess.size(); i++) {
        System.out.print(guess.get(i));
        System.out.println();
      }
    }

    public boolean checkIn(char temp) {
      if (!guess.contains(temp))
        return false;
      for (int i = 0; i < guess.size(); i++) {
        if (guess.get(i).equals(temp)) {
          guess.set(i, temp);
          count--;
        }
      }
      return true;
    }

    public boolean checkTrue() {
      if (count == 0) {
        System.out.println("Hurray!! You guess our word:");
        System.out.println(targetWord);
        return true;
      }
      return false;
    }

    public String getTargetWord() {
      return targetWord;
    }
}
