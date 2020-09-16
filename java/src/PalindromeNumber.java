/*

https://leetcode.com/problems/palindrome-number/

 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if ( x < 0 ) return false;
        int temp = x;
        int reverse = 0;
        while ( temp != 0 ){
            reverse = reverse*10 + temp%10;
            temp /= 10;
        }
        return reverse == x;
    }
    public static void main(String[] args ){
//        int val  = 9989900;
        int val =  102;
        long t = System.currentTimeMillis();
        System.out.println(isPalindrome(val));
        System.out.println("time = " + ( System.currentTimeMillis() - t ) );
    }
}
