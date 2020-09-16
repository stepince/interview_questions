/*

https://leetcode.com/problems/longest-palindromic-substring/

Expand around the center algorithm

Time:
   O(N^2)

Space:
   O(1)

 */


public class LongestPalindromeSubstring {

    static int palindromeOffset(char[] chars, int idx1, int idx2){
        int i = 0;
        for ( ;  0 <= (idx1-i) && (idx2+i) < chars.length; ++i ){
            if ( chars[idx1-i] != chars[idx2+i] )  break;
        }
        return i - 1;
    }

    private static String find(String s) {
        char[] chars = s.toCharArray();
        int maxBegin = 0;
        int maxLen = 0;
        for ( int i = 0; i < chars.length; ++i){
            int offset = palindromeOffset(chars,i,i);
            if ( offset!= -1 && 2*offset+1 > maxLen ){
                maxLen = 2*offset+1;
                maxBegin = i - offset;
            }
        }
        for ( int i = maxLen/2; i < chars.length-1; ++i){
            int offset = palindromeOffset(chars,i,i+1);
            if ( offset!= -1 && 2*offset+2 > maxLen ){
                maxLen = 2*offset+2;
                maxBegin = i - offset;
            }
        }
        return s.substring(maxBegin,maxBegin+maxLen);
    }

    public static void main(String[] args){
        String str = "xxxxaaabbbbbbbaa";
        System.out.println(find(str));
    }
}
