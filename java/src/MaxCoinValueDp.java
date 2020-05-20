import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*

Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. 
We play a game against an opponent by alternating turns. 
In each turn, a player selects either the first or last coin from the row, 
removes it from the row permanently, and receives the value of the coin. 
Determine the maximum possible amount of money we can definitely win if we move first.

Note: The opponent is as clever as the user.
Awesome each user takes the max between the end
5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)
8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)
*/

public class MaxCoinValueDp {


    private static int find( int[] coins, int beginIdx, int endIdx, Map<String,Integer> mem) {
        String key = beginIdx + ":" + endIdx;
        // this should not happen, 0 case;
        if ( beginIdx >= endIdx ) return 0;
        // this should not happen, odd case;
        if ( beginIdx == endIdx -1 ) return coins[beginIdx];
        // last 2 coins base case;
        if ( beginIdx + 1 == endIdx -1 ) return Math.max(coins[beginIdx], coins[beginIdx+1] );

        if ( mem.containsKey(key ) ) return mem.get(key);
        int max = Math.max(
                coins[beginIdx] + Math.min( find(coins,beginIdx+2,endIdx,mem), find(coins,beginIdx+1,endIdx-1,mem)),
                coins[endIdx-1] + Math.min( find(coins,beginIdx+1,endIdx-1,mem), find(coins,beginIdx,endIdx-2,mem)));
        mem.put(key,max);
        return max;
    }

    public static int find( int[] coins ) {
        return find(coins,0, coins.length, new HashMap<>());
    }

    public static void main(String[] args) {
        int[] coins = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(coins));
    }
}