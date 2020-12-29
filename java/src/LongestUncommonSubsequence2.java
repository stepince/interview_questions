/*
Given a list of strings, you need to find the longest uncommon subsequence among them.
The longest uncommon subsequence is defined as the longest subsequence of one of these
strings and this subsequence should not be any subsequence of the other strings
https://leetcode.com/problems/longest-uncommon-subsequence-ii/submissions/

 */

public class LongestUncommonSubsequence2 {

    private static boolean isSubsequence(String base, String str) {
        char[] charsBase = base.toCharArray();
        char[] charsStr = str.toCharArray();
        int j = 0;
        for ( int i = 0 ; i < charsBase.length && j < charsStr.length; ++i ){
            if ( charsBase[i] == charsStr[j] ) ++j;
        }
        return j == charsStr.length;
    }

    public static int findLUSlength(String[] strs) {
        int max = -1;

        // compare if all strings against each other
        // not this is not reflexive relation
        // e.g.
        // checking strings a,b is not the same as b,a
        // String "aa" is a supersequence of "aaaa"
        // but "aaaa" is not a supersequence of "aa"
        for ( int i = 0; i < strs.length; ++i ){
            if ( max >= strs[i].length() ) continue;
            boolean isCandidate = true;
            // check to see if the string is not a subsequence of every string
            for (int j = 0; j < strs.length; ++j) {
                // ignore self
                if ( i == j ) continue;
                if (isSubsequence(strs[j], strs[i])) {
                    isCandidate = false;
                    break;
                }
            }
            if ( isCandidate ) {
                max = strs[i].length();
            }
        }
        return max;
    }

    public static void main(String[] args){
        String[] strs = {"aaa","aaa","aa"};
//        String[] strs = {"aabbcc","cb", "cb"};
//        String[] strs = {"aabbcc", "aabbcc","b","bc"};
        System.out.println(findLUSlength(strs));
    }
}
