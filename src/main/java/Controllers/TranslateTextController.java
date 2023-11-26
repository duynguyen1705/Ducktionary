package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CommandlineVer.CallAPI;
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

public class TranslateTextController extends MainController {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        handleActionChangeScene();

        sourceLangField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (sourceLangField.getText().trim().isEmpty()) translateBtn.setDisable(true);
                else translateBtn.setDisable(false);
            }
        });

        translateBtn.setDisable(true);
        toLangField.setEditable(false);
        translateBtn.setOnAction(e -> {
            System.out.println("hello");
            String fromText = sourceLangField.getText();
            String text = CallAPI.translate(fromText, "", toLanguage);
            toLangField.setText(text);
        });
    }


    @FXML
    private void handleOnClickSwitchToggle() {
        sourceLangField.clear();
        toLangField.clear();
        if (isToVietnameseLang) {
            englishLabel.setLayoutX(426);
            vietnameseLabel.setLayoutX(104);
            toLanguage = "en";
        } else {
            englishLabel.setLayoutX(100);
            vietnameseLabel.setLayoutX(426);
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
    public AnchorPane container;
    private String toLanguage = "vi";
    private boolean isToVietnameseLang = true;
}