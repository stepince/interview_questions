import java.util.*;
/*
https://leetcode.com/problems/word-break

Given an input string and a dictionary of words, find out if the input string can be segmented
 into a space-separated sequence of dictionary words. See following examples for more details.
This is a famous Google interview question, also being asked by many other companies now a days.

input: ilikesamsung
output: i like sam sung, i like samsung: true

O(N*S)
 where N is the length of the string and
 S is the length of the longest word

 */
public class WordBreakDp {

    private static final Set<String> DICTIONARY = Set.of("i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango");

    private static boolean find( String str, Map<String,Boolean> mem ){

        if ( DICTIONARY.contains(str) ) return true;
        if ( mem.containsKey(str) ) return mem.get(str);

        for ( int i = 1, size = str.length(); i < size; ++i ) {
            String substr = str.substring(0,i);
            if ( DICTIONARY.contains(substr) && find( str.substring(i), mem) ) {
                return mem.merge(str,true,(prev,curr)->true);
            }
        }
        return mem.merge(str,false,(prev,curr)->false);
    }

    public static boolean find(String str) {
        return find(str, new HashMap<>() );
    }
    public static void main(String[] args){
        String str = args[0];
        System.out.println(find(str));
        System.out.println("string len:" + str.length());
    }
}