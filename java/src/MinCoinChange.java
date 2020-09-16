import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/

Given a value V, if we want to make change for V cents,
and we have infinite supply of each of C = { C1, C2, .. , Cm}
valued coins, what is the minimum number of coins to make the change?


java MinCoinChange

Input: coins[] = "5 2 1", V =  11

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required

find1: really slow exponential
O(S^N)
where S is the amount and N is the number of coins
find2:
0(2^S)
where S is the amount

*/
public class MinCoinChange {

    private static Integer findUtil(int[] coins, int amount) {
        if ( amount == 0 ) return 0;
        if ( amount < 0 ) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for ( int i = 0; i < coins.length && coins[i] <= amount; ++i ){
            int result = findUtil( coins, amount-coins[i]);
            min = ( result == Integer.MAX_VALUE ) ? min : Math.min(min, 1 + result);
        }
        return min;
    }

    public static Integer find1(int[] coins, int amount) {
        Arrays.sort(coins);
        int result = findUtil(coins, amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static long find2(int[] coins, int amount, int idx) {
        if ( idx == coins.length || amount < 0 ) return Integer.MAX_VALUE;
        if ( amount == 0 ) return 0;
        if ( coins[idx] > amount ){
            return find2(coins,amount,idx+1);
        }
        else {
            return Math.min( 1 + find2(coins,amount-coins[idx], idx), find2(coins,amount,idx+1) );
        }
    }

    public static long find2(int[] coins,int amount) {
        Arrays.sort(coins);
        long result = find2(coins,amount, 0);
        return result >= Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
//        int[] coins = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int value = Integer.parseInt(args[1]);

        int[] coins = {25, 10, 5};
        int amount = 30;
//        int[] coins = {186,419,83,408};
//        int amount = 6249;
//        int[] coins = {58,92,387,421,194,208,231};
//        int amount = 7798;
        System.out.println( find1(coins,amount) );
        long t = System.currentTimeMillis();
        System.out.println( find2(coins,amount) );
        System.out.println("time: " + ( System.currentTimeMillis() - t));
    }
}