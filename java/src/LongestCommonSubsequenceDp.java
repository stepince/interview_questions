/*
LCS for input Sequences "ABCDGH" and "AEDFHR" is "ADH" of length 3.
LCS for input Sequences "AGGTAB" and "GXTXAYB" is "GTAB" of length 4

node longestCommonSubsequence.js "ABCDGH" and "AEDFHR"


note: this could be done recursively instead of two loops
      if ( arr[idx1] == arr[idx2)] find( idx1+1, ... idx2+1 )
*/

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequenceDp {

    private static int find( char[] arr1, int idx1, char[] arr2, int idx2, Map<String,Integer> mem ){
        if ( idx1 >= arr1.length  || idx2 >= arr2.length ) return 0;
        String key = idx1 + ":" + idx2;
        if ( mem.containsKey(key) ) return mem.get(key);
        int currentMax = 0;
        for ( int i = idx1; i < arr1.length; ++i ){
            for ( int j = idx2; j < arr2.length; ++j ){
                if ( arr1[i] == arr2[j] ) {
                    currentMax = Math.max(currentMax, find( arr1, i+1, arr2, j+1, mem ));
                }
            }
        }
        mem.put(key,currentMax);
        return currentMax;
    }

    public static int find( char[] arr1, char[] arr2){
        return find( arr1, 0, arr2, 0,new HashMap<>());
    }

    public static void main(String[] args) {
        String str1 = args[0];
        String str2 = args[1];
        System.out.println( find(str1.toCharArray(), str2.toCharArray()));
    }
}