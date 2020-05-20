
/*

input : x = “GeeksforGeeks”, y = “GeeksQuiz"
output: Geeks

input : x = “abc123abc”, y = “123"
output:  123

find2: O(N^2)



notes: you can multiple common string. this is only returning one
 */

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubstrDp {

    static private int count;
    private static String maxWord( String word1, String word2 ){
        return word1.length() > word2.length() ? word1 : word2;
    }

    public static String find(String word1, String word2, int idx1, int idx2, String prefix, Map<String, String> mem){
        if ( idx1 == word1.length() || idx2 == word2.length() ) return prefix;
        String maxWord = prefix;
        String key = idx1 + ":" + idx2;
        if ( mem.containsKey(key) ) return mem.get(key);
        for ( int i = idx1; i < word1.length(); ++i ){
            for ( int j = idx2; j < word2.length(); ++j ){
                ++count;
                if ( word1.charAt(i) == word2.charAt(j) ){
                    maxWord = maxWord( maxWord, find(word1,word2, i+1, j+1, prefix + word1.charAt(i),mem));
                }
                // reset prefix to empty string
                prefix="";
            }
        }
        mem.put(key,maxWord);
        return maxWord;
    }

    public static String find(String word1, String word2 ){
        return find(word1,word2, 0, 0, "",new HashMap<>());
    }

    public static void main(String[] args){
//        String word1 = args[0];
//        String word2 = args[1];
        String word1 = "GeeksForGeeks";
        String word2 = "GeeksQuiz";
        System.out.println(find(word1,word2));
        System.out.println(count);
        System.out.println(  word1.length() * word2.length() );
    }

}