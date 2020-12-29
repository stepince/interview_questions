
public class LongestIncreasingSubsequence {
/*
https://leetcode.com/problems/longest-increasing-subsequence/solution/

We have discussed Overlapping Sub problems and Optimal Substructure properties.

Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem 
that can be solved using Dynamic Programming.
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80 }
Ans: 6
*/
    private static int find( int[] nums, int idx, int prev){
        if ( idx == nums.length ) return 0;
        //exclude
        int max = find(nums, idx+1, prev);
        if ( nums[idx] > prev ) {
            // include
            max = Math.max(max, 1 + find(nums, idx+1, nums[idx]) );
        }
        return max;
    }

    public static int find( int[] nums){
        // Integer.MAX_VALUE is the starting value
        return find( nums, 0, Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        //int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int[] nums = {1,3,5,4,7};
        int[] nums = {27,1,13,88,39,33,59,26,94,79,56,40,11,11,69,91,75,18,67,42,61,24,96,82,88,51,44,22,74,90,79,31,29,47,5,85,16,80,81,76,61,41,93,7,51,4,33,16,67,17,0,11,35,40,60,32,0,38,46,73,66,17,22,47,17,84,22,30,7,19,46,50,72,85,91,93,23,81,90,25,4,94,90,3,88,39};

        System.out.println(find(nums));
    }
}