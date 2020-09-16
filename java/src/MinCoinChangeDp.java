import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/

java MinCoinChange

input: coins "5 2 1" , 11
output: 3

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required

find1
O(S)
where S is the amount of money

find2
O(S*N)
where S is the amount of money and N is the amount of coins

*/
public class MinCoinChangeDp {

    private static long find2(int[] coins, int amount, int idx, Long[][] mem) {
        if ( idx == coins.length || amount < 0 ) return Integer.MAX_VALUE;
        if ( amount == 0 ) return 0;
        if ( mem[amount][idx] != null ) return mem[amount][idx];
        if ( coins[idx] > amount ){
            return mem[amount][idx] = ( 1 + find2(coins,amount,idx+1,mem));
        }
        else {
            return mem[amount][idx] = Math.min( 1 + find2(coins,amount-coins[idx], idx,mem), find2(coins,amount,idx+1,mem) );
        }
    }

    private static int find1(int[] coins, int amount, Integer[] mem) {
        if ( amount < 0 ) return Integer.MAX_VALUE;
        if ( amount == 0 ) return 0;
        if ( mem[amount] != null ) return mem[amount];
        int min = Integer.MAX_VALUE;
        for ( int i = 0; i < coins.length && coins[i] <= amount; ++i ){
            int result = find1( coins, amount-coins[i], mem );
            min = ( result == Integer.MAX_VALUE ) ? min : Math.min(min, 1 + result);
        }
        return mem[amount] = min;
    }

    public static int find1( int amount, int[] coins ){
        Arrays.sort(coins);
        int result = find1(coins,amount, new Integer[amount+1]);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static long find2( int amount, int[] coins ){
        Arrays.sort(coins);
        long result = find2(coins,amount, 0, new Long[amount+1][coins.length+1] );
        return result >= Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
//        int[] coins = Arrays.stream( args[0].split("[\\s,]+") ).mapToInt(Integer::parseInt).toArray();
//        int amount = Integer.parseInt(args[1]);

//        int[] coins = {25, 10, 5};
//        int amount = 30;

//        int[] coins = {186,419,83,408};
//        int amount = 6249;

        int[] coins = {58,92,387,421,194,208,231};
        int amount = 7798;
        long t = System.currentTimeMillis();
        System.out.println( find1( amount, coins) );
        System.out.println("time: " + ( System.currentTimeMillis() - t));
        t = System.currentTimeMillis();
        System.out.println( find2( amount, coins) );
        System.out.println("time: " + ( System.currentTimeMillis() - t));

    }
}