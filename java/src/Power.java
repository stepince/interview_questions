/*

https://leetcode.com/problems/powx-n/solution/

Time: O(logN);

Space: O(logN);
 */

public class Power {

    static double fastPower(double x, int n){
        if ( n == 0 ) {
            return 1;
        } else if ( n == 1 ) {
            return x;
        } else if ( n % 2 == 0 ) {
            double retval = fastPower(x, n/2);
            return retval * retval;
        } else {
            double retval = fastPower(x, n/2);
            return retval * retval * x;
        }
    }

    public static double myPow(double x, int n) {
        int absN = Math.abs(n);
        if ( n < 0 ) {
            return 1/fastPower(x,absN);
        } else {
            return fastPower(x,absN);
        }
    }
    public static void main(String[] args){
        System.out.println(myPow(5,2));
    }
}
