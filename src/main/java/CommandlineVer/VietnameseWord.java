package CommandlineVer;

import java.util.ArrayList;
import java.util.List;

public class VietnameseWord {
  private String displayTarget;
  private String posTag;
  private double confidence;
  private List<Word> backTranslations = new ArrayList<>();

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public List<Word> getBackTranslations() {
    return backTranslations;
  }

  public void setPosTag(String posTag) {
    this.posTag = posTag;
  }

  public void setDisplayTarget(String displayTarget) {
    this.displayTarget = displayTarget;
  }

  public void setBackTranslations(List<Word> backTranslations) {
    this.backTranslations = backTranslations;
  }

  public String getPosTag() {
    return posTag;
  }

  public String getDisplayTarget() {
    return displayTarget;
  }

  public double getConfidence() {
    return confidence;
  }

  @Override
  public String toString() {
    String backTrans = "";
    for (Word i : backTranslations)
      backTrans += i.getWordTarget() + ", ";
    return displayTarget
        + "\n" + "Loại từ: " + posTag
        + "\n" + "Độ tin cậy: " + confidence
        + "\n" + "Các từ liên quan: " + backTrans + "\n";
  }
}
