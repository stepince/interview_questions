

/*
Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.

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

    private static Integer find(String word1, String word2, int idx1, int idx2){

        if ( idx1 == 0 || idx2 == 0) return Math.abs( idx1 - idx2 );

        // same just increase;
        if ( word1.charAt(idx1-1) == word2.charAt(idx2-1) ) {
            return find(word1, word2, idx1-1, idx2-1) ;
        }

        int replaceCost = find(word1, word2, idx1-1, idx2) + 1;
        int insertCost = find(word1, word2, idx1, idx2-1) + 1 ;
        int deleteCost = find(word1, word2, idx1-1, idx2-1) + 1;

        return min( insertCost, replaceCost, deleteCost );
    }

    public  static Integer find(String word1, String word2){
        return find(word1, word2, word1.length(), word2.length());
    }

    public static void main(String[] args){
        String word1 = args[0];
        String word2 = args[1];
        System.out.println(find(word1, word2));
    }

}