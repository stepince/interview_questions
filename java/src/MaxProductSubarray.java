
/*
https://leetcode.com/problems/maximum-product-subarray/

 */
public class MaxProductSubarray {
    public static int maxProduct(int[] nums) {

        if ( nums.length == 0 ) return 0;
        int prod = nums[0], max = nums[0];
        int runningProd = nums[0];
        for ( int i = 1; i < nums.length; ++i ){
            runningProd = runningProd == 0 ? nums[i] : runningProd * nums[i];

            prod = Math.max(nums[i],prod*nums[i]);
            max = Math.max(prod,max);
            max = Math.max(runningProd,max);
        }

        prod = runningProd = nums[nums.length-1];
        for ( int i = nums.length-2; i >= 0 ; --i ){
            runningProd = runningProd == 0 ? nums[i] : runningProd * nums[i];

            prod = Math.max(nums[i],prod*nums[i]);
            max = Math.max(prod,max);
            max = Math.max(runningProd,max);
        }
        return max;
    }

    public static void main(String[] args){
        int[] nums = {1,-2,3,-2};
        System.out.println(maxProduct(nums));
    }
}
