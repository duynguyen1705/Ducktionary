package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    private static final DictionaryCommandline commandLine = new DictionaryCommandline();
    private static final DictionaryManagement management = new DictionaryManagement();

    public void showAllWord() {
        System.out.println("DUCKTIONARY\n----------------------------");
        System.out.printf("%-3s\t|\t%-15s\t|\t%-15s\t|\t%-15s%n", "NO", "ENGLISH", "WORD TYPE", "VIETNAMESE");
        for (int i = 0; i < Dictionary.dictionary.size(); i++) {
            String format = "%-3s\t|\t%-15s\t|\t%-15s\t|\t%-15s%n";
            String no = Integer.toString(i + 1);
            String english = Dictionary.dictionary.get(i).getWordTarget();
            String wordType = Dictionary.dictionary.get(i).getWordType();
            String vietnamese = Dictionary.dictionary.get(i).getWordExplain();
            System.out.printf(format, no, english, wordType, vietnamese);
        }
    }
    public static void dictionaryAdvanced(ArrayList<Word> dictionary) {
        final String pathInput = "C:\\Users\\Andrew\\Desktop\\dictionaries.txt";
        final String pathOutput = "C:\\Users\\Andrew\\Desktop\\DictionaryOutput.txt";
        while(true) {
            System.out.println("\n----------------------------");
            System.out.println("Welcome to Ducktionary!");
            final int numberFunction = 10;
            for (int i = 0; i < numberFunction; i++) {
                System.out.print("[" + i + "] ");
                switch (i) {
                    case 0:
                        System.out.println("Exit");
                        break;
                    case 1:
                        System.out.println("Add");
                        break;
                    case 2:
                        System.out.println("Remove");
                        break;
                    case 3:
                        System.out.println("Update");
                        break;
                    case 4:
                        System.out.println("Display");
                        break;
                    case 5:
                        System.out.println("Lookup");
                        break;
                    case 6:
                        System.out.println("Search");
                        break;
                    case 7:
                        System.out.println("Game");
                        break;
                    case 8:
                        System.out.println("Import from file");
                        break;
                    case 9:
                        System.out.println("Export to file");
                    default:
                        break;
                }
            }
            System.out.println("Your action: ");
            Scanner newScanner = new Scanner(System.in);
            int selection = newScanner.nextInt();
            if(selection == 0) {
                System.out.println("Bye, bye. See you again!");
                break;
            }
            switch (selection) {
                case 1:
                    management.addWord(Dictionary.dictionary, pathOutput);
                    break;
                case 2:
                    management.removeWord(Dictionary.dictionary);
                    //DictionaryManagement.insertFromCommandline(Dictionary.dictionary);
                    break;
                case 3:
                    management.updateWord(Dictionary.dictionary, pathOutput);
                    break;
                case 4:
                    commandLine.showAllWord();
                    break;
                case 5:
                    management.dictionaryLookup(Dictionary.dictionary);
                    break;
                case 6:
                    System.out.println("Search");
                    System.out.println("Please input word you want to search: ");
                    Scanner sc = new Scanner(System.in);
                    String word = sc.nextLine();
                    int index = management.searchWord(Dictionary.dictionary, word);
                    System.out.printf("%-3s\t|\t%-15s\t|\t%-15s\t|\t%-15s%n", "NO", "ENGLISH", "WORD TYPE", "VIETNAMESE");
                    System.out.printf("%-3s\t|\t%-15s\t|\t%-15s\t|\t%-15s%n", index + 1, dictionary.get(index).getWordTarget(), dictionary.get(index).getWordType(), dictionary.get(index).getWordExplain());
                    break;
                case 7:
                    System.out.println("Loading....");
                case 8:
                    management.insertFromFile(Dictionary.dictionary, pathInput);
                    break;
                case 9:
                    management.dictionaryExportToFile(Dictionary.dictionary, pathOutput);
                    break;
                default:
                    System.out.println("Action not supported");
                    break;
            }
        }

    }
    public void dictionaryBasic(Dictionary dictionary) {
        management.insertFromCommandline(Dictionary.dictionary);
        commandLine.showAllWord();
    }
    public static void main(String[] args) {
        dictionaryAdvanced(Dictionary.dictionary);
    }
}
