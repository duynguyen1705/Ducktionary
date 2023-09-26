package org.example;

public class DictionaryCommandline {

  public void showAllWord() {
    System.out.println("English  |  Vietnamese  |  type  |  example  |  level");
    for (int i = 0; i < Dictionary.count; i++) {
      System.out.println(i + "  |  " + Dictionary.word_list.get(i).getWord());
    }
  }

}
