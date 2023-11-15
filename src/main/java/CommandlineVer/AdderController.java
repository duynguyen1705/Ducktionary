package CommandlineVer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class AdderController {

    @FXML
    TextField wordName;

    @FXML
    TextField wordExplaination;

    TextField wordExample;

    @FXML

    Word newWord;

    public void createWord() {
        System.out.println("Button clicked! \n");
        try {
            String wordNameText = wordName.getText();
            String wordExplainationText = wordExplaination.getText();
            String wordExampleText = wordExample.getText();

            if (wordNameText == "" || wordExplainationText == "" || wordExampleText == "") {
                throw new RuntimeException("Missing components");
            }




        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
