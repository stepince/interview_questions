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

Time:
    O(n)
    where is the number of weights
*/
import java.util.Map;
import java.util.HashMap;
//import java.util.stream.Stream;

public class KnapsackMaxValueDp {

    public static int find(int[] wts, int[] values, int weight){
        return find(wts, values, weight,0, new HashMap<>());
    }

    private static int find(int[] wts, int[] values, int weight, int idx,  Map<Integer, Integer> mem){
        if ( weight <= 0 || idx == wts.length ) return 0;
        Integer key = weight;

        if ( mem.containsKey(weight) ) mem.get(key);
        // just run the exclude part if we can not include it
        if ( wts[idx] > weight ){
            return mem.merge(key,find(wts, values, weight, idx+1, mem),(prev,curr)->curr);
        }
        // the check for inclusion total has been done, so it is ok to include total
        int result = Math.max(values[idx] + find(wts, values,weight-wts[idx], idx+1,mem),find(wts, values, weight,idx+1,mem));
        return mem.merge(key,result,(prev,curr)->curr);
    }

    public static void main(String[] args) {
//        int[] values = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int[] wts = Stream.of(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int weight = Integer.parseInt(args[2]);

        int[] values = {60,100,120};
        int[] wts = {10,20,30};
        int weight = 50;
        System.out.println(find(wts, values, weight));
    }
}