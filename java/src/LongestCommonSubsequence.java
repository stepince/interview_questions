/*

https://leetcode.com/problems/longest-common-subsequence/

LCS for input Sequences "ABCDGH" and "AEDFHR" is "ADH" of length 3.
LCS for input Sequences "AGGTAB" and "GXTXAYB" is "GTAB" of length 4

node longestCommonSubsequence.js "ABCDGH" and "AEDFHR"

note: this could be done recursively instead of two loops
      if ( arr[idx1] == arr[idx2)] find( idx1+1, ... idx2+1 )
*/

public class LongestCommonSubsequence {

    private static int find( char[] chars1, int idx1, char[] chars2, int idx2){
        if ( idx1 >= chars1.length || idx2 >= chars2.length ) return 0;
        int max = 0;
        for ( int i = idx1; i < chars1.length; ++i ){
            for ( int j = idx2; j < chars2.length; ++j ){
                if ( chars1[i] == chars2[j] ) {
                    max = Math.max(max, 1 + find( chars1, i+1, chars2, j+1));
                }
            }
        }
        return max;
    }

    public static int find( char[] chars1, char[] chars2){
        return find( chars1, 0, chars2, 0);
    }

    public static void main(String[] args) {
//        String str1 = args[0];
//        String str2 = args[1];
        String str1= "ABCDGH";
        String str2= "AEDFHR";
        System.out.println( find(str1.toCharArray(), str2.toCharArray()));
    }
}