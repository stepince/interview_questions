import java.util.Arrays;

/*

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
 */

public class MaxFruit {

    public static int find(int[] arr) {
        if ( arr == null || arr.length == 0 ) return 0;
        int globalMax = 1;
        int prevCount = 0;
        int prevTree = arr[0];
        int currTree = arr[0];
        int currCount = 1;

        for ( int i = 1; i < arr.length; ++i ){
            if ( arr[i] == currTree ){
                ++currCount;
                globalMax = Math.max(globalMax, currCount + prevCount);
            }
            else if ( arr[i] == prevTree ){
                ++prevCount;
                globalMax = Math.max(globalMax, currCount + prevCount);
            }
            else {
                prevTree = currTree;
                currTree = arr[i];
                prevCount = currCount;
                currCount = 1;
                globalMax = Math.max(globalMax, currCount + prevCount);
            }
        }
        return globalMax;
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}