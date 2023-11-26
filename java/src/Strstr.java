

/*


https://leetcode.com/problems/implement-strstr/

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */

public class Strstr {


    static int[] computeLps(char[] needle){
        int[] lps = new int[needle.length];
        for ( int i = 1, j = 0; i < lps.length; ){
            if ( needle[i] == needle[j] ){
                lps[i++]=++j;
            } else if ( j == 0 ){
                ++i;
            } else {
                j = lps[j-1];
            }
        }
        return lps;
    }

    public static int strstr(String needle, String haystack) {
        if ( needle == null || needle.equals("") ) return 0;
        int[] lps = computeLps(needle.toCharArray());
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        for ( int i = 0, j = 0; i < haystackLen;   ){
            if ( haystack.charAt(i) == needle.charAt(j) ){
                ++i;
                ++j;
            } else if ( j == 0 ){
                ++i;
            } else {
                j = lps[j-1];
            }
            if ( j == needleLen ){
                return i - needleLen;
            }
        }
        return -1;
    }

    public static void main(String[] args){

//        String needle = "ll";
//        String haystack = "hello";

//        String needle = "bba";
//        String haystack = "aaaaa";

        String needle = "abcxabcy";
        String haystack = "abcxabcxabcy";
        System.out.println(strstr(needle,haystack));
    }
}
