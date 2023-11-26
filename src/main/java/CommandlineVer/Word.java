package CommandlineVer;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Word implements Comparable <Word>{
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

    public Word( String wordTarget, String wordType, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordType = wordType;
        this.wordExplain = wordExplain;
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

    @Override
    public String toString() {
        return wordTarget + "\n" + wordExplain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return this.getWordTarget() == word.getWordTarget();
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordTarget, wordExplain, wordType, wordExample);
    }


    @Override
    public int compareTo(@NotNull Word o) {
        return this.getWordTarget().compareTo(o.getWordTarget());
    }
}
