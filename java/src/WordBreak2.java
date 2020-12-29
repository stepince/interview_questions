import java.util.*;

/*
Given an input string and a dictionary of words, find out if the input string can be segmented
 into a space-separated sequence of dictionary words. See following examples for more details.
This is a famous Google interview question, also being asked by many other companies now a days.

input: ilikesamsung
output: i like sam sung, i like samsung: true

https://leetcode.com/problems/word-break-ii/

Time:
O(2^N)
breakpoint at every character
e.g. aaaaaaaaaaaaaaaaa

Space:
O(2^N)
breakpoint at every character

notes: this can be rewritten using a for loop recursion, which is O(2^n)
 */
public class WordBreak2 {

    public static List<String> wordBreakUtil(String s, Set<String> wordDict) {

        List<String> results = new ArrayList<>();
        if ( wordDict.contains(s) ){
            results.add( s );
        }
        for ( int i = 1, size = s.length(); i < size; ++i ){
            String lsubstring = s.substring(0,i);
            if ( wordDict.contains(lsubstring) ) {
                String rsubstring = s.substring(i);
                for ( String suffix : wordBreakUtil(rsubstring, wordDict)) {
                    results.add( lsubstring + " " + suffix );
                }
            }
        }
        return results;
    }

    public static List<String> wordBreak(String s, Set<String> wordDict) {
        return wordBreakUtil(s, wordDict );
    }

     public static void main(String[] args){
        
         //String str = args[0];
         String s = "catsanddog";
         Set<String> dict = Set.of("cat","cats","and","sand","dog");
         System.out.println(wordBreak(s,dict));

    }
}