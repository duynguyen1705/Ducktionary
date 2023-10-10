package org.example;

import java.util.Scanner;

public class DictionaryCommandline {

  public static void showAllWord() {
    System.out.println("English  |  Vietnamese  |  type  |  example");
    for (int i = 0; i < Dictionary.word_list.size(); i++) {
      System.out.println(Dictionary.word_list.get(i).getWord());
    }
  }

  public static void dictionaryBasic() {
    Scanner scan = new Scanner(System.in);
    while (scan.hasNext()) {
      int num = scan.nextInt();
      if (num == 0)
        break;
      if (num == 1)
        showAllWord();
      if (num == 2) {
        System.out.println("Enter your word");
        DictionaryManagement.insertFromCommandline();
      }
    }
  }

  public static void main(String[] args) {
    dictionaryBasic();
  }
}
