import java.util.HashMap;
import java.util.Map;

public class MostCommonNgram {

    public static String find( String str, int n ){
        Map<String,Integer> freqMap = new HashMap<>();
        int max = 0;
        String ngram = null;
        for ( int i = 0, j = n ; j <= str.length(); ++i, ++j ){
             String substr = str.substring(i,j);
             int freq = freqMap.merge(substr,1, Integer::sum );
             if ( freq > max ) {
                 max = freq;
                 ngram =  substr;
             }
        }
        return ngram;
    }

    public static void main(String[] args){
         String str =  "abcabcdefgdefgdefg";
         System.out.println(find(str,4));
    }

}
