package Controllers;

import CommandlineVer.*;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SearcherController extends Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(dictionary, path);
        System.out.println(dictionary.size());
        dictionaryManagement.setTrie(dictionary);
        setListDefault(0);

        searchTerm.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (searchTerm.getText().isEmpty()) {
                    cancelBtn.setVisible(false);
                    setListDefault(0);
                } else {
                    cancelBtn.setVisible(true);
                    handleOnKeyTyped();
                }
            }
        });

        cancelBtn.setOnAction(e -> {
                searchTerm.clear();
                notAvailableAlert.setVisible(false);
                cancelBtn.setVisible(false);
                setListDefault(0);
        });
        searchWordBtn.setOnAction(e -> showComponent("/GUI/SearcherGui.fxml"));

        addWordBtn.setOnAction(e -> showComponent("/GUI/AdditionGui.fxml"));

        translateBtn.setOnAction(e -> showComponent("/GUI/TranslationGui.fxml"));

        menuBtn.setOnAction(e -> showComponent("/GUI/MenuGui.fxml"));

        gameBtn.setOnAction(e -> showComponent("/GUI/MenuGameGui.fxml"));

        tooltip1.setShowDelay(Duration.seconds(0.5));
        tooltip2.setShowDelay(Duration.seconds(0.5));
        tooltip3.setShowDelay(Duration.seconds(0.5));
        tooltip4.setShowDelay(Duration.seconds(0.5));
        tooltip5.setShowDelay(Duration.seconds(0.5));
        tooltip6.setShowDelay(Duration.seconds(0.5));

        closeBtn.setOnMouseClicked(e -> {
            System.exit(0);
        });

        explanation.setEditable(false);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        notAvailableAlert.setVisible(false);
    }

    @FXML
    private void handleOnKeyTyped() {
        list.clear();
        String searchKey = searchTerm.getText().trim();
        list = dictionaryManagement.lookupWord(dictionary, searchKey);
        if (list.isEmpty()) {
            notAvailableAlert.setVisible(true);
            setListDefault(firstIndexOfListFound);
        } else {
            notAvailableAlert.setVisible(false);
            listResults.setItems(list);
            firstIndexOfListFound = dictionaryManagement.searchWord(dictionary, list.get(0));
        }
    }

    @FXML
    private void handleMouseClickAWord(MouseEvent arg0) {
        String selectedWord = listResults.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            indexOfSelectedWord = dictionaryManagement.searchWord(dictionary, selectedWord);
            if (indexOfSelectedWord == -1) return;
            Word word = dictionary.get(indexOfSelectedWord);
            englishWord.setText(word.getWordTarget());
            explanation.setText(word.getWordType() + "\n" + word.getWordExplain() + "\n" + word.getWordExample());
            headerOfExplanation.setVisible(true);
            explanation.setVisible(true);
            explanation.setEditable(false);
            saveBtn.setVisible(false);
        }
    }

    @FXML
    private void handleClickEditBtn() {
        explanation.setEditable(true);
        saveBtn.setVisible(true);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText("Bạn đã cho phép chỉnh sửa nghĩa này");
        alert.show();
    }

    @FXML
    private void handleClickSoundBtn() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(dictionary.get(indexOfSelectedWord).getWordTarget());
        } else throw new IllegalStateException("Cannot find voice: kevin16");
    }

    @FXML
    private void handleClickSaveBtn() {
        Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Cập nhật");
        alertConfirmation.setContentText("Bạn có chắc muốn cập nhật từ này");
        alertConfirmation.show();
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        if (option.get() == ButtonType.OK) {
            dictionaryManagement.updateWord(dictionary, indexOfSelectedWord, explanation.getText(), path);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn đã cập nhật thành công");
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Thay đổi không được công nhận");
            alert.show();
        }
        saveBtn.setVisible(false);
        explanation.setEditable(false);
    }

    @FXML
    private void handleClickDeleteBtn() {
        Alert alertWarning = new Alert(AlertType.WARNING);
        alertWarning.setTitle("Xóa");
        alertWarning.setTitle("Bạn có chắc muốn xóa từ này khỏi từ điển?");
        alertWarning.getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> option = alertWarning.showAndWait();
        if (option.get() == ButtonType.OK) {
            dictionaryManagement.deleteWord(dictionary, indexOfSelectedWord, path);
            refreshAfterDeleting();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn đã xóa thành công");
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Thay đổi không được công nhận");
            alert.show();
        }
    }

    private void refreshAfterDeleting() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(englishWord.getText())) {
                list.remove(i);
                break;
            }
        listResults.setItems(list);
        headerOfExplanation.setVisible(false);
        explanation.setVisible(false);
    }

    private void setListDefault(int index) {
        list.clear();
        for (int i = index; i < Math.min(index + 15, dictionary.size()); i++) {

                list.add(dictionary.get(i).getWordTarget());


        }
        listResults.setItems(list);
        englishWord.setText(dictionary.get(index).getWordTarget());
        explanation.setText(dictionary.get(index).getWordExplain());
    }

    @FXML
    private TextField searchTerm;

    @FXML
    private Button cancelBtn, saveBtn;

    @FXML
    private Label englishWord, notAvailableAlert;

    @FXML
    private TextArea explanation;

    @FXML
    private ListView<String> listResults;

    @FXML
    private Pane headerOfExplanation;
    @FXML
    public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;

    @FXML
    public Button addWordBtn, translateBtn, searchWordBtn, closeBtn, menuBtn, gameBtn;

    @FXML
    public AnchorPane container;
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private final String path = "src/main/resources/Utils/dictionaries.txt";
    ObservableList<String> list = FXCollections.observableArrayList();
    private int indexOfSelectedWord;
    private int firstIndexOfListFound = 0;

}
