import java.util.ArrayList;
import java.util.List;
/*

https://leetcode.com/problems/text-justification/submissions/
 */
public class TextJustification {

    public static String format(List<Object> words){
        StringBuilder sb = new StringBuilder();
        for (Object word : words) {
            sb.append(word.toString());
        }
        return sb.toString();
    }

    public static String formatLeftJustified(List<Object> words, int maxWidth){
        String str = format(words);
        int len  = str.length();
        if ( len == maxWidth ) return str;
        return str + " ".repeat(Math.max(0, maxWidth - len));
    }

    public static String formatJustified(List<Object> words, int len, int maxWidth){
        for ( int i = 0;  len < maxWidth; ++i ){
            Object w = words.get( i%words.size() );
            if ( w instanceof StringBuilder ){
                ((StringBuilder)w).append(" ");
                ++len;
            }
        }
        return format(words);
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> results = new ArrayList<>();
        for( int count = 0; count < words.length; ) {
            List<Object> seg = new ArrayList<>();
            int len = words[count].length();
            seg.add(words[count]);
            ++count;
            for ( int i = count ; i < words.length; ++i){
                if ( len + words[i].length() + 1 > maxWidth ) break;
                len += 1 + words[i].length();
                seg.add(new StringBuilder(" "));
                seg.add(words[i]);
                ++count;
            }
            if ( count == words.length || seg.size() == 1  ){
                results.add(formatLeftJustified(seg,maxWidth));
            }
            else {
                results.add(formatJustified(seg,len,maxWidth));
            }
        }
        return results;
    }

    public static void main(String[] args){
        long t = System.currentTimeMillis();
        String[] words = { "This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth  = 16;
        System.out.println(fullJustify(words,maxWidth));
        System.out.println("time: " + ( System.currentTimeMillis() - t));
    }
}
