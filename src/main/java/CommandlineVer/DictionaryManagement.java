package CommandlineVer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Trie.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DictionaryManagement {
    private Trie trie = new Trie();
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
        System.out.println(wordTarget + "is added in Ducktionary");
    }

    public ObservableList<String> lookupWord(Dictionary dictionary, String key ) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            setTrie(dictionary);
            List<String> results = trie.autoComplete(key);
            if (results != null) {
                int length = Math.min(results.size(), 15);
                for (int i = 0; i < length; i++) list.add(results.get(i));
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return list;
    }

    public void insertFromFile(ArrayList<Word> dictionary, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String englishWord = bufferedReader.readLine();
            englishWord = englishWord.replace("|", "");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word();
                word.setWordTarget(englishWord.trim());
                String meaning = line + "\n";
                while ((line = bufferedReader.readLine()) != null)
                    if (!line.startsWith("|")) meaning += line + "\n";
                    else {
                        englishWord = line.replace("|", "");
                        break;
                    }
                word.setWordExplain(meaning.trim());
                dictionary.add(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void dictionaryExportToFile(ArrayList<Word> dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary) {
                System.out.println(word.getWordExplain());
                bufferedWriter.write("|" + word.getWordTarget() + "\n"
                + "*" + word.getWordType() + "\n"
                + word.getWordExplain() + "\n"
                + word.getWordExample());

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

    public void dictionaryLookup() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input word you want to lookup: ");
            String word = scanner.nextLine();
            System.out.println("The result: ");
            System.out.println(CallAPI.lookup(word));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void translatetext() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your text");
        String text = scan.next();
        System.out.println("It means: " + CallAPI.translate(text, "en", "vi"));
    }

    public void updateWord(Dictionary dictionary, int index, String meaning, String path) {
        try {
            dictionary.get(index).setWordExplain(meaning);
            dictionaryExportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
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
    public void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
    public void deleteWord(Dictionary dictionary, int index, String path) {
        try {
            dictionary.remove(index);
            trie = new Trie();
            setTrie(dictionary);
            dictionaryExportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }
    public void setTrie(Dictionary dictionary) {
        try {
            for (Word word : dictionary) trie.insert(word.getWordTarget());
        } catch (NullPointerException e) {
            System.out.println("Something went wrong: " + e);
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
