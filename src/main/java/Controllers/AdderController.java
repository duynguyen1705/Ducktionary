package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import CommandlineVer.*;

public class AdderController implements Initializable {

    @FXML
    TextField wordName;

    @FXML
    TextField wordExplaination;

    @FXML
    ChoiceBox<String> wordType;

    @FXML
    TextField wordExample;

    @FXML
    Word newWord;

    public void createWord() {
        System.out.println("Button clicked! \n");
        try {
            String wordNameText = wordName.getText();
            String wordTypeText = wordType.getValue();
            String wordExplainationText = wordExplaination.getText();
            String wordExampleText = wordExample.getText();

            if (wordNameText == "" || wordExplainationText == "" || wordExampleText == "") {
                throw new RuntimeException("Missing components");
            }




        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] allWordType = {"Danh từ", "Động từ", "Tính từ", "Trạng từ", "Trợ động từ", "Khác"};
        wordType.getItems().addAll(allWordType);
    }
}
