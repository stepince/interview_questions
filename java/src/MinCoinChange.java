import java.util.Arrays;

/*

Given a value V, if we want to make change for V cents,
and we have infinite supply of each of C = { C1, C2, .. , Cm}
valued coins, what is the minimum number of coins to make the change?


java MinCoinChange

Input: coins[] = "5 2 1", V =  11

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required

0(2^n)
where is the number of coins

*/
public class MinCoinChange {

    private static Integer find(int amount, int[] coins, int count) {
        if ( amount == 0 ) return count;
        if ( amount < 0 ) return Integer.MAX_VALUE;
        int ways = Integer.MAX_VALUE;
        for ( int coin: coins ){
            ways= Math.min( ways, find(amount - coin, coins, count+1));
        }
        return ways;
    }

    public static Integer find(int amount, int[] coins) {
        int result = find(amount, coins, 0);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private static Integer find2(int amount, int[] coins, int idx, int count) {
        if ( idx >= coins.length || amount < 0 ) return Integer.MAX_VALUE;
        if ( amount == 0 ) return count;
        return Math.min( find2(amount-coins[idx], coins,idx,count+1), find2(amount,coins,idx+1,count) );
    }

    public static Integer find2(int amount, int[] coins) {
        int result = find2(amount, coins, 0, 0);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int[] coins = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int value = Integer.parseInt(args[1]);
        System.out.println( find(value, coins) );
        System.out.println( find2(value, coins) );
    }
}