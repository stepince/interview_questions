

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

O(3^m)
where m is the size of word1 and no matching.



 */
public class StringDistance {

    private static int min( int a, int b , int c){
        return Math.min(a, Math.min(b,c));
    }

    static int findUtil(char[] chars1, int idx1, char[] chars2, int idx2) {
        if ( idx1 == chars1.length && idx2 == chars2.length ) return 0;
        if ( idx1 == chars1.length ) return chars2.length - idx2;
        if ( idx2 == chars2.length ) return chars1.length - idx1;

        if ( chars1[idx1] == chars2[idx2] ) findUtil(chars1, idx1+1, chars2, idx2+1);

        // replace cost
        int replace = 1 + findUtil(chars1, idx1+1, chars2, idx2+1);

        // delete/insert char1
        int deleteInsert1 = 1 + findUtil(chars1, idx1+1, chars2, idx2);

        // delete/insert char2
        int deleteInsert2 = 1 + findUtil(chars1, idx1, chars2, idx2+1);
        return min( replace, deleteInsert1, deleteInsert2  );
    }

    public static int find(String word1, String word2) {
        return findUtil(word1.toCharArray(), 0, word2.toCharArray(), 0);
    }

    public static void main(String[] args){
        String word1 = args[0];
        String word2 = args[1];
        System.out.println(find(word1, word2));
    }

}