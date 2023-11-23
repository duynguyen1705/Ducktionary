package Controllers;

import CommandlineVer.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AdditionController extends Controller implements Initializable {


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

        searchWordBtn.setOnAction(e -> showComponent("/GUI/SearcherGui.fxml"));

        addWordBtn.setOnAction(e -> showComponent("/GUI/AdditionGui.fxml"));

        translateBtn.setOnAction(e -> showComponent("/GUI/TranslationGui.fxml"));

        menuBtn.setOnAction(e -> showComponent("/GUI/MenuGui.fxml"));

        gameBtn.setOnAction(e -> showComponent("/GUI/MenuGameGui.fxml"));
        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });
        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        tooltip4.setShowDelay(Duration.seconds(0.5));
        tooltip5.setShowDelay(Duration.seconds(0.5));
        tooltip6.setShowDelay(Duration.seconds(0.5));

        successAlert.setVisible(false);
    }

    @FXML
    private void handleClickAddBtn() {
        Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Thêm");
        alertConfirmation.setContentText("Bạn có chắc muốn thêm từ này vào từ điển");
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        String englishWord = wordTargetInput.getText().trim();
        String meaning = explanationInput.getText().trim();
        String type = wordTargetType.getText().trim();
        String examples = example.getText().trim();

        if (option.get() == ButtonType.OK) {
            Word word = new Word(englishWord, "*" + type + "\n" + "#" + meaning + "\n" + examples);
            if (dictionary.contains(word)) {
                int indexOfWord = dictionaryManagement.searchWord(dictionary, englishWord);
                Alert selectionAlert = new Alert(Alert.AlertType.CONFIRMATION);
                selectionAlert.setTitle("Từ này đã tồn tại");
                selectionAlert.setHeaderText(null);
                selectionAlert.setContentText("Thay thế hoặc bổ sung nghĩa cho từ này");
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
                if (selection.get() == ButtonType.CANCEL) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setContentText("Thay đổi không được công nhận");
                    alert.show();
                }
            } else {
                dictionary.add(word);
                dictionaryManagement.dictionaryExportToFile(dictionary, path);
                showSuccessAlert();
            }
            addBtn.setDisable(true);
            resetInput();
        } else if (option.get() == ButtonType.CANCEL) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setContentText("Thay đổi không được công nhận");
            alert.show();
        }
    }

    private void resetInput() {
        wordTargetInput.setText("");
        explanationInput.setText("");
        example.setText("");
        wordTargetType.setText("");
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
    @FXML
    public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;
    @FXML
    public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;
    @FXML
    public AnchorPane container;
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private final String path = "src/main/resources/Utils/dictionaries.txt";

}
