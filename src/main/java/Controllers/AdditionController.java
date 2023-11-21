package Controllers;

import Alerts.Alerts;
import CommandlineVer.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AdditionController implements Initializable {
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private final String path = "src/main/resources/Utils/dictionaries.txt";
    private Alerts alerts = new Alerts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(dictionary, path);
        if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()) addBtn.setDisable(true);

        wordTargetInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()) addBtn.setDisable(true);
                else addBtn.setDisable(false);
            }
        });

        explanationInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()) addBtn.setDisable(true);
                else addBtn.setDisable(false);
            }
        });

        successAlert.setVisible(false);
    }

    @FXML
    private void handleClickAddBtn() {
        Alert alertConfirmation = alerts.alertConfirmation("Add word", "Bạn chắc chắn muốn thêm từ này?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        String englishWord = wordTargetInput.getText().trim();
        String meaning = explanationInput.getText().trim();
        String type = wordTargetType.getText().trim();
        String examples = example.getText().trim();

        if (option.get() == ButtonType.OK) {
            Word word = new Word(englishWord, meaning, type, examples);
            if (dictionary.contains(word)) {
                int indexOfWord = dictionaryManagement.searchWord(dictionary, englishWord);
                Alert selectionAlert = alerts.alertConfirmation("This word already exists", "Từ này đã tồn tại.\nThay thế hoặc bổ sung nghĩa vừa nhập cho nghĩa cũ.");
                selectionAlert.getButtonTypes().clear();
                ButtonType replaceBtn = new ButtonType("Thay thế");
                ButtonType insertBtn = new ButtonType("Bổ sung");
                selectionAlert.getButtonTypes().addAll(replaceBtn, insertBtn, ButtonType.CANCEL);
                Optional<ButtonType> selection = selectionAlert.showAndWait();

                if (selection.get() == replaceBtn) {
                    dictionary.get(indexOfWord).setWordExplain(meaning);
                    dictionaryManagement.dictionaryExportToFile(dictionary, path);
                    showSuccessAlert();
                }
                if (selection.get() == insertBtn) {
                    String oldMeaning = dictionary.get(indexOfWord).getWordExplain();
                    dictionary.get(indexOfWord).setWordExplain(oldMeaning + "\n= " + meaning);
                    dictionaryManagement.dictionaryExportToFile(dictionary, path);
                    showSuccessAlert();
                }
                if (selection.get() == ButtonType.CANCEL) alerts.showAlertInfo("Information", "Thay đổi không được công nhận.");
            } else {
                dictionary.add(word);
                dictionaryManagement.dictionaryExportToFile(dictionary, path);
                showSuccessAlert();
            }
            addBtn.setDisable(true);
            resetInput();
        } else if (option.get() == ButtonType.CANCEL) alerts.showAlertInfo("Information", "Thay đổi không được công nhận.");
    }

    private void resetInput() {
        wordTargetInput.setText("");
        explanationInput.setText("");
    }

    private void showSuccessAlert() {
        successAlert.setVisible(true);
        dictionaryManagement.setTimeout(() -> successAlert.setVisible(false), 1500);
    }

    @FXML
    private Button addBtn;

    @FXML
    private TextField wordTargetInput;

    @FXML
    private TextArea explanationInput;

    @FXML
    private Label successAlert;

    @FXML
    private TextField wordTargetType;

    @FXML
    private TextArea example;
}
