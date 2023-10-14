package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandline(ArrayList<Word> dictionary) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input word you want to add: ");
        String wordTarget = sc.nextLine();

        System.out.println("Input type of *"  + wordTarget.trim() + " :");
        String wordType = sc.nextLine();

        System.out.println("Input meaning of *" + wordTarget.trim() + " :");
        String wordExplain = sc.nextLine();

        Word newWord = new Word();
        newWord.setWordTarget(wordTarget);
        newWord.setWordType(wordType);
        newWord.setWordExplain(wordExplain);

        dictionary.add(newWord);
        System.out.println(wordTarget + "is added in Ductionary");
        // dictionary.sort(new sortWord());
    }

    public void insertFromFile(ArrayList<Word> dictionary, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            String wordTarget = line.replace("|", "");

            while ((line = bufferedReader.readLine()) != null) {
                Word newWord = new Word();
                newWord.setWordTarget(wordTarget.trim());
                String wordExplain = "";
                String wordType = "";
                if(line.startsWith("*")) {
                    wordType = line.replace("*", "");
                }
                else {
                    wordExplain = line.trim()+ "\n";
                }
                while ((line = bufferedReader.readLine()) != null) {
                    if(line.startsWith("-")) {
                        wordExplain += line.trim() + "\n";
                    } else if(line.startsWith("|")) {
                        wordTarget = line.replace("|", "");
                        break;
                    }
                }
                newWord.setWordType(wordType.trim());
                newWord.setWordExplain(wordExplain.trim());
                dictionary.add(newWord);
                System.out.println("Insert from file successfully!");
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void dictionaryExportToFile(ArrayList<Word> dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary) {
                bufferedWriter.write("|" + word.getWordTarget() + "\n");
                if(!word.getWordType().isEmpty()) {
                    bufferedWriter.write("*" + word.getWordType() + "\n");
                }
                bufferedWriter.write(word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Export to file successfully!");
        } catch (Exception error) {
            System.out.println("Error: " + error);
        }
    }

    public int searchWord(ArrayList<Word> dictionary, String word) {
        try {
            dictionary.sort(new sortWord());
            int begin = 0;
            int last = dictionary.size() - 1;
            while(begin <= last) {
                int mid = (begin+last)/2;
                int check = dictionary.get(mid).getWordTarget().compareTo(word);
                if(check == 0) {
                    return mid;
                }
                if(check <= 0) {
                    begin = mid + 1;
                } else {
                    last = mid - 1;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void dictionaryLookup(ArrayList<Word> dictionary) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input word you want to lookup: ");
            String word = scanner.nextLine();
            System.out.println("The result: ");
            for (Word w : dictionary) {
                if (w.getWordTarget().startsWith(word)) {
                    System.out.println(w.getWordTarget());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWord(ArrayList<Word> dictionary, String path) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input word you want to update: ");

            String word = scanner.nextLine();

            int index = searchWord(dictionary, word);

            System.out.println("Do you want to update word type or meaning of " + word + "\n" + "1. Word type\n2. Meaning");
            int selection = scanner.nextInt();
            scanner.nextLine();
            if(selection == 1) {
                System.out.println("Input word type of *" + word + ": ");
                String wordType = scanner.nextLine();
                dictionary.get(index).setWordType(wordType);
            }
            else {
                System.out.println("Input meaning of *" + word +  ": ");
                String meaning = scanner.nextLine();
                dictionary.get(index).setWordExplain(meaning);
            }
            System.out.println(word + " is updated in Ducktionary.");
            dictionaryExportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void addWord(ArrayList<Word> dictionary, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input word you want to add: ");
            String wordTarget = scanner.nextLine();

            System.out.println("Input word type of *" + wordTarget + ": (if don't, please type 'null')");
            String wordType = scanner.nextLine();

            System.out.println("Input meaning of *" + wordTarget + ": ");
            String meaning = scanner.nextLine();

            Word newWord = new Word();
            newWord.setWordTarget(wordTarget);
            newWord.setWordType(wordType);
            newWord.setWordExplain(meaning);
            dictionary.add(newWord);
            bufferedWriter.write("|" + newWord.getWordTarget() + "\n");
            if(!newWord.getWordType().isEmpty()) {
                bufferedWriter.write("*" + newWord.getWordType() + "\n");
            }
            bufferedWriter.write(newWord.getWordExplain());
            bufferedWriter.newLine();

            System.out.println(wordTarget + " is added in Ducktionary");
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }
    public void removeWord(ArrayList<Word> dictionary) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input word you remove: ");

            String word= scanner.nextLine();
            int index = searchWord(dictionary, word);
            dictionary.remove(index);

            System.out.println(word + " is removed from Ducktionary");
        } catch (NullPointerException e){
            System.out.println("Null");
        }
    }
}
