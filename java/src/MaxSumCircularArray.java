
/*
*
*
*  https://leetcode.com/problems/maximum-sum-circular-subarray/
*
* Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
*
*
*   Solution
*    1, 2,3, 4,   -3 , -3,   4,5, 5
*    ----------||--min-----||-----
*        a         b          c
*
*  max sum = max kadane algo
*  min sum = min kadane algo
*  total = total sum
*  circlular max = max( toal - min. max );
*  except for all negative values
*  check for this case: totat = min
*     -3,-2,-1  = -6 +6 == 0  minSum is done by inverting array and applying kadane algo
*
 */
public class MaxSumCircularArray {

    public static int maxSubarraySumCircular(int[] nums) {
        int currMax = nums[0];
        int currMin = currMax;
        int max = currMax;
        int min = currMax;
        int total = currMax;
        for ( int i = 1; i < nums.length;++i) {
            currMax = Math.max(nums[i],nums[i]+currMax);
            max = Math.max(max,currMax);
            currMin = Math.min(nums[i],nums[i]+currMin);
            min = Math.min(min,currMin);
            total += nums[i];
        }
        
        // edga case: all negative case
        return (total == min) ? max : Math.max(max,total-min);
    }

    public static void main(String[] args){
        int[] nums = {1,-2,3,-2};
        System.out.println(maxSubarraySumCircular(nums));
    }

}
