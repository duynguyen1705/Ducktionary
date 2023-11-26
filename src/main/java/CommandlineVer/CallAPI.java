package CommandlineVer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import kong.unirest.Unirest;
import okhttp3.*;
import org.json.*;

public class CallAPI {

  public static String translate(String text, String from, String to) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://microsoft-translator-text.p.rapidapi.com/translate?to%5B0%5D=" + to + "&api-version=3.0&profanityAction=NoAction&textType=plain"))
              .header("content-type", "application/json")
              .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
              .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
              .method("POST", HttpRequest.BodyPublishers.ofString("[\r\n    {\r\n        \"Text\": \"" + text + "\"\r\n    }\r\n]"))
              .build();
      HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());
      return response.body().substring(76, response.body().length() - 15);
    }
    catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
    return "Không thể dịch";
  }

  public static Word lookup(String text) throws IOException, InterruptedException {

    String wordTarget = "";
    String wordType = "";
    String wordExplain = "";

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://microsoft-translator-text.p.rapidapi.com/Dictionary/Lookup?to=vi&api-version=3.0&from=en"))
            .header("content-type", "application/json")
            .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
            .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
            .method("POST", HttpRequest.BodyPublishers.ofString("[\r\n    {\r\n        \"Text\": \"" + text + "\"\r\n    }\r\n]"))
            .build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());


    if (response.statusCode() == 200) {// Kiểm tra xem yêu cầu đã thành công hay không
      System.out.println(response.body());
      String jsonData = response.body();
      System.out.println("response" + response);
      if (jsonData == null) {
        System.out.println("Vailzzz");
        return null;
      }
      JSONArray jsonArray = new JSONArray(response.body());
      System.out.println(jsonArray.getJSONObject(0));
      JSONObject obj = jsonArray.getJSONObject(0);
      JSONArray translations = obj.getJSONArray("translations");

      JSONObject subObj = translations.getJSONObject(0);

      wordTarget += obj.getString("normalizedSource");
      wordType += subObj.getString("posTag");

      if (wordType.equals("NOUN")) {
          wordType = "danh từ";
      } else if (wordType.equals("VERB")) {
          wordType = "động từ";
      } else if (wordType.equals("ADJECTIVE")) {
          wordType = "tính từ";
      } else if (wordType.equals("ADVERB")) {
          wordType = "trạng từ";
      } else {
          wordType = "khác";
      }
      for (int i = 0; i < translations.length(); i++) {
        JSONObject jsonObj = translations.getJSONObject(i);
        if (jsonObj.getString("normalizedTarget") != wordTarget) {
          wordExplain += subObj.getString("normalizedTarget");
          break;
        }
      }

    } else {
      // Xử lý lỗi hoặc thông báo khi yêu cầu không thành công
      System.out.println("Yêu cầu không thành công. Mã trạng thái: " + response.statusCode());
    }
    Word newWord = new Word(wordTarget, wordType, wordExplain);

    example(newWord);

    return newWord;
  }

  public static void example(Word word) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://microsoft-translator-text.p.rapidapi.com/Dictionary/Examples?to=vi&from=en&api-version=3.0"))
              .header("content-type", "application/json")
              .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
              .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
              .method("POST", HttpRequest.BodyPublishers.ofString("[\r\n    {\r\n        \"Text\": \"" + word.getWordTarget() + "\",\r\n        \"Translation\": \"" + word.getWordExplain() + "\"\r\n    }\r\n]"))
              .build();
      HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());

      String responseBody = response.body();
      System.out.println(responseBody);
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
          word.setWordExample(tmp.toString());
        } else {
          word.setWordExample("No examples found");
        }
      } else {
        System.out.println("response null");
        word.setWordExample("No response received");
      }
    } catch (IOException e) {
      System.out.println("IOExeption");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}