/*

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

  https://leetcode.com/problems/target-sum/

 */

public class TargetSumWays {

    public int findTargetSumWaysUtil(int[] nums, int idx, int S) {
        if ( idx == nums.length ) return S == 0 ? 1 : 0;
        return findTargetSumWaysUtil( nums, idx + 1, S+nums[idx]) + findTargetSumWaysUtil(nums, idx+1, S-nums[idx]);
    }

    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWaysUtil(nums, 0, S);
    }
}
