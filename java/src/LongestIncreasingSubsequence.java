
public class LongestIncreasingSubsequence {
/*

We have discussed Overlapping Sub problems and Optimal Substructure properties.

Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem 
that can be solved using Dynamic Programming.
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80 }
Ans: 6
*/


    private static int find( int[] arr, int idx, int maxValue){
        if ( idx == 0 ) return 0;
        //exclude
        int max = find(arr, idx-1, maxValue);
        if ( arr[idx-1] < maxValue ) {
            // include
            max = Math.max(max, 1 + find(arr, idx-1, arr[idx-1]) );
        }
        return max;
    }

    public static int find( int[] arr){
        // Integer.MAX_VALUE is the starting value
        return find( arr, arr.length, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        //int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr = {1};
        System.out.println(find(arr));
    }
}