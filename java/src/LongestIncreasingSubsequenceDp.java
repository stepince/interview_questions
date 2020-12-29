public class LongestIncreasingSubsequenceDp {
/*
https://leetcode.com/problems/longest-increasing-subsequence/solution/

We have discussed Overlapping sub problems and Optimal Substructure properties.

Let us discuss Longest Increasing Subsequence (LIS) problem as an example problem 
that can be solved using Dynamic Programming.
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
sequence such that all elements of the subsequence are sorted in increasing order.
For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80 }
Ans: 6


Notes:
   both find1 and find2 pass leetcode testcases

   find1 is simple

   find2 is more optimal
*/
     private static int find1( int[] nums, int idx, int prevIdx, Integer[][] mem ){
         if ( idx == nums.length ) return 0;

         if ( mem[idx+1][prevIdx+1] != null ) return mem[idx+1][prevIdx+1];
         // exclude
         int max = find1(nums, idx+1, prevIdx, mem);
         if ( prevIdx == - 1 || nums[idx] > nums[prevIdx]  ) {
             // include
             max = Math.max(max, 1 + find1(nums, idx+1, idx,mem));
         }
         return mem[idx+1][prevIdx+1] = max;
     }

     public static int find1( int[] nums){
        return find1( nums, 0, -1, new Integer[nums.length+1][nums.length+1]);
     }

    private static int find2(int[] nums, int idx,Integer[] mem) {
        if ( idx == nums.length ) return 0;
        if ( mem[idx] != null ) return mem[idx];
        int result = 0;
        for ( int i = idx+1; i < nums.length; ++i  ){
            if ( nums[i] > nums[idx] ){
                result = Math.max(result, 1 + find2( nums, i, mem) );
            }
        }
        return mem[idx] = result;
    }

    public static int find2(int[] nums) {
        if ( nums == null || nums.length == 0 ) return 0;
        if ( nums.length == 1 ) return 1;
        int result = 1;
        Integer[] mem = new Integer[nums.length];
        for ( int i = 0; i < nums.length; ++i){
            result = Math.max(result, 1 + find2(nums,i,mem ) );
        }
        return result;
    }

     public static void main(String[] args) {
//         int[] nums = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
         int[] nums = {27,1,13,88,39,33,59,26,94,79,56,40,11,11,69,91,75,18,67,42,61,24,96,82,88,51,44,22,74,90,79,31,29,47,5,85,16,80,81,76,61,41,93,7,51,4,33,16,67,17,0,11,35,40,60,32,0,38,46,73,66,17,22,47,17,84,22,30,7,19,46,50,72,85,91,93,23,81,90,25,4,94,90,3,88,39};
         long t = System.currentTimeMillis();
         System.out.println(find1(nums));
         System.out.println("time 1 = " + ( System.currentTimeMillis() - t ) );
         t = System.currentTimeMillis();
         System.out.println(find2(nums));
         System.out.println("time 2 = " + ( System.currentTimeMillis() - t ) );
     }
}