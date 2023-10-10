package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;
import java.util.List;

public class DictionaryManagement extends Dictionary {
    public static void insertFromCommandline() {
        // input
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
        String word_explain = sc.nextLine();
        String word_type = sc.nextLine();
        String word_example = sc.nextLine();
        word_list.add(new Word(word_target, word_explain, word_type, word_example));
        word_list.sort(new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2) {
                return word1.getWordTarget().compareTo(word2.getWordTarget());
            }
        });

    }
}