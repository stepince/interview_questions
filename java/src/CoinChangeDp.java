
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

O( C*N )
Where C is the number of coins and N is the amount
*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinChangeDp {

    public static int makeChange(int n, int[] coins) {
        return makeChange(n,coins,0, new HashMap<>());
    }

    private static int makeChange(int n, int[] coins, int idx, Map<String,Integer> mem) {
        if ( n < 0) return 0;
        if ( n == 0 ) return 1;
        if ( idx == coins.length ) return 0;
        String key = n + ":" + idx;
        if ( mem.containsKey(key) ) return mem.get(key);
        int result = makeChange( n - coins[idx], coins, idx,mem) + makeChange( n, coins, idx+1,mem);
        return mem.merge(key,result, (prev,curr)->curr);
    }

    private static int makeChange2(int n, int[] coins, int idx, Map<String,Integer> mem) {
        if( n < 0 ) return 0;
        if ( n == 0 ) return 1;
        int count = 0;
        String key = n+":" +idx;
        if ( mem.containsKey(key) ) return mem.get(key);
        for ( int i = idx; i < coins.length; ++i ) {
            if ( coins[i] > n ) break;
            // use i and not 0 so as not repeat
            // e.g. (1,2,2) is the same as (2,1,2) don't use (2,1,2)
            count +=makeChange2(n - coins[i], coins, i,mem);
        }
        return mem.merge(key,count,(prev,curr)->curr);
    }

    public static int makeChange2(int n, int[] coins) {
        return makeChange2(n,coins,0 , new HashMap<>());
    }
    public static void main( String[] args ) {
//        int[] coins = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int n = Integer.parseInt(args[1]);
        int[] coins = { 50, 25, 10, 5, 1};
        int n = 11;
        Arrays.sort( coins );
        System.out.println(makeChange( n, coins));
        System.out.println(makeChange2( n, coins));
    }

}