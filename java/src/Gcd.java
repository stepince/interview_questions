/*
  find the greatest common divisor
 */

import java.util.Arrays;

public class Gcd {

    static int gcd(int a, int b){
        if ( a == b ) return a;
        if ( a == 0 ) return b;
        if ( b == 0 ) return a;
        return a > b ? gcd( a - b , b) : gcd( a, b-a);
    }

    static int find( int[] arr ){
        if ( arr == null ) return 0;
        int div =  arr[0];
        for ( int i =0; i < arr.length-1; ++i){
            div = Math.min(div, gcd(arr[i],arr[i+1]));
        }
        return div;
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}
