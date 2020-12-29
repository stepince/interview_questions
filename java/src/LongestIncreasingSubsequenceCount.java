import java.util.*;

/*



*/

/*
https://leetcode.com/problems/number-of-longest-increasing-subsequence/

Given an unsorted array of integers, find the number of longest increasing subsequence.

*/
public class LongestIncreasingSubsequenceCount {

    public int lis( int[] nums, int idx, int[] lens, int[] counts){
        if ( lens[idx] != -1 ) return lens[idx];
        int result = 1;
        for( int i = idx + 1; i < nums.length;++i){
            int len = 1 + lis( nums, i, lens, counts);
            if ( nums[idx] >= nums[i] ) continue;
            if ( len == result ) {
                counts[idx] += counts[i];
            }
            else if ( len > result ){
                counts[idx] = counts[i];
                result = len;
            }
        }
        return lens[idx] = result;
    }

    int numberoflis(int[] nums){
        int[] lens = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(lens,-1);
        Arrays.fill(counts,1);
        int max = 1;
        lis(nums,0,lens,counts);
        for ( int len : lens  ) max = Math.max(max,len);

        int total = 0;
        for ( int i = 0; i < lens.length;++i ){
            if ( lens[i] == max ) total += counts[i];
        }
        return total;
    }

    public int findNumberOfLIS(int[] nums) {
        if ( nums.length == 0 ) return 0;
        return numberoflis(nums);
    }

    public static void main(String[] args) {
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int[] nums = {1,3,5,4,7};
//        int[] nums = {1,2,4};
//        int[] nums = {2,2};
        int[] nums = {27,1,13,88,39,33,59,26,94,79,56,40,11,11,69,91,75,18,67,42,61,24,96,82,88,51,44,22,74,90,79,31,29,47,5,85,16,80,81,76,61,41,93,7,51,4,33,16,67,17,0,11,35,40,60,32,0,38,46,73,66,17,22,47,17,84,22,30,7,19,46,50,72,85,91,93,23,81,90,25,4,94,90,3,88,39};
        long t = System.currentTimeMillis();
        System.out.println( new LongestIncreasingSubsequenceCount().findNumberOfLIS(nums));
        System.out.println("time = " + ( System.currentTimeMillis() - t ) );
    }
}