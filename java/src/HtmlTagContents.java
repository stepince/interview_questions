import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTagContents {
    private static final String regex = "(.*?<(.+?)>([^<]+)</\\2>)";
    private static final Pattern pattern = Pattern.compile(regex);

    private static String getContents(String seg){
        final StringBuilder result = new StringBuilder();
        Matcher matcher = pattern.matcher( seg );
        String sep= "";
        while ( matcher.find() ){
            final String content = matcher.group(3);
            result.append(sep).append(content);
            sep = "\n";
            seg = seg.replace(matcher.group(1),getContents(content) );
            matcher = pattern.matcher( seg );
        }
        return result.toString();
    }

    public static String find(String line) {
        line = getContents(line);
        if ( line.isEmpty() ){
            line = "None";
        }
        return line;
    }

    public static void main(String[] args) {
        String sentence = args[0];
        System.out.println(find(sentence));
    }
}