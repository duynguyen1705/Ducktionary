package CommandlineVer;

import java.util.ArrayList;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.*;

public class CallAPI {

  public static String translate(String text, String from, String to) {
    if (from != "")
      from = "from=" + from + "&";
    HttpResponse<String> response = Unirest.post(
                    "https://microsoft-translator-text.p.rapidapi.com/translate?to%5B0%5D=vi" + to
                            + "%3CREQUIRED%3E&api-version=3.0&" + from + "profanityAction=NoAction&textType=plain")
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

    HttpResponse<String> response = Unirest.post(
                    "https://microsoft-translator-text.p.rapidapi.com/Dictionary/Lookup?to=vi&api-version=3.0&from=en")
            .header("content-type", "application/json")
            .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
            .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
            .body("[\r\n    {\r\n        \"Text\": \"" + text + "\"\r\n    }\r\n]")
            .asString();


    if (response.getStatus() == 200) {// Kiểm tra xem yêu cầu đã thành công hay không
      System.out.println(response.getBody());
      String jsonData = response.getBody();
      System.out.println("response" + response);
      if (jsonData == null) {
        System.out.println("Vailzzz");
        return null;
      }
      JSONArray jsonArray = new JSONArray(response.getBody());
      System.out.println(jsonArray.getJSONObject(0));
      JSONObject obj = jsonArray.getJSONObject(0);
      String normalizedSource = obj.getString("normalizedSource");
      JSONArray translations = obj.getJSONArray("translations");

      JSONObject subObj = translations.getJSONObject(0);

      wordTarget += obj.getString("normalizedSource");
      wordType += subObj.getString("posTag");

      for (int i = 0; i < translations.length(); i++) {
        JSONObject jsonObj = translations.getJSONObject(i);
        if (jsonObj.getString("normalizedTarget") != wordTarget) {
          wordExplain += subObj.getString("normalizedTarget");
          break;
        }
      }

    } else {
      // Xử lý lỗi hoặc thông báo khi yêu cầu không thành công
      System.out.println("Yêu cầu không thành công. Mã trạng thái: " + response.getStatus());
    }
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