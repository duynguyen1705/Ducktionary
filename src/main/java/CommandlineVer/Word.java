package CommandlineVer;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Word {
    private String wordTarget;
    private String wordExplain;
    private String wordPronunciation;
    private String wordType;
    private List<String> examples = new ArrayList<>();
    private int frequencyCount;
    private double confidence;
    private List<Word> relatedWords = new ArrayList<>();

    public Word() {
        this.wordTarget = "";
        this.wordPronunciation = "";
        this.wordExplain = "";
        this.wordType = "";
    }
    public Word(String wordTarget, String wordPronunciation, String wordType, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordPronunciation = wordPronunciation;
        this.wordExplain = wordExplain;
        this.wordType = wordType;
    }
    public Word(String wordTarget, String wordExplain, int frequencyCount) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.frequencyCount = frequencyCount;
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

    public String getWordPronunciation() {
        return wordPronunciation;
    }

    public void setWordPronunciation(String wordPronunciation) {
        this.wordPronunciation = wordPronunciation;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getWordType() {
        return wordType;
    }

    public int getFrequencyCount() {
        return frequencyCount;
    }
    public void setFrequencyCount(int num) {
        this.frequencyCount = num;
    }

    public void setRelatedWords(List<Word> relatedWords) {
        this.relatedWords = relatedWords;
    }

    public List<Word> getRelatedWords() {
        return relatedWords;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public List<String> getExamples() {
        return examples;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
