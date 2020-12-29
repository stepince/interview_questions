/*
Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
https://leetcode.com/problems/edit-distance

Insert
Remove
Replace
All of the above operations are of equal cost.
input:  geek" "gesek"
output: 1

Input:   str1 = "sunday", str2 = "saturday"
Output:  3
O(N^2)
 */
public class StringDistanceDp {

    static private int min( int a, int b , int c){
        return Math.min(a, Math.min(b,c));
    }

    static int findUtil(char[] chars1, int idx1, char[] chars2, int idx2, Integer[][] mem ) {
        if ( idx1 == chars1.length && idx2 == chars2.length ) return 0;
        if ( idx1 == chars1.length ) return chars2.length - idx2;
        if ( idx2 == chars2.length ) return chars1.length - idx1;

        if ( mem[idx1][idx2] != null ) return mem[idx1][idx2];

        if ( chars1[idx1] == chars2[idx2] ) return mem[idx1][idx2] = findUtil(chars1, idx1+1, chars2, idx2+1, mem);

        // replace cost
        int replace = 1 + findUtil(chars1, idx1+1, chars2, idx2+1,mem);

        // delete/insert char1
        int deleteInsert1 = 1 + findUtil(chars1, idx1+1, chars2, idx2,mem);

        // delete/insert char2
        int deleteInsert2 = 1 + findUtil(chars1, idx1, chars2, idx2+1,mem);
        return mem[idx1][idx2] = min( replace, deleteInsert1, deleteInsert2  );
    }

    public static int find(String word1, String word2) {
        return findUtil(word1.toCharArray(), 0, word2.toCharArray(), 0, new Integer[word1.length()+1][word2.length()+1] );
    }

    public static void main(String[] args){
        String word1 = args[0];
        String word2 = args[1];
        System.out.println(find(word1,word2));
    }

}