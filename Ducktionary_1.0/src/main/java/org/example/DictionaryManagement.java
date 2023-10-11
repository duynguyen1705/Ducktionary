package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

import java.io.*;

public class DictionaryManagement extends Dictionary {
    public static void insertFromCommandline() {
        // input
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
        String word_type = sc.nextLine();
        String word_explain = sc.nextLine();
        String word_example = sc.nextLine();

        word_list.add(new Word(word_target, word_explain, word_type, word_example));
        word_list.sort(new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2) {
                return word1.getWordTarget().compareTo(word2.getWordTarget());
            }
        });

    }
    public void exportToFile(ArrayList<Word> word_list, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : word_list) {
                bufferedWriter.write("-" + word.getWordTarget() + "\n" + word.getWordType() + "\n" + word.getWordExplain() + "\n" + word.getWordExample());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public void insertFromFile(ArrayList<Word> word_list, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word newWord = new Word();
                String[] parts = line.split("\t");
                String wordTarget = "", wordExplain = "";
                if (parts.length == 2) {
                    wordTarget = parts[0].trim();
                    wordExplain = parts[1].trim();
                }
                newWord.setWordTarget(wordTarget);
                newWord.setWordExplain(wordExplain);
                newWord.setWordExample("Word Example is Loading");
                newWord.setWordType("Word Type is Loading");
                word_list.add(newWord);
            }
        } catch(IOException error) {
            error.printStackTrace();
        }
    }
    public void lookupWord(ArrayList<Word> words_list, String key ) {
        try {
            Scanner newScanner = new Scanner(System.in);
            for (int i = 0; i < words_list.size(); i++) {
                if(Objects.equals(words_list.get(i).getWord(), key)) {
                    System.out.print(words_list.get(i).getWord() + " - " + words_list.get(i).getWordExplain());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWord(ArrayList<Word> words_list, int index, String meaning, String path) {
        try {
            words_list.get(index).setWordExplain(meaning);
            exportToFile(words_list, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void deleteWord(ArrayList<Word> words_list, int index, String path) {
        try {
            words_list.remove(index);
            exportToFile(words_list, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void addWord(Word word, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(word.getWordTarget() + "\t" + word.getWordExplain());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }
}