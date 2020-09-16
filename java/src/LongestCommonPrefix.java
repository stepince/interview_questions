/*
    https://leetcode.com/problems/longest-common-prefix/
    Find the longest common prefix in a list of words:
    Time:
     O(S)
     Where S is the sum of all word lengths

 */

public class LongestCommonPrefix {

    public String find(String[] strs) {
        if ( strs.length == 0 ) return "";
        final StringBuilder sb = new StringBuilder(strs[0]);
        for (int i = 1; i < strs.length; ++i ){
            final char[] chars = strs[i].toCharArray();
            for ( int j = 0; j < Math.min(chars.length,sb.length()) ; ++j ){
                // set a new sb.length
                if ( sb.charAt(j) != chars[j] ) sb.setLength(j);
            }
            // set a sb.length for a too large arr
            if ( sb.length() > chars.length ) sb.setLength(chars.length);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        //String[] strs = {"aaa","aa","aaa"};
        String[] strs = {"flower","flow"};
        //String[] strs = {"a"};
        System.out.println( new LongestCommonPrefix().find(strs));
    }
}
