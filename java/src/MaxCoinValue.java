import java.util.Arrays;
/*

Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. 
We play a game against an opponent by alternating turns. 
In each turn, a player selects either the first or last coin from the row, 
removes it from the row permanently, and receives the value of the coin. 
Determine the maximum possible amount of money we can definitely win if we move first.

Note: The opponent is as clever as the user.
Awesome each user takes the max between the end
5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)
this is max min problem

optimization problem
*/

public class MaxCoinValue {

    private static int find( int[] coins, int beginIdx, int endIdx ) {
        int totalCoins = endIdx - beginIdx;
        if ( totalCoins ==  0 ) return 0;

        if ( totalCoins == 1 ) return coins[beginIdx];

        if ( totalCoins == 2 ) return Math.max(coins[beginIdx], coins[beginIdx+1] );

        return Math.max(
                // user 1 picks from begin, second user picks begin, or end
                coins[beginIdx] + Math.min( find(coins,beginIdx+2,endIdx), find(coins,beginIdx+1,endIdx-1)),
                // user 1 picks from end, second user picks begin, or end
                coins[endIdx-1] + Math.min( find(coins,beginIdx+1,endIdx-1), find(coins,beginIdx,endIdx-2)));
    }

    public static int find( int[] coins ) {
        return find(coins,0, coins.length);
    }

    public static void main(String[] args) {
        int[] coins = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(coins));
    }
}