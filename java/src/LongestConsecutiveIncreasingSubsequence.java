/*
https://leetcode.com/problems/longest-continuous-increasing-subsequence/

Given an unsorted array of integers, find the length
of longest continuous increasing subsequence (subarray).

 */
public class LongestConsecutiveIncreasingSubsequence {

    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int count = 1;
        int global = 1;

        for (int i = 1; i < nums.length; ++i) {
            count = (nums[i] > nums[i-1]) ? ++count : 1;
            global = Math.max(count, global);
        }
        return global;
    }

    public static void main(String[] args){
        int[] nums = {1,3,5,4,7};
        System.out.println(findLengthOfLCIS(nums));
    }
}
