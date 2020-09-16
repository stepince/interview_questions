import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

public class LongestIncreasingSubsequenceDp {
/*

We have discussed Overlapping sub problems and Optimal Substructure properties.

Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem 
that can be solved using Dynamic Programming.
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80 }
Ans: 6
*/
     private static int find( int[] arr, int idx, int maxValue, Map<String,Integer> mem ){
         if ( idx == 0 ) return 0;
         String key = idx + ":"+ maxValue;
         if ( mem.containsKey(key) ) {
             return mem.get(key);
         }
         // exclude
         int max = find(arr, idx-1, maxValue, mem);
         if ( arr[idx-1] < maxValue ) {
             // include
             max = Math.max(max, 1 + find(arr, idx-1, arr[idx-1],mem));
         }
         mem.put(key,max);
         return max;
     }

     public static int find( int[] arr){
        return find( arr, arr.length, Integer.MAX_VALUE, new HashMap<>());
     }

     public static void main(String[] args) {
         int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
         System.out.println(find(arr));
     }


}