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

    HttpResponse<String> response = Unirest.post("https://microsoft-translator-text.p.rapidapi.com/Dictionary/Lookup?to=vi&api-version=3.0&from=en")
        .header("content-type", "application/json")
        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
        .body("[\r\n    {\r\n        \"Text\": \"" + text + "\"\r\n    }\r\n]")
        .asString();
//    return response.getBody();

    if (response.getStatus() == 200) { // Kiểm tra xem yêu cầu đã thành công hay không
      String jsonData = response.getBody();
      System.out.println("response" + jsonData);
      if (jsonData == null) {
        return null;
      }
      JSONArray jsonArray = new JSONArray(response.getBody());
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

    return newWord;
  }


  public static void example(Word word) {
    try {
      HttpResponse<String> response = Unirest.post(
                      "https://microsoft-translator-text.p.rapidapi.com/Dictionary/Examples?to=vi&from=en&api-version=3.0")
              .header("content-type", "application/json")
              .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
              .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
              .body("[{\"Text\": \"" + word.getWordTarget() + "\", \"Translation\": \"" + word.getWordExplain() + "\"}]")
              .asString();

      System.out.println(response);
      String responseBody = response.getBody();


      if (responseBody != null && !responseBody.isEmpty()) {
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

          word.setWordExample(tmp.toString());
        } else {
          word.setWordExample("No examples found");
        }
      } else {
        // Handle the case where the response body is null or empty
        // It could indicate an issue with the API request or no data received
        word.setWordExample("No response received");
      }
    } catch (Exception e) {
      // Handle exceptions gracefully
      e.printStackTrace(); // Log the exception for debugging purposes
      // Add appropriate error handling or fallback mechanism
    }
  }






}

