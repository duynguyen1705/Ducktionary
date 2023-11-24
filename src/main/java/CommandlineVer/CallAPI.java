package CommandlineVer;

import java.util.ArrayList;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.json.*;

public class CallAPI {

  public static String translate(String text, String from, String to) {
    if (from != "")
      from = "from=" + from + "&";
    HttpResponse<String> response = Unirest.post(
            "https://microsoft-translator-text.p.rapidapi.com/translate?to=" + to
                + "%3CREQUIRED%3E&api-version=3.0" + from + "profanityAction=NoAction&textType=plain")
        .header("content-type", "application/json")
        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
        .body(
            "[\r\n    {\r\n        \"Text\": \"I would really like to drive your car around the block a few times.\"\r\n    }\r\n]")
        .asString();
    return response.getBody();
  }

  public static Word lookup(String text) {
    String wordTarget = "";
    String wordType = "";
    String wordExplain = "";

    try {
      HttpResponse<String> response = Unirest.post("https://microsoft-translator-text.p.rapidapi.com/Dictionary/Lookup?to=vi&api-version=3.0&from=en")
              .header("content-type", "application/json")
              .header("X-RapidAPI-Key", "ceea9bf779mshdf99b2304bc6e47p120ad2jsn89f22c1d4cbc")
              .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
              .body("[\r {\r\"Text\": \"" + text + "\"\r}\r]")
              .asString();

      int statusCode = response.getStatus();
      if (statusCode == 200) {
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray translations = jsonResponse.getJSONArray("translations");

        if (translations.length() > 0) {
          JSONObject translationObj = translations.getJSONObject(0);
          wordTarget = jsonResponse.getString("normalizedSource");
          wordType = translationObj.getString("posTag");
          wordExplain = translationObj.getString("normalizedTarget");
        }
      } else {
        System.out.println("Yêu cầu không thành công. Mã trạng thái: " + statusCode);
      }
    } catch (UnirestException | JSONException e) {
      System.out.println("Exception occurred: " + e.getMessage());
    }

    System.out.println("wordType: " + wordType);
    System.out.println("wordExplain: " + wordExplain);

    Word newWord = new Word(wordTarget, wordType, wordExplain);
    example(newWord);

    wordExplain = "*" + wordType + "\n" + "#" + wordExplain + "\n" + newWord.getWordExample();
    newWord.setWordExplain(wordExplain);
    return newWord;
  }


  public static void example(Word word) {
    try {
      System.out.println(word);
      HttpResponse<String> response = Unirest.post("https://microsoft-translator-text.p.rapidapi.com/Dictionary/Examples?to=vi&from=en&api-version=3.0")
          .header("content-type", "application/json")
          .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
          .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
          .body("[\r\n    {\r\n        \"Text\": \"" + word.getWordTarget() + "\",\r\n        \"Translation\": \"" + word.getWordExplain() +"\"\r\n    }\r\n]")
          .asString();

      String responseBody = response.getBody();
      System.out.println(responseBody);
      System.out.println(response.getBody())  ;
      if (responseBody != null) {
        JSONArray jsonArray = new JSONArray(responseBody);
        System.out.println(jsonArray);

        List<String> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
          JSONObject item = jsonArray.getJSONObject(i);
          String normalizedSource = item.getString("normalizedSource");

          if (normalizedSource.equals(word.getWordTarget())) {
            JSONArray examples = item.getJSONArray("examples");

            for (int j = 0; j < examples.length(); j++) {
              JSONObject example = examples.getJSONObject(j);
              String temp = example.getString("sourcePrefix") + example.getString("sourceTerm")
                      + example.getString("sourceSuffix");
              String trans = example.getString("targetPrefix") + example.getString("targetTerm")
                      + example.getString("targetSuffix");
              String last = temp + " (" + trans + ")";
              list.add(last);
            }
          }
        }

        if (!list.isEmpty()) {
          StringBuilder tmp = new StringBuilder();
          for (String s : list) {
            tmp.append(s).append("\n");
          }
          System.out.println(tmp.toString());
          word.setWordExample(tmp.toString());
        } else {
          word.setWordExample("No examples found");
        }
      } else {
        System.out.println("response null");
        word.setWordExample("No response received");
      }
    } catch (Exception e) {
      e.printStackTrace(); 
    }
  }
}

