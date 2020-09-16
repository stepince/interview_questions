/*

https://leetcode.com/problems/nth-digit/

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 */

public class NthDigit {

    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    public static int findNthDigit(int n) {

        if ( n < 10 ) return n;
        // find the places bucket
        int len = 0;
        while ( 9*Math.pow(10,len)*(len+1) < n ) {
            n-= 9*Math.pow(10,len)*(++len);
        }
        // find the then number bucket

        int place = (int)Math.pow(10,len);
        int numberBucket = place + (n-1)/++len;

        // digit within bucket
        int mod = (n-1)%len;

        String numberStr = String.valueOf(numberBucket);
        char digitChar = numberStr.charAt(mod);
        return digitChar - '0';
    }

    static public void main(String[] args){
        int n = 1000;
        System.out.println(findNthDigit(n));
    }
}
