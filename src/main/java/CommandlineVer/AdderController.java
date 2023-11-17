package CommandlineVer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdderController implements Initializable {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    // cai nay neu su dung txt
    private final String path = "src/main/resources/dictionaries.txt";
    @FXML
    private TextField WordTarget;

    @FXML
    private TextField WordExplanation;

    @FXML
    private ChoiceBox<String> WordType;

    @FXML
    private TextField WordExample;

    @FXML
    Word newWord;

    @FXML
    private Button AdderSubmitBtn1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(Dictionary.dictionary, path);
        if(WordExplanation.getText().isEmpty() || WordTarget.getText().isEmpty()) {
            AdderSubmitBtn1.setDisable(true);
        }

        WordTarget.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (WordExplanation.getText().isEmpty() || WordTarget.getText().isEmpty()) {
                   AdderSubmitBtn1.setDisable(true);
                } else
                    AdderSubmitBtn1.setDisable(false);
            }
        });

        WordExplanation.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (WordExplanation.getText().isEmpty() || WordTarget.getText().isEmpty()) {
                    AdderSubmitBtn1.setDisable(true);
                } else
                    AdderSubmitBtn1.setDisable(false);
            }
        });
    }
    public void createWord(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add word");
        alert.setContentText("Do you want to add this word");

        Optional<ButtonType> option = alert.showAndWait();
        String wordTarget = WordTarget.getText().trim();
        String wordExplanation = WordExplanation.getText().trim();
        String wordType = WordType.getValue();
        String wordExample = WordExample.getText().trim();


        if (option.get() == ButtonType.OK) {
             Word newWord = new Word();
             newWord.setWordTarget(wordTarget);
             newWord.setWordExplain(wordExplanation);
             newWord.setWordType(wordType);
             if (Dictionary.dictionary.contains(newWord)) {
                 int indexOfWord = dictionaryManagement.searchWord(Dictionary.dictionary, wordTarget);
                 Alert selectionAlert = new Alert(Alert.AlertType.INFORMATION);
                 selectionAlert.setTitle("This word is exist");
                 selectionAlert.setContentText("Sorry, this word is contained in Dictionary \n You only can update content for this word");
                 selectionAlert.getButtonTypes().clear();
                 
                 ButtonType replace = new ButtonType("Replace");
                 ButtonType update = new ButtonType("Update");

                 selectionAlert.getButtonTypes().addAll(replace, update, ButtonType.CANCEL);
                 Optional<ButtonType> select = selectionAlert.showAndWait();
                 Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);

                 if(select.get() == replace) {
                     Dictionary.dictionary.get(indexOfWord).setWordExplain(wordExplanation);
                     dictionaryManagement.dictionaryExportToFile(Dictionary.dictionary, path);
                     alertSuccess.setTitle("Successfull");
                 }

                 if(select.get() == update) {
                     String oldExplain = Dictionary.dictionary.get(indexOfWord).getWordExplain();
                     Dictionary.dictionary.get(indexOfWord).setWordExplain(oldExplain + "\n=" + wordExplanation);
                     dictionaryManagement.dictionaryExportToFile(Dictionary.dictionary, path);
                     alertSuccess.setTitle("Successfull");
                 }
                 if(select.get() == ButtonType.CANCEL) {
                     Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                     newAlert.setTitle("ALERT");
                     newAlert.setTitle("Oh no, your word is not updated");
                 }
             } else {
                 Dictionary.dictionary.add(newWord);
                 dictionaryManagement.addWord(Dictionary.dictionary, path);
             }
        }


    }


}
