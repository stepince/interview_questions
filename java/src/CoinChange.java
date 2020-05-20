
/*
  Given a value N, if we want to make change for N cents, 
and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
how many ways can we make the change? The order of coins doesn't matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions:
{1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. 
For N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
{2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.

java CoinChange "1 2 3" 4
java CoinChange "2 5 3 6" 10

O( c^n )
  where c is the number of coins
   and n is the amount
*/
import java.util.Arrays;
public class CoinChange {

    public static int makeChange(int n, int[] coins) {
        return makeChange(n,coins,0);
    }

    private static int makeChange(int n, int[] coins, int idx ) {
        if ( n < 0) return 0;
        if ( n == 0 ) return 1;
        if ( idx == coins.length ) return 0;
        return makeChange( n - coins[idx], coins, idx) + makeChange( n, coins, idx+1 );
    }

    private static int makeChange2(int n, int[] coins, int idx) {
        if( n < 0 ) return 0;
        if ( n == 0 ) return 1;
        int count = 0;
        // loop through each coin, decrement the total with each recursion
        for ( int i = idx; i < coins.length; ++i ) {
            count +=makeChange2(n - coins[i], coins, i);
        }
        return count;
    }

    public static int makeChange2(int n, int[] coins) {
        return makeChange2(n,coins,0 );
    }

    public static void main( String[] args ) {
        int[] coins = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(args[1]);
        Arrays.sort( coins );
        System.out.println(makeChange( n, coins ));
        System.out.println(makeChange2( n, coins ));
    }

}