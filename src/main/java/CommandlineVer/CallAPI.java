package CommandlineVer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.util.Objects;
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

  public static String lookup(String text) {
    HttpResponse<String> response = Unirest.post("https://microsoft-translator-text.p.rapidapi.com/Dictionary/Lookup?to=vi&api-version=3.0&from=en")
        .header("content-type", "application/json")
        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
        .body("[\r\n    {\r\n        \"Text\": \"" + text + "\"\r\n    }\r\n]")
        .asString();
    return response.getBody();
//
//    if (response.getStatus() == 200) { // Kiểm tra xem yêu cầu đã thành công hay không
//      String jsonData = response.getBody();
//      if (jsonData == null) {
//        System.out.println(response.getBody());
//        return newWord;
//      }
//      JSONArray jsonArray = new JSONArray(response.getBody());
//      for (int i = 0; i < jsonArray.length(); i++) {
//        JSONObject obj = jsonArray.getJSONObject(i);
//        String normalizedSource = obj.getString("normalizedSource");
//        JSONArray translations = obj.getJSONArray("translations");
//
//        for (int j = 0; j < translations.length(); j++) {
//          JSONObject translation = translations.getJSONObject(j);
//          String normalizedTarget = translation.getString("normalizedTarget");
//          JSONArray backTranslations = translation.getJSONArray("backTranslations");
//
//          for (int k = 0; k < backTranslations.length(); k++) {
//            JSONObject backTranslation = backTranslations.getJSONObject(k);
//            String normalizedText = backTranslation.getString("normalizedText");
//            int frequencyCount = backTranslation.getInt("frequencyCount");
//
//            // In ra dữ liệu đã lọc được
//            System.out.println("Normalized Source: " + normalizedSource);
//            System.out.println("Normalized Target: " + normalizedTarget);
//            System.out.println("Normalized Text: " + normalizedText);
//            System.out.println("Frequency Count: " + frequencyCount);
//            System.out.println("----------------------");
//          }
//        }
//      }
//
//
//    } else {
//      // Xử lý lỗi hoặc thông báo khi yêu cầu không thành công
//      System.out.println("Yêu cầu không thành công. Mã trạng thái: " + response.getStatus());
//    }
//
//    return newWord;
  }


  public static void example(Word word) {
    HttpResponse<String> response = Unirest.post(
            "https://microsoft-translator-text.p.rapidapi.com/Dictionary/Examples?to=vi%3CREQUIRED%3E&from=en%3CREQUIRED%3E&api-version=3.0")
        .header("content-type", "application/json")
        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
        .body(
            "[\r\n    {\r\n        \"Text\": \"" + word.getWordTarget() + "\",\r\n        \"Translation\": \"" + word.getWordExplain() + "\"\r\n    }\r\n]")
        .asString();
    List<String> list = new ArrayList<>();
    JSONArray jsonArray = new JSONArray(response.getBody());
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject item = jsonArray.getJSONObject(i);
      String normalizedSource = item.getString("normalizedSource");
      if (normalizedSource.equals(word.getWordTarget())) {
        JSONArray examples = item.getJSONArray("examples");
        for (int j = 0; j < examples.length(); j++) {
          JSONObject example = examples.getJSONObject(i);
          String temp = example.getString("sourcePrefix") + example.getString("sourceTerm")
              + example.getString("sourceSuffix");
          String trans = example.getString("targetPrefix") + example.getString("targetTerm")
              + example.getString("targetSuffix");
          String last = temp + " (" + trans + ")";
          list.add(last);
        }
      }
    }
    word.setWordExample(list.get(0));
  }




}

