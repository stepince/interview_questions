import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/fruit-into-baskets/

  In a row of trees, the i-th tree produces fruit with type tree[i].

  You start at any tree of your choice, then repeatedly perform the following steps:

  1) Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
  2) Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.

  Note: That you do not have any choice after the initial choice of starting tree:
  you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
  The number represents the type of fruit.

  You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to
  only carry one type of fruit each.

  What is the total amount of fruit you can collect with this procedure?

  input : "1,2,1"
 output : 3

Explanation: We can collect [1,2,1].

This solution is pretty fast O(N*size);
 */

public class MaxFruitDp {

    static int totalFruitUtil(int[] tree, int idx, Set<Integer> basket, Integer[][] mem ) {
        if ( idx == tree.length || (basket.size() == 2 && !basket.contains(tree[idx]) ) ) return 0;
        basket.add(tree[idx]);
        int size = basket.size();
        if ( mem[idx][size] != null ) return mem[idx][size] ;
        return mem[idx][size] = (1 + totalFruitUtil(tree,idx+1,basket,mem));
    }

    public static int totalFruit(int[] tree) {
        int total = 0;
        Integer[][] mem = new Integer[tree.length+1][3];
        for ( int i = 0; i < tree.length; ++i ) {
            Set<Integer> basket = new HashSet<>();
            basket.add(tree[i]);
            total = Math.max( total, 1 + totalFruitUtil(tree,i+1, basket, mem) );
        }
        return total;
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(totalFruit(arr));
    }
}