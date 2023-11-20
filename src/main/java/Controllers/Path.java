package Controllers;

public class Path {
  private static final String SearchGUI = "GUI/Searcher.fxml";
  private static final String AddGUI = "GUI/Adder.fxml";
  private static final String TranslateGUI = "GUI/Translate.fxml";
  private static final String MainGUI = "GUI/Main.fxml";
  private static final String Dictionary = "dictionaries.txt";

  public static String getAddGUI() {
    return AddGUI;
  }

  public static String getSearchGUI() {
    return SearchGUI;
  }

  public static String getTranslateGUI() {
    return TranslateGUI;
  }

  public static String getMainGUI() {
    return MainGUI;
  }
}
