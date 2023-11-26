import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/*
    Add google gson libs to project.

 */

public class TopicCount {

    static class Page {
        Parse parse;
    }
    static class Parse {
        Text text;
    }
    static class Text {
        @SerializedName(value = "*")
        String all;
    }


    static final String url = "https://en.wikipedia.org/w/api.php?action=parse&format=json&page=";

    static String encode(String value ) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    static String getContent( String url ) throws Exception {
        InputStream is = new URL(url).openStream();
        BufferedReader rdr = new BufferedReader( new InputStreamReader(is, StandardCharsets.UTF_8));
        return rdr.lines().collect(Collectors.joining("\n"));
    }

    static int wordCount(String text, String word){
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(text);
        // java 9 matcher.results().count();
        int count = 0;
        while (matcher.find()) ++count;
        return count;
    }

    static int getTopicCount(String topic){
        try {
            String requestUrl = url + encode(topic);
            String text = getContent( requestUrl );
            Gson gson = new GsonBuilder().create();
            Page pageObj = gson.fromJson(text, Page.class);
            return wordCount(pageObj.parse.text.all, topic);
        }
        catch ( Exception ex ){
            return 0;
        }
    }

    public static void main(String[] args ){
        String topic = "pizza";
        System.out.println(  getTopicCount(topic)  );
    }
}
