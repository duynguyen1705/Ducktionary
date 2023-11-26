package CommandlineVer;

import java.util.Scanner;

public class DictionaryCommandline {
    private static final DictionaryCommandline commandLine = new DictionaryCommandline();
    private static final DictionaryManagement management = new DictionaryManagement();
    private static Dictionary dictionary = new Dictionary();
    public void showAllWord() {
        System.out.println("DUCKTIONARY\n----------------------------");
        System.out.printf("%-3s\t|\t%-15s\t|\t%-15s\t|\t%-15s%n", "NO", "ENGLISH", "WORD TYPE", "VIETNAMESE");
        for (int i = 0; i < dictionary.size(); i++) {
            String format = "%-3s\t|\t%-15s\t|\t%-15s\t|\t%-15s%n";
            String no = Integer.toString(i + 1);
            String english = dictionary.get(i).getWordTarget();
            String wordType = dictionary.get(i).getWordType();
            String vietnamese = dictionary.get(i).getWordExplain();
            System.out.printf(format, no, english, wordType, vietnamese);
        }
    }
    public static void dictionaryAdvanced(Dictionary dictionary) {
        String pathInput = "src/main/resources/Utils/dictionaries.txt";
        String pathOutput = "src/main/resources/Utils/dictionariesOutput.txt";
        while(true) {
            System.out.println("\n----------------------------");
            System.out.println("Welcome to Ducktionary!");
            final int numberFunction = 9;
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
                        System.out.println("Import from file");
                        break;
                    case 8:
                        System.out.println("Export to file");
                        break;
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
                    management.addWord(dictionary, pathOutput);
                    break;
                case 2:
                    management.removeWord(dictionary, pathOutput);
                    break;
                case 3:
                    management.updateWord(dictionary, pathOutput);
                    break;
                case 4:
                    commandLine.showAllWord();
                    break;
                case 5:
                    management.dictionaryLookup(dictionary);
                    break;
                case 6:
                    System.out.println("Search");
                    System.out.println("Please input word you want to search: ");
                    Scanner sc = new Scanner(System.in);
                    String word = sc.nextLine();
                    int index = management.searchWord(dictionary, word);
                    System.out.print("\n--------\n" + dictionary.get(index).getWordTarget() + "\n--------\n"+ '\n');
                    System.out.print(dictionary.get(index).getWordExplain());
                    break;
                case 7:
                    management.insertFromFile(dictionary, pathInput);
                    break;
                case 8:
                    management.dictionaryExportToFile(dictionary, pathOutput);
                    break;
                default:
                    System.out.println("Action not supported");
                    break;
            }
        }

    }
    public void dictionaryBasic(Dictionary dictionary) {
        management.insertFromCommandline(dictionary);
        commandLine.showAllWord();
    }
    public static void main(String[] args) {
        dictionaryAdvanced(dictionary);
    }

}
