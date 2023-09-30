package org.example;

public class Word {
  private String word_target;
  private String word_explain;
  private String word_type;
  private String word_example;

  /**constructor with parameters. */
  public Word(String _word_target, String _word_explain, String _word_type, String _word_example) {
    word_target = new String(_word_target);
    word_explain = new String(_word_explain);
    word_type = new String(_word_type);
    word_example = new String(_word_example);
  }

  /**constructor without parameters */
  public Word() {
    word_target = "";
    word_explain = "";
    word_type = "";
    word_example = "";
  }

  /**getter word. */
  public String getWord() {
    return new String (word_target + "  |  " + "  |  " + word_explain + "  |  " + word_type + "  |  " + word_example);
  }

  /**setter word_target. */
  public void setWordTarget(String n) {
    word_target = new String(n);
  }

  /**getter word_target. */
  public String getWordTarget() {
    return new String(word_target);
  }

  /**setter word_explain. */
  public void setWordExplain(String n) {
    word_explain = new String(n);
  }

  /**getter word_explain. */
  public String getWordExplain() {
    return new String(word_explain);
  }

  /**setter word_type. */
  public void setWordType(String n) {
    word_example = new String(n);
  }

  /**getter word_type. */
  public String getWordType() {
    return new String(word_type);
  }

  /**setter word_example. */
  public void setWordExample(String n) {
    word_example = new String(n);
  }

  /**getter word_example. */
  public String getWordExample() {
    return new String(word_example);
  }

}