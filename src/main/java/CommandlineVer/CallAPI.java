//package CommandlineVer;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import kong.unirest.HttpResponse;
//import kong.unirest.Unirest;
//import java.util.Objects;
//import org.json.*;
//
//public class CallAPI {
//
//  public static String translate(String text, String from, String to) {
//    if (from != "")
//      from = "from=" + from + "&";
//    HttpResponse<String> response = Unirest.post(
//            "https://microsoft-translator-text.p.rapidapi.com/translate?to%5B0%5D=" + to
//                + "%3CREQUIRED%3E&api-version=3.0&" + from + "profanityAction=NoAction&textType=plain")
//        .header("content-type", "application/json")
//        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
//        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
//        .body(
//            "[\r\n    {\r\n        \"Text\": \"I would really like to drive your car around the block a few times.\"\r\n    }\r\n]")
//        .asString();
//    return response.getBody();
//  }
//
//  public static String dectect(String text) {
//    HttpResponse<String> response = Unirest.post(
//            "https://microsoft-translator-text.p.rapidapi.com/Detect?api-version=3.0")
//        .header("content-type", "application/json")
//        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
//        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
//        .body(
//            "[\r\n    {\r\n        \"Text\": \"Ich würde wirklich gern Ihr Auto um den Block fahren ein paar Mal.\"\r\n    }\r\n]")
//        .asString();
//    return response.getBody();
//  }
//
//  public static List<VietnameseWord> lookup(String text, String from, String to) {
//    HttpResponse<String> response = Unirest.post(
//            "https://microsoft-translator-text.p.rapidapi.com/Dictionary/Lookup?to=" + to
//                + "&api-version=3.0&from=" + from)
//        .header("content-type", "application/json")
//        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
//        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
//        .body("[\r\n    {\r\n        \"Text\": \"" + text + "\"\r\n    }\r\n]")
//        .asString();
//    String jsonString = response.getBody();
//    JSONArray jsonArray = new JSONArray(jsonString);
//    JSONObject jsonObject = jsonArray.getJSONObject(0);
//    List<VietnameseWord> list = new ArrayList<>();
//
//    JSONArray translations = jsonObject.getJSONArray("translations");
//    if (translations == null)
//      return null;
//    for (int i = 0; i < translations.length(); i++) {
//      VietnameseWord vnword = new VietnameseWord();
//      JSONObject translation = translations.getJSONObject(i);
//      String displayTarget = translation.getString("displayTarget");
//      vnword.setDisplayTarget(displayTarget);
//      String posTag = translation.getString("posTag");
//      vnword.setPosTag(posTag);
//      double confidence = translation.getDouble("confidence");
//      vnword.setConfidence(confidence);
//      JSONArray backTranslations = translation.getJSONArray("backTranslations");
//      List<Word> backTrans = new ArrayList<>();
//      for (int j = 0; j < backTranslations.length(); j++) {
//        JSONObject backTranslation = backTranslations.getJSONObject(j);
//        String displayText = backTranslation.getString("displayText");
//        int frequencyCount = Integer.parseInt(backTranslation.getString("frequencyCount"));
//        Word word = new Word(displayText, displayTarget, frequencyCount);
//        backTrans.add(word);
//      }
//      vnword.setBackTranslations(backTrans);
//      list.add(vnword);
//    }
//    return list;
//
//  }
//
//  public static List<String> example(String text, String mean) {
//    HttpResponse<String> response = Unirest.post(
//            "https://microsoft-translator-text.p.rapidapi.com/Dictionary/Examples?to=vi%3CREQUIRED%3E&from=en%3CREQUIRED%3E&api-version=3.0")
//        .header("content-type", "application/json")
//        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
//        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
//        .body(
//            "[\r\n    {\r\n        \"Text\": \"" + text + "\",\r\n        \"Translation\": \"" + mean + "\"\r\n    }\r\n]")
//        .asString();
//    List<String> list = new ArrayList<>();
//    JSONArray jsonArray = new JSONArray(response.getBody());
//    for (int i = 0; i < jsonArray.length(); i++) {
//      JSONObject item = jsonArray.getJSONObject(i);
//      String normalizedSource = item.getString("normalizedSource");
//      if (normalizedSource.equals(text)) {
//        JSONArray examples = item.getJSONArray("examples");
//        for (int j = 0; j < examples.length(); j++) {
//          JSONObject example = examples.getJSONObject(i);
//          String temp = example.getString("sourcePrefix") + example.getString("sourceTerm")
//              + example.getString("sourceSuffix");
//          String trans = example.getString("targetPrefix") + example.getString("targetTerm")
//              + example.getString("targetSuffix");
//          String last = temp + " (" + trans + ")";
//          list.add(last);
//        }
//      }
//    }
//    return list;
//  }
//
////  public static Map<String, String> languages() {
////    HttpResponse<String> response = Unirest.get("https://microsoft-translator-text.p.rapidapi.com/languages?api-version=3.0")
////        .header("X-RapidAPI-Key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
////        .header("X-RapidAPI-Host", "microsoft-translator-text.p.rapidapi.com")
////        .asString();
////    JSONArray jsonArray = new JSONArray(response.getBody());
////    JSONObject jsonObject = jsonArray.getJSONObject(0);
////    JSONArray translation = jsonObject.getJSONArray("translation");
////
////
////  }
////}
//
