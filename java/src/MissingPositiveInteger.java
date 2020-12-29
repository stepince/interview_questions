
/*

Given an unsorted integer array nums, find the smallest missing positive integer.

Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?



Notes:
   Hard leetcode question.
   For constant space it uses the array itself tricks.
      e.g. flipping positive signs

 */



public class MissingPositiveInteger {

    public static int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        boolean hasN = false;
        for( int i = 0; i < nums.length;++i ){
            int num = nums[i];
            hasOne = hasOne || (num == 1);
            hasN = hasN || (num == nums.length);
            if ( num <= 0 || num > nums.length ) nums[i] = 1;
        }
        if (!hasOne) return 1;
        if ( nums.length == 1 ) return 2;
        for( int num : nums ){
            num = Math.abs(num);
            if ( num < nums.length && nums[num] > 0 ){
                nums[num] *= -1;
            }
        }
        for( int i = 1; i < nums.length;++i ){
            if ( nums[i] > 0 && nums[i-1] < 0 ){
                return i;
            }
        }
        return hasN ? (nums.length+1) : nums.length;
    }
}
