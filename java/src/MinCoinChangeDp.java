import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
/*

java MinCoinChange

input: coins "5 2 1" , 11
output: 3

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required

O(n)
where is the amount of money
*/
public class MinCoinChangeDp {

    private static Integer find(int amount, int[] coins, int count, Map<Integer,Integer> mem) {
        if ( amount == 0 ) return count;
        if ( amount < 0 ) return Integer.MAX_VALUE;
        int ways = Integer.MAX_VALUE;
        if ( mem.containsKey(amount) ) return mem.get(amount);
        for ( int coin: coins ){
            ways= Math.min( ways, find(amount - coin, coins, count+1, mem));
        }
        mem.put(amount,ways);
        return ways;
    }

    public static int find( int amount, int[] coins ){
        return find(amount, coins, 0,new HashMap<>());
    }

    public static void main(String[] args) {
        int[] coins = Arrays.stream( args[0].split("[\\s,]+") ).mapToInt(Integer::parseInt).toArray();
        int value = Integer.parseInt(args[1]);
        System.out.println( find( value, coins) );
    }
}