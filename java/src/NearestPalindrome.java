import java.util.Objects;
import java.util.PriorityQueue;

/*

Needs to be improved, a really low leetcode score but passed all test cases.
Is is classified as hard.
https://leetcode.com/problems/find-the-closest-palindrome/
 */
public class NearestPalindrome {

    static long min( long a, long b, long c ){
        return Math.min(Math.min(a,b),c);
    }
    static int numLength(long num){
        return (int) (Math.log10(num) + 1);
    }
    static long nearest( long x, long palindromeA, long palindromeB, long palindromeC ){
        // rule is to ignore same, get diff
        long diffA = ( x == palindromeA ) ? Long.MAX_VALUE  : Math.abs(x - palindromeA);
        long diffB = ( x == palindromeB ) ? Long.MAX_VALUE  : Math.abs(x - palindromeB);
        long diffC = ( x == palindromeC ) ? Long.MAX_VALUE  : Math.abs(x - palindromeC);

        long min = min(diffA,diffB,diffC);

        PriorityQueue<Long> mins = new PriorityQueue<>();
        if ( min == diffA ) {
            mins.add( palindromeA );
        }
        if ( min == diffB ) {
            mins.add( palindromeB );
        }
        if ( min == diffC ) {
            mins.add( palindromeC );
        }
        return Objects.requireNonNull(mins.poll());
    }

    static boolean isPalindrome(long num){
        if ( num < 0 ) return false;
        long reverse = 0;
        for ( long i = num; i != 0 ; i/=10) {
            reverse = reverse*10 + i%10;
        }
        return num == reverse;
    }

    static String reverse(String str){
        char[] chars = new char[str.length()];
        for ( int i = 0; i < chars.length; ++i){
            chars[i] = str.charAt(chars.length-1-i);
        }
        return new String(chars);
    }

    static long getMinPalindrome( long x, long rootVal ){
        // choose between upper and lower palindromes;
        // rootVal is a palindrome and lower
        if ( rootVal < x ){
            return rootVal;
        }
        else {
            // choose lower if it is a palindrome
            long diff = Math.abs( rootVal - x);
            long lower = x - diff;
            return ( isPalindrome(lower) ) ? lower : rootVal;
        }
    }

    static long getEvenPalindrome(long val){
        String root = String.valueOf(val);
        return Long.parseLong(root +reverse(root) );
    }

    // 1001  100-99
    static long getEvenDecreasePalindrome(long val){
        String root = String.valueOf(val);
        return Long.parseLong(root+"9"+reverse(root) );
    }

    // 101  100-99
    static long getOddDecreasePalindrome(long val){
        String root = String.valueOf(val);
        return Long.parseLong(root+reverse(root) );
    }

    static long getEvenIncreasePalindrome(long val){
        String valStr = String.valueOf(val);
        int len = valStr.length();
        String root = valStr.substring(0,len-1);
        return Long.parseLong(root+"0"+reverse(root) );
    }

    //1001 100  99
    static long getOddIncreasePalindrome(long val){
        String valStr = String.valueOf(val);
        int len = valStr.length();
        String root = valStr.substring(0,len-1);
        return Long.parseLong(root+reverse(root) );
    }

    static long getOddPalindrome(long val){
        String valStr = String.valueOf(val);
        int len = valStr.length();
        String root = valStr.substring(0,len-1);
        return Long.parseLong(root+ valStr.charAt(len-1) +reverse(root) );
    }

    static long getPalindromeFromRoot(long root, int centerLen, int len ){
        int rootLen = numLength(root);
        if ( rootLen == centerLen ) {
            return len % 2 == 0 ? getEvenPalindrome(root) : getOddPalindrome(root);
        }
        else if ( rootLen > centerLen ) {
            return len % 2 == 0 ? getEvenIncreasePalindrome(root) : getOddIncreasePalindrome(root);
        }
        else {
            return len % 2 == 0 ? getEvenDecreasePalindrome(root) : getOddDecreasePalindrome(root) ;
        }
    }

    public static String nearestPalindromic(String n) {
        long x = Long.parseLong(n);
        // cheaper to do brute force for lower numbers and handles edge cases
        if ( x < 100 ) return nearestPalindromicBruteForce(n);

        int len = n.length();
        String root = n.substring(0,len/2);
        if ( len%2 == 1  ) root += n.charAt(len/2);
        long rootVal = Long.parseLong(root);

        long rootCenterVal = getPalindromeFromRoot(rootVal,root.length(), len );

        // root higher val
        long rootHigherVal = getPalindromeFromRoot(rootVal+1, root.length(), len );

        // root lower val
        long rootLowerVal = getPalindromeFromRoot(rootVal-1, root.length(), len );

        rootCenterVal = getMinPalindrome(x,rootCenterVal);
        rootHigherVal = getMinPalindrome(x,rootHigherVal);
        rootLowerVal = getMinPalindrome(x,rootLowerVal);
        return String.valueOf(nearest(x,rootCenterVal,rootHigherVal,rootLowerVal));
    }

    static String nearestPalindromicBruteForce(String n) {
        long x = Long.parseLong(n);
        for ( long i = 1 ; i < Long.MAX_VALUE; ++i){
            if ( isPalindrome(x-i) ){
                return String.valueOf(x-i);
            }
            else if ( isPalindrome(x+i) ){
                return String.valueOf(x+i);
            }
        }
        return null;
    }
    public static void main(String[] args){
        long t = System.currentTimeMillis();
        //          807045053350540708
//        String n = "807045053224792883";
        String n = "10001";
        System.out.println(nearestPalindromic(n));
        System.out.println("time: " + (System.currentTimeMillis() -t) );
    }
}
