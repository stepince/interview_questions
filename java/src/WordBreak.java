import java.util.Set;

/*
Given an input string and a dictionary of words, find out if the input string can be segmented
 into a space-separated sequence of dictionary words. See following examples for more details.
This is a famous Google interview question, also being asked by many other companies now a days.

input: ilikesamsung
output: i like sam sung, i like samsung: true

https://leetcode.com/problems/word-break/

loop
O(n^2)

recursion
O(2^n)

notes: this can be rewritten using a for loop recursion, which is O(2^n)
 */
public class WordBreak {

    private static final Set<String> DICTIONARY = Set.of("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango");

    private static boolean find2(String str, int idx) {
        if ((str == null) || (str.length() == idx) ) return false;

        String suffix = str.substring(idx + 1);
        if (DICTIONARY.contains(suffix)) return true;

        String prefix = str.substring(0, idx + 1);
        if (!DICTIONARY.contains(prefix)) return find2(str, idx + 1);
        return find2(str.substring(idx + 1), 0) || find2(str, idx + 1);
    }

    private static boolean find2( String str ) {
        return find2( str, 0);
    }

    public static boolean find( String str ){
        if ( str == null || str.length() == 0 ) return false;
        if ( DICTIONARY.contains(str) ) return true;
        for ( int i = 1; i < str.length() ; ++i ){
            String substr = str.substring(0,i);
            if ( DICTIONARY.contains(substr) && find( str.substring(i)) ) return true;
        }
        return false;
    }

     public static void main(String[] args){
         String str = args[0];
         System.out.println(find(str));
         System.out.println(find2(str));
    }
}