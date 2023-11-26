/*

https://leetcode.com/problems/divide-two-integers/


 */

public class Divide {

    public static int divide(int a, int b){
        long absA = Math.abs(a);
        long absB = Math.abs(b);

        if ( a == 0 ) return 0;
        if ( b == 0 ) return Integer.MAX_VALUE;

        boolean pos = ( a > 0 ) == ( b > 0 );
        long ans = 0;

        int start = 1;
        long temp = absA;
        while ( temp < absB ) {
            ++start;
            temp *=2;
        }

        for( int i = start; i >= 0 ; --i ) {
            long subtr = ( absB << i);
            if ( absA >= subtr ) {
                absA -= subtr;
                ans += (1 << i);
            }
        }


        return pos
                ? (int)Math.min(ans,Integer.MAX_VALUE)
                : (int)Math.max(-1L*ans,Integer.MIN_VALUE);
    }

    public static void main(String[] args){
        int a = 444445555;
        int b = -5;
        System.out.println("ans: " + divide(a, b));
    }
}
