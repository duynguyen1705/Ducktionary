package CommandlineVer;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String wordType;

    private String wordExample;


    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
        this.wordType = "";
        this.wordExample = "";
    }
    public Word(String wordTarget, String wordExplain, String wordType, String wordExample) {
        this.wordTarget = wordTarget;
        this.wordExample = wordExample;
        this.wordExplain = wordExplain;
        this.wordType = wordType;
    }
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }


    public Word(String wordTarget, String wordExplain, String wordType) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordType = wordType;
    }
    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }



    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordExample(String wordExample) {
        this.wordExample = wordExample;
    }

    public String getWordExample() {
        return wordExample;
    }
}
