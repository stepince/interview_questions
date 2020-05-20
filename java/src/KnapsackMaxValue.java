/*
Given weights and values of n items,
put these items in a knapsack of capacity W to get the maximum
total value in the knapsack. In other words, 
given two integer arrays val[0..n-1] and wt[0..n-1] 
which represent values and weights associated with n items respectively. 
Also given an integer W which represents knapsack capacity, 
find out the maximum value subset of val[] such that sum of the weights 
of this subset is smaller than or equal to W. 
You cannot break an item, either pick the complete item, or don�t pick it (0-1 property)

java KnapsackMaxValue "60 100 120" "10 20 30" 50
answer: 220
*/
import java.util.stream.Stream;

public class KnapsackMaxValue {

    public static int find(int[] wts, int[] values, int weight){
        return find(wts, values, weight,0);
    }

    private static int find(int[] wts, int[] values, int weight, int idx){
        if ( weight <= 0 || idx == wts.length ) return 0;
        // just run the exclude part if we can not include it
        // we can not include it.
        if ( wts[idx] > weight ){
            return find(wts, values, weight, idx+1);
        }
        int exclude = find(wts, values, weight,idx+1);
        // the check for inclusion total has been done, so it is ok to include total
        int include = values[idx] + find(wts, values,weight-wts[idx], idx+1);
        return Math.max(include,exclude);
    }

    public static void main(String[] args) {
        int[] values = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] wts = Stream.of(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int max = Integer.parseInt(args[2]);
        System.out.println(find(wts, values, max));
    }

}