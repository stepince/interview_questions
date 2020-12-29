/*
Count the number of prime numbers less than a non-negative number, n.
https://leetcode.com/problems/count-primes/

 */

import java.util.Arrays;

public class CountPrimes {

    public static int countPrimes(int n) {
        if ( n < 2 ) return 0;
        boolean[] primes = new boolean[n+1];
        Arrays.fill(primes,true);
        primes[0]= false;
        primes[1]= false;
        for ( int i = 2; i*i <= n; ++i ){
            // following a prime number pattern
            //  i^2, i^2+i, i^2+2i, i^2+3i, ..., not exceeding n do
            if ( primes[i] ) for ( long j = (long)i*i; j <= n; j+=i ){
                primes[(int)j] = false;
            }
        }
        int count = 0;
        for ( int i = 0; i <= n; ++i){
            if ( primes[i] ) ++count;
        }
        return count;
    }

    public static void main(String[] args){
//        int n = Integer.parseInt(args[0]);
        int n  = 10;
        System.out.println(countPrimes(n));
    }
}
