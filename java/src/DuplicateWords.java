import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicateWords {

   public static String removeDuplicates(String sentence) {
        String regex = "(\\b(\\w+)(\\s+\\2\\b)+)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE );
        Matcher m = p.matcher(sentence);
        // Check for subsequences of input that match the compiled pattern
        while (m.find()) {
            sentence = sentence.replaceAll(m.group(1), m.group(2));
        }
        return sentence;
    }

    public static void main(String[] args) {
        String sentence = args[0];
        System.out.println(removeDuplicates(sentence));
    }
}