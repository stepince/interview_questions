/*

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

  https://leetcode.com/problems/target-sum/

 */

public class TargetSumWaysDp {


    int total;
    int[] totals;
    public int findTargetSumWaysUtil(int[] nums, int idx, int S, Integer[][] mem) {
        if ( idx == nums.length ) return S == 0 ? 1 : 0;
        if ( Math.abs(S) > this.totals[idx] ) return 0;
        int key = this.total-S;
        if ( mem[idx][key] != null ) return mem[idx][key];
        int result = findTargetSumWaysUtil( nums, idx + 1, S+nums[idx], mem) + findTargetSumWaysUtil(nums, idx+1, S-nums[idx], mem);
        return mem[idx][key] = result;
    }

    public int findTargetSumWays(int[] nums, int S) {
        this.totals = new int[nums.length];
        int total = 0;
        for ( int i = nums.length-1; i >= 0; --i ){
            total += nums[i];
            totals[i] = total;
        }
        this.total = total;
        Integer[][] mem = new Integer[nums.length][2*total+1];
        return findTargetSumWaysUtil(nums, 0, S, mem);
    }
}
