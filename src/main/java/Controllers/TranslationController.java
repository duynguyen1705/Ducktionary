package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class TranslationController extends Controller implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleOnClickTranslateBtn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        sourceLangField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (sourceLangField.getText().trim().isEmpty()) translateBtn.setDisable(true);
                else translateBtn.setDisable(false);
            }
        });

        translateBtn.setDisable(true);
        toLangField.setEditable(false);
    }

    @FXML
    private void handleOnClickTranslateBtn() throws IOException {
        String fromText = sourceLangField.getText();
        HttpResponse<String> response = Unirest.post("https://microsoft-translator-text.p.rapidapi.com/translate?to%5B0%5D=" + toLanguage +"&api-version=3.0&profanityAction=NoAction&textType=plain")
            .header("content-type", "application/json")
            .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
            .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
            .body("[\r\n    {\r\n        \"Text\": \"" + fromText + "\"\r\n    }\r\n]")
            .asString();

        String text = response.getBody().substring(76, response.getBody().length()-15);
        toLangField.setText(text);

    }

    @FXML
    private void handleOnClickSwitchToggle() {
        sourceLangField.clear();
        toLangField.clear();
        if (isToVietnameseLang) {
            englishLabel.setLayoutX(426);
            vietnameseLabel.setLayoutX(104);
            sourceLanguage = "vi";
            toLanguage = "en";
        } else {
            englishLabel.setLayoutX(100);
            vietnameseLabel.setLayoutX(426);
            sourceLanguage = "en";
            toLanguage = "vi";
        }
        isToVietnameseLang = !isToVietnameseLang;
    }

    @FXML
    private TextArea sourceLangField, toLangField;

    @FXML
    private Button translateBtn;

    @FXML
    private Label englishLabel , vietnameseLabel;
    @FXML
    public Tooltip tooltip1, tooltip2, tooltip3, tooltip4, tooltip5, tooltip6;

    @FXML
    public Button addWordBtn, translateBtn1, searchWordBtn, closeBtn, menuBtn, gameBtn;

    @FXML
    public AnchorPane container;
    private String sourceLanguage = "en";
    private String toLanguage = "vi";
    private boolean isToVietnameseLang = true;
}
