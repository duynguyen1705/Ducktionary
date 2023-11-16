package CommandlineVer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import java.net.URLEncoder;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class translateText {
  static final String VN = "vi";
  static final String EN = "en";

  public static String callGoogleTrans(String translateThis, String toLanguage) {
    // Default query
    // final String emptyQueryValue = "%3CREQUIRED%3E";     Can be excluded
    String translateThisQuery = "q=EMPTY&target=TO&source=FROM";

    // Finish the query
    if (Objects.equals(translateThis, "")) {
//            translateThisQuery = translateThisQuery.replace("EMPTY", emptyQueryValue);
      return "";  // Return an empty String if the input is empty.
    } else {
      translateThisQuery = translateThisQuery.replace("EMPTY", translateThis);
    }

    if (toLanguage.equals(VN)) {
      translateThisQuery = translateThisQuery.replace("FROM", EN);
      translateThisQuery = translateThisQuery.replace("TO", VN);
    } else {
      translateThisQuery = translateThisQuery.replace("FROM", VN);
      translateThisQuery = translateThisQuery.replace("TO", EN);
    }

    // Json format: {"data":{"translations":[{"translatedText":"TRANSLATED WORD"}]}}
    // Length:      0123456789ABCDEFGHIJ123456789abcdefghij123456789ABCDEFGHIJ123456
    String response = getResponse(translateThisQuery);
    return response.substring(44, response.length() - 5);   // Return the translatedText only!
  }

  public static String getResponse(String translateThisQuery) {
    HttpResponse<String> response = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
        .header("content-type", "application/x-www-form-urlencoded")
        .header("accept-encoding", "application/gzip")
        .header("x-rapidapi-host", "google-translate1.p.rapidapi.com")
        .header("x-rapidapi-key", "9e99971471mshfdd0cf36e96afbap15b1dajsn192fdc524617")
        .body(translateThisQuery)
        .asString();
    return response.getBody();
  }

}
