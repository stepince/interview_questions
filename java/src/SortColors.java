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
        for (int num : nums) {
            if ( num == 0 ) {
                ++reds;
            }
            else if (num == 1) {
                ++whites;
            }
        }
        for ( int red = 0; red < reds;++red){
            nums[red] = 0;
        }
        for ( int white = 0; white < whites;++white){
            nums[reds+white] = 1;
        }
        for ( int blue = 0; blue < nums.length - (reds+whites); ++blue){
            nums[reds+whites+blue] = 2;
        }
    }
    public static void main(String[] args){
        int[] nums = {2,0,1,1, 0,2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
