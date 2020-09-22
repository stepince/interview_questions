import java.util.Arrays;
/*

https://leetcode.com/problems/sort-colors/

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects
of the same color are adjacent, with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

 */
public class SortColors {

    static public void sortColors(int[] nums) {
        int reds = 0;
        int whites = 0;
        for ( int num : nums ) {
            reds += ( num == 0 ? 1 : 0);
            whites += ( num == 1 ? 1 : 0);
        }
        for ( int red = 0; red < reds; ++red) nums[red] = 0;
        for ( int white = reds; white < whites+reds; ++white) nums[white] = 1;
        for ( int blue = reds+whites; blue < nums.length; ++blue) nums[blue] = 2;
    }

    public static void main(String[] args){
        int[] nums = {2,0,1,1, 0,2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
