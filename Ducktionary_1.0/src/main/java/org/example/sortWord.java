package org.example;

import java.util.Comparator;

public class sortWord implements Comparator<Word> {
    @Override
    public int compare(Word word, Word word1) {
        return word.getWordTarget().compareTo(word1.getWordTarget());
    }
}
