import java.util.ArrayList;
import java.util.List;
/*
Time:
O( word1.length * word2.length);
e.g
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaa
*/

public class Grep {

    public static boolean compareWord( String word1, String word2, int idx ){
        if ( word1.length() + idx > word2.length() ) return false;
        for ( int i =0; i < word1.length(); ++i ){
            if ( word1.charAt(i) != word2.charAt(idx+i) ) return false;
        }
        return true;
    }

    public static void findUtil(String needle, String haystack, int idx, List<Integer> indices){
        if ( idx >= haystack.length() - needle.length() ) return;
        if ( compareWord(needle,haystack,idx) ){
            indices.add(idx);
        }
        findUtil(needle, haystack, idx+1,  indices);
    }

    public static List<Integer> find(String needle, String haystack){
        List<Integer> l = new ArrayList<>();
        findUtil(needle, haystack, 0,l);
        return l;
    }

    public static void main(String[] args){
        String needle = args[0];
        String haystack = args[1];
        System.out.println(find(needle,haystack));

    }
}
